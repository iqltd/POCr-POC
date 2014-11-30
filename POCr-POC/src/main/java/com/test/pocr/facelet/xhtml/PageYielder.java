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

	private static final String ATTRIBUTES = "xmlns=\"http://www.w3.org/1999/xhtml\" %s";
	private static final String NAMESPACE_ATTR = "xmlns:%s=\"%s\" ";

	public NamespaceEnum getNamespace() {
		return NAMESPACE;
	}

	@Override
	public String yield() {
		final StringBuffer page = new StringBuffer(2000);
		page.append(String.format(BOILERPLATE, getNamespaceDeclarations()));
		page.append(super.yield());

		return page.toString();
	}

	private String getNamespaceDeclarations() {
		final StringBuffer nsDeclarations = new StringBuffer();
		for (final NamespaceEnum namespace : getNamespaces()) {
			nsDeclarations.append(String.format(NAMESPACE_ATTR,
					namespace.getPrefix(), namespace.getUri()));
		}
		return nsDeclarations.toString();
	}

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected String getAttributes() {
		return String.format(ATTRIBUTES, getNamespaceDeclarations());
	}

}
