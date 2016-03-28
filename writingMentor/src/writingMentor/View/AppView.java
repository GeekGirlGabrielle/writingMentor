package writingMentor.View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import writingMentor.Controller.ThemeServiceController;
import writingMentor.Model.Mot;
import writingMentor.Model.Theme;

public class AppView {
	//la vue doit connaitre le controller

	private static final String PANEL_MOT_ID = "mots";
	private static final String PANEL_THEME_ID = "themes";
	private static final String PANEL_CONSULTATION_ID = "consultation";

	
	//permettra de notifier le controller lors de saisies de l'utilisateur
	//objet controller que la vue doit connaitre pour se mettre à jour
	private ThemeServiceController controller;
	
// déclaration des objets à afficher dans les vues
//	private Collection<Mot> mots;
//	private Collection<Theme> themes;

	// déclaration de la fenêtre
	private JFrame application = new JFrame();

	// déclaration des panels pour constituer le menu
	private JPanel panelConsultation = new JPanel();
	private JPanel panelAjoutMots = new JPanel();
	private JPanel panelAjoutThemes = new JPanel();
	private JPanel principal = new JPanel();

	// utilisation de motUser dans plusieurs fonctions
	private JTextField motUser;

	// déclaration des composants du panelAjoutThemes
	private CardLayout cardLayout;

//	private Collection<Theme> getThemeView(){
//		return controller.getTheme();
//	}
	
	public AppView() {
		addFrameProperties();
		createAjoutThemes();
		createAjoutMots();
		createConsultation();
		addPanels();
		ajoutDuMenu();
	}

	public void addFrameProperties() {
		// propriétés de la fenetre
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(500, 647);
		application.setTitle("Writing Mentor");

		// application.getContentPane().setLayout(cardLayout);
		// application.add(principal);
		application.setVisible(true);

		// application.pack();

	}

	// ajout des panels sur l'application
	public void addPanels() {

		// principal.setSize(640, 480);
		// création du cardLayout
		cardLayout = new CardLayout();
		// Application du CardLayout manager sur le panel principal
		principal.setLayout(cardLayout);
		// ajout des sous panels dans le cardLayout
		principal.add(panelAjoutThemes, PANEL_THEME_ID);
		principal.add(panelAjoutMots, PANEL_MOT_ID);
		principal.add(panelConsultation, PANEL_CONSULTATION_ID);
		// définition du Panel principal comme ContentPane
		application.setContentPane(principal);

	}

	// mise en place du panelConsultation
	public void createConsultation() {
		// déclaration des composants
		JLabel titreConsultation = new JLabel("Consultation de mots clés");
		JLabel lblThemeSelect = new JLabel("S\u00E9lectionner un th\u00E8me:");
		JLabel lblMotsCles = new JLabel("Mots clés:");
		JLabel lblResultMotCles = new JLabel("");
		JLabel lblTitreDef = new JLabel("S\u00E9lectionner un mot pour avoir sa d\u00E9finition:");
		JComboBox MotChoice;
		JLabel lblDefinition = new JLabel("");
		
		//déclaration des sous panels
		JPanel titre = new JPanel();
		JPanel theme = new JPanel();
		JPanel mot = new JPanel();
		JPanel definition = new JPanel();
		JPanel choiceMot = new JPanel();
		
		
		// on ajoute le titre dans un panel avec un BoxLayout manager
		titre.setLayout(new FlowLayout());
		titreConsultation.setFont(new Font("Courier New", Font.BOLD, 20));
		titre.add(titreConsultation);
		
		// on ajoute le lbThemeSelect et le ThemeChoice dans un panel avec FlowLayout manager
		theme.setLayout(new FlowLayout());
		lblThemeSelect.setPreferredSize(new Dimension(136, 14));
		JComboBox ThemeChoiceConsult = new JComboBox();
		ThemeChoiceConsult.setPreferredSize(new Dimension(136, 20));
		//ajout d'un listener sur le comboBox
//		ThemeChoiceConsult.addActionListener(new getSelectedTheme(ThemeChoiceAddMot));
		Theme themeO = new Theme();
//		System.out.println(ThemeChoiceConsult.getSelectedItem());
//		ThemeChoiceConsult.actionPerformed(e);
		
		//Ajouter le listener après avoir ajouté l'objet au panel
		controller = new ThemeServiceController();
		setThemeChoice(controller.showTheme(), ThemeChoiceConsult);
		
				
		theme.add(lblThemeSelect);
		theme.add(ThemeChoiceConsult);
		
		
		//sous panel mot pour lblMotsCles et lblResultMotCles
		mot.setLayout(new BoxLayout(mot, BoxLayout.PAGE_AXIS));
		lblResultMotCles.setPreferredSize(new Dimension(334, 192));
		mot.add(lblMotsCles);
		mot.add(lblResultMotCles);
		
		ThemeChoiceConsult.addActionListener(new ComboBoxListener(this, lblResultMotCles, controller));

		//sous panel definition pour lblTitreDef et MotChoice
		definition.setLayout(new FlowLayout());
		definition.add(lblTitreDef);
		MotChoice = new JComboBox();
		MotChoice.setPreferredSize(new Dimension(136, 20));
		definition.add(MotChoice);
		
		//sous panel choiceMot pour lblDefinition 
		choiceMot.setLayout(new FlowLayout());		
		choiceMot.add(lblDefinition);
		lblDefinition.setPreferredSize(new Dimension(334,192));
		
		//on ajoute les sous panels au panel
		panelConsultation.setLayout(new BoxLayout(panelConsultation, BoxLayout.PAGE_AXIS));
		panelConsultation.add(titre);
		panelConsultation.add(theme);
		panelConsultation.add(mot);
		panelConsultation.add(definition);
		panelConsultation.add(choiceMot);		
	}

