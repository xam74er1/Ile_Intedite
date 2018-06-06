package ille_intedite;

import ille_intedite.Aventurie.Aventurier;
import Carte.NomTresor;
import java.util.*;

public class Tuile {

    ArrayList<Aventurier> AventuriesList;
    private final int num;
    // Pour les inodation : 0 = pas inonder , 1 = inonder , 2 couler 
    private int statut;
    //Type cest quoi ?
    private final String nom;
    private int xT;
    private int yT;

    /**
     *
     * @param num
     * @param statut
     * @param type
     */
    
    public Tuile(int num, String nom, int xT, int yT) {
        this.num = num;
        this.nom = nom;
        this.xT = xT;
        this.yT = yT;
        statut =0;
        AventuriesList = new ArrayList<Aventurier>();
    }
    
    
    

    public int getNum() {
		return num;
	}

	public void inonder() {
        // TODO - implement Tuile.inonder
       
        statut++;
    }
    
    public void assecher(){
    	setStatut(0);
    }

    private void setStatut(int i) {
        // TODO - implement Tuile.setStatut
        statut = i;
    }

    public int getxT() {
        return xT;
    }

    public void setxT(int xT) {
        this.xT = xT;
    }

    public int getyT() {
        return yT;
    }

    public void setyT(int yT) {
        this.yT = yT;
    }

    /**
     *
     * @param xT
     * @param yT
     */
    public int[] getLocation() {
        // TODO - implement Tuile.getLocation
        return new int[]{this.xT, this.yT};
    }

    public int getStatue() {
        // TODO - implement Tuile.getStatue
        return statut;
    }

    /**
     *
     * @param joueur
     */
    public boolean isOnTuile(Aventurier joueur) {
        return AventuriesList.contains(joueur);
    }

    public void addAventurie(Aventurier joueur) {
        AventuriesList.add(joueur);
    }

    public void removeAventurie(Aventurier joueur) {
        AventuriesList.remove(joueur);
    }
    
    public ArrayList<Aventurier> getAventurie() {
    	return AventuriesList;
    }
    
    public int getNbrAventurie() {
    	return AventuriesList.size();
    }

    public NomTresor getTresort() {
        // TODO - implement Tuile.getTresort
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Tuile{" + "num=" + num + ", statut=" + statut + ", type=" + nom + ", xT=" + xT + ", yT=" + yT + '}';
    }
    
    public String getNom() {
    	return nom;
    }
    

}
