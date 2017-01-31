package com.acme.etl.test;

import com.acme.etl.core.Controller;
import com.acme.etl.exceptions.ETLException;
import com.acme.etl.extractor.*;
import com.acme.etl.loader.DBUserWriter;
import com.acme.etl.loader.LDAPUserWriter;
import org.w3c.dom.Document;

import javax.naming.Context;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Properties;


/**
 * @author vm.andreev
 */
public class ControllerTest {

    /**
     * Test ETL
     *
     * @param args input params
     */
    public static void main(String[] args) {
        try {
            String connectionUrl = "jdbc:derby://127.0.0.1:1527/example";
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = dBuilder.parse(new File(args[0]));
            document.getDocumentElement().normalize();
            try (
                    XMLBatchedUserReader xmlBatchedUserReader = new XMLBatchedUserReader(
                    new BatchedXMLReader(
                            new XMLReader(0, document))
            );
//                    BatchedCSVUserReader batchedCSVUserReader = new BatchedCSVUserReader(
//                            new BatchedUserReader(
//                                    new CSVBufferedReader(
//                                            3,
//                                            new BufferedReader(new FileReader(new File(args[0]))))
//                            )
//                    )
            ){
                Controller controller = new Controller(
                        xmlBatchedUserReader,
                        new DBUserWriter(connectionUrl), new LDAPUserWriter(getLDAPConnectionProperties())
                );

                try {
                    controller.doETL();
                } catch (ETLException e) {
                    e.printStackTrace();
                    System.out.println("Error during handling ETL operations");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CSV file not found");
        }
    }

    private static Properties getLDAPConnectionProperties() {
        Properties ldapProperties = new Properties();
        ldapProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapProperties.put(Context.PROVIDER_URL, "ldap://localhost:10389/ou=system");
        ldapProperties.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldapProperties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        ldapProperties.put(Context.SECURITY_CREDENTIALS, "secret");
        ldapProperties.put("com.sun.jndi.ldap.connect.timeout", "5000");
        ldapProperties.put("com.sun.jndi.ldap.connect.pool", "true");
        ldapProperties.put("com.sun.jndi.ldap.connect.pool.debug", "fine");
        return ldapProperties;
    }
}
