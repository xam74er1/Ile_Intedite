package Carte;

import ille_intedite.Aventurie.Aventurier;

public abstract class Carte {

	Aventurier apartienA;
	private String nom;

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public Carte(String nom) {
		// TODO - implement Carte.Carte
		this.nom = nom;
	}
        //test netbeans
}