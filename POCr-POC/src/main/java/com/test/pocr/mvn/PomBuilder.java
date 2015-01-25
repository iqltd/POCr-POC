package com.test.pocr.mvn;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Build;
import org.apache.maven.model.Model;

public class PomBuilder {

	private static final String VERSION = "1.0-SNAPSHOT";
	private static final String GROUP_ID = "com.test.pocr";
	private static final String DEFAULT_ARTIFACT_NAME = "PocrGeneratedApp";

	private final Model pom;

	public PomBuilder() {
		pom = new Model();
	}

	public void buildPom(final String artifactName) {
		pom.setGroupId(GROUP_ID);
		pom.setArtifactId(StringUtils.isEmpty(artifactName) ? DEFAULT_ARTIFACT_NAME
				: artifactName);
		pom.setVersion(VERSION);
	}

	public Model getPom() {
		return pom;
	}

	protected Build getBuild() {
		if (pom.getBuild() == null) {
			pom.setBuild(new Build());
		}
		return pom.getBuild();
	}

}
