package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ille_intedite.Message;
import ille_intedite.Observe;
import ille_intedite.TypeMessage;

public class PanelButton extends JPanel{

	private Observe o;
	private String name;
	private MouseListener m;

	public PanelButton (Observe o,String name,TypeMessage type) {
		super();
		this.o=o;
		this.name=name;
		m=mouse(type);
		this.addMouseListener(m);
	}
	
	@Override
	public void paintComponent(Graphics g){

		BufferedImage image;

		name=name.replace(" ","");

		try {
			image = ImageIO.read(new File("images\\Art\\btn"+name+".png"));
			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private MouseListener mouse(TypeMessage type) {
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
				Message m =new Message(type);
				o.notifierObservateur(m);
				
			}
		};
	}

}
