/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AddressDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.Exceptions.BlankFieldException;
import TeamScheduler.Exceptions.OutsideHoursException;
import TeamScheduler.Exceptions.OverlappingApptException;
import TeamScheduler.model.Customer;
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

			if(name.isEmpty()) {throw new BlankFieldException("name");}
			if(addr1.isEmpty()) {throw new BlankFieldException("addr1");}
			if(addr2.isEmpty()) {throw new BlankFieldException("addr2");}
			if(city.isEmpty()) {throw new BlankFieldException("city");}
			if(zip.isEmpty()) {throw new BlankFieldException("zip");}
			if(country.isEmpty()) {throw new BlankFieldException("country");} 
			if(phone.isEmpty()) {throw new BlankFieldException("phone");} 
			
			CustomerFactory.createCustomer(name, addr1, addr2, city, zip, country, phone);
			
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();

		} catch ( BlankFieldException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Error: unable to save new appointment!");
			alert.setContentText("Unable to save!  All fields must have an appropriate value.  "
				+ "Please correct any empty or incorrect information and try again.  "
				+ "See the following error message for more details: " + ex);
			alert.showAndWait();

			System.out.println("Unable to save!  All fields must have an appropriate value.  Error message: " + ex);
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
