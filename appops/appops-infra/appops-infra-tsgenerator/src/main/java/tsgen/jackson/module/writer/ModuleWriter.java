package tsgen.jackson.module.writer;

import java.io.IOException;
import java.io.Writer;

import tsgen.jackson.module.grammar.Module;

public interface ModuleWriter {
	void write(Module module, Writer writer) throws IOException;
}
