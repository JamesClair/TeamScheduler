/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AddressDao;
import TeamScheduler.DAO.AppointmentDao;
import TeamScheduler.DAO.CityDao;
import TeamScheduler.DAO.CountryDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.model.Address;
import TeamScheduler.model.Appointment;
import TeamScheduler.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class MainMenuController implements Initializable {
	
	final ToggleGroup group = new ToggleGroup();
 	LocalDateTime now = LocalDateTime.now();
	
	Stage stage;
	Parent scene;
	
	AppointmentDao apptDao = new AppointmentDao();
	CustomerDao customerDao = new CustomerDao();
	AddressDao addressDao = new AddressDao();
	CityDao cityDao = new CityDao();
	CountryDao countryDao = new CountryDao();
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

	@FXML
	private RadioButton MonthRBtn;
	@FXML
	private RadioButton WeekRBtn;	
	@FXML
	private TableView<Appointment> apptTableView;
	@FXML
	private TableColumn<Appointment, String> apptStartCol;
	@FXML
	private TableColumn<Appointment, String> apptEndCol;
	@FXML
	private TableColumn<Appointment, String> apptTitleCol;
	@FXML
	private TableColumn<Appointment, String> apptTypeCol;
	@FXML
	private TableColumn<Appointment, String> apptCustomerCol;
	@FXML
	private TableView<Customer> customerTableView;
	@FXML
	private TableColumn<Customer, String> customerNameCol;
	@FXML
	private TableColumn<Customer, String> customerAddressCol;
	@FXML
	private TableColumn<Customer, String> customerPhoneCol;


	
	
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			MonthRBtn.setToggleGroup(group);
			WeekRBtn.setToggleGroup(group);

			
			apptTableView.setItems(apptDao.getAll());
			/**
			 * Requirement Lambda #2: This Lambda was introduced after originally using PropertyValueFactory object
			 * created from the Appointment class "start" member.  The lambda uses the callback functional interface
			 * to take a Callback object taking a CellDataFeatures<Appointment, String> object (cellData) and returning 
			 * an ObservableValue<String>>().  The ReadOnlyStringWrapper is used to provide an accessible string property
			 * for each appt using the getStart() method.
			 **/
			apptStartCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStart()));
			
			// left the rest of columns alone to compare the two methods. The lambda is much more flexible.
			apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
			apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
			apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
			apptCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName")); 

			customerTableView.setItems(customerDao.getAll());
			customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
			customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
			customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

		} catch (SQLException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	}	

	@FXML
	private void onActionMonthRBtn(ActionEvent event) throws SQLException {
		
 	        LocalDateTime oneMonthFromNow = now.plusMonths(1);
 	        FilteredList<Appointment> filteredData = new FilteredList<>(apptDao.getAll());
 	        
		/**
		 * Requirement Lambda #1: This lambda uses the Predicate functional interface to create a test for whether a row/object
		 * in the filteredData list has a start time between oneMonthAgo and now.  Each row that returns true is 
		 * added to the newly filtered filteredData list.
		 */
		filteredData.setPredicate(row -> {
 	        	LocalDateTime apptStart = LocalDateTime.parse(row.getStart(), dtf);
 	        	LocalDateTime apptEnd = LocalDateTime.parse(row.getEnd(), dtf);
 	        	return (apptStart.isBefore(oneMonthFromNow) && apptStart.isAfter(now)) 
				|| (apptEnd.isBefore(oneMonthFromNow) && apptEnd.isAfter(now));
 	        });

 	        apptTableView.setItems(filteredData);
	}

	@FXML
	private void onActionWeekRBtn(ActionEvent event) throws SQLException {
 	        LocalDateTime oneWeekFromNow = now.plusDays(7);
 	        FilteredList<Appointment> filteredData = new FilteredList<>(apptDao.getAll());
 	        
		filteredData.setPredicate(row -> {
 	        	LocalDateTime apptStart = LocalDateTime.parse(row.getStart(), dtf);
 	        	LocalDateTime apptEnd = LocalDateTime.parse(row.getEnd(), dtf);
 	        	return (apptStart.isBefore(oneWeekFromNow) && apptStart.isAfter(now)) 
				|| (apptEnd.isBefore(oneWeekFromNow) && apptEnd.isAfter(now));
 	        });

 	        apptTableView.setItems(filteredData);
	
	}

	@FXML
	private void onActionAddAppt(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/AddAppointment.fxml"));
		stage.setScene(new Scene(scene));
		stage.show(); 
	}

	@FXML
	private void onActionModifyAppt(ActionEvent event) throws IOException, SQLException {
		int appointmentId = apptTableView.getSelectionModel().getSelectedItem().getAppointmentId();
		FXMLLoader loader = new FXMLLoader();	
		loader.setLocation(getClass().getResource("/TeamScheduler/view/ModifyAppointment.fxml"));
		loader.load();
		
		ModifyAppointmentController apptController = loader.getController();
		apptController.sendAppointment(appointmentId);
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();
		
	}

	@FXML
	private void onActionDeleteAppt(ActionEvent event) throws SQLException {
		//TODO: Handle nullpointer exceptions when someone doesn't have something selected modify buttons.	
		int apptId = apptTableView.getSelectionModel().getSelectedItem().getAppointmentId();
		apptDao.delete(apptId);
	
		apptTableView.setItems(apptDao.getAll());	
	}

	@FXML
	private void onActionAddCustomer(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/AddCustomer.fxml"));
		stage.setScene(new Scene(scene));
		stage.show(); 
	}

	@FXML
	private void onActionModifyCustomer(ActionEvent event) throws IOException, SQLException {
		int customerId = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
		FXMLLoader loader = new FXMLLoader();	
		loader.setLocation(getClass().getResource("/TeamScheduler/view/ModifyCustomer.fxml"));
		loader.load();
		
		ModifyCustomerController customerController = loader.getController();
		customerController.sendCustomer(customerId);
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();

	}

	@FXML
	private void onActionDeleteCustomer(ActionEvent event) throws SQLException {
		int customerId = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
		Customer customer = customerDao.get(customerId);
		Address address = addressDao.get(customer.getAddressId());
		

		/**
		 * Unfortunately with the current design the order of these delete statements matters. 
		 * A joined view in the Dao would have been a better option when deleting data with FK constraints.
		 * Or use a wrapper class to abstract the deletion process from the caller.
		**/
		
		//TODO: Put in a warning that this will delete all associated appts too
		for(Appointment appt : apptDao.getAll()) {
			if(appt.getCustomerId() == customerId)
				apptDao.delete(appt.getAppointmentId());
		}
		
		customerDao.delete(customerId);
		addressDao.delete(address.getAddressId());
		

		if(MonthRBtn.isSelected())
			MonthRBtn.setSelected(false);
		if(WeekRBtn.isSelected())
			WeekRBtn.setSelected(false);

		customerTableView.setItems(customerDao.getAll());
		apptTableView.setItems(apptDao.getAll());
	}

	@FXML
	private void onActionReports(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/Reports.fxml"));
		stage.setScene(new Scene(scene));
		stage.show(); 
	}

	@FXML
	private void onActionLogFile(ActionEvent event) {
	}

	@FXML
	private void onActionExit(ActionEvent event) {
		System.exit(0);
	}
	
}
