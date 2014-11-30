package com.test.pocr.facelet.xhtml;

public enum NamespaceEnum {
	NO_NAMESPACE("", ""),
	HTML("h", "http://java.sun.com/jsf/html"),
	FACES("f", "http://java.sun.com/jsf/core");

	private static final String SEPARATOR = ":";
	private final String prefix;
	private final String uri;

	private NamespaceEnum(final String prefix, final String uri) {
		this.prefix = prefix;
		this.uri = uri;
	}

	public String getPrefix() {
		return getPrefix(false);
	}

	public String getPrefix(final boolean withSeparator) {
		if (withSeparator && !prefix.isEmpty()) {
			return prefix + SEPARATOR;
		} else {
			return prefix;
		}
	}

	public String getUri() {
		return uri;
	}

}