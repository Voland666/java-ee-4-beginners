package com.acme.etl.extractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class CSVBufferedReader implements AutoCloseable, Iterator {
    private final int batchSize;
    private final BufferedReader bufferedReader;
    private Collection<String> nextBatch = null;

    public CSVBufferedReader(int batchSize, BufferedReader bufferedReader) {
        this.batchSize = batchSize;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void close() throws Exception {
        this.bufferedReader.close();
    }

    @Override
    public boolean hasNext() {
        nextBatch = next();
        return nextBatch.size() > 0;
    }

    @Override
    public Collection<String> next() {
        Collection<String> result;
        if (nextBatch == null) {
            result = fetchBatch();
        } else {
            result = nextBatch;
            nextBatch = fetchBatch();
        }
        return result;
    }

    private Collection<String> fetchBatch() {
        Collection<String> batchLines = new ArrayList<>();
        String batchLine;
        for (int counter = 0; counter < batchSize; counter++) {
            try {
                batchLine = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("The bufferReader can not read line");
                break;
            }
            if (batchLine != null) {
                batchLines.add(batchLine);
            } else {
                break;
            }
        }
        return batchLines;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException("forEachRemaining");
    }
}
