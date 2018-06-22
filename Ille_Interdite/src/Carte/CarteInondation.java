package Carte;

import Carte.Carte;
import ille_intedite.Tuile;

public class CarteInondation extends Carte {

	Tuile tuile;

    public CarteInondation(String nom,Tuile tuile) {
    	super(nom);
        this.tuile = tuile;
    }
    
	public Tuile getTuile() {
		return tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}

}