package miniEditeur;

public class Suppr extends ActionReversible{
	
	private Selection s;
	private Buffer tampon;
	
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
    
    private void setSelection(Selection selection){
    	this.s=selection;
    }
    
    private void setBuffer(Buffer buffer){
    	this.tampon=buffer;
    } 
    
	public Action clone() {
		Suppr a = new Suppr(super.editeurDeTexte);
		a.state = new Integer(this.state);
		a.setSelection(new Selection(this.s));
		a.setBuffer(new Buffer(this.tampon));	
		return a;
	}
	
}
