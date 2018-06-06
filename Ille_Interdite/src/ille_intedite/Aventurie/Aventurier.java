package ille_intedite.Aventurie;

import Carte.Carte;
import Carte.Classique;
import ille_intedite.CarteTresor;
import ille_intedite.NomTresor;
import ille_intedite.Tuile;
import java.awt.Color;
import java.util.*;
import utils.Utils.Pion;

public class Aventurier {
//Com de debug
	ArrayList<Classique> listeCarteJoueur;
	

	Tuile tuile;
	private int Num;
	private String nom;
	private NomTresor getType;
	private Pion pion;
	// nbr d'action pouvant etre jouer dans le tour 
	private int nbAction = 3;

	public Aventurier(int Num, String nom,Pion pion) {
		this.Num = Num;
		this.nom = nom;
		this.tuile = null;
		this.pion = pion;
		listeCarteJoueur = new ArrayList<Classique>();
	}

	public Tuile getPosition() {
		// TODO - implement Aventurier.getPosition
		return this.tuile;
	}

	/**
	 *
	 * @param xG
	 * @param yG
	 */

	public ArrayList<Classique> getListeCarteJoueur() {
		return listeCarteJoueur;
	}

	public void setListeCarteJoueur(ArrayList<Classique> listeCarteJoueur) {
		this.listeCarteJoueur = listeCarteJoueur;
	}

	/**
	 *
	 * @param t
	 */
	public void setPosition(Tuile t) {
		// TODO - implement Aventurier.setPosition

		this.setTuile(t);
	}

	/**
	 *
	 * @param t
	 */
	//Action pour deplace la perssone 
	public boolean deplacer(Tuile t) {
		// TODO - implement Aventurier.deplacer
		if (deplacementPossible(getTuile(), t)) {
			setPosition(t);
			return true;
		}else {
			return false;
		}

	}

	public boolean assecher(Tuile t){

		if(assecherPossible(getTuile(), t)){
			t.assecher();
			return true;
		}
		return false;

	}

	public boolean assecherPossible(Tuile from, Tuile to) {
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return ((x == 1 & y == 0) || (x == 0 & y == 1)) || (x==0 & y==0) & (to.getStatue() ==1);
	}



	/**
	 *
	 * @param t1.
	 * @param t2
	 */
	public static boolean deplacementPossible(Tuile from, Tuile to) {
		System.out.println("to = "+to+" from = "+from);
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return ((x == 1 & y == 0) || (x == 0 & y == 1)) & (to.getStatue() < 2)&to.getNum()!=-1;
	}

	/**
	 *
	 * @param joeur
	 */
	public void donneCarteJoeur(Aventurier joeur) {
		// TODO - implement Aventurier.donneCarteJoeur
		throw new UnsupportedOperationException();
	}


	/**
	 *
	 * @param numCarte
	 */
	

	public void Defausse() {
		// TODO - implement Aventurier.Defausse
		throw new UnsupportedOperationException();
	}


	/**
	 *
	 * @param main
	 */
	
	

	public void RecupereTresort() {
		// TODO - implement Aventurier.RecupereTresort
		throw new UnsupportedOperationException();
	}

	public Tuile getTuile() {
		return tuile;
	}

	// Place dans une nouvelle case 
	private void setTuile(Tuile t) {
		// Enleve laventuire de la case , lui affecte une nvll case et lajoute as la nvll case 
		if(this.getTuile()!=null){
			this.getTuile().removeAventurie(this);
		}

		this.tuile = t;
		//System.out.println("t = "+t);
		t.addAventurie(this);
	}

	public int getNum() {
		return Num;
	}

	public Pion getPion() {
		return pion;
	}
    public int getNbCarte() {
        return listeCarteJoueur.size();
    }


    /**
     *
     * @param numCarte
     */
    public Carte getCarte(int numCarte) {
        // TODO - implement Aventurier.getCarte
        return listeCarteJoueur.get(numCarte);
    }

	public Color getColor(){

		return pion.getCouleur();
	}
    /**
     *
     * @param C
     */
    public void addCarte(Classique C) {
        // TODO - implement Aventurier.addCarte
    	listeCarteJoueur.add(C);
    }

	public void setNum(int Num) {
		this.Num = Num;
	}
    /**
     *
     * @param c
     */
    public void removeCarte(Classique c) {
        // TODO - implement Aventurier.removeCarte
    	listeCarteJoueur.remove(c);
    }

	public String getNom() {
		return nom;
	}
    /**
     *
     * @param c
     */
    public void addDefausePerso(CarteTresor c) {
        // TODO - implement Aventurier.addDefausePerso
        throw new UnsupportedOperationException();
    }

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void restoreMain(ArrayList<Classique> main) {
        // TODO - implement Aventurier.restoreMain
        throw new UnsupportedOperationException();
    }
	public NomTresor getGetType() {
		return getType;
	}

	public void setGetType(NomTresor getType) {
		this.getType = getType;
	}

	public int getNbAction() {
		return nbAction;
	}

	public void setNbAction(int nbAction) {
		this.nbAction = nbAction;
	}

	public void actionJouer() {
		nbAction --;
	}

	public void actionAnuller() {
		nbAction++;
	}

	public void finTour() {
		nbAction =3;
	}

	@Override
	public String toString() {
		return "Aventurier [Num=" + Num + ", nom=" + nom + ", getType=" + getType + ", pion=" + pion + ", nbAction="
				+ nbAction + "]";
	}



}
