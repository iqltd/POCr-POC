package com.test.pocr.facelet;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

import com.test.pocr.dto.FieldDto;

public class InputTextHelperTest {

	@Test
	public void nominalCase() {
		final FieldDto field = new FieldDto();
		field.setName("fieldName");
		field.setRequired(true);

		final Element input = InputTextHelper.getInput("bean", field);
		Assert.assertNotNull(input);
		Assert.assertEquals("fieldName",
				input.getAttributeValue(InputTextHelper.LABEL_ATTR));
		Assert.assertEquals("true",
				input.getAttributeValue(InputTextHelper.REQUIRED_ATTR));
		Assert.assertEquals("#{bean.fieldName}",
				input.getAttributeValue(InputTextHelper.VALUE_ATTR));

	}
}
