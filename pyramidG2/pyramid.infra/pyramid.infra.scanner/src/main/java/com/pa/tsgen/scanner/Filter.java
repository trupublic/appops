
package com.pa.tsgen.scanner;

public interface Filter {

    /**
     * If true, the file is accepted, else rejected.
     * 
     * @param filename
     * @return
     */
    boolean accepts(String filename);
}
