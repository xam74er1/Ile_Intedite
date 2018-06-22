package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ille_intedite.Curseur;

public class FenetreFin extends JFrame{
	private JLabel labelFin;
	public FenetreFin(MessageFinPartie msg){
		//Si le curseur jouais la musique de fin 
		Curseur.stopSound();
		if (msg.isVictoire()) {
			labelFin = new JLabel("Vous avez gagn� !");
			PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\YEAAAAH.wav");
			Font font = new Font("Serif", Font.BOLD, 25);
			labelFin.setFont(font);
		} else {
			labelFin = new JLabel("Vous avez perdu...");
			Font font = new Font("Serif", Font.BOLD, 25);
			labelFin.setFont(font);
			if(msg.getTypeDefaite().equalsIgnoreCase("L'ile a sombre completement..")) {

			}else if(msg.getTypeDefaite().equalsIgnoreCase("L'heliport a coule..")) {

			}else if(msg.getTypeDefaite().equalsIgnoreCase("Un des aventuriers s'est noye..")) {

			}
		}

		JLabel label = new JLabel(msg.getTypeDefaite());
		this.setTitle("Fin Ile Interdite");

		this.setSize(500, 500);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		this.setVisible(true);

		this.setResizable(false);
		setBackground(new Color(139,69,18));
		getContentPane().setLayout(null);



		PanelFont pan = new PanelFont(5,true);
		pan.setLayout(new GridLayout(2, 1));
		pan.setBounds(0, 0, 494, 465);
		getContentPane().add(pan);

		JPanel panHaut = new JPanel();
		panHaut.setBackground(new Color(0,0,0,0));
		JPanel panBas = new JPanel();
		panBas.setBackground(new Color(0,0,0,0));

		pan.add(panHaut);
		pan.add(panBas);

		panHaut.add(labelFin);
		panBas.add(label);

	}


}
