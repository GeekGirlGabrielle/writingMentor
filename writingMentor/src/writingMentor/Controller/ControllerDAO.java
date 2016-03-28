package writingMentor.Controller;

import java.util.ArrayList;
import java.util.Collection;

import writingMentor.Model.DbConnection;
import writingMentor.Model.DaoObjectImpl;
import writingMentor.Model.Mot;
import writingMentor.Model.Theme;
import writingMentor.Model.ThemeService;

public class ControllerDAO {
	private DaoObjectImpl dao;
	private ThemeService model;
	private Theme modelTheme;
	
	
	public ThemeService getModel() {
		return model;
	}
	
	public Theme getModelTheme(){
		return modelTheme;
	}


	/**
	 * Ajoute les thèmes depuis la bdd dans le model
	 */
	public void setThemesToModel(){
		model = new ThemeService();
		dao = new DaoObjectImpl();
		Collection<Theme> tmp = new ArrayList();
		tmp = dao.getThemes();
		if(!tmp.isEmpty()){
			model.setThemes(tmp);
		}
	}
	
	/**
	 * Ajoute les mots depuis la bdd dans le model en fonction du thème
	 */
	public void setMotToModelByTheme(Theme theme){
		modelTheme = new Theme();
		dao = new DaoObjectImpl();
		Collection<Mot> tmp = new ArrayList();
		tmp = dao.getMotByTheme(theme);
		if(!tmp.isEmpty()){
			modelTheme.setMots(tmp);
		}
	}
	
	/**
	 * Crée un thème existant dans la base à partir du label pour l'envoyer au model
	 * @param label
	 */
	public void createThemeWithId(String label){
		modelTheme = new Theme();
		dao = new DaoObjectImpl();
		System.out.println(label);
		int id = dao.getIdThemeByLabel(label);
		modelTheme.setId(id);
		modelTheme.setLabel(label);
	}
}
