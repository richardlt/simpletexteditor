package miniEditeur;

public class Suppr extends ActionReversible{
	
	Selection s;
	Buffer tampon;
	
	public Suppr(EditeurDeTexte e){
		super(e);
	}
	
	public void executer() {
		s=super.zoneDeTravail.getSelection();
		tampon=super.zoneDeTravail.getBuffer().getInterval(s);
		super.zoneDeTravail.suppr();
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(tampon.toString());
	}
	
}
