package com.example.koira.inclass06;

import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by koira on 10/9/2017.
 */

public class JSONAsync extends AsyncTask<String, Void, ArrayList<Person>> {
@Override
protected ArrayList<Person> doInBackground(String...params){
        HttpURLConnection connection=null;
        ArrayList<Person> result=new ArrayList<>();
        try{
            URL url=new URL(params[0]);
            connection=(HttpURLConnection)url.openConnection();
            connection.connect();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                String json= IOUtils.toString(connection.getInputStream(),"UTF8");

                JSONObject root=new JSONObject(json);
                JSONArray persons=root.getJSONArray("persons");
                for(int i=0;i<persons.length();i++){
                    JSONObject personJson=persons.getJSONObject(i);
                    /*
                    Person person=new Person();
                    person.name=personJson.getString("name");
                    person.id=personJson.getLong("id");
                    person.age=personJson.getInt("age");
                    */

                    JSONObject addressJson=personJson.getJSONObject("address");

                    /*
                    Address address=new Address();
                    address.line1=addressJson.getString("line1");
                    address.city=addressJson.getString("city");
                    address.state=addressJson.getString("state");
                    address.zip=addressJson.getString("zip");

                    person.address=address;
                    result.add(person);
                    */
            }
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }finally{
        if(connection!=null){
        connection.disconnect();
        }
        }
        return result;
        }

@Override
protected void onPostExecute(ArrayList<Person> result){
    if(result.size()>0){
        Log.d("demo",result.toString());
    }else{
        Log.d("demo","empty result");
    }
    }
}
