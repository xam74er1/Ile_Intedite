package IHM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class IHMV3 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHMV3 window = new IHMV3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IHMV3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JPanel south1 = new JPanel();
		south1.setBackground(new Color(160, 82, 45));
		FlowLayout fl_south1 = (FlowLayout) south1.getLayout();
		fl_south1.setVgap(1);
		fl_south1.setHgap(2);
		panel.add(south1);
		
		JPanel south11 = new JPanel();
		south1.add(south11);
		south11.setBackground(new Color(160, 82, 45));
		
		JButton btDeplacer = new JButton("Deplacer");
		south11.add(btDeplacer);
		
		JPanel south2 = new JPanel();
		south2.setBackground(new Color(160, 82, 45));
		panel.add(south2);
		
		JButton btDonnerCarte = new JButton("Donner Carte");
		south2.add(btDonnerCarte);
		btDonnerCarte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel south3 = new JPanel();
		south3.setBackground(new Color(160, 82, 45));
		panel.add(south3);
		
		JButton btAssecher = new JButton("Assecher");
		south3.add(btAssecher);
		
		JPanel south4 = new JPanel();
		south4.setBackground(new Color(160, 82, 45));
		panel.add(south4);
		
		JPanel south5 = new JPanel();
		south5.setBackground(new Color(160, 82, 45));
		panel.add(south5);
		
		JButton btFinDeTour = new JButton("Fin de Tour");
		panel.add(btFinDeTour);
		
		JPanel northP1 = new JPanel();
		frame.getContentPane().add(northP1, BorderLayout.NORTH);
		northP1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel northP2 = new JPanel();
		northP2.setBackground(Color.YELLOW);
		northP1.add(northP2);
		northP2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel northP3 = new JPanel();
		northP2.add(northP3);
		northP3.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel northP4 = new JPanel();
		northP4.setBackground(new Color(139, 69, 19));
		northP3.add(northP4);
		
		JPanel northP41 = new JPanel();
		northP41.setBackground(new Color(255, 215, 0));
		northP4.add(northP41);
		
		JLabel labelImgTresor1 = new JLabel("New label");
		northP41.add(labelImgTresor1);
		
		JPanel northP5 = new JPanel();
		northP5.setBackground(new Color(139, 69, 19));
		northP3.add(northP5);
		
		JPanel northP51 = new JPanel();
		northP5.add(northP51);
		northP51.setBackground(new Color(255, 215, 0));
		
		JLabel labelImgTresor2 = new JLabel("New label");
		northP51.add(labelImgTresor2);
		
		JPanel northP6 = new JPanel();
		northP6.setBackground(new Color(139, 69, 19));
		northP3.add(northP6);
		
		JPanel northP61 = new JPanel();
		northP61.setBackground(new Color(255, 215, 0));
		northP6.add(northP61);
		
		JLabel labelImgTresor3 = new JLabel("New label");
		northP61.add(labelImgTresor3);
		
		JPanel northP7 = new JPanel();
		northP7.setBackground(new Color(139, 69, 19));
		northP3.add(northP7);
		
		JPanel northP71 = new JPanel();
		northP71.setBackground(new Color(255, 215, 0));
		northP7.add(northP71);
		
		JLabel labelImgTresor4 = new JLabel("New label");
		northP71.add(labelImgTresor4);
		
		JPanel northP8 = new JPanel();
		northP1.add(northP8);
		northP8.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel northP81 = new JPanel();
		northP81.setBackground(new Color(139, 69, 19));
		northP8.add(northP81);
		
		JPanel northP82 = new JPanel();
		northP82.setBackground(new Color(139, 69, 19));
		northP8.add(northP82);
		
		JButton btAide = new JButton("Aide");
		btAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		northP8.add(btAide);
		
		JPanel CenterP1 = new JPanel();
		frame.getContentPane().add(CenterP1, BorderLayout.CENTER);
		CenterP1.setLayout(new BorderLayout(0, 0));
		
		JPanel CenterSouthP0 = new JPanel();
		CenterP1.add(CenterSouthP0, BorderLayout.SOUTH);
		CenterSouthP0.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel CenterSouthP1 = new JPanel();
		CenterSouthP1.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP1);
		
		JLabel labelImageJcourant = new JLabel("New label");
		CenterSouthP1.add(labelImageJcourant);
		
		JPanel CenterSouthP2 = new JPanel();
		CenterSouthP2.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP2);
		
		JPanel CenterSouthP21 = new JPanel();
		CenterSouthP2.add(CenterSouthP21);
		
		JLabel labelCarte1 = new JLabel("New label");
		CenterSouthP21.add(labelCarte1);
		
		JPanel CenterSouthP3 = new JPanel();
		CenterSouthP3.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP3);
		
		JPanel CenterSouthP31 = new JPanel();
		CenterSouthP3.add(CenterSouthP31);
		
		JLabel labelCarte2 = new JLabel("New label");
		CenterSouthP31.add(labelCarte2);
		
		JPanel CenterSouthP4 = new JPanel();
		CenterSouthP4.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP4);
		
		JPanel CenterSouthP41 = new JPanel();
		CenterSouthP4.add(CenterSouthP41);
		
		JLabel labelCarte3 = new JLabel("New label");
		CenterSouthP41.add(labelCarte3);
		
		JPanel CenterSouthP5 = new JPanel();
		CenterSouthP5.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP5);
		
		JPanel CenterSouthP51 = new JPanel();
		CenterSouthP5.add(CenterSouthP51);
		
		JLabel labelCarte4 = new JLabel("New label");
		CenterSouthP51.add(labelCarte4);
		
		JPanel CenterSouthP6 = new JPanel();
		CenterSouthP6.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP6);
		
		JPanel CenterSouthP61 = new JPanel();
		CenterSouthP6.add(CenterSouthP61);
		
		JLabel labelCarte5 = new JLabel("New label");
		CenterSouthP61.add(labelCarte5);
		
		JPanel CenterSouthP7 = new JPanel();
		CenterSouthP7.setBackground(new Color(173, 216, 230));
		CenterSouthP0.add(CenterSouthP7);
		
		JPanel CenterEastP0 = new JPanel();
		CenterP1.add(CenterEastP0, BorderLayout.EAST);
		CenterEastP0.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel CenterEastP1 = new JPanel();
		CenterEastP1.setBackground(new Color(160, 82, 45));
		CenterEastP0.add(CenterEastP1);
		
		JLabel labelImgJoueur4 = new JLabel("New label");
		labelImgJoueur4.setHorizontalAlignment(SwingConstants.CENTER);
		CenterEastP1.add(labelImgJoueur4);
		
		JPanel CenterEastP2 = new JPanel();
		CenterEastP2.setBackground(new Color(160, 82, 45));
		CenterEastP0.add(CenterEastP2);
		
		JLabel labelImgJoueur3 = new JLabel("New label");
		labelImgJoueur3.setHorizontalAlignment(SwingConstants.CENTER);
		CenterEastP2.add(labelImgJoueur3);
		
		JPanel CenterEastP3 = new JPanel();
		CenterEastP3.setBackground(new Color(160, 82, 45));
		CenterEastP0.add(CenterEastP3);
		
		JLabel labelImgJoueur2 = new JLabel("New label");
		labelImgJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
		CenterEastP3.add(labelImgJoueur2);
		
		JPanel CenterWestP0 = new JPanel();
		CenterWestP0.setBackground(new Color(0, 0, 139));
		CenterP1.add(CenterWestP0, BorderLayout.CENTER);
		CenterWestP0.setLayout(new BorderLayout(0, 0));
		
		JSlider slider = new JSlider();
		CenterWestP0.add(slider, BorderLayout.WEST);
		slider.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		slider.setForeground(new Color(250, 235, 215));
		slider.setPaintLabels(true);
		slider.setBackground(new Color(160, 82, 45));
		slider.setPaintTicks(true);
		slider.setValue(5);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(10);
		slider.setOrientation(SwingConstants.VERTICAL);
	}

}
