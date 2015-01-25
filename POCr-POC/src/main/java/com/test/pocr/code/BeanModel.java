package com.test.pocr.code;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JJavaName;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class BeanModel {

	private final JCodeModel cm;
	private final JDefinedClass bean;

	private final List<String> fields;

	private static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	public BeanModel(final String qualifiedName)
			throws JClassAlreadyExistsException {
		cm = new JCodeModel();
		bean = cm._class(qualifiedName);
		fields = new ArrayList<String>();
	}

	protected JCodeModel getCodeModel() {
		return cm;
	}

	public void addAnnotation(final Class<? extends Annotation> annotation) {
		bean.annotate(annotation);
	}

	public void addProperty(final String name, final Class<?> type) {
		validatePropertyName(name);
		addField(name, type);
		addSetter(name, type);
		addGetter(name, type);
	}

	private void addField(final String name, final Class<?> type) {
		bean.field(JMod.PRIVATE, type, name);
		fields.add(name);
	}

	private void validatePropertyName(final String name) {
		if (!JJavaName.isJavaIdentifier(name)) {
			throw new IllegalArgumentException(
					"The property name is not a java identifier.");
		}
		if (getListOfFields().contains(name)) {
			throw new IllegalArgumentException(
					"The property is already defined.");
		}
	}

	protected List<String> getListOfFields() {
		final List<String> fields = new ArrayList<String>();
		fields.addAll(bean.fields().keySet());
		return fields;
	}

	private void addGetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod getter = bean.method(JMod.PUBLIC, type, GET_PREFIX
				+ capitalizedName);
		getter.body()._return(JExpr.ref(name));
	}

	private void addSetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod setter = bean.method(JMod.PUBLIC, void.class, SET_PREFIX
				+ capitalizedName);
		setter.param(String.class, name);
		setter.body().assign(JExpr._this().ref(name), JExpr.ref(name));
	}

}
