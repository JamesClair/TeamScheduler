/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import TeamScheduler.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class UserDao implements Dao<User>{
	public static int currentUserId;
	
	public static int getCurrentUser() {
		return currentUserId;
	}
	
	public static void setCurrentUser(String userName) throws SQLException {
		Query.makeQuery("SELECT * FROM user WHERE userName = \'" + userName + "\';");
		ResultSet result = Query.getResult();
		while(result.next())
			currentUserId = result.getInt("userId");
	}
	
	
	@Override
	public User get(int userId) throws SQLException {
		Query.makeQuery("SELECT * FROM user WHERE userId =" + userId + ";");
		ResultSet result = Query.getResult();
		return mapData(result).get(0);  //userId's are unique, only one record will be returned on successful query.		
	}

	@Override
	public ObservableList<User> getAll() throws SQLException {
		Query.makeQuery("SELECT * FROM user;");
		ResultSet result = Query.getResult();
		return mapData(result);
		

	}

	@Override
	public void create(User t) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void update(User t) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public ObservableList<User> mapData(ResultSet result) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		
		while (result.next()) {
			User user = new User(
				result.getInt("userId"),
				result.getString("userName"),
				result.getString("password"),
				result.getInt("active"),
				result.getString("createDate"),
				result.getString("createdBy"),
				result.getString("lastUpdate"),
				result.getString("lastUpdateBy")
			);

			users.add(user);	
		}
		return users;	}

	@Override
	public int getId(String name) throws SQLException {
		Query.makeQuery("SELECT * FROM user WHERE userName =\'" + name + "\';");
		ResultSet result = Query.getResult();
		return mapData(result).get(0).getUserId();
	}


}
