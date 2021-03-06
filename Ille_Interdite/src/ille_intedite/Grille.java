package ille_intedite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

//import IHM.IHM;
import IHM.IHMV2;
import ille_intedite.Aventurie.Aventurier;
import ille_intedite.Aventurie.Aviateur;
import ille_intedite.Aventurie.Explorateur;
import ille_intedite.Aventurie.Ingenieur;
import ille_intedite.Aventurie.Messager;
import ille_intedite.Aventurie.Navigateur;
import ille_intedite.Aventurie.Plongeur;
import utils.Parameters;

public class Grille {
	IHMV2 ihm;
	public static HashMap<String, Tuile> tuilesListe;
	private String[] nomTuiles = {"000Le Pont des Abimes",//00x = tuile normale 02x = tuile spawn 3xx = tuile tresor
			"020La Porte de Bronze",//021 = spawn ingenieur
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
							if ((a instanceof Explorateur && t.getNum()==25)
									|| (a instanceof Ingenieur && t.getNum()==20)
									|| (a instanceof Plongeur && t.getNum()==21)
									|| (a instanceof Navigateur && t.getNum()==22)
									|| (a instanceof Aviateur && t.getNum()==24)
									|| (a instanceof Messager && t.getNum()==23)) {
								a.setPosition(t);
							}
					
						}

					}

					tuilesListe.put(x+":"+y, t);

					//tailleNom--;
					current_tile++;
				}
				i++;
			}
		}


	}

	public Tuile getTuile(int x, int y) {
		// TODO - implement Grille.getTuile

		return tuilesListe.get(x+":"+y);
	}

	public Tuile getTuile(String str) {
		// TODO - implement Grille.getTuile
		return tuilesListe.get(str);
	}

	public void activateAll() {
		for(Tuile t : tuilesListe.values()) {
			if (t.getNum()!=-1) {
				t.getCase().removeBlanc();
			}
		}
	}



}
