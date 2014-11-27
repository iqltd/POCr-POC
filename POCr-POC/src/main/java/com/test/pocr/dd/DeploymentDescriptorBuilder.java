package com.test.pocr.dd;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jcp.xmlns.xml.ns.javaee.FullyQualifiedClassType;
import org.jcp.xmlns.xml.ns.javaee.ObjectFactory;
import org.jcp.xmlns.xml.ns.javaee.ServletMappingType;
import org.jcp.xmlns.xml.ns.javaee.ServletNameType;
import org.jcp.xmlns.xml.ns.javaee.ServletType;
import org.jcp.xmlns.xml.ns.javaee.UrlPatternType;
import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import com.test.pocr.util.Util;

public class DeploymentDescriptorBuilder {

	private final WebAppType web;
	private final ObjectFactory factory;
	private final Set<String> servlets;

	private Marshaller jaxbMarshaller;

	public DeploymentDescriptorBuilder(final String id) {
		web = new WebAppType();
		web.setId(id);
		web.setVersion("2.5");
		factory = new ObjectFactory();
		servlets = new HashSet<String>();
	}

	public void addServlet(final String qualifiedClassName,
			final List<String> patterns) {

		Util.validateQualifiedClassName(qualifiedClassName);
		validatePatterns(patterns);

		final String servletName = getServletName(Util
				.extractClassFromQualifiedName(qualifiedClassName));

		final ServletType servlet = getServlet(qualifiedClassName, servletName);
		getListModules().add(factory.createWebAppTypeServlet(servlet));

		for (final String pattern : patterns) {
			final ServletMappingType servletMapping = getServletMapping(
					pattern, servletName);
			getListModules().add(
					factory.createWebAppTypeServletMapping(servletMapping));
		}
		servlets.add(servletName);
	}

	public void writeToFile(final File out) throws JAXBException {
		setMarshaller();
		jaxbMarshaller.marshal(web, out);
	}

	private void setMarshaller() throws JAXBException, PropertyException {
		if (jaxbMarshaller == null) {
			// create JAXB context and initializing Marshaller
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(WebAppType.class);
			jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			jaxbMarshaller
					.setProperty(
							Marshaller.JAXB_SCHEMA_LOCATION,
							"http://java.sun.com/xml/ns/javaee "
							+ "http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd");
		}
	}

	private String getServletName(final String className) {
		String servletName = className;
		if (servlets.contains(className)) {
			int count = 1;
			while (servlets.contains(className + count)) {
				count++;
			}
			servletName += count;
		}
		return servletName;
	}

	private void validatePatterns(final List<String> patterns) {
		if (patterns.isEmpty()) {
			throw new IllegalArgumentException("Invalid list of patterns");
		}
		// TODO vezi specificatia Servlet 3.1 pentru validare
		for (final String pattern : patterns) {
			if (pattern.contains("<") || pattern.contains(">")
					|| pattern.contains("&gt") || pattern.contains("&lt")) {
				throw new IllegalArgumentException("Url Pattern " + pattern
						+ " invalid");
			}
		}
	}

	private ServletType getServlet(final String className,
			final String servletName) {
		final ServletType servlet = factory.createServletType();

		final ServletNameType nameType = new ServletNameType();
		nameType.setValue(servletName);
		servlet.setServletName(nameType);

		final FullyQualifiedClassType classType = new FullyQualifiedClassType();
		classType.setValue(className);
		servlet.setServletClass(classType);

		return servlet;
	}

	private ServletMappingType getServletMapping(final String pattern,
			final String servletName) {
		final ServletMappingType servletMapping = factory
				.createServletMappingType();

		final ServletNameType nameType = new ServletNameType();
		nameType.setValue(servletName);
		servletMapping.setServletName(nameType);

		final UrlPatternType urlPattern = new UrlPatternType();
		urlPattern.setValue(pattern);
		servletMapping.getUrlPattern().add(urlPattern);

		return servletMapping;
	}

	private List<JAXBElement<?>> getListModules() {
		return web.getModuleNameOrDescriptionAndDisplayName();
	}

}
