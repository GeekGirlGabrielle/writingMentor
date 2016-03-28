package writingMentor.Controller;

import java.util.ArrayList;
import java.util.Collection;

import writingMentor.View.*;

import javax.swing.JPanel;

import writingMentor.Model.DaoObjectImpl;
import writingMentor.Model.Mot;
import writingMentor.Model.Theme;
import writingMentor.Model.ThemeService;

public class ThemeServiceController {
	
	private ControllerDAO controller;
	
	/**
	 * setThemesToModel utiliser cette m�thode ici
	 */
	
	
	
	
	
	/**
	 * renvoie les themes vers la vue
	 * @return 
	 */
	public Collection<Theme> showTheme(){
		controller = new ControllerDAO();
		Collection<Theme> tmp = new ArrayList();
		controller.setThemesToModel();
		
		try {
			 //on r�cup les th�mes du model
			tmp=controller.getModel().getThemes();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		//on renvoie vers la vue
		return tmp;
	}
	
	public Collection<Mot> showMotByTheme(Theme theme){
		controller = new ControllerDAO();
		Collection<Mot> tmp = new ArrayList();
		controller.setMotToModelByTheme(theme);
		
		try {
			 //on r�cup les mot du th�me concern�s depuis le model
			tmp=controller.getModelTheme().getMots();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		//on renvoie vers la vue
		return tmp;
	}
	
	public int getIdByLabelTheme(String label){
		controller = new ControllerDAO();
		controller.createThemeWithId(label);
		System.out.println("fonction getIdByLabelTheme de ThemeServiceController "+label);
		int id;
		id = controller.getModelTheme().getIdThemeByLabel(label);
		System.out.println(id);
		return id;
	}
	
	//TODO quand un changement est effectu� dans la vue on doit envoyer au model
	
	//fonctions qui permettent de mettre � jour la vue
	public Collection<Mot> updateMotsByTheme(Theme theme){
		//TODO coder pour mettre � jour la combobox de mot
		Collection<Mot> updateMots;
		
		return null;
	}
	
	public void updateView()
	{
		//TODO stuff update view
	}
	//TODO ajout de mots 
		//on cr�e un nouvel objet mot et un objet theme et on utilise la m�thode DAO insertMot(Mot mot, Theme theme)
		
		//TODO ajout de th�me
		//on cr�e un nouvel objet theme et on utilise la m�thode DAO insertTheme(Theme theme) throws Exception;
	
}
