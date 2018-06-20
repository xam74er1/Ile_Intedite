
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
import IHM.IHMV2;
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

	private Curseur curseur;
	private Grille grille;
	private ArrayList<Classique> carteTresorDeck;
	public ArrayList<Classique> carteTresorsDefausse;
	private ArrayList<CarteInondation> inondationDeck;
	public ArrayList<CarteInondation> inondationDefausse;
	public static ArrayList<Aventurier> joueursList;
	private String messageConsole;
	private int NBR_JOUEUR = 4;
	private  TypeMessage lastAction = TypeMessage.Clique_Send;
	private int numTour;
	private VueGrille vue;
	private ArrayList<NomTresor> tresorsRecuperes = new ArrayList<>();
	private Aventurier givePlayer = null;
	private boolean noyade=false;
	private Tuile helicoTuileSelect;
	private Aventurier urgence = null;
	private boolean urg=false;
	private Classique carteSpe;


	IHMV2 ihm;


	public Controleur(IHMV2 ihm,VueGrille vue) {
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
			ihm.afichierConsole("Cliquez sur une case pour vous y deplacer");
			break;

		case Clique_Asseche :
			assecher2();
			ihm.afichierConsole("Cliquez sur une case pour l'assecher");
			break;

		case Clique_Tuile :
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

				if(getJoueurTour() instanceof Aviateur) {
					Aviateur a = (Aviateur) getJoueurTour();
					Tuile from = a.getFrom();
					Tuile to = grille.getTuile(msg.getLocation());
					if(!(to.getxT()-from.getxT()==0 && Math.abs(to.getyT()-from.getyT())==1 || to.getyT()-from.getyT()==0 && Math.abs(to.getxT()-from.getxT())==1)) {
						a.tp();
					}
				}

				break;

			case Clique_Deplace_Helico :
				getJoueurTour().removeCarte(carteSpe);
				carteTresorsDefausse.add(carteSpe);
				if (helicoTuileSelect!=null) {
					Tuile t = grille.getTuile(msg.getLocation());
					for(Aventurier a : joueursList) {
						if(a.getTuile().equals(helicoTuileSelect)) {


							deplacer(msg.getLocation(),a);
						}
					}
					helicoTuileSelect=null;
				}else {
					ArrayList<Tuile> tuilesDep = new ArrayList<Tuile>();
					for(Tuile t : Grille.tuilesListe.values()) {
						if(t.getStatut()!=2 && t.getNum()!=-1 && !(t.equals(grille.getTuile(msg.getLocation())))) {
							tuilesDep.add(t);
						}
					}
					ihm.afficherDep(tuilesDep);
					helicoTuileSelect=grille.getTuile(msg.getLocation());
				}
				afficherCartes(getJoueurTour());
				miseAJourGrille();



				break;

			case Clique_Asseche_SacDeSable :
				grille.getTuile(msg.getLocation()).assecher();
				ihm.afichierConsole("Case assechee en "+msg.getLocation());
				getJoueurTour().removeCarte(carteSpe);
				carteTresorsDefausse.add(carteSpe);
				grille.activateAll();
				miseAJourGrille();
				afficherCartes(getJoueurTour());
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

			case Clique_Fin_Tour :
				deplacer(msg.getLocation(),urgence);
				miseAJourGrille();
				grille.activateAll();
				lastAction=TypeMessage.Clique_Fin_Tour;
				finDeTour();

				break;
			case Clique_Tuile :
				if (urg) {
					deplacer(msg.getLocation(),urgence);
					miseAJourGrille();
					grille.activateAll();
					lastAction=TypeMessage.Clique_Fin_Tour;
					finDeTour();
				}


			}


			break;

		case Clique_Fin_Tour :
			lastAction=TypeMessage.Clique_Deplace_Urgence;
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
		case Clique_RecupereTresor :
			if(RecupereTresort()) {
				getJoueurTour().actionJouer();
				ihm.afichierConsole("Vous avez recupere le tresor");
			}else {
				ihm.afichierConsole("Impossible de recuperer le tresor");
			}
			break;
		case Clique_DonneCarte :
			aficherJoeurCase();
			break;
		case Clique_Asseche_SacDeSable :
			carteSpe=msg.getCarte();
			ArrayList<Tuile> listAsseche = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getStatut()==1) {
					listAsseche.add(t);
				}
			}
			ihm.afficherDep(listAsseche);
			activateSpecialButton(getJoueurTour());
			miseAJourGrille();
			
			break;

		case Clique_Deplace_Helico :
			carteSpe=msg.getCarte();
			ArrayList<Tuile> listCaseAvent = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getAventurie().size()!=0) {
					listCaseAvent.add(t);
				}
			}
			ihm.afficherDep(listCaseAvent);
			activateSpecialButton(getJoueurTour());
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
		if(!urg) {
			ihm.afichierConsole("Fin du tour du joueur "+numTour);


			for (int i=0;i<curseur.getNbCartesInond();i++) {
				piocherInondation();
			}
		}
		urg=false;
		deplacerUrgence();

		if(!urg) {

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

			ihm.addConsole("Joueur "+numTour+" A vous de jouer");
			ihm.miseAJourPlayer(numTour," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
			activateSpecialButton(getJoueurTour());
			afficherCartes(getJoueurTour());
			//	Utils.debugln("Fin de tour");
			ihm.rool(getJoueurTour().getNum(), joueursList);
			grille.activateAll();
			miseAJourGrille();
		}
	}

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
					carteTresorDeck.add(new CarteTresor(j+"Cristal", NomTresor.CristalArdent));
					break;
				case 2:
					// Calise
					carteTresorDeck.add(new CarteTresor(j+"Calice", NomTresor.CaliceOnde));
					break;
				case 3:
					// Statue
					carteTresorDeck.add(new CarteTresor(j+"Zephyr", NomTresor.StatueZephir));
					break;
				case 4:
					// Pierre
					carteTresorDeck.add(new CarteTresor(j+"Pierre", NomTresor.PierreSacree));
					break;
				}
			}

			carteTresorDeck.add(new CarteSacSable("1SacsDeSable"));
			carteTresorDeck.add(new CarteSacSable("2SacsDeSable"));
			carteTresorDeck.add(new CarteSacSable("3SacsDeSable"));

			carteTresorDeck.add(new MonteeEaux("1Montee des EAU"));
			carteTresorDeck.add(new MonteeEaux("2Montee des EAU"));
			//carteTresorDeck.add(new MonteeEaux("3Monte des EAU"));

			carteTresorDeck.add(new CarteHelicoptere("1Helicoptere"));
			carteTresorDeck.add(new CarteHelicoptere("2Helicoptere"));
			carteTresorDeck.add(new CarteHelicoptere("3Helicoptere"));

		}
		if(Parameters.ALEAS) {
			Collections.shuffle(carteTresorDeck);
		}

	}

	public void piocherClassique() {
		if(carteTresorDeck.size() != 0) {
			Classique cC = carteTresorDeck.get(0);
			if(!(cC instanceof MonteeEaux)) {
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

		a = new Ingenieur(0,"Ingenieur",Pion.ROUGE);

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
		
		Collections.shuffle(joueursList);



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
		//test();
	}

	private void deplacer(String str, Aventurier a) {

		Tuile t = grille.getTuile(str);
		a.deplacer(t);
		grille.activateAll();
		miseAJourGrille();

	}

	private void deplacer2(Aventurier a) {

		ArrayList<Tuile> tuilesDep = a.deplacer2();
		if (tuilesDep.size()==0 && urg) {
			noyade=true;
			verifierFinDePartie();
		}

		ihm.afficherDep(tuilesDep);
		miseAJourGrille();


	}

	private void deplacerUrgence() {
		urgence=null;
		urg=false;
		
		Iterator<Aventurier> it = joueursList.iterator();
		while(it.hasNext()) {
			Aventurier a =it.next();
			if(!urg && a.getTuile().getStatut()==2) {
				ihm.print("Deplacez "+a.getNom()+" en urgence");
				grille.activateAll();
				urgence=a;
				urg=true;
				deplacer2(a);
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
				str +=a.getNom()+"("+(a.getNum()+1)+") \n";
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
	
	private void defausser(String str, Aventurier a) {
		a.removeCarte(a.getCarte(Integer.parseInt(str)));
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

	public void addDefausse(ArrayList<Carte> main) {
		// TODO - implement Controleur.addDefausse
		throw new UnsupportedOperationException();
	}

	public void miseAJourGrille() {

		//Provisoire 
		vue.afficherGrille();

	}	
	
	public void afficherCartes(Aventurier a) {
		ArrayList<Classique> listCartes = a.getListeCarteJoueur();
		for(int i=0;i<5;i++) {
			try {
				ihm.setCartePanel(i, listCartes.get(i));
			}catch(Exception e) {
				ihm.setCartePanel(i, null);
			}
		}
		
	}

	public boolean RecupereTresort() {
		// 

		Aventurier a = getJoueurTour();

		NomTresor  t = a.recupereTresor();

		boolean dejaPresent=false;
		if(t!=null) {
			for(int i=0;i>tresorsRecuperes.size(); i++) {
				if (!dejaPresent&&tresorsRecuperes.get(i)==t) {
					dejaPresent=true;
				}
			}
			if(!dejaPresent) {
				tresorsRecuperes.add(t);
				for(int i=0;i<a.getListeCarteJoueur().size(); i++) {
					if(a.getListeCarteJoueur().get(i).getNom()==t.getType().getNom()) {
						a.getListeCarteJoueur().remove(i);
					}
				}
			}
			return true;
		}else {
			return false;
		}
	}

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
			if(t.getNum()==22) {			//Si la tuile est l'heliport
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
		//Et pourquoi pas les deux ??

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
			return -1;												//Deux cases de recuperation de tresor coulees
		}

		return 0;
	}

	/*public void test() {


		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));
		getJoueurTour().addCarte(new CarteTresor("test ", NomTresor.CaliceOnde));





	}*/


}

