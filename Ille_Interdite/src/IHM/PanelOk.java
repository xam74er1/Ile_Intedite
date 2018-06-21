package IHM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ille_intedite.Observe;

public class PanelOk extends JPanel {
	
	private Observe o;

	PanelOk(Observe o){
		super();
		this.o=o;
	}
	
	@Override
	public void paintComponent(Graphics g){
		BufferedImage img;
		
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\"+"images\\"));
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
