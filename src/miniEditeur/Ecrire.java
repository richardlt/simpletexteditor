package miniEditeur;

public class Ecrire extends ActionReversible{
	
	Selection s;
	Buffer old;
	String tampon;
	
	public Ecrire(EditeurDeTexte e){
		super(e);
		s=new Selection();
		tampon=null;
		old=null;
	}
	
	public void executer() {
		if(tampon==null){
			tampon=super.editeurDeTexte.getTampon();
			s.setSelection(super.zoneDeTravail.getSelection().getPosition(), tampon.length());
			old=super.zoneDeTravail.getBuffer().getInterval(s);
		}
		super.zoneDeTravail.ecrire(tampon);
		this.state=true;
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(old.toString());
		this.state=false;
	}
	
}
