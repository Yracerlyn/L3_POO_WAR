package fr.pantheonsorbonne.miage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Role;

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
        Queue<Role> role1 = new LinkedList<>();
        Queue<Role> role2 = new LinkedList<>();

        role1.add(Role.haveRole("1"));
        role2.add(Role.haveRole("2"));

        test2.playerRole.put("J1", role1);
        test2.playerRole.put("J2", role2);

        assertEquals("J1", test2.getPresident());

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
