package com.test.pocr.facelet;

import java.util.Arrays;

import org.apache.maven.model.Dependency;

import com.test.pocr.code.ManagedBeanBuilder;
import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;
import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.mvn.DependencyBuilder;
import com.test.pocr.webapp.WebApplicationBuilder;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	public static final String CLASSES_PATH = "/WEB-INF/classes";
	public static final String FACES_SERVLET = "javax.faces.webapp.FacesServlet";
	private static final String[] PATTERNS = { "*.xhtml" };

	public static final String GROUP_ID = "javax.faces";
	public static final String ARTIFACT_ID = "jsf-api";
	public static final String VERSION = "2.1";
	public static final String SCOPE = "compile";

	public JsfApplicationBuilder(final String name) {
		super(name);
		getDdBuilder().addServlet(FACES_SERVLET, Arrays.asList(PATTERNS));
		final Dependency dependency = DependencyBuilder.getScopedDependency(
				GROUP_ID, ARTIFACT_ID, VERSION, SCOPE);
		getPomBuilder().addDependency(dependency);
	}

	public void addForm(final FormDto form) {
		addManagedBean(form);
		addPage(form);
	}

	private void addPage(final FormDto form) {
		final FacesPageBuilder facesPageBuilder = new FacesPageBuilder(
				form.getFormName());

		for (final FieldDto field : form.getFields()) {
			final InputFieldModel component = new InputFieldModel();
			component.setLabel(field.getName());
			component.setRequired(field.isRequired());
			component.setBeanName(form.getFormName().toLowerCase());
			facesPageBuilder.addComponent(component);
		}

		final ButtonModel button = new ButtonModel();
		button.setLabel("Submit");
		facesPageBuilder.addButton(button);

		addArtifact(facesPageBuilder.getGenerator());
	}

	private void addManagedBean(final FormDto form) {
		final ManagedBeanBuilder builder = new ManagedBeanBuilder(
				getNamespace(), form.getFormName());
		for (final FieldDto field : form.getFields()) {
			builder.addProperty(field.getName(), field.getType());
		}

		addArtifact(builder.getGenerator());

	}

}