	// mise en place du panel AjoutThèmes
	public void createAjoutThemes() {
		//Déclaration des JComponents
		JLabel titreAjoutsThemes = new JLabel("Ajout d'un thème");
		JLabel lblThemeToAdd = new JLabel("Thème à ajouter:");
		JTextField themeUser = new JTextField();
		JButton btnAjoutTheme = new JButton("Ajouter");
		
		//Déclaration des sous panels:
		JPanel titre = new JPanel();
		JPanel theme = new JPanel();
		JPanel ajouter = new JPanel();
		
		//sous panel titre
		titre.setLayout(new FlowLayout());
		titreAjoutsThemes.setFont(new Font("Courier New", Font.BOLD, 20));
		titre.add(titreAjoutsThemes);
		
		//sous panel theme
		theme.setLayout(new FlowLayout());
		lblThemeToAdd.setPreferredSize(new Dimension(100, 14));
		themeUser.setPreferredSize(new Dimension(232, 20));
		theme.add(lblThemeToAdd);
		theme.add(themeUser);
		
		//sous panel ajouter
		ajouter.setLayout(new FlowLayout());
		btnAjoutTheme.setPreferredSize(new Dimension(100, 20));
		btnAjoutTheme.addActionListener(new ajouterTheme());
		ajouter.add(btnAjoutTheme);
		
		//ajout des sous panels au panelAjoutThemes
		panelAjoutThemes.setLayout(new BoxLayout(panelAjoutThemes, BoxLayout.PAGE_AXIS));
		panelAjoutThemes.add(titre);
		panelAjoutThemes.add(theme);
		panelAjoutThemes.add(ajouter);
	}

	// mise en place du panelAjoutMots
	public void createAjoutMots() {
		// declaration des JItems
		JLabel titreAjoutsMots = new JLabel("Ajout d'un mot clé");
		JLabel lblwordToAdd = new JLabel("Mot à ajouter:");
		JLabel lblThemeMot = new JLabel("Thème du mot:");
		JLabel lbldefToAdd = new JLabel("Définition du mot:");
		JTextArea defUser = new JTextArea();
		JButton btnAjoutMot = new JButton("Ajouter");
		
		//Déclaration des sous panels
		JPanel titre = new JPanel();
		JPanel ajoutMot = new JPanel();
		JPanel themeMot = new JPanel();
		JPanel definition = new JPanel();
		JPanel ajouter = new JPanel();
		
		//sous panel Titre
		titre.setLayout(new FlowLayout());
		titreAjoutsMots.setFont(new Font("Courier New", Font.BOLD, 20));
		titre.add(titreAjoutsMots);
		
		//sous panel ajoutMot
		ajoutMot.setLayout(new FlowLayout());
		lblwordToAdd.setPreferredSize(new Dimension(100, 14));
		motUser = new JTextField();
		motUser.setPreferredSize(new Dimension(232, 20));
		ajoutMot.add(lblwordToAdd);
		ajoutMot.add(motUser);
		
		//sous panel themeMot
		themeMot.setLayout(new FlowLayout());
		lblThemeMot.setPreferredSize(new Dimension(100, 14));
		
		JComboBox ThemeChoiceAddMot = new JComboBox();
		ThemeChoiceAddMot.setPreferredSize(new Dimension(136, 20));
		themeMot.add(lblThemeMot);
		themeMot.add(ThemeChoiceAddMot);
		controller = new ThemeServiceController();
		setThemeChoice(controller.showTheme(), ThemeChoiceAddMot);
		
		
		
		//sous panel definition
		definition.setLayout(new FlowLayout());
		lbldefToAdd.setPreferredSize(new Dimension(100, 14));
		defUser.setPreferredSize(new Dimension(300, 200));
		definition.add(lbldefToAdd);
		definition.add(defUser);
		
		//sous panel ajouter
		ajouter.setLayout(new FlowLayout());		
		btnAjoutMot.setPreferredSize(new Dimension(100, 20)); 
		ajouter.add(btnAjoutMot);
		//AjoutMots qui génère l'action à faire si on appuie sur le bouton
		btnAjoutMot.addActionListener(new ajouterMot());
		
		//ajout des sous panel au panel panelAjoutMots
		panelAjoutMots.setLayout(new BoxLayout(panelAjoutMots, BoxLayout.PAGE_AXIS));
		panelAjoutMots.add(titre);
		panelAjoutMots.add(ajoutMot);
		panelAjoutMots.add(themeMot);
		panelAjoutMots.add(definition);
		panelAjoutMots.add(ajouter);	 
	}

