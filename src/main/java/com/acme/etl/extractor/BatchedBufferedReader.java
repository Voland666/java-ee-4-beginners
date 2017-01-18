package com.acme.etl.extractor;

import com.acme.etl.exceptions.UserReaderException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class BatchedBufferedReader implements AutoCloseable, Iterator {
    private int batchSize;
    private BufferedReader bufferedReader;
    private boolean hasNextBatch;
    private String nextBatchLine = null;

    public BatchedBufferedReader(int batchSize, BufferedReader bufferedReader) {
        this.batchSize = batchSize;
        this.bufferedReader = bufferedReader;
        this.hasNextBatch = true;
        this.nextBatchLine = null;
    }

    public void close() throws IOException {
        this.bufferedReader.close();
    }

    @Override
    public boolean hasNext() {
        if (hasNextBatch) {
            try {
                this.nextBatchLine = this.bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to read line from file");
                return false;
            }
        }
        return (this.nextBatchLine != null);
    }

    @Override
    public Collection<String> next() {
        Collection<String> batchLines = new HashSet<String>();
        batchLines.add(nextBatchLine);
        for (int counter = 1; counter < batchSize; counter++) {
            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to read line from file");
                hasNextBatch = false;
                nextBatchLine = null;
                break;
            }
            if (line != null) {
                batchLines.add(line);
            } else {
                hasNextBatch = false;
                nextBatchLine = null;
                break;
            }
        }
        return batchLines;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }
}
