package com.test.pocr.code;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

public class ManagedBeanGeneratorTest {

	private static JDefinedClass bean;

	@BeforeClass
	public static void init() throws JClassAlreadyExistsException {
		final JCodeModel cm = new JCodeModel();
		bean = cm._class("com.test.pocr.TestBean");
	}

	@Test(expected = IllegalArgumentException.class)
	public void addIllegalProperty() {

		final ManagedBeanModel model = new ManagedBeanModel(bean);

		model.addProperty("goto", String.class);

	}

	public void addOkProperty() throws JClassAlreadyExistsException {

		final ManagedBeanModel model = new ManagedBeanModel(bean);

		model.addProperty("camp1", int.class);

		Assert.assertEquals(2, model.getListOfProperties().size());

	}
}
