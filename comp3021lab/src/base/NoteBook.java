package base;

import java.util.ArrayList;

public class NoteBook {
	
	private ArrayList<Folder> folders;
	
	public NoteBook (){
		
		folders = new ArrayList<Folder>();
	}
	

	
	public ArrayList<Folder> getFolders (){
		
		return folders;
	}
	
	public boolean insertNote (String foldername, Note note){
		
		Folder f = null;
		boolean canInsert = true;
		
		for (Folder f1: folders){
			
			if(foldername.equals(f1.getName())){
				f = f1;
			}
		
		}
		
		if(f == null){
			
			f = new Folder(foldername);
			
			 folders.add(f);
		}
		
		for (Note n: f.getNotes()){
			
			if(n.getTitle().equals(note.getTitle())){
				
				System.out.println("Creating note " + note.getTitle() + " under folder " + foldername + " failed.");
				canInsert = false;
			}
			
		}
		
		if(canInsert != false){
			
			f.addNote(note);
			canInsert = true;
		}
		
		
		return canInsert;
		
		
		
		
	}
	
	public boolean createTextNote (String foldername, String title){
		
		TextNote note = new TextNote(title);
		return insertNote (foldername, note);
	}
	
public boolean createImageNote (String foldername, String title){
		
		ImageNote note = new ImageNote (title);
		return insertNote (foldername, note);
	}
	

}
