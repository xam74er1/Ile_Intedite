package ille_intedite;
import Carte.Carte;
import Carte.Classique;
import Carte.CarteInondation;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;

import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{
	//Com de referencement 1
	Curseur curseur;
	Grille grille;
	ArrayList<Classique> carteTresorDeck;
	ArrayList<Classique> carteTresorsDefausse;
	ArrayList<CarteInondation> inondationDeck;
	ArrayList<CarteInondation> inondationDefausse;
	ArrayList<Aventurier> joueursList;
	private int NBR_JOUEUR = 4;
	// Dernere action effectuer 
	private  TypeMessage lastAction = null;
	private int numTour;
	private VueGrille vue;


	IHM ihm;

	public Controleur(IHM ihm,VueGrille vue) {
		this.ihm = ihm;
		carteTresorDeck = new ArrayList<Classique>();
		carteTresorsDefausse = new ArrayList<Classique>();
		inondationDeck = new ArrayList<CarteInondation>();
		inondationDefausse = new ArrayList<CarteInondation>();
		joueursList = new ArrayList<Aventurier>();
		this.vue = vue;
		init();
		numTour =0;
		NBR_JOUEUR = joueursList.size();
		//Utils.debugln("controleur start");

	}




	@Override
	public void traiterMessage(Message msg) {


		/*
		 * Marche as suire pour une action jouer par tour : 
		 * Si laction est valide et fini utiliser : getJoueurTour().actionJouer(); 
		 * Cela permet de savoir le nombre daction jouer en un tour par une perssone 
		 */

		//Mesage pour depalcer 

		switch(msg.getMessage()) {
		case Clique_Deplace :
			ihm.afichierConsole("Cliquer sur une classe pour vous deplace");
			break;

		case Clique_Asseche :
			ihm.afichierConsole("Cliquer sur une classe pour l'assecher");
			break;

		case Clique_Tuille :
			switch(lastAction) {
			case Clique_Deplace:


				//Utils.debugln("Tuille = "+msg.getLocation());

				//Si le deplacement cest bien passe 
				if(deplacer(msg.getLocation())) {
					ihm.afichierConsole("Deplacement en "+msg.getLocation());
					getJoueurTour().actionJouer();
					//Si le deplacement cest mal passe 
				}else {
					Tuile to =getJoueurTour().getPosition();
					ihm.addConsole("Vous ne pouvez pas vous deplace de"+to.getxT()+":"+to.getyT()+" a  "+msg.getLocation());
					//Pour ne pas fair perdre une action 

				}

				break;

			case Clique_Asseche :

				break;


			}


			if(assecher(msg.getLocation())){
				ihm.afichierConsole("Casse assache en "+msg.getLocation());
				getJoueurTour().actionJouer();
			}else{
				ihm.addConsole("Vous ne pouvez pas asseche en  "+msg.getLocation());
				//Pour ne pas fair perdre une action 

			}


			break;

		case Clique_Fin_Tour :
			finDeTour();
			break;
		}


		//Si la conditon au dessu est fausse elle continue 




		lastAction = msg.getMessage();


		if(getJoueurTour().getNbAction()<1) {
			//	System.out.println(" nb act = "+getJoueurTour().getNbAction());
			finDeTour();
		}

	}

	private void finDeTour() {
		// TODO Auto-generated method stub
		ihm.afichierConsole("Fin du tour du joeur n°"+numTour);

		getJoueurTour().finTour();
		piocherInondation();
		System.out.println("woua");
		numTour++;
		numTour%=joueursList.size();

		ihm.addConsole("Jouer n°"+numTour+" as vous de jouer");
		ihm.miseAJourPlayer(numTour, getJoueurTour().getColor());
		//	Utils.debugln("Fin de tour");

	}

	////////////////////////	
	//	Gestion des carte //	
	////////////////////////

	//	Mise en place du deck Inondation //\\ actuellement sur plus de 24 Tuile
	public void creeDeckInondation() {
		for(Tuile t :grille.getTuilesListe().values()){
			if(t.getNum()!=-1) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}

		}
		Collections.shuffle(inondationDeck);
	}

	public void creeDeckClassique() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<5;j++) {
				switch (i) {
				case 1:
					//					carteTresorDeck.add(new CarteTresor("carte"+i+":"+j, ))  A modifier
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				}
			}
		}


	}





	public void init() {
		int i =0;//creer les aventuriers

		for(Pion p : Pion.values()){
			Aventurier a = new Aventurier(i,"Bob Morane",p);

			//a.setPosition(grille.getTuile(i, 0));
			joueursList.add(a);
			i++;
		}

		grille = new Grille(ihm,joueursList);

		ihm.fillPlataux2(grille);
		//Metre les tuille de depare 

		//Provisoire pour les test 

		//Je met sur 0 0 pour les test 


		creeDeckInondation();

		ihm.miseAJourPlayer(0, getJoueurTour().getColor());
	}

	private void nextJoueur() {
		// TODO - implement Controleur.nextJoueur
		throw new UnsupportedOperationException();
	}

	private void conditionVictoire() {
		// TODO - implement Controleur.conditionVictoire
		throw new UnsupportedOperationException();
	}

	private void conditionDefaite() {
		// TODO - implement Controleur.conditionDÃ©faite
		throw new UnsupportedOperationException();
	}

	private boolean deplacer(String str) {
		Aventurier a = getJoueurTour();

		Tuile t = grille.getTuile(str);

		if(a.deplacer(t)) {
			miseAJourGrille();
			return true;
		}else {
			return false;
		}



	}

	private boolean assecher(String str) {
		// TODO - implement Controleur.assecher
		Aventurier a = getJoueurTour();
		Tuile t = grille.getTuile(str);
		if (a instanceof Aventurier) {
			if (a.assecher(t)) {
				miseAJourGrille();
			}
		}

		if(a.assecher(t)){
			miseAJourGrille();
			return true;
		}
		return false;

	}

	private void donneCarte() {
		// TODO - implement Controleur.donneCarte
		throw new UnsupportedOperationException();
	}

	private void actSpeciale() {
		// TODO - implement Controleur.actSpeciale
		throw new UnsupportedOperationException();
	}

	private void piocherTresor() {

		for(int i =0;i<2;i++) {
			Classique cC = carteTresorDeck.get(1);
			getJoueurTour().getListeCarteJoueur().add(cC);
		}

	}

	//	Mise en place de la pioche et deffause auto des carte innondation
	private void piocherInondation() {
		if(inondationDeck.size()!=0) {
			CarteInondation cInP = inondationDeck.get(0);
			cInP.getTuile().inonder();
			inondationDefausse.add(cInP);
			inondationDeck.remove(cInP);
		}
		else {
			for(int i=0;i<inondationDefausse.size();i++) {
				CarteInondation cInD = inondationDefausse.get(0);
				inondationDeck.add(cInD);
				inondationDefausse.remove(cInD);
			}
			Collections.shuffle(inondationDeck);
			piocherInondation();		
		}
		miseAJourGrille();
	}


	//Provisoire 
	@Deprecated
	public Aventurier getJoueurTour() {
		int i =  numTour%(joueursList.size());

		//	Utils.debugln(" jouer n = "+i);
		return joueursList.get(i);

	}

	public void recupereTresort() {
		// TODO - implement Controleur.recupereTresort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numJoeur
	 */
	public void getAventurie(int numJoeur) {
		// TODO - implement Controleur.getAventurie
		throw new UnsupportedOperationException();
	}

	public void DonneCarte() {
		// TODO - implement Controleur.DonneCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param main
	 */
	public void addDefausse(ArrayList<Carte> main) {
		// TODO - implement Controleur.addDefausse
		throw new UnsupportedOperationException();
	}


	//Provisoire 
	// A optimiser 
	public void miseAJourGrille() {

		//Provisoire 

		vue.afficherGrille();

	}
}
