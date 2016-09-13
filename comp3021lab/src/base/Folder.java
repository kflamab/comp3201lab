package base;

import java.util.ArrayList;

public class Folder {
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder (String name) {
	
		this.name = name;
		notes = new ArrayList<Note>();
		
	}
	
	public void addNote (Note oneNote){
		
		notes.add(oneNote);
	}
	
	public String getName (){
		
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		
		return notes;
	}
	
	public String toString () {
		
		int nText = 0;
		int nImage = 0;
		
		for (Note eachNote : notes){
			
			if(eachNote instanceof ImageNote){
				
				
				nImage = nImage + 1;
				
		
				
			}else if (eachNote instanceof TextNote){
				
				
				nText = nText + 1;
				
		
			}
		}
		

		return name + ":" + nText + ":" + nImage;
		
	
		
		
	}
	
	public boolean equals (Folder otherFolder){
		
		if(otherFolder.getName().equals(this.name)){
			
			return true;
		}else{
			
			return false;
		}
		
	}
	
	
	
}
