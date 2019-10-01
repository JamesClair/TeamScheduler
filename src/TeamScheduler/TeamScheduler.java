
package TeamScheduler;

import TeamScheduler.DAO.DBConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author james.clair
 */
public class TeamScheduler extends Application {
	
	
	@Override
	public void start(Stage stage) throws Exception {
	        Parent root = null;

		//Wanted to demonstrate the use of resource bundle propery files as an extensible pattern for supporting more languages
		//and make it easy to extend the pattern to other parts of the app.
		ResourceBundle rb = ResourceBundle.getBundle("language_files/rb");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
	        loader.setResources(rb);
	        
		root = loader.load();	
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		try {
			DBConnection.makeConnection();
			launch(args);

		} catch (SQLException ex) {
			System.out.println("A SQL error has occurred: " + ex);
		} catch (ClassNotFoundException ex) {
			System.out.println("An error occurred while loading the JDBC driver: " + ex);
		} finally {
			
			//Best Practice: After SQLException attempt to close DBConnection if not already closed
			if( DBConnection.conn  != null )
				try {DBConnection.closeConnection();} catch(SQLException ex) {
					System.out.println("Unable to close SQL connection: " + ex);
				}
		}

	}
	
}
