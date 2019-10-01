/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;


/**
 *
 * @author james.clair
 */
public class Customer {
	private int customerId;
	private String customerName;
	private int addressId;
	private int active;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;
	private String address;
	private String phone;

	public Customer(String customerName) {
		this.customerName = customerName;
	}
	
	public Customer(int customerId, String customerName, int addressId, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, String address, String phone) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.addressId = addressId;
		this.active = active;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
		this.address = address;
		this.phone = phone;
	}

	Customer(String customerName, int addressId) {
		this.customerId = 0;
		this.customerName = customerName;
		this.addressId = addressId;
		this.active = 0;
		this.createDate = "";
		this.createdBy = "";
		this.lastUpdate = "";
		this.lastUpdateBy = "";
		this.address = "";
		this.phone = "";
	}


	@Override
	public String toString() {
		return "customerId: " + customerId +
			", customerName: " + customerName +
			", addressId: " + addressId +
			", active: " + active +
			", createDate: " + createDate +
			", createdBy: " + createdBy +
			", lastUpdate: " + lastUpdate +
			", lastUpdateBy: " + lastUpdateBy +
			", address: " + address +
			", phone: " + phone;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getAddressId() {
		return addressId;
	}

	public int getActive() {
		return active;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
}
