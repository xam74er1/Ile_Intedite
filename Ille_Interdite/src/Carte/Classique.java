package Carte;

import IHM.PanelCarte;

public abstract class Classique extends Carte {
	
	private PanelCarte panel;

    public Classique(String nom) {
        super(nom);
    }
    
    public void setPanel(PanelCarte panel) {
    	this.panel=panel;
    }
}