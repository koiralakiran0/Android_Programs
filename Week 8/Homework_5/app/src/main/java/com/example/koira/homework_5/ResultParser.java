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
        int smallestHeight;
        int largestHeight;

        public static ArrayList<Result> parseResult(InputStream inputStream) throws IOException, SAXException {
            // stream of XML is pushed to program
            ResultSaxParser feedSaxParser = new ResultSaxParser();
            Xml.parse(inputStream , Xml.Encoding.UTF_8 , feedSaxParser);
            return feedSaxParser.results;
        }

        public ResultSaxParser() {
            super();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();

            results = new ArrayList<>();
            innerXml = new StringBuilder();
            height = 0;
            smallestHeight = 0;
            largestHeight = 0;
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
                largestHeight = 0;
                smallestHeight = 0;
            } else if (localName.equals("title")){
                result.setTitle(innerXml.toString().trim());
            } else if (localName.equals("summary")){
                result.setSummary(innerXml.toString().trim());
            } else if (localName.equals("releaseDate")){
                result.setReleaseDate(innerXml.toString().trim());
            } else if (localName.equals("updated")){
                result.setUpdatedDate(innerXml.toString());
            } else if (localName.equals("image")){

            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }
}
