package ille_intedite;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import IHM.IHM;
import IHM.IHMV2;
import ille_intedite.Aventurie.Aventurier;

public class VueGrille {

	Controleur ctrl;
	IHMV2 ihm;

	public void afficherGrille() {


		HashMap<String, Tuile> hmTuille =  ctrl.grille.getTuilesListe();

		Iterator<Entry<String, Tuile>> it = hmTuille.entrySet().iterator();

		while(it.hasNext()) {
			Entry<String, Tuile> me = it.next();


			//J'effasse laventurie 

			ihm.getButonPlateau(me.getKey()).setBackground(null);
			//Puis je redessine 
			for(Aventurier a : me.getValue().getAventurie()) {
				//Provisoire 
				ihm.getButonPlateau(me.getKey()).setBackground(a.getColor());;

			}

			if (me.getValue().getNum()==-1) {
				ihm.getButonPlateau(me.getKey()).setBackground(Color.BLACK);
			}


			if(me.getValue().getCase().isActivated()) {


				if(me.getValue().getStatut()==1){
					ihm.getButonPlateau(me.getKey()).setFond(Color.cyan);
				}else if(me.getValue().getStatut()>1){
					ihm.getButonPlateau(me.getKey()).setFond(Color.BLUE);
				}else if(me.getValue().getStatut()==0){
					ihm.getButonPlateau(me.getKey()).setFond(new Color(204, 102, 0));
				}

			}

			
		}
		ihm.updateGrille();
	}

	public void ensabler() {
		// TODO - implement VueGrille.ensabler
		throw new UnsupportedOperationException();
	}

	public Controleur getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controleur ctrl) {
		this.ctrl = ctrl;

	}

	public IHMV2 getIhm() {
		return ihm;
	}

	public void setIhm(IHMV2 ihm2) {
		this.ihm = ihm2;
	}



}