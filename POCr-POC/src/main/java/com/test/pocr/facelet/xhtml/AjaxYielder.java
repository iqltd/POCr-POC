package com.test.pocr.facelet.xhtml;

public class AjaxYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -5130180029798706819L;
	private static final NamespaceEnum NAMESPACE = NamespaceEnum.FACES;
	private static final String COMPONENT_NAME = "ajax";
	private static final String ATTRIBUTES = "";

	public NamespaceEnum getNamespace() {
		return NAMESPACE;
	}

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected String getAttributes() {
		return ATTRIBUTES;
	}

}
