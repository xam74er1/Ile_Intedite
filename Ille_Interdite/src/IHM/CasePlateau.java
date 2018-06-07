package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.Tuile;
import ille_intedite.TypeMessage;
import ille_intedite.Aventurie.Aventurier;

public class CasePlateau extends JPanel  {
//Com de referencement
	private String str,location;
	private Observe o;
	private Tuile t;
	private boolean activated = false;
	private MouseListener m;
	private Color c = new Color(204, 102, 0);

	public CasePlateau(String str,String location, Observe o,Tuile t) {

		this.str = str;
		this.o = o;
		this.t = t;
		this.location = location;

		m=mouse();		
		this.addMouseListener(m);
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		add(lblNewLabel);
		setBackground(Color.gray);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public void paintComponent(Graphics g){
		int x = (int) (this.getHeight()*0.1);
		int y = (int) (this.getWidth()*0.05);
		int s = x;
		int xa = x;
		int ya = y;
		
		if(activated) {
			g.setColor(c);

			g.fillRect(0, 0,  this.getWidth(),this.getHeight());
		}

		for(Aventurier a : t.getAventurie()) {
			g.setColor(a.getColor());
			g.fillOval(xa, ya, xa+s, ya+s);
			xa += 2*s;

		}

		
		
	}
	
	public MouseListener mouse() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub


			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println(location);
				Message m = new Message(TypeMessage.Clique_Tuille);
				m.setLocation(location);
				o.notifierObservateur(m);



			}
		};
		
	}



	public void unActivated() {
		this.removeMouseListener(m);
		activated = false;
	}
	
	public void activate() {
		this.addMouseListener(mouse());
		activated = true;
	}

	public void setFond(Color c) {
		this.c =c ;
	}


}
