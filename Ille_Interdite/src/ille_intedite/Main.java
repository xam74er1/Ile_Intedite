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
        IHM ihm = new IHM();
        Controleur ctrl = new Controleur(ihm);
//        ctrl.creeDeckInondation(); Debug en cours
        
        ihm.addObservateur(ctrl);
        
   }    
}
