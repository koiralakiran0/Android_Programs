/*
a.Assignment #: InClass 10
File Name: MainActivity.java
Full name: Kiran Koirala
 */

package com.example.kiran.inclass011;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Gson gson = new Gson();

        //places = new ArrayList<>();
        //File file = new File(R.raw.trip);
        //places = gson.fromJson(, Place.class);

        InputStream is = getResources().openRawResource(R.raw.trip);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e){

        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();


        //Places p = gson.fromJson(, Places.class);
        //Log.d("demopoints",p.toString());


        //JsonReader reader = new JsonReader(new InputStreamReader(getResources().openRawResource(R.raw.trip)));
        Places p_places = gson.fromJson(jsonString, Places.class);

        places = p_places.getPoints();
        Log.d("demo", p_places.getPoints().toString());

        double maxLat, maxLon, minLat, minLon;
        maxLat = maxLon = -300.0;
        minLat = minLon = 300.0;

        LatLng onePlace = new LatLng(places.get(0).latitude, places.get(0).longitude);
        mMap.addMarker(new MarkerOptions().position(onePlace).title("Start"));

        PolylineOptions options = new PolylineOptions();
        options.color(Color.BLUE);

        for (int i = 0; i < places.size(); i++){
            options.add(new LatLng(places.get(i).latitude, places.get(i).longitude));
            if (places.get(i).latitude < minLat){
                minLat = places.get(i).latitude;
            } else if(places.get(i).latitude > maxLat){
                maxLat = places.get(i).latitude;
            }

            if (places.get(i).longitude < minLon){
                minLon = places.get(i).longitude;
            } else if(places.get(i).longitude > maxLon){
                maxLon = places.get(i).longitude;
            }

        }

        onePlace = new LatLng(places.get(places.size()-1).latitude, places.get(places.size()-1).longitude);

        mMap.addMarker(new MarkerOptions().position(onePlace).title("End"));
        Polyline polyline = googleMap.addPolyline(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(onePlace));

        //FIND MIDPOINT OF LARGEST LATITUDE AND LONGITUDE AND SMALLEST LATITUDE AND LONGITUDE
        double x = (minLat + maxLat)/2;
        double y = (minLon + maxLon)/2;
        Log.d("demo", x + " " + y);

        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 4));

        //ZOOM BASED ON THE MIDPOINTS
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x, y), 10));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(64, 149)));ALASKA
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x, y), 10));

        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
