package base;


import java.util.ArrayList;
import java.util.Collections;


public class Folder implements Comparable<Folder>{
	
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
	
	public void sortNotes (){
		
		Collections.sort(notes);
		
	}
	
	public int compareTo(Folder otherFolder){
		
		if (name.compareTo(otherFolder.getName()) > 0 ){
			
			//the current name is greater than the argument name
			return 1;
			
		}else if ( name.compareTo(otherFolder.getName()) < 0 ){
			
			return -1;
		}else {
			
			return 0;
		}
	}
	
	public ArrayList<Note> searchNote (String keywords){
		
		
		keywords = keywords.toLowerCase();
		//System.out.println("Print out the keywords: " + keywords);
		String keyPair [] = keywords.split(" ");
	
		ArrayList <String []> or_condition = new ArrayList <String []> ();
		
		ArrayList<Note> result = new ArrayList<Note> ();
		
		
		
		for (int i=0; i < keyPair.length; i++){
			
			if(keyPair[i].equals("or")){
				
				String pairs [] = {keyPair[i-1],keyPair[i+1] };
				or_condition.add(pairs);
			}
		}
		
		/*System.out.println("Print out the or_condition array of array:");
		for (int i=0; i < or_condition.size(); i++){
			
		System.out.println(or_condition.get(i)[0] + " " + or_condition.get(i)[1]);
			
		}*/
		
		
		
		for (Note eachNote : notes){
			
			boolean foundInImageNote = true;
			
			if(eachNote instanceof ImageNote){
				
				for (int i=0; i < or_condition.size(); i++){
					
					if(eachNote.getTitle().toLowerCase().contains(or_condition.get(i)[0]) ||
					eachNote.getTitle().toLowerCase().contains(or_condition.get(i)[1]) ){
						
					/*System.out.println("The ImageNote title is " +  eachNote.getTitle() +
						" and the key pairs are " + or_condition.get(i)[0] +" and " + or_condition.get(i)[1] + " ");*/
						
					}else{
						/*System.out.println("Key pairs: "+ or_condition.get(i)[0] +" and " + or_condition.get(i)[1] + " is not contained"
								+ " in ImageNote title: " + eachNote.getTitle());*/
						foundInImageNote = false;
					}
				}
				
				if(foundInImageNote){
					
					result.add(eachNote);
				}
				
			}else if (eachNote instanceof TextNote){
				
				TextNote tempTextNote = (TextNote) eachNote;
				
				boolean foundInTextNoteTitle = true;
				
				for (int i=0; i < or_condition.size(); i++){
					
					if(tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[0]) ||
							tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[1]) ){
						
						/*System.out.println("The TextNote title is " +  eachNote.getTitle() +
								" and the key pairs are " + or_condition.get(i)[0] +" and " + or_condition.get(i)[1] + " ");*/
						
					}else{
						/*System.out.println("Key pairs: "+ or_condition.get(i)[0] +" and " + or_condition.get(i)[1] + " is not contained"
								+ " in TextNote title: " + eachNote.getTitle());*/
						
						foundInTextNoteTitle = false;
					}
				}
				
				if(foundInTextNoteTitle){
					
					result.add(tempTextNote);
				}
				
				boolean foundInTextNoteContent = true;
				
				for (int i=0; i < or_condition.size(); i++){
					
					
					if(tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[0]) 
							|| tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[1])){
					
						
						
					}else{
						
						 foundInTextNoteContent = false;
					}
					 
					
					
				}
				
				
				if(foundInTextNoteContent){
					
					result.add(tempTextNote);
				}
				
			} // end of else - here the note is textNote
			
		} //end of for loop
		
		
		return result;
		
	}
	
	
	
}
