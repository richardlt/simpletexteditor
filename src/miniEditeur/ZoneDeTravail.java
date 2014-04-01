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
		return false;
	}

	public boolean couper() {
		return false;
	}

	public boolean ecrire() {
		return false;
	}

	public boolean coller() {
		return false;
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
	

}
