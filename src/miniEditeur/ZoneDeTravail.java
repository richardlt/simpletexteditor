package miniEditeur;

public class ZoneDeTravail {

	/**
	 * Constructor of PressePapier
	 */
	private PressePapier pressePapier;
	private Selection selection;
	private Buffer buffer;

	public ZoneDeTravail(EditeurDeTexte e) {
		this.buffer 		= new Buffer();
		this.pressePapier 	= new PressePapier();
		this.selection 		= new Selection();
	}

	public boolean copier() {
		this.pressePapier.setValue(this.buffer.getInterval(this.selection));
		return true;
	}

	public boolean couper() {
		return false;
	}

	public boolean ecrire(String text) {
		Buffer newT=new Buffer(text.getBytes());
		this.buffer.replaceInterval(newT, this.selection);
		this.selection.setIndexDebut(this.selection.getIndexDebut()+newT.size());
		this.selection.toCursor();
		return true;
	}

	public boolean coller(){
		this.buffer.remove(this.selection.getIndexDebut(), this.selection.getIndexFin()-this.selection.getIndexDebut());
		this.selection.toCursor();
		this.buffer.insertInto(this.pressePapier.getBuffer(), this.selection.getIndexDebut());
		return true;
	}

	public void effacer() {
            
	}

	// Class ZoneDeTravail's accessors
	
	public PressePapier getPressePapier() {
		return this.pressePapier;
	}

	public void setPressePapier(PressePapier pressePapier) {
		this.pressePapier = pressePapier;
	}

	public Selection getSelection() {
		return this.selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public Buffer getBuffer() {
		return this.buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}	
	
	public String toString(){
		String temp="";
		for(int i=0;i<=this.buffer.size();i++){
			if(this.selection.getIndexDebut()<=i && i<=this.selection.getIndexFin()){
				temp+="^";
			}else{
				temp+=" ";
			}
		}
		return "Presse papier : "+this.pressePapier.toString()+"\n"+this.buffer.toString()+"\n"+temp;
		
	}

	public void retourArriere() {
		// TODO Auto-generated method stub	
	}
        
        public void refaire(){
            
        }

	public void suppr() {
		// TODO Auto-generated method stub
		
	}	

}
