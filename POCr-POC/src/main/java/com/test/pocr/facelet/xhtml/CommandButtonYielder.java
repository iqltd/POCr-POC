package com.test.pocr.facelet.xhtml;

import com.test.pocr.facelet.model.ButtonModel;

public class CommandButtonYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -7446048056694792218L;

	private static final NamespaceEnum NAMESPACE = NamespaceEnum.HTML;
	private static final String COMPONENT_NAME = "commandButton";
	private static final String ATTRIBUTES = "value=\"%s\" action=\"%s\" ";

	private final ButtonModel button;

	public CommandButtonYielder(final ButtonModel button) {
		this.button = button;
	}

	public NamespaceEnum getNamespace() {
		return NAMESPACE;
	}

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected String getAttributes() {
		return String.format(ATTRIBUTES, button.getLabel(), button.getAction());
	}

}
