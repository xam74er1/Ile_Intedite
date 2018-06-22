package IHM;

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

public class PanelOk extends JPanel {

	private Observe o;
	private MouseListener m;

	PanelOk(Observe o){
		super();
		this.o=o;
		m=new MouseListener() {

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
				Message m = new Message(TypeMessage.Clique_Ok);
				o.notifierObservateur(m);
			}
		};
		this.addMouseListener(m);
	}

	@Override
	public void paintComponent(Graphics g){
		BufferedImage img;

		try {
			img = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\"+"images\\Art\\btnOk.png"));
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void activate() {
		this.addMouseListener(m);
	}

	public void desactivate() {
		this.removeMouseListener(m);
	}

}
