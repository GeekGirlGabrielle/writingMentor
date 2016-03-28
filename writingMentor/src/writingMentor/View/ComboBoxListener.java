package writingMentor.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import writingMentor.Controller.ThemeServiceController;
import writingMentor.Model.Mot;
import writingMentor.Model.Theme;

public class ComboBoxListener implements ActionListener {
	private AppView vue;
	private JLabel label;
	private ThemeServiceController controller;
	
	//TODO peut être en profiter aussi pour remplir la comboBox de consultation
	
	public ComboBoxListener(AppView vue, JLabel label, ThemeServiceController controller){
		this.vue = vue;
		this.label = label;
		this.controller = controller;
	}
	
	//TODO faire marcher cette action qui sert à afficher les mots clés aux bons endroits
	@Override
	public void actionPerformed(ActionEvent e) {
		String textLbl="";
		String labelTheme = ((JComboBox)e.getSource()).getSelectedItem().toString();
		System.out.println(labelTheme);
		//label.setText(labelTheme); fonctionne bien
		//Ne marche plus à partir d'ici:
//		Theme theme = new Theme();
//		theme.setLabel(labelTheme);
//		theme.setId(theme.getIdThemeByLabel(labelTheme));
//		Collection<Mot> tmp = new ArrayList();
//		tmp = controller.showMotByTheme(theme);
//		
//		for(Mot mot: tmp){
//			textLbl = textLbl+", "+mot.getLabel();
//		}
//		label.setText(textLbl);
		
		
		//System.out.println(e.getID()+str);
		
		//renvoyer vers la vue le texte selectionné
		
	}

}
