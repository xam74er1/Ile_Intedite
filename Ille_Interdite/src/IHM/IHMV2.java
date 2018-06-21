package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Carte.Classique;
import Carte.NomTresor;
import ille_intedite.Grille;
import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.VueGrille;
import ille_intedite.Aventurie.Aventurier;
import java.awt.BorderLayout;

public class IHMV2 extends Observe{
	public JFrame frame;
	private JTextArea console;
	private JPanel Plateau ;
	private JLabel lblJoeurN;
	private JPanel panHelp;
	private JLabel textCusor,msgHelp;
	JPanel sliderPanel;
	private PanelCurseur sliderImg;
	JLabel lbImgTresor1 = new JLabel("");
	JLabel lbImgTresor2 = new JLabel("");
	JLabel lbImgTresor3 = new JLabel("");
	JLabel lbImgTresor4 = new JLabel("");

	HashMap<String,PanelButton> listButton = new HashMap();
	HashMap<String,JPanel> listPan = new HashMap();
	HashMap<Integer,PanelCarte> listCartes = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes3T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes2T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes1T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer,JPanel> listCurseur = new HashMap<Integer,JPanel>();
	private JTextField textField;
	JPanel PanelHelp;
	JPanel CardPlayer;
	JPanel PanelSouth;
	JPanel PanelEast;
	JPanel PanelTresor;

	VueGrille vue;

	PanelAfficheCarte panelDefausse;
	PanelAfficheCarte panelCarteJoueur;
	PanelAfficheCarte panelCarteInondations;
	PanelAfficheCarte panelCartePiochee;


	private ImageIcon imgPlayerIn1T,imgPlayerIn2T,imgPlayerIn3T,imgP1T,imgP2T,imgP3T;
	JPanel PanelPlayerIn3T,PanelPlayerIn2T,PanelPlayerIn1T;
	Image imP3T,imP2T,imP1T;
	JLabel lbIconJoueur;
	JButton btnImgPlayerIn1T,btnImgPlayerIn2T,btnImgPlayerIn3T;
	private JPanel panelCarte3T2;
	private JPanel panelCarte3T1;
	private JPanel panelCarte3T3;
	private JPanel panelCarte3T4;
	private JPanel panelCarte3T5;
	private JPanel panelCarte2T1;
	private JPanel panelCarte2T2;
	private JPanel panelCarte2T3;
	private JPanel panelCarte2T4;
	private JPanel panelCarte2T5;
	private JPanel panelCarte1T1;
	private JPanel panelCarte1T2;
	private JPanel panelCarte1T3;
	private JPanel panelCarte1T4;
	private JPanel panelCarte1T5;

	/**
	 * 
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

		panelDefausse=new PanelAfficheCarte(this, frame);

		panelDefausse.setBounds(0, 0, 1280, 680);
		frame.getContentPane().add(panelDefausse);
		
		panelCarteInondations=new PanelAfficheCarte(this, frame);
		panelCarteInondations.setBounds(0, 0, 1280, 680);
		frame.getContentPane().add(panelCarteInondations);
		
		panelCarteJoueur=new PanelAfficheCarte(this, frame);
		panelCarteJoueur.setBounds(0, 0, 1280, 680);
		frame.getContentPane().add(panelCarteJoueur);
		
		panelCartePiochee=new PanelAfficheCarte(this, frame);
		panelCartePiochee.setBounds(0, 0, 1280, 680);
		frame.getContentPane().add(panelCartePiochee);



		sliderImg = new PanelCurseur(this);
		sliderImg.setBounds(0, 59, 100, 685);
		frame.getContentPane().add(sliderImg);

		/*sliderPanel = new JPanel();
		sliderPanel.setBounds(0, 60, 100, 610);
		sliderPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(sliderPanel);
		sliderPanel.setLayout(new GridLayout(11,1));*/

		/*for (int i=0;i<11;i++) {
			JPanel panelCurseur = new JPanel();
			panelCurseur.setBackground(Color.WHITE);
			sliderPanel.add(panelCurseur);
			listCurseur.put(i,panelCurseur);
		}*/

