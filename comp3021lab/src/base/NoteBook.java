package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class NoteBook implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook (){
		
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file){
		// TODO
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		try {
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				NoteBook loadedObject = (NoteBook) in.readObject();
				this.folders = loadedObject.getFolders();
				in.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		
		
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

public boolean save(String file){
//TODO
	FileOutputStream fos = null;
	ObjectOutputStream out = null;
	
	try {
	//TODO
		fos = new FileOutputStream(file);
		out = new ObjectOutputStream(fos);
		out.writeObject(this);
		out.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	
return true;
}
	

}
