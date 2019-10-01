/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.Address;
import TeamScheduler.model.Environment;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class AddressDao implements Dao<Address> {


	@Override
	public ObservableList<Address> mapData(ResultSet result) throws SQLException {
		ObservableList<Address> addresses = FXCollections.observableArrayList();

		while (result.next()) {
			Address address = new Address(
				result.getInt("addressId"),
				result.getString("address"),
				result.getString("address2"),
				result.getInt("cityId"),
				result.getString("postalCode"),
				result.getString("phone"),
				result.getString("createDate"),
				result.getString("createdBy"),
				result.getString("lastUpdate"),
				result.getString("lastUpdateBy")
			);

			addresses.add(address);
		}

		return addresses;
	}

	@Override
	public Address get(int addressId) throws SQLException {
		Query.makeQuery("SELECT * FROM address WHERE addressId =" + addressId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //addressId's are unique, only one record will be returned on successful query.	
	}

	@Override
	public ObservableList<Address> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM address;");
		ResultSet result = Query.getResult();
		return mapData(result);
	}

	@Override
	public void create(Address address) {

		Query.makeQuery("INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdateBy, lastUpdate) VALUES ("
			+ "\'" + address.getAddress() + "\', "
			+ "\'" + address.getAddress2() + "\', "
			+ address.getCityId() + ", "
			+ "\'" + address.getPostalCode() + "\', "
			+ "\'" + address.getPhone() + "\', "
			+ "CURRENT_TIMESTAMP, "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "\'" + UserDao.getCurrentUser() + "\', "
			+ "CURRENT_TIMESTAMP);"
		);

	}

	@Override
	public void update(Address address) {
		Query.makeQuery("UPDATE address SET "
			+ "address = \'" + address.getAddress() + "\'"
			+ ", address2 = \'" + address.getAddress2() + "\'"
			+ ", cityId = " + address.getCityId()
			+ ", postalCode = \'" + address.getPostalCode() + "\'"
			+ ", phone = \'" + address.getPhone() + "\'"
			+ ", lastUpdate = CURRENT_TIMESTAMP"
			+ ", lastUpdateBy = \'" + Environment.getInstance().getCurrentUser().getUserName() + "\'"
			+ " WHERE addressId = " + address.getAddressId() + ";"
		);
	}

	@Override
	public void delete(int addressId) {
		Query.makeQuery("DELETE FROM address WHERE addressId =" + addressId + ";");
	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM address WHERE address =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getAddressId();
	}

}
