package lk.techgays.drugsyou.xmlhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import lk.techgays.drugsyou.item.Item_Sub_Category;

/**
 * * Created by Isuru Chanaka on 2018-03-06.
 **/

public class SubCategory_XMLHandler extends DefaultHandler {

    private Boolean currentElement = false;
    private String currentValue = "";
    private Item_Sub_Category item = null;
    private final ArrayList<Item_Sub_Category> itemsList = new ArrayList<>();

    public ArrayList<Item_Sub_Category> getItemsList() {
        return itemsList;
    }

    // Called when tag starts
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        currentElement = true;
        currentValue = "";
        if (localName.equals("story")) {
            item = new Item_Sub_Category();
        }

    }

    // Called when tag closing
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        currentElement = false;

        if (localName.equalsIgnoreCase("cat_id"))
            item.setCatId(currentValue);
        else if (localName.equalsIgnoreCase("sub_cat_id"))
            item.setSubCatId(currentValue);
        else if (localName.equalsIgnoreCase("sub_cat_status"))
            item.setSubCatStatus(currentValue);
        else if (localName.equalsIgnoreCase("sub_cat_name"))
            item.setSubCatName(currentValue);
        else if (localName.equalsIgnoreCase("story"))
            itemsList.add(item);

    }

    // Called to get tag characters
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (currentElement) {
            currentValue = currentValue +  new String(ch, start, length);
        }

    }

}