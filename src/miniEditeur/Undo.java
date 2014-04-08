package miniEditeur;

public class Undo extends ActionNonReversible {

    /**
     * Constructor of the Undo class
     *
     * @param e the editor to link with
     */
    public Undo(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to undo the previous reversible action
     */
    public void executer() {
        super.editeurDeTexte.getZoneDeTravail().retourArriere();
    }

}
