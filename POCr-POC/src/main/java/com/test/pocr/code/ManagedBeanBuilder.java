package com.test.pocr.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.text.WordUtils;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JJavaName;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class ManagedBeanBuilder {

	private final JCodeModel cm;
	private final JDefinedClass bean;
	private static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	/**
	 *
	 * @param bean
	 * @throws JClassAlreadyExistsException
	 */
	public ManagedBeanBuilder(final String qualifiedClassName)
			throws JClassAlreadyExistsException {

		cm = new JCodeModel();
		bean = cm._class(qualifiedClassName);
		bean.annotate(ManagedBean.class);
		bean.annotate(SessionScoped.class);
	}

	/**
	 *
	 * @param name
	 * @param type
	 */
	public void addProperty(final String name, final Class<?> type) {

		validatePropertyName(name);
		addField(name, type);
		addSetter(name, type);
		addGetter(name, type);
	}

	public void writeToFile(final File file) throws IOException {
		cm.build(file);
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

	protected List<String> getListOfMethods() {
		final Collection<JMethod> methods = bean.methods();
		final List<String> methodNames = new ArrayList<String>();
		for (final JMethod method : methods) {
			methodNames.add(method.name());
		}
		return methodNames;
	}

	protected List<String> getListOfFields() {
		final List<String> fields = new ArrayList<String>();
		fields.addAll(bean.fields().keySet());
		return fields;
	}

	private void addField(final String name, final Class<?> type) {
		bean.field(JMod.PRIVATE, type, name);
	}

	private void addGetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod getCamp = bean.method(JMod.PUBLIC, type, GET_PREFIX
				+ capitalizedName);
		getCamp.body()._return(JExpr.ref(name));
	}

	private void addSetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod setCamp = bean.method(JMod.PUBLIC, void.class, SET_PREFIX
				+ capitalizedName);
		setCamp.param(String.class, name);
		setCamp.body().assign(JExpr._this().ref(name), JExpr.ref(name));
	}

}
