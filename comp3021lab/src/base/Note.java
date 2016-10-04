package base;

import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>, Serializable{
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note (String title) {
		
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
		
	}
	
	public String getTitle (){
		
		return this.title;
	}
	
	public Date getDate (){
		
		return this.date;
	}
	
	public boolean equals(Note otherNote){
		
		if(otherNote.getTitle() == this.title){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public int compareTo (Note otherNote){
		
		if ( date.before(otherNote.getDate()) ){
			// date is more recent
			
			return 1;
			
		}else if (date.after(otherNote.getDate())){
			
			return -1;
		}else{
			
			return 0;
		}
		
	}
	
	public String toString (){
		
		return date.toString() + "\t" + title;
	}
	

}
