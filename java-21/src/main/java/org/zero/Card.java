package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public sealed interface Card permits BoxedCard, Domino, Mahjong, Poker, Bridge {
   default String getName(){
      return this.getClass().getSimpleName();
   }

   Number getTotal();
}
