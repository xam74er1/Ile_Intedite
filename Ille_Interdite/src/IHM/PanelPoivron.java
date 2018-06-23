package IHM;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelPoivron extends JPanel{

	PanelPoivron(){
		super();
	}
	
	@Override
	public void paintComponent(Graphics g){
		BufferedImage img;

		try {
			img = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\"+"images\\Poivron.png"));
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
