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

	public PanelAfficheCarte(Observe o, JFrame frame) {
		super();
		this.o=o;
		this.frame=frame;
		this.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		listCarte=new ArrayList();
		this.setBackground(new Color(0,0,0,128));
	}
	
	@Override
	public void paintComponent(Graphics g){

	}

	public void setListCarte(ArrayList<Carte> listCarte) {
		this.listCarte = listCarte;
		this.setLayout(new GridLayout((this.listCarte.size()/16)+1, 7*(this.listCarte.size()%16),0,0));
		for (int i=0;i<this.listCarte.size();i++) {
			if(i%16==0) this.add(new JPanel());
			Carte c = this.listCarte.get(i);
			PanelCarte pane = new PanelCarte(i, o, c instanceof Classique);
			this.add(pane);
			pane.setCarte(c);
			pane.setBounds(0, 0,80, 112);
			if(i%16==15) this.add(new JPanel());
		}
		this.setVisible(true);
	}

	
	
}
