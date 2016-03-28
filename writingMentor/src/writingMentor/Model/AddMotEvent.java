package writingMentor.Model;

import java.util.EventObject;

public class AddMotEvent extends EventObject{
	private Mot wordToAdd;
	
	public AddMotEvent(Object source, Mot wordToAdd){
		super(source);
		this.wordToAdd = wordToAdd;
	}
	
	public Mot getNewMot(){
		return this.wordToAdd;
	}
}
