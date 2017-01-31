package com.acme.etl.extractor;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by vm.andreev on 20.01.17.
 */
public class BatchedUserReader implements AutoCloseable, Iterable<Collection<String>> {
    private final CSVBufferedReader CSVBufferedReader;

    public BatchedUserReader(CSVBufferedReader CSVBufferedReader) {
        this.CSVBufferedReader = CSVBufferedReader;
    }

    @Override
    public void close() throws Exception {
        this.CSVBufferedReader.close();
    }

    @Override
    public Iterator<Collection<String>> iterator() {
        return CSVBufferedReader;
    }

}
