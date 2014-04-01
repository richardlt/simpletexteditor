package miniEditeur;

public class Selection{
	
	private int indexDebut;
	private int indexFin;
	
	/**
	 * Constructor of Selection initialize startIndex and endIndex to 0
	 */
	public Selection(){
		super();
		this.indexDebut=0;
		this.indexFin=0;
	}
	
	// Class Selection's accessors 
	
	public void setIndexDebut(int i){
		this.indexDebut = i;
	}
	
	public void setIndexFin(int i){
		this.indexFin = i;
	}
	
	public int getIndexDebut(){
		return this.indexDebut;
	}
	
	public int getIndexFin(){
		return this.indexFin;
	}

}

