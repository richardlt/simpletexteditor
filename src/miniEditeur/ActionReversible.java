package miniEditeur;

public abstract class ActionReversible extends Action{
	
	private int attribute;
	private Buffer buffer;	
	protected ZoneDeTravail zoneDeTravail;	
	protected EditeurDeTexte editeurDeTexte;
	
	public ActionReversible(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
		zoneDeTravail=e.getZoneDeTravail();
	}

	public abstract void executer();
	
	public abstract void annuler();
	
}

