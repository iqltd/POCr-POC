package com.test.pocr.facelet.xhtml;

public class BodyYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = 3698843930589334825L;
	private static final NamespaceEnum NAMESPACE = NamespaceEnum.HTML;
	private static final String COMPONENT_NAME = "body";
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
