package miniEditeur;

public class Ecrire extends ActionReversible{
	
	Selection s;
	Buffer old;
	String tampon;
	
	public Ecrire(EditeurDeTexte e){
		super(e);
		s=null;
		tampon=null;
		old=null;
	}
	
	public void executer() {
		if(tampon==null && s==null){
			tampon=super.editeurDeTexte.getTampon();
			s=new Selection();s.setSelection(super.zoneDeTravail.getSelection().getPosition(), tampon.length());
			old=super.zoneDeTravail.getBuffer().getInterval(s);
		}
		super.zoneDeTravail.ecrire(tampon);
		this.state=1;
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(old.toString());
		this.state=0;
	}
    
    public String toString(){
		return "Ecrire(Tampon: "+tampon+" Old: "+old.toString()+" "+s.toString()+")";
    }
	
}
