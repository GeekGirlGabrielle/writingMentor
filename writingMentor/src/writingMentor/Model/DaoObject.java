package writingMentor.Model;

import java.util.Collection;

public interface DaoObject {
	public void insertMot(Mot mot, Theme theme);
	public void insertTheme(Theme theme) throws Exception;
	public String getDefByLabelMot(String label);
	public int getIdDernierTheme();
	public int getIdDernierMot();
	public Collection<String> getLabelsThemes();
	public int getIdThemeByLabel(String label);
	public Collection<Mot> getMotByTheme(Theme theme);
}
