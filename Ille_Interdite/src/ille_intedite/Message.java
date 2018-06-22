/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ille_intedite;

import Carte.Carte;
import Carte.Classique;

/**
 *
 * @author clercma
 */
public class Message {
    TypeMessage message;
    String location;
    String text;
    int num = -1,numJoueur = -1;
    Carte carte;
    
   

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Message(TypeMessage message) {

		this.message = message;
	}

	public TypeMessage getMessage() {
		return message;
	}

	public String getLocation() {
		return location;
	}

	public void setMessage(TypeMessage message) {
		this.message = message;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
	
	
    
    
    //test
	
}
