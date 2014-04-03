package miniEditeur;

public class Ecrire extends ActionReversible{
	
	Selection s;
	Buffer old;
	
	public Ecrire(EditeurDeTexte e){
		super(e);
	}
	
	public void executer() {
		String tampon=super.editeurDeTexte.getTampon();
		s=super.zoneDeTravail.getSelection();
		old=super.zoneDeTravail.getBuffer().getInterval(s);
		super.zoneDeTravail.effacer();
		super.zoneDeTravail.ecrire(tampon);
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(old.toString());
	}
	
}

