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
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Carte.Carte;
import Carte.CarteTresor;
import Carte.Classique;
import Carte.NomTresor;
import ille_intedite.Controleur;
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
	HashMap<String,CasePlateau> listPan = new HashMap();
	HashMap<Integer,PanelCarte> listCartes = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes3T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes2T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer, PanelCarte> listeCartes1T = new HashMap<Integer,PanelCarte>();
	HashMap<Integer,JPanel> listCurseur = new HashMap<Integer,JPanel>();
	private JTextField textField;
	JPanel PanelHelp;
	PanelFont CardPlayer;
	JPanel PanelSouth;
	JPanel PanelEast;
	JPanel PanelTresor;
	PanelButton btDeplace;
	PanelButton btAsseche;
	PanelButton btDonneCarte;
	PanelButton btRecupereTresor;
	PanelButton btFinDeTour;

	VueGrille vue;

	PanelAfficheCarte panelCartePiochee;


	private ImageIcon imgPlayerIn1T,imgPlayerIn2T,imgPlayerIn3T,imgP1T,imgP2T,imgP3T;
	JPanel PanelPlayerIn3T,PanelPlayerIn2T,PanelPlayerIn1T;
	Image imP3T,imP2T,imP1T;
	JLabel lbIconJoueur;
	JButton btnHelp;
	PanelJoueur btnImgPlayerIn2T,btnImgPlayerIn1T,btnImgPlayerIn3T;
	private PanelCarte panelCarte3T2,
	panelCarte3T1,
	panelCarte3T3,
	panelCarte3T4,
	panelCarte3T5,
	panelCarte2T1,
	panelCarte2T2,
	panelCarte2T3,
	panelCarte2T4,
	panelCarte2T5,
	panelCarte1T1,
	panelCarte1T2,
	panelCarte1T3,
	panelCarte1T4,
	panelCarte1T5;
	private PanelCarte panelCarte1,panelCarte2,panelCarte3,panelCarte4,panelCarte5;

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
		//Creation de la fenetre
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);

		panelCartePiochee=new PanelAfficheCarte(this, frame);
		panelCartePiochee.setBounds(0, 0, 1280, 680);
		frame.getContentPane().add(panelCartePiochee);



		sliderImg = new PanelCurseur(this);
		sliderImg.setBounds(0, 59, 100, 685);
		frame.getContentPane().add(sliderImg);

		PanelHelp = new JPanel();
		PanelHelp.setBounds(0, 0, 100, 60);
		frame.getContentPane().add(PanelHelp);
		PanelHelp.setLayout(null);

		btnHelp = new JButton("");
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



		ImageIcon imgHelp = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/Art/btnAide.png");

		Image imH = imgHelp.getImage();

		imH = imH.getScaledInstance(100, 60, Image.SCALE_DEFAULT);

		ImageIcon imgH = new ImageIcon(imH);
		btnHelp.setIcon(imgH);
		btnHelp.setBorder(null);
		PanelHelp.add(btnHelp);


		CardPlayer = new PanelFont(0,true);
		CardPlayer.setBackground(new Color(139, 69, 19));
		CardPlayer.setBounds(114, 31, 250, 597);
		frame.getContentPane().add(CardPlayer);
		CardPlayer.setLayout(null);

		panelCarte1 = new PanelCarte(0,this,true);
		panelCarte1.setBounds(19, 13, 100, 140);
		CardPlayer.add(panelCarte1);
		listCartes.put(0, panelCarte1);
		panelCarte1.setLayout(null);

		panelCarte2 = new PanelCarte(1,this,true);
		panelCarte2.setBounds(131, 13, 100, 140);
		CardPlayer.add(panelCarte2);
		listCartes.put(1, panelCarte2);
		panelCarte2.setLayout(null);

		panelCarte3 = new PanelCarte(2,this,true);
		panelCarte3.setBounds(19, 166, 100, 140);
		CardPlayer.add(panelCarte3);
		listCartes.put(2, panelCarte3);
		panelCarte3.setLayout(null);

		panelCarte4 = new PanelCarte(3,this,true);
		panelCarte4.setBounds(131, 166, 100, 140);
		CardPlayer.add(panelCarte4);
		listCartes.put(3, panelCarte4);
		panelCarte4.setLayout(null);

		panelCarte5 = new PanelCarte(4,this,true);
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

		PanelSouth = new PanelFont(4,true);
		PanelSouth.setBackground(new Color(139, 69, 19));
		PanelSouth.setBounds(99, 641, 1165, 44);
		frame.getContentPane().add(PanelSouth);
		PanelSouth.setLayout(null);

		btDeplace = new PanelButton(this,"Deplacer",TypeMessage.Clique_Deplace);
		btDeplace.setBounds(10, 5, 110, 34);
		PanelSouth.add(btDeplace);

		btAsseche = new PanelButton(this,"Assecher",TypeMessage.Clique_Asseche);
		btAsseche.setBounds(140, 5, 110, 34);
		PanelSouth.add(btAsseche);

		btDonneCarte = new PanelButton(this,"DonnerCarte",TypeMessage.Clique_DonneCarte);
		btDonneCarte.setBounds(270, 5, 110, 34);
		PanelSouth.add(btDonneCarte);

		btRecupereTresor = new PanelButton(this,"RecupererTresor",TypeMessage.Clique_RecupereTresor);
		btRecupereTresor.setBounds(454, 5, 156, 34);
		PanelSouth.add(btRecupereTresor);


		btFinDeTour = new PanelButton(this,"FinDeTour",TypeMessage.Clique_Fin_Tour);
		btFinDeTour.setBounds(1030, 5, 110, 34);
		PanelSouth.add(btFinDeTour);


		//PLATEAU ---------------------------
		Plateau = new JPanel();
		Plateau.setBackground(new Color(160, 82, 45));
		Plateau.setBounds(376, 31, 597, 597);
		Plateau .setLayout(new GridLayout(6,6,0,0));
		frame.getContentPane().add(Plateau);
		//--------------------------------

		PanelEast = new PanelFont(3,true);
		PanelEast.setBackground(new Color(139, 69, 19));
		PanelEast.setBounds(985, 31, 279, 597);
		frame.getContentPane().add(PanelEast);
		PanelEast.setLayout(null);
		//--------------------- 1 et principal -----------------------
