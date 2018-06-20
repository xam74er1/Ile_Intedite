package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ille_intedite.Observe;

public class PanelCurseur extends JPanel {
	
	private Observe o;
	private HashMap<Integer,JPanel> listCurseur = new HashMap<Integer,JPanel>();
	private int niv = 0;
	
	public PanelCurseur(Observe o) {

		this.o = o;
		this.setLayout(new GridLayout(11,1));
		for (int i=0;i<11;i++) {
			listCurseur.put(i,new JPanel());
		}
		

	}

	public void paintComponent(Graphics g){
		
		for (int i=0;i<11;i++) {
			JPanel p = listCurseur.get(i);
			if (i<niv) {
				p.setBackground(new Color(255,255,255));
			}else {
				p.setBackground(new Color(0,0,128));
			}
		}

		BufferedImage image;

		g.setColor(Color.black);
		g.drawRect(0, 0,  this.getWidth(),this.getHeight());
		
		try {
			image=ImageIO.read(new File("images\\curseur.png"));
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("marche pas");
			e.printStackTrace();
		}

	}
	
	public void setNiv(int niv) {
		this.niv = niv;
		if (niv==9) niv=10;
	}
	
}
