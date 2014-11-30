package com.test.pocr.facelet.xhtml;

public class FormYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -4506625695387191988L;
	private static final NamespaceEnum NAMESPACE = NamespaceEnum.HTML;
	private static final String COMPONENT_NAME = "form";
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
