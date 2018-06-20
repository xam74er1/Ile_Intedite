package ille_intedite;
import IHM.IHM;
import IHM.IHMV2;
import ille_intedite.Aventurie.Aventurier;
import utils.Parameters;

import java.lang.reflect.Parameter;
import java.util.*;
import javax.swing.JButton;

public class Grille {
	IHMV2 ihm;
	public static HashMap<String, Tuile> tuilesListe;
	private String[] nomTuiles = {"000Le Pont des Abimes",//00x = tuile normale 02x = tuile spawn 3xx = tuile tresor
			"020La Porte de Bronze",//021 = spawn ingé
			"311La Caverne des Ombres",//31x = tresor brasier
			"021La Porte de Fer",//025 = spawn plongeur
			"022La Porte d'Or",//026 = spawn navigateur
			"001Les Falaises de l'Oubli",
			"321Le Palais de Corail",//32x = tresor calice
			"023La Porte d'Argent",//024 = spawn messager
			"002Les Dunes de l'Illusion",
			"024Heliport",//023 = spawn pilote
			"025La Porte de Cuivre",//022 = spawn explo
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
	"332Le Jardin des Murmures"};
	private int tailleNom=nomTuiles.length;
	private ArrayList<String> listNomTuile;   


	//IHM pour metre en commun les bouton et les tuille 
	public Grille(IHMV2 ihm, ArrayList<Aventurier> aventuriers) {
		this.tuilesListe = new HashMap();
		this.ihm = ihm;

		//Sa aurais pus marche mais sa la melange 
		listNomTuile = new ArrayList<String>(Arrays.asList(nomTuiles));

		if(Parameters.ALEAS) {
			Collections.shuffle(listNomTuile);
		}


		iniGrille(aventuriers);
		//test
	}

	public void iniGrille(ArrayList<Aventurier> aventuriers){

		int i =0;
		int p =0;
		int current_tile = 0;

		for (int y = 0;y<6;y++) {
			for(int x = 0;x<6;x++) {


				if (i<2 || (i>3 && i<7) || i==11 || i==24 || (i>28 && i<32) || i>33) {
					tuilesListe.put(x+":"+y, new Tuile(-1,null,x,y));
				}else {

					//int num = tailleNom-1;

					//Je tente un truc avec une arrayList 
					Tuile t = new Tuile(Integer.parseInt(listNomTuile.get(current_tile).substring(0,3)),listNomTuile.get(current_tile).substring(3),x,y);
					int typeT= t.getNum();

					if (typeT>19 && typeT < 30 && p<aventuriers.size()) {
						Iterator<Aventurier> it = aventuriers.iterator();
						while(it.hasNext()) {
							Aventurier a = it.next();
							if (a.getNum()==typeT-20) {
								a.setPosition(t);
							}
						}

					}


					//				int num=(int) (Math.random()*tailleNom);
					//				Tuile t = new Tuile(Integer.parseInt(nomTuiles[num].substring(0,3)),nomTuiles[num].substring(3),x,y);
					//				nomTuiles[num]=nomTuiles[tailleNom-1];
					tuilesListe.put(x+":"+y, t);

					//tailleNom--;
					current_tile++;
				}
				i++;
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

	public void activateAll() {
		for(Tuile t : tuilesListe.values()) {
			if (t.getNum()!=-1) {
				t.getCase().removeBlanc();
			}
		}
	}



}
