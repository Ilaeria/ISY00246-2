/**
 * Created by: Jennifer Doherty
 * Unit: ISY00246 S2 2015
 * Date: 5 September 2015
 * Assignment 2 Part 1
 * Validates XML Schemas using a DOM parser. From Lab 7 documentation.
 **/

package com.Part1;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.validation.*;
import org.w3c.dom.Document;

public class ValidateXML
{
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("XML file and schema must be set in command line arguments.");
            System.exit(-1);
        }
        File xmlFile = new File(args[0]);
        File schemaFile = new File(args[1]);

        try
        {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            SchemaFactory sfactory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sfactory.newSchema(schemaFile);

            factory.setSchema(schema);
            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
        }
        catch (ParserConfigurationException pce)
        {
            System.out.println();
            System.out.println(pce);
            pce.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
