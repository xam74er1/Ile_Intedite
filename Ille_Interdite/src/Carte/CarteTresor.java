package Carte;

import Carte.Classique;
import Carte.NomTresor;

public class CarteTresor extends Classique {

	private NomTresor type;

    public CarteTresor(String nom,NomTresor type) {
        super(nom);
        this.type = type;
    }

	public NomTresor getType() {
		return type;
	}

	public void setType(NomTresor type) {
		this.type = type;
	}
    
    

}