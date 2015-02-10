package com.test.pocr.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	// public static final String M2_HOME = "/usr/local/maven";
	public static final String M2_HOME = System.getenv("M2_HOME");

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
		final List<String> goals = new ArrayList<String>();
		goals.add("clean");
		goals.add("install");
		goals.add("com.oracle.weblogic:wls-maven-plugin:deploy");

		request.setGoals(goals);
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
