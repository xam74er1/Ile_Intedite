package ille_intedite;
import Carte.Carte;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;

import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{

	Curseur curseur;
	Grille grille;
	ArrayList<CarteTresor> carteTresorDeck;
	ArrayList<CarteTresor> carteTresorsDefausse;
	ArrayList<CarteInondation> inondationDeck;
	ArrayList<CarteInondation> inondationDefausse;
	ArrayList<Aventurier> joueursList;
	private int NBR_JOUEUR = 4;
	// Dernere action effectuer 
	private  TypeMessage lastAction = null;
	private int numTour;


	IHM ihm;

	public Controleur(IHM ihm) {
		this.ihm = ihm;
		joueursList = new ArrayList<Aventurier>();
		init();
		miseAJourGrille();
		numTour =0;
		NBR_JOUEUR = joueursList.size();
		Utils.debugln("controleur start");

	}




	@Override
	public void traiterMessage(Message msg) {

		if(msg.getMessage() == TypeMessage.Clique_Deplace) {
			ihm.afichierConsole("Cliquer sur une classe pour vous deplace");


			//Fair deplce joeur 
		}else if(msg.getMessage() == TypeMessage.Clique_Asseche){

			ihm.afichierConsole("Cliquer sur une classe pour l'assecher");

		}else if(msg.getMessage() == TypeMessage.Clique_Tuille && lastAction == TypeMessage.Clique_Deplace) {
			Utils.debugln("Tuille = "+msg.getLocation());

			if(deplacer(msg.getLocation())) {
				ihm.afichierConsole("Deplacement en "+msg.getLocation());
			}else {
				ihm.addConsole("Vous ne pouvez pas vous deplace en  "+msg.getLocation());
				//Pour ne pas fair perdre une action 
				getJoueurTour().actionAnuller();
			}


		}else if(msg.getMessage() == TypeMessage.Clique_Tuille && lastAction == TypeMessage.Clique_Asseche){
				
			if(assecher(msg.getLocation())){
				ihm.afichierConsole("Casse assache en "+msg.getLocation());
			}else{
				ihm.addConsole("Vous ne pouvez pas asseche en  "+msg.getLocation());
				//Pour ne pas fair perdre une action 
				getJoueurTour().actionAnuller();
			}

		}else if(msg.getMessage() == TypeMessage.Clique_Fin_Tour){
			finDeTour();
			Utils.debugln("player n ="+getJoueurTour().toString());
		}

		lastAction = msg.getMessage();
		getJoueurTour().actionJouer();

		if(getJoueurTour().getNbAction()<1) {
			finDeTour();
		}

	}

	private void finDeTour() {
		// TODO Auto-generated method stub
		piocherInondation();
		numTour++;
		Utils.debugln("PAS ENCORE SUPORTE");
		
	}
	
////////////////////////	
//	Gestion des carte //	
////////////////////////
	
//	Mise en place du deck Inondation //\\ actuellement sur plus de 24 Tuile
	public void creeDeckInondation() {
		for(int x = 0;x<6;x++){
            for(int y =0;y<6;y++){
			inondationDeck.add(new CarteInondation(x+":"+y, grille.getTuile(x, y)));
            }
		}
//		Liste A randomisé par la suite
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
		grille = new Grille(ihm);

		//Metre les tuille de depare 
		int i =0;
		for(Pion p : Pion.values()){
			Aventurier a = new Aventurier(i,"Bob Morane",p);
			a.setPosition(grille.getTuile(i, 0));
			joueursList.add(a);
			i++;
		}

		//Provisoire pour les test 

		//Je met sur 0 0 pour les test 
		
		
		grille.getTuile(2,1).inonder();
		grille.getTuile(2,2).inonder();
		
		
		
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
		// TODO - implement Controleur.piocherTresor
		throw new UnsupportedOperationException();
	}

//	Mise en place de la pioche et deffause auto des carte innondation
	private void piocherInondation() {
		CarteInondation Cin = inondationDeck.get(1);
		Cin.getTuile().inonder();
		inondationDefausse.add(Cin);
		inondationDeck.remove(Cin);
	}


	//Provisoire 
	@Deprecated
	public Aventurier getJoueurTour() {
		int i =  numTour%(NBR_JOUEUR-1);
		
		Utils.debugln(" jouer n = "+i);
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

			if(me.getValue().getStatue()==1){
				ihm.getButonPlateau(me.getKey()).setBackground(Color.BLUE);
			}else if(me.getValue().getStatue()>1){
				ihm.getButonPlateau(me.getKey()).setBackground(Color.BLUE);
			}else{
				ihm.getButonPlateau(me.getKey()).setForeground(Color.BLACK);
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