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
import android.support.v7.widget.GridLayoutManager;
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

    private RecyclerView recyclerView, hRecyclerView, gRecyclerView;
    private RecyclerView.Adapter eventListAdapter, hCompanyAdapter, gCompanyAdapter;

    private List<EventList> listEvent;
    private List<HorizontalCompanyList> companyLists;
    private List<GridCompanyList> gCompanyLists;

    String cdmessage;
    String cdmessage_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/cdmessage/?format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hRecyclerView = (RecyclerView) findViewById(R.id.horizontalRecyclerView);
        hRecyclerView.setHasFixedSize(true);
        hRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        gRecyclerView = (RecyclerView) findViewById(R.id.GridRecyclerView);
        gRecyclerView.setHasFixedSize(true);
        gRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        listEvent = new ArrayList<>();
        companyLists = new ArrayList<>();
        gCompanyLists = new ArrayList<>();

        TextView cdm = (TextView) findViewById(R.id.message);
        message(cdmessage_URL, cdm);

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
                                cdm.setVisibility(View.GONE);
                            } else {
                                cdm.setText(cdmessage.toString());
                                cdm.setVisibility(View.VISIBLE);
                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                            cdm.setVisibility(View.GONE);

                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        if (!networkConnectivity()) {

                            cdm.setVisibility(View.GONE);

                        } else {
                            cdm.setVisibility(View.GONE);
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


            listEvent.add(new EventList("Live"," Gives you a list of coding contests, which are currently going on.", (R.drawable.livesmall)));
            listEvent.add(new EventList("Upcoming"," Gives you a list of coding contests, which are yet to be conducted.", (R.drawable.upcomingsmall)));

            eventListAdapter = new EventListAdapter(listEvent, MainActivity.this);
            recyclerView.setAdapter(eventListAdapter);

            companyLists.add(new HorizontalCompanyList("Amazon", R.drawable.default_image));
            companyLists.add(new HorizontalCompanyList("Facebook", R.drawable.default_image));
            companyLists.add(new HorizontalCompanyList("Google", R.drawable.default_image));
            companyLists.add(new HorizontalCompanyList("Microsoft", R.drawable.default_image));

            hCompanyAdapter = new HorizontalCompanyAdapter(companyLists,MainActivity.this);
            hRecyclerView.setAdapter(hCompanyAdapter);

            gCompanyLists.add(new GridCompanyList("CodeChef", R.drawable.default_image));
            gCompanyLists.add(new GridCompanyList("CodeForces", R.drawable.default_image));
            gCompanyLists.add(new GridCompanyList("HackerRank", R.drawable.default_image));
            gCompanyLists.add(new GridCompanyList("HackerEarth", R.drawable.default_image));
            gCompanyLists.add(new GridCompanyList("SPOJ", R.drawable.default_image));
            gCompanyLists.add(new GridCompanyList("TopCoder", R.drawable.default_image));

            gCompanyAdapter = new GridCompanyAdapter(gCompanyLists,MainActivity.this);
            gRecyclerView.setAdapter(gCompanyAdapter);

            return null;
        }
    }

}