package miniEditeur;

public class Coller extends ActionReversible{
	
	public Coller(EditeurDeTexte e){
		super(e);
	}

	public void executer() {
		super.zoneDeTravail.coller();
	}
	
	public void annuler() {
		// TODO : to implement	
	}
	
}

