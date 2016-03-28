/**
 * 
 * @author gbotbol
 */


import java.awt.EventQueue;

import writingMentor.View.AppView;

public class Launcher{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppView appView = new AppView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
