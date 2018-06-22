
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
import IHM.IHMV2;
import IHM.MessageFinPartie;
import IHM.MessageInit;
import IHM.PlaySound;

import ille_intedite.Aventurie.Aventurier;
import ille_intedite.Aventurie.Aviateur;
import ille_intedite.Aventurie.Ingenieur;
import ille_intedite.Aventurie.Messager;
import ille_intedite.Aventurie.Plongeur;

import java.util.*;

import utils.Parameters;
import utils.Utils.Pion;

public class Controleur implements Observateur{

	private Curseur curseur;
	
	private Grille grille;
	
	private ArrayList<Carte> carteTresorDeck;
	public ArrayList<Carte> carteTresorsDefausse;
	private ArrayList<Carte> inondationDeck;
	public ArrayList<Carte> inondationDefausse;
	private ArrayList<Carte> listPioche;
	
	public static ArrayList<Aventurier> joueursList;
	
	private ArrayList<NomTresor> tresorsRecuperes = new ArrayList<>();

	private int nbJoueurs;
	private int numCarte = -1;
	private int numTour;

	private String messageConsole;
	
	private Tuile helicoTuileSelect;

	private TypeMessage lastAction = TypeMessage.Clique_Send;
	
	private Classique carteSpe;

	private Aventurier memoireAventuire = null;
	private Aventurier urgence = null;
	private Aventurier givePlayer = null;

	private boolean isInit = false;
	private boolean noyade=false;
	private boolean helicoCoule=false;
	private boolean aCarteHelicoptere = false;
	private boolean urg=false;
	private boolean finTour=false;
	private boolean defausse=false;

	private VueGrille vue;

