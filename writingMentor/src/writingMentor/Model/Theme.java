/**
 * 
 * @author gbotbol
 */
package writingMentor.Model;

import java.util.ArrayList;
import java.util.Collection;



public class Theme 
{
	private int id;
	private String label;
	private Collection<Mot> mots;
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public void setMots(Collection<Mot> mots){
		this.mots = mots;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getLabel()
	{
		return this.label;
	}
	
	public Collection<Mot> getMots()
	{
		return this.mots;
	}
	
	/**
	 * Ajoute un mot à un thème
	 * @param word
	 */
	public void addMot(Mot mot)
	{
		this.mots.add(mot);
	}
	
	public String getThemeById(int id)
	{
		if(this.id == id)
		{
			return this.label;
		}
		else
		{
			return "theme non trouvé";
		}
	}
	
	public int getIdThemeByLabel(String label){
		if(this.label == label){
			return this.id;
		}
		else{
			return -1;
		}
	}
	
}
