package miniEditeur;

public abstract class ActionReversion extends Action{
	
	protected Action action;	
	protected EditeurDeTexte editeurDeTexte;
	
	public ActionReversion(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
		action=null;
	}

	public abstract void executer();
	
	public abstract void annuler();

	public abstract String toString(); 
	
	public abstract Action clone();
	
}

