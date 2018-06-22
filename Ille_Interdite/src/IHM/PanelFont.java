package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Carte.Carte;
import ille_intedite.Observe;

public class PanelFont extends JPanel {
	
	private int num;
	private boolean activated = false;
	private Color c = new Color(139, 69, 19);
	String path=" ";
	private boolean blanc = false;
	private JPanel me ;
	private boolean classique;
	
	
	public PanelFont(int num,boolean classique) {

		this.num = num;
		this.classique=classique;
	
		setLayout(new BorderLayout(0, 0));
		me = this;
	

	}
	
	public void paintComponent(Graphics g){

		g.setColor(c);
		
		BufferedImage image;
		
		if (num == 0) {
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelWestV2.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelWestV2.png";
				e.printStackTrace();
			}

			try {			image = ImageIO.read(new File(path));
				g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelWestV2.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelWestV2.png";
				e.printStackTrace();
			}
		}
		
		
		
		if (num == 1) {
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelStartv2.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelStartv2.png";
				e.printStackTrace();
			}

			try {			image = ImageIO.read(new File(path));
				g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelStartv2.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\PanelStartv2.png";
				e.printStackTrace();
				repaint();
				revalidate();
			}
		}
		
		if (num == 2) {
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\StartPanelPanel1V1.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\StartPanelPanel1V1.png";
				e.printStackTrace();
			}

			try {			image = ImageIO.read(new File(path));
				g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\StartPanelPanel1V1.png";
			}catch(Exception e){
				path = System.getProperty("user.dir")+"\\src\\"+"images\\Art\\StartPanelPanel1V1.png";
				e.printStackTrace();
				repaint();
				revalidate();
			}
		}
		


	}
	
}
