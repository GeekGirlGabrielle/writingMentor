package writingMentor.View;

import java.util.EventObject;

import writingMentor.Model.Mot;
import writingMentor.Model.Theme;

public class WritingMentorChangedEvent extends EventObject{
	private Mot newMot;
	private Theme newTheme;
	
	//mot
	public WritingMentorChangedEvent(Object source, Mot newMot){
		super(source);
		this.newMot = newMot;
		
	}
	
	//thème
	public WritingMentorChangedEvent(Object source, Theme newTheme){
		super(source);
		this.newTheme = newTheme;
		
	}
	
	public Mot getNewMot(){
		return newMot;
	}
	
	public Theme getNewTheme(){
		return newTheme;
	}
}
