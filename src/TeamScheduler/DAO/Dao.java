/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 * @param <T>
 * This interface is implemented by all DAOs to ensure all basic CRUD operations and any useful helper functions are supported.
 * Each DAO is associated to a DB table. 
 */
public interface Dao<T> {
	
	/**
	 *
	 * @param id
	 * @return 
	 */

	
	T get(int id) throws SQLException;
	ObservableList<T> getAll() throws SQLException;

	void create(T t);

	void update(T t);

	void delete(int id);

	ObservableList<T> mapData(ResultSet result) throws SQLException;

	int getId(String name) throws SQLException;
}
