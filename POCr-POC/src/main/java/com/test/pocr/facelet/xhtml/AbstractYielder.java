package com.test.pocr.facelet.xhtml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractYielder implements Yielder {

	/**
	 *
	 */
	private static final long serialVersionUID = -8494084422485609402L;
	private static final String XHTML_START = "<%s%s %s>";
	private static final String XHTML_END = "</%s%s>";

	private final List<Yielder> children;

	protected AbstractYielder() {
		children = new ArrayList<Yielder>();
	}

	public List<Yielder> getChildren() {
		return children;
	}

	public void addChild(final Yielder child) {
		children.add(child);
	}

	public Set<NamespaceEnum> getNamespaces() {
		final Set<NamespaceEnum> namespaces = new HashSet<NamespaceEnum>();
		namespaces.add(getNamespace());
		for (final Yielder child : children) {
			namespaces.addAll(child.getNamespaces());
		}
		return namespaces;
	}

	public String yield() {
		final StringBuffer xhtml = new StringBuffer(200);
		final String prefix = getNamespace().getPrefix(true);

		xhtml.append(String.format(XHTML_START, prefix, getComponentName(),
				getAttributes()));
		for (final Yielder child : children) {
			xhtml.append(child.yield());
		}
		xhtml.append(String.format(XHTML_END, prefix, getComponentName()));

		return xhtml.toString();
	}

	protected abstract String getComponentName();

	protected abstract String getAttributes();
}
