package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokerDeck {
    List<PokerCard> deck;

    public PokerDeck()
    {
        deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                PokerCard a = new PokerCard(Suit.values()[i], Rank.values()[j]);
                deck.add(a);
            }
        }
        barajar();
    }

    private void barajar()
    {
        Random r = new Random();
        List<PokerCard> auxiliar = new ArrayList<>();
        while (deck.size() != 0)
        {
            int azar = r.nextInt(deck.size());
            auxiliar.add(deck.get(azar));
            deck.remove(azar);
        }
        deck.addAll(auxiliar);
    }

    public PokerCard drawCard()
    {
        PokerCard selected;
        selected = deck.get(0);
        deck.remove(0);
        return selected;
    }
}