//Gestion du panel contenant bouton joueur dans 1 tour
		PanelPlayerIn1T = new JPanel();
		PanelPlayerIn1T.setBackground(new Color(139, 69, 19,0));
		PanelPlayerIn1T.setBounds(23, 425, 150, 150);
		PanelEast.add(PanelPlayerIn1T);
		PanelPlayerIn1T.setLayout(null);
//Gestion du bouton joueur dans 1 tour
		btnImgPlayerIn1T = new PanelJoueur(0,this, true);
		btnImgPlayerIn1T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBounds(0, 0, 150, 150);
		PanelPlayerIn1T.add(btnImgPlayerIn1T);
		btnImgPlayerIn1T.setBorder(null);
//Gestion du panel contenant bouton joueur dans 2 tour
		PanelPlayerIn2T = new JPanel();
		PanelPlayerIn2T.setBackground(new Color(139, 69, 19,0));
		PanelPlayerIn2T.setBounds(23, 264, 150, 150);
		PanelEast.add(PanelPlayerIn2T);
		PanelPlayerIn2T.setLayout(null);
//Gestion du bouton joueur dans 2 tour
		btnImgPlayerIn2T = new PanelJoueur(1, this, true);
		btnImgPlayerIn2T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBackground(new Color(139, 69, 19,0));
		btnImgPlayerIn2T.setBounds(0, 0, 150, 150);
		PanelPlayerIn2T.add(btnImgPlayerIn2T);
		btnImgPlayerIn2T.setBorder(null);

//Gestion du panel contenant bouton joueur dans 3 tour
		PanelPlayerIn3T = new JPanel();
		PanelPlayerIn3T.setBackground(new Color(139, 69, 19,0));
		PanelPlayerIn3T.setBounds(23, 101, 150, 150);
		PanelEast.add(PanelPlayerIn3T);
		PanelPlayerIn3T.setLayout(null);

//Gestion du bouton joueur dans 3 tour
		btnImgPlayerIn3T = new PanelJoueur(2,this, true);
		btnImgPlayerIn3T.setBackground(new Color(139, 69, 19,0));
		btnImgPlayerIn3T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setBounds(0, 0, 150, 150);
		PanelPlayerIn3T.add(btnImgPlayerIn3T);
		btnImgPlayerIn3T.setBorder(null);

//Mise en place panel Tresor
		PanelTresor = new JPanel();
		PanelTresor.setBackground(new Color(165, 42, 42));
		PanelTresor.setBounds(0, 0, 279, 84);
		PanelEast.add(PanelTresor);
		PanelTresor.setLayout(null);

//Mise en place Label des Tresor (Image)
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

