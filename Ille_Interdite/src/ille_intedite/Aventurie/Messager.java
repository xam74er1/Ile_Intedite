package ille_intedite.Aventurie;

import ille_intedite.Controleur;
import ille_intedite.Aventurie.Aventurier;

import java.util.ArrayList;

import Carte.NomTresor;
import utils.Utils;

public class Messager extends Aventurier {

    public Messager(int Num, String nom, Utils.Pion pion) {
        super(Num, nom,pion);
    }
    
    //le messager peut donner une carte a n'importe qui
    @Override
    public ArrayList<Aventurier> getJoueurTuile(){
		return Controleur.joueursList;
	}

    
}