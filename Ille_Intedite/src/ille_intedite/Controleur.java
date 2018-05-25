package ille_intedite;
import Carte.Carte;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import utils.Utils;
import utils.Utils.Pion;

public class Controleur implements Observateur{

	Curseur curseur;
	Grille grille;
	ArrayList<Aventurier> joueursList;
        private final int NBR_JOUEUR = 4;
       
        
        
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

	private void conditionDéfaite() {
		// TODO - implement Controleur.conditionDéfaite
		throw new UnsupportedOperationException();
	}

	private void deplacer() {
		// TODO - implement Controleur.deplacer
		throw new UnsupportedOperationException();
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

	public Aventurier getJoueurTour() {
		// TODO - implement Controleur.getJoueurTour
		throw new UnsupportedOperationException();
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

    
        public void miseAJourGrille() {
		
		//Provisoire 
                
                for(Aventurier a : joueursList){
                    Tuile t = a.getTuile();
                    
                    if(t != null){
                        ihm.getButonPlateau(t.getxT()+":"+t.getyT()).setBackground(a.getColor());
                    }
                    
                    
                }
                
                
	}
}