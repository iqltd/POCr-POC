package com.test.pocr.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import com.test.pocr.mvn.PomBuilder;

public class ApplicationGenerator {

	public static final String POM_XML = "/pom.xml";

	public static final String PATH_PREFIX = "./target/";

	public static final String PATH_SEPARATOR = "/";

	private final ApplicationModel model;
	private final String outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = model.getName();
	}

	public boolean generateApplication() throws IOException {
		createFolders();
		writePom(getPom(model));
		writeArtifacts(model.getBeans());
		writeArtifacts(model.getConfigurationFiles());

		return true;
	}

	private void createFolders() {
		final File output = new File(PATH_PREFIX + outputFolder);
		if (output.exists()) {
			output.delete();
		}
		output.mkdir();

	}

	private Model getPom(final ApplicationModel model) {
		final PomBuilder pomBuilder = model.getPomBuilder();
		pomBuilder.buildPom(model.getName());
		return pomBuilder.getPom();
	}

	private void writePom(final Model pom) throws FileNotFoundException,
	IOException {
		final File pomXml = new File(PATH_PREFIX + outputFolder + POM_XML);
		pomXml.createNewFile();
		final Writer w = new PrintWriter(pomXml);
		new MavenXpp3Writer().write(w, pom);
	}

	private void writeArtifacts(final Map<String, Writable> artifacts)
			throws IOException {
		for (final Entry<String, Writable> entry : artifacts.entrySet()) {
			String path = entry.getKey();
			if (!path.startsWith(PATH_SEPARATOR)) {
				path = PATH_SEPARATOR + path;
			}
			final Writable artifact = entry.getValue();
			artifact.writeToFile(new File(PATH_PREFIX + outputFolder + path));
		}
	}

}
