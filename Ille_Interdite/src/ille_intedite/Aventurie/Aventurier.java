package ille_intedite.Aventurie;

import Carte.Carte;
import ille_intedite.NomTresor;
import ille_intedite.Tresor;
import ille_intedite.Tuile;
import java.awt.Color;
import java.util.*;
import utils.Utils;
import utils.Utils.Pion;

public class Aventurier {

    ArrayList<Carte> main;
    Tuile tuile;
    private int Num;
    private String nom;
    private NomTresor getType;
    private Pion pion;

    public Aventurier(int Num, String nom,Pion pion) {
        this.Num = Num;
        this.nom = nom;
        this.tuile = null;
        this.pion = pion;
        main = new ArrayList<Carte>();
    }

    public Tuile getPosition() {
        // TODO - implement Aventurier.getPosition
        return this.tuile;
    }

    /**
     *
     * @param xG
     * @param yG
     */
    public void setPosition(int xG, int yG) {
        // TODO - implement Aventurier.setPosition
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param t
     */
    public void setPosition(Tuile t) {
        // TODO - implement Aventurier.setPosition

        this.setTuile(t);
    }

    /**
     *
     * @param t
     */
    //Action pour deplace la perssone 
    public void deplacer(Tuile t) {
        // TODO - implement Aventurier.deplacer
        if (deplacementPossible(getTuile(), t)) {
            setPosition(t);
        }

    }

    /**
     *
     * @param t1
     * @param t2
     */
    public static boolean deplacementPossible(Tuile from, Tuile to) {
        int x = Math.abs(from.getxT() - to.getxT());
        int y = Math.abs(from.getyT() - to.getyT());
        // Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
        return ((x == 1 & y == 0) || (x == 0 & y == 1)) & (to.getStatue() < 2);
    }

    /**
     *
     * @param joeur
     */
    public void donneCarteJoeur(Aventurier joeur) {
        // TODO - implement Aventurier.donneCarteJoeur
        throw new UnsupportedOperationException();
    }

    public int getNbCarte() {
        return main.size();
    }

    /**
     *
     * @param numCarte
     */
    public Carte getCarte(int numCarte) {
        // TODO - implement Aventurier.getCarte
        return main.get(numCarte);
    }

    public void Defausse() {
        // TODO - implement Aventurier.Defausse
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param C
     */
    public void addCarte(Carte C) {
        // TODO - implement Aventurier.addCarte
        main.add(C);
    }

    /**
     *
     * @param c
     */
    public void removeCarte(Carte c) {
        // TODO - implement Aventurier.removeCarte
       main.remove(c);
    }

    /**
     *
     * @param c
     */
    public void addDefausePerso(Tresor c) {
        // TODO - implement Aventurier.addDefausePerso
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param main
     */
    public void restoreMain(ArrayList<Carte> main) {
        // TODO - implement Aventurier.restoreMain
        throw new UnsupportedOperationException();
    }

    public void RecupereTresort() {
        // TODO - implement Aventurier.RecupereTresort
        throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuile;
    }

    // Place dans une nouvelle case 
    private void setTuile(Tuile t) {
        // Enleve laventuire de la case , lui affecte une nvll case et lajoute as la nvll case 
        if(this.getTuile()!=null){
            this.getTuile().removeAventurie(this);
        }
        
        this.tuile = t;
        
        t.addAventurie(this);
    }

    public int getNum() {
        return Num;
    }

    public Pion getPion() {
        return pion;
    }
    
    
    public Color getColor(){
        
        return pion.getCouleur();
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NomTresor getGetType() {
        return getType;
    }

    public void setGetType(NomTresor getType) {
        this.getType = getType;
    }

}
