/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author james.clair
 */
public class ApptType {
	private StringProperty nameProp = new SimpleStringProperty();
	private IntegerProperty countProp = new SimpleIntegerProperty();

	public ApptType(String name, int count) {
		nameProp.set(name);
		countProp.set(count);
	}

	public String getName() {
		return nameProp.get();
	}

	public int getCount() {
		return countProp.get();
	}

	public StringProperty getNameProp() {
		return nameProp;
	}

	public IntegerProperty getCountProp() {
		return countProp;
	}

	public void setName(String name) {
		nameProp.set(name);
	}

	public void setCount(int count) {
		countProp.set(count);
	}

}
