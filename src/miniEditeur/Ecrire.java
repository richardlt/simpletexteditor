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
			s=new Selection(super.zoneDeTravail.getSelection());
			old=super.zoneDeTravail.getBuffer().getInterval(s);
		}else{
			super.zoneDeTravail.setSelection(s);
		}
		super.zoneDeTravail.ecrire(tampon);
		this.state=1;
	}
	
	public void annuler() {
		super.zoneDeTravail.setSelection(s.getPosition(), tampon.length());
		super.zoneDeTravail.suppr();
		super.zoneDeTravail.setSelection(s.getPosition(), 0);
		super.zoneDeTravail.ecrire(old.toString());
		this.state=0;
	}
    
    public String toString(){
		return "Ecrire(Tampon: "+tampon+" Old: "+old.toString()+" "+s.toString()+")";
    }
	
    private void setSelection(Selection selection){
    	this.s=selection;
    }
    
    private void setBuffer(Buffer buffer){
    	this.old=buffer;
    } 
    
    private void setTampon(String tampon){
    	this.tampon=tampon;
    } 
    
	public Action clone() {
		Ecrire a = new Ecrire(super.editeurDeTexte);
		a.state = new Integer(this.state);
		a.setSelection(new Selection(this.s));
		a.setBuffer(new Buffer(this.old));	
		a.setTampon(new String(this.tampon));	
		return a;
	}
    
}
