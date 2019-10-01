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
public class Country {
	private int countryId;
	private String countryName;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;

	public Country(String countryName) {
		this.countryId = 0;
		this.countryName = countryName;
		this.createDate = "";
		this.createdBy = "";
		this.lastUpdate = "";
		this.lastUpdate = "";
	}
	
	public Country(int countryId, String countryName, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
	}

	Country() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}



	@Override
	public String toString() {
		return "countryId: " + countryId +
			", countryName: " + countryName +
			", createDate: " + createDate +
			", createdBy: " + createdBy +
			", lastUpdate: " + lastUpdate +
			", lastUpdateBy: " + lastUpdateBy;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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


}
