
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private boolean activated = true , blanc = false;
	private MouseListener m;
	private Color c = new Color(204, 102, 0);
	String path=" ";

	public CasePlateau(String str,String location, Observe o,Tuile t) {
//com ref
		this.str = str;
		this.o = o;
		this.t = t;
		this.location = location;

		m=mouse();		
		this.addMouseListener(m);
		setLayout(new BorderLayout(0, 0));

		/*JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		add(lblNewLabel);
		setBackground(Color.gray);*/
		
		this.t.setCase(this);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public void paintComponent(Graphics g){
		int x = (int) (this.getHeight()*0.1);
		int y = (int) (this.getWidth()*0.05);
		int s = x;
		int xa = x;
		int ya = y;

		BufferedImage image;
		
		if(activated) {
			g.setColor(c);

			g.fillRect(0, 0,  this.getWidth(),this.getHeight());

			g.setColor(Color.black);
			g.drawRect(0, 0,  this.getWidth(),this.getHeight());
		}
		
		if(activated) {
			String name = t.getNom();
			path=" ";

			int min = Math.min(this.getWidth(),this.getHeight());

			//Pour fair en sorte que le string sois de bonne taille 
			name=	name.replace(" d", "D");
			name=	name.replace(" ", "");
			name = name.replace("’", "");
			name = name.replace("'", "");


			//On gere les image en fonction des donne 
			try {
				if(t.getStatut()<2) {


					if(t.getStatut()==0) {
						path = System.getProperty("user.dir")+"\\src\\"+"images\\tuiles\\"+name+".png";
					}else if(t.getStatut()==1) {
						path = System.getProperty("user.dir")+"\\src\\"+"images\\tuiles\\"+name+"_Inonde.png";
					}

					image = ImageIO.read(new File(path));
					//super.paintComponent(g);
					g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
				}else {
					path = System.getProperty("user.dir")+"\\src\\"+"images\\watterTexture.jpg";
					//System.out.println("coule");
					image = ImageIO.read(new File(path));
					//super.paintComponent(g);
					g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("name = "+name);
				System.out.println("erruer :"+ path+" ");
				//e.printStackTrace();
			}
		}else {

			try {
				image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\"+"images\\watterTexture.jpg"));
				//super.paintComponent(g);
				g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//System.out.println(new File().exists());
				e.printStackTrace();
			}
		}

		//Effect blanc pour la ou on ne peux pas ce deplace 
		if(blanc&&activated) {
			g.setColor(new Color(0,0,0,128));

			g.fillRect(0, 0,  this.getWidth(),this.getHeight());
		}
		
		for(Aventurier a : t.getAventurie()) {
			String name = a.getPion().toString();
			path = System.getProperty("user.dir")+"\\src\\"+"images\\pions\\pion"+name+".png";

			try {
				image = ImageIO.read(new File(path));
				//super.paintComponent(g);
				g.drawImage(image, xa, ya, 4*s, 4*s, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//System.out.println(new File().exists());
				System.out.println(path);
				//e.printStackTrace();
			}
			//			g.setColor(a.getColor());
			//			g.fillOval(xa, ya, xa+s, ya+s);
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
				if(!blanc) {
				
				Message m = new Message(TypeMessage.Clique_Tuile);
				m.setLocation(location);
				o.notifierObservateur(m);
				}


			}
		};
		
	}
	public void desactivate() {
		this.removeMouseListener(m);
		activated = false;
	}
	
	public void activate() {
		this.addMouseListener(m);
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setFond(Color c) {
		this.c =c ;
	}
	
	public void setBlanc() {
		this.removeMouseListener(m);
		
		this.blanc = true;
	}
	
	public void removeBlanc() {
		this.blanc = false;
		this.addMouseListener(m);
	}

}

//testEUUU
