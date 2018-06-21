package IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.*;

public class FrameHtml extends JFrame {

	private JPanel contentPane;
	private static int num = 0;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public FrameHtml() {

		
		CardLayout cl = new CardLayout();

		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Main = new JPanel();
		contentPane.add(Main, BorderLayout.CENTER);
		Main.setLayout(cl);


		System.out.println(System.getProperty("user.dir"));
		JScrollPane page1 = new JScrollPane( new htmlPan(System.getProperty("user.dir")+"\\src\\"+"images\\Help\\page1.html",System.getProperty("user.dir")+"\\src\\"+"images\\Help\\Fond2.jpg"));
		page1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		Main.add(page1, "page1");
	
		JScrollPane page2 =new JScrollPane( new htmlPan(System.getProperty("user.dir")+"\\src\\"+"images\\Help\\page2.html",System.getProperty("user.dir")+"\\src\\"+"images\\Help\\Fond2.jpg"));
		page2.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		Main.add(page2, "page2");

		JScrollPane page3 = new JScrollPane(new htmlPan(System.getProperty("user.dir")+"\\src\\"+"images\\Help\\page3.html",System.getProperty("user.dir")+"\\src\\"+"images\\Help\\Fond2.jpg"));
	page3.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		Main.add(page3, "page3");

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton precedent = new JButton("<precedent");
		precedent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){


				cl.previous(Main);
			}
		});
		panel.add(precedent);

		JButton suivant = new JButton("suivant>");
		suivant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//Via cette instruction, on passe au prochain conteneur de la pile
				cl.next(Main);
			}
		});
		panel.add(suivant);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		this.setVisible(true);
//		this.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//               System.out.println("lol");
//            }
//        });
		
	}

	public void setPan() {
		JPanel pan = new JPanel();
	}

	
}
