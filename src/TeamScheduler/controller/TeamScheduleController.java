/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.model.Appointment;
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
public class TeamScheduleController implements Initializable {
	Stage stage;
	Parent scene;	
	
	AppointmentDao apptDao = new AppointmentDao();	
	
	@FXML
	private TableView<Appointment> teamScheduleTableView;
	@FXML
	private TableColumn<Appointment, String> consultantCol;
	@FXML
	private TableColumn<Appointment, String> startCol;
	@FXML
	private TableColumn<Appointment, String> endCol;
	@FXML
	private TableColumn<Appointment, String> titleCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			teamScheduleTableView.setItems(apptDao.getAll());
			consultantCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
			startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
			endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
			titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		} catch (SQLException ex) {
			Logger.getLogger(TeamScheduleController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}	

	@FXML
	private void onActionBack(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/Reports.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
}
