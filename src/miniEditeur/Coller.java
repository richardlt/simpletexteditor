package miniEditeur;

public class Coller extends ActionReversible {

    /**
     * Constructor of the Coller class
     *
     * @param e the editor to link with
     */
    public Coller(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to paste the selection in into the Paper Press
     */
    public void executer() {
        super.zoneDeTravail.coller();
    }

    /**
     * Procedure to cancel effects of the paste action (unprint the selection into the text area and puts it in the PaperPress)
     */
    public void annuler() {
        super.zoneDeTravail.suppr();
    }

}
