package miniEditeur;

public abstract class ActionNonReversible implements Action{
	
	private ActionReversible actionReversible;	
	protected EditeurDeTexte editeurDeTexte;
	
	/**
	 * Constructor of the class ActionNonReversible
	 * @param e the editor to link with
	 */
	public ActionNonReversible(EditeurDeTexte e){
		super();
		editeurDeTexte=e;
	}

	/**
	 * Method that send an order to cancel the execution of the linked ActionReversible
	 */
	public void executer() {
		this.actionReversible.annuler();
	}

	// Accessors of actionReversible attribute
	
	public ActionReversible getActionReversible(){
		return this.actionReversible;
	}
	
	public void setActionReversible(ActionReversible ar){
		this.actionReversible = ar;
	}
	
}

