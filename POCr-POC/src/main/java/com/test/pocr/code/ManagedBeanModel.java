package com.test.pocr.code;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.text.WordUtils;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JJavaName;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class ManagedBeanModel {

	private final JDefinedClass bean;
	private static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	/**
	 *
	 * @param bean
	 */
	public ManagedBeanModel(final JDefinedClass bean) {
		this.bean = bean;
		this.bean.annotate(ManagedBean.class);
		this.bean.annotate(SessionScoped.class);
	}

	/**
	 *
	 * @param name
	 * @param type
	 */
	public void addProperty(final String name, final Class<?> type) {
		if (!JJavaName.isJavaIdentifier(name)) {
			throw new IllegalArgumentException(
					"The property name is not a java identifier.");
		}
		this.addField(name, type);
		this.addSetter(name, type);
		this.addGetter(name, type);
	}

	public Collection<JMethod> getListOfProperties() {
		return this.bean.methods();
	}

	private void addField(final String name, final Class<?> type) {
		this.bean.field(JMod.PRIVATE, type, name);
	}

	private void addGetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod getCamp = this.bean.method(JMod.PUBLIC, type, GET_PREFIX
				+ capitalizedName);
		getCamp.body()._return(JExpr.ref(name));
	}

	private void addSetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod setCamp = this.bean.method(JMod.PUBLIC, void.class,
				SET_PREFIX + capitalizedName);
		setCamp.param(String.class, name);
		setCamp.body().assign(JExpr._this().ref(name), JExpr.ref(name));
	}

}
