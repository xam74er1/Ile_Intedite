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
	private int niv = 1;

	public PanelCurseur (Observe o) {
		super();
		this.o=o;
	}
	
	public void paintComponent(Graphics g){
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(new Color(0,0,128));
		if(niv<10) {
			g.fillRect(0, 620-(niv*58), 100,620);
		}else {
			g.fillRect(0, 0, 100, 640);
		}
		
		BufferedImage image;
		
		try {

			image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\"+"images\\Art\\curseur.png"));

			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setNiv(int niv) {
		this.niv = niv;
	}
	
}
