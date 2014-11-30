package com.test.pocr.facelet;

import java.io.Serializable;

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

public class FacesPageGenerator implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2483547502697294122L;

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
}
