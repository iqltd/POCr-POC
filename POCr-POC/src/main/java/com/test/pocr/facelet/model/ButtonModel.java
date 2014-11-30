package com.test.pocr.facelet.model;

import java.io.Serializable;

public class ButtonModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1900578302196247014L;
	private String label;
	private String action;

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public String getAction() {
		return action;
	}

	public void setAction(final String action) {
		this.action = action;
	}

}
