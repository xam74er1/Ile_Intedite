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
		
		JPanel Sud1 = new JPanel();
		Sud1.setBackground(new Color(160, 82, 45));
		FlowLayout fl_Sud1 = (FlowLayout) Sud1.getLayout();
		fl_Sud1.setVgap(1);
		fl_Sud1.setHgap(2);
		panel.add(Sud1);
		
		JPanel panel_15 = new JPanel();
		Sud1.add(panel_15);
		panel_15.setBackground(new Color(160, 82, 45));
		
		JButton btnNewButton_2 = new JButton("D\u00E9placer");
		panel_15.add(btnNewButton_2);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(160, 82, 45));
		panel.add(panel_14);
		
		JButton btnNewButton_3 = new JButton("Donner Carte");
		panel_14.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(160, 82, 45));
		panel.add(panel_13);
		
		JButton btnNewButton_1 = new JButton("Ass\u00E9cher");
		panel_13.add(btnNewButton_1);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(160, 82, 45));
		panel.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(160, 82, 45));
		panel.add(panel_17);
		
		JButton btnNewButton = new JButton("Fin de Tour");
		panel.add(btnNewButton);
		
		JPanel EAST = new JPanel();
		EAST.setBackground(new Color(0, 0, 139));
		frame.getContentPane().add(EAST, BorderLayout.WEST);
		EAST.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider = new JSlider();
		slider.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		slider.setForeground(new Color(250, 235, 215));
		slider.setPaintLabels(true);
		slider.setBackground(new Color(160, 82, 45));
		slider.setPaintTicks(true);
		slider.setValue(5);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(10);
		slider.setOrientation(SwingConstants.VERTICAL);
		EAST.add(slider);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(139, 69, 19));
		panel_4.add(panel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 215, 0));
		panel_5.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(139, 69, 19));
		panel_4.add(panel_6);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);
		panel_9.setBackground(new Color(255, 215, 0));
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel_9.add(lblNewLabel_7);
		
		JPanel Carte4 = new JPanel();
		Carte4.setBackground(new Color(139, 69, 19));
		panel_4.add(Carte4);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(new Color(255, 215, 0));
		Carte4.add(panel_21);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_21.add(lblNewLabel);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(139, 69, 19));
		panel_4.add(panel_19);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(255, 215, 0));
		panel_19.add(panel_20);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_20.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(139, 69, 19));
		panel_3.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(139, 69, 19));
		panel_3.add(panel_12);
		
		JButton btnNewButton_4 = new JButton("Aide");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_3.add(btnNewButton_4);
		
		JPanel panel_18 = new JPanel();
		frame.getContentPane().add(panel_18, BorderLayout.EAST);
		panel_18.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		frame.getContentPane().add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.SOUTH);
		panel_10.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel panel_52 = new JPanel();
		panel_52.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_52);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		panel_52.add(lblNewLabel_12);
		
		JPanel panel_51 = new JPanel();
		panel_51.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_51);
		
		JPanel panel_53 = new JPanel();
		panel_51.add(panel_53);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel_53.add(lblNewLabel_6);
		
		JPanel panel_50 = new JPanel();
		panel_50.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_50);
		
		JPanel panel_54 = new JPanel();
		panel_50.add(panel_54);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel_54.add(lblNewLabel_8);
		
		JPanel panel_49 = new JPanel();
		panel_49.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_49);
		
		JPanel panel_55 = new JPanel();
		panel_49.add(panel_55);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		panel_55.add(lblNewLabel_9);
		
		JPanel panel_48 = new JPanel();
		panel_48.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_48);
		
		JPanel panel_56 = new JPanel();
		panel_48.add(panel_56);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		panel_56.add(lblNewLabel_10);
		
		JPanel panel_46 = new JPanel();
		panel_46.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_46);
		
		JPanel panel_57 = new JPanel();
		panel_46.add(panel_57);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		panel_57.add(lblNewLabel_11);
		
		JPanel panel_47 = new JPanel();
		panel_47.setBackground(new Color(173, 216, 230));
		panel_10.add(panel_47);
		
		JPanel panel_22 = new JPanel();
		panel_8.add(panel_22, BorderLayout.EAST);
		panel_22.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(new Color(160, 82, 45));
		panel_22.add(panel_23);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_23.add(lblNewLabel_2);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(new Color(160, 82, 45));
		panel_22.add(panel_25);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_25.add(lblNewLabel_3);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(new Color(160, 82, 45));
		panel_22.add(panel_24);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_24.add(lblNewLabel_4);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBackground(new Color(0, 0, 139));
		panel_8.add(panel_26, BorderLayout.CENTER);
		panel_26.setLayout(new BorderLayout(0, 0));
	}

}
