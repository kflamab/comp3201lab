package base;

import java.util.ArrayList;
import java.util.List;

import base.Folder;
import base.Note;
import base.NoteBook;

public class testLab3 {
	public static void main(String args[]){
		
		NoteBook nb = new NoteBook();
		nb.createTextNote("Note1", "Java", "comp3021");
		nb.createTextNote("Note2", "Assignment", "due on 2016-10-16");
		nb.createTextNote("Note3", "lab","need to attend weekly");
		nb.createTextNote("Note4", "lab4","testing");
		
		nb.sortFolders();
		int findex = 0;
		for (Folder folder : nb.getFolders()) {
			System.out.println("Folder " + findex++ + ":" + folder.toString());
			List<Note> notes = folder.getNotes();
			int nindex = 0;
			for (Note note : notes) {
				System.out.println("--" + nindex++ + ":" + note.toString());
			}
		}
		
		List<Note> notes = nb.searchNotes("java DUE or testing");
		System.out.println("Search Results:");
		if (notes == null || notes.size() == 0) {
			System.out.println("No Search Results");
		} else  {
			for (Note note : notes) {
				System.out.println(note.toString());
			}
		}
		
		
	}
}
