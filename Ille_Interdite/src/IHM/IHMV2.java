package IHM;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

public class IHMV2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHMV2 window = new IHMV2();
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
	public IHMV2() {
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
		slider.setBounds(12, 13, 76, 647);
		SliderPanel.add(slider);
		slider.setOrientation(SwingConstants.VERTICAL);
		
		JPanel CardPlayer = new JPanel();
		CardPlayer.setBackground(new Color(139, 69, 19));
		CardPlayer.setBounds(112, 13, 250, 597);
		frame.getContentPane().add(CardPlayer);
		CardPlayer.setLayout(null);
		
		ImageIcon imgCarte1 = new ImageIcon("images/cartes/Calice.png");
		Image im = imgCarte1.getImage();
		im = im.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img1 = new ImageIcon(im);
		
		JPanel panelCarte1 = new JPanel();
		panelCarte1.setBackground(new Color(139, 69, 19));
		panelCarte1.setBounds(12, 13, 100, 140);
		CardPlayer.add(panelCarte1);
		panelCarte1.setLayout(null);
		
		JLabel lbCarteIcon1 = new JLabel("");
		lbCarteIcon1.setBounds(0, 0, 100, 140);
		panelCarte1.add(lbCarteIcon1);
		lbCarteIcon1.setIcon(img1);
		
		
		
		JPanel panelCarte2 = new JPanel();
		panelCarte2.setBackground(new Color(139, 69, 19));
		panelCarte2.setBounds(138, 13, 100, 140);
		CardPlayer.add(panelCarte2);
		panelCarte2.setLayout(null);
		
		JLabel lbCarteIcon2 = new JLabel("");
		lbCarteIcon2.setBackground(new Color(139, 69, 19));
		lbCarteIcon2.setBounds(0, 0, 100, 140);
		panelCarte2.add(lbCarteIcon2);
		Icon imgCarte2 = new ImageIcon("images/cartes/Calice.png");
		Image im2 = imgCarte1.getImage();
		im2 = im2.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(im2);
		lbCarteIcon2.setIcon(img2);
		
		JPanel panelCarte3 = new JPanel();
		panelCarte3.setBackground(new Color(139, 69, 19));
		panelCarte3.setBounds(12, 166, 100, 140);
		CardPlayer.add(panelCarte3);
		panelCarte3.setLayout(null);
		
		JLabel lbCarteIcon3 = new JLabel("");
		lbCarteIcon3.setBounds(0, 0, 100, 140);
		panelCarte3.add(lbCarteIcon3);
		Icon imgCarte3 = new ImageIcon("images/cartes/Calice.png");
		Image im3 = imgCarte1.getImage();
		im3 = im3.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(im3);
		lbCarteIcon3.setIcon(img3);
		
		JPanel panelCarte4 = new JPanel();
		panelCarte4.setBackground(new Color(139, 69, 19));
		panelCarte4.setBounds(138, 166, 100, 140);
		CardPlayer.add(panelCarte4);
		panelCarte4.setLayout(null);
		
		JLabel lbCarteIcon4 = new JLabel("");
		lbCarteIcon4.setBackground(new Color(139, 69, 19));
		lbCarteIcon4.setBounds(0, 0, 100, 140);
		panelCarte4.add(lbCarteIcon4);
		Icon imgCarte4 = new ImageIcon("images/cartes/Calice.png");
		Image im4 = imgCarte1.getImage();
		im4 = im4.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img4 = new ImageIcon(im4);
		lbCarteIcon4.setIcon(img4);
		
		JPanel panelCarte5 = new JPanel();
		panelCarte5.setBackground(new Color(139, 69, 19));
		panelCarte5.setBounds(75, 319, 100, 140);
		CardPlayer.add(panelCarte5);
		panelCarte5.setLayout(null);
		
		JLabel lbCarteIcon5 = new JLabel("");
		lbCarteIcon5.setBackground(new Color(51, 0, 0));
		lbCarteIcon5.setBounds(0, 0, 100, 140);
		panelCarte5.add(lbCarteIcon5);
		Icon imgCarte5 = new ImageIcon("images/cartes/Calice.png");
		Image im5 = imgCarte1.getImage();
		im5 = im5.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img5 = new ImageIcon(im5);
		lbCarteIcon5.setIcon(img5);
		
		JLabel lbIconJoueur = new JLabel("");
		lbIconJoueur.setBounds(75, 472, 100, 100);
		CardPlayer.add(lbIconJoueur);
		Icon img = new ImageIcon("images/persos/explorateur.png");
		lbIconJoueur.setIcon(img);
		
		
		JPanel PanelSouth = new JPanel();
		PanelSouth.setBackground(new Color(139, 69, 19));
		PanelSouth.setBounds(97, 623, 1165, 50);
		frame.getContentPane().add(PanelSouth);
		PanelSouth.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(12, 13, 97, 25);
		PanelSouth.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(121, 13, 97, 25);
		PanelSouth.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(230, 13, 97, 25);
		PanelSouth.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(339, 13, 97, 25);
		PanelSouth.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(1056, 13, 97, 25);
		PanelSouth.add(btnNewButton_4);
		
		JPanel PanelGrid = new JPanel();
		PanelGrid.setBackground(new Color(160, 82, 45));
		PanelGrid.setBounds(374, 13, 597, 597);
		frame.getContentPane().add(PanelGrid);
		
		JPanel PanelEast = new JPanel();
		PanelEast.setBackground(new Color(139, 69, 19));
		PanelEast.setBounds(983, 13, 267, 597);
		frame.getContentPane().add(PanelEast);
		PanelEast.setLayout(null);
		
		JPanel PanelPlayerIn2T = new JPanel();
		PanelPlayerIn2T.setBounds(84, 284, 100, 100);
		PanelEast.add(PanelPlayerIn2T);
		
		JPanel PanelPlayerIn3T = new JPanel();
		PanelPlayerIn3T.setBounds(84, 84, 100, 100);
		PanelEast.add(PanelPlayerIn3T);
		
		JPanel PanelPlayerIn1T = new JPanel();
		PanelPlayerIn1T.setBounds(84, 484, 100, 100);
		PanelEast.add(PanelPlayerIn1T);
		
		JPanel PanelHelp = new JPanel();
		PanelHelp.setBounds(0, 0, 267, 40);
		PanelEast.add(PanelHelp);
		
		JButton btAide = new JButton("Aide");
		PanelHelp.add(btAide);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
