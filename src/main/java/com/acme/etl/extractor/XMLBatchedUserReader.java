package com.acme.etl.extractor;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserReaderException;
import com.acme.etl.exceptions.UserWriterException;
import com.acme.etl.loader.UserWriter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by vm.andreev on 31.01.17.
 */
public class XMLBatchedUserReader implements UserReader, AutoCloseable {
    private final BatchedXMLReader batchedXMLReader;

    public XMLBatchedUserReader(BatchedXMLReader batchedXMLReader) {
        this.batchedXMLReader = batchedXMLReader;
    }

    private Collection<User> parseUsers(Collection<Node> batch) {
        Collection<User> users = new ArrayList<>(batch.size());
        for (Node userNode : batch) {
            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;
                users.add(
                        new User(
                                Integer.parseInt(userElement.getAttribute("id")),
                                userElement.getElementsByTagName("name").item(0).getTextContent()
                        )
                );
            }
        }
        return users;
    }

    @Override
    public void readAndSave(UserWriter... userWriters) throws UserReaderException, UserWriterException {
        for (Collection<Node> batch : batchedXMLReader) {
            Collection<User> batchUsers = parseUsers(batch);
            for (UserWriter userWriter : userWriters) {
                userWriter.save(batchUsers);
            }
        }
    }

    @Override
    public void close() throws Exception {
        batchedXMLReader.close();
    }
}
