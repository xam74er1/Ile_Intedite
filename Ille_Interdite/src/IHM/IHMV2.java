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
				
		JPanel panelCarte1 = new JPanel();
		panelCarte1.setBackground(new Color(139, 69, 19));
		panelCarte1.setBounds(12, 13, 100, 140);
		CardPlayer.add(panelCarte1);
		panelCarte1.setLayout(null);
		
		JButton btnCarteIcon1 = new JButton("");
		btnCarteIcon1.setForeground(new Color(139, 69, 19));
		btnCarteIcon1.setBackground(new Color(139, 69, 19));
		btnCarteIcon1.setBounds(0, 0, 100, 140);
		ImageIcon imgCarte1 = new ImageIcon("images/cartes/Calice.png");
		Image im = imgCarte1.getImage();
		im = im.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img1 = new ImageIcon(im);
		btnCarteIcon1.setIcon(img1);
		panelCarte1.add(btnCarteIcon1);
		
		JPanel panelCarte2 = new JPanel();
		panelCarte2.setBackground(new Color(139, 69, 19));
		panelCarte2.setBounds(138, 13, 100, 140);
		CardPlayer.add(panelCarte2);
		panelCarte2.setLayout(null);
		
		JButton btnCarteIcon2 = new JButton("");
		btnCarteIcon2.setForeground(new Color(139, 69, 19));
		btnCarteIcon2.setBackground(new Color(139, 69, 19));
		btnCarteIcon2.setBounds(0, 0, 100, 140);
		panelCarte2.add(btnCarteIcon2);
		ImageIcon imgCarte2 = new ImageIcon("images/cartes/SacsDeSable.png");
		Image im2 = imgCarte2.getImage();
		im2 = im2.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(im2);
		btnCarteIcon2.setIcon(img2);
		
		JPanel panelCarte3 = new JPanel();
		panelCarte3.setBackground(new Color(139, 69, 19));
		panelCarte3.setBounds(12, 166, 100, 140);
		CardPlayer.add(panelCarte3);
		panelCarte3.setLayout(null);
		
		JButton btnCarteIcon3 = new JButton("");
		btnCarteIcon3.setForeground(new Color(139, 69, 19));
		btnCarteIcon3.setBackground(new Color(139, 69, 19));
		btnCarteIcon3.setBounds(0, 0, 100, 140);
		panelCarte3.add(btnCarteIcon3);
		ImageIcon imgCarte3 = new ImageIcon("images/cartes/Calice.png");
		Image im3 = imgCarte3.getImage();
		im3 = im3.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(im3);
		btnCarteIcon3.setIcon(img3);
		
		JPanel panelCarte4 = new JPanel();
		panelCarte4.setBackground(new Color(139, 69, 19));
		panelCarte4.setBounds(138, 166, 100, 140);
		CardPlayer.add(panelCarte4);
		panelCarte4.setLayout(null);
		
		JButton btnCarteIcon4 = new JButton("");
		btnCarteIcon4.setForeground(new Color(139, 69, 19));
		btnCarteIcon4.setBackground(new Color(139, 69, 19));
		btnCarteIcon4.setBounds(0, 0, 100, 140);
		panelCarte4.add(btnCarteIcon4);
		ImageIcon imgCarte4 = new ImageIcon("images/cartes/Cristal.png");
		Image im4 = imgCarte4.getImage();
		im4 = im4.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img4 = new ImageIcon(im4);
		btnCarteIcon4.setIcon(img4);
		
		JPanel panelCarte5 = new JPanel();
		panelCarte5.setBackground(new Color(139, 69, 19));
		panelCarte5.setBounds(75, 319, 100, 140);
		CardPlayer.add(panelCarte5);
		panelCarte5.setLayout(null);
		
		JButton btnCarteIcon5 = new JButton("");
		btnCarteIcon5.setForeground(new Color(139, 69, 19));
		btnCarteIcon5.setBackground(new Color(139, 69, 19));
		btnCarteIcon5.setBounds(0, 0, 100, 140);
		panelCarte5.add(btnCarteIcon5);
		ImageIcon imgCarte5 = new ImageIcon("images/cartes/Pierre.png");
		Image im5 = imgCarte5.getImage();
		im5 = im5.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
		ImageIcon img5 = new ImageIcon(im5);
		btnCarteIcon5.setIcon(img5);
		
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
		PanelGrid.setLayout(null);
		
		JPanel PanelEast = new JPanel();
		PanelEast.setBackground(new Color(139, 69, 19));
		PanelEast.setBounds(983, 13, 267, 597);
		frame.getContentPane().add(PanelEast);
		PanelEast.setLayout(null);
		
		JPanel PanelPlayerIn2T = new JPanel();
		PanelPlayerIn2T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn2T.setBounds(59, 258, 150, 150);
		PanelEast.add(PanelPlayerIn2T);
		PanelPlayerIn2T.setLayout(null);
		
		
		JButton btnImgPlayerIn2T = new JButton("");
		btnImgPlayerIn2T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn2T.setBounds(0, 0, 150, 150);
		PanelPlayerIn2T.add(btnImgPlayerIn2T);
		ImageIcon imgPlayerIn2T = new ImageIcon("images/persos/ingenieur.png");
		Image imP2T = imgPlayerIn2T.getImage();
		imP2T = imP2T.getScaledInstance(120, 150, Image.SCALE_DEFAULT);
		ImageIcon imgP2T = new ImageIcon(imP2T);
		btnImgPlayerIn2T.setIcon(imgP2T);
		btnImgPlayerIn2T.setBorder(null);
		
		
		JPanel PanelPlayerIn3T = new JPanel();
		PanelPlayerIn3T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn3T.setBounds(59, 84, 150, 150);
		PanelEast.add(PanelPlayerIn3T);
		PanelPlayerIn3T.setLayout(null);
		
		JButton btnImgPlayerIn3T = new JButton("");
		btnImgPlayerIn3T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn3T.setBounds(0, 0, 150, 150);
		PanelPlayerIn3T.add(btnImgPlayerIn3T);
		ImageIcon imgPlayerIn3T = new ImageIcon("images/persos/pilote.png");
		Image imP3T = imgPlayerIn3T.getImage();
		imP3T = imP3T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon imgP3T = new ImageIcon(imP3T);
		btnImgPlayerIn3T.setIcon(imgP3T);
		btnImgPlayerIn3T.setBorder(null);
		
		JPanel PanelPlayerIn1T = new JPanel();
		PanelPlayerIn1T.setBackground(new Color(139, 69, 19));
		PanelPlayerIn1T.setBounds(59, 434, 150, 150);
		PanelEast.add(PanelPlayerIn1T);
		PanelPlayerIn1T.setLayout(null);
		
		JButton btnImgPlayerIn1T = new JButton("");
		btnImgPlayerIn1T.setForeground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBackground(new Color(139, 69, 19));
		btnImgPlayerIn1T.setBounds(0, 0, 150, 150);
		PanelPlayerIn1T.add(btnImgPlayerIn1T);
		ImageIcon imgPlayerIn1T = new ImageIcon("images/persos/plongeur.png");
		Image imP1T = imgPlayerIn1T.getImage();
		imP1T = imP1T.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon imgP1T = new ImageIcon(imP1T);
		btnImgPlayerIn1T.setIcon(imgP1T);
		btnImgPlayerIn1T.setBorder(null);
		
		JPanel PanelHelp = new JPanel();
		PanelHelp.setBounds(207, 0, 60, 60);
		PanelEast.add(PanelHelp);
		
		JButton btAide = new JButton("Aide");
		PanelHelp.add(btAide);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
