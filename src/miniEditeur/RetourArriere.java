package miniEditeur;

public class RetourArriere extends ActionReversible{
	
	Selection s;
	Buffer old;
	Boolean invalid;
	
	public RetourArriere(EditeurDeTexte e){
		super(e);
		this.invalid=false;
	}
	public void executer() {
		if(s==null && this.invalid==false){
			if(!super.zoneDeTravail.getSelection().isCursor()){
				s=new Selection(super.zoneDeTravail.getSelection());
				old=super.zoneDeTravail.getBuffer().getInterval(s);				
			}
			else{
				if(super.zoneDeTravail.getSelection().getPosition()>0){
					s=new Selection();
					s.setSelection(super.zoneDeTravail.getSelection().getPosition()-1, 1);
					old=super.zoneDeTravail.getBuffer().getInterval(s);	
					s=new Selection(super.zoneDeTravail.getSelection());					
				}else{
					this.invalid=true;
					return;
				}
			}
		}
		else{
			this.zoneDeTravail.setSelection(s);
		}
		super.zoneDeTravail.retourArriere();
		this.state=1;
	}
	
	public void annuler() {
		if(s.isCursor()){
			Selection temp=new Selection();temp.setSelection(s.getPosition()-1, 0);
			super.zoneDeTravail.setSelection(temp);		
		}else{
			super.zoneDeTravail.setSelection(s);		
		}
		super.zoneDeTravail.ecrire(old.toString());
		this.state=0;
	}

    
    public String toString(){
		return "RetourArriere";
    }
	
}
