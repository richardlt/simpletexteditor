package miniEditeur;

public class Copier extends ActionReversible{
	
	public Copier(EditeurDeTexte e){
		super(e);
	}

	public void executer(){
		super.zoneDeTravail.copier();
	}
	
	public void annuler(){}
	
}

