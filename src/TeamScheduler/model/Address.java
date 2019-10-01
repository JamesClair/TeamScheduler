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
public class Address {

	private int addressId;
	private String address;
	private String address2;
	private int cityId;
	private String postalCode;
	private String phone;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;

	public Address(String address, String address2, String postalCode, String phone, int cityId) {
		this.addressId = 0;
		this.address = address;
		this.address2 = address2;
		this.cityId = cityId;
		this.postalCode = postalCode;
		this.phone = phone;
		this.createDate = "";
		this.createdBy = "";
		this.lastUpdate = "";
		this.lastUpdateBy = "";
	}
	public Address(int addressId, String address, String address2, int cityId, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdateBy
	) {
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.cityId = cityId;
		this.postalCode = postalCode;
		this.phone = phone;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
	}

	@Override
	public String toString() {
		return "addressId: " + addressId
			+ ", address: " + address
			+ ", address2: " + address2
			+ ", cityId: " + cityId
			+ ", postalCode: " + postalCode
			+ ", phone: " + phone
			+ ", createDate: " + createDate
			+ ", createdBy: " + createdBy
			+ ", lastUpdate: " + lastUpdate
			+ ", lastUpdateBy: " + lastUpdateBy;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

}
