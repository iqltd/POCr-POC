package com.test.pocr.facelet;

import org.jdom2.Attribute;
import org.jdom2.Element;

import com.test.pocr.dto.FieldDto;
import com.test.pocr.facelet.xhtml.NamespaceEnum;
import com.test.pocr.util.JdomUtil;

public class InputTextHelper {

	private InputTextHelper() {
	}

	public static final String INPUT_TEXT_ELEM = "inputText";
	public static final String REQUIRED_ATTR = "required";
	public static final String LABEL_ATTR = "label";
	public static final String VALUE_ATTR = "value";

	public static Element getInput(final String beanName, final FieldDto field) {
		final Element input = new Element(INPUT_TEXT_ELEM,
				JdomUtil.getNamespace(NamespaceEnum.HTML));
		input.setAttribute(new Attribute(REQUIRED_ATTR, String.valueOf(field
				.isRequired())));
		input.setAttribute(new Attribute(LABEL_ATTR, field.getName()));
		input.setAttribute(new Attribute(VALUE_ATTR, getFieldValue(beanName,
				field.getName())));
		return input;
	}

	private static String getFieldValue(final String beanName,
			final String fieldName) {
		return "#{" + beanName + "." + fieldName + "}";
	}

}
