package com.test.pocr.mvn;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.xml.Xpp3Dom;

import com.test.pocr.util.FileUtil;

public class WeblogicDecorator extends PomDecorator {

	public static final String GROUP_ID = "com.oracle.weblogic";
	public static final String ARTIFACT_ID = "wls-maven-plugin";
	public static final String VERSION = "12.1.3.0";

	private static final String CONFIGURATION = "configuration";

	private static final String CONFIGURATION_FILE = "wls-maven-plugin.properties";

	private final PomBuilder builder;

	public WeblogicDecorator(final PomBuilder builder) {
		this.builder = builder;
	}

	@Override
	protected Model decoratePom() {
		final Plugin wlsMavenPlugin = new Plugin();
		wlsMavenPlugin.setGroupId(GROUP_ID);
		wlsMavenPlugin.setArtifactId(ARTIFACT_ID);
		wlsMavenPlugin.setVersion(VERSION);

		final Xpp3Dom wlsConf = getConfiguration();

		wlsMavenPlugin.setConfiguration(wlsConf);
		builder.getBuild().addPlugin(wlsMavenPlugin);
		return builder.getPom();
	}

	@Override
	protected PomBuilder getBuilder() {
		return builder;
	}

	private Xpp3Dom getConfiguration() {
		final Xpp3Dom wlsConf = new Xpp3Dom(CONFIGURATION);

		final Map<String, String> configuration = getConfigurationFromFile(CONFIGURATION_FILE);

		for (final Entry<String, String> entry : configuration.entrySet()) {
			wlsConf.addChild(PomUtil.createElement(entry.getKey(),
					entry.getValue()));
		}
		return wlsConf;
	}

	private Map<String, String> getConfigurationFromFile(
			final String configurationFile) {
		final Properties conf = FileUtil
				.loadPropertiesFromFile(configurationFile);
		return FileUtil.convertToMap(conf);
	}

}
