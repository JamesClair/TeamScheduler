
package TeamScheduler.controller;

import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.DAO.UserDao;
import TeamScheduler.Exceptions.CredentialsNotFoundException;
import TeamScheduler.model.Appointment;
import TeamScheduler.model.Environment;
import TeamScheduler.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class LoginController implements Initializable {

	private ResourceBundle rb;
	private Stage stage;
	private Parent scene;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	Logger logger = Logger.getLogger("log.txt");
	AppointmentDao apptDao = new AppointmentDao();
	UserDao userDao = new UserDao();
	

	@FXML
	private Label loginPromptLbl;
	@FXML
	private Label usernameLbl;
	@FXML
	private TextField usernameTxt;
	@FXML
	private Label passwordLbl;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button submitBtn;
	@FXML
	private Button exitBtn;
	

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.rb = rb;
		System.out.println(Locale.getDefault());

		loginPromptLbl.setText(rb.getString("loginPromptLbl"));
		usernameLbl.setText(rb.getString("usernameLbl"));
		passwordLbl.setText(rb.getString("passwordLbl"));
		submitBtn.setText(rb.getString("submitBtn"));
		exitBtn.setText(rb.getString("exitBtn"));
	}

	@FXML
	private void onActionLogin(ActionEvent event) throws IOException {
		String username = usernameTxt.getText();
		String password = passwordField.getText();

		FileHandler fileHandler = new FileHandler("log.txt", true);
            	SimpleFormatter simpleFormatter = new SimpleFormatter();
            	fileHandler.setFormatter(simpleFormatter);
            	logger.addHandler(fileHandler);

		try {

			User user = userDao.get(userDao.getId(username));
			ObservableList<Appointment> appts = apptDao.getAll();
			
	
			if (user.getPassword().equals(password)) {
				logger.log(Level.INFO, "Login succeeded for " + username + ".");
				
				Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
				successAlert.setHeaderText(rb.getString("successTitle"));
				successAlert.setContentText(rb.getString("successContent"));
				successAlert.showAndWait();
				
				Environment.getInstance(user, password);
				
				
				// TODO: Getting the data and looping through like this is super slow, could improve this 
				// by using a specific query/view in the dao and creating the object during load.
				for (Appointment appt : appts) {
					if ((LocalDateTime.parse(appt.getStart(), dtf).isAfter(LocalDateTime.now()))
						&& (LocalDateTime.parse(appt.getStart(), dtf).isBefore(LocalDateTime.now().plusMinutes(15)))) {
						Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
						apptAlert.setHeaderText("Appointment starting soon!");
						apptAlert.setContentText("There is an appointment with " + appt.getCustomerName()
							+ " starting at " + appt.getStart() + ".");
						apptAlert.showAndWait();
					}
				}

				stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
				scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
				stage.setScene(new Scene(scene));
				stage.show();

			} else {
				throw new CredentialsNotFoundException("");
			}

			//IndexOutOfBounds happens when username is not found.  CredentialsNotFound is when the password doesn't match.
		} catch (IndexOutOfBoundsException | CredentialsNotFoundException ex) {
			logger.log(Level.SEVERE, "Login failed for " + username + ".");
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(rb.getString("failTitle"));
			alert.setContentText(rb.getString("failContent"));
			alert.showAndWait();

			System.out.println("Login failed, please try again.");

			
		} catch (SQLException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void onActionExit(ActionEvent event) {
		System.exit(0);
	}

}
