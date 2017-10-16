package com.example.koira.midtermpracticepiazza;

import android.content.Context;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by koira on 10/16/2017.
 */

public class ResultParser {
    public static class ResultSaxParser extends DefaultHandler{
        ArrayList<Result> results;
        StringBuilder innerXml;
        ArrayList<Genre> genres;

        static public ArrayList<Result> parseResults(InputStream inputStream) throws IOException, SAXException{
            ResultSaxParser parser = new ResultSaxParser();
            Xml.parse(inputStream, Xml.Encoding.UTF_8, parser);
            return parser.results;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            this.results = new ArrayList<>();
            this.innerXml = new StringBuilder();
            this.genres = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }
}
