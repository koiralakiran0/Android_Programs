package com.example.koira.homework_5;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by koira on 10/28/2017.
 */

class ResultParser {
    public static class ResultSaxParser extends DefaultHandler{
        ArrayList<Result> results;
        Result result;
        StringBuilder innerXml;
        int height;
        int smallHeight;
        int largeHeight;

        public static ArrayList<Result> parseResult(InputStream inputStream) throws IOException, SAXException {
            ResultSaxParser resultSaxParser = new ResultSaxParser();
            Xml.parse(inputStream , Xml.Encoding.UTF_8 , resultSaxParser);
            return resultSaxParser.results;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            results = new ArrayList<>();
            innerXml = new StringBuilder();
            height = 0;
            smallHeight = 0;
            largeHeight = 0;
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (localName.equals("entry")){
                result = new Result();
            }

            if (localName.equals("image")){
                if (result != null){
                    height = Integer.valueOf(attributes.getValue("height"));
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if (localName.equals("entry")){
                results.add(result);
                largeHeight = 0;
                smallHeight = 0;
            } else if (localName.equals("title")){
                if (result != null) {
                    result.setTitle(innerXml.toString());
                }
            } else if (localName.equals("summary")){
                if (result != null)
                    result.setSummary(innerXml.toString());
            } else if (localName.equals("releaseDate")){
                if (result != null)
                    result.setReleaseDate(innerXml.toString());
            } else if (localName.equals("updated")){
                if (result != null)
                    result.setUpdatedDate(innerXml.toString());
            } else if (localName.equals("image")){
                if (result != null) {
                    if (result.getSmallImage() == null) {
                        result.setSmallImage(innerXml.toString());
                        smallHeight = height;
                        largeHeight = height;
                    } else if (height < smallHeight) {
                        result.setSmallImage(innerXml.toString());
                    } else if (height > largeHeight) {
                        result.setLargeImage(innerXml.toString());
                    }
                }
            }

            innerXml.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            innerXml.append(ch, start, length);
        }
    }
}
