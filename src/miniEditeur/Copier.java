package miniEditeur;

public class Copier extends ActionReversible{
	
	PressePapier p;
	
	public Copier(EditeurDeTexte e){
		super(e);
	}

	public void executer(){		
		p=new PressePapier(zoneDeTravail.getPressePapier());
		super.zoneDeTravail.copier();
	}
	
	public void annuler(){
		super.zoneDeTravail.setPressePapier(p);
	}
	
}

