package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Carte.Carte;
import Carte.Classique;
import ille_intedite.Observe;

public class PanelAfficheCarte extends JPanel {

	private Observe o;
	private ArrayList<Carte> listCarte;
	private JFrame frame;
	private PanelOk pok;
	private boolean ok;

	public PanelAfficheCarte(Observe o, JFrame frame) {
		super();
		this.o=o;
		this.frame=frame;
		this.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		listCarte=new ArrayList<Carte>();
		this.setBackground(new Color(0,0,0,128));
		this.setVisible(false);
	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(0,0,0,128));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void setListCarte(ArrayList<Carte> listCarte) {
		this.listCarte = new ArrayList<Carte>();
		this.listCarte = listCarte;
		this.setLayout(new GridLayout(4, 10,10,10));
		for(int i=0;i<10;i++) {
			this.add(new PanelNoir());
		}
		this.add(new PanelNoir());
		for (int i=0;i<this.listCarte.size();i++) {
			if(i%10==9) {
				this.add(new PanelNoir());
				this.add(new PanelNoir());
			}

			Carte c = this.listCarte.get(i);
			PanelCarte pane = new PanelCarte(i, o, c instanceof Classique);
			this.add(pane);
			pane.setCarte(c);
			if(ok) {
				pane.desactivate();
			}
			pane.setBounds(0, 0,80, 112);


		}
		for (int i=listCarte.size();i<29-(2*(listCarte.size()/8+1));i++) {
			this.add(new PanelNoir());
		}
		PanelNoir pane = new PanelNoir();
		pane.setLayout(new GridLayout(4,1));
		for (int i=0;i<3;i++) {
			pane.add(new PanelNoir());
		}
		pok = new PanelOk(o);
		if(!ok) pok.desactivate();
		pane.add(pok);
		this.add(pane);
		this.add(new PanelNoir());
	}
	
	public void setOk(boolean ok) {
		this.ok=ok;
	}

}
