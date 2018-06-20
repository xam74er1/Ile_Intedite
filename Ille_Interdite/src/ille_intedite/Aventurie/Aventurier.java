package ille_intedite.Aventurie;

import Carte.Carte;
import Carte.Classique;
import Carte.CarteTresor;
import Carte.NomTresor;
import IHM.IHM;
import ille_intedite.Controleur;
import ille_intedite.Grille;
import ille_intedite.Tuile;
import java.awt.Color;
import java.util.*;
import utils.Utils.Pion;

public abstract class Aventurier {
	//Com de debug
	ArrayList<Classique> listeCarteJoueur;


	private Tuile tuile;
	private int Num;
	private String nom;
	private NomTresor type;
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
	public void deplacer(Tuile t) {
		// TODO - implement Aventurier.deplacer
		setPosition(t);

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
		return ((x == 1 & y == 0) || (x == 0 & y == 1)) || (x==0 & y==0) & (to.getStatut() ==1);

	}



	/**
	 *
	 * @param t1.
	 * @param t2
	 */
	public boolean deplacementPossible(Tuile from, Tuile to) {
		System.out.println("to = "+to+" from = "+from);
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return ((x == 1 & y == 0) || (x == 0 & y == 1)) & (to.getStatut() < 2)&to.getNum()!=-1;
	}

	/**
	 *
	 * @param joueur
	 */
	public void donneCarteJoueur(Aventurier joueur) {
		// TODO - implement Aventurier.donneCarteJoeur
		throw new UnsupportedOperationException();
	}


	/**
	 *
	 * @param numCarte
	 */


	public void defausse() {
		// TODO - implement Aventurier.Defausse
		throw new UnsupportedOperationException();
	}


	/**
	 *
	 * @param main
	 * @return 
	 */




	public NomTresor recupereTresor() {
		int num = getTuile().getNum();
		int numTresor=0;
		if(num/100==3) {
			 numTresor = (num/10)%10;
			int nbr = 0;

			for(Carte c : listeCarteJoueur) {
				if(c instanceof CarteTresor) {
					CarteTresor ct = (CarteTresor) c;
					if(ct.getType().getNum()==num) {
						nbr ++;
					}
				}
			}

		}
		
		if(num>=4) {
		return NomTresor.getWisNum(numTresor);
		}
		
		return null;
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
	public NomTresor getType() {
		return type;
	}

	public void setType(NomTresor type) {
		this.type = type;
	}

	public int getNbAction() {
		return nbAction;
	}

	public void setNbAction(int nbAction) {
		this.nbAction = nbAction;
	}

	public void actionJouer() {
		nbAction--;
	}

	public void actionAnuller() {
		nbAction++;
	}

	public void finTour() {
		nbAction =3;
	}

	@Override
	public String toString() {
		return "Aventurier [Num=" + Num + ", nom=" + nom + ", getType=" + type + ", pion=" + pion + ", nbAction="
				+ nbAction + "]";
	}

	public ArrayList<Tuile> getAdjacent(Tuile from, ArrayList<Tuile> listTuile){
		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();
		int xF=from.getxT();
		int yF=from.getyT();

		Iterator<Tuile> it = listTuile.iterator();

		while(it.hasNext()) {
			Tuile to = it.next();

			int x = Math.abs(xF-to.getxT());
			int y = Math.abs(yF-to.getyT());

			if ((x==0 && y==1 || x==1 && y==0) && to.getStatut()!=2 && to.getNum()!=-1) {
				adjacent.add(to);
			}

		}

		return adjacent;


	}



	public ArrayList<Tuile> deplacer2(){
		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();

		for (Tuile t : Grille.tuilesListe.values()) {
			listTuile.add(t);
		}

		return getAdjacent(this.tuile, listTuile);

	}

	public ArrayList<Tuile> getAssecher(Tuile from, ArrayList<Tuile> listTuile){
		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();
		int xF=from.getxT();
		int yF=from.getyT();

		Iterator<Tuile> it = listTuile.iterator();

		while(it.hasNext()) {
			Tuile to = it.next();

			int x = Math.abs(xF-to.getxT());
			int y = Math.abs(yF-to.getyT());

			if ((x==0 && y==1 || x==1 && y==0 || to.equals(from)) && to.getStatut()==1 && to.getNum()!=-1) {
				adjacent.add(to);
			}

		}
		return adjacent;
	}

	public ArrayList<Tuile> assecher2(){
		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();

		for (Tuile t : Grille.tuilesListe.values()) {
			listTuile.add(t);
		}

		return getAssecher(this.tuile, listTuile);
	}

	public ArrayList<Aventurier> getJoueurTuile(){
		return this.getTuile().getAventurie();
	}

}

