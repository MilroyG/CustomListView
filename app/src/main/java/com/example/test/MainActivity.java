package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.NameList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.graphics.Insets.add;

public class MainActivity extends AppCompatActivity {
    //json string parsing
    private static String JSON_URl = "https://reqres.in/api/users";

    List<Name> Name;
    RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        GetData getData = new GetData();
        getData.execute();

    }

    public  class GetData extends AsyncTask<String,String,String>{
    //Get URL
        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection =  null;

                try {
                    url = new URL(JSON_URl);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                       return  current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection!= null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Name name = new Name();
                    name.setFname(jsonObject1.getString("first_name"));
                    name.setLname(jsonObject1.getString("last_name"));
                    name.setEmail(jsonObject1.getString("email"));
                    name.setImg(jsonObject1.getString("avatar"));

                    Name.add(name);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(Name);

        }
    }
    private void PutDataIntoRecyclerView(List<Name>Name){

        Adapter adapter= new Adapter(this,Name);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}