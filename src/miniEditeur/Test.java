package miniEditeur;

public class Test {

	public static void main(String[] args) {
		EditeurDeTexte edtg = new EditeurDeTexteGraphique();
		edtg.start();
		EditeurDeTexte edtt = new EditeurDeTexteTextuelle();
		edtt.start();
	}

}
