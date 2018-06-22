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
	    
	    ImageIcon imgIcon2 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messager.png");
	    ImageIcon imgIcon2S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messagerSelected.png");
	    ImageIcon imgIcon2AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/messageralreadySelected.png");
	    
	    ImageIcon imgIcon3 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateur.png");
	    ImageIcon imgIcon3S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateurSelected.png");
	    ImageIcon imgIcon3AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/navigateuralreadySelected.png");
	    
	    ImageIcon imgIcon4 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/aviateur.png");
	    ImageIcon imgIcon4S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/piloteSelected.png");
	    ImageIcon imgIcon4AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/pilotealreadySelected.png");
	    
	    ImageIcon imgIcon5 = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeur.png");
	    ImageIcon imgIcon5S = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeurSelected.png");
	    ImageIcon imgIcon5AS = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/persos/plongeuralreadySelected.png");
	    
	    
	moi = this;
    this.setTitle("Ile Interdite");

    this.setSize(800, 700);

    this.setLocationRelativeTo(null);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

    this.setVisible(true);
    
    this.setResizable(false);
    setBackground(new Color(139,69,18));
    
   
    
    JPanel pan = new JPanel();
    pan.setBackground(new Color(139,69,18));
    pan.setLayout(null);
    pan.setBounds(0, 0, 800, 700);
     
    ImageIcon imgIconTitre = new ImageIcon(System.getProperty("user.dir")+"\\src\\"+"images/titre.png");
    Font font = new Font("Serif", Font.BOLD, 25);

    
    ButtonGroup choix = new ButtonGroup();
        JLabel labelJoueurCourrant = new JLabel("");
        labelJoueurCourrant.setBounds(0, 0, 0, 0);
        pan.add(labelJoueurCourrant);

    
    this.setContentPane(pan);
    this.setVisible(true);
            
            PanelFont PanelFont = new PanelFont(1, true);
//            PanelFont.setBackground(new Color(139, 69, 19));
            PanelFont.setBounds(0, 0, 794, 665);
            pan.add(PanelFont);
            PanelFont.setLayout(null);
            
            
            JLabel labelTitre = new JLabel();
            labelTitre.setBounds(250, 42, 300, 100);
            PanelFont.add(labelTitre);
            labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
            labelTitre.setIcon(imgIconTitre);
            
            JRadioButton deuxJoueurs = new JRadioButton("2 joueurs");
            
            JRadioButton troisJoueurs = new JRadioButton("3 joueurs");
            
            JRadioButton quatreJoueurs = new JRadioButton("4 joueurs");
            
            PanelFont panel = new PanelFont(2,true);
            panel.setBounds(150, 155, 500, 200);
            PanelFont.add(panel);
            panel.setLayout(null);
            
            deuxJoueurs.setBounds(51, 82, 100, 25);
            panel.add(deuxJoueurs);
//            deuxJoueurs.setBackground(new Color(139,69,18));
            deuxJoueurs.setSelected(true);
            
            choix.add(deuxJoueurs);
            
            troisJoueurs.setBounds(199, 82, 100, 25);
            panel.add(troisJoueurs);
//            troisJoueurs.setBackground(new Color(139,69,18));
            choix.add(troisJoueurs);
            
            quatreJoueurs.setBounds(347, 82, 100, 25);
            panel.add(quatreJoueurs);
//            quatreJoueurs.setBackground(new Color(139,69,18));
            choix.add(quatreJoueurs);
            
                difficulte = new JComboBox();
                difficulte.setBounds(357, 116, 90, 22);
                panel.add(difficulte);
                difficulte.addItem("Novice");
                difficulte.addItem("Normal");
                difficulte.addItem("Elite");
                difficulte.addItem("Legendaire");
                
                    
                    JLabel labelDifficulte = new JLabel("Choisir la difficulte du jeu :");
                    labelDifficulte.setForeground(Color.WHITE);
                    labelDifficulte.setBounds(181, 119, 154, 16);
                    panel.add(labelDifficulte);
