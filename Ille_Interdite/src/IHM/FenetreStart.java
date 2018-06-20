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

import ille_intedite.Aventurie.Aventurier;
import ille_intedite.Aventurie.Aviateur;
import ille_intedite.Aventurie.Explorateur;
import ille_intedite.Aventurie.Ingenieur;
import ille_intedite.Aventurie.Messager;
import ille_intedite.Aventurie.Navigateur;
import ille_intedite.Aventurie.Plongeur;
import utils.Utils.Pion;

 

public class FenetreStart extends JFrame {
	
	private int nbJoueurs;
	private HashMap<Integer,String> listJoueurs = new HashMap();
        
		
	
	
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

    this.setTitle("Ile Interdite");

    this.setSize(800, 700);

    this.setLocationRelativeTo(null);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

    this.setVisible(true);
    
    this.setResizable(false);
    setBackground(new Color(139,69,18));
    
    JPanel pan = new JPanel();
    pan.setBackground(new Color(139,69,18));
    pan.setLayout(new GridLayout(4,1));
    
    
    JLabel labelTitre = new JLabel();
    JPanel panTitre = new JPanel();
     
    ImageIcon imgIconTitre = new ImageIcon("images/titre.png");
    labelTitre.setIcon(imgIconTitre);
    panTitre.add(labelTitre);
    panTitre.setBackground(new Color(139,69,18));
    JLabel labelChoix = new JLabel("Nombre de joueurs");
    Font font = new Font("Serif", Font.BOLD, 25);
    labelChoix.setFont(font);
    JPanel panChoixTexte = new JPanel();
    panChoixTexte.setBackground(new Color(139,69,18));
    panChoixTexte.add(labelChoix);

    
    ButtonGroup choix = new ButtonGroup();
    JRadioButton deuxJoueurs = new JRadioButton("2 joueurs");
    deuxJoueurs.setBackground(new Color(139,69,18));
    deuxJoueurs.setSelected(true);
    JRadioButton troisJoueurs = new JRadioButton("3 joueurs");
    troisJoueurs.setBackground(new Color(139,69,18));
    JRadioButton quatreJoueurs = new JRadioButton("4 joueurs");
    quatreJoueurs.setBackground(new Color(139,69,18));

    JPanel panChoix = new JPanel();
    panChoix.setBackground(new Color(139,69,18));
    panChoix.setBackground(new Color(139,69,18));
    
    JPanel panChoixDifficult = new JPanel();
    panChoixDifficult.setBackground(new Color(139,69,18));

    
    JLabel labelDifficulte = new JLabel("Choisir la difficulté du jeu :");
    labelDifficulte.setBackground(new Color(139,69,18));

    JComboBox difficulte = new JComboBox();
    difficulte.addItem("Novice");
    difficulte.addItem("Normal");
    difficulte.addItem("Elite");
    difficulte.addItem("Légendaire");

    
    panChoixDifficult.add(labelDifficulte);
    panChoixDifficult.add(difficulte);

    
    
    
    JPanel choixTout = new JPanel();
    choixTout.setBackground(new Color(139,69,18));
    choixTout.setLayout(new GridLayout(4,1));
    choixTout.add(panChoixTexte);
    choixTout.add(panChoix);
    choixTout.add(panChoixDifficult);
    
    choix.add(deuxJoueurs);
    choix.add(troisJoueurs);
    choix.add(quatreJoueurs);
    
    pan.add(panTitre);
    pan.add(choixTout);
    panChoix.add(deuxJoueurs);
    panChoix.add(troisJoueurs);
    panChoix.add(quatreJoueurs);
    JPanel panSelectChoix = new JPanel();
    JButton selectChoix = new JButton("Comfirmer");
    
    
    panSelectChoix.setBackground(new Color(139,69,18));
    panSelectChoix.add(selectChoix);
    
    choixTout.add(panSelectChoix);
    
    

    
    
    JPanel panChoixCarte = new JPanel();
    panChoixCarte.setBackground(new Color(139,69,18));
    JLabel labelExplo = new JLabel();
    JLabel labelInge = new JLabel();
    JLabel labelMessa = new JLabel();
    JLabel labelNavi = new JLabel();
    JLabel labelPilo = new JLabel();
    JLabel labelPlon = new JLabel();
    