	private IHMV2 ihm;

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

	}

	@Override
	public void traiterMessage(Message msg) {


		/*
		 * Marche a suivre pour une action jouee par tour : 
		 * Si l'action est valide et terminee : getJoueurTour().actionJouer(); 
		 * Cela permet de savoir le nombre d'action jouees en un tour par une personne 
		 */

		switch(msg.getMessage()) {
		//Clic sur le bouton deplacer
		case Clique_Deplace :
			deplacer2(getJoueurTour());

			ihm.setIndication("Cliquez sur une case pour vous y deplacer");
			break;
		//Clic sur le bouton assecher
		case Clique_Asseche :
			assecher2();
			ihm.setIndication("Cliquez sur une case pour l'assecher");
			break;

			/* -------------------------CLIC SUR UNE TUILE ----------------------------------------------- */
			//Diffrentes actions lorsque l'on clique sur une tuile, on regarde l'action precedente pour savoir quoi faire 
		case Clique_Tuile :
			switch(lastAction) {

			//Si on a choisi de deplacer le joueur
			case Clique_Deplace:
				deplacer(msg.getLocation(),getJoueurTour());
				ihm.updateGrille();
				getJoueurTour().actionJouer();

				//Cas special pour l'ingeneur
				if(getJoueurTour() instanceof Ingenieur) {
					Ingenieur i = (Ingenieur) getJoueurTour();
					i.setDerniereActionAssecher(false);
				}
				//Cas special pour l'aviateur
				if(getJoueurTour() instanceof Aviateur) {
					Aviateur a = (Aviateur) getJoueurTour();
					Tuile from = a.getFrom();
					Tuile to = grille.getTuile(msg.getLocation());
					if(!(to.getxT()-from.getxT()==0 && Math.abs(to.getyT()-from.getyT())==1 || to.getyT()-from.getyT()==0 && Math.abs(to.getxT()-from.getxT())==1)) {
						a.tp();
					}
				}

				break;
				//si on a choisi d'utiliser un helico
			case Clique_Deplace_Helico :
				//tuile de destination choisie
				if (helicoTuileSelect!=null && memoireAventuire != null) {

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
					
				}else { //tuile de depart choisie

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
				ihm.miseAsJourJoeurCotte(getJoueurTour(),joueursList);
				miseAJourGrille();



				break;
				//Si on utilise un sac de sable
			case Clique_Asseche_SacDeSable :
				grille.getTuile(msg.getLocation()).assecher();
				ihm.setIndication("Case assechee en "+msg.getLocation());
				memoireAventuire.removeCarte(carteSpe);
				carteTresorsDefausse.add(carteSpe);
				grille.activateAll();
				ihm.setIndication("Cliquez sur une case pour l'assecher");
				miseAJourGrille();
				afficherCartes(getJoueurTour());
				ihm.miseAsJourJoeurCotte(getJoueurTour(),joueursList);
				break;
				//Si on a choisi d'assecher un case
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
				//si un aventurier se noie on le deplace
			case Clique_Fin_Tour :
				deplacer(msg.getLocation(),urgence);
				miseAJourGrille();
				grille.activateAll();
				lastAction=TypeMessage.Clique_Fin_Tour;
				afficherPiocheFinTour();

				break;
				//si un aventurier se noie on le deplace (idem precedent sinon bug)
			case Clique_Tuile :
				ihm.setIndication("");
				if (urg) {
					deplacer(msg.getLocation(),urgence);
					miseAJourGrille();
					grille.activateAll();
					lastAction=TypeMessage.Clique_Fin_Tour;
					finDeTour();

				}

				
			}

			break;
			
			//-------------FIN DES CLIC TUILE ----------------------------	
			
			//Clic sur le bouton Fin de tour
		case Clique_Fin_Tour :
			lastAction=TypeMessage.Clique_Deplace_Urgence;
			afficherPiocheFinTour();
			break;
			
			//Clic sur le bouton Recuperer Tresor
		case Clique_RecupereTresor :
			if(recupererTresor()) {
				getJoueurTour().actionJouer();
				ihm.setIndication("Vous avez recupere le tresor");
				PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\Recuperer_Tresor.wav");
			}else {
				ihm.setIndication("Impossible de recuperer le tresor");
			}
			break;
			
			//Clic sur une carte Sac de sable
		case Clique_Asseche_SacDeSable :
			
			//Si on est dans la fenetre de defausse, on la defausse
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
				//sinon on la joue
			}else {
				carteSpe=(Classique) msg.getCarte();
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
			
			//Clic sur le bouton Donner une carte
		case Clique_DonneCarte :

			ihm.setIndication("Cliquez sur la carte que vous voulez donner ");
			break;
			
			//Clic sur une carte Helico
		case Clique_Deplace_Helico :
			
			//si on est dans l'ecran de defausse, on le defausse
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
			}else { //sinon on le joue
				//on d'abord teste si l'Helico est utilise pour la victoire
				aCarteHelicoptere=true;
				verifierFinDePartie();

				carteSpe=(Classique) msg.getCarte();
				if(msg.getNumJoueur() != -1) {
					memoireAventuire = joueursList.get(msg.getNumJoueur());
				}else {
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
			//Si on clique sur une carte tresor
		case Clique_Carte_Tresor :
			//si on a choisi de donner la carte
			if(lastAction ==TypeMessage.Clique_DonneCarte) {

				numCarte = msg.getNum();
				ihm.setIndication("Cliquez sur le joueur a qui vous voulez donner la carte");
			//si on est dans l'ecran de defausse
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
			//si on a choisi de donner une carte on la donne au joueur
			if(lastAction == TypeMessage.Clique_Carte_Tresor && numCarte != -1&&getJoueurTour().getListeCarteJoueur().size()>numCarte&&msg.getNum()!=-1) {

				givePlayer = joueursList.get(msg.getNum()); 
				Classique c =  (Classique) getJoueurTour().getListeCarteJoueur().get(numCarte);

				//Si le joeur sur le quelle on as clique sur le bouton est  sur la meme case que le joeure actuelle / ou quil peut donne la carte 
				if(donnerCarteJoueur(msg.getNum())) {
					getJoueurTour().getListeCarteJoueur().remove(c);
					givePlayer.getListeCarteJoueur().add(c);
					afficherCartes(getJoueurTour());
					ihm.miseAsJourJoeurCotte(getJoueurTour(), joueursList);
					numCarte = -1;
					ihm.setIndication("La carte as bien ete donne ");
				}else {
					ihm.setIndication("Vous ne pouvez pas donne de carte as ce joueure ! ");
				}
			}

			break;

		case Clique_Ok :
			//Si on est dans l'affichage de la pioche tresor
			if (lastAction==TypeMessage.Clique_Fin_Tour){
				if(getJoueurTour().getNbCarte()>5) {
					ihm.afficherPlateau();
					afficherDefausseFinTour();
				}else {
					ihm.afficherPlateau();
					afficherPiocheInondation();
				}
				//Si on est dans l'affichage de la pioche inondation
			}else if (lastAction==TypeMessage.Clique_Ok){
				ihm.afficherPlateau();
				finDeTour();
				//idem mais si on a affiche la defausse avant
			}else if(finTour) {
				ihm.afficherPlateau();
				finDeTour();
			}

			break;

		}

		//Si la conditon au-dessus est fausse elle continue 

		lastAction = msg.getMessage();
		//Pour les besoins du code
		if (helicoTuileSelect!=null) {
			lastAction=TypeMessage.Clique_Deplace_Helico;
		}else if(defausse) {
			lastAction=TypeMessage.Defausse_Joueur;
		}


		if(getJoueurTour().getNbAction()<1) {
			afficherPiocheFinTour();
		}

	}
	
	//Gestion de la fin de tour (apres affichage des pioches)
	private void finDeTour() {
		ihm.setIndication("Fin du tour du joueur "+numTour);
		
		//on regarde s'il faut sauver quelqu'un
		urg=false;
		deplacerUrgence();

		if(!urg) {

			getJoueurTour().finTour();
			numTour++;		
			numTour%=joueursList.size();
			ihm.setIndication("Joueur "+(numTour+1)+" A vous de jouer");
			ihm.miseAJourPlayer(numTour," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());
			afficherCartes(getJoueurTour());
			ihm.miseAsJourJoeurCotte(getJoueurTour(), joueursList);
			grille.activateAll();
			ihm.afficherPlateau();
			miseAJourGrille();
			finTour=false;
			//on teste si un des tresor a coule
			verifierFinDePartie();
		}
	}

	//affichage de la pioche de carte tresor
	private void afficherPiocheFinTour() {
		finTour=true;
		listPioche=new ArrayList();
		ihm.setIndication("Voici les cartes tresor que vous avez pioche");
		melanger(carteTresorDeck);
		listPioche.add(piocherClassique(getJoueurTour()));
		listPioche.add(piocherClassique(getJoueurTour()));
		ihm.afficherPioche(listPioche,true);
	}
	
	//affichage des cartes du joueur pour en defausser
	private void afficherDefausseFinTour() {
		defausse=true;
		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;
			ihm.afficherPioche(getJoueurTour().getListeCarteJoueur(),false);
			ihm.setIndication("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes a defausser :");
		}
	}
	
	//affichage de la pioche de cartes inondation
	private void afficherPiocheInondation(){
		ihm.setIndication("Voici les cartes inondation que vous avez pioche");
		listPioche=new ArrayList();
		for (int i=0;i<curseur.getNbCartesInond();i++) {
			listPioche.add(piocherInondation());
		}
		ihm.afficherPioche(listPioche,true);
	}

	//creation du deck de cartes inondation
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

	//creation du deck de cartes tresor
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
					carteTresorDeck.add(new CarteTresor(j+"Zephyr", NomTresor.StatueZephyr));
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

	//fonction pour piocher une carte tresor
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

	//initialisation du jeu
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

		curseur=new Curseur(msgInit.niveauEau);
		ihm.afficherNivCurseur(msgInit.niveauEau);
		
		//Differents scenari pouvant etre utilises
		String sc = msgInit.scenario;
		if(sc == "Victoire") {
			scenario_victoire();
		}else if(sc == "D\u00E9faite curseur") {
			scenario_defaite_curseur();
		}else if(sc == "D\u00E9faite heliport") {
			scenario_defaite_heliport();
		}else if(sc == "D\u00E9faite noyade") {
			scenario_defaite_noyade();
		}else if(sc == "D\u00E9faite tr\u00E9sor") {
			scenario_defaite_tresor();
		}

		ihm.miseAJourPlayer(0," ( "+getJoueurTour().getNom()+" )", getJoueurTour().getColor());

		ihm.miseAsJourJoeurCotte(getJoueurTour(), joueursList);

		afficherCartes(getJoueurTour());
		isInit = true;
	}

	//deplacement d'un aventurier
	private void deplacer(String str, Aventurier a) {

		Tuile t = grille.getTuile(str);
		a.deplacer(t);
		grille.activateAll();
		miseAJourGrille();

	}

	//deplacement d'un aventurier (necessite deux fonctions)
	private void deplacer2(Aventurier a) {

		ArrayList<Tuile> tuilesDep = a.deplacer2();
		if (tuilesDep.size()==0 && urg) {
			noyade=true;
			verifierFinDePartie();

		}

		ihm.afficherDep(tuilesDep);
		miseAJourGrille();


	}

	//deplacement (si necessaire) des aventuriers a sauver
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

	//assecher une case
	private void assecher(String str) {

		Tuile t = grille.getTuile(str);

		t.assecher();
		grille.activateAll();
		miseAJourGrille();

	}

	//encore une fois necessite deux fonctions
	private void assecher2() {
		Aventurier a = getJoueurTour();
		ihm.afficherDep(a.assecher2());
		miseAJourGrille();
	}


	//Savoir si le joeur num est sur la case , il vas retune tout les joeur as qui il peut donne

	//pour donner une carte a un joueur

	private boolean donnerCarteJoueur(int num) {

		int nbr = getJoueurTour().getNum();
		for(Aventurier a : getJoueurTour().getJoueurTuile()) {
			//Si l'aventurie n'est pas lui meme , et quil et egale au meme que le joeur cible je retun true 
			if(nbr != a.getNum()&&num==a.getNum()) {
				givePlayer = a;
				return true;
			}

		}
//Si non il ne peut pas donne les carte as ce joeur 
		return  false;
	}


	//afficher un joueur sur sa case

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

	//piocher 6 cartes inondation (debut du jeu)
	public void piocher6Inondation() {
		for(int i = 0 ;i <6 ;i++) {
			piocherInondation();
		}
	}

	//piocher une carte inondation
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

	//retourne le joueur courant
	public Aventurier getJoueurTour() {
		int i =  numTour%(joueursList.size());

		return joueursList.get(i);

	}


	//mettre a jour le plateau de jeu
	public void miseAJourGrille() {
 
		vue.afficherGrille();

	}	

	//afficher les cartes d'un joueur
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

	//recuperer un tresor
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

	//verification des conditions de fin de partie
	public void verifierFinDePartie() {
		MessageFinPartie msg = new MessageFinPartie();
		//Condition(s) defaite
		if(noyade) {
			msg.setVictoire(false);
			msg.setTypeDefaite("Un des aventuriers s'est noy\u00E9..");
			new FenetreFin(msg);
		}

		if(helicoCoule) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'h\u00E9liport a coul\u00E9..");
			new FenetreFin(msg);												//Heliport coule
		}

		if(curseur.getNiv()==10) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'\u00CEle a sombr\u00E9 compl\u00E8tement..");
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

			msg.setTypeDefaite("Un des tr\u00E9sors coul\u00E9..");
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

	//pour melanger un deck/defausse
	private void melanger(ArrayList a) {
		Collections.shuffle(a);
	}

	//retourne le nombre de joueurs
	public static int getNbJoueur() {
		return joueursList.size();
	}

	//SCENARI
	//victoire
	private void scenario_victoire() {

		for(Tuile t : Grille.tuilesListe.values()) {
			t.removeAllAventurier();
		}

		joueursList.removeAll(joueursList);
		joueursList.add(new Aviateur(0,"Aviateur",Pion.BLEU));
		joueursList.add(new Ingenieur(1,"Ingenieur",Pion.ROUGE));
		joueursList.add(new Plongeur(2,"Plongeur",Pion.NOIR));
		joueursList.add(new Messager(3,"Messager",Pion.GRIS));

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
		tresorsRecuperes.add(NomTresor.StatueZephyr);

		ihm.setTresorEnabled(NomTresor.CaliceOnde);
		ihm.setTresorEnabled(NomTresor.PierreSacree);
		ihm.setTresorEnabled(NomTresor.StatueZephyr);



		afficherCartes(getJoueurTour());


	}

	//defaite par le curseur
	private void scenario_defaite_curseur() {
		curseur.setNiv(8);
		curseur.monteeEaux();
		ihm.afficherNivCurseur(9);
		
		carteTresorDeck.removeAll(carteTresorDeck);
		carteTresorDeck.add(new MonteeEaux("1MonteeDesEaux"));
		carteTresorDeck.add(new MonteeEaux("2MonteeDesEaux"));
	}
	
	//defaite par heliport
	private void scenario_defaite_heliport() {
		inondationDeck.removeAll(inondationDeck);
		for(Tuile t : Grille.tuilesListe.values()) {
			if(t.getNum()==24) {
				t.setStatut(-1);
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}
			if(t.getNum()==22 || t.getNum()==23) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}
		}
	}
	
	//defaite par noyade d'un joueur
	private void scenario_defaite_noyade() {
		curseur.setNiv(8);
		ihm.afficherNivCurseur(8);
		inondationDeck.removeAll(inondationDeck);
		for(Tuile t :Grille.tuilesListe.values()){
			if(t.getNum()!=-1 && t.getNum()!=24) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}
		}
		carteTresorDeck.removeAll(carteTresorDeck);
		tresorsRecuperes.add(NomTresor.CaliceOnde);
		tresorsRecuperes.add(NomTresor.PierreSacree);
		tresorsRecuperes.add(NomTresor.StatueZephyr);
		tresorsRecuperes.add(NomTresor.CristalArdent);
		ihm.setTresorEnabled(NomTresor.CaliceOnde);
		ihm.setTresorEnabled(NomTresor.PierreSacree);
		ihm.setTresorEnabled(NomTresor.StatueZephyr);
		ihm.setTresorEnabled(NomTresor.CristalArdent);
	}
	
	//defaite par perte d'un tresor
	private void scenario_defaite_tresor() {
		inondationDeck.removeAll(inondationDeck);
		for(Tuile t :Grille.tuilesListe.values()){
			if(t.getNum()!=-1 && t.getNum()/100==3 || t.getNum()==1) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}
		}
		
		carteTresorDeck.removeAll(carteTresorDeck);
	}

}

