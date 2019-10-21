/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AddressDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.Exceptions.BlankFieldException;
import TeamScheduler.Exceptions.InvalidDataException;
import TeamScheduler.model.CustomerFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class AddCustomerController implements Initializable {
	CustomerDao customerDao = new CustomerDao();
	AddressDao addressDao = new AddressDao();
	
	Stage stage;
	Parent scene;
	
	@FXML
	private TextField idTxt;
	@FXML
	private TextField nameTxt;
	@FXML
	private TextField addr1Txt;
	@FXML
	private TextField addr2Txt;
	@FXML
	private TextField cityTxt;
	@FXML
	private TextField zipTxt;
	@FXML
	private TextField countryTxt;
	@FXML
	private TextField phoneTxt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}	

	@FXML
	private void onActionSave(ActionEvent event) throws SQLException, IOException {
		try{
			String name = nameTxt.getText();
			String addr1 = addr1Txt.getText();
			String addr2 = addr2Txt.getText();
			String city = cityTxt.getText();
			String zip = zipTxt.getText();
			String country = countryTxt.getText();
			String phone = phoneTxt.getText();


			//Alert on blank fields
			if(name.isEmpty()) {throw new BlankFieldException("name");}
			if(addr1.isEmpty()) {throw new BlankFieldException("addr1");}
			if(addr2.isEmpty()) {throw new BlankFieldException("addr2");}
			if(city.isEmpty()) {throw new BlankFieldException("city");}
			if(zip.isEmpty()) {throw new BlankFieldException("zip");}
			if(country.isEmpty()) {throw new BlankFieldException("country");} 
			if(phone.isEmpty()) {throw new BlankFieldException("phone");} 


			//Alert when data exceeds length of db fields
			if(name.length() > 45) {throw new InvalidDataException("Customer Name should be 45 characters or less.");}
			if(addr1.length()> 50 
				|| addr2.length() > 50) {throw new InvalidDataException("Address fields should be 50 characters or less.");}
			if(city.length() > 50) {throw new InvalidDataException("City should be less than 50 characters.");}
			if(country.length() > 50) {throw new InvalidDataException("Country should be 50 characters or less characters.");}
			
			
			//Alert when fields contain unexpected characters.
			if(!(name.matches("^[A-Za-z.\\s]*"))) {throw new InvalidDataException("Name only accepts characters in "
				+ "A-Z, a-z and periods example: 8765 east park pl.");}
			if(!(addr1.matches("^[A-Za-z0-9.\\s]*"))) {throw new InvalidDataException("Addr1 only accepts characters in "
				+ "A-Z, a-z, 0-9, and periods example: 8765 east park pl.");}
			if(!(addr2.matches("^[A-Za-z0-9.\\s]*"))) {throw new InvalidDataException("Addr2 only accepts characters in "
				+ "A-Z, a-z, 0-9, and periods example: Apt. 4000 Rm. 12");}
			if(!(city.matches("^[A-Za-z.\\s]*"))) {throw new InvalidDataException("City only accepts characters in "
				+ "A-Z, a-z, and periods example: Mt. Pierre");}
			if(!(zip.matches("^[0-9]{5}"))) {throw new InvalidDataException("Zip only accepts 5 digits 0-9 example: 88888");}
			if(!(country.matches("^[A-Za-z0-9.\\s]*"))) {throw new InvalidDataException("Country only accepts characters in "
				+ "A-Z, a-z, 0-9, and periods example: U.S.A.");}
			if(!(phone.matches("^[0-9]{10}"))) {throw new InvalidDataException("Phone only accepts 10 digits 0-9 example: 5559995555");}	
			
			CustomerFactory.createCustomer(name, addr1, addr2, city, zip, country, phone);
			
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();

		} catch ( BlankFieldException | InvalidDataException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Error: unable to save new appointment!");
			alert.setContentText("Please correct any empty or incorrect information and try again.  "
				+ "See the following error message for more details: " + ex);
			alert.showAndWait();

			System.out.println("Unable to save!  Error message: " + ex);
		}
	}

	@FXML
	private void onActionMainMenu(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
}
