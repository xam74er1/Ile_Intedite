package ille_intedite;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import javax.swing.JButton;

public class Grille {
        IHM ihm;
	private HashMap<String,Tuile> tuilesListe;
//	private final String[] nomTuiles = ["Le Pont des Abimes",
//	                                    "La Porte de Bronze",
//	                                    "La Caverne des Ombres",
//	                                    "La Porte de Fer",
//	                                    "La Porte d’Or",
//	                                    "Les Falaises de l’Oubli",
//	                                    "Le Palais de Corail",
//	                                    "La Porte d’Argent",
//	                                    "Les Dunes de l’Illusion",
//	                                    "Heliport",
//	                                    "La Porte de Cuivre",
//	                                    "Le Jardin des Hurlements",
//	                                    "La Foret Pourpre",
//	                                    "Le Lagon Perdu",
//	                                    "Le Marais Brumeux",
//	                                    "Observatoire",
//	                                    "Le Rocher Fantome",
//	                                    "La Caverne du Brasier",
//	                                    "Le Temple du Soleil",
//	                                    "Le Temple de La Lune",
//	                                    "Le Palais des Marees",
//	                                    "Le Val du Crepuscule",
//	                                    "La Tour du Guet",
//	                                    "Le Jardin des Murmures"]
        
     
        
        //IHM pour metre en commun les bouton et les tuille 
    public Grille(IHM ihm) {
        this.tuilesListe = new HashMap();
        this.ihm = ihm;
        iniGrille();
        //test
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

	public HashMap<String, Tuile> getTuilesListe() {
		return tuilesListe;
	}
	
	

}