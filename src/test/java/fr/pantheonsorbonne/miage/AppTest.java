package fr.pantheonsorbonne.miage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import fr.pantheonsorbonne.miage.enums.RoleValue;
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
        ArrayList<Card> cardJ1 = new ArrayList<>();
        ArrayList<Card> cardJ2 = new ArrayList<>();
        cardJ1.add(Card.valueOf("QH"));
        cardJ2.add(Card.valueOf("KH"));

        test1.playerCards.put("J1", cardJ1);
        test1.playerCards.put("J2", cardJ2);

        assertEquals("J1", test1.getPlayerWithQueenOFHeart());
    }

    @Test
    public void getPresident() throws Exception{
        HashSet<String> players = new HashSet<>();
        players.add("J1");
        players.add("J2");

        var test2 = new LocalWarGame(players);

        test2.playerRole.put("J1", RoleValue.PRESIDENT);
        test2.playerRole.put("J2", RoleValue.TROU);

        assertEquals("J1", test2.getPresident());
        assertEquals("J2", test2.getTrou());

    }

   /*  @Test
    public void getBestCardinPlayerHand() {
        HashSet<String> players = new HashSet<>();
        players.add("J1");

        var test3 = new LocalWarGame(players);
        ArrayList<Card> cardJ1 = new ArrayList<>();
        ArrayList<Card> cardJ2 = new ArrayList<>();
        cardJ1.add(Card.valueOf("QH"));
        cardJ2.add(Card.valueOf("KH"));

        test3.playerCards.put("J1", cardJ1);
        test3.playerCards.put("J1", cardJ2);

        assertEquals("KH", test3.getBestCardinPlayerHand(players.get(0)));
    }*/

    @Test
    public void getBestCardinPlayerHand(){
        HashSet<String> players = new HashSet<>();
        players.add("J1");

        var test1 = new LocalWarGame(players);
        ArrayList<Card> cardJ1 = new ArrayList<>();
        cardJ1.add(Card.valueOf("QH"));
        cardJ1.add(Card.valueOf("KH"));

        test1.playerCards.put("J1", cardJ1);


        assertEquals("KH", test1.getBestCardinPlayerHand());    
    }

}
