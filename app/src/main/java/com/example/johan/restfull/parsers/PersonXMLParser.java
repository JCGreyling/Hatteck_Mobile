package com.example.johan.restfull.parsers;

import com.example.johan.restfull.model.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class PersonXMLParser {

    public static List<Person> parseFeed(String content) {

        try {
            boolean inDataItemTag = false;
            String currentTagName = "";
            Person person = null;
            List<Person> personList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            System.out.println("eventType = " + eventType);
           // currentTagName = parser.getName();
            currentTagName = parser.getNamespace();
            System.out.println("currentTagName = " + currentTagName);

            while(eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if(currentTagName.equals("person")) {
                            inDataItemTag = true;
                            person = new Person();
                            personList.add(person);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("person")) {
                            inDataItemTag = false;
                        }

                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        currentTagName = parser.getName();
                        System.out.println("currentTagName = " + currentTagName);
                        if(inDataItemTag && person != null) {

                            switch (currentTagName) {
                                case "id":
                                    person.setId(Integer.parseInt(parser.getText()));
                                    break;
                                case "fullName":
                                    person.setFullName(parser.getText());
                                    break;
                                case "age":
                                    person.setAge(Integer.parseInt(parser.getText()));
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;

                }

                eventType = parser.next();

            }

            return personList;

        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }
}
