package com.test.pocr.mvn;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;

public abstract class PomDecorator extends PomBuilder {

	@Override
	public Model getPom() {
		return getBuilder().getPom();
	}

	@Override
	public void buildPom(final String artifactName) {
		getBuilder().buildPom(artifactName);
		decoratePom();
	}

	protected abstract Model decoratePom();

	protected abstract PomBuilder getBuilder();

	@Override
	protected Build getBuild() {
		return getBuilder().getBuild();
	}

}
