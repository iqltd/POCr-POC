package com.test.pocr.facelet.xhtml;

import com.test.pocr.facelet.model.InputFieldModel;

public class InputTextYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -4114595948327199064L;
	private static final NamespaceEnum NAMESPACE = NamespaceEnum.HTML;
	private static final String COMPONENT_NAME = "inputText";
	private static final String ATTRIBUTES = "required=\"%s\" label=\"%s\" value=\"#{%s.%s}\" ";

	private final InputFieldModel input;

	public InputTextYielder(final InputFieldModel input) {
		this.input = input;
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
		return String.format(ATTRIBUTES, input.isRequired(), input.getLabel(),
				input.getBeanName(), input.getLabel());
	}

}
