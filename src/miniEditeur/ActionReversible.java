package miniEditeur;

public abstract class ActionReversible implements Action{
	
	private int attribute;
	private Buffer buffer;	
	private ZoneDeTravail zoneDeTravail;	
	private EditeurDeTexte editeurDeTexte;
	
	public ActionReversible(){
		super();
	}

	public void executer() {
		// TODO : to implement	
	}
	
	public void annuler() {
		// TODO : to implement	
	}
	
}

