package lk.techgays.drugsyou.xmlhandler;

import lk.techgays.drugsyou.item.Item_StoryList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class StoryList_XMLHandler extends DefaultHandler {
 
    private Boolean currentElement = false;
    private String currentValue = "";
    private Item_StoryList item = null;
    private final ArrayList<Item_StoryList> itemsList = new ArrayList<>();
 
    public ArrayList<Item_StoryList> getItemsList() {
        return itemsList;
    }
 
    // Called when tag starts 
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
 
        currentElement = true;
        currentValue = "";
        if (localName.equals("story")) {
            item = new Item_StoryList();
        } 
 
    }
 
    // Called when tag closing 
    @Override
    public void endElement(String uri, String localName, String qName)
    throws SAXException {
 
        currentElement = false;

        if (localName.equalsIgnoreCase("story_id"))
            item.setStoryId(currentValue);
        else if (localName.equalsIgnoreCase("story_title"))
            item.setStoryTitle(currentValue);
        else if (localName.equalsIgnoreCase("story_des"))
            item.setStoryDes(currentValue);
        else if (localName.equalsIgnoreCase("cat_id"))
            item.setCatId(currentValue);
        else if (localName.equalsIgnoreCase("sub_cat_id"))
            item.setSubCatId(currentValue);
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