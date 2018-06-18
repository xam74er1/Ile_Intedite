package ille_intedite;
public class Curseur {

	private int niveau;

	/**
	 * 
	 * @param niv
	 */
	public Curseur(int niveau) {
		// TODO - implement Curseur.Curseur
		this.niveau = niveau;
	}

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
	}

}