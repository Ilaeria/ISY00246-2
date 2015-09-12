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
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(args[0]);
            Element root = doc.getDocumentElement();

            //Process and print the team details
            Element eTeamDetail = (Element) root.getElementsByTagName("teamDetail").item(0);
            Element eFullName = (Element) eTeamDetail.getElementsByTagName("fullname").item(0);
            NodeList nFullName = eFullName.getChildNodes();
            Text teamDetailText = (Text) nFullName.item(0);

            System.out.println("Team details for \"" + teamDetailText.getWholeText()
                    + "\" (code " + eTeamDetail.getAttribute("id") + "):");
            System.out.println();
            System.out.println("Players:");

            //Process and print the member details, and count the number of members processed
            NodeList nMember = root.getChildNodes();
            int memberTotal = 0;

            for (int i = 0; i < nMember.getLength(); i++)
            {
                Node n = nMember.item(i);

                if (n.getNodeName().equals("member"))
                {
                    memberTotal++;
                    Element eMember = (Element) n;
                    Element eFeesPaid = (Element) eMember.getElementsByTagName("feesPaid").item(0);
                    NodeList nFeesPaid = eFeesPaid.getChildNodes();
                    Text feesPaidText = (Text) nFeesPaid.item(0);
                    Element eMailAddress = (Element) eMember.getElementsByTagName("mailAddress").item(0);
                    NodeList nMailAddress = eMailAddress.getChildNodes();
                    Text mailAddressText = (Text) nMailAddress.item(0);

                    System.out.println(eMember.getAttribute("name") + ", " + eMember.getAttribute("position")
                            + " (Fees paid: $" + feesPaidText.getWholeText() + ")");
                    System.out.println("    " + mailAddressText.getWholeText());

                    //Alt Position is optional
                    try
                    {
                        Element eAltPosition = (Element) eMember.getElementsByTagName("altPosition").item(0);
                        NodeList nAltPosition = eAltPosition.getChildNodes();
                        Text altPositionText = (Text) nAltPosition.item(0);
                        System.out.println("    Alt Position: " + altPositionText.getWholeText());
                    }
                    catch (NullPointerException npe)
                    {
                    }
                }
            }

            //Set up and print summary info
            Element eLeader = (Element) eTeamDetail.getElementsByTagName("leader").item(0);
            Element eLeaderName = (Element) eLeader.getElementsByTagName("name").item(0);
            NodeList nLeaderName = eLeaderName.getChildNodes();
            Text leaderText = (Text) nLeaderName.item(0);
            Element eLeaderPhone = (Element) eLeader.getElementsByTagName("phone").item(0);
            NodeList nLeaderPhone = eLeaderPhone.getChildNodes();
            Text leaderPhoneText = (Text) nLeaderPhone.item(0);

            //Leader email is optional
            String emailString = "no email provided";
            try
            {
                Element eLeaderEmail = (Element) eLeader.getElementsByTagName("email").item(0);
                NodeList nLeaderEmail = eLeaderEmail.getChildNodes();
                Text leaderEmailText = (Text) nLeaderEmail.item(0);
                emailString = leaderEmailText.getWholeText();
            }
            catch (NullPointerException npe)
            {
            }

            System.out.println();
            System.out.println("Total of " + memberTotal + " team member(s).");
            System.out.println("Team contact " + leaderText.getWholeText() + " "
                    + leaderPhoneText.getWholeText() +", " + emailString);
        }
        catch (SAXException se)
        {
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
