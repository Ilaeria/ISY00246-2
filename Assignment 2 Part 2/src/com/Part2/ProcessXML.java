/**
 * Created by: Jennifer Doherty
 * Unit: ISY00246 S2 2015
 * Date: 5 September 2015
 * Assignment 2 Part 2
 * Processes XML documents. From Lab 8 documentation.
 **/

package com.Part2;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class ProcessXML
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("XML file must be set in command line arguments.");
            System.exit(-1);
        }

        try
        {
            //Set up processing
            File xml = new File("team.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            Element root = doc.getDocumentElement();

            /*
            //Set up node lists
            NodeList teamDetail = root.getElementsByTagName("teamDetail");
            NodeList member = root.getElementsByTagName("member");

            //Set up elements
            Element teamDetail = (Element) root.getElementsByTagName("teamDetail").item(0);
            Element leader = (Element) root.getElementsByTagName("leader").item(0);
            Element name = (Element) root.getElementsByTagName("name").item(0);
            Element phone = (Element) root.getElementsByTagName("phone").item(0);
            Element email = (Element) root.getElementsByTagName("email").item(0);
            Element fullname = (Element) root.getElementsByTagName("fullname").item(0);
            Element players = (Element) root.getElementsByTagName("players").item(0);
            Element member = (Element) root.getElementsByTagName("member").item(0);
            Element mailAddress = (Element) root.getElementsByTagName("mailAddress").item(0);
            Element altPosition = (Element) root.getElementsByTagName("altPosition").item(0);
            Element feesPaid = (Element) root.getElementsByTagName("feesPaid").item(0);
            */

            Element eTeamDetail = (Element) root.getElementsByTagName("teamDetail").item(0);
            Element eFullName = (Element) eTeamDetail.getElementsByTagName("fullname").item(0);
            NodeList nFullName = eFullName.getChildNodes();
            Text teamDetailText = (Text) nFullName.item(0);

            System.out.println("Team details for " + teamDetailText.getWholeText() + " (code " + eTeamDetail.getAttribute("id") + "):");

            /*
            Team details for "The A Team" (code theATeam):

            Players:
            Jo Player, left half (Fees paid: $100.00)
                Long Road, Upper Woop Woop West, NSW
                Alt Position: left wing
            Peter Player, goal keeper (Fees paid: $0.00)
                Short Road, Woop Woop, NSW
            Barry Wilks, back (Fees paid: $100.00)
                23 Jetty Street, Coffs Jetty, NSW
                Alt position: left right out

            Total of 3 team member(s).
            Team contact Barry Wilks 9999 3002, barwil@mail.com
            */
        }
        catch (SAXException se)
        {
            System.out.println(se);
            se.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
