package miniEditeur;

public class Coller extends ActionReversible {

	Selection s;
	Buffer old;
	PressePapier p;
	
    /**
     * Constructor of the Coller class
     *
     * @param e the editor to link with
     */
    public Coller(EditeurDeTexte e) {
        super(e);
        s=null;
        old=null;
        p=null;
    }

    /**
     * Method that send an order to paste the selection in into the Paper Press
     */
    public void executer() {
    	if(s==null){
			s=new Selection();s.setSelection(super.zoneDeTravail.getSelection().getPosition(), super.zoneDeTravail.getSelection().getLongueur());
			old=super.zoneDeTravail.getBuffer().getInterval(s);
			p=new PressePapier(super.zoneDeTravail.getPressePapier());
		}
    	else{
    		super.zoneDeTravail.setSelection(s);
    	}
        super.zoneDeTravail.coller();
        this.state=1;
    }

    /**
     * Procedure to cancel effects of the paste action (unprint the selection into the text area and puts it in the PaperPress)
     */
    public void annuler(){    	
		super.zoneDeTravail.setSelection(s.getPosition(), this.p.getBuffer().size());
		super.zoneDeTravail.suppr();		
		super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(old.toString());
		this.state=0;
    }
    
    public String toString(){
		return "Coller";
    }

}
