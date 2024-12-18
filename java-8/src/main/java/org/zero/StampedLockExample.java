package org.zero;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.StampedLock;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/3
 */
public class StampedLockExample {
    private final StampedLock lock = new StampedLock();
    @Setter
    @Getter
    private double data = 0.0;

    public double optimisticRead() {
        // 尝试获取乐观读锁
        long stamp = lock.tryOptimisticRead();
        // 模拟读取数据
        double value = this.getData();
        // 检查锁是否仍然有效：如果无效，获取悲观读锁；如果有效也无需释放，因为乐观读锁本质是一种非阻塞的读操作
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                // 模拟重新读取数据
                value = this.getData();
            } finally {
                // 释放悲观读锁
                lock.unlockRead(stamp);
            }
        }
        return value;
    }

    public double read() {
        // 获取读锁
        long stamp = lock.readLock();
        try {
            return this.getData();
        } finally {
            // 释放读锁
            lock.unlockRead(stamp);
        }
    }

    public void write(double value) {
        // 获取写锁
        long stamp = lock.writeLock();
        try {
            this.setData(value);
        } finally {
            // 释放写锁
            lock.unlockWrite(stamp);
        }
    }

    public void readAndWrite() {
        // 获取悲观读锁
        long stamp = lock.readLock();
        boolean isWriteLock = false;
        try {
            // 模拟读取数据
            double value = this.getData();
            // 尝试将读锁升级为写锁
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if (writeStamp == 0L) {
                // 如果升级失败，先释放读锁，再获取写锁
                lock.unlockRead(stamp);
                writeStamp = lock.writeLock();
                isWriteLock = true;
                try {
                    // 模拟写入数据
                    this.setData(value);
                } finally {
                    // 释放写锁
                    lock.unlockWrite(writeStamp);
                }
            } else {
                isWriteLock = true;
                // 升级成功，使用新的写锁戳
                try {
                    // 模拟写入数据
                    this.setData(value);
                } finally {
                    // 释放写锁
                    lock.unlockWrite(writeStamp);
                }
            }
        } finally {
            // 只有在没有升级为写锁的情况下才释放读锁
            if (stamp != 0L && !isWriteLock) {
                lock.unlockRead(stamp);
            }
        }
    }
}
