package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ille_intedite.Controleur;
import ille_intedite.VueGrille;
import ille_intedite.Aventurie.Aventurier;
import ille_intedite.Aventurie.Aviateur;
import ille_intedite.Aventurie.Explorateur;
import ille_intedite.Aventurie.Ingenieur;
import ille_intedite.Aventurie.Messager;
import ille_intedite.Aventurie.Navigateur;
import ille_intedite.Aventurie.Plongeur;
import utils.Utils.Pion;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;



public class FenetreStart extends JFrame {

	private int nbJoueurs;

	private JFrame moi;

	private JLabel labelExplo;
	private JLabel labelInge;
	private JLabel labelMessa;
	private JLabel labelNavi;
	private JLabel labelPilo;
	private JLabel labelPlon;

	private boolean choisis = false;

	private JComboBox difficulte;
	private JComboBox combobox;



	private ArrayList<JLabel> joueurs = new ArrayList<JLabel>();
	private static int choixCourant = 0;
	private int choixAven;
	private boolean etatBoutonExplo = false;
	private boolean etatBoutonInge = false;
	private boolean etatBoutonMessa = false;
	private boolean etatBoutonNavi = false;
	private boolean etatBoutonPilo = false;
	private boolean etatBoutonPlon = false;
	private boolean termine = false;

