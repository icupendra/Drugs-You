package lk.techgays.drugsyou.xmlhandler;

import lk.techgays.drugsyou.item.Item_Category;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class Category_XMLHandler extends DefaultHandler {
 
    private Boolean currentElement = false;
    private String currentValue = "";
    private Item_Category item = null;
    private final ArrayList<Item_Category> itemsList = new ArrayList<>();
 
    public ArrayList<Item_Category> getItemsList() {
        return itemsList;
    }
 
    // Called when tag starts 
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
 
        currentElement = true;
        currentValue = "";
        if (localName.equals("category")) {
            item = new Item_Category();
        } 
 
    }
 
    // Called when tag closing 
    @Override
    public void endElement(String uri, String localName, String qName)
    throws SAXException {
 
        currentElement = false;

        if (localName.equalsIgnoreCase("cat_id"))
            item.setCatId(currentValue);
        else if (localName.equalsIgnoreCase("cat_name"))
            item.setCatName(currentValue);
        else if (localName.equalsIgnoreCase("sub_cat_status"))
            item.setSubCatStatus(currentValue);
         else if (localName.equalsIgnoreCase("category"))
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