		PanelHelp = new JPanel();
		PanelHelp.setBounds(0, 0, 100, 60);
		frame.getContentPane().add(PanelHelp);
		PanelHelp.setLayout(null);

		JButton btnHelp = new JButton("");
		btnHelp.setBounds(0, 0, 100, 60);
		btnHelp.setForeground(new Color(0, 0, 205));
		btnHelp.setBackground(new Color(0, 0, 205));

		btnHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new FrameHtml();
			}
			
		});
		//BOUTON HELP
		

		ImageIcon imgHelp = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/icones/iconHelp.png");

		Image imH = imgHelp.getImage();

		imH = imH.getScaledInstance(100, 60, Image.SCALE_DEFAULT);

		ImageIcon imgH = new ImageIcon(imH);
		btnHelp.setIcon(imgH);
		btnHelp.setBorder(null);
		PanelHelp.add(btnHelp);


		CardPlayer = new JPanel();
		CardPlayer.setBackground(new Color(139, 69, 19));
		CardPlayer.setBounds(114, 31, 250, 597);
		frame.getContentPane().add(CardPlayer);
		CardPlayer.setLayout(null);

		PanelCarte panelCarte1 = new PanelCarte(0,this,true);
		panelCarte1.setBounds(12, 13, 100, 140);
		CardPlayer.add(panelCarte1);
		listCartes.put(0, panelCarte1);
		panelCarte1.setLayout(null);

		PanelCarte panelCarte2 = new PanelCarte(1,this,true);
		panelCarte2.setBounds(138, 13, 100, 140);
		CardPlayer.add(panelCarte2);
		listCartes.put(1, panelCarte2);
		panelCarte2.setLayout(null);

		PanelCarte panelCarte3 = new PanelCarte(2,this,true);
		panelCarte3.setBounds(12, 166, 100, 140);
		CardPlayer.add(panelCarte3);
		listCartes.put(2, panelCarte3);
		panelCarte3.setLayout(null);

		PanelCarte panelCarte4 = new PanelCarte(3,this,true);
		panelCarte4.setBounds(138, 166, 100, 140);
		CardPlayer.add(panelCarte4);
		listCartes.put(3, panelCarte4);
		panelCarte4.setLayout(null);

		PanelCarte panelCarte5 = new PanelCarte(4,this,true);
		panelCarte5.setBounds(75, 319, 100, 140);
		CardPlayer.add(panelCarte5);
		listCartes.put(4, panelCarte5);
		panelCarte5.setLayout(null);

		lbIconJoueur = new JLabel("");

		lbIconJoueur.setBounds(75, 472, 100, 100);
		CardPlayer.add(lbIconJoueur);
		ImageIcon imgIconJoueur = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/explorateur.png");
		Image imPC = imgIconJoueur.getImage();
		imPC = imPC.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon imgPC = new ImageIcon(imPC);
		lbIconJoueur.setIcon(imgPC);

		PanelSouth = new JPanel();
		PanelSouth.setBackground(new Color(139, 69, 19));
		PanelSouth.setBounds(99, 641, 1165, 50);
		frame.getContentPane().add(PanelSouth);
		PanelSouth.setLayout(null);

		PanelButton Deplace = new PanelButton(this,"Deplacer",TypeMessage.Clique_Deplace);
		Deplace.setBounds(10, 5, 110, 34);
		PanelSouth.add(Deplace);

		PanelButton btAssecher = new PanelButton(this,"Assecher",TypeMessage.Clique_Asseche);
		btAssecher.setBounds(140, 5, 110, 34);
		PanelSouth.add(btAssecher);

		PanelButton btDonneCarte = new PanelButton(this,"DonnerCarte",TypeMessage.Clique_DonneCarte);
		btDonneCarte.setBounds(270, 5, 110, 34);
		PanelSouth.add(btDonneCarte);

		PanelButton btRecupereTresort = new PanelButton(this,"RecupererTresor",TypeMessage.Clique_RecupereTresor);
		btRecupereTresort.setBounds(454, 5, 156, 34);
		PanelSouth.add(btRecupereTresort);

		PanelButton btFinDeTour = new PanelButton(this,"FinDeTour",TypeMessage.Clique_Fin_Tour);
		btFinDeTour.setBounds(1030, 5, 110, 34);
		PanelSouth.add(btFinDeTour);


		//PLATEAU ---------------------------
		Plateau = new JPanel();
		Plateau.setBackground(new Color(160, 82, 45));
		Plateau.setBounds(376, 31, 597, 597);
		Plateau .setLayout(new GridLayout(6,6,0,0));
		frame.getContentPane().add(Plateau);
		//--------------------------------

		PanelEast = new JPanel();
		PanelEast.setBackground(new Color(139, 69, 19));
		PanelEast.setBounds(985, 31, 279, 597);
		frame.getContentPane().add(PanelEast);
		PanelEast.setLayout(null);
		//--------------------- 1 et principal -----------------------
		PanelPlayerIn1T = new JPanel();
		PanelPlayerIn1T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn1T.setBounds(23, 434, 150, 150);
		PanelEast.add(PanelPlayerIn1T);
		PanelPlayerIn1T.setLayout(null);

		btnImgPlayerIn1T = new JButton("");
		btnImgPlayerIn1T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBounds(0, 0, 150, 150);
		PanelPlayerIn1T.add(btnImgPlayerIn1T);
		imgPlayerIn1T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeur.png");
		imP1T = imgPlayerIn1T.getImage();
		imP1T = imP1T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		imgP1T = new ImageIcon(imP1T);
		btnImgPlayerIn1T.setIcon(imgP1T);
		btnImgPlayerIn1T.addActionListener(actionBoutonJoueur());
		btnImgPlayerIn1T.setBorder(null);
		//btnImgPlayerIn1T.setEnabled(false);
		//------------- 2 -----------------------

		PanelPlayerIn2T = new JPanel();
		PanelPlayerIn2T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn2T.setBounds(23, 264, 150, 150);
		PanelEast.add(PanelPlayerIn2T);
		PanelPlayerIn2T.setLayout(null);

		btnImgPlayerIn2T = new JButton("");
		btnImgPlayerIn2T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBounds(0, 0, 150, 150);
		btnImgPlayerIn2T.addActionListener(actionBoutonJoueur());
		PanelPlayerIn2T.add(btnImgPlayerIn2T);



		/*imgPlayerIn2T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/ingenieur.png");
		imP2T = imgPlayerIn2T.getImage();
		imP2T = imP2T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		imgP2T = new ImageIcon(imP2T);

		btnImgPlayerIn2T.setIcon(imgP2T);*/
		btnImgPlayerIn2T.setBorder(null);
		//btnImgPlayerIn2T.setEnabled(false);


		PanelPlayerIn3T = new JPanel();
		PanelPlayerIn3T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn3T.setBounds(23, 101, 150, 150);
		PanelEast.add(PanelPlayerIn3T);
		PanelPlayerIn3T.setLayout(null);

		btnImgPlayerIn3T = new JButton("");

		btnImgPlayerIn3T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setBounds(0, 0, 150, 150);
		PanelPlayerIn3T.add(btnImgPlayerIn3T);
		/*imgPlayerIn3T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/aviateur.png");
		imP3T = imgPlayerIn3T.getImage();
		imP3T = imP3T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		imgP3T = new ImageIcon(imP3T);
		btnImgPlayerIn3T.addActionListener(actionBoutonJoueur());
		btnImgPlayerIn3T.setIcon(imgP3T);*/
		btnImgPlayerIn3T.setBorder(null);
		//btnImgPlayerIn3T.setEnabled(false);



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
		//		ImageIcon imgHelp = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/icones/iconHelp.png");
		//		Image imH = imgHelp.getImage();
		//		imH = imH.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		//		ImageIcon imgH = new ImageIcon(imH);
		//		btnHelp.setIcon(imgH);





		PanelTresor = new JPanel();
		PanelTresor.setBackground(new Color(165, 42, 42));
		PanelTresor.setBounds(0, 0, 279, 84);
		PanelEast.add(PanelTresor);
		PanelTresor.setLayout(null);


		lbImgTresor1.setBounds(0, 0, 60, 82);
		PanelTresor.add(lbImgTresor1);
		ImageIcon ImgTresor1 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/tresors/calice.png");
		Image imT1 = ImgTresor1.getImage();
		imT1 = imT1.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT1 = new ImageIcon(imT1);
		lbImgTresor1.setIcon(imgT1);
		lbImgTresor1.setEnabled(false);


		lbImgTresor2.setBounds(72, 0, 60, 82);
		PanelTresor.add(lbImgTresor2);
		ImageIcon ImgTresor2 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/tresors/cristal.png");
		Image imT2 = ImgTresor2.getImage();
		imT2 = imT2.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT2 = new ImageIcon(imT2);
		lbImgTresor2.setIcon(imgT2);
		lbImgTresor2.setEnabled(false);


		lbImgTresor3.setBounds(144, 0, 60, 82);
		PanelTresor.add(lbImgTresor3);
		ImageIcon ImgTresor3 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/tresors/pierre.png");
		Image imT3 = ImgTresor3.getImage();
		imT3 = imT3.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT3 = new ImageIcon(imT3);
		lbImgTresor3.setIcon(imgT3);
		lbImgTresor3.setEnabled(false);


		lbImgTresor4.setBounds(219, 0, 60, 82);
		PanelTresor.add(lbImgTresor4);
		ImageIcon ImgTresor4 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/tresors/zephyr.png");
		Image imT4 = ImgTresor4.getImage();
		imT4 = imT4.getScaledInstance(60, 82, Image.SCALE_DEFAULT);
		ImageIcon imgT4 = new ImageIcon(imT4);
		lbImgTresor4.setIcon(imgT4);
		lbImgTresor4.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(0, 84, 279, 10);
		PanelEast.add(panel);
		
		
		panelCarte3T1 = new PanelCarte(0,this,true);
		panelCarte3T1.setBounds(185, 101, 25, 35);
		PanelEast.add(panelCarte3T1);
		listeCartes3T.put(0, (PanelCarte) panelCarte3T1);
		
		panelCarte3T2 = new PanelCarte(1,this,true);
		panelCarte3T2.setBounds(230, 101, 25, 35);
		PanelEast.add(panelCarte3T2);
		listeCartes3T.put(1, (PanelCarte) panelCarte3T2);
		
		panelCarte3T3 = new PanelCarte(2,this,true);
		panelCarte3T3.setBounds(185, 149, 25, 35);
		PanelEast.add(panelCarte3T3);
		listeCartes3T.put(2, (PanelCarte) panelCarte3T3);
		
		panelCarte3T4 = new PanelCarte(3,this,true);
		panelCarte3T4.setBounds(230, 149, 25, 35);
		PanelEast.add(panelCarte3T4);
		listeCartes3T.put(3, (PanelCarte) panelCarte3T4);
		
		panelCarte3T5 = new PanelCarte(4,this,true);
		panelCarte3T5.setBounds(208, 197, 25, 35);
		PanelEast.add(panelCarte3T5);
		listeCartes3T.put(4, (PanelCarte) panelCarte3T5);
		
		panelCarte2T1 = new PanelCarte(0,this,true);
		panelCarte2T1.setBounds(185, 264, 25, 35);
		PanelEast.add(panelCarte2T1);
		listeCartes2T.put(0, (PanelCarte) panelCarte3T4);
		
		panelCarte2T2 = new PanelCarte(1,this,true);
		panelCarte2T2.setBounds(230, 264, 25, 35);
		PanelEast.add(panelCarte2T2);
		listeCartes2T.put(1, (PanelCarte) panelCarte3T4);
		
		panelCarte2T3 = new PanelCarte(2,this,true);
		panelCarte2T3.setBounds(185, 312, 25, 35);
		PanelEast.add(panelCarte2T3);
		listeCartes2T.put(2, (PanelCarte) panelCarte3T4);
		
		panelCarte2T4 = new PanelCarte(3,this,true);
		panelCarte2T4.setBounds(230, 312, 25, 35);
		PanelEast.add(panelCarte2T4);
		listeCartes2T.put(3, (PanelCarte) panelCarte3T4);
		
		panelCarte2T5 = new PanelCarte(4,this,true);
		panelCarte2T5.setBounds(208, 360, 25, 35);
		PanelEast.add(panelCarte2T5);
		listeCartes2T.put(4, (PanelCarte) panelCarte3T4);
		
		panelCarte1T1 = new PanelCarte(0,this,true);
		panelCarte1T1.setBounds(185, 434, 25, 35);
		PanelEast.add(panelCarte1T1);
		listeCartes1T.put(0, (PanelCarte) panelCarte3T4);
		
		panelCarte1T2 = new PanelCarte(1,this,true);
		panelCarte1T2.setBounds(230, 434, 25, 35);
		PanelEast.add(panelCarte1T2);
		listeCartes1T.put(1, (PanelCarte) panelCarte3T4);
		
		panelCarte1T3 = new PanelCarte(2,this,true);
		panelCarte1T3.setBounds(185, 482, 25, 35);
		PanelEast.add(panelCarte1T3);
		listeCartes1T.put(2, (PanelCarte) panelCarte3T4);
		
		panelCarte1T4 = new PanelCarte(3,this,true);
		panelCarte1T4.setBounds(230, 482, 25, 35);
		PanelEast.add(panelCarte1T4);
		listeCartes1T.put(3, (PanelCarte) panelCarte3T4);
		
		panelCarte1T5 = new PanelCarte(4,this,true);
		panelCarte1T5.setBounds(208, 530, 25, 35);
		PanelEast.add(panelCarte1T5);
		listeCartes1T.put(4, (PanelCarte) panelCarte3T4);

		panHelp = new JPanel();
		panHelp.setBackground(new Color(144, 158, 181));
		panHelp.setBounds(114, 0, 1150, 32);
		frame.getContentPane().add(panHelp);
		panHelp.setLayout(new BorderLayout(0, 0));

		msgHelp = new JLabel("");
		msgHelp.setForeground(Color.red);
		msgHelp.setFont(new Font("Serif", Font.BOLD, 25));
		msgHelp.setHorizontalAlignment(JLabel.CENTER);
		msgHelp.setVerticalAlignment(JLabel.CENTER);

		panHelp.add(msgHelp, BorderLayout.CENTER);
		frame.setBounds(100, 100, 1280, 720);

		//----------------
		//PROVISOIRE 
		//--------------
		
		frame.getContentPane().add(panelDefausse);

		lblJoeurN = new JLabel();

		System.out.println("Fin IHM generation");
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setTresorEnabled(NomTresor nomTresor) {
		switch (nomTresor) {
		case CaliceOnde:lbImgTresor1.setEnabled(true);
			break;
		case CristalArdent:lbImgTresor2.setEnabled(true);
			break;
		case PierreSacree:lbImgTresor3.setEnabled(true);
			break;
		case StatueZephir:lbImgTresor4.setEnabled(true);
			break;
		}
	}

	public void fillPlataux2(Grille g){

		CasePlateau c;
		HashMap<String, Tuile> tuilesListe = Grille.tuilesListe;

		//int i = 0;

		Iterator<Tuile> it = Grille.tuilesListe.values().iterator();
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

	public  HashMap<String, PanelButton> getListButton() {
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


	public void setCartePanel(int num, Classique c) {
		PanelCarte p = listCartes.get(num);
		p.setCarte(c);
		p.repaint();
		p.revalidate();
	}


	public ActionListener actionBoutonJoueur() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bt = (JButton) e.getSource();

				if(bt.getName() != null) {
					Message m = new Message(TypeMessage.Clique_Joueur);

					m.setNum(Integer.parseInt(bt.getName()));

					notifierObservateur(m);
				}else {
					System.out.println("name is null"+(bt==null));
				}
			}
		};
	}

	public void rool(Aventurier a  , ArrayList<Aventurier> listAvent) {
		int i = 1;
		int k = listAvent.indexOf(a);

		//System.out.println(" principal = "+a.getNom()+" k"+k);
		String path = a.getNom().toLowerCase();

		i++;
		//		Icon img = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
		//		lbIconJoueur.setIcon(img);



		ImageIcon imgIconJoueur = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
		Image imPC = imgIconJoueur.getImage();
		imPC = imPC.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon imgPC = new ImageIcon(imPC);
		lbIconJoueur.setIcon(imgPC);
		//images/persos/explorateur.png


		k++;
		k %=listAvent.size();
		a = listAvent.get(k);
		path = a.getNom().toLowerCase();

		i++;

		imgPlayerIn1T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
		imP1T = imgPlayerIn1T.getImage();
		imP1T = imP1T.getScaledInstance(150,150, Image.SCALE_DEFAULT);
		imgP1T = new ImageIcon(imP1T);
		btnImgPlayerIn1T.setIcon(imgP1T);
		btnImgPlayerIn1T.setName(a.getNum()+"");

		PanelPlayerIn1T.repaint();
		//System.out.println(" 1 = "+a.getNom()+" k"+k);

		if(i<=listAvent.size()) {
			k++;
			k %= listAvent.size();
			a = listAvent.get(k);
			path = a.getNom().toLowerCase();
			i++;

			imgPlayerIn2T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
			imP2T = imgPlayerIn2T.getImage();
			imP2T = imP2T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			imgP2T = new ImageIcon(imP2T);
			btnImgPlayerIn2T.setIcon(imgP2T);
			btnImgPlayerIn2T.setName(a.getNum()+"");
			PanelPlayerIn2T.repaint();

		}

		if(i<=listAvent.size()) {
			k++;
			k %= listAvent.size();
			a = listAvent.get(k);
			path = a.getNom().toLowerCase();
			i++;


			imgPlayerIn3T = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
			imP3T = imgPlayerIn3T.getImage();
			imP3T = imP3T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			imgP3T = new ImageIcon(imP3T);
			btnImgPlayerIn3T.setIcon(imgP3T);
			btnImgPlayerIn3T.setName(a.getNum()+"");
			PanelPlayerIn3T.repaint();
			//	System.out.println(" 3 = "+a.getNom()+" k"+k);
		}


	}

	public void afficherNivCurseur(int niv) {
		sliderImg.setNiv(niv);
		sliderImg.repaint();
		sliderImg.revalidate();
	}

	public void setIndication(String str) {
		msgHelp.setText(str);
	}

	public void afficherDefausse(Aventurier a) {
		//setPanelVisible(false);
		panelDefausse.setListCarte(a.getListeCarteJoueur());
		panelDefausse.repaint();
		panelDefausse.setVisible(true);
	}

	public void setPanelVisible(boolean b) {
		PanelEast.setVisible(b);
		PanelHelp.setVisible(b);
		panHelp.setVisible(b);
		PanelSouth.setVisible(b);
		Plateau.setVisible(b);
		PanelTresor.setVisible(b);
		sliderImg.setVisible(b);
		CardPlayer.setVisible(b);
		PanelEast.setEnabled(b);
		PanelHelp.setEnabled(b);
		panHelp.setEnabled(b);
		PanelSouth.setEnabled(b);
		Plateau.setEnabled(b);
		PanelTresor.setEnabled(b);
		sliderImg.setEnabled(b);
		CardPlayer.setEnabled(b);
	}
}
