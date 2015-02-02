package com.test.pocr.application;

import com.test.pocr.mvn.PomBuilder;
import com.test.pocr.mvn.WeblogicDecorator;

public class ApplicationBuilder {

	private final String NAME_SPACE = "com.pocr.generated";
	private final ApplicationModel model;

	public ApplicationBuilder(final String name) {
		model = new ApplicationModel(name);
		model.setPomBuilder(new WeblogicDecorator(new PomBuilder()));
	}

	protected void addBean(final String path, final IGenerator bean) {
		model.addBean(path, bean);
	}

	protected void addConfigurationFile(final String path,
			final IGenerator confFile) {
		model.addConfigurationFile(path, confFile);
	}

	protected ApplicationModel getModel() {
		return model;
	}

	protected String getNamespace() {
		return NAME_SPACE;
	}
}
