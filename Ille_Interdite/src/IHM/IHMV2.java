package IHM;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Carte.CarteHelicoptere;
import Carte.CarteSacSable;
import Carte.CarteTresor;
import Carte.Classique;
import Carte.NomTresor;
import ille_intedite.Grille;
import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.VueGrille;
import ille_intedite.Aventurie.Aventurier;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class IHMV2 extends Observe{
	//Com ref 2
	private JFrame frame;
	private JTextArea console;
	private JPanel Plateau ;
	private JLabel lblJoeurN;
	private JLabel textCusor;



	HashMap<String,JButton> listButton = new HashMap();
	HashMap<String,JPanel> listPan = new HashMap();
	HashMap<Integer,PanelCarte> listCartes = new HashMap<Integer,PanelCarte>();
	private JTextField textField;

	VueGrille vue;

	private JButton btnSacSable;
	private JButton btnHelico;
	private ImageIcon imgPlayerIn1T,imgPlayerIn2T,imgPlayerIn3T,imgP1T,imgP2T,imgP3T;
	JPanel PanelPlayerIn3T,PanelPlayerIn2T,PanelPlayerIn1T;
	Image imP3T,imP2T,imP1T;
	JLabel lbIconJoueur;
	JButton btnImgPlayerIn1T,btnImgPlayerIn2T,btnImgPlayerIn3T;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @param vue 
	 */
	public IHMV2(VueGrille vue) {
		this.vue = vue;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);

		JPanel SliderPanel = new JPanel();
		SliderPanel.setBackground(new Color(0, 0, 128));
		SliderPanel.setBounds(0, 0, 100, 673);
		frame.getContentPane().add(SliderPanel);
		SliderPanel.setLayout(null);

		JSlider slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setValue(5);
		slider.setPaintTicks(true);
		slider.setBackground(new Color(0, 0, 205));
		slider.setMaximum(10);
		slider.setBounds(12, 86, 76, 574);
		SliderPanel.add(slider);
		slider.setOrientation(SwingConstants.VERTICAL);
		
		JPanel PanelHelp = new JPanel();
		PanelHelp.setBounds(20, 13, 60, 60);
		SliderPanel.add(PanelHelp);
		PanelHelp.setLayout(null);
		
		JButton btnHelp = new JButton("");
		btnHelp.setBounds(0, 0, 60, 60);
		btnHelp.setForeground(new Color(0, 0, 205));
		btnHelp.setBackground(new Color(0, 0, 205));
		ImageIcon imgHelp = new ImageIcon("images/icones/iconHelp.png");
		Image imH = imgHelp.getImage();
		imH = imH.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon imgH = new ImageIcon(imH);
		btnHelp.setIcon(imgH);
		PanelHelp.add(btnHelp);
		

		JPanel CardPlayer = new JPanel();
		CardPlayer.setBackground(new Color(139, 69, 19));
		CardPlayer.setBounds(112, 13, 250, 597);
		frame.getContentPane().add(CardPlayer);
		CardPlayer.setLayout(null);
		
		PanelCarte panelCarte1 = new PanelCarte(0,this);
		panelCarte1.setBounds(12, 13, 100, 140);
		CardPlayer.add(panelCarte1);
		listCartes.put(0, panelCarte1);
		panelCarte1.setLayout(null);

		PanelCarte panelCarte2 = new PanelCarte(1,this);
		panelCarte2.setBounds(138, 13, 100, 140);
		CardPlayer.add(panelCarte2);
		listCartes.put(1, panelCarte2);
		panelCarte2.setLayout(null);

		PanelCarte panelCarte3 = new PanelCarte(2,this);
		panelCarte3.setBounds(12, 166, 100, 140);
		CardPlayer.add(panelCarte3);
		listCartes.put(2, panelCarte3);
		panelCarte3.setLayout(null);

		PanelCarte panelCarte4 = new PanelCarte(3,this);
		panelCarte4.setBounds(138, 166, 100, 140);
		CardPlayer.add(panelCarte4);
		listCartes.put(3, panelCarte4);
		panelCarte4.setLayout(null);

		PanelCarte panelCarte5 = new PanelCarte(4,this);
		panelCarte5.setBounds(75, 319, 100, 140);
		CardPlayer.add(panelCarte5);
		listCartes.put(4, panelCarte5);
		panelCarte5.setLayout(null);
		
		lbIconJoueur = new JLabel("");

		lbIconJoueur.setBounds(75, 472, 100, 100);
		CardPlayer.add(lbIconJoueur);
		ImageIcon imgIconJoueur = new ImageIcon("images/persos/explorateur.png");
		Image imPC = imgIconJoueur.getImage();
		imPC = imPC.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon imgPC = new ImageIcon(imPC);
		lbIconJoueur.setIcon(imgPC);

		JPanel PanelSouth = new JPanel();
		PanelSouth.setBackground(new Color(139, 69, 19));
		PanelSouth.setBounds(97, 623, 1165, 50);
		frame.getContentPane().add(PanelSouth);
		PanelSouth.setLayout(null);

		JButton Deplace = new JButton("Deplacer");
		Deplace.setBounds(12, 13, 97, 25);
		PanelSouth.add(Deplace);
		Deplace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Deplace);

				;

				notifierObservateur(m);
			}
		});

		JButton btAssecher = new JButton("Assecher");
		btAssecher.setBounds(121, 13, 97, 25);

		btAssecher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Asseche);

				;

				notifierObservateur(m);
			}
		});
		PanelSouth.add(btAssecher);

		JButton btDonneCarte = new JButton("Donne carte");
		btDonneCarte.setBounds(230, 13, 97, 25);
		btDonneCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_DonneCarte);

				;

				notifierObservateur(m);
			}
		});
		PanelSouth.add(btDonneCarte);

		JButton btRecupereTresort = new JButton("Recupere Tresrot");
		btRecupereTresort.setBounds(339, 13, 130, 25);
		btRecupereTresort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_RecupereTresor);

				;

				notifierObservateur(m);
			}
		});
		PanelSouth.add(btRecupereTresort);

		JButton btFinDeTour = new JButton("Fin de tour");
		btFinDeTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Fin_Tour);

				;

				notifierObservateur(m);
			}
		});
		btFinDeTour.setBounds(1056, 13, 97, 25);
		PanelSouth.add(btFinDeTour);

		JPanel panActionSpetiale = new JPanel();
		panActionSpetiale.setBounds(501, 13, 218, 25);
		PanelSouth.add(panActionSpetiale);
		panActionSpetiale.setLayout(new GridLayout(0, 2, 0, 0));

		btnSacSable = new JButton("Sac de Sable");
		panActionSpetiale.add(btnSacSable);
		btnSacSable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Asseche_SacDeSable);
				notifierObservateur(m);
			}
		});

		btnHelico = new JButton("Helicoptere");
		panActionSpetiale.add(btnHelico);
		btnHelico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Deplace_Helico);

				notifierObservateur(m);
			}
		});



		//PLATEAU ---------------------------
		Plateau = new JPanel();
		Plateau.setBackground(new Color(160, 82, 45));
		Plateau.setBounds(374, 13, 597, 597);
		Plateau .setLayout(new GridLayout(6,6,0,0));
		frame.getContentPane().add(Plateau);
		//--------------------------------

		JPanel PanelEast = new JPanel();
		PanelEast.setBackground(new Color(139, 69, 19));
		PanelEast.setBounds(983, 13, 279, 597);
		frame.getContentPane().add(PanelEast);
		PanelEast.setLayout(null);

		PanelPlayerIn2T = new JPanel();
		PanelPlayerIn2T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn2T.setBounds(59, 264, 150, 150);
		PanelEast.add(PanelPlayerIn2T);
		PanelPlayerIn2T.setLayout(null);

		btnImgPlayerIn2T = new JButton("");
		btnImgPlayerIn2T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBounds(0, 0, 150, 150);
		PanelPlayerIn2T.add(btnImgPlayerIn2T);


		imgPlayerIn2T = new ImageIcon("images/persos/ingenieur.png");
		imP2T = imgPlayerIn2T.getImage();
		imP2T = imP2T.getScaledInstance(120, 150, Image.SCALE_DEFAULT);
		imgP2T = new ImageIcon(imP2T);

		btnImgPlayerIn2T.setIcon(imgP2T);
		btnImgPlayerIn2T.setBorder(null);


		PanelPlayerIn3T = new JPanel();
		PanelPlayerIn3T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn3T.setBounds(59, 95, 150, 150);
		PanelEast.add(PanelPlayerIn3T);
		PanelPlayerIn3T.setLayout(null);

		btnImgPlayerIn3T = new JButton("");

		btnImgPlayerIn3T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setBounds(0, 0, 150, 150);
		PanelPlayerIn3T.add(btnImgPlayerIn3T);
		imgPlayerIn3T = new ImageIcon("images/persos/pilote.png");
		imP3T = imgPlayerIn3T.getImage();
		imP3T = imP3T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		imgP3T = new ImageIcon(imP3T);
		btnImgPlayerIn3T.setIcon(imgP3T);
		btnImgPlayerIn3T.setBorder(null);

		PanelPlayerIn1T = new JPanel();
		PanelPlayerIn1T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn1T.setBounds(59, 434, 150, 150);
		PanelEast.add(PanelPlayerIn1T);
		PanelPlayerIn1T.setLayout(null);

		btnImgPlayerIn1T = new JButton("");
		btnImgPlayerIn1T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBounds(0, 0, 150, 150);
		PanelPlayerIn1T.add(btnImgPlayerIn1T);
		imgPlayerIn1T = new ImageIcon("images/persos/plongeur.png");
		imP1T = imgPlayerIn1T.getImage();
		imP1T = imP1T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		imgP1T = new ImageIcon(imP1T);
		btnImgPlayerIn1T.setIcon(imgP1T);
		btnImgPlayerIn1T.setBorder(null);


		//-----------------------HELP -----------------
