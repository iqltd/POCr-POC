package com.test.pocr.mvn;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;

import com.test.pocr.application.IGenerator;

public class PomBuilder {

	private static final String VERSION = "1.0-SNAPSHOT";
	private static final String GROUP_ID = "com.test.pocr";
	private static final String DEFAULT_ARTIFACT_NAME = "PocrGeneratedApp";

	private final Model model;

	public PomBuilder(final String artifactName) {
		model = new Model();
		model.setGroupId(GROUP_ID);
		model.setArtifactId(StringUtils.isEmpty(artifactName) ? DEFAULT_ARTIFACT_NAME
				: artifactName);
		model.setVersion(VERSION);
	}

	public IGenerator getGenerator() {
		return new PomGenerator(model);
	}

	public Model getPomModel() {
		return model;
	}

	public void setPackaging(final String packaging) {
		model.setPackaging(packaging);
	}

	public void addDependency(final Dependency dependency) {
		model.addDependency(dependency);
	}

	public void addBuildPlugin(final Plugin plugin) {
		if (model.getBuild() == null) {
			model.setBuild(new Build());
		}
		model.getBuild().addPlugin(plugin);
	}

}
