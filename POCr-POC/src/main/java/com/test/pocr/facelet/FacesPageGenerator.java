package com.test.pocr.facelet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.test.pocr.application.IGenerator;
import com.test.pocr.facelet.model.ButtonModel;
import com.test.pocr.facelet.model.InputFieldModel;
import com.test.pocr.facelet.model.PageModel;
import com.test.pocr.facelet.xhtml.BodyYielder;
import com.test.pocr.facelet.xhtml.CommandButtonYielder;
import com.test.pocr.facelet.xhtml.FormYielder;
import com.test.pocr.facelet.xhtml.HeadYielder;
import com.test.pocr.facelet.xhtml.InputTextYielder;
import com.test.pocr.facelet.xhtml.PageYielder;
import com.test.pocr.facelet.xhtml.Yielder;

public class FacesPageGenerator implements IGenerator {

	private static final String FILE_PATH = "src/main/webapp/";
	private static final String EXTENSION = ".xhtml";

	private final PageModel model;

	public FacesPageGenerator(final PageModel model) {
		this.model = model;
	}

	public String generate() {
		final PageYielder page = new PageYielder();
		page.addChild(new HeadYielder());
		final Yielder body = new BodyYielder();
		page.addChild(body);

		final Yielder form = new FormYielder();
		body.addChild(form);

		for (final InputFieldModel input : model.getComponents()) {
			form.addChild(new InputTextYielder(input));
		}

		for (final ButtonModel button : model.getMenu()) {
			form.addChild(new CommandButtonYielder(button));
		}

		return page.yield();
	}

	public String getRelativePath() {
		return FILE_PATH + model.getName() + EXTENSION;
	}

	public void writeInFolder(final File folder) throws IOException {
		final File page = new File(folder, getRelativePath());
		page.getParentFile().mkdirs();
		page.createNewFile();
		final Writer pw = new PrintWriter(page);
		pw.write(generate());
		pw.flush();
		pw.close();

	}
}
