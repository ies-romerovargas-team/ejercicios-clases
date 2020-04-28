package com.company;

public class PokerHand {

    PokerCard[] hand = new PokerCard[5];

    public PokerHand(PokerCard n1, PokerCard n2, PokerCard n3, PokerCard n4, PokerCard n5)
    {
        hand[0] = n1;
        hand[1] = n2;
        hand[2] = n3;
        hand[3] = n4;
        hand[4] = n5;
        sortHand();
    }

    public void sortHand() {
        PokerCard[] auxiliar = new PokerCard[hand.length];
        PokerCard b = new PokerCard(null, null);
        // ordenar por rank
        for (int j = 0; j < hand.length; j++) {
            for (int i = 0; i < hand.length - 1; i++) {
                if (hand[i].rank.ordinal() > hand[i + 1].rank.ordinal()) {
                    b = hand[i];
                    hand[i] = hand[i + 1];
                    hand[i + 1] = b;
                }
            }
        }
        // ordenar por suit
        for (int j = 0; j < hand.length; j++) {
            for (int i = 0; i < hand.length - 1; i++) {
                if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal()) {
                    if(hand[i].suit.ordinal() > hand[i + 1].suit.ordinal())
                    {
                        b = hand[i];
                        hand[i] = hand[i + 1];
                        hand[i + 1] = b;
                    }
                }
            }
        }
    }

    public String toString()
    {
        String cards = "";
        for (int i = 0; i < hand.length; i++) {
            cards = cards + hand[i].toString();
            if(i < hand.length - 1) cards = cards + " ";
        }
        return cards;
    }

    public boolean isPair()
    {
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal()) return true;
        }
        return false;
    }

    public boolean isTwoPair()
    {
        int j = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal()) {
                j++;
                i++;
            }
        }
        return j == 2;
    }

    public boolean isThree()
    {
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal()) {
                if (i < hand.length - 2 && hand[i + 2].rank.ordinal() == hand[i].rank.ordinal()) return true;
            }
        }
        return false;
    }

    public boolean isStraight()
    {
        int j = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() - hand[i + 1].rank.ordinal() == -1) {
                j++;
            }
        }
        return j == 4;
    }

    public boolean isFlush()
    {
        int j = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].suit.ordinal() == hand[i + 1].suit.ordinal()) {
                j++;
            }
        }
        return j == 4;
    }

    public boolean isFull()
    {
        int pair = 0;
        int trio = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal())
            {
                pair++;
                if (i < hand.length - 2 && hand[i + 2].rank.ordinal() == hand[i].rank.ordinal()) trio++;
            }
        }
        return (trio == 1 && pair == 3);
    }

    public boolean isPoker()
    {
        int rep = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].rank.ordinal() == hand[i + 1].rank.ordinal())
            {
                rep++;
                if(rep == 3) return true;
            }
            else {
                rep = 0;
            }
        }
        return false;
    }

    public boolean isStraightFlush()
    {
        return (isFlush() && isStraight());
    }

    public boolean isRoyalFlush()
    {
        return (isStraightFlush() && hand[4].rank == Rank.Ace);
    }
}