	public FenetreStart(){

		// images explorateur
		ImageIcon imgIcon = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/explorateur.png");
		ImageIcon imgIconS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/explorateurSelected.png");
		ImageIcon imgIconAS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/explorateurAlreadySelected.png");

		// images ingenieur
		ImageIcon imgIcon1 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/ingenieur.png");
		ImageIcon imgIcon1S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/ingenieurSelected.png");
		ImageIcon imgIcon1AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/ingenieuralreadySelected.png");

		// images messager
		ImageIcon imgIcon2 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messager.png");
		ImageIcon imgIcon2S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messagerSelected.png");
		ImageIcon imgIcon2AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messageralreadySelected.png");
		
		// imgaes navigateur
		ImageIcon imgIcon3 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateur.png");
		ImageIcon imgIcon3S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateurSelected.png");
		ImageIcon imgIcon3AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateuralreadySelected.png");
		
		// images pilote
		ImageIcon imgIcon4 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/aviateur.png");
		ImageIcon imgIcon4S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/piloteSelected.png");
		ImageIcon imgIcon4AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/pilotealreadySelected.png");
		
		// images plongeur
		ImageIcon imgIcon5 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeur.png");
		ImageIcon imgIcon5S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeurSelected.png");
		ImageIcon imgIcon5AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeuralreadySelected.png");

		//création de la fenêtre
		moi = this;
		this.setTitle("Ile Interdite");

		this.setSize(800, 700);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		this.setVisible(true);

		this.setResizable(false);
		setBackground(new Color(139,69,18));


		// création du panel principal
		JPanel pan = new JPanel();
		pan.setBackground(new Color(139,69,18));
		pan.setLayout(null);
		pan.setBounds(0, 0, 800, 700);

				ImageIcon imgIconTitre = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/titre.png");
		Font font = new Font("Serif", Font.BOLD, 25);

		//création d'un boutonGroup pour le choix de joueurs
		ButtonGroup choix = new ButtonGroup();
		JLabel labelJoueurCourrant = new JLabel("");
		labelJoueurCourrant.setBounds(0, 0, 0, 0);
		pan.add(labelJoueurCourrant);


		this.setContentPane(pan);
		this.setVisible(true);

		PanelFont PanelFont = new PanelFont(1, true);
		PanelFont.setBounds(0, 0, 794, 665);
		pan.add(PanelFont);
		PanelFont.setLayout(null);

		//ajout de l'image du titre "Ile Interdite"
		JLabel labelTitre = new JLabel();
		labelTitre.setBounds(250, 42, 300, 100);
		PanelFont.add(labelTitre);
		labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitre.setIcon(imgIconTitre);

		// création des radios boutons pour le choix des joueurs et ajout dans le bouton group
		JRadioButton deuxJoueurs = new JRadioButton("2 joueurs");

		JRadioButton troisJoueurs = new JRadioButton("3 joueurs");

		JRadioButton quatreJoueurs = new JRadioButton("4 joueurs");

		PanelFont panel = new PanelFont(2,true);
		panel.setBounds(150, 155, 500, 200);
		PanelFont.add(panel);
		panel.setLayout(null);

		deuxJoueurs.setBounds(51, 82, 100, 25);
		panel.add(deuxJoueurs);
		// choix par défault
		deuxJoueurs.setSelected(true);

		choix.add(deuxJoueurs);

		troisJoueurs.setBounds(199, 82, 100, 25);
		panel.add(troisJoueurs);
		choix.add(troisJoueurs);

		quatreJoueurs.setBounds(347, 82, 100, 25);
		panel.add(quatreJoueurs);
		choix.add(quatreJoueurs);
		
		// création d'une combo box avec les difficultés
		difficulte = new JComboBox();
		difficulte.setBounds(357, 116, 90, 22);
		panel.add(difficulte);
		difficulte.addItem("Novice");
		difficulte.addItem("Normal");
		difficulte.addItem("Elite");
		difficulte.addItem("Legendaire");

		// création du texte et ajour dans le panel principal
		JLabel labelDifficulte = new JLabel("Choisir la difficulte du jeu :");
		labelDifficulte.setForeground(Color.WHITE);
		labelDifficulte.setBounds(181, 119, 154, 16);
		panel.add(labelDifficulte);
		JLabel labelChoix = new JLabel("Nombre de joueurs");
		labelChoix.setBounds(145, 40, 210, 33);
		panel.add(labelChoix);
		labelChoix.setForeground(Color.WHITE);
		labelChoix.setHorizontalAlignment(SwingConstants.CENTER);
		labelChoix.setFont(font);

		// choix du scénario
		combobox = new JComboBox();
		combobox.setModel(new DefaultComboBoxModel(new String[] {
				"Partie normale",
				"Victoire",
				"D\u00E9faite curseur",
				"D\u00E9faite heliport",
				"D\u00E9faite noyade",
		"D\u00E9faite tr\u00E9sor"}));
		combobox.setBounds(51, 116, 100, 22);
		panel.add(combobox);


		//création du bouton pour confirmer
		JButton selectChoix = new JButton("Confirmer");
		selectChoix.setBounds(350, 384, 100, 25);
		PanelFont.add(selectChoix);

		//création d'un bouton règles
		JButton btnRegle = new JButton("Regles");
		btnRegle.setBounds(200, 600, 110, 25);
		PanelFont.add(btnRegle);

		// création d'un bouton commencer
		JButton btnCommencer = new JButton("Commencer");
		btnCommencer.setBounds(490, 600, 110, 25);
		PanelFont.add(btnCommencer);

		// empecher l'utilisateur de cliquer sur "Commencer" si le choix des aventuriers n'as pas été fait
		btnCommencer.setEnabled(false);




		// création d'un autre panel qui va contenir les label des aventurier (avec les différentes images)
		JPanel panChoixCarte = new JPanel();
		panChoixCarte.setBounds(50, 422, 700, 160);
		PanelFont.add(panChoixCarte);
		panChoixCarte.setBackground(new Color(139,69,18,0));
		labelExplo = new JLabel();
		labelExplo.setBounds(50, 25, 120, 120);
		labelInge = new JLabel();
		labelInge.setBounds(144, 25, 120, 120);
		labelMessa = new JLabel();
		labelMessa.setBounds(250, 25, 120, 120);
		labelNavi = new JLabel();
		labelNavi.setBounds(350, 25, 120, 120);
		labelPilo = new JLabel();
		labelPilo.setBounds(435, 25, 120, 120);
		labelPlon = new JLabel();
		labelPlon.setBounds(550, 25, 120, 120);
		panChoixCarte.setLayout(null);


		// Initialisation des label avec l'image grisé
		labelExplo.setIcon(imgIconAS);
		panChoixCarte.add(labelExplo);



		labelInge.setIcon(imgIcon1AS);
		panChoixCarte.add(labelInge);



		labelMessa.setIcon(imgIcon2AS);
		panChoixCarte.add(labelMessa);



		labelNavi.setIcon(imgIcon3AS);
		panChoixCarte.add(labelNavi);



		labelPilo.setIcon(imgIcon4AS);
		panChoixCarte.add(labelPilo);


		labelPlon.setIcon(imgIcon5AS);
		panChoixCarte.add(labelPlon);


		// mouseListener permettant de rendre les boutons cliquables et de changer les images en fonction du choix effectué
		labelExplo.addMouseListener(new MouseListener(){


			@Override
			public void mouseClicked(MouseEvent e) {
				// reset toutes les images à "Séléectionnable" sauf celles déjà sélectionnées
				if (termine == false){
					if (etatBoutonInge){    
						labelInge.setIcon(imgIcon1);

					}

					if (etatBoutonMessa){   
						labelMessa.setIcon(imgIcon2);
					}

					if (etatBoutonNavi){   
						labelNavi.setIcon(imgIcon3);
					}

					if (etatBoutonPilo){   
						labelPilo.setIcon(imgIcon4);
					}

					if (etatBoutonPlon){   
						labelPlon.setIcon(imgIcon5);
					}

					// change l'image du label sélectionner pour que cela soit visible par l'utilisateur
					// rendre la bouton confirmer cliquable
					// set la variable choixAven au numéro de l'aventurier selectionné
					if (etatBoutonExplo){
						labelExplo.setIcon(imgIconS);
						choixAven = 0;
						selectChoix.setEnabled(true);
					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		labelInge.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (termine == false){
					if (etatBoutonExplo){    
						labelExplo.setIcon(imgIcon);
					}

					if (etatBoutonMessa){   
						labelMessa.setIcon(imgIcon2);
					}

					if (etatBoutonNavi){   
						labelNavi.setIcon(imgIcon3);
					}

					if (etatBoutonPilo){   
						labelPilo.setIcon(imgIcon4);
					}

					if (etatBoutonPlon){   
						labelPlon.setIcon(imgIcon5);
					}


					if (etatBoutonInge){
						labelInge.setIcon(imgIcon1S);
						choixAven = 1;
						selectChoix.setEnabled(true);                    }
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		labelMessa.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (termine == false){
					if (etatBoutonInge){    
						labelInge.setIcon(imgIcon1);
					}

					if (etatBoutonExplo){   
						labelExplo.setIcon(imgIcon);
					}

					if (etatBoutonNavi){   
						labelNavi.setIcon(imgIcon3);
					}

					if (etatBoutonPilo){   
						labelPilo.setIcon(imgIcon4);
					}

					if (etatBoutonPlon){   
						labelPlon.setIcon(imgIcon5);
					}


					if (etatBoutonMessa){
						labelMessa.setIcon(imgIcon2S);
						choixAven = 2;
						selectChoix.setEnabled(true);
					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		labelNavi.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (termine == false){
					if (etatBoutonInge){    
						labelInge.setIcon(imgIcon1);
					}

					if (etatBoutonMessa){   
						labelMessa.setIcon(imgIcon2);
					}

					if (etatBoutonExplo){   
						labelExplo.setIcon(imgIcon);
					}

					if (etatBoutonPilo){   
						labelPilo.setIcon(imgIcon4);
					}

					if (etatBoutonPlon){   
						labelPlon.setIcon(imgIcon5);
					}

					if (etatBoutonNavi){
						labelNavi.setIcon(imgIcon3S);
						choixAven = 3;
						selectChoix.setEnabled(true);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		labelPilo.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (termine == false){
					if (etatBoutonInge){    
						labelInge.setIcon(imgIcon1);
					}

					if (etatBoutonMessa){   
						labelMessa.setIcon(imgIcon2);
					}

					if (etatBoutonNavi){   
						labelNavi.setIcon(imgIcon3);
					}

					if (etatBoutonExplo){   
						labelExplo.setIcon(imgIcon);
					}

					if (etatBoutonPlon){   
						labelPlon.setIcon(imgIcon5);
					}


					if (etatBoutonPilo){
						labelPilo.setIcon(imgIcon4S);
						choixAven = 4;
						selectChoix.setEnabled(true);
					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		labelPlon.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (termine == false){
					if (etatBoutonInge){    
						labelInge.setIcon(imgIcon1);
					}

					if (etatBoutonMessa){   
						labelMessa.setIcon(imgIcon2);
					}

					if (etatBoutonNavi){   
						labelNavi.setIcon(imgIcon3);
					}

					if (etatBoutonPilo){   
						labelPilo.setIcon(imgIcon4);
					}

					if (etatBoutonExplo){   
						labelExplo.setIcon(imgIcon);
					}


					if (etatBoutonPlon){
						labelPlon.setIcon(imgIcon5S);
						choixAven = 5;
						selectChoix.setEnabled(true);

					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		btnCommencer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// initialisation de l'IHM principal et de la vue grille
				VueGrille vue = new VueGrille();
				IHMV2 ihm = new IHMV2(vue);
				Controleur ctrl = new Controleur(ihm,vue,createMessageInit());
				//	        ctrl.creeDeckInondation(); Debug en cours

				vue.setCtrl(ctrl);
				vue.setIhm(ihm);

				ihm.addObservateur(ctrl);

				vue.afficherGrille();

				ctrl.piocher5Inondation();
				//Comentaire de modification 
				moi.setVisible(false);
				//moi.dispose();


			}

		});
		
		// afficher la fenêtre des regles lors du clic sur le bouton regles 
		btnRegle.addActionListener(new ActionListener(){



			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrameHtml();

			}
		});

		// actionListener sur le bouton Selection des choix
		selectChoix.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				// le bouton est set à Confirmer par défault
				// permet de récupérer le nombre de joueurs sélectionné via les boutons radio et de la rendre non cliquable
				if ("Confirmer".equals(selectChoix.getText())){

					if (deuxJoueurs.isSelected()) {
						nbJoueurs = 2;
					} else if (troisJoueurs.isSelected()){
						nbJoueurs = 3;
					} else if (quatreJoueurs.isSelected()){
						nbJoueurs = 4;
					}
					deuxJoueurs.setEnabled(false);
					troisJoueurs.setEnabled(false);
					quatreJoueurs.setEnabled(false);
					choixCourant = choixCourant+1;
					
					//change le texte du bouton avec "Joueur + choixCourant"
					//choixCourant initalisé à 1
					labelJoueurCourrant.setText("Joueur " + choixCourant);
					selectChoix.setText("Valider");
					selectChoix.setEnabled(false);

					//dégrisage des images
					labelExplo.setIcon(imgIcon);
					labelInge.setIcon(imgIcon1);
					labelMessa.setIcon(imgIcon2);
					labelNavi.setIcon(imgIcon3);
					labelPilo.setIcon(imgIcon4);
					labelPlon.setIcon(imgIcon5);
					etatBoutonExplo = true;
					etatBoutonInge = true;
					etatBoutonMessa = true;
					etatBoutonNavi = true;
					etatBoutonPilo = true;
					etatBoutonPlon = true;

				} else {
					//incrémentation de choixCourant et changement de nom du bouton
					choixCourant = choixCourant+1;
					labelJoueurCourrant.setText("Joueur " + choixCourant);
					selectChoix.setText("Valider");
					//vérifier si le bouton est séléctionner et le rendre grisé si c'est la cas
					//ajouter le label(image) dans une liste de joueurs 
					switch (choixAven) {
					case 0:
						labelExplo.setIcon(imgIconAS);
						etatBoutonExplo = false;
						joueurs.add(labelExplo);
						break;
					case 1:
						labelInge.setIcon(imgIcon1AS);
						etatBoutonInge = false;
						joueurs.add(labelInge);
						break;
					case 2:
						labelMessa.setIcon(imgIcon2AS);
						etatBoutonMessa = false;
						joueurs.add(labelMessa);
						break;
					case 3:
						labelNavi.setIcon(imgIcon3AS);
						etatBoutonNavi = false;
						joueurs.add(labelNavi);
						break;
					case 4:
						labelPilo.setIcon(imgIcon4AS);
						etatBoutonPilo = false;
						joueurs.add(labelPilo);
						break;
					case 5:
						labelPlon.setIcon(imgIcon5AS);
						etatBoutonPlon = false;
						joueurs.add(labelPlon);
						break;               
					default:
						break;


					}
					// rendre le bouton de validation des choix non cliquable
					selectChoix.setEnabled(false);
				} 
				// tout les joueurs on choisis 
				if (choixCourant>nbJoueurs){
					//faire disparaitre le bouton de validation des choix
					selectChoix.setEnabled(false);
					selectChoix.setVisible(false);
					btnCommencer.setEnabled(true);
					labelJoueurCourrant.setText("");
					termine = true;
					
					


					//faire disparaitre les images de tout les aventurier 
					labelExplo.setVisible(false);
					labelInge.setVisible(false);
					labelMessa.setVisible(false);
					labelNavi.setVisible(false);
					labelPilo.setVisible(false);
					labelPlon.setVisible(false);
					
					//set l'image de base en couleur pour le prochain affichage
					labelExplo.setIcon(imgIcon);
					labelInge.setIcon(imgIcon1);
					labelMessa.setIcon(imgIcon2);
					labelNavi.setIcon(imgIcon3);
					labelPilo.setIcon(imgIcon4);
					labelPlon.setIcon(imgIcon5);
					labelExplo.setBackground(new Color(0, 0, 0, 0));
					int j = 0;
					
					// parcourir la liste de joueurs et rendre les images des aventurier dans la liste visible
					for (JLabel i : joueurs){
						Aventurier a;
						i.setVisible(true);
						
					}

				}

			}

		});


	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	//création d'un MessageInit qui permettre de donner au controleur les différentes
	//données choisis par l'utilisateur
	public MessageInit createMessageInit(){
		MessageInit m = new MessageInit();
		m.nbJoueurs = nbJoueurs;
		
		// initialisation de la difficulté grace au ComboBox 
		if (difficulte.getSelectedItem() == "Novice"){
			m.niveauEau =1;
		} else if  (difficulte.getSelectedItem() == "Normal"){
			m.niveauEau =2;
		} else if  (difficulte.getSelectedItem() == "Epique") {
			m.niveauEau =3;
		} else {
			m.niveauEau =4;
		}

		m.scenario=(String) combobox.getSelectedItem();
		
		//création et ajout des aventuriers sélectionnés dans la paramêtre listJoueur du MessageInit
		int j = 0;
		for (JLabel i : joueurs){
			Aventurier a;
			i.setVisible(true);
			if (i == labelExplo){
				a = new Explorateur(j,"Explorateur",Pion.VERT);
				m.listJoueurs.add(a);

			} else if (i == labelInge) {
				a= new Ingenieur(j,"Ingenieur",Pion.ROUGE);
				m.listJoueurs.add(a);

			} else if (i == labelMessa){
				a = new Messager(j,"Messager",Pion.ORANGE);
				m.listJoueurs.add(a);

			} else if (i == labelNavi){
				a = new Navigateur(j,"Navigateur",Pion.JAUNE);
				m.listJoueurs.add(a);

			} else if (i == labelPilo){
				a = new Aviateur(j,"Aviateur",Pion.BLEU);
				m.listJoueurs.add(a);

			} else if (i == labelPlon){
				a = new Plongeur(j,"Plongeur",Pion.VIOLET);
				m.listJoueurs.add(a);

			}
			j = j +1;
		}

		return m;
	}
}
