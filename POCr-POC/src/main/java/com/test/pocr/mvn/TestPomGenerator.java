package com.test.pocr.mvn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

public class TestPomGenerator {

	public static void main(String[] args) {
		try {
			testGeneratePom();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testGeneratePom() throws IOException {
		Model model = new Model();
		model.setGroupId( "com.test.pocr" );
		model.setArtifactId("testGenerated");
		model.setVersion("1.0-SNAPSHOT");
		
		List<Dependency> dependencies = new ArrayList<Dependency>();
		Dependency jsf = new Dependency();
		jsf.setGroupId("javax.faces");
		jsf.setArtifactId("jsf-api");
		jsf.setVersion("2.1");
		jsf.setScope("compile");
		
		dependencies.add(jsf );
		model.setDependencies(dependencies );
		
		
		Writer w = new PrintWriter(new File("./target/pom.xml")); 
		
		new MavenXpp3Writer().write( w, model );
	}
	
	
}
