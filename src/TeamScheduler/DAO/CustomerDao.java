/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.Address;
import TeamScheduler.model.City;
import TeamScheduler.model.Country;
import TeamScheduler.model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class CustomerDao implements Dao<Customer> {

	@Override
	public ObservableList<Customer> mapData(ResultSet result) throws SQLException {

		ObservableList<Customer> customers = FXCollections.observableArrayList();
		AddressDao addressDao = new AddressDao();
		CityDao cityDao = new CityDao();
		CountryDao countryDao = new CountryDao();

		while (result.next()) {
			int addressId = result.getInt("addressId");
			Address address = addressDao.get(addressId);
			City city = cityDao.get(address.getCityId());
			Country country = countryDao.get(city.getCountryId());

			Customer customer = new Customer(
				result.getInt("customerId"),
				result.getString("customerName"),
				addressId,
				result.getInt("active"),
				result.getString("createDate"),
				result.getString("createdBy"),
				result.getString("lastUpdate"),
				result.getString("lastUpdateBy"),
				address.getAddress().concat(", " + address.getAddress2()
					+ ", " + address.getPostalCode()
					+ ", " + city.getCityName()
					+ ", " + country.getCountryName()
					+ "."),	
				address.getPhone()
			);
			customers.add(customer);
		}

		return customers;
	}

	@Override
	public ObservableList<Customer> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM customer;");
		ResultSet result = Query.getResult();
		return mapData(result);
	}

	@Override
	public Customer get(int customerId) throws SQLException {
		Query.makeQuery("SELECT * FROM customer WHERE customerId =" + customerId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //customerId's are unique, only one record will be returned on successful query.

	}

	@Override
	public void create(Customer customer) {
		Query.makeQuery("INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdateBy, lastUpdate) VALUES ("
			+ "\'" + customer.getCustomerName() + "\', "
			+ customer.getAddressId() + ", "
			+ customer.getActive() + ", "
			+ "CURRENT_TIMESTAMP, "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "\'" + customer.getLastUpdateBy() + "\', "
			+ "CURRENT_TIMESTAMP);"
		);

	}

	@Override
	public void update(Customer customer) {
		Query.makeQuery("UPDATE customer SET "
			+ "customerName = \'" + customer.getCustomerName() + "\'"
			+ ", addressId = " + customer.getAddressId()
			+ ", active = " + customer.getActive()
			+ ", lastUpdate = CURRENT_TIMESTAMP"
			+ ", lastUpdateBy = \'" + customer.getLastUpdateBy() + "\'"
			+ " WHERE customerId = " + customer.getCustomerId() + ";"
		);

	}

	@Override
	public void delete(int customerId) {
		Query.makeQuery("DELETE FROM customer WHERE customerId =" + customerId + ";");
	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM customer WHERE customerName =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getCustomerId();
	}

}
