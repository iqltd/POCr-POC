package com.test.pocr.code;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.test.pocr.util.Util;

public class ManagedBeanGenerator {

	private static final String FILE_PATH = Util.ROOT_PATH + "/WEB-INF/classes";

	public static void main(final String[] args) {
		try {
			generateManagedBean();
		} catch (final JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void generateManagedBean()
			throws JClassAlreadyExistsException, IOException {
		final JCodeModel cm = new JCodeModel();
		final JDefinedClass bean = cm._class("com.test.pocr.TestBean");

		final ManagedBeanModel model = new ManagedBeanModel(bean);

		model.addProperty("camp1", String.class);
		model.addProperty("camp2", int.class);

		final File file = new File(FILE_PATH);
		if (file.exists()) {
			FileUtils.deleteDirectory(file);
		}
		file.mkdirs();
		cm.build(file);
	}
}
