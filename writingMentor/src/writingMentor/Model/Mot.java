/**
 * 
 * @author gbotbol
 */
package writingMentor.Model;

import javax.swing.event.EventListenerList;

public class Mot 
{
	private int idTheme;
	private String label;
	private String def;
	
	
	/**
	 * returns a label of a word
	 * 
	 * @return
	 */
	public String getLabel()
	{
		return this.label;
	}
	
	public int getIdTheme()
	{
		return this.idTheme;
	}
	
	public String getDef()
	{
		return this.def;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setIdTheme(int idTheme)
	{
		this.idTheme = idTheme;
	}
	
	public void setDef(String def)
	{
		this.def = def;
	}
	
}
