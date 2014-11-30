package com.test.pocr.facelet.xhtml;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface Yielder extends Serializable {

	String yield();

	NamespaceEnum getNamespace();

	Set<NamespaceEnum> getNamespaces();

	List<Yielder> getChildren();

	void addChild(final Yielder child);

}