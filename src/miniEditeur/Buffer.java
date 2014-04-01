package miniEditeur;

import java.util.ArrayList;

public class Buffer{
	
	private ArrayList<Byte> buffer;
	
	public Buffer(){
		super();
		buffer=new ArrayList<Byte>();
	}
	
	public ArrayList<Byte> getValue(){
		return buffer;
	}
	
	public void insertInto(ArrayList<Byte> newBuffer, int position){
		buffer.addAll(position, newBuffer);
	}
	
	public void remove(int position, int length){
		for(int i=position;i<(position+length);i++){
			buffer.remove(i);
		}
	}
	
}

