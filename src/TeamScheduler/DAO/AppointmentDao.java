/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.Appointment;
import TeamScheduler.model.ApptType;
import TeamScheduler.model.CustomerAppt;
import TeamScheduler.model.Environment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class AppointmentDao implements Dao<Appointment> {

	UserDao userDao = new UserDao();
	CustomerDao customerDao = new CustomerDao();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	ZoneId utcZoneId = ZoneId.of("UTC");

	@Override
	public Appointment get(int appointmentId) throws SQLException {
		Query.makeQuery("SELECT * FROM appointment WHERE appointmentId =" + appointmentId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //appointmentId's are unique, only one record will be returned on successful query.	
	}

	@Override
	public ObservableList<Appointment> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM appointment;");
		ResultSet result = Query.getResult();
		return mapData(result);
	}

	@Override
	public void create(Appointment appointment) {

		try {
			Query.makeQuery("INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ("
				+ appointment.getCustomerId() + ", "
				//Example of a ternary, this would be a better pattern for query building 
				//and handling 0 matching records in the DB
				+ ((appointment.getUserId() == 0) ? userDao.getId(Environment.getInstance().getCurrentUser().getUserName()) : appointment.getUserId()) + ", "
				+ "\'" + appointment.getTitle() + "\', "
				+ "\'" + appointment.getDescription() + "\', "
				+ "\'" + appointment.getLocation() + "\', "
				+ "\'" + appointment.getContact() + "\', "
				+ "\'" + appointment.getType() + "\', "
				+ "\'" + appointment.getUrl() + "\', "
				+ "\'" + LocalDateTime
					.parse(appointment.getStart(), dtf)
					.atZone(ZoneId.systemDefault())
					.withZoneSameInstant(utcZoneId)
					.format(dtf) + "\', "
				+ "\'" + LocalDateTime
					.parse(appointment.getEnd(), dtf)
					.atZone(ZoneId.systemDefault())
					.withZoneSameInstant(utcZoneId)
					.format(dtf) + "\', "
				+ "CURRENT_TIMESTAMP, "
				+ "\'" + Environment.getInstance().getCurrentUser().getUserName() + "\', "
				+ "CURRENT_TIMESTAMP, "
				+ "\'" + Environment.getInstance().getCurrentUser().getUserName() + "\');"
			);
		} catch (SQLException ex) {
			Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(Appointment appointment) {
		Query.makeQuery("UPDATE appointment SET "
			+ "title = \'" + appointment.getTitle() + "\'"
			+ ", description = \'" + appointment.getDescription() + "\'"
			+ ", location = \'" + appointment.getLocation() + "\'"
			+ ", contact = \'" + appointment.getContact() + "\'"
			+ ", type = \'" + appointment.getType() + "\'"
			+ ", url = \'" + appointment.getUrl() + "\'"
			+ ", start = \'" + LocalDateTime
					.parse(appointment.getStart(), dtf)
					.atZone(ZoneId.systemDefault())
					.withZoneSameInstant(utcZoneId)
					.format(dtf) + "\'"
			+ ", end = \'" + LocalDateTime
					.parse(appointment.getEnd(), dtf)
					.atZone(ZoneId.systemDefault())
					.withZoneSameInstant(utcZoneId)
					.format(dtf) + "\'"
			+ ", lastUpdate = CURRENT_TIMESTAMP"
			+ ", lastUpdateBy = \'" + Environment.getInstance().getCurrentUser().getUserName() + "\'"
			+ " WHERE appointmentId = " + appointment.getAppointmentId() + ";"
		);
	}

	@Override
	public void delete(int id) {
		Query.makeQuery("DELETE FROM appointment WHERE appointmentId =" + id + ";");
	}

	@Override
	public ObservableList<Appointment> mapData(ResultSet result) throws SQLException {
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();

		while (result.next()) {
			int userId = result.getInt("userId");
			int customerId = result.getInt("customerId");

			Appointment appointment = new Appointment(
				result.getInt("appointmentid"),
				customerId,
				userId,
				result.getString("title"),
				result.getString("description"),
				result.getString("location"),
				result.getString("contact"),
				result.getString("type"),
				result.getString("url"),
				LocalDateTime.parse(result.getString("start"), dtf)
					.atZone(utcZoneId)
					.withZoneSameInstant(ZoneId.systemDefault())
					.format(dtf),
				LocalDateTime.parse(result.getString("end"), dtf)
					.atZone(utcZoneId)
					.withZoneSameInstant(ZoneId.systemDefault())
					.format(dtf),
				result.getString("createdate"),
				result.getString("createdby"),
				result.getString("lastupdate"),
				result.getString("lastupdateby"),
				userDao.get(userId).getUserName(),
				customerDao.get(customerId).getCustomerName()
			);

			appointments.add(appointment);
		}

		return appointments;
	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM address WHERE title =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getAppointmentId();
	}

	public ObservableList<ApptType> apptsByType() throws SQLException {
		ObservableList<ApptType> apptTypes = FXCollections.observableArrayList();
		
		Query.makeQuery("SELECT type, COUNT(*) AS count FROM appointment GROUP BY type;");
		ResultSet result = Query.getResult();
		while (result.next()) {
			ApptType apptType = new ApptType(
				result.getString("type"),
				result.getInt("count")
			);
			apptTypes.add(apptType);
		}
		return apptTypes;
	}

	public ObservableList<CustomerAppt> apptsByCustomer() throws SQLException {
		ObservableList<CustomerAppt> customerAppts = FXCollections.observableArrayList();
		
		Query.makeQuery("SELECT c.customerName, COUNT(*) AS count FROM customer c, appointment a WHERE c.customerId = a.customerId GROUP BY customerName;");
		ResultSet result = Query.getResult();
		while (result.next()) {
			CustomerAppt customerAppt = new CustomerAppt(
				result.getString("customerName"),
				result.getInt("count")
			);
			customerAppts.add(customerAppt);
		}
		return customerAppts;
	}
}
