package com.company;

public class PokerCard {

    Rank rank;
    Suit suit;

    String ANSI_RED="\u001B[31m";
    String ANSI_RESET="\u001B[0m";

    public PokerCard(Suit suit, Rank rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    public String toString()
    {
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] deck = {"♥", "♦", "♣", "♠"};
        String card = values[rank.ordinal()];
        if(suit.ordinal() < 2) card = card + ANSI_RED;
        card = card + deck[suit.ordinal()];
        card = card + ANSI_RESET;
        return card;
    }
}
