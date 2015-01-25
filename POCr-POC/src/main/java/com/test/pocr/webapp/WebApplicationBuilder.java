package com.test.pocr.webapp;

import java.util.ArrayList;
import java.util.List;

import com.test.pocr.application.ApplicationBuilder;
import com.test.pocr.application.ApplicationModel;

public class WebApplicationBuilder extends ApplicationBuilder {

	public final String DD_PATH = "/WEB-INF/web.xml";

	private final DeploymentDescriptorBuilder ddBuilder;

	public WebApplicationBuilder(final String name) {
		super(name);
		ddBuilder = new DeploymentDescriptorBuilder(name);
	}

	public ApplicationModel getApplicationModel() {
		addConfigurationFile(DD_PATH, ddBuilder);
		return getModel();
	}

	public void addServlet(final String qualifiedClass, final String pattern) {
		final List<String> patterns = new ArrayList<String>();
		patterns.add(pattern);
		addServlet(qualifiedClass, patterns);
	}

	public void addServlet(final String qualifiedClass,
			final List<String> patterns) {
		ddBuilder.addServlet(qualifiedClass, patterns);
	}
}