    ImageIcon imgIcon = new ImageIcon("images/persos/explorateur.png");
    ImageIcon imgIconS = new ImageIcon("images/persos/explorateurSelected.png");
    ImageIcon imgIconAS = new ImageIcon("images/persos/explorateurAlreadySelected.png");

    labelExplo.setIcon(imgIconAS);
    panChoixCarte.add(labelExplo);
    
    ImageIcon imgIcon1 = new ImageIcon("images/persos/ingenieur.png");
    ImageIcon imgIcon1S = new ImageIcon("images/persos/ingenieurSelected.png");
    ImageIcon imgIcon1AS = new ImageIcon("images/persos/ingenieuralreadySelected.png");
    
    labelInge.setIcon(imgIcon1AS);
    panChoixCarte.add(labelInge);
    
    ImageIcon imgIcon2 = new ImageIcon("images/persos/messager.png");
    ImageIcon imgIcon2S = new ImageIcon("images/persos/messagerSelected.png");
    ImageIcon imgIcon2AS = new ImageIcon("images/persos/messageralreadySelected.png");
    
    labelMessa.setIcon(imgIcon2AS);
    panChoixCarte.add(labelMessa);
    
    ImageIcon imgIcon3 = new ImageIcon("images/persos/navigateur.png");
    ImageIcon imgIcon3S = new ImageIcon("images/persos/navigateurSelected.png");
    ImageIcon imgIcon3AS = new ImageIcon("images/persos/navigateuralreadySelected.png");
    
    labelNavi.setIcon(imgIcon3AS);
    panChoixCarte.add(labelNavi);
    
    ImageIcon imgIcon4 = new ImageIcon("images/persos/aviateur.png");
    ImageIcon imgIcon4S = new ImageIcon("images/persos/piloteSelected.png");
    ImageIcon imgIcon4AS = new ImageIcon("images/persos/pilotealreadySelected.png");
    
    labelPilo.setIcon(imgIcon4AS);
    panChoixCarte.add(labelPilo);

    ImageIcon imgIcon5 = new ImageIcon("images/persos/plongeur.png");
    ImageIcon imgIcon5S = new ImageIcon("images/persos/plongeurSelected.png");
    ImageIcon imgIcon5AS = new ImageIcon("images/persos/plongeuralreadySelected.png");
    
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
    
    
    
    pan.add(panChoixCarte);
    

    JButton btnRegle = new JButton("Regles");
    JButton btnCommencer = new JButton("Commencer");
    
    JPanel panBas = new JPanel();
    panBas.setBackground(new Color(139,69,18));
    panBas.add(btnRegle);
    panBas.add(btnCommencer);
    
    pan.add(panBas);

    
    this.setContentPane(pan);
    this.setVisible(true);
    
    btnCommencer.setEnabled(false);
    
    selectChoix.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if ("Comfirmer".equals(selectChoix.getText())){

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
                selectChoix.setText("Choix joueur " + choixCourant);
                
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
                selectChoix.setText("Choix joueur " +choixCourant);
                
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
            } 
            
            if (choixCourant>nbJoueurs){
                selectChoix.setEnabled(false);
                selectChoix.setVisible(false);
                btnCommencer.setEnabled(true);
                termine = true;
                MessageInit m = new MessageInit();
                
                m.nbJoueurs = nbJoueurs;
                
                if (difficulte.getSelectedItem() == "Novice"){
                	m.niveauEau =0;
                } else if  (difficulte.getSelectedItem() == "Normal"){
                	m.niveauEau =2;
                } else if  (difficulte.getSelectedItem() == "Epique") {
                	m.niveauEau =5;
                } else if  (difficulte.getSelectedItem() == "Légendaire") {
                	m.niveauEau =7;
                }
                 
                
                
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
                
                for (JLabel i : joueurs){
                	int j = 0;
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
                    
                }
                
            }
            
        }
        
    });
    
  }

	public int getNbJoueurs() {
		return nbJoueurs;
	}
        
	

	public static void main(String[] args){
		FenetreStart f = new FenetreStart();
	}



}