package miniEditeur;

public abstract class ActionReversible implements Action{
	
	private int attribute;
	private Buffer buffer;	
	protected ZoneDeTravail zoneDeTravail;	
	protected EditeurDeTexte editeurDeTexte;
	
	public ActionReversible(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
		zoneDeTravail=e.getZoneDeTravail();
	}

	public void executer() {
		// TODO : to implement	
	}
	
	public void annuler() {
		// TODO : to implement	
	}
	
}

