/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ille_intedite;

import Carte.Classique;

/**
 *
 * @author clercma
 */
public class Message {
    TypeMessage message;
    String location;
    String text;
    int num;
    Classique carte;
    
   

	public Classique getCarte() {
		return carte;
	}

	public void setCarte(Classique carte) {
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
	
	
    
    
    //test
	
}
