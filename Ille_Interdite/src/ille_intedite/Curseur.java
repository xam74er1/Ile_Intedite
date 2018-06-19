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
	}

}