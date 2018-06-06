package ille_intedite;

import Carte.Carte;

public class CarteInondation extends Carte {

	Tuile tuile;
	String nom;

    public CarteInondation(String nom,Tuile tuile) {
    	super(nom);
        this.tuile = tuile;
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Tuile getTuile() {
		return tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}

}