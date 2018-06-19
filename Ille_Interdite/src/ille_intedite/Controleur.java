
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
import ille_intedite.Aventurie.Ingenieur;
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
	private ArrayList<NomTresor> tresorsRecuperes = new ArrayList<>();
	private Aventurier givePlayer = null;
	private boolean noyade=false;
	private Tuile helicoTuileSelect;

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
			deplacer2(getJoueurTour());
			ihm.afichierConsole("Cliquez sur une case pour vous y déplacer");
			break;

		case Clique_Asseche :
			assecher2();
			ihm.afichierConsole("Cliquez sur une case pour l'assécher");
			break;

		case Clique_Tuille :
			switch(lastAction) {
			case Clique_Deplace:


				//Utils.debugln("Tuille = "+msg.getLocation());

				//Si le deplacement cest bien passe 
				deplacer(msg.getLocation(),getJoueurTour());
				ihm.updateGrille();
				getJoueurTour().actionJouer();

				if(getJoueurTour() instanceof Ingenieur) {
					Ingenieur i = (Ingenieur) getJoueurTour();
					i.setDerniereActionAssecher(false);
				}

				break;

			case Clique_Deplace_Helico :
				// A modifier
				if (helicoTuileSelect!=null) {
					Tuile t = grille.getTuile(msg.getLocation());
					for(Aventurier a : joueursList) {
						if(a.getTuile().equals(helicoTuileSelect)) {


							deplacer(msg.getLocation(),a);
						}
					}
					miseAJourGrille();
					helicoTuileSelect=null;
				}else {
					ArrayList<Tuile> tuilesDep = new ArrayList<Tuile>();
					for(Tuile t : Grille.tuilesListe.values()) {
						if(t.getStatut()!=2 && t.getNum()!=-1 && !(t.equals(grille.getTuile(msg.getLocation())))) {
							tuilesDep.add(t);
						}
					}
					ihm.afficherDep(tuilesDep);
					miseAJourGrille();
					helicoTuileSelect=grille.getTuile(msg.getLocation());
				}


				break;

			case Clique_Asseche_SacDeSable :
				grille.getTuile(msg.getLocation()).assecher();
				ihm.afichierConsole("Case asséchée en "+msg.getLocation());
				grille.activateAll();
				miseAJourGrille();
				break;

			case Clique_Asseche :
				assecher(msg.getLocation());
				ihm.updateGrille();
				getJoueurTour().actionJouer();

				if(getJoueurTour() instanceof Ingenieur) {
					Ingenieur i = (Ingenieur) getJoueurTour();

					if(!i.getDerniereActionAssecher()) {
						i.actionAnuller();
					}

					i.setDerniereActionAssecher(!i.getDerniereActionAssecher());
				}
				break;


			}


			break;

		case Clique_Fin_Tour :
			finDeTour();
			break;
			//------------------------SEND---------------------------------
		case Clique_Send :

			messageConsole = msg.getText();



			switch(lastAction) {

			case Defausse_Joueur :


				defausserCarteMain(); break;

			case Clique_DonneCarte: 

				if(donneCarteJoeur(messageConsole)) {
					afficherListeCarteJoueur();
					msg.setMessage( TypeMessage.Defausse_NumCarte);
					ihm.addConsole("Choisire le numero : ");
				}else {
					ihm.afichierConsole(" Ce joeur n'est pas dans la liste , merci de recomencer  ");
					msg.setMessage( TypeMessage.Clique_DonneCarte);
					aficherJoeurCase();
				}
				break;
			case Defausse_NumCarte :

				if(donneCarte(messageConsole)) {
					getJoueurTour().actionJouer();
					
				}else {
					msg.setMessage( TypeMessage.Defausse_NumCarte);
					ihm.addConsole("Cette carte n'est pas disponible");
				}

				break;
				//------------------------ FIN SEND---------------------------------
			}

			break;
		case Clique_RecupereTresort :
			if(RecupereTresort()) {
				getJoueurTour().actionJouer();
				ihm.afichierConsole("Vous avez récupéré le trésor");
			}else {
				ihm.afichierConsole("Impossible de récupérer le trésor");
			}
			break;
		case Clique_DonneCarte :
			aficherJoeurCase();
			break;
		case Clique_Asseche_SacDeSable :
			ArrayList<Tuile> listAsseche = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getStatut()==1) {
					listAsseche.add(t);
				}
			}
			ihm.afficherDep(listAsseche);
			miseAJourGrille();
			break;

		case Clique_Deplace_Helico :
			ArrayList<Tuile> listCaseAvent = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getAventurie().size()!=0) {
					listCaseAvent.add(t);
				}
			}
			ihm.afficherDep(listCaseAvent);
			miseAJourGrille();
			break;

		}




		//Si la conditon au dessu est fausse elle continue 

		lastAction = msg.getMessage();
		if (helicoTuileSelect!=null) {
			lastAction=TypeMessage.Clique_Deplace_Helico;
		}


		if(getJoueurTour().getNbAction()<1) {
			//	System.out.println(" nb act = "+getJoueurTour().getNbAction());
			finDeTour();
		}

	}



	private void finDeTour() {
		// TODO Auto-generated method stub
		ihm.afichierConsole("Fin du tour du joueur n°"+numTour);


		for (int i=0;i<curseur.getNbCartesInond();i++) {
			piocherInondation();
		}
		deplacerUrgence();
		piocherClassique();
		piocherClassique();


		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;


			ihm.addConsole("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes Ã  dÃ©fausser :");

		}



		//afficherListeCarteJoueur


		getJoueurTour().finTour();
		numTour++;		
		numTour%=joueursList.size();
		afficherListeCarteJoueur();

		ihm.addConsole("Joueur n°"+numTour+" A vous de jouer");
		ihm.miseAJourPlayer(numTour," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
		activateSpecialButton(getJoueurTour());
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

			carteTresorDeck.add(new MonteeEaux("Montee des EAU 1"));
			carteTresorDeck.add(new MonteeEaux("Montee des EAU 2"));
			//carteTresorDeck.add(new MonteeEaux("Monte des EAU 3"));

			carteTresorDeck.add(new CarteHelicoptere("Helico 1"));
			carteTresorDeck.add(new CarteHelicoptere("Helico 2"));
			carteTresorDeck.add(new CarteHelicoptere("Helico 3"));

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
				ihm.setLevelCursort(curseur.getNbCartesInond());
			}

		}
	}





	public void init() {
		//creer les aventuriers
		Aventurier a;
		//Marche

		a = new Ingenieur(0,"Ingénieur",Pion.ROUGE);

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
		activateSpecialButton(getJoueurTour());
		ihm.miseAJourPlayer(0," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
		test();
	}

	private void conditionVictoire() {
		// TODO - implement Controleur.conditionVictoire
		throw new UnsupportedOperationException();
	}

	private void conditionDefaite() {
		// TODO - implement Controleur.conditionDÃÂÃÂÃÂÃÂ©faite
		throw new UnsupportedOperationException();
	}

	private void deplacer(String str, Aventurier a) {

		Tuile t = grille.getTuile(str);
		a.deplacer(t);
		grille.activateAll();
		miseAJourGrille();

	}

	private void deplacer2(Aventurier a) {

		ArrayList<Tuile> tuilesDep = a.deplacer2();


		ihm.afficherDep(tuilesDep);
		miseAJourGrille();


	}

	private void deplacerUrgence() {

		for(Tuile t : Grille.tuilesListe.values()) {
			if (t.getStatut()==2) {
				Iterator<Aventurier> it = joueursList.iterator();
				while(it.hasNext()) {
					Aventurier a =it.next();
					if(t.getAventurie().contains(a)) {
						try {
							ArrayList<Tuile> tuilesDep =a.deplacer2();
							Collections.shuffle(tuilesDep);
							deplacer(tuilesDep.get(0).getxT()+":"+tuilesDep.get(0).getyT(),a);
							miseAJourGrille();
						}catch(Exception e) {
							System.out.println("Perdu");
						}
					}
				}
			}
		}
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

	//--------------------------------------------
	//DONE LES CARTE 
	//-----------------------------

	private boolean donneCarteJoeur(String str) {
		// TODO - implement Controleur.donneCarte


		int num = Integer.parseInt(str)-1;

	

		int nbr = getJoueurTour().getNum();
		for(Aventurier a : getJoueurTour().getJoueurTuile()) {
			System.out.println(" k = "+a.getNum()+" nbr "+nbr);
			if(nbr != a.getNum()&&num==a.getNum()) {
				givePlayer = a;
				return true;
			}


		}

		return  false;
	}


	private boolean donneCarte(String str) {

		int num = Integer.parseInt(str);
		num-=1;
		if(givePlayer != null &&  getJoueurTour().getListeCarteJoueur().size()>num&&num>=0) {
			
			

			Classique c =  getJoueurTour().getListeCarteJoueur().get(num);

			getJoueurTour().getListeCarteJoueur().remove(c);

			givePlayer.getListeCarteJoueur().add(c);

			ihm.addConsole("Carte donne");
			givePlayer = null;
			return true;
		}else {
			return false;
		}



	}


	public boolean aficherJoeurCase() {
		String str = "";
		int nbr = getJoueurTour().getNum();
		for(Aventurier a : getJoueurTour().getJoueurTuile()) {
			if(nbr != a.getNum()) {
				str +=a.getNom()+" ( n° "+(a.getNum()+1)+") \n";
			}
		}


		if(str.equalsIgnoreCase("")) {
			
			return false;
		}else {
		ihm.addConsole("Joueur sur la meme case  : "+str+" \n Veiller entre le numerau du joeur selectione");
			return true;
		}





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

	@Deprecated
	public void afficherListeCarteJoueur() {
		ihm.afichierConsole("Main du joueur :" + getJoueurTour().getNom());
		int i = 1;
		for (Classique c : getJoueurTour().getListeCarteJoueur()){
			ihm.addConsole(i +" : " + c.getNom());
			i = i+1;
		}

		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;
			ihm.addConsole("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes ÃÂ  dÃÂ©fausser :");
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

	public boolean recupereTresort() {
		// 

		Aventurier a = getJoueurTour();

		NomTresor  t = a.recupereTresor();

		if(t!=null) {
			tresorsRecuperes.add(t);
			return true;
		}else {
			return false;
		}

	}

	public void activateSpecialButton(Aventurier a) {
		boolean aHelico=false;
		boolean aSacSable=false;

		for(Classique c : a.getListeCarteJoueur()) {
			if (c instanceof CarteHelicoptere) {
				aHelico=true;
			}
			if(c instanceof CarteSacSable) {
				aSacSable=true;
			}
		}

		ihm.activateSpecialButton(aHelico, aSacSable);

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

	public boolean RecupereTresort() {
		// 

		Aventurier a = getJoueurTour();

		NomTresor  t = a.recupereTresor();

		if(t!=null) {
			tresorsRecuperes.add(t);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 * @return 1 pour victoire, 0 si neutre et -1 pour partie perdue
	 */
	public int verifierFinDePartie() {

		//Verification de si un joueur a une carte helicoptere
		boolean aCarteHelicoptere = false;
		for(int i=0; i<joueursList.size(); i++) {
			for(int j=0; j<joueursList.get(i).getNbCarte();j++) {
				if(joueursList.get(i).getCarte(j).getClass().getName()=="CarteHelicoptere") {
					aCarteHelicoptere=true;
				}
			}
		}

		int joueursPresentsHeliport=0;
		boolean heliportCoule=false;
		for(Tuile t : grille.getTuilesListe().values()) {
			System.out.println(t.getNum());
			if(t.getNum()==22) {			//Si la tuile est l'héliport
				joueursPresentsHeliport = t.getNbrAventurie();
				heliportCoule = t.getStatut()==-2;
			}
		}

		//Condition victoire
		if(	joueursPresentsHeliport>3&&
				tresorsRecuperes.size()==4&&
				aCarteHelicoptere) {
			return 1;												//Partie gagnee et livraison de colis de bonbons
		}															//De bonbons ? et pas de cookies ?

		//Condition(s) defaite

		if(noyade) {
			return -1;
		}

		if(heliportCoule) {
			return -1;												//Heliport coule
		}

			if(curseur.getNbCartesInond()==0) {
				return -1;												//Curseur au niveau maximum
			}


			int temple=0,caverne=0,palais=0,jardin=0;
			for(int i=0;i<tresorsRecuperes.size(); i++) {
				int numT=tresorsRecuperes.get(i).getNum();
				switch (numT) {
				case 1: caverne=-1;
				case 2: palais=-1;
				case 3: jardin=-1;
				case 4: temple=-1;
				}
			}



			for(Tuile t : grille.getTuilesListe().values()) {

					for(String key : grille.getTuilesListe().keySet()) {
						if(temple>-1 && (t.getNum()==341 || t.getNum()==342) && t.getStatut()==2) {
							temple++;
						}
						if(caverne>-1 && (t.getNum()==311 || t.getNum()==312) && t.getStatut()==2) {
							caverne++;
						}
						if(palais>-1 && (t.getNum()==321 || t.getNum()==322) && t.getStatut()==2) {
							palais++;
						}
						if(jardin>-1 && (t.getNum()==331 || t.getNum()==332) && t.getStatut()==2) {
							jardin++;
						}
					}
					if(temple==2||caverne==2||palais==2||jardin==2) {
						return -1;												//Deux cases de rÃÂ©cupÃÂ©ration de trÃÂ©sor coulÃÂ©es
					}

					// A COMPLETER



				}
			}
		}
		return 0;
	}


	public void test() {


		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));





	}


}

