package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Carte.CarteHelicoptere;
import Carte.CarteSacSable;
import Carte.CarteTresor;
import Carte.Classique;
import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.Aventurie.Aventurier;

public class PanelCarte extends JPanel{

	private int num;
	private Observe o;
	private boolean activated = false;
	private MouseListener m;
	private Color c = new Color(204, 102, 0);
	String path=" ";
	private Classique carte;
	private boolean blanc = false;
	private JPanel moi ;

	public PanelCarte(int num, Observe o) {

		this.num = num;
		this.o = o;


		m=mouse();		
		setLayout(new BorderLayout(0, 0));
		moi = this;
	

	}

	public void paintComponent(Graphics g){

		BufferedImage image;

		g.setColor(c);

		g.fillRect(0, 0,  this.getWidth(),this.getHeight());

		g.setColor(Color.black);
		g.drawRect(0, 0,  this.getWidth(),this.getHeight());

		try {
			path = "images\\cartes\\"+carte.getNom().substring(1)+".png";
		}catch(Exception e){
			path = "images\\cartes\\Fond rouge.png";
		}

		try {			image = ImageIO.read(new File(path));
			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(blanc) {
			g.setColor(new Color(255,255,255,128));

			g.fillRect(0, 0,  this.getWidth(),this.getHeight());
		}
	}

	private MouseListener mouse() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				blanc = false;
				moi.repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				blanc = true;
				moi.repaint();

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(" clique carte");
				Message m;
				if (carte instanceof CarteHelicoptere) {
					m = new Message(TypeMessage.Clique_Deplace_Helico);
				}else if (carte instanceof CarteSacSable) {
					m = new Message(TypeMessage.Clique_Asseche_SacDeSable);
				}else {
					m = new Message(TypeMessage.Clique_Carte_Tresor);				
				}
				m.setNum(num);
				m.setCarte(carte);
				o.notifierObservateur(m);
			}
		};

	}

	public void desactivate() {
		this.removeMouseListener(m);
		activated = false;
	}

	public void activate() {
		this.addMouseListener(m);
		activated = true;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setCarte(Classique c) {
		carte=c;
		if(carte == null) {
			desactivate();
		}else {
			activate();
		}
	}

}
