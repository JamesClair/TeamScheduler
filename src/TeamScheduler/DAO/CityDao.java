/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class CityDao implements Dao<City>{

	@Override
	public City get(int cityId) throws SQLException {
		Query.makeQuery("SELECT * FROM city WHERE cityId =" + cityId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //addressId's are unique, only one record will be returned on successful query.	
	}

	@Override
	public ObservableList<City> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM city;");
		ResultSet result = Query.getResult();
		return mapData(result);
	}

	@Override
	public void create(City city) {
		Query.makeQuery("INSERT INTO city (city, createDate, createdBy, lastUpdateBy, lastUpdate, countryId) VALUES ("
			+ "\'" + city.getCityName() + "\', "
			+ "CURRENT_TIMESTAMP, "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "CURRENT_TIMESTAMP, " +
			+ city.getCountryId() + ");"
		);
	}

	@Override
	public void update(City city) {
		Query.makeQuery("UPDATE city SET "
			+ "city = \'" + city.getCityName() + "\'"
			+ ", lastUpdate = CURRENT_TIMESTAMP"
			+ ", lastUpdateBy = \'" + city.getLastUpdateBy() + "\'"
			+ " WHERE cityId = " + city.getCityId() + ";"
		);
	}

	@Override
	public void delete(int cityId) {
		Query.makeQuery("DELETE FROM city WHERE cityId =" + cityId + ";");
	}

	@Override
	public ObservableList<City> mapData(ResultSet result) throws SQLException {
				ObservableList<City> cities = FXCollections.observableArrayList();

		while (result.next()) {
			City city = new City(
				result.getInt("cityId"),
				result.getString("city"),
				result.getString("createDate"),
				result.getString("createdBy"),
				result.getString("lastUpdate"),
				result.getString("lastUpdateBy"),
				result.getInt("countryId")
			);

			cities.add(city);
		}

		return cities;
	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM city WHERE city =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getCityId();
	}
	
}
