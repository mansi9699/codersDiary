package asquero.com.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter eventListAdapter;

    private List<EventList> listEvent;

    String cdmessage;
    String cdmessage_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/cdmessage/?format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listEvent = new ArrayList<>();

        //TextView tv = (TextView) findViewById(R.id.message);
        //RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        //rl.removeView(tv);

        TextView cdm = (TextView) findViewById(R.id.message);
        message(cdmessage_URL, cdm);

        /*//for (int i = 0; i<= 2 ; i++){
            //if (i==0) {
                //EventList listItem = new EventList("Live"," Gives you a list of coding contests, which are currently going on.", (R.drawable.live));
                listEvent.add(new EventList("Live"," Gives you a list of coding contests, which are currently going on.", (R.drawable.live)));
            //}
            //if (i==1) {
                //EventList listItem = new EventList("Upcoming"," Gives you a list of coding contests, which are yet to be conducted.", (R.drawable.upcoming));
                listEvent.add(new EventList("Upcoming"," Gives you a list of coding contests, which are yet to be conducted.", (R.drawable.upcoming)));
            //}
            //if (i==2) {
                //EventList listItem = new EventList("Ended"," Gives you a list of coding contests, which were once live, but now they have ended.", (R.drawable.ended));
                listEvent.add(new EventList("Ended"," Gives you a list of coding contests, which were once live, but now they have ended.", (R.drawable.ended)));
            //}
        //}*/

        /*eventListAdapter = new EventListAdapter(listEvent, this);
        recyclerView.setAdapter(eventListAdapter);*/

        additems aiObj = new additems();
        aiObj.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about) {

            Intent intent = new Intent(this, About.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void message(final String cdmessage_url, final TextView cdm) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, cdmessage_url, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {


                            JSONObject JsonObj = response.getJSONObject(0);
                            cdmessage = JsonObj.getString("message");

                            Log.i("cd_message", cdmessage + "***");

                            //TextView noInternetConnection = (TextView) findViewById(R.id.noInternetConnection);

                            if (cdmessage.toString() == "") {
                                cdm.setVisibility(View.INVISIBLE);
                            } else {
                                cdm.setText(cdmessage.toString());
                                cdm.setVisibility(View.VISIBLE);
                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        if (!networkConnectivity()) {

                            cdm.setVisibility(View.INVISIBLE);
                            //Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public boolean networkConnectivity() {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }

    private class additems extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... urls) {

            //for (int i = 0; i<= 2 ; i++){
            //if (i==0) {
            //EventList listItem = new EventList("Live"," Gives you a list of coding contests, which are currently going on.", (R.drawable.live));
            listEvent.add(new EventList("Live"," Gives you a list of coding contests, which are currently going on.", (R.drawable.livesmall)));
            //}
            //if (i==1) {
            //EventList listItem = new EventList("Upcoming"," Gives you a list of coding contests, which are yet to be conducted.", (R.drawable.upcoming));
            listEvent.add(new EventList("Upcoming"," Gives you a list of coding contests, which are yet to be conducted.", (R.drawable.upcomingsmall)));
            //}
            //if (i==2) {
            //EventList listItem = new EventList("Ended"," Gives you a list of coding contests, which were once live, but now they have ended.", (R.drawable.ended));
            listEvent.add(new EventList("Ended"," Gives you a list of coding contests, which were once live, but now they have ended.", (R.drawable.endedsmall)));
            //}
            //}
            eventListAdapter = new EventListAdapter(listEvent, MainActivity.this);
            recyclerView.setAdapter(eventListAdapter);

            return null;
        }

        protected void onProgressUpdate(Void... progress) {

        }

        protected void onPostExecute(Void result) {


        }
    }
}