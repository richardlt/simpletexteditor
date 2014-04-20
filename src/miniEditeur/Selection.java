package miniEditeur;

public class Selection{
	
	private int position;
	private int longueur;
	
	/**
	 * Constructor of Selection initialize startIndex and endIndex to 0
	 */
	public Selection(){
		this.position=0;
		this.longueur=0;
		//selecton qui a le meme index debut et fin est un curseur
	}
	
	// Class Selection's accessors 
	
	public Selection(Selection selection) {
		this.longueur=selection.longueur;
		this.position=selection.position;
	}

	public void setSelection(int position, int longueur){
		this.position = position;
		this.longueur = longueur;
	}
	
	
	
	public int getPosition(){
		return this.position;
	}
	
	public int getLongueur(){
		return this.longueur;
	}

	public void toCursor(){
		this.longueur=0;	
	}

	public boolean isCursor() {
		return this.longueur==0;
	}
	
	public String toString(){
		return "Selection : "+this.getPosition()+" - "+this.getLongueur();
	}

}

