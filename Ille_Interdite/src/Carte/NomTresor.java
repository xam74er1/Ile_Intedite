package Carte;
public enum NomTresor {
	
	PierreSacree("Pierre Sacree",4),
	StatueZephyr("Statue Zephir",3),
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
		return this;
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
		case 3: return StatueZephyr;
		case 4: return PierreSacree;
		default : return null;
		
		}
	}

}