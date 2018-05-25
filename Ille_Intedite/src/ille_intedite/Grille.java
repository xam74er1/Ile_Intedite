package ille_intedite;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import javax.swing.JButton;

public class Grille {
        IHM ihm;
	private HashMap<String,Tuile> tuilesListe;
        
     
        
        //IHM pour metre en commun les bouton et les tuille 
    public Grille(IHM ihm) {
        this.tuilesListe = new HashMap();
        this.ihm = ihm;
        
        
    }
	
        public void iniGrille(){
            int num = 0;
           for(int x = 0;x<6;x++){
               for(int y =0;y<6;y++){
               Tuile t = new Tuile(num,0,x,y);
               //Meme nom que pour les bouton 
               tuilesListe.put(x+":"+y,t);
               num++;
           }
           }
        }
        

	

	/**
	 * 
	 * @param t1
	 * @param t2
	 */
	public boolean assechePossible(Tuile t1, Tuile t2) {
		// TODO - implement Grille.assechePossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param xG
	 * @param yG
	 */
	public Tuile getTuile(int x, int y) {
		// TODO - implement Grille.getTuile
		return tuilesListe.get(x+":"+y);
	}
        
        public Tuile getTuile(String str) {
		// TODO - implement Grille.getTuile
		return tuilesListe.get(str);
	}

	/**
	 * 
	 * @param a
	 */
	public void getDepPossible(Aventurier a) {
		// TODO - implement Grille.getDepPossible
		throw new UnsupportedOperationException();
	}

}