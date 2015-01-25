package com.test.pocr.facelet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.test.pocr.application.Writable;
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

public class FacesPageBuilder implements Writable {

	private final PageModel model;

	public FacesPageBuilder() {
		model = new PageModel();
	}

	public void addComponent(final InputFieldModel component) {
		model.addComponent(component);
	}

	public void addButton(final ButtonModel button) {
		model.addButton(button);
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

	public void writeToFile(final File f) throws IOException {
		final Writer pw = new PrintWriter(f);
		pw.write(generate());
		pw.flush();
		pw.close();
	}
}
