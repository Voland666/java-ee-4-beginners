package com.acme.etl.test;

import com.acme.etl.core.Controller;
import com.acme.etl.exceptions.ETLException;
import com.acme.etl.extractor.BatchedBufferedReader;
import com.acme.etl.extractor.BatchedCSVUserReader;
import com.acme.etl.extractor.CSVUserReader;
import com.acme.etl.loader.DBUserWriter;

import java.io.*;


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
            try (
                    BatchedCSVUserReader batchedCsvUserReader = new BatchedCSVUserReader(new CSVUserReader(new BatchedBufferedReader(3, new BufferedReader(new FileReader(new File(args[0]))))));
            ) {
                Controller controller = new Controller(
                        batchedCsvUserReader,
                        new DBUserWriter(connectionUrl), new UserWriterStub("LDAP")
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
}
