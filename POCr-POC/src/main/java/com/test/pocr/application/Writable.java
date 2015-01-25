package com.test.pocr.application;

import java.io.File;
import java.io.IOException;

public interface Writable {

	void writeToFile(File f) throws IOException;

}
