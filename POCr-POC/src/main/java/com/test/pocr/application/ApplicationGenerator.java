package com.test.pocr.application;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ApplicationGenerator {

	public static final String PATH_PREFIX = "./target/";

	private final ApplicationModel model;
	private final File outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = new File(PATH_PREFIX + model.getName());
	}

	/**
	 * Generates the files of the application on the disk
	 *
	 * @throws IOException
	 */
	public void generateApplication() throws IOException {
		recreateFolder();
		writeArtifacts(model.getArtifacts());
	}

	private void recreateFolder() {
		if (outputFolder.exists()) {
			outputFolder.delete();
		}
		outputFolder.mkdir();

	}

	private void writeArtifacts(final List<IGenerator> artifacts)
			throws IOException {

		for (final IGenerator artifact : artifacts) {
			artifact.writeInFolder(outputFolder);
		}
	}
}
