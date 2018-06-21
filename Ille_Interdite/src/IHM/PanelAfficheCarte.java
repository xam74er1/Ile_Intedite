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
	private PanelAfficheCarte me;

	public PanelAfficheCarte(Observe o, JFrame frame) {
		super();
		this.o=o;
		this.frame=frame;
		this.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		listCarte=new ArrayList<Carte>();
		this.setBackground(new Color(0,0,0));
		me=this;
		this.setVisible(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(0,0,0,128));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void setListCarte(ArrayList<Carte> listCarte) {
		me.listCarte = listCarte;
		me.setLayout(new GridLayout(4, 10,10,10));
		for(int i=0;i<10;i++) {
			me.add(new PanelNoir());
		}
		for (int i=0;i<me.listCarte.size();i++) {
			if(i%10==0) me.add(new PanelNoir());
			Carte c = me.listCarte.get(i);
			PanelCarte pane = new PanelCarte(i, o, c instanceof Classique);
			me.add(pane);
			pane.setCarte(c);
			pane.setBounds(0, 0,80, 112);
			if(i%10==9) me.add(new PanelNoir());
		}
		for (int i=listCarte.size();i<30-(2*(listCarte.size()/10+1));i++) {
			me.add(new PanelNoir());
		}
		me.add(new PanelNoir());
	}

	
	
}
