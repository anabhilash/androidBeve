package com.b_eve.abhilash.b_eve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley;
import org.json.JSONArray;



public class MainActivity extends AppCompatActivity
{

    Button but;
    ListView lv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        but =(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.error);
        lv=(ListView)findViewById(R.id.lists);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.0.101:3000/get";
                JsonArrayRequest jsObjRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try
                                {
                                    String[] names=new String[response.length()];
                                    for(int i=0;i<response.length();i++)
                                    {
                                        names[i]=response.getJSONObject(i).getString("firstName");
                                    }
                                    final ArrayAdapter<String> adapter =new ArrayAdapter<>(getApplicationContext(),R.layout.listfile,names);

                                    lv.setAdapter(adapter);
                                }
                                catch(Exception e)
                                {
                                    tv.setText(""+e);
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub

                            }
                        });
// Add the request to the RequestQueue.
                queue.add(jsObjRequest);
            }
        });
    }



}
