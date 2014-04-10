package miniEditeur;

public class Copier extends ActionReversible {

    PressePapier p;

    /**
     * Constructor of the Copier class
     *
     * @param e the editor to link with
     */
    public Copier(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to copy the selection in into the Paper Press
     */
    public void executer() {
        p = new PressePapier(zoneDeTravail.getPressePapier());
        super.zoneDeTravail.copier();
        this.state=-1;
    }

    /**
     * Procedure to cancel effects of the copy action (reset the Paper Press' content to its previous version)
     */
    public void annuler(){}
    
    public String toString(){
		return "Copier";
    }

}
