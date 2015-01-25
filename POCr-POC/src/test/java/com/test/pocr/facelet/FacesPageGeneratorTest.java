package com.test.pocr.facelet;

import org.junit.Assert;
import org.junit.Test;

import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.facelet.model.PageModel;

public class FacesPageGeneratorTest {

	@Test
	public void nominal() {
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

		final String expectedField = "<h:inputText required=\"false\" label=\"field1\"value=\"#{user.name}\" ></h:inputText>";
		final String expectedButton = "<h:commandButton value=\"test\" action=\"null\" ></h:commandButton>";

		final String generatedPage = generator.generate();
		Assert.assertTrue(generatedPage.contains(expectedButton));
		Assert.assertTrue(generatedPage.contains(expectedField));
	}
}