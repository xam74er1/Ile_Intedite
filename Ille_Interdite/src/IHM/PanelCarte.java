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

import Carte.Carte;
import Carte.CarteHelicoptere;
import Carte.CarteSacSable;
import Carte.CarteTresor;
import Carte.Classique;
import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.Aventurie.Aventurier;
//panel permettant d'afficher une carte
public class PanelCarte extends JPanel{

	private int numCarte,numJoueur = -1;
	private Observe o;
	private boolean activated = false;
	private MouseListener m;
	private Color c = new Color(204, 102, 0);
	String path=" ";
	private Carte carte;
	private boolean blanc = false;
	private JPanel me ;
	private boolean classique;


	public PanelCarte(int num, Observe o,boolean classique) {

		this.numCarte = num;
		this.o = o;
		this.classique=classique;


		m=mouse();		
		setLayout(new BorderLayout(0, 0));
		me = this;


	}

	public void paintComponent(Graphics g){

		BufferedImage image;

		g.setColor(c);

		g.fillRect(0, 0,  this.getWidth(),this.getHeight());

		g.setColor(Color.black);
		g.drawRect(0, 0,  this.getWidth(),this.getHeight());

		try {
			if(classique) {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\cartes\\"+carte.getNom().substring(1)+".png";
			}else {
				String name = carte.getNom();

				name=	name.replace(" d", "D");
				name=	name.replace(" ", "");
				name = name.replace("�", "");
				name = name.replace("'", "");

				path = System.getProperty("user.dir")+"\\src\\"+"images\\cartes\\"+name+".png";
				//Pour que lon puisse clique sur les carte 
				if(activated && isEnabled()) {

					this.addMouseListener(m);
				}

			}
		}catch(Exception e){
			if (classique) {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\cartes\\Fond rouge.png";
			}else {

				path = System.getProperty("user.dir")+"\\src\\"+"images\\cartes\\Fond bleu.png";
			}

			this.removeMouseListener(m);
		}

		try {		
			image = ImageIO.read(new File(path));
			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {

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
				if(activated) {
					blanc = false;
					me.repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(activated) {
					blanc = true;
					me.repaint();
				}

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


				if(activated) {
					Message m;
					if (carte instanceof CarteHelicoptere) {
						m = new Message(TypeMessage.Clique_Deplace_Helico);
					}else if (carte instanceof CarteSacSable) {
						m = new Message(TypeMessage.Clique_Asseche_SacDeSable);
					}else if (carte instanceof CarteTresor){
						m = new Message(TypeMessage.Clique_Carte_Tresor);				
					}else {
						m=new Message(TypeMessage.Clique_Ok);
					}

					m.setNumJoueur(numJoueur);
					m.setNum(numCarte);
					m.setCarte(carte);
					o.notifierObservateur(m);
				}else {
					me.removeMouseListener(m);
				}
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

	public void setCarte(Carte c2) {
		carte=c2;
		if(carte == null) {
			desactivate();
		}else {
			activate();
		}
	}

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {

		this.numJoueur = numJoueur;
	}



}
