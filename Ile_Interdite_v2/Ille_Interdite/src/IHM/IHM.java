package IHM;

import ille_intedite.Grille;
import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.VueGrille;
import utils.Utils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SpringLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class IHM extends Observe{
	//Com de debug 2
	private JFrame frame;
	private JTextArea console;
	private JPanel Plateau ;
	private JLabel lblJoeurN;


	HashMap<String,JButton> listButton = new HashMap();
	HashMap<String,JPanel> listPan = new HashMap();
	private JTextField textField;
	
	VueGrille vue;
	
	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					IHM window = new IHM();
	//					window.frame.setVisible(true);
	//
	//
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public IHM(VueGrille vue) {
		this.vue = vue;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		//Page principale
		JPanel Main = new JPanel();
		Main.setBackground(Color.YELLOW);
		frame.getContentPane().add(Main);
		Main.setLayout(new BorderLayout(0, 0));
		//Curseur
		JPanel cursor = new JPanel();
		cursor.setBackground(Color.PINK);
		Main.add(cursor, BorderLayout.WEST);
		cursor.setLayout(new BorderLayout(0, 0));

		JLabel textCusor = new JLabel("Monte \r\ndes eau :\r\n X");
		cursor.add(textCusor);

		JPanel aplication = new JPanel();
		aplication.setBackground(Color.GREEN);
		Main.add(aplication, BorderLayout.CENTER);
		aplication.setLayout(new BorderLayout(0, 0));

		JPanel up = new JPanel();
		aplication.add(up, BorderLayout.NORTH);

		lblJoeurN = new JLabel("Joeur n \u00B0 X");
		up.add(lblJoeurN);

		JPanel Center = new JPanel();
		aplication.add(Center, BorderLayout.CENTER);
		Center.setLayout(new GridLayout(2, 0, 0, 0));

		//Pannel de jeux 
		Plateau = new JPanel();
		Plateau .setLayout(new GridLayout(6,6,10,10));
		//fillPlataux();
		Utils.debugln("plateau done");

		Center.add(Plateau);




		JPanel Saisit = new JPanel();
		Center.add(Saisit);
		GridBagLayout gbl_Saisit = new GridBagLayout();
		gbl_Saisit.columnWidths = new int[]{0, 0};
		gbl_Saisit.rowHeights = new int[]{0, 0, 0};
		gbl_Saisit.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Saisit.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		Saisit.setLayout(gbl_Saisit);

		JPanel afichageConsole = new JPanel();
		GridBagConstraints gbc_afichageConsole = new GridBagConstraints();
		gbc_afichageConsole.insets = new Insets(0, 0, 5, 0);
		gbc_afichageConsole.fill = GridBagConstraints.BOTH;
		gbc_afichageConsole.gridx = 0;
		gbc_afichageConsole.gridy = 0;
		Saisit.add(afichageConsole, gbc_afichageConsole);
		afichageConsole.setLayout(new BorderLayout(0, 0));

		console = new JTextArea();
		console.setText("Console");
		console.setEditable(false);
		afichageConsole.add(console, BorderLayout.CENTER);

		JPanel bouton_et_autre = new JPanel();
		GridBagConstraints gbc_bouton_et_autre = new GridBagConstraints();
		gbc_bouton_et_autre.fill = GridBagConstraints.BOTH;
		gbc_bouton_et_autre.gridx = 0;
		gbc_bouton_et_autre.gridy = 1;
		Saisit.add(bouton_et_autre, gbc_bouton_et_autre);
		bouton_et_autre.setLayout(new BorderLayout(0, 0));

		JPanel bouton = new JPanel();
		bouton_et_autre.add(bouton, BorderLayout.CENTER);
		bouton.setLayout(new GridLayout(2, 2, 0, 0));

		JButton btnAction = new JButton("Deplace");
		btnAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Deplace);

				;

				notifierObservateur(m);
			}
		});
		bouton.add(btnAction);

		JButton btnAction_1 = new JButton("Assecher");
		btnAction_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Asseche);

				;

				notifierObservateur(m);
			}
		});
		bouton.add(btnAction_1);

		JButton btnAction_3 = new JButton("action 4");
		btnAction_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});
		bouton.add(btnAction_3);

		JButton btnAction_2 = new JButton("Fin de Tour");
		btnAction_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Fin_Tour);

				;

				notifierObservateur(m);
			}
		});
		bouton.add(btnAction_2);

		JPanel chamText = new JPanel();
		bouton_et_autre.add(chamText, BorderLayout.NORTH);
		chamText.setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		chamText.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message m =new Message(TypeMessage.Clique_Send);
				m.setText(textField.getText());
				notifierObservateur(m);
			}
		});
		chamText.add(send, BorderLayout.EAST);

		frame.setVisible(true);
	}

	public void fillPlataux(Grille g){

		HashMap<String, Tuile> tuilesListe = g.getTuilesListe();

		//int i = 0;

		Iterator<Tuile> it = g.getTuilesListe().values().iterator();
		int k = 0;
		String str;
		for(int y =0;y<6;y++){
			for(int x =0;x<6 ;x++){


				str = g.getTuile(x, y).getNom();

				JButton bt = new JButton(str);
				bt.setName(x+":"+y);
				bt.addActionListener(actionBoutonPlatau());


				listButton.put(x+":"+y, bt);

				Plateau.add(bt); 
			}
		}

		Plateau.revalidate();


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
					c.unActivated();
				}

				listPan.put(x+":"+y, c);

				Plateau.add(c); 
				k++;
			}
		}

		Plateau.revalidate();


	}



	private ActionListener actionBoutonPlatau(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Message m = new Message(TypeMessage.Clique_Tuille);

				JButton bt = (JButton) e.getSource();
				m.setLocation(bt.getName());
				notifierObservateur(m);
			}
		};
	}
	
	public void afficherDep(ArrayList<Tuile> listDep) {
		
		for(Tuile t : Grille.tuilesListe.values()) {
			if (!listDep.contains(t)) {
				t.getCase().unActivated();
			}else {
				t.getCase().activate();
			}
			
			
		}
		
	}

	//effece les message et ajoute un message 
	public void afichierConsole(String str){
		console.setText(str);
	}

	//Ajoute une message en plus des message aficher
	public void addConsole(String str) {
		console.setText(console.getText()+"\n"+str);
	}

	public void print(String str){
		console.setText(str);
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
		lblJoeurN.setText("Joeur n� "+(x+1)+str);
	}

	public void updateGrille() {
		Plateau.repaint();
		Plateau.revalidate();
	}

}
