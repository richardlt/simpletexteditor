package miniEditeur;

public class RetourArriere extends ActionReversible{
	
	Selection s;
	Buffer tampon;
	
	public RetourArriere(EditeurDeTexte e){
		super(e);
	}
	public void executer() {
		s=super.zoneDeTravail.getSelection();
		if(!s.isCursor()){
			tampon=super.zoneDeTravail.getBuffer().getInterval(s);
			super.zoneDeTravail.suppr();
		}
		else{
			if(s.getPosition()>0){
				s.setSelection(s.getPosition()-1, s.getLongueur());
				tampon=super.zoneDeTravail.getBuffer().getInterval(s);
				s.setSelection(s.getPosition()+1, s.getLongueur());
				super.zoneDeTravail.retourArriere();
			}
		}
	}
	
	public void annuler() {
		if(!s.isCursor()){
			super.zoneDeTravail.setSelection(s);
			super.zoneDeTravail.ecrire(tampon.toString());
		}else{
			if(s.getPosition()>0){
				s.setSelection(s.getPosition()-1, s.getLongueur());
				super.zoneDeTravail.setSelection(s);
				super.zoneDeTravail.ecrire(tampon.toString());
			}
		}
	}
	
}
