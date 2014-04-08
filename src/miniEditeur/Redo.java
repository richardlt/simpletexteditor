package miniEditeur;

public class Redo extends ActionNonReversible {
    
    /**
     * Constructor of the Redo class
     *
     * @param e the editor to link with
     */
    public Redo(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to redo the next action
     */
    public void executer() {
        super.editeurDeTexte.getZoneDeTravail().refaire();
    }

}
