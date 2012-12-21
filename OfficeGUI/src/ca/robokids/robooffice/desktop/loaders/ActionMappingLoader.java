/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.loaders;

import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Coldsoft
 */
public class ActionMappingLoader
{

    /**
     *
     * @param actionID
     * @return
     */
    public static ImageIcon getImageFileName(String actionID)
    {
        Element action = getElementByActionID(actionID);
        if (action == null)
        {
            return null;
        }

        Node node = action.getElementsByTagName("image").item(0).getFirstChild();
        String url = FramePropertyLoader.getImageDIR() + node.getNodeValue();
        return new ImageIcon(url.getClass().getResource(url));
    }

    /**
     *
     * @param actionID
     * @return
     */
    public static String getTabClass(String actionID)
    {
        Element action = getElementByActionID(actionID);
        if (action == null)
        {
            return "";
        }

        Node node = action.getElementsByTagName("tabClass").item(0).getFirstChild();
        return node.getNodeValue();
    }

    /**
     *
     * @param actionID
     * @return
     */
    public static String getTabName(String actionID)
    {
        Element action = getElementByActionID(actionID);
        if (action == null)
        {
            return "";
        }

        Node node = action.getElementsByTagName("tabName").item(0).getFirstChild();
        return node.getNodeValue();
    }

    /**
     *
     * @param menuItemText
     * @return
     */
    public static String getActionIDByMenuItemText(String menuItemText)
    {
        Element action = getElementByMenuItemText(menuItemText);
        if (action == null)
        {
            return "";
        }

        return action.getAttribute("id");
    }

    private static Document getDocument()
    {
        try
        {
            String filepath = "ActionMetadata.xml";
            InputStream in = ActionMappingLoader.class.getResourceAsStream(filepath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(in);
        } catch (ParserConfigurationException | IOException | SAXException pce)
        {
            pce.printStackTrace();
        }
        return null;
    }

    private static Element getElementByMenuItemText(String menuItemText)
    {

        Document doc = getDocument();

        NodeList list = doc.getElementsByTagName("action");
        for (int i = 0; i < list.getLength(); i++)
        {
            Element element = (Element) list.item(i);
            if (element.getAttribute("menuItem").equals(menuItemText))
            {
                return element;
            }
        }

        return null;
    }

    private static Element getElementByActionID(String actionID)
    {

        Document doc = getDocument();

        NodeList list = doc.getElementsByTagName("action");
        for (int i = 0; i < list.getLength(); i++)
        {
            Element element = (Element) list.item(i);
            if (element.getAttribute("id").equals(actionID))
            {
                return element;
            }
        }

        return null;
    }
    
    public static void main(String args[])
    {
       System.out.println(getTabClass("reportIssue"));
       System.out.println(getTabName("reportIssue"));
    }
}
