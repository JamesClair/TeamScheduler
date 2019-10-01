/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.model.Appointment;
import TeamScheduler.model.Customer;
import TeamScheduler.model.Environment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class ModifyAppointmentController implements Initializable {

	Stage stage;
	Parent scene;
	AppointmentDao apptDao = new AppointmentDao();
	CustomerDao customerDao = new CustomerDao();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

	@FXML
	private TextField idTxt;
	@FXML
	private ChoiceBox<String> customerChoice;
	@FXML
	private TextField typeTxt;
	@FXML
	private TextField titleTxt;
	@FXML
	private TextField descTxt;
	@FXML
	private TextField locationTxt;
	@FXML
	private TextField contactTxt;
	@FXML
	private TextField urlTxt;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
	@FXML
	private TextField startTimeHourTxt;
	@FXML
	private TextField startTimeMinuteTxt;
	@FXML
	private TextField endTimeHourTxt;
	@FXML
	private TextField endTimeMinuteTxt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			ObservableList<String> customers = FXCollections.observableArrayList();
			for (Customer customer : customerDao.getAll()) {
				customers.add(customer.getCustomerName());
			}
			customerChoice.setItems(customers);
		} catch (SQLException ex) {
			Logger.getLogger(ModifyAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void onActionSave(ActionEvent event) throws IOException, SQLException {
		try {
			int id = Integer.parseInt(idTxt.getText());
			Appointment appt = apptDao.get(id);
			
			LocalDate startDate = startDatePicker.getValue();
			LocalDate endDate = endDatePicker.getValue();
			LocalDateTime startLdt = startDate.atTime(
				Integer.parseInt(startTimeHourTxt.getText()), Integer.parseInt(startTimeMinuteTxt.getText())
			);
			LocalDateTime endLdt = endDate.atTime(
				Integer.parseInt(endTimeHourTxt.getText()), Integer.parseInt(endTimeMinuteTxt.getText())
			);

			appt.setType(typeTxt.getText());
			appt.setTitle(titleTxt.getText());
			appt.setDescription(descTxt.getText());
			appt.setLocation(locationTxt.getText());
			appt.setContact(contactTxt.getText());
			appt.setUrl(urlTxt.getText());
			appt.setStart(startLdt.format(dtf));
			appt.setEnd(endLdt.format(dtf));

			apptDao.update(appt);

			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();
		} catch(NumberFormatException | NullPointerException ex) {
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

	public void sendAppointment(int id) throws SQLException {
		Appointment appointment = apptDao.get(id);
		Customer customer = customerDao.get(appointment.getCustomerId());
		LocalDateTime apptStart = LocalDateTime.parse(appointment.getStart(), dtf);
		LocalDateTime apptEnd = LocalDateTime.parse(appointment.getEnd(), dtf);

		idTxt.setText(String.valueOf(id));
		customerChoice.getSelectionModel().select(customer.getCustomerName());
		typeTxt.setText(appointment.getType());
		titleTxt.setText(appointment.getTitle());
		descTxt.setText(appointment.getDescription());
		locationTxt.setText(appointment.getLocation());
		contactTxt.setText(appointment.getContact());
		urlTxt.setText(appointment.getUrl());
		startDatePicker.setValue(LocalDate.parse(appointment.getStart(), dtf));
		startTimeHourTxt.setText(String.valueOf(apptStart.getHour()));
		startTimeMinuteTxt.setText(String.valueOf(apptStart.getMinute()));
		endDatePicker.setValue(LocalDate.parse(appointment.getEnd(), dtf));
		endTimeHourTxt.setText(String.valueOf(apptEnd.getHour()));
		endTimeMinuteTxt.setText(String.valueOf(apptEnd.getMinute()));

	}

}
