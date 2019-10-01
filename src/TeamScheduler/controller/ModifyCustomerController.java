/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.controller;

import TeamScheduler.DAO.AddressDao;
import TeamScheduler.DAO.CityDao;
import TeamScheduler.DAO.CountryDao;
import TeamScheduler.DAO.CustomerDao;
import TeamScheduler.model.Address;
import TeamScheduler.model.City;
import TeamScheduler.model.Country;
import TeamScheduler.model.Customer;
import TeamScheduler.model.CustomerFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class ModifyCustomerController implements Initializable {

	Stage stage;
	Parent scene;

	@FXML
	private TextField idTxt;
	@FXML
	private TextField nameTxt;
	@FXML
	private TextField addr1Txt;
	@FXML
	private TextField addr2Txt;
	@FXML
	private TextField cityTxt;
	@FXML
	private TextField zipTxt;
	@FXML
	private TextField countryTxt;
	@FXML
	private TextField phoneTxt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@FXML
	private void onActionSave(ActionEvent event) throws IOException, SQLException {
		int id = Integer.parseInt(idTxt.getText());
		String name = nameTxt.getText();
		String addr1 = addr1Txt.getText();
		String addr2 = addr2Txt.getText();
		String cityName = cityTxt.getText();
		String zip = zipTxt.getText();
		String countryName = countryTxt.getText();
		String phone = phoneTxt.getText();

		CustomerFactory.updateCustomer(id, name, addr1, addr2, zip, phone, cityName, countryName);

		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	private void onActionMainMenu(ActionEvent event) throws IOException {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/TeamScheduler/view/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	public void sendCustomer(int id) throws SQLException {
		CustomerDao customerDao = new CustomerDao();
		AddressDao addressDao = new AddressDao();
		CityDao cityDao = new CityDao();
		CountryDao countryDao = new CountryDao();

		Customer customer = customerDao.get(id);
		Address address = addressDao.get(customer.getAddressId());
		City city = cityDao.get(address.getCityId());
		Country country = countryDao.get(city.getCountryId());

		idTxt.setText(String.valueOf(id));
		nameTxt.setText(customer.getCustomerName());
		addr1Txt.setText(address.getAddress());
		addr2Txt.setText(address.getAddress2());
		cityTxt.setText(city.getCityName());
		zipTxt.setText(address.getPostalCode());
		countryTxt.setText(country.getCountryName());
		phoneTxt.setText(address.getPhone());

	}
}
