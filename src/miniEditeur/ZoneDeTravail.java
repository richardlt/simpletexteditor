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
		this.pressePapier.setValue(this.buffer.getInterval(this.selection));
		this.suppr();
		return false;
	}

	public boolean ecrire(String text) {
		Buffer newT=new Buffer(text.getBytes());
		if(!this.selection.isCursor()){
			this.suppr();
		}
		this.buffer.insertAfterCursor(newT, this.selection.getPosition());
		this.selection.setSelection(this.selection.getPosition()+newT.size(), 0);
		return true;
	}

	public boolean coller(){
		if(!this.selection.isCursor()){
			this.suppr();
		}
		this.buffer.insertAfterCursor(this.pressePapier.getBuffer(), this.selection.getPosition());
		return true;
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
	
	public String toString(){
		String temp="";
		for(int i=0;i<=this.buffer.size();i++){
			if(this.selection.getPosition()<=i && i<=this.selection.getPosition()+this.selection.getLongueur()){
				if(this.selection.isCursor()){
					temp+="|";
				}else if(i<this.selection.getPosition()+this.selection.getLongueur()){
					temp+="^";
				}
			}else{
				temp+=" ";
			}
		}
		return "Presse papier : "+this.pressePapier.toString()+" Selection : "+this.selection.getPosition()+" - "+this.selection.getLongueur()+"\n"+this.buffer.toString()+"\n"+temp;
		
	}

	public void retourArriere() {
		if(!this.selection.isCursor()){
			this.suppr();
		}else{
			this.buffer.remove(this.selection.getPosition()-1, 1);
			this.selection.setSelection(this.selection.getPosition()-1, 0);
		}
	}
	
	public void suppr(){
		this.buffer.remove(this.selection.getPosition(), this.selection.getLongueur());
		this.selection.toCursor();
	}	

}
