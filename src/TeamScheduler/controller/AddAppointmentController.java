/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.Exceptions.OverlappingApptException;
import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.Exceptions.OutsideHoursException;
import TeamScheduler.model.Appointment;
import TeamScheduler.model.Customer;
import TeamScheduler.model.Environment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class AddAppointmentController implements Initializable {

	Stage stage;
	Parent scene;

	AppointmentDao apptDao = new AppointmentDao();
	CustomerDao customerDao = new CustomerDao();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

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
			LocalDate startDate = startDatePicker.getValue();
			LocalDate endDate = endDatePicker.getValue();
			LocalDateTime startLdt = startDate.atTime(
				Integer.parseInt(startTimeHourTxt.getText()), Integer.parseInt(startTimeMinuteTxt.getText())
			);
			LocalDateTime endLdt = endDate.atTime(
				Integer.parseInt(endTimeHourTxt.getText()), Integer.parseInt(endTimeMinuteTxt.getText())
			);

			LocalTime workStart = LocalTime.of(8, 00);
			LocalTime workEnd = LocalTime.of(17, 00);

			//Check to ensure new appts are not scheduled between outside the hours of 08:00 - 17:00
			if (!(startLdt.toLocalTime().isBefore(workEnd) && startLdt.toLocalTime().isAfter(workStart))
				|| !(endLdt.toLocalTime().isBefore(workEnd) && endLdt.toLocalTime().isAfter(workStart))) {

				throw new OutsideHoursException("Appointment time outside of working hours.  "
					+ "Please adjust start and end times between 08:00-17:00, in your local timezone.");
			}

			for (Appointment appt : apptDao.getAll()) {
				LocalDateTime apptStartLdt = LocalDateTime.parse(appt.getStart(), dtf);
				LocalDateTime apptEndLdt = LocalDateTime.parse(appt.getEnd(), dtf);
				if ((startLdt.isBefore(apptEndLdt)
					&& apptStartLdt.isBefore(endLdt))) {
					throw new OverlappingApptException("Appointment overlaps another appointment.  "
						+ "Please ensure any new appointments do NOT overlap.");
				} 
			}

			Appointment appt = new Appointment(
				customerDao.getId(customerChoice.getValue()),
				titleTxt.getText(),
				descTxt.getText(),
				locationTxt.getText(),
				contactTxt.getText(),
				typeTxt.getText(),
				urlTxt.getText(),
				startLdt.format(dtf),
				endLdt.format(dtf)
			);
			apptDao.create(appt);

			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();
		} catch (NumberFormatException | NullPointerException | OutsideHoursException | OverlappingApptException ex) {
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
