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
public class City {
	private int cityId;
	private String cityName;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;
	private int countryId;

	public City(String cityName, int countryId) {
		this.cityId = 0;
		this.cityName = cityName;
		this.createDate = "";
		this.createdBy = "";
		this.lastUpdate = "";
		this.lastUpdateBy = "";
		this.countryId = countryId;
	}

	public City(int cityId, String cityName, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int countryId) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
		this.countryId = countryId;
	}


	@Override
	public String toString() {
		return "cityId: " + cityId +
			", cityName: " + cityName +
			", createDate: " + createDate +
			", createdBy: " + createdBy +
			", lastUpdate: " + lastUpdate +
			", lastUpdateBy: " + lastUpdateBy +
			", countryId: " + countryId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}	

	
}
