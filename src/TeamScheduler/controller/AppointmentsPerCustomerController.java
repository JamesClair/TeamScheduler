/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.model.CustomerAppt;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class AppointmentsPerCustomerController implements Initializable {
	Stage stage;
	Parent scene;	

	AppointmentDao AppointmentDao = new AppointmentDao();

	@FXML
	private TableView<CustomerAppt> apptPerCustomerTableView;
	@FXML
	private TableColumn<CustomerAppt, String> customerCol;
	@FXML
	private TableColumn<CustomerAppt, Integer> numApptCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			apptPerCustomerTableView.setItems(AppointmentDao.apptsByCustomer());
			customerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			numApptCol.setCellValueFactory(new PropertyValueFactory<>("count"));
			     
			
		} catch (SQLException ex) {
			Logger.getLogger(AppointmentsPerCustomerController.class.getName()).log(Level.SEVERE, null, ex);
		}	}	

	@FXML
	private void onActionBack(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/Reports.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
}
