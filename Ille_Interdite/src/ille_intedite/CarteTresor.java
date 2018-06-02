package ille_intedite;

import Carte.Classique;

public class CarteTresor extends Classique {

	private NomTresor type;

    public CarteTresor(String nom,NomTresor type) {
        super(nom);
        this.type = type;
    }

}