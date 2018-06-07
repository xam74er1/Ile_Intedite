/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ille_intedite;

import IHM.IHM;

/**
 *
 * @author clercma
 */
public class Main {
    public static void main(String [] args) {
    	VueGrille vue = new VueGrille();
        IHM ihm = new IHM(vue);
        Controleur ctrl = new Controleur(ihm,vue);
//        ctrl.creeDeckInondation(); Debug en cours
        
        vue.setCtrl(ctrl);
        vue.setIhm(ihm);
        
        ihm.addObservateur(ctrl);
        
        vue.afficherGrille();
        
   }    
}
