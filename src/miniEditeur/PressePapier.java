package miniEditeur;

public class PressePapier{
	
	private Buffer buffer;
	
	public PressePapier(){
		super();
		buffer=new Buffer();
	}
	
	public PressePapier(PressePapier pressePapier) {
		super();
		this.buffer=new Buffer(pressePapier.getBuffer());
	}

	public void setValue(Buffer buf){
		buffer=new Buffer(buf);	
	}

	public Buffer getBuffer() {
		return buffer;
	}
	
	public String toString(){
		return this.buffer.toString();
	}

}

