package com.acme.etl.extractor;

import org.w3c.dom.Node;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by vm.andreev on 31.01.17.
 */
public class BatchedXMLReader implements AutoCloseable, Iterable<Collection<Node>> {
    private final XMLReader xmlReader;

    public BatchedXMLReader(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Iterator<Collection<Node>> iterator() {
        return xmlReader;
    }
}
