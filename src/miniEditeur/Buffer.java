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
	
	public void insertAfterCursor(Buffer buf, int position){
		int indexInsert=position;
		for(int i=0;i<buf.size();i++){
			if(indexInsert<this.size()){
				this.add(indexInsert, buf.get(i).byteValue());
				indexInsert++;
			}else{
				this.add(buf.get(i).byteValue());
				indexInsert++;
			}
		}
	}
	
	public void remove(int position, int length){
		for(int i=(position+length)-1;i>=position && i<buffer.size();i--){
			buffer.remove(i);
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
		for(int i=s.getPosition();i<s.getPosition()+s.getLongueur() && i<buffer.size();i++){
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

