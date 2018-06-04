package ille_intedite;
import IHM.IHM;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import javax.swing.JButton;

public class Grille {
        IHM ihm;
	private HashMap<int,Tuile> tuilesListe;
	private String[] nomTuiles = ["000Le Pont des Abimes",//00x = tuile normale 02x = tuile spawn 3xx = tuile tresor
	                                    "021La Porte de Bronze",//021 = spawn ingé
	                                    "311La Caverne des Ombres",//31x = tresor brasier
	                                    "022La Porte de Fer",//022 = spawn plongeur
	                                    "023La Porte d’Or",//023 = spawn navigateur
	                                    "001Les Falaises de l’Oubli",
	                                    "321Le Palais de Corail",//32x = tresor calice
	                                    "024La Porte d’Argent",//024 = spawn messager
	                                    "002Les Dunes de l’Illusion",
	                                    "025Heliport",//025 = spawn pilote
	                                    "026La Porte de Cuivre",//026 = spawn explo
	                                    "331Le Jardin des Hurlements",//33x = tresor statue
	                                    "003La Foret Pourpre",
	                                    "004Le Lagon Perdu",
	                                    "005Le Marais Brumeux",
	                                    "006Observatoire",
	                                    "007Le Rocher Fantome",
	                                    "312La Caverne du Brasier",
	                                    "341Le Temple du Soleil",//34x = tresor pierre
	                                    "342Le Temple de La Lune",
	                                    "322Le Palais des Marees",
	                                    "008Le Val du Crepuscule",
	                                    "009La Tour du Guet",
	                                    "332Le Jardin des Murmures}"];
	private int tailleNom=nomTuiles.lenght();
        
     
        
        //IHM pour metre en commun les bouton et les tuille 
    public Grille(IHM ihm) {
        this.tuilesListe = new HashMap();
        this.ihm = ihm;
        iniGrille();
        //test
    }
	
	public void iniGrille(){
		for (int i = 0;i<32;i++) {
			int x = i%6;
			int y = i/6;
			
			if (i<2 || (i>3 && i<7) || i==11 || i==24 || (i>28 && i<32) || i>33) {
				tuilesListe.put(i, new Tuile(null,null,x,y));
			}else {
				num=Math.random()*tailleNom;
				Tuile t = new Tuile(Integer.parseInt(nomTuiles[num].substring(0,3)),nomTuiles[num].substring(3),x,y);
				nomTuiles[num]=nomTuiles[tailleNom-1];
				tailleNom--;
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