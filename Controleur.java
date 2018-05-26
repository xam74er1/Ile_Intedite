package ille_intedite;
import Carte.Carte;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import java.util.Map.Entry;

import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{

	Curseur curseur;
	Grille grille;
	ArrayList<Aventurier> joueursList;
	private final int NBR_JOUEUR = 4;
	// Dernere action effectuer 
	private  TypeMessage lastAction = null;
	private int numTour;


	IHM ihm;

	public Controleur(IHM ihm) {
		this.ihm = ihm;
		joueursList = new ArrayList();
		init();
		miseAJourGrille();

		Utils.debugln("controleur start");

	}




	@Override
	public void traiterMessage(Message msg) {

		if(msg.getMessage() == TypeMessage.Clique_Deplace) {
			ihm.afichierConsole("Cliquer sur une classe pour vous deplace");
		}else if(msg.getMessage() == TypeMessage.Clique_Tuille && lastAction == TypeMessage.Clique_Deplace) {
			Utils.debugln("Tuille = "+msg.getLocation());

			if(deplacer(msg.getLocation())) {
				ihm.afichierConsole("Deplacement en "+msg.getLocation());
			}else {
				ihm.addConsole("Vous ne pouvez pas vous deplace en  "+msg.getLocation());
				//Pour ne pas fair perdre une action 
				getJoueurTour().actionAnuller();
			}


		}

		lastAction = msg.getMessage();
		getJoueurTour().actionJouer();

		if(getJoueurTour().getNbAction()<1) {
			finDeTour();
		}

	}

	private void finDeTour() {
		// TODO Auto-generated method stub
		Utils.debugln("PAS ENCORE SUPORTE");
	}




	public void init() {
		grille = new Grille(ihm);

		for(int i = 0;i<NBR_JOUEUR;i++){
			// Par defaut en attendent 
			Aventurier a = new Aventurier(i,"Bob Morane",Pion.ROUGE);
			joueursList.add(a);
		}

		//Provisoire pour les test 

		//Je met sur 0 0 pour les test 
		Tuile t = grille.getTuile(1,1);
		System.out.println(t.toString());
		joueursList.get(0).setPosition(t);
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
		// TODO - implement Controleur.conditionDéfaite
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

	private void assecher() {
		// TODO - implement Controleur.assecher
		throw new UnsupportedOperationException();
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
		// TODO - implement Controleur.piocherTresor
		throw new UnsupportedOperationException();
	}

	private void piocherInondation() {
		// TODO - implement Controleur.piocherInondation
		throw new UnsupportedOperationException();
	}


	//Provisoire 
	@Deprecated
	public Aventurier getJoueurTour() {
		return joueursList.get(0);

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

		HashMap<String, Tuile> hmTuille = ((Grille) grille).getTuilesListe();

		Iterator<Entry<String, Tuile>> it = hmTuille.entrySet().iterator();

		while(it.hasNext()) {
			Entry<String, Tuile> me = it.next();

			//Jefasse laventurie 
			ihm.getButonPlateau(me.getKey()).setBackground(null);
			//Puis je redessine 
			for(Aventurier a : me.getValue().getAventurie()) {
				//Provisoire 
				ihm.getButonPlateau(me.getKey()).setBackground(a.getColor());;

			}

		}


		//                
		//                for(Aventurier a : joueursList){
		//                    Tuile t = a.getTuile();
		//                    
		//                    if(t != null){
		//                        ihm.getButonPlateau(t.getxT()+":"+t.getyT()).setBackground(a.getColor());
		//                    }
		//                    
		//                    
		//                }


	}
}