//Panel Des petit carte a coter du joueur
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
		listeCartes2T.put(0, (PanelCarte) panelCarte2T1);

		panelCarte2T2 = new PanelCarte(1,this,true);
		panelCarte2T2.setBounds(230, 264, 25, 35);
		PanelEast.add(panelCarte2T2);
		listeCartes2T.put(1, (PanelCarte) panelCarte2T2);

		panelCarte2T3 = new PanelCarte(2,this,true);
		panelCarte2T3.setBounds(185, 312, 25, 35);
		PanelEast.add(panelCarte2T3);
		listeCartes2T.put(2, (PanelCarte) panelCarte2T3);

		panelCarte2T4 = new PanelCarte(3,this,true);
		panelCarte2T4.setBounds(230, 312, 25, 35);
		PanelEast.add(panelCarte2T4);
		listeCartes2T.put(3, (PanelCarte) panelCarte2T4);

		panelCarte2T5 = new PanelCarte(4,this,true);
		panelCarte2T5.setBounds(208, 360, 25, 35);
		PanelEast.add(panelCarte2T5);
		listeCartes2T.put(4, (PanelCarte) panelCarte2T5);

		panelCarte1T1 = new PanelCarte(0,this,true);
		panelCarte1T1.setBounds(185, 425, 25, 35);
		PanelEast.add(panelCarte1T1);
		listeCartes1T.put(0, (PanelCarte) panelCarte1T1);

		panelCarte1T2 = new PanelCarte(1,this,true);
		panelCarte1T2.setBounds(230, 425, 25, 35);
		PanelEast.add(panelCarte1T2);
		listeCartes1T.put(1, (PanelCarte) panelCarte1T2);

		panelCarte1T3 = new PanelCarte(2,this,true);
		panelCarte1T3.setBounds(185, 473, 25, 35);
		PanelEast.add(panelCarte1T3);
		listeCartes1T.put(2, (PanelCarte) panelCarte1T3);

		panelCarte1T4 = new PanelCarte(3,this,true);
		panelCarte1T4.setBounds(230, 473, 25, 35);
		PanelEast.add(panelCarte1T4);
		listeCartes1T.put(3, (PanelCarte) panelCarte1T4);

		panelCarte1T5 = new PanelCarte(4,this,true);
		panelCarte1T5.setBounds(208, 521, 25, 35);
		PanelEast.add(panelCarte1T5);
		listeCartes1T.put(4, (PanelCarte) panelCarte1T5);

		panHelp = new PanelFont(4,true);
		panHelp.setBackground(new Color(144, 158, 181));
		panHelp.setBounds(114, 0, 1150, 32);
		frame.getContentPane().add(panHelp);
		panHelp.setLayout(new BorderLayout(0, 0));

		msgHelp = new JLabel("");
		msgHelp.setForeground(Color.WHITE);
		msgHelp.setFont(new Font("Serif", Font.BOLD, 25));
		msgHelp.setHorizontalAlignment(JLabel.CENTER);
		msgHelp.setVerticalAlignment(JLabel.CENTER);

		panHelp.add(msgHelp, BorderLayout.CENTER);
		frame.setBounds(100, 100, 1280, 720);

		//----------------
		//PROVISOIRE 
		//--------------

		lblJoeurN = new JLabel();

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
		case StatueZephyr:lbImgTresor4.setEnabled(true);
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


		//------Desactivation des bouton non necessaire en fonction du nombre de joeur ------------
		btnImgPlayerIn2T.setVisible(true);
		btnImgPlayerIn2T.setEnabled(true);	
		if(Controleur.getNbJoueur()<3) {

			btnImgPlayerIn2T.setEnabled(false);	



			panelCarte2T1.setEnabled(false);
			panelCarte2T1.setVisible(false);

			panelCarte2T2.setEnabled(false);
			panelCarte2T2.setVisible(false);

			panelCarte2T3.setEnabled(false);
			panelCarte2T3.setVisible(false);

			panelCarte2T4.setEnabled(false);
			panelCarte2T4.setVisible(false);

			panelCarte2T5.setEnabled(false);
			panelCarte2T5.setVisible(false);



		}

		if(Controleur.getNbJoueur()<4) {
			btnImgPlayerIn3T.setEnabled(false);	

			btnImgPlayerIn3T.setEnabled(false);	
			panelCarte3T1.setEnabled(false);
			panelCarte3T1.setVisible(false);

			panelCarte3T2.setEnabled(false);
			panelCarte3T2.setVisible(false);

			panelCarte3T3.setEnabled(false);
			panelCarte3T3.setVisible(false);

			panelCarte3T4.setEnabled(false);
			panelCarte3T4.setVisible(false);

			panelCarte3T5.setEnabled(false);
			panelCarte3T5.setVisible(false);


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


	public void setCartePanel(int num, Classique c,int numJoeur) {
		PanelCarte p = listCartes.get(num);

		p.setNumJoueur(numJoeur);
		p.setCarte(c);
		p.repaint();
		p.revalidate();
	}


	//Genere automatiquement les bouton lorsque lon clique sur les joeur 
	public ActionListener actionBoutonJoueur() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bt = (JButton) e.getSource();
				
				if(bt.getName() != null) {
					Message m = new Message(TypeMessage.Clique_Joueur);

					m.setNum(Integer.parseInt(bt.getName()));

					notifierObservateur(m);
				}else {
				}
			}
		};
	}


	
	//Mise as jour des carte sur le cote , des joeur qui ne joue pas 
	public void miseAsJourJoeurCotte(Aventurier a  , ArrayList<Aventurier> listAvent) {
		

		int i = 1;
		int k = listAvent.indexOf(a);
		Iterator<PanelCarte> it;
		int nbr,s ;
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
		//---------------Joeur qui joeur dans 1T ------------------------
		k %=listAvent.size();
		a = listAvent.get(k);

		path = a.getNom().toLowerCase();

		s = a.getListeCarteJoueur().size();

		it = listeCartes1T.values().iterator();


		for(int j = 0;it.hasNext();j++) {
			PanelCarte p =it.next(); 
			if(j<s) {

				p.setCarte(a.getListeCarteJoueur().get(j));
				// System.out.println("conard");
				p.setNumJoueur(a.getNum());

				if(a.getListeCarteJoueur().get(j) instanceof CarteTresor) {
					p.desactivate();
				}else {
					p.activate();
				}

			}else {
				p.setCarte(null);
			}
 

			p.repaint();
		}

		i++;

		btnImgPlayerIn1T.setNumJoueur(a.getNum());
		btnImgPlayerIn1T.setPath(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");

		PanelPlayerIn1T.repaint();
		btnImgPlayerIn1T.repaint();
		btnImgPlayerIn1T.revalidate();

		//---------------Joeur qui joeur dans 2T ------------------------
		if(i<=listAvent.size()) {
			k++;
			k %= listAvent.size();
			a = listAvent.get(k);
			path = a.getNom().toLowerCase();

			s = a.getListeCarteJoueur().size();

			it = listeCartes2T.values().iterator();


			for(int j = 0;it.hasNext();j++) {
				PanelCarte p =it.next(); 
				if(j<s) {

					p.setCarte(a.getListeCarteJoueur().get(j));
					// System.out.println("conard");
					p.setNumJoueur(a.getNum());

					if(a.getListeCarteJoueur().get(j) instanceof CarteTresor) {
						p.desactivate();
					}else {
						p.activate();
					}

				}else {
					p.setCarte(null);
				}

				p.repaint();
			}

			i++;

			btnImgPlayerIn2T.setNumJoueur(a.getNum());
			btnImgPlayerIn2T.setPath(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");

		}
		//---------------Joeur qui joeur dans 3T ------------------------
		if(i<=listAvent.size()) {
			k++;
			k %= listAvent.size();
			a = listAvent.get(k);

			s = a.getListeCarteJoueur().size();

			s = a.getListeCarteJoueur().size();

			it = listeCartes3T.values().iterator();


			for(int j = 0;it.hasNext();j++) {
				PanelCarte p =it.next(); 
				if(j<s) {

					p.setCarte(a.getListeCarteJoueur().get(j));
					// System.out.println("conard");
					p.setNumJoueur(a.getNum());

					if(a.getListeCarteJoueur().get(j) instanceof CarteTresor) {
						p.desactivate();
					}else {
						p.activate();
					}

				}else {
					p.setCarte(null);
				}

				p.repaint();
			}


			path = a.getNom().toLowerCase();
			i++;


			btnImgPlayerIn3T.setNumJoueur(a.getNum());
			btnImgPlayerIn3T.setPath(System.getProperty("user.dir")+"\\src\\"+"images/persos/"+path+".png");
			//	System.out.println(" 3 = "+a.getNom()+" k"+k);
		}
		
		PanelEast.repaint();
		PanelEast.revalidate();

	}

	public void afficherNivCurseur(int niv) {
		sliderImg.setNiv(niv);
		sliderImg.repaint();
		sliderImg.revalidate();
	}

	public void setIndication(String str) {
		msgHelp.setText(str);
	}

	public void afficherPioche(ArrayList<Carte> listCartes, boolean ok) {
		setPanelEnabled(false);
		panelCartePiochee.setOk(ok);
		panelCartePiochee.removeAll();
		panelCartePiochee.setListCarte(listCartes);
		panelCartePiochee.repaint();
		panelCartePiochee.setVisible(true);		
	}

	public void setPanelEnabled(boolean b) {
		
		PanelEast.setEnabled(b);
		PanelHelp.setEnabled(b);
		panHelp.setEnabled(b);
		PanelSouth.setEnabled(b);
		Plateau.setEnabled(b);
		PanelTresor.setEnabled(b);
		sliderImg.setEnabled(b);
		CardPlayer.setEnabled(b);
		//CardPlayer.setVisible(b);

		//	panelC
		if(b) {
			btAsseche.activate();
			btDeplace.activate();
			btDonneCarte.activate();
			btFinDeTour.activate();
			btRecupereTresor.activate();
			btnImgPlayerIn1T.activate();
			btnImgPlayerIn2T.activate();
			btnImgPlayerIn3T.activate();
			panelCarte1.activate();
			panelCarte2.activate();
			panelCarte3.activate();
			panelCarte4.activate();
			panelCarte5.activate();

			for(PanelCarte p : listeCartes1T.values()) {
				p.setEnabled(true);
				p.activate();;
			}
			
			for(PanelCarte p : listeCartes1T.values()) {
				p.setEnabled(true);
				p.activate();;
			}
			
			for(PanelCarte p : listeCartes1T.values()) {
				p.setEnabled(true);
				p.activate();;
			}


		}else {
			btAsseche.desactivate();
			btDeplace.desactivate();
			btDonneCarte.desactivate();
			btFinDeTour.desactivate();
			btRecupereTresor.desactivate();
			btnImgPlayerIn1T.desactivate();
			btnImgPlayerIn2T.desactivate();
			btnImgPlayerIn3T.desactivate();
			panelCarte1.desactivate();
			panelCarte2.desactivate();
			panelCarte3.desactivate();
			panelCarte4.desactivate();
			panelCarte5.desactivate();
			
			
			for(PanelCarte p : listeCartes1T.values()) {
				p.setEnabled(false);
				p.desactivate();
			}
			
			for(PanelCarte p : listeCartes2T.values()) {
				p.desactivate();
				p.setEnabled(false);
			}
			
			for(PanelCarte p : listeCartes3T.values()) {
				p.setEnabled(false);
				p.desactivate();
			}
			
			

		}
		btnImgPlayerIn1T.setEnabled(b);



		panelCarte1.setEnabled(b);
		panelCarte2.setEnabled(b);
		panelCarte3.setEnabled(b);
		panelCarte4.setEnabled(b);
		panelCarte5.setEnabled(b);
		panelCarte3T2.setEnabled( b);
		panelCarte3T1.setEnabled( b);
		panelCarte3T3.setEnabled( b);
		panelCarte3T4.setEnabled( b);
		panelCarte3T5.setEnabled( b);
		panelCarte2T1.setEnabled(b);
		panelCarte2T2.setEnabled(b);
		panelCarte2T3.setEnabled(b);
		panelCarte2T4.setEnabled(b);
		panelCarte2T5.setEnabled(b);
		panelCarte1T1.setEnabled(b);
		panelCarte1T2.setEnabled(b);
		panelCarte1T3.setEnabled(b);
		panelCarte1T4.setEnabled(b);
		panelCarte1T5.setEnabled(b);
		
		

		for (PanelButton j : listButton.values()) {
			j.setEnabled(b);
		}
		for (PanelCarte j : listCartes.values()) {
			j.setEnabled(b);
		}
		for (JPanel j : listCurseur.values()) {
			j.setEnabled(b);
		}
		for (JPanel j : listPan.values()) {
			j.setEnabled(b);
		}

		for(CasePlateau c : listPan.values()) {
			c.mouseEnable(b);
		}

	}

	public void setActionEnabled(boolean b) {
		if(b) {
			btAsseche.activate();
			btDeplace.activate();
			btDonneCarte.activate();
			btFinDeTour.activate();
			btRecupereTresor.activate();
		}else {
			btAsseche.desactivate();
			btDeplace.desactivate();
			btDonneCarte.desactivate();
			btFinDeTour.desactivate();
			btRecupereTresor.desactivate();
		}
	}

	public void afficherPlateau() {
		setPanelEnabled(true);
		panelCartePiochee.setVisible(false);
	}
	
	public void setEtaCasePlateauEneble(boolean b) {
		for(CasePlateau c : listPan.values()) {
			c.mouseEnable(b);
		}
	}
}
