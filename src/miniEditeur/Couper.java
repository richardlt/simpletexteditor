package miniEditeur;

public class Couper extends ActionReversible {

    PressePapier p;

    /**
     * Constructor of the Couper class
     *
     * @param e the editor to link with
     */
    public Couper(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to cut the selection in the text area into the
     * paper press to the ZoneDeTravail
     */
    public void executer() {
        p = new PressePapier(zoneDeTravail.getPressePapier());
        super.zoneDeTravail.couper();
    }

    /**
     * Procedure to cancel effects of the cut action (reprint the selection into
     * the text area to its right place)
     */
    public void annuler() {
        super.zoneDeTravail.ecrire(p.toString());
    }

}
