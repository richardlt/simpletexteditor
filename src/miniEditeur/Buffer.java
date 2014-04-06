package miniEditeur;

import java.util.ArrayList;

public class Buffer{
	
	private ArrayList<Byte> buffer;
	
	public Buffer(){
		super();
		buffer=new ArrayList<Byte>();
	}
	
	public Buffer(byte[] bytes) {
		super();
		buffer=new ArrayList<Byte>();
		for(int i=0;i<bytes.length;i++){
			buffer.add(bytes[i]);
		}
	}

	public Buffer(Buffer buf) {
		super();
		buffer=new ArrayList<Byte>();
		for(int i=0;i<buf.size();i++){
			buffer.add(buf.get(i));
		}
	}

	public ArrayList<Byte> getValue(){
		return buffer;
	}
	
	public void insertInto(Buffer newBuffer, int position){
		buffer.addAll(position, newBuffer.getValue());
	}
	
	public void remove(int position, int length){
		for(int i=position;i<(position+length);i++){
			buffer.remove(i);
		}
	}
	
	public void replaceInterval(Buffer buf, Selection s){
		int indexInsert=s.getIndexDebut();
		for(int i=0;i<buf.size();i++){
			if(indexInsert<=s.getIndexFin()){
				this.set(indexInsert, buf.get(i).byteValue());
				indexInsert++;
			}else{
				if(indexInsert<this.size()){
					this.add(indexInsert, buf.get(i).byteValue());
					indexInsert++;
				}else{
					System.out.println(indexInsert+" - "+i+" - "+(char) buf.get(i).byteValue());
					this.add(buf.get(i).byteValue());
					indexInsert++;
				}
			}
		}
	}
	
	private void add(byte byteValue) {
		this.buffer.add(byteValue);
	}

	private void add(int i, byte byteValue) {
		this.buffer.add(i, byteValue);
	}

	public int size() {
		return buffer.size();
	}

	private Byte get(int i) {
		return this.buffer.get(i);
	}

	public String toString(){
		String text="";
		for(int i=0;i<buffer.size();i++){
			text+=(char) buffer.get(i).byteValue();
		}
		return text;
	}

	public Buffer getInterval(Selection s) {		
		Buffer buf=new Buffer();
		for(int i=s.getIndexDebut();i<=s.getIndexFin();i++){
			buf.set(i, buffer.get(i).byteValue());
		}
		return buf;
	}

	private void set(int i, byte byteValue) {
		if(i<this.size()-1){
			this.buffer.set(i, byteValue);		
		}else{
			this.buffer.add(byteValue);		
		}
	}
	
}

