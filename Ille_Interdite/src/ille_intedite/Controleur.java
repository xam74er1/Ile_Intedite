
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
	private ArrayList<CarteInondation> inondationDeck;
	public ArrayList<CarteInondation> inondationDefausse;
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

	private int numCarte = -1;

	private boolean isInit = false;



	IHMV2 ihm;

//Consturcteur
	public Controleur(IHMV2 ihm,VueGrille vue,MessageInit msgInit) {

		this.ihm = ihm;
		carteTresorDeck = new ArrayList<Carte>();
		carteTresorsDefausse = new ArrayList<Carte>();
		inondationDeck = new ArrayList<CarteInondation>();
		inondationDefausse = new ArrayList<CarteInondation>();
		joueursList = new ArrayList<Aventurier>();
		this.vue = vue;
		nbJoueurs = msgInit.nbJoueurs;
		init(msgInit.listJoueurs);
		numTour =0;
		ihm.afficherNivCurseur(msgInit.niveauEau);
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
				

				if (helicoTuileSelect!=null) {


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
					getJoueurTour().removeCarte(carteSpe);
					carteTresorsDefausse.add(carteSpe);
					helicoTuileSelect=null;
				}else {

					ihm.setIndication("Clique sur une case pour vous deplace");


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
				miseAJourGrille();



				break;

/* -------------------------FIN CLIQUE SUR UNE TUILLE ----------------------------------------------- */
				//Actoin lors du clique sur un sac de sable (carte ) 
			case Clique_Asseche_SacDeSable :
				grille.getTuile(msg.getLocation()).assecher();
				ihm.setIndication("Case assechee en "+msg.getLocation());
				getJoueurTour().removeCarte(carteSpe);
				carteTresorsDefausse.add(carteSpe);
				grille.activateAll();
				ihm.setIndication("Clique sur une case pour l'assecher");
				miseAJourGrille();
				afficherCartes(getJoueurTour());
				break;
//Action lors du clique du bouton assehce 
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
//Action lors du bouton fin de tour 
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
					afficherPiocheFinTour();

				}

				//-------------FIN DES CLIQUE TUILE ----------------------------
			}


			break;

		case Clique_Fin_Tour :
			lastAction=TypeMessage.Clique_Deplace_Urgence;
			afficherPiocheFinTour();
			break;

		case Clique_RecupereTresor :
			if(recupereTresor()) {
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
			carteSpe=(Classique) msg.getCarte();
			ArrayList<Tuile> listAsseche = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getStatut()==1) {
					listAsseche.add(t);
				}
			}
			ihm.afficherDep(listAsseche);
			miseAJourGrille();

			break;
		case Clique_DonneCarte :

			ihm.setIndication("Clique sur la carte que vous voulez donner ");
			break;
		case Clique_Deplace_Helico :
			ihm.setIndication("Clique sur le joueur que vous voulez deplacer");
			carteSpe=(Classique) msg.getCarte();
			ArrayList<Tuile> listCaseAvent = new ArrayList<Tuile>() ;
			for(Tuile t : Grille.tuilesListe.values()) {
				if (t.getAventurie().size()!=0) {
					listCaseAvent.add(t);
				}
			}
			ihm.afficherDep(listCaseAvent);
			miseAJourGrille();

			break;

		case Clique_Carte_Tresor :

			if(lastAction ==TypeMessage.Clique_DonneCarte) {

				numCarte = msg.getNum();
				ihm.setIndication("Cliquez sur le joueur a qui vous voulez donner la carte");
			}else if(lastAction==TypeMessage.Defausse_Joueur) {
				getJoueurTour().removeCarte((Classique) msg.getCarte());
				carteTresorsDefausse.add(msg.getCarte());
				if(getJoueurTour().getNbCarte()>5) {
					afficherDefausseFinTour();
				}else {
					defausse=false;
					finDeTour();
				}
			}
			break;
		case Clique_Joueur :

			if(lastAction == TypeMessage.Clique_Carte_Tresor && numCarte != -1&&getJoueurTour().getListeCarteJoueur().size()>numCarte&&msg.getNum()!=-1) {

				givePlayer = joueursList.get(msg.getNum()); 
				Classique c =  (Classique) getJoueurTour().getListeCarteJoueur().get(numCarte);

				if(donneCarteJoeur(msg.getNum())) {
					getJoueurTour().getListeCarteJoueur().remove(c);
					givePlayer.getListeCarteJoueur().add(c);
					afficherCartes(getJoueurTour());
					numCarte = -1;
					ihm.setIndication("");
				}
			}

			break;
			
		case Clique_Ok :
			
			if(defausse) {
				ihm.afficherPlateau();
				ihm.afficherDefausse(getJoueurTour());
			}else if (lastAction==TypeMessage.Clique_Fin_Tour){
				if(getJoueurTour().getNbCarte()>5) {
					afficherDefausseFinTour();
				}else {
					ihm.afficherPlateau();
					finDeTour();
					//afficherPiocheInondation();
				}
			}else if (lastAction==TypeMessage.Clique_Ok){
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
			//	System.out.println(" nb act = "+getJoueurTour().getNbAction());
			afficherPiocheFinTour();
		}

	}

	private void finDeTour() {
		// TODO Auto-generated method stub

		

		if(!urg) {
			ihm.setIndication("Fin du tour du joueur "+numTour);


			for (int i=0;i<curseur.getNbCartesInond();i++) {
				piocherInondation();
			}
		}
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
		System.out.println(listPioche.size());
		ihm.afficherPioche(listPioche);
	}
	
	private void afficherDefausseFinTour() {
		defausse=true;
		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;
			ihm.afficherDefausse(getJoueurTour());
			ihm.setIndication("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes ÃƒÂ  dÃƒÂ©fausser :");

		}
	}

	public void creeDeckInondation() {
		for(Tuile t :Grille.tuilesListe.values()){
			if(t.getNum()!=-1) {
				inondationDeck.add(new CarteInondation(t.getNom(),t));
			}

		}

		if(Parameters.ALEAS) {
			melanger(inondationDeck);
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
		}

		carteTresorDeck.add(new CarteSacSable("1SacsDeSable"));
		carteTresorDeck.add(new CarteSacSable("2SacsDeSable"));
		carteTresorDeck.add(new CarteSacSable("3SacsDeSable"));

		carteTresorDeck.add(new MonteeEaux("1MonteeDesEaux"));
		carteTresorDeck.add(new MonteeEaux("2MonteeDesEaux"));
		//carteTresorDeck.add(new MonteeEaux("3Monte des EAU"));

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

	public void init(ArrayList<Aventurier> listJoueurs) {

		joueursList = listJoueurs;


		Collections.shuffle(joueursList);

		melanger(joueursList);
		grille = new Grille(ihm,joueursList);

		curseur = new Curseur(0);

		ihm.fillPlataux2(grille);

		creeDeckInondation();
		creeDeckClassique();

		for(Aventurier av : joueursList) {
			piocherClassique(av);
			piocherClassique(av);
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

		Iterator<Aventurier> it = joueursList.iterator();
		while(it.hasNext()) {
			Aventurier a =it.next();
			if(!urg && a.getTuile().getStatut()==2) {
				ihm.setIndication("Deplacez "+a.getNom()+" en urgence");
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

	private boolean donneCarteJoeur(int num) {
		// TODO - implement Controleur.donneCarte






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



			Classique c =  (Classique) getJoueurTour().getListeCarteJoueur().get(num);

			getJoueurTour().getListeCarteJoueur().remove(c);

			givePlayer.getListeCarteJoueur().add(c);

			ihm.setIndication("Carte donne");
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
			ihm.setIndication("Joueur sur la meme case  : "+str+" \n Veiller entre le numerau du joeur selectione");
			return true;
		}
	}

	private void piocherTresor() {

		for(int i =0;i<2;i++) {
			Carte cC = carteTresorDeck.get(1);
			getJoueurTour().getListeCarteJoueur().add(cC);
		}

	}

	@Deprecated
	public void afficherListeCarteJoueur() {
		ihm.setIndication("Main du joueur : " + getJoueurTour().getNom());
		int i = 1;
		for (Carte c : getJoueurTour().getListeCarteJoueur()){
			ihm.setIndication(i +" : " + c.getNom());
			i = i+1;
		}

		if (getJoueurTour().getListeCarteJoueur().size() > 5) {
			lastAction = TypeMessage.Defausse_Joueur;
			ihm.setIndication("Vous avez " + (getJoueurTour().getListeCarteJoueur().size()-5) + " cartes en trop dans votre main, choisir les cartes ÃƒÂƒÃ‚Â  dÃƒÂƒÃ‚Â©fausser :");
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

	private void piocherInondation() {
		if(inondationDeck.size()!=0) {
			CarteInondation cInP = inondationDeck.get(0);
			cInP.getTuile().inonder();
			if(cInP.getTuile().getNum()==22 && cInP.getTuile().getStatut()==2) {
				helicoCoule=true;
				verifierFinDePartie();
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
				melanger(inondationDeck);
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
				ihm.setCartePanel(i, (Classique) listCartes.get(i));
			}catch(Exception e) {
				ihm.setCartePanel(i, null);
			}
		}

	}

	public boolean recupereTresor() {
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
				ihm.setTresorEnabled(t.getType());
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

	public void verifierFinDePartie() {
		MessageFinPartie msg = new MessageFinPartie();
		//Condition(s) defaite
		if(noyade) {
			msg.setVictoire(false);
			msg.setTypeDefaite("Un des aventuriers s'est noyé..");
			new FenetreFin(msg);
					}

		if(helicoCoule) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'héliport a coulé..");
			new FenetreFin(msg);												//Heliport coule
		}

		if(curseur.getNiv()==10) {
			msg.setVictoire(false);
			msg.setTypeDefaite("L'île a sombré complètement..");
			new FenetreFin(msg);												//Curseur au niveau maximum
		}


		int temple=0,caverne=0,palais=0,jardin=0;
		for(int i=0;i<tresorsRecuperes.size(); i++) {
			int numT=tresorsRecuperes.get(i).getNum();
			switch (numT) {
			case 1: caverne=-1;
			case 2: palais=-1;
			case 3: jardin=-1;
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
			
			msg.setTypeDefaite("Tout les trésors ont coulés..");
			new FenetreFin(msg);												//Deux cases de recuperation de tresor coulees
		}
		

		//Condition victoire

		//Verification de si un joueur a une carte helicoptere
		aCarteHelicoptere = false;
		for(int i=0; i<joueursList.size(); i++) {
			for(int j=0; j<joueursList.get(i).getNbCarte();j++) {
				if(joueursList.get(i).getCarte(j) instanceof CarteHelicoptere) {
					aCarteHelicoptere=true;
				}
			}
		}


		int joueursPresentsHeliport=0;
		for(Tuile t : Grille.tuilesListe.values()) {
			if(t.getNum()==22) {			//Si la tuile est l'heliport
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


}

