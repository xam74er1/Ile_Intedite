/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ille_intedite;

import IHM.IHM;
import IHM.IHMV2;

/**
 *
 * @author clercma
 */
public class Main {
    public static void main(String [] args) {
    	VueGrille vue = new VueGrille();
        IHMV2 ihm = new IHMV2(vue);
        Controleur ctrl = new Controleur(ihm,vue);
//        ctrl.creeDeckInondation(); Debug en cours
        
        vue.setCtrl(ctrl);
        vue.setIhm(ihm);
        
        ihm.addObservateur(ctrl);
        
        vue.afficherGrille();
        
       ctrl.piocher5Inondation();
       //Comentaire de modification 
        
   }    
}
