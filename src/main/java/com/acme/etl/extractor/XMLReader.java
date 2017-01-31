package com.acme.etl.extractor;

import com.acme.etl.exceptions.UserReaderException;
import com.acme.etl.exceptions.UserWriterException;
import com.acme.etl.loader.UserWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by vm.andreev on 31.01.17.
 */
public class XMLReader implements Iterator, AutoCloseable {
    private final int batchSize;
    private final Document document;
    private Collection<Node> nextBatch = null;

    public XMLReader(int batchSize, Document document) {
        this.batchSize = batchSize;
        this.document = document;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public boolean hasNext() {
        nextBatch = next();
        return nextBatch.size() > 0;
    }

    private Collection<Node> fetchBatch() {
        Collection<Node> batchNodes = new ArrayList<>();
        Node batchNode;
        NodeList nodes = document.getElementsByTagName("user");
        for (int counter = 0; counter < nodes.getLength(); counter++) {
            batchNode = nodes.item(counter);
            if (batchNode != null) {
                batchNodes.add(batchNode);
            } else {
                break;
            }
        }
        return batchNodes;
    }

    @Override
    public Collection<Node> next() {
        Collection<Node> result;
        if (nextBatch == null) {
            result = fetchBatch();
        } else {
            result = nextBatch;
            nextBatch = fetchBatch();
        }
        return result;
    }
}
