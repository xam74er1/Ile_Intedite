package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ille_intedite.Curseur;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class FenetreFin extends JFrame{

	private JLabel labelFin;
	private JFrame moi = this;
	public static int nbFen=0;
	

	public FenetreFin(MessageFinPartie msg){
		//Si le curseur jouais la musique de fin 
		nbFen++;
		Curseur.stopSound();
		this.setTitle("Fin Ile Interdite");

		this.setSize(500, 500);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		this.setVisible(true);

		this.setResizable(false);
		setBackground(new Color(139,69,18));
		getContentPane().setLayout(null);



		PanelFont pan = new PanelFont(5,true);
		pan.setBackground(Color.WHITE);
		pan.setBounds(0, 0, 494, 465);
		getContentPane().add(pan);
		pan.setLayout(null);
		
				JLabel labelFin = new JLabel("");
				labelFin.setForeground(Color.WHITE);
				labelFin.setHorizontalAlignment(SwingConstants.CENTER);
				labelFin.setBounds(0, 100, 500, 40);
				pan.add(labelFin);
				
				JLabel labelMort = new JLabel("");
				labelMort.setForeground(Color.WHITE);
				labelMort.setHorizontalAlignment(SwingConstants.CENTER);
				labelMort.setBounds(0, 179, 500, 40);
				labelMort.setFont(new Font("Serif", Font.BOLD, 25));
				pan.add(labelMort);
		if (msg.isVictoire()) {
			labelFin.setText("Vous avez gagn� !");
			PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\YEAAAAH.wav");
			Font font = new Font("Serif", Font.BOLD, 25);
			labelFin.setFont(font);
		} else {
			labelFin .setText("Vous avez perdu...");
			Font font = new Font("Serif", Font.BOLD, 25);
			labelFin.setFont(font);
			
			labelMort.setText(msg.typeDefaite);
			switch(msg.typeDefaite) {
			case "Un des aventuriers s'est noy\u00E9.." :
				PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\Darkness.wav");
				break;
			case "D\\u00E9faite curseur" : break;
			
			case "D\\u00E9faite heliport" : break;
			
			case "D\\u00E9faite tr\\u00E9sor" : break;
			}
	
		}
		

	}
}
