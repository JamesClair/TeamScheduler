/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;

import TeamScheduler.DAO.AddressDao;
import TeamScheduler.DAO.CityDao;
import TeamScheduler.DAO.CountryDao;
import TeamScheduler.DAO.CustomerDao;
import java.sql.SQLException;

/**
 *
 * @author james.clair
 */
public class CustomerFactory {
	
	public static void createCustomer(String name, String addr1, String addr2, String cityName, String zip, String countryName, String phone) throws SQLException {
		
		CountryDao countryDao = new CountryDao();
		countryDao.create(new Country(countryName));

		//System.out.println(countryDao.getId(countryName));
		CityDao cityDao = new CityDao();
		cityDao.create(new City(cityName, countryDao.getId(countryName)));

		AddressDao addressDao = new AddressDao();
		addressDao.create(new Address(addr1, addr2, zip, phone, cityDao.getId(cityName)));
		
		CustomerDao customerDao = new CustomerDao();
		customerDao.create(new Customer(name, addressDao.getId(addr1)));
	}

	public static void updateCustomer(int id, String name, String addr1, String addr2, String zip, String phone, String cityName, String countryName) throws SQLException {
		CustomerDao customerDao = new CustomerDao();
		AddressDao addressDao = new AddressDao();
		CityDao cityDao = new CityDao();
		CountryDao countryDao = new CountryDao();

		Customer customer = customerDao.get(id);
		Address address = addressDao.get(customer.getAddressId());
		City city = cityDao.get(address.getCityId());
		Country country = countryDao.get(city.getCountryId());
		
		customer.setCustomerName(name);
		address.setAddress(addr1);
		address.setAddress2(addr2);
		address.setAddress2(addr2);
		address.setPostalCode(zip);
		address.setPhone(phone);
		city.setCityName(cityName);
		country.setCountryName(countryName);

		customerDao.update(customer);
		addressDao.update(address);
		cityDao.update(city);
		countryDao.update(country);
	}
}
