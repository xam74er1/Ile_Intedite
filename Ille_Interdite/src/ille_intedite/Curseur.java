package ille_intedite;

import javax.sound.sampled.Clip;

import IHM.PlaySound;

public class Curseur {

	private int niveau;
	private static Clip cl ;

	/**
	 * 
	 * @param niv
	 */
	public Curseur(int niveau) {
		// TODO - implement Curseur.Curseur
		this.niveau = niveau;
	}

	
	/**
	 * 
	 * @return 2 si niveau<3, 3 si niveau>2 et niveau<6, 4 si niveau>5 et niveau<8, 5 si niveau>7 et niveau<10, 0 si niveau=10 (perdu).
	 */
	public int getNbCartesInond() {
		// TODO - implement Curseur.getNbCartesInond
		if(niveau<3) {
			return 2;
		}
		if(niveau>2 && niveau<6) {
			return 3;
		}
		if(niveau>5 && niveau<8) {
			return 4;
		}
		if(niveau>7 && niveau<10) {
			return 5;
		}
		else { return 0;}
			
	}

	public void monteeEaux() {
		niveau++;
		
		if(niveau==9) {
			cl = PlaySound.play(System.getProperty("user.dir")+"\\src\\"+"sound\\Jawstheme.wav");
		}
	}
	
	public int getNiv() {
		return niveau;
	}
	
	public static void stopSound() {
		if(cl != null) {
			if(cl.isOpen()&& cl.isActive()) {
				cl.stop();
			}
		}
	}
	
	public void setNiv(int niv) {
		this.niveau=niv;
	}

}