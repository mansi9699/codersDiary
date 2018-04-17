package asquero.com.myapplication;

import android.content.Context;
import android.content.DialogInterface;
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
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter eventListAdapter;

    private List<EventList> listEvent;

    String cdmessage;
    String cdmessage_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/cdmessage/?format=json";


    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL to query the USGS dataset for earthquake information
     */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.navDrawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);

        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listEvent = new ArrayList<>();

        //TextView tv = (TextView) findViewById(R.id.message);
        //RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        //rl.removeView(tv);

        TextView cdm = (TextView) findViewById(R.id.message);
        message(cdmessage_URL, cdm);

        for (int i = 0; i <= 2; i++) {
            if (i == 0) {
                EventList listItem = new EventList("Live", (R.drawable.live));
                listEvent.add(listItem);
            }
            if (i == 1) {
                EventList listItem = new EventList("Upcoming", (R.drawable.upcoming));
                listEvent.add(listItem);
            }
            if (i == 2) {
                EventList listItem = new EventList("Ended", (R.drawable.ended));
                listEvent.add(listItem);
            }
        }

        eventListAdapter = new EventListAdapter(listEvent, this);
        recyclerView.setAdapter(eventListAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        return true;
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
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
}