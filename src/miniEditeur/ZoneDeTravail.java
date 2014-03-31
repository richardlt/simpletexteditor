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

}
