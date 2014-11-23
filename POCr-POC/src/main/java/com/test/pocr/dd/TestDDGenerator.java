package com.test.pocr.dd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.test.pocr.util.Util;

public class TestDDGenerator {

	private static final String FILE_PATH = Util.ROOT_PATH + "/WEB-INF/web.xml";

	public static void main(final String[] args) {
		try {
			final File dd = new File(FILE_PATH);
			if (dd.exists()) {
				dd.delete();
			}
			final DeploymentDescriptorBuilder builder = new DeploymentDescriptorBuilder(
					"test");
			final List<String> patterns = new ArrayList<String>();
			patterns.add("*.xhtml");
			builder.addServlet("javax.faces.webapp.FacesServlet", patterns);
			builder.writeToFile(dd);
		} catch (final JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
