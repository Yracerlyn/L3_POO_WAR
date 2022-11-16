package fr.pantheonsorbonne.miage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Card;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    
    @Test
    public void getPlayerWithQueenOFHeart() {
        HashSet<String> players = new HashSet<>();
        players.add("J1");
        players.add("J2");

        var test1 = new LocalWarGame(players);
        Queue<Card> cardJ1 = new LinkedList<>();
        Queue<Card> cardJ2 = new LinkedList<>();
        cardJ1.add(Card.valueOf("QH"));
        cardJ2.add(Card.valueOf("KH"));

        test1.playerCards.put("J1", cardJ1);
        test1.playerCards.put("J2", cardJ2);

        assertEquals("J1", test1.getPlayerWithQueenOFHeart());
    }

}
