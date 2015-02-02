package com.test.pocr.facelet.xhtml;

import org.junit.Assert;
import org.junit.Test;

import com.test.pocr.facelet.model.ButtonModel;

public class ButtonYielderTest {

	@Test
	public void nominalCase() {
		final ButtonModel model = new ButtonModel();
		model.setAction("action");
		model.setLabel("label");

		final CommandButtonYielder yielder = new CommandButtonYielder(model);
		final String actual = yielder.yield();
		final String expected = "<h:commandButton value=\"label\" action=\"action\" ></h:commandButton>";
		Assert.assertEquals(expected, actual);
	}

}
