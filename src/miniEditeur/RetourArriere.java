package miniEditeur;

public class RetourArriere extends ActionReversible{
	
	Selection s;
	Buffer tampon;
	
	public RetourArriere(EditeurDeTexte e){
		super(e);
	}
	public void executer() {
		s=super.zoneDeTravail.getSelection();
		if((s.getIndexDebut()-s.getIndexFin())>0){
			tampon=super.zoneDeTravail.getBuffer().getInterval(s);
			super.zoneDeTravail.effacer();
		}
		else{
			if(s.getIndexDebut()>0){
				s.setIndexDebut(s.getIndexDebut()-1);
				tampon=super.zoneDeTravail.getBuffer().getInterval(s);
				s.setIndexDebut(s.getIndexDebut()+1);
				super.zoneDeTravail.retourArriere();
			}
		}
	}
	
	public void annuler() {
		if((s.getIndexDebut()-s.getIndexFin())>0){
			super.zoneDeTravail.setSelection(s);
			super.zoneDeTravail.ecrire(tampon.toString());
		}else{
			if(s.getIndexDebut()>0){
				s.setIndexDebut(s.getIndexDebut()-1);
				super.zoneDeTravail.setSelection(s);
				super.zoneDeTravail.ecrire(tampon.toString());
			}
		}
	}
	
}
