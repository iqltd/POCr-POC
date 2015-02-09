package com.test.pocr.application;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import com.test.pocr.exception.PocrException;

public class ApplicationGenerator {

	public static final String PATH_PREFIX = "./target/";

	private final ApplicationModel model;
	private final File outputFolder;

	// TODO get it from the environment variables
	public static final String M2_HOME = "/usr/local/maven";

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
		deployApplication();
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

	private void deployApplication() {
		final InvocationRequest request = new DefaultInvocationRequest();
		request.setGoals(Collections
				.singletonList("com.oracle.weblogic:wls-maven-plugin:deploy"));
		request.setBaseDirectory(outputFolder);

		final Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(M2_HOME));

		try {
			final InvocationResult result = invoker.execute(request);
			if (result.getExitCode() != 0) {
				System.out.println("Naspa");
			}
		} catch (final MavenInvocationException e) {
			throw new PocrException("Problem occured at deployment", e);
		}

	}
}
