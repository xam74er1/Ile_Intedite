package IHM;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class htmlPan extends JPanel {
	private String img;
	
	public htmlPan(String html,String img) {
		this.setLayout(new BorderLayout());
		
		JLabel text = new JLabel();
		String str = getHtml(html);
		text.setText(str);
		
		this.img = img;
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.CENTER);
		this.add(text,BorderLayout.CENTER);
		
	}
	
	
	public String getHtml(String path) {
		String str = "";
		File f = new File(path);
		
		try {
			Scanner sc = new Scanner(f);
			
			while(sc.hasNext()) {
				str += sc.nextLine()+"<br> \n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public void paintComponent(Graphics g){
		BufferedImage image;
		try {
			image = ImageIO.read(new File(img));
			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//super.paintComponent(g);
		
	}
}
