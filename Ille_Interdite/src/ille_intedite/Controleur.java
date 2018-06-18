
package ille_intedite;
import Carte.Carte;
import Carte.CarteHelicoptere;
import Carte.Classique;
import Carte.CarteInondation;
import Carte.CarteTresor;
import Carte.CarteSacSable;
import Carte.MonteeEaux;
import Carte.NomTresor;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import ille_intedite.Aventurie.Aviateur;
import ille_intedite.Aventurie.Explorateur;
import ille_intedite.Aventurie.Ingenieure;
import ille_intedite.Aventurie.Messager;
import ille_intedite.Aventurie.Navigateur;
import ille_intedite.Aventurie.Plongeur;
import Carte.NomTresor;
import Carte.CarteHelicoptere;

import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import utils.Parameters;
import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{
	//Com de referencement 1
	Curseur curseur;
	Grille grille;
	ArrayList<Classique> carteTresorDeck;
	public ArrayList<Classique> carteTresorsDefausse;
	ArrayList<CarteInondation> inondationDeck;
	public ArrayList<CarteInondation> inondationDefausse;
	ArrayList<Aventurier> joueursList;
	String messageConsole;
	private int NBR_JOUEUR = 4;
	// Dernere action effectuer 
	private  TypeMessage lastAction = TypeMessage.Clique_Send;
	private int numTour;
	private VueGrille vue;
	private ArrayList<String> tresorsRecuperes = new ArrayList<>();


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
			deplacer2();
			ihm.afichierConsole("Cliquer sur une classe pour vous deplace");
			break;

		case Clique_Asseche :
			assecher2();
			ihm.afichierConsole("Cliquer sur une classe pour l'assecher");
			break;

		case Clique_Tuille :
			switch(lastAction) {
			case Clique_Deplace:


				//Utils.debugln("Tuille = "+msg.getLocation());

				//Si le deplacement cest bien passe 
				deplacer(msg.getText());
				grille.activateAll();
				miseAJourGrille();
				getJoueurTour().actionJouer();

				break;

			case Clique_Asseche :
				assecher(msg.getText());
				grille.activateAll();
				miseAJourGrille();
				getJoueurTour().actionJouer();
				break;

				
			case Clique_Deplace_Urgence :
				
				deplacer(msg.getText());
				grille.activateAll();
				miseAJourGrille();
				
			case Clique_Deplace_Helico :
				// A modifier
				if (grille.getTuile(msg.getLocation()).getStatue() != 2 && grille.getTuile(msg.getLocation()).getStatue() != -1) {
					Tuile t = grille.getTuile(msg.getLocation());
					for(int i=0;i<t.getAventurie().size();i++) {
						
					}
					
				}
				else {
					ihm.addConsole("Vous ne pouvez pas utiliser l'helicopter sur cette case "+msg.getLocation());
					//Pour ne pas fair perdre une action 

				}
				break;
				
			case Clique_Asseche_SacDeSable :
				System.out.println("Assecher");
				if(grille.getTuile(msg.getLocation()).getStatue() == 1){
					grille.getTuile(msg.getLocation()).assecher();
					ihm.afichierConsole("Casse assache en "+msg.getLocation());
					miseAJourGrille();
				}else{
					ihm.addConsole("Vous ne pouvez pas asseche en  "+msg.getLocation());
					//Pour ne pas fair perdre une action
				}
				break;

			}





			break;

		case Clique_Fin_Tour :
			finDeTour();
			break;
			
		case Clique_Send :
			messageConsole = msg.getText();
			break;
		}
	}

	private void finDeTour() {
		// TODO Auto-generated method stub
		ihm.afichierConsole("Fin du tour du joeur nÂ°"+numTour);

		getJoueurTour().finTour();
		
		for(int i =0;i<curseur.getNbCartesInond();i++) {
			piocherInondation();
		}
		piocherTresor();
		piocherTresor();
		
		numTour++;
		numTour%=joueursList.size();
		afficherListeCarteJoueur();

		ihm.addConsole("Jouer nÂ°"+numTour+" as vous de jouer");
		ihm.miseAJourPlayer(numTour," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
		//	Utils.debugln("Fin de tour");
		grille.activateAll();
		miseAJourGrille();

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

		if(Parameters.ALEAS) {
			Collections.shuffle(inondationDeck);
		}

	}

	public void creeDeckClassique() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<5;j++) {
				switch (i) {
				case 1:
					carteTresorDeck.add(new CarteTresor("CristalArdent"+j, NomTresor.CristalArdent));
					break;
				case 2:
					// Calise
					carteTresorDeck.add(new CarteTresor("CaliceOnde"+j, NomTresor.CaliceOnde));
					break;
				case 3:
					// Statue
					carteTresorDeck.add(new CarteTresor("StatueZephir"+j, NomTresor.StatueZephir));
					break;
				case 4:
					// Pierre
					carteTresorDeck.add(new CarteTresor("PierreSacree"+j, NomTresor.PierreSacree));
					break;
				}
			}

			carteTresorDeck.add(new CarteSacSable("Sac de sable 1"));
			carteTresorDeck.add(new CarteSacSable("Sac de sable 2"));
			carteTresorDeck.add(new CarteSacSable("Sac de sable 3"));

			carteTresorDeck.add(new MonteeEaux("Monte des EAU 1"));
			carteTresorDeck.add(new MonteeEaux("Monte des EAU 2"));
			//carteTresorDeck.add(new MonteeEaux("Monte des EAU 3"));

		}
		if(Parameters.ALEAS) {
			Collections.shuffle(carteTresorDeck);
		}

	}

	public void piocherClassique() {
		if(carteTresorDeck.size() != 0) {
			Classique cC = carteTresorDeck.get(0);
			if(cC instanceof CarteTresor || cC instanceof CarteSacSable || cC instanceof CarteHelicoptere ) {
				getJoueurTour().getListeCarteJoueur().add(cC);	
				carteTresorDeck.remove(cC);
			}
			else {
				carteTresorsDefausse.add(cC);
				curseur.monteeEaux();
				carteTresorDeck.remove(cC);
			}

		}
	}





	public void init() {
		//creer les aventuriers
		Aventurier a;
		//Marche

		a = new Ingenieure(0,"Ingenieure",Pion.ROUGE);

		joueursList.add(a);

		a = new Plongeur(4,"Plongeur",Pion.VIOLET);
		joueursList.add(a);

		a = new Navigateur(5,"Navigateur",Pion.JAUNE);
		joueursList.add(a);

		a = new Messager(3,"Messager",Pion.ORANGE);
		joueursList.add(a);

		a = new Aviateur(2,"Aviateur",Pion.BLEU);

		joueursList.add(a);

		a = new Explorateur(1,"Explorateur",Pion.VERT);

		joueursList.add(a);



		//		int i = 0;
		//				for(Pion p : Pion.values()){
		//					Aventurier a = new Aventurier(i,"Bob Morane",p);
		//		
		//					//a.setPosition(grille.getTuile(i, 0));
		//					joueursList.add(a);
		//					i++;
		//				}

		grille = new Grille(ihm,joueursList);

		curseur = new Curseur(0);

		ihm.fillPlataux2(grille);
		//Metre les tuille de depare 

		//Provisoire pour les test 

		//Je met sur 0 0 pour les test 

		creeDeckInondation();
		creeDeckClassique();


		//		for(int j =0;j<5;j++) {
		//			piocherInondation();
		//		}

		ihm.miseAJourPlayer(0," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
	}

	private void conditionVictoire() {
		// TODO - implement Controleur.conditionVictoire
		throw new UnsupportedOperationException();
	}

	private void conditionDefaite() {
		// TODO - implement Controleur.conditionDÃƒÂ©faite
		throw new UnsupportedOperationException();
	}

	private void deplacer(String str) {
		Aventurier a = getJoueurTour();

		Tuile t = grille.getTuile(str);

		a.deplacer(t);
		grille.activateAll();
		miseAJourGrille();

	}

	private void deplacer2() {

		Aventurier a = getJoueurTour();

		ihm.afficherDep(a.deplacer2());
		miseAJourGrille();

	}
	
	private void deplacerUrgence(Aventurier a) {
		ihm.afficherDepUrg(a.deplacer2());
		miseAJourGrille();
		lastAction=TypeMessage.Clique_Deplace_Urgence;
	}

	private void assecher(String str) {

		Tuile t = grille.getTuile(str);

		t.assecher();
		grille.activateAll();
		miseAJourGrille();

	}

	private void assecher2() {
		Aventurier a = getJoueurTour();
		ihm.afficherDep(a.assecher2());
		miseAJourGrille();
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

	public void afficherListeCarteJoueur() {
		ihm.afichierConsole("Main du joueur :" + getJoueurTour().getNom());
		int i = 1;
		for (Classique c : getJoueurTour().getListeCarteJoueur()){
			ihm.addConsole(i +" : " + c.getNom());
			i = i+1;
		}

		if (getJoueurTour().getListeCarteJoueur().size() > 5) {

			ihm.addConsole("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " carte en trop dans votre main, choisir les cartes à défausser :");
		}


	}
	public void defausserCarteMain() {
		if (getJoueurTour().getListeCarteJoueur().size() > 5) {

			int numCarte = Integer.parseInt(messageConsole) -1;
			this.carteTresorsDefausse.add(getJoueurTour().getListeCarteJoueur().get(numCarte));
			getJoueurTour().getListeCarteJoueur().remove(numCarte);
			afficherListeCarteJoueur();


		}
	}


	public void piocher5Inondation() {
		for(int i = 0 ;i <5 ;i++) {
			piocherInondation();
		}
	}

	//	Mise en place de la pioche et deffause auto des carte innondation
	private void piocherInondation() {
		if(inondationDeck.size()!=0) {
			CarteInondation cInP = inondationDeck.get(0);
			cInP.getTuile().inonder();
			if (cInP.getTuile().getStatue()==2) {
				for (Aventurier a : cInP.getTuile().getAventurie()) {
					deplacerUrgence(a);
				}
			}		
			inondationDefausse.add(cInP);
			inondationDeck.remove(cInP);
		}
		else {
			for(int i=0;i<inondationDefausse.size();i++) {
				CarteInondation cInD = inondationDefausse.get(0);
				inondationDeck.add(cInD);
				inondationDefausse.remove(cInD);
			}

			if(Parameters.ALEAS) {
				Collections.shuffle(inondationDeck);
			}

			piocherInondation();		
		}
		miseAJourGrille();
	}


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

	public String getMessageConsole() {
		return messageConsole;
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
	public void miseAJourDep(ArrayList<Tuile> listdep) {

		HashMap<String, Tuile> hmTuille = ((Grille) grille).getTuilesListe();

		Iterator<Entry<String, Tuile>> it = hmTuille.entrySet().iterator();

		while(it.hasNext()) {
			Entry<String, Tuile> me = it.next();


			if(!listdep.contains(me.getValue())) {
				ihm.getButonPlateau(me.getKey()).unActivated();
			}			



		}
	}
	
	
	/**
	 * 
	 * @return 1 pour victoire, 0 si neutre et -1 pour partie perdue
	 */
	public int finDePartie() {
		
		/*SOLUTION TEMPORAIRE (ou pas si ça marche bien)
		 *Retourne 1 pour victoire, 0 si neutre et -1 pour partie perdue 
		 */
		
		//Verification de si un joueur a une carte helicoptère
		boolean aCarteHelicoptere = false;
		for(int i=0; i<joueursList.size(); i++) {
			for(int j=0; j<joueursList.get(i).getNbCarte();j++) {
				if(joueursList.get(i).getCarte(j).getClass().getName()=="CarteHelicoptere") {
					aCarteHelicoptere=true;
				}
			}
		}
		
		//Condition victoire
		if(	grille.getTuile("Heliport").isOnTuile(joueursList.get(0))&
			grille.getTuile("Heliport").isOnTuile(joueursList.get(1))&
			grille.getTuile("Heliport").isOnTuile(joueursList.get(2))&
			grille.getTuile("Heliport").isOnTuile(joueursList.get(3))&
			tresorsRecuperes.size()==4&
			aCarteHelicoptere) {
			return 1;
		}
		
		//Condition(s) défaite
		if(grille.getTuile("Heliport").getStatue()==-2) {
			return -1;
		}
		
		
		
		
		
		
		
		
		return 0;
	}
}

