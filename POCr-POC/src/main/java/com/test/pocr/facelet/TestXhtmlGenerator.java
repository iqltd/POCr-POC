package com.test.pocr.facelet;

import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.facelet.model.PageModel;

public class TestXhtmlGenerator {

	public static void main(final String[] args) {
		generatePage();
	}

	public static void generatePage() {
		final PageModel page = new PageModel();
		final ButtonModel button1 = new ButtonModel();
		button1.setLabel("test");
		page.addButton(button1);

		final InputFieldModel field1 = new InputFieldModel();
		field1.setLabel("field1");
		field1.setRequired(false);

		page.addButton(button1);
		page.addComponent(field1);

		final FacesPageGenerator generator = new FacesPageGenerator(page);
		System.out.println(generator.generate());
	}

}
