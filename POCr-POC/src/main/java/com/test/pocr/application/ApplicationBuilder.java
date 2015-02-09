package com.test.pocr.application;

import org.apache.maven.model.Plugin;

import com.test.pocr.mvn.PluginBuilder;
import com.test.pocr.mvn.PomBuilder;
import com.test.pocr.util.FileUtil;

public class ApplicationBuilder {

	private final String NAME_SPACE = "com.pocr.generated";
	private final ApplicationModel model;

	private static final String GROUP_ID = "com.oracle.weblogic";
	private static final String ARTIFACT_ID = "wls-maven-plugin";
	private static final String VERSION = "12.1.3.0";
	private static final String MODEL_VERSION = "4.0.0";

	private static final String CONFIGURATION_FILE = "wls-maven-plugin.properties";

	private final PomBuilder pomBuilder;

	/**
	 * @param name
	 *            the name of the application to be built
	 */
	public ApplicationBuilder(final String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"Cannot build a nameless application");
		}
		model = new ApplicationModel(name);
		pomBuilder = new PomBuilder(name);

		final Plugin wlsPlugin = PluginBuilder.getPluginWithConfiguration(
				GROUP_ID, ARTIFACT_ID, VERSION,
				FileUtil.getConfigurationFromFile(CONFIGURATION_FILE));
		pomBuilder.addBuildPlugin(wlsPlugin);
		pomBuilder.getPomModel().setModelVersion(MODEL_VERSION);
	}

	/**
	 * Adds an artifact generator to the application built
	 *
	 * @param artifactGenerator
	 */
	public void addArtifact(final IGenerator artifactGenerator) {
		model.addArtifact(artifactGenerator);
	}

	/**
	 * Provides access to the pom builder for the subclasses
	 */
	protected PomBuilder getPomBuilder() {
		return pomBuilder;
	}

	/**
	 * Provides a namespace to be used by subclasses for building components of
	 * the application
	 */
	protected String getNamespace() {
		return NAME_SPACE + model.getName().toLowerCase();
	}

	/**
	 * Creates an application generator object
	 *
	 */
	public ApplicationGenerator getGenerator() {
		addSpecificArtifacts();
		return new ApplicationGenerator(model);
	}

	/**
	 * Used to add specific artifacts to the model just before creating the
	 * generator. Don't forget to invoke the parent method first, like in the
	 * example below:
	 *
	 * <pre>
	 * super.addSpecificArtifact();
	 * addArtifact(specificArtifactName);
	 * </pre>
	 */
	protected void addSpecificArtifacts() {
		model.addArtifact(pomBuilder.getGenerator());
	}

	ApplicationModel getApplicationModel() {
		return model;
	}

}
