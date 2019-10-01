/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author james.clair
 */
public class Appointment {
	private int appointmentId;
	private int customerId;
	private int userId;
	private String title;
	private String description;
	private String location;
	private String contact;
	private String type;
	private String url;
	private String start;
	private String end;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;
	private String userName;
	private String customerName;

	public Appointment(int appointmentId, int customerId, int userId, String title, String description, String location, String contact, String type, String url, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, String userName, String customerName) {
		this.appointmentId = appointmentId;
		this.customerId = customerId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.type = type;
		this.url = url;
		this.start = start;
		this.end = end;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
		this.userName = userName;
		this.customerName = customerName;
	}

	public Appointment(int customerId, String title, String description, String location, String contact, String type, String url, String start, String end) {
		this.customerId = customerId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.type = type;
		this.url = url;
		this.start = start;
		this.end = end;
	
	}

	@Override
	public String toString() {
		return "appointmentId: " + appointmentId +
			", customerId: " + customerId +
			", userId: " + userId +
			", title: " + title +
			", description: " + description +
			", location: " + location +
			", contact: " + contact +
			", type: " + type +
			", url: " + url +
			", start: " + start +
			", end: " + end +
			", createDate: " + createDate +
			", createdBy: " + createdBy +
			", lastUpdate: " + lastUpdate +
			", lastUpdateBy: " + lastUpdateBy +
			", userName: " + userName +
			", customerName: " + customerName;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


}


