package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.enums.RoleValue;
import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;

import java.util.*;
import java.util.stream.Collectors;

/**
 * this class implements the war game locally
 */
public class LocalWarGame extends WarGameEngine {

    final Set<String> initialPlayers;
    final Map<String, ArrayList<Card>> playerCards = new HashMap<>();
    final Map<String, RoleValue> playerRole = new HashMap<>();

    public LocalWarGame(Set<String> initialPlayers) {
        this.initialPlayers = initialPlayers;
        for (String player : initialPlayers) {
            playerCards.put(player, new ArrayList<>());

        }
    }

    public static void main(String... args) {
        LocalWarGame localWarGame = new LocalWarGame(Set.of("Joueur1", "Joueur2", "Joueur3", "Joueur4"));
        localWarGame.play();

    }

    @Override
    protected Set<String> getInitialPlayers() {
        return this.initialPlayers;
    }

    @Override
    protected void giveCardsToPlayer(String playerName, String hand) {
        List<Card> cards = Arrays.asList(Card.stringToCards(hand));
        this.giveCardsToPlayer(cards, playerName);
    }

    @Override
    protected boolean playRound(Queue<String> players, String playerA, String playerB, Queue<Card> roundDeck) {
        System.out.println("New round:");
        System.out
                .println(
                        this.playerCards
                                .keySet().stream().filter(p -> !this.playerCards.get(p).isEmpty()).map(
                                        p -> p + " has "
                                                + this.playerCards.get(p).stream().map(c -> c.toFancyString())
                                                        .collect(Collectors.joining(" ")))
                                .collect(Collectors.joining("\n")));
        System.out.println();
        return super.playRound(players, playerA, playerB, roundDeck);

    }

    @Override
    protected void declareWinner(String winner) {
        System.out.println(winner + " has won!");
    }

    @Override
    protected Card getCardOrGameOver(Collection<Card> leftOverCard, String cardProviderPlayer,
            String cardProviderPlayerOpponent) {

        if (!this.playerCards.containsKey(cardProviderPlayer) || this.playerCards.get(cardProviderPlayer).isEmpty()) {
            this.playerCards.get(cardProviderPlayerOpponent).addAll(leftOverCard);
            this.playerCards.remove(cardProviderPlayer);
            return null;
        } else {
            return ((Queue<Card>) this.playerCards.get(cardProviderPlayer)).poll();
        }
    }