//                    labelDifficulte.setBackground(new Color(139,69,18));
                    JLabel labelChoix = new JLabel("Nombre de joueurs");
                    labelChoix.setBounds(145, 40, 210, 33);
                    panel.add(labelChoix);
                    labelChoix.setForeground(Color.WHITE);
                    labelChoix.setHorizontalAlignment(SwingConstants.CENTER);
                    labelChoix.setFont(font);
                    
                    JComboBox comboBox = new JComboBox();
                    comboBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9nario 1", "S\u00E9nario 2", "S\u00E9nario 3", "S\u00E9nario 4", "S\u00E9nario 5"}));
                    comboBox.setBounds(51, 116, 100, 22);
                    panel.add(comboBox);
                    
                    
                    
                    JButton selectChoix = new JButton("Confirmer");
                    selectChoix.setBounds(350, 384, 100, 25);
                    PanelFont.add(selectChoix);
                    
                    JButton btnRegle = new JButton("Regles");
                    btnRegle.setBounds(200, 600, 110, 25);
                    PanelFont.add(btnRegle);
                    
                    JButton btnCommencer = new JButton("Commencer");
                    btnCommencer.setBounds(490, 600, 110, 25);
                    PanelFont.add(btnCommencer);
                    
                    btnCommencer.setEnabled(false);
                    
                    

                    
                    
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

                        

                    labelExplo.addMouseListener(new MouseListener(){


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
                                        
                                    if (etatBoutonPlon){   
                                        labelPlon.setIcon(imgIcon5);
                                    }
                                        
                                                
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
                    btnRegle.addActionListener(new ActionListener(){
                    	
                    

@Override
public void actionPerformed(ActionEvent arg0) {
	new FrameHtml();
	
}
});
                    
                    
                    selectChoix.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
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
                                labelJoueurCourrant.setText("Joueur " + choixCourant);
                                selectChoix.setText("Valider");
                                selectChoix.setEnabled(false);


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
                                
                                choixCourant = choixCourant+1;
                                labelJoueurCourrant.setText("Joueur " + choixCourant);
                                selectChoix.setText("Valider");
                                
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
                                selectChoix.setEnabled(false);
                            } 
                            
                            if (choixCourant>nbJoueurs){
                                selectChoix.setEnabled(false);
                                selectChoix.setVisible(false);
                                btnCommencer.setEnabled(true);
                                labelJoueurCourrant.setText("");
                                termine = true;
                                MessageInit m = new MessageInit();
                                
                                
                                 
                                
                                
                                labelExplo.setVisible(false);
                                labelInge.setVisible(false);
                                labelMessa.setVisible(false);
                                labelNavi.setVisible(false);
                                labelPilo.setVisible(false);
                                labelPlon.setVisible(false);
                                
                                labelExplo.setIcon(imgIcon);
                                labelInge.setIcon(imgIcon1);
                                labelMessa.setIcon(imgIcon2);
                                labelNavi.setIcon(imgIcon3);
                                labelPilo.setIcon(imgIcon4);
                                labelPlon.setIcon(imgIcon5);
                                labelExplo.setBackground(new Color(0, 0, 0, 0));
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
                                
                            }
                            
                        }
                        
                    });
            
    
  }

	public int getNbJoueurs() {
		return nbJoueurs;
	}
        
	
	public MessageInit createMessageInit(){
		MessageInit m = new MessageInit();
		m.nbJoueurs = nbJoueurs;
        
        if (difficulte.getSelectedItem() == "Novice"){
        	m.niveauEau =1;
        } else if  (difficulte.getSelectedItem() == "Normal"){
        	m.niveauEau =2;
        } else if  (difficulte.getSelectedItem() == "Epique") {
        	m.niveauEau =3;
        } else {
        	m.niveauEau =4;
        }
		
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