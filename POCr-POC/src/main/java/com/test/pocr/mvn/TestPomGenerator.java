package com.test.pocr.mvn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class TestPomGenerator {

	public static void main(final String[] args) {
		try {
			testGeneratePom();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testGeneratePom() throws IOException {
		final Model model = new Model();
		model.setGroupId("com.test.pocr");
		model.setArtifactId("testGenerated");
		model.setVersion("1.0-SNAPSHOT");

		final List<Dependency> dependencies = new ArrayList<Dependency>();
		final Dependency jsf = new Dependency();
		jsf.setGroupId("javax.faces");
		jsf.setArtifactId("jsf-api");
		jsf.setVersion("2.1");
		jsf.setScope("compile");

		dependencies.add(jsf);

		model.setDependencies(dependencies);

		final Build build = new Build();
		final Plugin wlsMavenPlugin = new Plugin();
		wlsMavenPlugin.setGroupId("com.oracle.weblogic");
		wlsMavenPlugin.setArtifactId("wls-maven-plugin");
		wlsMavenPlugin.setVersion("12.1.3.0");
		final Xpp3Dom wlsConf = new Xpp3Dom("configuration");

		wlsConf.addChild(createElement("middlewareHome", "/opt/wls12130"));
		wlsConf.addChild(createElement("userConfigFile",
				"/home/micul/pocr/sec/userconf.secure"));
		wlsConf.addChild(createElement("userKeyFile",
				"/home/micul/pocr/sec/userkeyfile.secure"));
		wlsConf.addChild(createElement("name", "testGenerated"));

		wlsMavenPlugin.setConfiguration(wlsConf);
		build.addPlugin(wlsMavenPlugin);
		model.setBuild(build);

		final Writer w = new PrintWriter(new File("./target/pom.xml"));

		new MavenXpp3Writer().write(w, model);
	}

	private static Xpp3Dom createElement(final String name, final String value) {
		final Xpp3Dom middlewareHome = new Xpp3Dom(name);
		middlewareHome.setValue(value);
		return middlewareHome;
	}

}
