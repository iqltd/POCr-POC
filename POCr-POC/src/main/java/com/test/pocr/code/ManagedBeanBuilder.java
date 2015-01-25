package com.test.pocr.code;

import java.io.File;
import java.io.IOException;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.test.pocr.application.Writable;

public class ManagedBeanBuilder implements Writable {

	private static final String ROOT_PACKAGE = "com.test.pocr.";
	private static final String PACKAGE_SEPARATOR = ".";

	private final BeanModel model;

	public ManagedBeanBuilder(final String className)
			throws JClassAlreadyExistsException {

		model = new BeanModel(ROOT_PACKAGE + className.toLowerCase()
				+ PACKAGE_SEPARATOR + className);
		model.addAnnotation(ManagedBean.class);
		model.addAnnotation(SessionScoped.class);
	}

	public BeanModel getModel() {
		return model;
	}

	/**
	 *
	 * @param name
	 * @param type
	 */
	public void addProperty(final String name, final Class<?> type) {
		model.addProperty(name, type);
	}

	public void writeToFile(final File file) throws IOException {
		file.mkdirs();
		model.getCodeModel().build(file);
	}

}
