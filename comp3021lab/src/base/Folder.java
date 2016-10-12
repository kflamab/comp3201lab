package base;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class Folder implements Comparable<Folder>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	private ArrayList<String[]> getOrConditionPair (String [] spiltKeyword){
		
		ArrayList<String[]> result = new ArrayList<String[]> ();
		
		for (int i=0; i < spiltKeyword.length; i++){
			
			if(spiltKeyword[i].equals("or")){
				
				String [] pairs = new String[2];
				pairs[0] = spiltKeyword[i-1];
				pairs[1] = spiltKeyword[i+1];
				
				result.add(pairs);
				
			}
			
		}
		
		return result;
		
	}
	
	private String[] getTheSpiltKeyword (String keywords){
		
		String lowCaseKeyWord = keywords.toLowerCase();
		String [] spiltKeyword = lowCaseKeyWord.split(" ");
		
		return spiltKeyword;
		
	}
	
	private int getTheNumberOfOr (String [] spiltKeyword){
		
		int counter = 0;
		
		for (String keyword : spiltKeyword){
			
			if(keyword.equals("or")){
				
				
				counter++;
			}
			
		}
		
		return counter;
	}
	
	public ArrayList<Note> searchNote (String keywords){
		
		String [] spiltKeyword = getTheSpiltKeyword(keywords);
		int numberOfOr = getTheNumberOfOr(spiltKeyword);
//		System.out.println("num of or = " + numberOfOr);
//		System.out.println("is match? " + (spiltKeyword.length - numberOfOr - 1));
//		System.out.println("length = " + spiltKeyword.length);
//		System.out.println("num of keyword = " + (spiltKeyword.length - numberOfOr) );
		ArrayList<Note> result = new ArrayList<Note> ();
		
		for (Note eachNote : notes){
			
			boolean foundInImageNote = false;
			
			
			if(eachNote instanceof ImageNote){
				
			
				// just match one keyword
				
				if (numberOfOr == spiltKeyword.length - numberOfOr - 1 ){
					//System.out.println("go to just one match");
					for (int i=0; i < spiltKeyword.length; i++){
						
							
							if(eachNote.getTitle().toLowerCase().contains(spiltKeyword[i])){
								
								foundInImageNote = true;
								break;
							}
						
					}
					
					
				// must match all	
				}else if(numberOfOr == 0){
					
					foundInImageNote = true;
					
					for (int i=0; i < spiltKeyword.length; i++){
							
							if(!eachNote.getTitle().toLowerCase().contains(spiltKeyword[i])){
								
								foundInImageNote = false;
							}
							
							
						}
					
					
				}else{
					
					ArrayList<String[]> or_condition = getOrConditionPair(spiltKeyword);
					
					for (int i=0; i < or_condition.size(); i++){
								
							if (!eachNote.getTitle().toLowerCase().contains(or_condition.get(i)[0]) &&
									!eachNote.getTitle().toLowerCase().contains(or_condition.get(i)[1]) ){
								
								foundInImageNote = false;
								
								}else{
									
									foundInImageNote = true;
								}
							
					}		
					
				}
				
				if(foundInImageNote){
					
					result.add(eachNote);
				}
				
				
			}else if (eachNote instanceof TextNote){
				
				TextNote tempTextNote = (TextNote) eachNote;
				boolean foundInTextNoteTitle = false;
				
				// just match one keyword
				
				if (numberOfOr == spiltKeyword.length - numberOfOr - 1){
					
					for (int i=0; i < spiltKeyword.length; i++){
						
							
							if(tempTextNote.getTitle().toLowerCase().contains(spiltKeyword[i]) ||
									tempTextNote.getContent().toLowerCase().contains(spiltKeyword[i])){
								
								foundInTextNoteTitle = true;
								break;
							}
						
					}
					
				}else if(numberOfOr == 0){
					
					foundInTextNoteTitle = true;
					
					for (int i=0; i < spiltKeyword.length; i++){
							
							if(!tempTextNote.getTitle().toLowerCase().contains(spiltKeyword[i]) &&
									!tempTextNote.getContent().toLowerCase().contains(spiltKeyword[i])	){
								
								foundInTextNoteTitle = false;
								break;
								
							}	
						}
				}else{
					
					ArrayList<String[]> or_condition = getOrConditionPair(spiltKeyword);
					boolean findInOrcondition = true;
					
					for (int i=0; i < or_condition.size(); i++){
								
							if ( tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[0]) ||
									tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[1])   ||
									 tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[0]) ||
									tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[1])  ){
								
								findInOrcondition = true;
//								System.out.println("********  fail to find the TextNote of the name - " + tempTextNote.getTitle() + "**************");
//								
//								System.out.println("the result of searching " + or_condition.get(i)[0] + " in title is " + 
//								tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[0])  );
//								
//								System.out.println("the result of searching " + or_condition.get(i)[1] + " in title is " + 
//										tempTextNote.getTitle().toLowerCase().contains(or_condition.get(i)[1])  );
//								
//								System.out.println("the result of searching " + or_condition.get(i)[0] + " in content is " + 
//										tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[0])  );
//										
//								System.out.println("the result of searching " + or_condition.get(i)[1] + " in title content is " + 
//										tempTextNote.getContent().toLowerCase().contains(or_condition.get(i)[1])  );

								
								}else{
									
									findInOrcondition = false;
									break;
								}
							
					}		
					foundInTextNoteTitle = findInOrcondition;
					
				}
				
				if(foundInTextNoteTitle){
					
					result.add(tempTextNote);
				}
				
			}// end of the else statement of if this is the textNote
			
		} //end of for loop of eachNote no matter what type of note it is
		
		
		return result;
		
	}
	
	
	
}
