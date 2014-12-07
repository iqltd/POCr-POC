package com.test.pocr.facelet.xhtml;

public class PageYielder extends AbstractYielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -6705613165953587927L;
	private static final NamespaceEnum NAMESPACE = NamespaceEnum.NO_NAMESPACE;
	private static final String COMPONENT_NAME = "html";
	private static final String BOILERPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
			+ "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> ";

	public NamespaceEnum getNamespace() {
		return NAMESPACE;
	}

	@Override
	public String yield() {
		final StringBuffer page = new StringBuffer(2000);
		page.append(String.format(BOILERPLATE, getAttributes()));
		page.append(super.yield());

		return page.toString();
	}

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected String getAttributes() {
		final StringBuffer nsDeclarations = new StringBuffer();
		for (final NamespaceEnum namespace : getNamespaces()) {
			nsDeclarations.append(namespace.getUri());
		}
		return nsDeclarations.toString();
	}
}