    @Override
    protected void giveCardsToPlayer(Collection<Card> roundStack, String winner) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(roundStack);
        Collections.shuffle(cards);
        this.playerCards.get(winner).addAll(cards);
    }

    @Override
    protected Card getCardFromPlayer(String player) throws NoMoreCardException {
        if (this.playerCards.get(player).isEmpty()) {
            throw new NoMoreCardException();
        } else {
            return ((Queue<Card>) this.playerCards.get(player)).poll();
        }
    }

    @Override
    public String getTrou() {
        return getRole(RoleValue.TROU);
    }

    private final static Card DAME_COEUR = Card.valueOf("QH");

    @Override
    protected void addFinishedPlayer(String currPlayer) {

    }

    @Override
    protected int getCurrentPlayerCount() {
        return 0;
    }

    @Override
    protected boolean isTapisFinished(Collection<Card> tapis) {
        return false;
    }

    @Override
    protected String getNextPlayer(String currPlayer) {
        return null;
    }

    @Override
    protected Collection<Card> playerPlayCards(String currPlayer, Collection<Card> tapis) throws NoMoreCardException {
        return null;
    }

    @Override
    protected Collection<Card> getBestCardsFromPlayer(String trou, int countCard) {
        return null;
    }

    @Override
    protected Collection<Card> getWorstCardsFromPlayer(String firstPlayerInRound, int countCard) {
        return null;
    }

    @Override
    protected String getPlayerWithQueenOFHeart() {
        for (String playerName : this.playerCards.keySet()) {
            if (this.playerCards.get(playerName).contains(DAME_COEUR)) {
                return playerName;
            }
        }
        throw new RuntimeException();
    }



    @Override
    protected String getPresident() {
      return this.getRole(RoleValue.PRESIDENT);
    }


    protected String getVicePresident() {
        return this.getRole(RoleValue.VICE_PRESIDENT);
    }


    protected String getRole(RoleValue role) {
        for (String playerName : this.playerRole.keySet()) {
            if (this.playerRole.get(playerName).equals(role)) {
                return playerName;
            }
        }
        throw new RuntimeException();
    }

    @Override
    protected boolean getFirstParty() {
        for (int i = 0; i < numberParty.length; i++) {
            if (i == 0) {
                firstPartie = true;
            } else {
                firstPartie = false;
            }
        }
        return firstPartie;
    }

    protected ArrayList<Card> getBestCardinPlayerHand() {
        ArrayList<Card> bestCard = new ArrayList();
        bestCard.add(((ArrayList<Card>) playerCards).get(0));
        for (Map.Entry m : playerCards.entrySet()) {
            for (int i = 1; i < playerCards.size(); i++) {
                if (((ArrayList<Card>) playerCards).get(i).getValue().compareTo(bestCard.get(0).getValue()) > 0) {
                    bestCard.remove(((ArrayList<Card>) playerCards).get(0));
                    bestCard.add(((ArrayList<Card>) playerCards).get(i));
                }
            }
        }
        return bestCard;

    }

    ArrayList<Card> manyCardPlayed = new ArrayList<>();

    public boolean verifyPair(Map<String, ArrayList<Card>> playerCards) {
        int count = 1;
        Card currentCard = ((ArrayList<Card>) playerCards).get(0); 
        manyCardPlayed.add(currentCard);
        for (String playerName : this.playerRole.keySet()) {
            for (int i = 1; i < playerCards.size(); i++) {
                if (((ArrayList<Card>) playerCards).get(i).getValue().compareTo(currentCard.getValue()) == 0) {
                    Card cardCarre = ((ArrayList<Card>) playerCards).get(i);
                    manyCardPlayed.add(cardCarre);
                    count++;
                }
            }
        }

            if (count == 2) {
                return true;
            }
        
        return false;

    }

    public boolean verifyBrelon(Map<String, ArrayList<Card>> playerCards) {
        int count = 1;
        Card currentCard = ((ArrayList<Card>) playerCards).get(0);
        manyCardPlayed.add(currentCard);
        for (String playerName : this.playerRole.keySet()) {
            for (int i = 1; i < playerCards.size(); i++) {
                if (((ArrayList<Card>) playerCards).get(i).getValue().compareTo(currentCard.getValue()) == 0) {
                    Card cardCarre = ((ArrayList<Card>) playerCards).get(i);
                    manyCardPlayed.add(cardCarre);
                    count++;
                }
            }
        }
            if (count == 3) {
                return true;
            }
        
        return false;
    }

    public boolean verifyCarre(Map<String, ArrayList<Card>> playerCards) {
        int count = 1;
        Card currentCard = ((ArrayList<Card>) playerCards).get(0);
        manyCardPlayed.add(currentCard);
        for (String playerName : this.playerRole.keySet()) {
            for (int i = 1; i < playerCards.size(); i++) {
                if (((ArrayList<Card>) playerCards).get(i).getValue().compareTo(currentCard.getValue()) == 0) {
                    Card cardCarre = ((ArrayList<Card>) playerCards).get(i);
                    manyCardPlayed.add(cardCarre);
                    count++;
                }
            }
        }
        if (count == 4) {
            return true;
        }
        return false;
    }

    public ArrayList<Card>  cardWhichThePlayerAreGoingToPlay(){
        if (verifyCarre(playerCards)){
            return manyCardPlayed;
        }
        else if (verifyBrelon(playerCards)){
            return manyCardPlayed;
        }
        else if (verifyPair(playerCards)){
            return manyCardPlayed;
        }
        else{
            return ((LocalWarGame) playerCards).getBestCardinPlayerHand();
        }

    }

    protected ArrayList<Card> passHisTurn(String player){
        ArrayList<Card> vide= new ArrayList<>();
        return vide;
    }

    
}
