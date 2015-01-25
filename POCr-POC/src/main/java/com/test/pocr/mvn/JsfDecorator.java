package com.test.pocr.mvn;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

public class JsfDecorator extends PomDecorator {

	public static final String GROUP_ID = "javax.faces";
	public static final String ARTIFACT_ID = "jsf-api";
	public static final String VERSION = "2.1";
	public static final String SCOPE = "compile";

	public static final String PACKAGING = "war";

	private final PomBuilder builder;

	public JsfDecorator(final PomBuilder builder) {
		this.builder = builder;
	}

	@Override
	protected Model decoratePom() {
		builder.getPom().setPackaging(PACKAGING);

		final Dependency jsf = new Dependency();
		jsf.setGroupId(GROUP_ID);
		jsf.setArtifactId(ARTIFACT_ID);
		jsf.setVersion(VERSION);
		jsf.setScope(SCOPE);

		builder.getPom().addDependency(jsf);
		return builder.getPom();

	}

	@Override
	protected PomBuilder getBuilder() {
		return builder;
	}

}
