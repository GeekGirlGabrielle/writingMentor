package writingMentor.Model;

import java.util.ArrayList;
import java.util.Collection;

public class ThemeService 
{
	private Collection<Mot> mots;
	private Collection<Theme> themes;
	
	public ThemeService(){
		this.mots = new ArrayList();
		this.themes = new ArrayList();
	}
	
	/**
	 * Retourne les mots en fonction du thème
	 * @param theme
	 * @return
	 * @throws Exception
	 */
	public Collection<Mot> getMotsByTheme(Theme theme) throws Exception
	{
		if(!theme.getMots().isEmpty()){
			this.mots = theme.getMots();
		}
		else
		{
			throw new Exception("Il n'y pas pas de mots pour ce thème");
		}
		return mots;
	}
	
	public Collection<Theme> getThemes() 
	{
		return this.themes;
	}
	
	public void setThemes(Collection<Theme> themes)
	{

		this.themes = themes;
	}
}
