package miniEditeur;

public abstract class ActionReversible extends Action{
	
	protected ZoneDeTravail zoneDeTravail;	
	protected EditeurDeTexte editeurDeTexte;
	
	public ActionReversible(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
		zoneDeTravail=e.getZoneDeTravail();
	}

	public abstract void executer();
	
	public abstract void annuler();

	public abstract String toString(); 
	
	public abstract Action clone();
	
}

