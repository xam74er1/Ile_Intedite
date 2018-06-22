package Carte;
public enum NomTresor {
	
	PierreSacree("Pierre Sacree",4),
	StatueZephir("Statue Zephir",3),
	CristalArdent("Cristal Ardent",1),
	CaliceOnde("Calice Onde",2);
	
	private final String nom;
	private final int num;
	
	 NomTresor(String nom,int num){
		 this.nom = nom;
		this.num = num; 
	 }

	public NomTresor getType() {
		// TODO - implement NomTresor.getType
		throw new UnsupportedOperationException();
	}

	public String getNom() {
		return nom;
	}

	public int getNum() {
		return num;
	}
	
	public static NomTresor getWisNum(int n ) {
		switch(n) {
		case 1: return CristalArdent; 
		case 2: return CaliceOnde; 
		case 3: return StatueZephir;
		case 4: return PierreSacree;
		default : return null;
		
		}
	}

}