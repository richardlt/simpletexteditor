package miniEditeur;

public class Couper extends ActionReversible {

    Buffer old;
    Selection s;

    /**
     * Constructor of the Couper class
     *
     * @param e the editor to link with
     */
    public Couper(EditeurDeTexte e) {
        super(e);
		s=null;
		old=null;
    }

    /**
     * Method that send an order to cut the selection in the text area into the
     * paper press to the ZoneDeTravail
     */
    public void executer() {
    	if(this.s==null){
			s=new Selection(super.zoneDeTravail.getSelection());
			old=super.zoneDeTravail.getBuffer().getInterval(s);
		}else{
			super.zoneDeTravail.setSelection(s);
		}
		super.zoneDeTravail.couper();
        this.state=1;
    }

    /**
     * Procedure to cancel effects of the cut action (reprint the selection into
     * the text area to its right place)
     */
    public void annuler(){
    	Selection s=new Selection(this.s);s.toCursor();
    	super.zoneDeTravail.setSelection(s);
		super.zoneDeTravail.ecrire(old.toString());
		this.state=0;
    }
    
    public String toString(){
		return "Couper";
    }

}
