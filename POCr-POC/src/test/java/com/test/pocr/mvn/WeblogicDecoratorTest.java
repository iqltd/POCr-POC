package com.test.pocr.mvn;

import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.junit.Assert;
import org.junit.Test;

public class WeblogicDecoratorTest {

	@Test
	public void test() {
		final PomBuilder builder = new WeblogicDecorator(new PomBuilder());
		builder.buildPom("test");

		final Model pom = builder.getPom();

		Assert.assertTrue(pom != null);
		Assert.assertEquals("test", pom.getArtifactId());

		final List<Plugin> plugins = pom.getBuild().getPlugins();
		Assert.assertEquals(1, plugins.size());
		Assert.assertEquals(WeblogicDecorator.GROUP_ID, plugins.get(0)
				.getGroupId());
		Assert.assertEquals(WeblogicDecorator.ARTIFACT_ID, plugins.get(0)
				.getArtifactId());
		Assert.assertEquals(WeblogicDecorator.VERSION, plugins.get(0)
				.getVersion());

	}

}
