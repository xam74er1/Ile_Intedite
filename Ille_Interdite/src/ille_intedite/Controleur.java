
package ille_intedite;
import Carte.Carte;
import Carte.CarteHelicoptere;
import Carte.Classique;
import Carte.CarteInondation;
import Carte.CarteTresor;
import Carte.CarteSacSable;
import Carte.MonteeEaux;
import Carte.NomTresor;
import IHM.FenetreFin;
import IHM.FenetreStart;
//import IHM.IHM;
import IHM.IHMV2;
import IHM.MessageFinPartie;
import IHM.MessageInit;
import IHM.PlaySound;
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
import java.util.concurrent.TimeUnit;

import utils.Parameters;
import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{

	private Curseur curseur;
	private Grille grille;
	private ArrayList<Carte> carteTresorDeck;
	public ArrayList<Carte> carteTresorsDefausse;
	private ArrayList<Carte> inondationDeck;
	public ArrayList<Carte> inondationDefausse;
	public static ArrayList<Aventurier> joueursList;
	private String messageConsole;
	private int nbJoueurs;
	private  TypeMessage lastAction = TypeMessage.Clique_Send;
	private int numTour;
	private VueGrille vue;
	private ArrayList<NomTresor> tresorsRecuperes = new ArrayList<>();
	private Aventurier givePlayer = null;
	private boolean noyade=false;
	private boolean helicoCoule=false;
	private boolean aCarteHelicoptere = false;
	private Tuile helicoTuileSelect;
	private Aventurier urgence = null;
	private boolean urg=false;
	private Classique carteSpe;
	private ArrayList<Carte> listPioche;
	private boolean finTour=false;
	private boolean defausse=false;
	private Aventurier memoireAventuire = null;

	private int numCarte = -1;

	private boolean isInit = false;



	IHMV2 ihm;

	//Consturcteur
	public Controleur(IHMV2 ihm,VueGrille vue,MessageInit msgInit) {

		this.ihm = ihm;
		carteTresorDeck = new ArrayList<Carte>();
		carteTresorsDefausse = new ArrayList<Carte>();
		inondationDeck = new ArrayList<Carte>();
		inondationDefausse = new ArrayList<Carte>();
		joueursList = new ArrayList<Aventurier>();
		this.vue = vue;
		nbJoueurs = msgInit.nbJoueurs;
		init(msgInit);
		numTour =0;
		curseur = new Curseur(msgInit.niveauEau);
		ihm.afficherNivCurseur(10);

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

			ihm.setIndication("Cliquez sur une case pour vous y deplacer");
			break;

		case Clique_Asseche :
			assecher2();
			ihm.setIndication("Cliquez sur une case pour l'assecher");
			break;

			/* -------------------------CLIQUE SUR UNE TUILLE ----------------------------------------------- */
			//Diffrente action lorsque lon clique sur une tuille on regarde laction precedente pour savoir quoi faire 
		case Clique_Tuile :
			switch(lastAction) {


			case Clique_Deplace:
				deplacer(msg.getLocation(),getJoueurTour());
				ihm.updateGrille();
				getJoueurTour().actionJouer();

				//Cas spetiale pour lingeneure 
				if(getJoueurTour() instanceof Ingenieur) {
					Ingenieur i = (Ingenieur) getJoueurTour();
					i.setDerniereActionAssecher(false);
				}
				//Cas spetiale pour l'aviateur
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


				if (helicoTuileSelect!=null && memoireAventuire != null) {


					if (helicoTuileSelect.getNum()==22) {
						aCarteHelicoptere=true;
						verifierFinDePartie();
					}

					Tuile t = grille.getTuile(msg.getLocation());
					for(Aventurier a : joueursList) {
						if(a.getTuile().equals(helicoTuileSelect)) {


							deplacer(msg.getLocation(),a);
							PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\vrai_son_helicoptere.wav");
						}
					}
					memoireAventuire.removeCarte(carteSpe);
					carteTresorsDefausse.add(carteSpe);
					helicoTuileSelect=null;
					if(defausse) {
						if(getJoueurTour().getNbCarte()>5) {
							afficherDefausseFinTour();
						}else {
							defausse=false;
							afficherPiocheInondation();
						}
					}
					memoireAventuire = null;
				}else {

					ihm.setIndication("Cliquez sur une case pour vous deplacer");


					if (grille.getTuile(msg.getLocation()).getNum()==22) {
						aCarteHelicoptere=true;
						verifierFinDePartie();
					}

					ArrayList<Tuile> tuilesDep = new ArrayList<Tuile>();
					for(Tuile t : Grille.tuilesListe.values()) {
						if(t.getStatut()!=2 && t.getNum()!=-1 && !(t.equals(grille.getTuile(msg.getLocation())))) {
							tuilesDep.add(t);
						}
					}
					ihm.afficherDep(tuilesDep);
					helicoTuileSelect=grille.getTuile(msg.getLocation());

				}


				//Mise as jour de la grille quimporte laction effectuer 
				afficherCartes(getJoueurTour());
				ihm.rool(getJoueurTour(),joueursList);
				miseAJourGrille();



				break;

			case Clique_Asseche_SacDeSable :
				grille.getTuile(msg.getLocation()).assecher();
				ihm.setIndication("Case assechee en "+msg.getLocation());
				memoireAventuire.removeCarte(carteSpe);
				carteTresorsDefausse.add(carteSpe);
				grille.activateAll();
				ihm.setIndication("Cliquez sur une case pour l'assecher");
				miseAJourGrille();
				afficherCartes(getJoueurTour());
				ihm.rool(getJoueurTour(),joueursList);
				break;
				//Action lors du clique du bouton assehce 
			case Clique_Asseche :
				assecher(msg.getLocation());
				ihm.updateGrille();
				getJoueurTour().actionJouer();
				PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\Sac_de_Sable.wav");

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
				afficherPiocheFinTour();

				break;
			case Clique_Tuile :
				ihm.setIndication("");
				if (urg) {
					deplacer(msg.getLocation(),urgence);
					miseAJourGrille();
					grille.activateAll();
					lastAction=TypeMessage.Clique_Fin_Tour;
					finDeTour();

				}

				//-------------FIN DES CLIQUE TUILE ----------------------------
			}

			break;

		case Clique_Fin_Tour :
			lastAction=TypeMessage.Clique_Deplace_Urgence;
			afficherPiocheFinTour();
			break;

		case Clique_RecupereTresor :
			if(recupererTresor()) {
				getJoueurTour().actionJouer();
				ihm.setIndication("Vous avez recupere le tresor");
			}else {
				ihm.setIndication("Impossible de recuperer le tresor");
			}
			break;
			//		case Clique_DonneCarte :
			//		int provi  = 1+1;
			//			break;
		case Clique_Asseche_SacDeSable :

			if(lastAction==TypeMessage.Defausse_Joueur) {
				getJoueurTour().removeCarte((Classique) msg.getCarte());
				carteTresorsDefausse.add(msg.getCarte());
				ihm.afficherPlateau();
				defausse=false;
				afficherPiocheInondation();
				if(getJoueurTour().getNbCarte()>5) {
					afficherDefausseFinTour();
				}else {
					afficherPiocheInondation();
				}
			}else {
				carteSpe=(Classique) msg.getCarte();
				System.out.println(" num joeur = "+msg.getNumJoueur());
				if(msg.getNumJoueur()!= -1) {
					memoireAventuire = joueursList.get(msg.getNumJoueur());
				}

				ArrayList<Tuile> listAsseche = new ArrayList<Tuile>() ;
				for(Tuile t : Grille.tuilesListe.values()) {
					if (t.getStatut()==1) {
						listAsseche.add(t);


						carteSpe=(Classique) msg.getCarte();

					}
				}
				ihm.afficherDep(listAsseche);
				miseAJourGrille();
			}
			break;
		case Clique_DonneCarte :

			ihm.setIndication("Cliquez sur la carte que vous voulez donner ");
			break;
		case Clique_Deplace_Helico :

			aCarteHelicoptere=true;
			verifierFinDePartie();
			if(lastAction==TypeMessage.Defausse_Joueur) {
				getJoueurTour().removeCarte((Classique) msg.getCarte());
				carteTresorsDefausse.add(msg.getCarte());
				ihm.afficherPlateau();
				defausse=false;
				afficherPiocheInondation();
				if(getJoueurTour().getNbCarte()>5) {
					afficherDefausseFinTour();
				}else {
					afficherPiocheInondation();
				}
			}else {
				ihm.setIndication("Cliquez sur le joueur que vous voulez deplacer");
				carteSpe=(Classique) msg.getCarte();
				System.out.println(" num "+msg.getNumJoueur());
				if(msg.getNumJoueur() != -1) {
					memoireAventuire = joueursList.get(msg.getNumJoueur());
				}else {
					System.out.println("Le joeur est =  -1 ");
				}
				ArrayList<Tuile> listCaseAvent = new ArrayList<Tuile>() ;
				for(Tuile t : Grille.tuilesListe.values()) {
					if (t.getAventurie().size()!=0) {
						listCaseAvent.add(t);							
						ihm.setIndication("Cliquez sur le joueur que vous voulez deplacer");
						carteSpe=(Classique) msg.getCarte();

					}
				}
				ihm.afficherDep(listCaseAvent);
				miseAJourGrille();
			}


			break;

		case Clique_Carte_Tresor :
			if(lastAction ==TypeMessage.Clique_DonneCarte) {

				numCarte = msg.getNum();
				ihm.setIndication("Cliquez sur le joueur a qui vous voulez donner la carte");
			}else if(lastAction==TypeMessage.Defausse_Joueur) {
				getJoueurTour().removeCarte((Classique) msg.getCarte());
				carteTresorsDefausse.add(msg.getCarte());
				ihm.afficherPlateau();
				defausse=false;
				afficherPiocheInondation();
				if(getJoueurTour().getNbCarte()>5) {
					afficherDefausseFinTour();
				}else {
					afficherPiocheInondation();
				}
			}
			break;
		case Clique_Joueur :

			if(lastAction == TypeMessage.Clique_Carte_Tresor && numCarte != -1&&getJoueurTour().getListeCarteJoueur().size()>numCarte&&msg.getNum()!=-1) {

				givePlayer = joueursList.get(msg.getNum()); 
				Classique c =  (Classique) getJoueurTour().getListeCarteJoueur().get(numCarte);

				if(donnerCarteJoueur(msg.getNum())) {
					getJoueurTour().getListeCarteJoueur().remove(c);
					givePlayer.getListeCarteJoueur().add(c);
					afficherCartes(getJoueurTour());
					ihm.rool(getJoueurTour(), joueursList);
					numCarte = -1;
					ihm.setIndication("");
				}
			}

			break;

		case Clique_Ok :

			if(defausse) {
				ihm.afficherPlateau();
				ihm.afficherPioche(getJoueurTour().getListeCarteJoueur(),false);
			}else if (lastAction==TypeMessage.Clique_Fin_Tour){
				if(getJoueurTour().getNbCarte()>5) {
					ihm.afficherPlateau();
					afficherDefausseFinTour();
				}else {
					ihm.afficherPlateau();
					afficherPiocheInondation();
				}
			}else if (lastAction==TypeMessage.Clique_Ok){
				ihm.afficherPlateau();
				finDeTour();
			}else if(finTour) {
				ihm.afficherPlateau();
				finDeTour();
			}

			break;

		}




		//Si la conditon au dessu est fausse elle continue 

		lastAction = msg.getMessage();
		if (helicoTuileSelect!=null) {
			lastAction=TypeMessage.Clique_Deplace_Helico;
		}else if(defausse) {
			lastAction=TypeMessage.Defausse_Joueur;
		}


		if(getJoueurTour().getNbAction()<1) {
			afficherPiocheFinTour();
		}

	}

	private void finDeTour() {
		ihm.setIndication("Fin du tour du joueur "+numTour);

		urg=false;
		deplacerUrgence();

		if(!urg) {


			getJoueurTour().finTour();
			numTour++;		
			numTour%=joueursList.size();
			ihm.setIndication("Joueur "+numTour+" A vous de jouer");
			ihm.miseAJourPlayer(numTour," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
			afficherCartes(getJoueurTour());
			ihm.rool(getJoueurTour(), joueursList);
			grille.activateAll();
			ihm.afficherPlateau();
			miseAJourGrille();
			finTour=false;
		}
	}

	private void afficherPiocheFinTour() {
		finTour=true;
		listPioche=new ArrayList();

		melanger(carteTresorDeck);
		listPioche.add(piocherClassique(getJoueurTour()));
		listPioche.add(piocherClassique(getJoueurTour()));
		ihm.afficherPioche(listPioche,true);
	}

	private void afficherDefausseFinTour() {
		defausse=true;
		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;
			ihm.afficherPioche(getJoueurTour().getListeCarteJoueur(),false);
			ihm.setIndication("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes a defausser :");


		}
	}

	private void afficherPiocheInondation(){

		listPioche=new ArrayList();
		for (int i=0;i<curseur.getNbCartesInond();i++) {
			listPioche.add(piocherInondation());
		}
		ihm.afficherPioche(listPioche,true);
	}

	public void creerDeckInondation() {
		for(Tuile t :Grille.tuilesListe.values()){
			if(t.getNum()!=-1) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}

		}

		if(Parameters.ALEAS) {
			melanger(inondationDeck);
		}

	}

	public void creerDeckClassique() {
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
		}

		carteTresorDeck.add(new CarteSacSable("1SacsDeSable"));
		carteTresorDeck.add(new CarteSacSable("2SacsDeSable"));
		carteTresorDeck.add(new CarteSacSable("3SacsDeSable"));

		carteTresorDeck.add(new MonteeEaux("1MonteeDesEaux"));
		carteTresorDeck.add(new MonteeEaux("2MonteeDesEaux"));
		//carteTresorDeck.add(new MonteeEaux("3MonteeDesEaux"));

		carteTresorDeck.add(new CarteHelicoptere("1Helicoptere"));
		carteTresorDeck.add(new CarteHelicoptere("2Helicoptere"));
		carteTresorDeck.add(new CarteHelicoptere("3Helicoptere"));



		if(Parameters.ALEAS) {
			melanger(carteTresorDeck);
		}

	}

	public Carte piocherClassique(Aventurier a) {
		if(carteTresorDeck.size()!=0) {
			Classique cC = (Classique) carteTresorDeck.get(0);
			if(!(cC instanceof MonteeEaux)) {
				a.getListeCarteJoueur().add(cC);	
				carteTresorDeck.remove(0);
			}
			else {
				if (isInit) {
					carteTresorsDefausse.add(cC);
					curseur.monteeEaux();
					melanger(inondationDefausse);
					for (Carte c : inondationDefausse) {
						inondationDeck.add(0,c);
					}
					inondationDefausse.removeAll(inondationDefausse);
					if (curseur.getNiv()>=10) {
						verifierFinDePartie();
					}

					ihm.afficherNivCurseur(curseur.getNiv());
					carteTresorDeck.remove(0);
				}else {
					carteTresorDeck.remove(0);
					carteTresorDeck.add((int) (Math.random()*carteTresorDeck.size()),cC);
					return piocherClassique(a);
				}
			}
			return cC;
		}else {
			ArrayList<Carte> stamp = carteTresorDeck;
			carteTresorDeck=carteTresorsDefausse;
			carteTresorsDefausse=stamp;
			return null;
		}

	}

	public void init(MessageInit msgInit) {

		joueursList = msgInit.listJoueurs;


		Collections.shuffle(joueursList);

		melanger(joueursList);
		grille = new Grille(ihm,joueursList);

		ihm.fillPlataux2(grille);


		creerDeckInondation();
		creerDeckClassique();

		for(Aventurier av : joueursList) {
			piocherClassique(av);
			piocherClassique(av);
		}

		String sc = msgInit.scenario;
		if(sc == "Victoire") {
			scenario_victoire();
		}

		ihm.miseAJourPlayer(0," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());

		ihm.rool(getJoueurTour(), joueursList);

		afficherCartes(getJoueurTour());
		isInit = true;
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
		ihm.setActionEnabled(false);
		Iterator<Aventurier> it = joueursList.iterator();
		while(it.hasNext()) {
			Aventurier a =it.next();
			if(!urg && a.getTuile().getStatut()==2) {
				ihm.setIndication("Deplacer "+a.getNom()+" en urgence");
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

	private boolean donnerCarteJoueur(int num) {

		int nbr = getJoueurTour().getNum();
		for(Aventurier a : getJoueurTour().getJoueurTuile()) {
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

			Classique c =  (Classique) getJoueurTour().getListeCarteJoueur().get(num);

			getJoueurTour().getListeCarteJoueur().remove(c);

			givePlayer.getListeCarteJoueur().add(c);

			ihm.setIndication("Carte donnee");
			givePlayer = null;
			return true;
		}else {
			return false;
		}

	}

	public boolean afficherJoueurCase() {
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
			ihm.setIndication("Joueurs sur la meme case  : "+str+" \n Entrez le numero du joueur selectionne");
			return true;
		}
	}

	private void piocherTresor() {

		for(int i =0;i<2;i++) {
			Carte cC = carteTresorDeck.get(1);
			getJoueurTour().getListeCarteJoueur().add(cC);
		}

	}

	private void defausser(String str, Aventurier a) {
		a.removeCarte(a.getCarte(Integer.parseInt(str)));
	}

	public void defausserCarteMain() {
		if (getJoueurTour().getListeCarteJoueur().size() > 5) {

			int numCarte = Integer.parseInt(messageConsole) -1;
			this.carteTresorsDefausse.add((Classique) getJoueurTour().getListeCarteJoueur().get(numCarte));
			getJoueurTour().getListeCarteJoueur().remove(numCarte);

		}
	}

	public void piocher5Inondation() {
		for(int i = 0 ;i <5 ;i++) {
			piocherInondation();
		}
	}

	private Carte piocherInondation() {


		if(inondationDeck.size()==0) {
			for(int i=0;i<inondationDefausse.size();i++) {
				CarteInondation cInD = (CarteInondation) inondationDefausse.get(0);
				inondationDeck.add(cInD);
				inondationDefausse.remove(cInD);
			}

			if(Parameters.ALEAS) {
				melanger(inondationDeck);
			}
		}
		CarteInondation cInP = (CarteInondation) inondationDeck.get(0);
		cInP.getTuile().inonder();
		miseAJourGrille();
		if(cInP.getTuile().getNum()==24 && cInP.getTuile().getStatut()==2) {
			helicoCoule=true;
			verifierFinDePartie();
		}

		inondationDefausse.add(cInP);
		inondationDeck.remove(cInP);
		return cInP;
	}

	public Aventurier getJoueurTour() {
		int i =  numTour%(joueursList.size());

		//	Utils.debugln(" jouer n = "+i);
		return joueursList.get(i);

	}

	public String getMessageConsole() {
		return messageConsole;
	}

	public void miseAJourGrille() {

		//Provisoire 
		vue.afficherGrille();

	}	

	public void afficherCartes(Aventurier a) {
		ArrayList<Carte> listCartes = a.getListeCarteJoueur();
		for(int i=0;i<5;i++) {
			try {
				ihm.setCartePanel(i, (Classique) listCartes.get(i),a.getNum());
			}catch(Exception e) {
				ihm.setCartePanel(i, null,a.getNum());
			}
		}
	}

	public boolean recupererTresor() {

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
				ihm.setTresorEnabled(t.getType());
				ArrayList<Carte> listCarte = new ArrayList<Carte>();
				for(Carte c : a.getListeCarteJoueur()) {
					if(c instanceof CarteTresor) {
						CarteTresor cT=(CarteTresor) c;
						if(cT.getType()==t.getType()) {
							listCarte.add(cT);
						}
					}
				}
				for(Carte c : listCarte) {
					a.removeCarte((Classique) c);
				}
				afficherCartes(a);
			}
			return true;
		}else {
			return false;
		}
	}

	public void verifierFinDePartie() {
		MessageFinPartie msg = new MessageFinPartie();
		//Condition(s) defaite
		if(noyade) {
			msg.setVictoire(false);
			msg.setTypeDefaite("Un des aventuriers s'est noye..");
			new FenetreFin(msg);
		}

		if(helicoCoule) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'heliport a coule..");
			new FenetreFin(msg);												//Heliport coule
		}

		if(curseur.getNiv()==10) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'ile a sombre completement..");
			new FenetreFin(msg);												//Curseur au niveau maximum
		}


		int temple=0,caverne=0,palais=0,jardin=0;
		for(int i=0;i<tresorsRecuperes.size(); i++) {
			int numT=tresorsRecuperes.get(i).getNum();
			switch (numT) {
			case 1: caverne=-1;
				break;
			case 2: palais=-1;
				break;
			case 3: jardin=-1;
				break;
			case 4: temple=-1;
				break;
			}
		}

		for(Tuile t : Grille.tuilesListe.values()) {
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

			msg.setTypeDefaite("Tous les tresors ont coules..");
			new FenetreFin(msg);												//Deux cases de recuperation de tresor coulees
		}


		//Condition victoire

		//Verification de si un joueur a une carte helicoptere


		int joueursPresentsHeliport=0;
		for(Tuile t : Grille.tuilesListe.values()) {
			if(t.getNum()==24) {			//Si la tuile est l'heliport
				joueursPresentsHeliport = t.getNbrAventurie();
			}
		}


		if(	joueursPresentsHeliport==joueursList.size()&&
				tresorsRecuperes.size()==4&&
				aCarteHelicoptere) {
			msg.setVictoire(true);
			new FenetreFin(msg);
		}

		aCarteHelicoptere=false;


	}

	private void melanger(ArrayList a) {
		Collections.shuffle(a);
	}

	/*public void test() {
		//Forcer les conditions de victoire
		for(Tuile t : grille.getTuilesListe().values()) {
			if(t.getNum()==22) {			//Si la tuile est l'heliport
				for(Aventurier a : joueursList) {
					a.deplacer(t);
				}
			}
		} 
		tresorsRecuperes.add(NomTresor.CaliceOnde);
		tresorsRecuperes.add(NomTresor.CaliceOnde);
		tresorsRecuperes.add(NomTresor.CaliceOnde);
		tresorsRecuperes.add(NomTresor.CaliceOnde);
		getJoueurTour().addCarte(new CarteHelicoptere("ble"));
		verifierFinDePartie();

	}*/

	public static int getNbJoueur() {
		return joueursList.size();
	}

	private void scenario_victoire() {

		for(Tuile t : Grille.tuilesListe.values()) {
			t.removeAllAventurier();
		}

		joueursList.removeAll(joueursList);
		joueursList.add(new Aviateur(0,"Aviateur",Pion.BLEU));
		joueursList.add(new Ingenieur(1,"Aviateur",Pion.ROUGE));
		joueursList.add(new Plongeur(2,"Aviateur",Pion.NOIR));
		joueursList.add(new Messager(3,"Aviateur",Pion.GRIS));

		for(Tuile t : Grille.tuilesListe.values()) {
			if(t.getNum()==24) {
				joueursList.get(1).setPosition(t);
				joueursList.get(2).setPosition(t);
				joueursList.get(3).setPosition(t);
			}else if(t.getNum()==311) {
				joueursList.get(0).setPosition(t);
			}
		}

		Aventurier a = joueursList.get(0);
		a.setListeCarteJoueur(new ArrayList<Carte>());
		for(int i=0;i<4;i++) {
			a.addCarte(new CarteTresor(i+"Cristal", NomTresor.CristalArdent));
		}
		a.addCarte(new CarteHelicoptere("1Helicoptere"));

		tresorsRecuperes.add(NomTresor.CaliceOnde);
		tresorsRecuperes.add(NomTresor.PierreSacree);
		tresorsRecuperes.add(NomTresor.StatueZephir);

		ihm.setTresorEnabled(NomTresor.CaliceOnde);
		ihm.setTresorEnabled(NomTresor.PierreSacree);
		ihm.setTresorEnabled(NomTresor.StatueZephir);



		afficherCartes(getJoueurTour());


	}

}

