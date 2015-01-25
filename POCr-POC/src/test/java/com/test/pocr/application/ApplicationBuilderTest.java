package com.test.pocr.application;

import org.apache.maven.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationBuilderTest {

	public ApplicationBuilderTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testNominal() {
		final ApplicationBuilder builder = new ApplicationBuilder("test");

		final ApplicationModel model = builder.getModel();

		Assert.assertTrue(model != null);

		final Model pom = model.getPomBuilder().getPom();

		Assert.assertTrue(pom != null);
	}

}