	// mise en place du menu:
	private void ajoutDuMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu mnFichier = new JMenu("Fichier");
		JMenuItem consultation = new JMenuItem("Consultation de mots clés ");
		JMenuItem ajoutMots = new JMenuItem("Ajout de mots clés");
		JMenuItem ajoutTheme = new JMenuItem("Ajout de thèmes ");
		// ajout des listeners au menu:
		consultation.addActionListener(new afficheConsultation());
		ajoutMots.addActionListener(new afficheAjoutMots());
		ajoutTheme.addActionListener(new afficheAjoutThemes());

		menuBar.add(mnFichier);
		mnFichier.add(consultation);
		mnFichier.add(ajoutMots);
		mnFichier.add(ajoutTheme);

		application.setJMenuBar(menuBar);
		// principal.add(menuBar);

	}
	
	//TODO méthode qui rempli la combox de theme
	public void setThemeChoice(Collection<Theme> themes, JComboBox combo)
	{
		//this.ThemeChoice = new JComboBox();
		if(!themes.isEmpty())
		{
			Iterator<Theme> it = themes.iterator();
			while(it.hasNext()){
				combo.addItem(it.next().getLabel());
				
			}
//			for(Theme theme : themes)
//			combo.addItem(theme.getLabel());
			
			
		}
		else
		{
			combo.addItem("pas de thèmes");
		}
	}
	
	//TODO méthode qui rempli la combobox de mot par thème
	
	
	//TODO méthode qui rempli le label des mots clés
	
	
	// Quand on clique sur le sous menu consultation
	private class afficheConsultation extends AbstractAction {
		public afficheConsultation() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			// affichage du panel consultation
			cardLayout.show(principal, PANEL_CONSULTATION_ID);
			controller = new ThemeServiceController();
//			setThemeChoice(controller.showTheme(), (JComboBox)e.getSource());
		}
	}

	// Quand on clique sur le sous menu ajoutMots
	private class afficheAjoutMots extends AbstractAction {
		public afficheAjoutMots() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			// affichage du panel d'ajouts de mots
			cardLayout.show(principal, PANEL_MOT_ID);
//			controller = new ThemeServiceController();
//			setThemeChoice(controller.showTheme(), ThemeChoice);
		}
	}

	// Quand on clique sur le sous menu ajoutTheme
	private class afficheAjoutThemes extends AbstractAction {
		public afficheAjoutThemes() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			// affichage du panel d'ajout de thème
			cardLayout.show(principal, PANEL_THEME_ID);
		}
	}

	// Quand on clique sur le bouton ajoutMots
	private class ajouterMot extends AbstractAction {
		public ajouterMot() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String text = motUser.getText();
			System.out.println("Texte a ajouter à la BDD " + text);
		}
	}

	//TODO get over with this function cause it's getting quite annoying mf...
//	private class updateMotsByTheme extends AbstractAction{
//		private JComboBox combo;
//		private Theme theme;
//		public updateMotsByTheme(JComboBox combo, Theme theme){
//			super();
//			this.combo = combo;
//			this.theme = theme;
//		}
//		
//		public void actionPerformed(ActionEvent e){
//			Collection<Mot> mots = new ArrayList();
//			mots = controller.showMotByTheme(theme);
//			if(!mots.isEmpty()){
//				for(Mot mot : mots){
//					combo.addItem(mot.getLabel());
//				}
//			}
//			else
//			{
//				combo.addItem("Ce thème ne contient pas encore de mots clés");
//			}
//		}
//	}
	
	private class getSelection extends AbstractAction{
		private JComboBox combo;
		public getSelection(JComboBox combo){
			this.combo = combo;
		}
		public void actionPerformed(ActionEvent e){
			combo.getSelectedItem().toString();
		}
	}
	private class SetMotChoice extends AbstractAction{
		private Theme theme;
		private Collection<Mot> mots;
		public SetMotChoice(Theme theme, Collection<Mot> mots){
			super();
			this.theme = theme;
			this.mots = mots;
		}
		/**
		 * if(!themes.isEmpty())
		{
			for(Theme theme : themes)
			combo.addItem(theme.getLabel());
			
		}
		else
		{
			combo.addItem("pas de thèmes");
		}
		 */
		public void actionPerformed(ActionEvent e)
		{
			
//			theme.setLabel(combo.getSelectedItem().toString());
			
		}

		
	}
	// Quand on clique sur le bouton ajoutMots
	private class ajouterTheme extends AbstractAction {
		public ajouterTheme() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String text = motUser.getText();
			System.out.println("Texte a ajouter à la BDD " + text);

		}
	}
	
//	public String getSaisieTextbox()
//	{
//		return this.motUser.getText();
//	}

	// méthode pour afficher un message box à l'utilisateur:
	private void msgbox(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
