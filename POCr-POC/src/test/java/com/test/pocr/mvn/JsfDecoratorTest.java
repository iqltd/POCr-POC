package com.test.pocr.mvn;

import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class JsfDecoratorTest {

	@Test
	public void testNominal() {
		final PomBuilder builder = new JsfDecorator(new PomBuilder());
		builder.buildPom("test");

		final Model pom = builder.getPom();

		Assert.assertTrue(pom != null);
		Assert.assertEquals("test", pom.getArtifactId());

		Assert.assertEquals(JsfDecorator.PACKAGING, pom.getPackaging());

		final List<Dependency> dependencies = pom.getDependencies();
		Assert.assertEquals(1, dependencies.size());
		final Dependency jsfDependency = dependencies.get(0);
		Assert.assertEquals(JsfDecorator.GROUP_ID, jsfDependency.getGroupId());
		Assert.assertEquals(JsfDecorator.ARTIFACT_ID,
				jsfDependency.getArtifactId());
		Assert.assertEquals(JsfDecorator.VERSION, jsfDependency.getVersion());
		Assert.assertEquals(JsfDecorator.SCOPE, jsfDependency.getScope());

	}

}
