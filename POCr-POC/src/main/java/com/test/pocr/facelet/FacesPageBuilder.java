package com.test.pocr.facelet;

import com.test.pocr.application.IGenerator;
import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.facelet.model.PageModel;

public class FacesPageBuilder {

	private final PageModel model;

	public FacesPageBuilder(final String name) {
		model = new PageModel(name);
	}

	public void addComponent(final InputFieldModel component) {
		model.addComponent(component);
	}

	public void addButton(final ButtonModel button) {
		model.addButton(button);
	}

	public IGenerator getGenerator() {
		return new FacesPageGenerator(model);
	}

}
