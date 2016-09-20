package base;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public boolean createTextNote (String foldername, String title, String content){
		
		TextNote note = new TextNote(title, content);
		return insertNote (foldername, note);
	}
	
public boolean createImageNote (String foldername, String title){
		
		ImageNote note = new ImageNote (title);
		return insertNote (foldername, note);
	}

public void sortFolders (){
	
	for (Folder folder : folders){
		folder.sortNotes();
		
	}
	
	Collections.sort(this.folders);
	
	
	
}

public ArrayList<Note> searchNotes(String keywords){
	
	ArrayList<Note> result = new ArrayList<Note> ();
	
	
	for (Folder eachFolder : folders){
		
		result.addAll(eachFolder.searchNote(keywords));
		
	}
	
	return result;
	
}
	

}
