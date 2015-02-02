package com.test.pocr.facelet;

import com.test.pocr.code.ManagedBeanBuilder;
import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;
import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.mvn.JsfDecorator;
import com.test.pocr.mvn.PomBuilder;
import com.test.pocr.webapp.WebApplicationBuilder;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	public static final String CLASSES_PATH = "/WEB-INF/classes";
	public static final String FACES_SERVLET = "javax.faces.webapp.FacesServlet";
	public static final String PATTERN = "*.xhtml";

	public JsfApplicationBuilder(final String name) {
		super(name);
		addServlet(FACES_SERVLET, PATTERN);
		final PomBuilder pomBuilder = getModel().getPomBuilder();
		getModel().setPomBuilder(new JsfDecorator(pomBuilder));
	}

	public void addForm(final FormDto form) {
		addManagedBean(form);
		addPage(form);
	}

	private void addPage(final FormDto form) {
		final FacesPageBuilder facesPageBuilder = new FacesPageBuilder();

		for (final FieldDto field : form.getFields()) {
			final InputFieldModel component = new InputFieldModel();
			component.setLabel(field.getName());
			component.setRequired(field.isRequired());
			facesPageBuilder.addComponent(component);
		}

		final ButtonModel button = new ButtonModel();
		button.setLabel("Submit");
		facesPageBuilder.addButton(button);

		addConfigurationFile(form.getFormName() + ".xhtml", facesPageBuilder);
	}

	private void addManagedBean(final FormDto form) {
		final ManagedBeanBuilder builder = new ManagedBeanBuilder(
				getNamespace(), form.getFormName());
		for (final FieldDto field : form.getFields()) {
			builder.addProperty(field.getName(), field.getType());
		}

		addBean(CLASSES_PATH, builder.getGenerator());

	}

}
