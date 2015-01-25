package com.test.pocr.code;

import org.junit.Assert;
import org.junit.Test;

import com.sun.codemodel.JClassAlreadyExistsException;

public class ManagedBeanGeneratorTest {

	private static String CLASS_NAME = "com.test.pocr.TestBean";

	public void addOkProperty() throws JClassAlreadyExistsException {

		final ManagedBeanBuilder builder = new ManagedBeanBuilder(CLASS_NAME);

		final String fieldName = "camp1";
		builder.addProperty(fieldName, int.class);

		final BeanModel model = builder.getModel();

		Assert.assertEquals(1, model.getListOfFields().size());
		Assert.assertEquals(fieldName, model.getListOfFields().get(0));

	}

	@Test(expected = NullPointerException.class)
	public void addNullProperty() throws JClassAlreadyExistsException {

		final ManagedBeanBuilder model = new ManagedBeanBuilder(CLASS_NAME);

		model.addProperty(null, String.class);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addEmptyNameProperty() throws JClassAlreadyExistsException {

		final ManagedBeanBuilder model = new ManagedBeanBuilder(CLASS_NAME);

		model.addProperty("", String.class);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addIllegalProperty() throws JClassAlreadyExistsException {

		final ManagedBeanBuilder model = new ManagedBeanBuilder(CLASS_NAME);

		model.addProperty("goto", String.class);

	}

}
