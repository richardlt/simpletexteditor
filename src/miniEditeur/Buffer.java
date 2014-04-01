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
	
	public String toString(){
		String text="";
		for(int i=0;i<buffer.size();i++){
			text+=(char) buffer.get(i).byteValue();
		}
		return text;
	}

	public void insert(String text){
		Buffer n=new Buffer(text.getBytes());
		this.insertInto(n, buffer.size());
	}
	
}

