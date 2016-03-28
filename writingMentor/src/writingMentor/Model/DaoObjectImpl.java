package writingMentor.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DaoObjectImpl implements DaoObject{

	/**
	 * Statement state = DbConnection.getInstance().createStatement();
	 */
	@Override
	public void insertMot(Mot mot, Theme theme) {
		/*
		 * sqlite> INSERT INTO words(label, definition, themeid) 
		 * VALUES ('formation continue','dispositif mis en place pour permettre à un adulte de reprendre ses études',1);
		 * */
		try{
			
			
				Statement stmt = DbConnection.getInstance().createStatement();
				String sql = "Insert INTO words (label, definition, themeid) VALUES ('"+mot.getLabel()+"', '"+mot.getDef()+"','"+theme.getId()+"');";
				stmt.executeUpdate(sql);
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }	
	}

	@Override
	public void insertTheme(Theme theme) throws Exception {
		try{
			Collection<String> tmp;
			tmp = getLabelsThemes();
			for(String label: tmp)
			{
				if(label.equalsIgnoreCase(theme.getLabel()))
				{
					throw new Exception("Ce thème existe déjà");
				}				
			}
			Statement stmt = DbConnection.getInstance().createStatement();
			String sql = "Insert into themes values ("+theme.getId()+", '"+theme.getLabel()+"');";
			stmt.executeUpdate(sql);
		  	}
			catch ( Exception e ) 
			{
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
		    }
	}


	public String getDefByLabelMot(String label) {
		String result="";
		try{
			Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select definition from mots where label = '"+label+"';";
			  ResultSet rset = stmt.executeQuery(sql);
			  result = rset.toString();
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		return result;
		
	}

	@Override
	public int getIdDernierTheme() {
		int id = 0;
		  try{
			  Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select MAX(id) from themes;";
			  ResultSet rset = stmt.executeQuery(sql);
			  id = rset.getInt("MAX(id)"); 
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		  return id;
	}

	@Override
	public int getIdDernierMot() {
		 int id = 0;
		  try{
			  Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select MAX(id) from mots;";
			  ResultSet rset = stmt.executeQuery(sql);
			  id = rset.getInt("MAX(id)"); 
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		  return id;
	}

	@Override
	public Collection<String> getLabelsThemes() {
		Collection<String> labelsThemes = new ArrayList();
		  try{		 
			  Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select label from themes;";
			  ResultSet rset = stmt.executeQuery(sql);
			  while ( rset.next() ) {
			  labelsThemes.add(rset.getString("label"));
			 }
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }  
		  return labelsThemes;
	}
	
	public Collection<Theme> getThemes(){
		Collection<Theme> themes = new ArrayList();
		Collection<Mot> mots = new ArrayList();
		Theme theme = new Theme();
		  try{		 
			  Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select * from themes;";
			  ResultSet rset = stmt.executeQuery(sql);
			  while ( rset.next() ) 
			  {
				  theme.setId(rset.getInt("id"));
				  theme.setLabel(rset.getString("label"));
				  //on récupère les mots du thèmes concerné:
				  mots = getMotByTheme(theme);
				  //on les ajoutes au model
				  if(mots.isEmpty()){
					  System.out.println("pas de mots à ajouter au theme dans daoobjectimpl");
				  }
				  theme.setMots(mots);
				  //on ajoute le thème à la liste
				  themes.add(theme);
			 }
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }  
		  if(themes.isEmpty()){
			  System.out.println("pas de themes dans la collec dans setThemesToModel de ControllerDAO");
		  }
		  return themes;
	}
	
	
	public int getIdThemeByLabel(String label){
		int result=-1;
		try{
			Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select id from themes where label = '"+label+"';";
			  ResultSet rset = stmt.executeQuery(sql);
			  result = rset.getInt("id");
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		return result;
	}
	public Collection<Mot> getMotByTheme(Theme theme){
		Collection<Mot> mots = new ArrayList();
		Mot mot = new Mot();
		try{
			Statement stmt = DbConnection.getInstance().createStatement();
			  String sql = "Select * from words where themeid = '"+theme.getId()+"';";
			  ResultSet rset = stmt.executeQuery(sql);
			  while ( rset.next() ) 
			  {
				  mot.setDef(rset.getString("definition"));
				  mot.setLabel(rset.getString("label"));
				  mot.setIdTheme(rset.getInt("themeid"));
				  mots.add(mot);
			  }
		  }catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		return mots;
	}
}