//		JPanel PanelHelp = new JPanel();
//		PanelHelp.setBounds(207, 0, 60, 60);
//		PanelEast.add(PanelHelp);
//		PanelHelp.setLayout(null);
//
//		JButton btnHelp = new JButton("");
//		btnHelp.setForeground(new Color(139, 69, 19));
//		btnHelp.setBackground(new Color(139, 69, 19));
//		btnHelp.setBounds(0, 0, 60, 60);
//		PanelHelp.add(btnHelp);
//		ImageIcon imgHelp = new ImageIcon("images/icones/iconHelp.png");
//		Image imH = imgHelp.getImage();
//		imH = imH.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
//		ImageIcon imgH = new ImageIcon(imH);
//		btnHelp.setIcon(imgH);




		
		JPanel PanelTresor = new JPanel();
		PanelTresor.setBackground(new Color(165, 42, 42));
		PanelTresor.setBounds(0, 0, 279, 84);
		PanelEast.add(PanelTresor);
		PanelTresor.setLayout(null);
		
		JLabel lbImgTresor1 = new JLabel("");
		lbImgTresor1.setBounds(0, 0, 60, 82);
		PanelTresor.add(lbImgTresor1);
		ImageIcon ImgTresor1 = new ImageIcon("images/tresors/calice.png");
		Image imT1 = ImgTresor1.getImage();
		imT1 = imT1.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT1 = new ImageIcon(imT1);
		lbImgTresor1.setIcon(imgT1);
		
		JLabel lbImgTresor2 = new JLabel("");
		lbImgTresor2.setBounds(72, 0, 60, 82);
		PanelTresor.add(lbImgTresor2);
		ImageIcon ImgTresor2 = new ImageIcon("images/tresors/cristal.png");
		Image imT2 = ImgTresor2.getImage();
		imT2 = imT2.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT2 = new ImageIcon(imT2);
		lbImgTresor2.setIcon(imgT2);
		
		JLabel lbImgTresor3 = new JLabel("");
		lbImgTresor3.setBounds(144, 0, 60, 82);
		PanelTresor.add(lbImgTresor3);
		ImageIcon ImgTresor3 = new ImageIcon("images/tresors/pierre.png");
		Image imT3 = ImgTresor3.getImage();
		imT3 = imT3.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT3 = new ImageIcon(imT3);
		lbImgTresor3.setIcon(imgT3);
		
		JLabel lbImgTresor4 = new JLabel("");
		lbImgTresor4.setBounds(219, 0, 60, 82);
		PanelTresor.add(lbImgTresor4);
		ImageIcon ImgTresor4 = new ImageIcon("images/tresors/zephyr.png");
		Image imT4 = ImgTresor4.getImage();
		imT4 = imT4.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT4 = new ImageIcon(imT4);
		lbImgTresor4.setIcon(imgT4);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(0, 84, 279, 10);
		PanelEast.add(panel);
		frame.setBounds(100, 100, 1280, 720);

		//----------------
		//PROVISOIRE 
		//--------------

		lblJoeurN = new JLabel();

		System.out.println("Fin IHM generation");
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void fillPlataux2(Grille g){

		CasePlateau c;
		HashMap<String, Tuile> tuilesListe = g.getTuilesListe();

		//int i = 0;

		Iterator<Tuile> it = g.getTuilesListe().values().iterator();
		int k = 0;
		String str;
		for(int y =0;y<6;y++){
			for(int x =0;x<6 ;x++){


				str = g.getTuile(x, y).getNom();
				c= new CasePlateau(str,x+":"+y,this, g.getTuile(x, y));
				if(k%2==0) {
					c.setBackground(Color.red);
				}else {
					c.setBackground(Color.BLUE);
				}

				if( g.getTuile(x, y).getNum()==-1) {
					c.desactivate();
				}

				listPan.put(x+":"+y, c);

				Plateau.add(c); 
				k++;
			}
		}

		Plateau.revalidate();
		//Fin fill plateau

	}


	public void afficherDep(ArrayList<Tuile> listDep) {

		for(Tuile t : Grille.tuilesListe.values()) {
			if (!listDep.contains(t)) {
				t.getCase().setBlanc();
			}else {
				t.getCase().removeBlanc();
			}


		}

	}

	public void afficherDepUrg(ArrayList<Tuile> listDep) {
		for(Tuile t : Grille.tuilesListe.values()) {
			if (!listDep.contains(t)) {
				t.getCase().setBlanc();
			}else {
				t.getCase().removeBlanc();
			}


		}
	}

	//effece les message et ajoute un message 
	public void afichierConsole(String str){
		//console.setText(str);
	}

	//Ajoute une message en plus des message aficher
	public void addConsole(String str) {
		//console.setText(console.getText()+"\n"+str);
	}

	public void print(String str){
		//console.setText(str);
	}

	public  HashMap<String, JButton> getListButton() {
		return listButton;
	}

	//	public JButton getButonPlateau(String str){
	//		return listButton.get(str);
	//	}

	public CasePlateau getButonPlateau(String str){
		return (CasePlateau) listPan.get(str);
	}


	public void miseAJourPlayer(int x,String str ,Color c) {
		lblJoeurN.setForeground(c);
		lblJoeurN.setText("Joueur n° "+(x+1)+str);
	}

	public void updateGrille() {
		Plateau.repaint();
		//Plateau.revalidate();
	}

	public void setLevelCursort(int i) {
		//		 textCusor.setText("Niveau de l'eau "+i);
	}

	public void activateSpecialButton(boolean he, boolean sa) {
		btnHelico.setEnabled(he);
		btnSacSable.setEnabled(sa);
	}

	public void setCartePanel(int num, Classique c) {
		PanelCarte p = listCartes.get(num);
		p.setCarte(c);
		p.repaint();
		p.revalidate();
	}
	
	public void rool(int joeurAsJouer , ArrayList<Aventurier> listAvent) {
		int i = 1;
		Aventurier a = listAvent.get(joeurAsJouer);

		String path = a.getNom().toLowerCase();
		System.out.println(path);
		i++;
//		Icon img = new ImageIcon("images/persos/"+path+".png");
//		lbIconJoueur.setIcon(img);
		
		
		
		ImageIcon imgIconJoueur = new ImageIcon("images/persos/"+path+".png");
		Image imPC = imgIconJoueur.getImage();
		imPC = imPC.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon imgPC = new ImageIcon(imPC);
		lbIconJoueur.setIcon(imgPC);
		//images/persos/explorateur.png

		int k = joeurAsJouer;
		
		k = (k+1)%listAvent.size();
		a = listAvent.get(k);
		path = a.getNom().toLowerCase();

		i++;

		imgPlayerIn1T = new ImageIcon("images/persos/"+path+".png");
		imP1T = imgPlayerIn1T.getImage();
		imP1T = imP1T.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
		imgP1T = new ImageIcon(imP1T);
		btnImgPlayerIn1T.setIcon(imgP1T);
		PanelPlayerIn1T.repaint();
		
		if(i<=listAvent.size()) {
			
			k = (k+1)%listAvent.size();
			a = listAvent.get(k);
			path = a.getNom().toLowerCase();
			i++;
			
			imgPlayerIn2T = new ImageIcon("images/persos/"+path+".png");
			imP2T = imgPlayerIn2T.getImage();
			imP2T = imP2T.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
			imgP2T = new ImageIcon(imP2T);
			btnImgPlayerIn2T.setIcon(imgP2T);
			PanelPlayerIn2T.repaint();
		}
		
		if(i<=listAvent.size()) {
			
			k = (k+1)%listAvent.size();
			a = listAvent.get(k);
			path = a.getNom().toLowerCase();
			i++;
			

			imgPlayerIn3T = new ImageIcon("images/persos/"+path+".png");
			imP3T = imgPlayerIn3T.getImage();
			imP3T = imP3T.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
			imgP3T = new ImageIcon(imP3T);
			btnImgPlayerIn3T.setIcon(imgP3T);
			PanelPlayerIn3T.repaint();
		}
		
		
	}
}
