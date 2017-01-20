package com.acme.etl.extractor;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by vm.andreev on 20.01.17.
 */
public class CSVUserReader implements AutoCloseable, Iterable<Collection<String>> {
    private final BatchedBufferedReader batchedBufferedReader;

    public CSVUserReader(BatchedBufferedReader batchedBufferedReader) {
        this.batchedBufferedReader = batchedBufferedReader;
    }

    @Override
    public void close() throws Exception {
        this.batchedBufferedReader.close();
    }

    @Override
    public Iterator<Collection<String>> iterator() {
        return batchedBufferedReader;
    }

}
