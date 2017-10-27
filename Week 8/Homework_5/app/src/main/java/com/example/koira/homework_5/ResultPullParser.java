package com.example.koira.homework_5;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by koira on 10/26/2017.
 */

class ResultPullParser {
    public static ArrayList<Result> parseResults(InputStream inputStream) throws IOException, XmlPullParserException {
        ArrayList<Result> results = new ArrayList<>();
        Result result = null;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, "UTF-8");

        int event = parser.getEventType();

        while(event != XmlPullParser.END_DOCUMENT){
            switch (event){
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    } else if (parser.getName().equals("")){

                    }
            }
        }
        return results;
    }
}
