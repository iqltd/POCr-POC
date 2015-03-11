package com.test.pocr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.pocr.application.ApplicationDeployer;
import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;
import com.test.pocr.facelet.JsfApplicationBuilder;

public class POCrPOCMain {

	public POCrPOCMain() {
	}

	public static void main(final String[] args) throws IOException {

		final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");
		final FormDto form = new FormDto("testForm");
		form.setFields(getFieldList());
		builder.addForm(form);

		final File outputFolder = builder.getGenerator().generateApplication();
		ApplicationDeployer.deployApplication(outputFolder);

	}

	private static List<FieldDto> getFieldList() {
		final List<FieldDto> fields = new ArrayList<FieldDto>();
		fields.add(new FieldDto("camp1", String.class));
		fields.add(new FieldDto("camp2", int.class));
		return fields;
	}

}
