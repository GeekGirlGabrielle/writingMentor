package writingMentor.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnection {
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * URL de connection
	 */
	private static String url = "jdbc:sqlite:writingMentor.db";
	
	
	
	/**
	 * Méthode qui va retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url);
				System.out.println("Connection successful");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return connect;	
	}
	
	

}
