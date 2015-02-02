package com.test.pocr.application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.test.pocr.mvn.PomBuilder;

public class ApplicationModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9173277311569679954L;

	public ApplicationModel(final String name) {
		beans = new HashMap<String, IGenerator>();
		configurationFiles = new HashMap<String, IGenerator>();
		this.name = name;
	}

	private final String name;

	private final Map<String, IGenerator> beans;

	private final Map<String, IGenerator> configurationFiles;

	private PomBuilder pomBuilder;

	public void addBean(final String path, final IGenerator bean) {
		beans.put(path, bean);
	}

	public Map<String, IGenerator> getBeans() {
		return beans;
	}

	public Map<String, IGenerator> getConfigurationFiles() {
		return configurationFiles;
	}

	public void addConfigurationFile(final String path, final IGenerator confFile) {
		configurationFiles.put(path, confFile);
	}

	public PomBuilder getPomBuilder() {
		return pomBuilder;
	}

	public void setPomBuilder(final PomBuilder pomBuilder) {
		this.pomBuilder = pomBuilder;
	}

	public String getName() {
		return name;
	}

}
