package miniEditeur;

public class Suppr extends ActionReversible{
	
	Selection s;
	Buffer tampon;
	
	public Suppr(EditeurDeTexte e){
		super(e);
	}
	
	public void executer() {
		if(this.s==null){
			s=new Selection(super.zoneDeTravail.getSelection());
			tampon=super.zoneDeTravail.getBuffer().getInterval(s);
		}else{
			super.zoneDeTravail.setSelection(s);
		}		
		super.zoneDeTravail.suppr();
		this.state=1;
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(tampon.toString());
		this.state=0;
	}
    
    public String toString(){
		return "Suppr";
    }
	
}
