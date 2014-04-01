package miniEditeur;

public class Ecrire extends ActionReversible{
	
	public Ecrire(EditeurDeTexte e){
		super(e);
	}
	public void executer() {
		String tampon=super.editeurDeTexte.getTampon();
		super.zoneDeTravail.ecrire(tampon);
	}
	
	public void annuler() {
		
	}
	
}

