package fr.pantheonsorbonne.miage.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.pantheonsorbonne.miage.game.Card;

public class pile {
    protected List<Card> cartes = new ArrayList<Card>();
	
	public pile() {
		Arrays.fill(cartes.toArray(), null);
	}
	
	public List<Card> getCartes() {
		return this.cartes;
	}
	
	public Card getCarte(int index) {
		return this.cartes.get(index);
	}
	
	public void ajouterCarte(Card carte) {
		this.cartes.add(carte);
	}

    public void reinitialiser() {
		this.cartes.clear();
	}
	
	public boolean isVide() {
		return this.cartes.isEmpty();
	}

    
}
    

