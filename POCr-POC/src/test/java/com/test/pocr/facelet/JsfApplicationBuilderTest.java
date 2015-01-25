package com.test.pocr.facelet;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.test.pocr.application.ApplicationModel;
import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;

public class JsfApplicationBuilderTest {

	public JsfApplicationBuilderTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testNominal() {
		final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");
		final FormDto form = new FormDto("testForm");
		form.setFields(getFieldList());
		builder.addForm(form);

		final ApplicationModel model = builder.getApplicationModel();

		Assert.assertTrue(model != null);
	}

	private static List<FieldDto> getFieldList() {
		final List<FieldDto> fields = new ArrayList<FieldDto>();
		fields.add(new FieldDto("camp1", String.class));
		fields.add(new FieldDto("camp2", int.class));
		return fields;
	}

}
