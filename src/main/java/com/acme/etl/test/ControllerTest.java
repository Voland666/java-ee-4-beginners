package com.acme.etl.test;

import com.acme.etl.core.Controller;
import com.acme.etl.exceptions.ETLException;
import com.acme.etl.extractor.BatchedBufferedReader;
import com.acme.etl.extractor.CSVUserReader;

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
            try (CSVUserReader csvUserReader = new CSVUserReader(new BatchedBufferedReader(3, new BufferedReader(new FileReader(new File(args[0])))))) {
                Controller controller = new Controller(
                        csvUserReader,
                        new UserWriterStub("LDAP"), new UserWriterStub("DB")
                );

                try {
                    controller.doETL();
                } catch (ETLException e) {
                    e.printStackTrace();
                    System.out.println("Error during handling ETL operations");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("CSV file not found");
        }
    }
}
