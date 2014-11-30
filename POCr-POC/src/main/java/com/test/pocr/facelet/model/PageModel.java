package com.test.pocr.facelet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7255084228885882934L;

	List<InputFieldModel> components = new ArrayList<InputFieldModel>();

	List<ButtonModel> menu = new ArrayList<ButtonModel>();

	public void addComponent(final InputFieldModel component) {
		components.add(component);
	}

	public void addButton(final ButtonModel button) {
		menu.add(button);
	}

	public List<InputFieldModel> getComponents() {
		return components;
	}

	public List<ButtonModel> getMenu() {
		return menu;
	}

}
