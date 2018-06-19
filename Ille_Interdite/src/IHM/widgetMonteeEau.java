 //* To change this template file, choose Tools | Templates
 //* and open the template in the editor.
 //*/
package IHM;

import ille_intedite.Curseur;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author fontaipi
 */
public class widgetMonteeEau extends JPanel{
    public int niveauEau;
    public int niveauDifficulte;
    
    
    public widgetMonteeEau() {
        niveauEau = Curseur.getNiveau();
        niveauDifficulte = Curseur.getNbCartesInond();
    }
    
    public void paintComponent(Graphics g){
        int x = getWidth();
        int y = getHeight();
        g.setColor(Color.red);
        g.drawRect(0, (y*0.1), (y*0.2), (x*0.7));
    }
}