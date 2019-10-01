/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class CountryDao implements Dao<Country>{

	@Override
	public Country get(int countryId) throws SQLException {
		Query.makeQuery("SELECT * FROM country WHERE countryId =" + countryId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //countryId's are unique, only one record will be returned on successful query.	
	}

	@Override
	public ObservableList<Country> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM country;");
		ResultSet result = Query.getResult();
		return mapData(result);
	}

	@Override
	public void create(Country country) {
		Query.makeQuery("INSERT INTO country (country, createDate, createdBy, lastUpdateBy, lastUpdate) VALUES ("
			+ "\'" + country.getCountryName() + "\', "
			+ "CURRENT_TIMESTAMP, "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "CURRENT_TIMESTAMP);"
		);
	}

	@Override
	public void update(Country country) {
		Query.makeQuery("UPDATE country SET "
			+ "country = \'" + country.getCountryName() + "\'"
			+ ", lastUpdate = CURRENT_TIMESTAMP"
			+ ", lastUpdateBy = \'" + country.getLastUpdateBy() + "\'"
			+ " WHERE countryId = " + country.getCountryId() + ";"
		);
	}

	@Override
	public void delete(int countryId) {
		Query.makeQuery("DELETE FROM country WHERE countryId =" + countryId + ";");
	}

	@Override
	public ObservableList<Country> mapData(ResultSet result) throws SQLException {
		ObservableList<Country> countries = FXCollections.observableArrayList();

		while (result.next()) {
			Country country = new Country(
				result.getInt("countryId"),
				result.getString("country"),
				result.getString("createDate"),
				result.getString("createdBy"),
				result.getString("lastUpdate"),
				result.getString("lastUpdateBy")
			);

			countries.add(country);
		}

		return countries;
	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM country WHERE country =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getCountryId();
	}
	
}
