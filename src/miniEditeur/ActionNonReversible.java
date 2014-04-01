package miniEditeur;

public abstract class ActionNonReversible implements Action{
	
	private ActionReversible actionReversible;	
	protected EditeurDeTexte editeurDeTexte;
	
	public ActionNonReversible(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
	}

	public void executer() {
		// TODO : to implement	
	}
	
}

