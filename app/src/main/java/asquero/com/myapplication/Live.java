package asquero.com.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Live extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter liveListAdapter;

    private List<LiveList> listLive;

    private String JSON_Codechef_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/codechef/?cstatus=1&format=json";
    private String JSON_Spoj_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/spoj/?cstatus=1&format=json";
    private String JSON_Hackerrank_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/hackerrank/?cstatus=1&format=json";

    private Context context = this;
    SwipeRefreshLayout mySwipeRefreshLayout;
    TextView noInternetConnection;
    ProgressBar progressBar;
    TextView searchingdata;
    int size = 0;
    int responseCounter = 0;
    int index = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        getSupportActionBar().setTitle("Live");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listLive = new ArrayList<>();

        noInternetConnection = (TextView) findViewById(R.id.noInternetConnection);

        if (!networkConnectivity()) {

            recyclerView.setVisibility(View.INVISIBLE);
            noInternetConnection.setVisibility(View.VISIBLE);
            //Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            loadLiveData(noInternetConnection);
        }

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("Swipe Refresh", "onRefresh called from SwipeRefreshLayout");

                        recyclerView.setVisibility(View.INVISIBLE);
                        if (!networkConnectivity()) {

                            noInternetConnection.setVisibility(View.VISIBLE);
                            //Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            size = 0;
                            index = 0;
                            responseCounter = 0;
                            listLive.clear();
                            liveListAdapter.notifyDataSetChanged();
                            loadLiveData(noInternetConnection);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                mySwipeRefreshLayout.setRefreshing(false);
                            }
                        }, 3000);
                    }
                }
        );

    }


    public void loadLiveData(TextView noInternetConnection) {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        searchingdata = (TextView) findViewById(R.id.searchingData);
        noInternetConnection.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        searchingdata.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        //Codechef data request*************************************************************************************************

        dataRequest(JSON_Codechef_URL, "codechef", progressBar, searchingdata, R.drawable.codechef_logo);


        //Spoj data request*****************************************************************************************************

        dataRequest(JSON_Spoj_URL, "spoj", progressBar, searchingdata, R.drawable.spoj_logo);

        //Hackerrank data request***********************************************************************************************

        dataRequest(JSON_Hackerrank_URL, "hackerrank", progressBar, searchingdata, R.drawable.hackerrank_logo);

        //progressBar.setVisibility(View.INVISIBLE);

    }

    public void dataRequest(String JSON_URL, final String site, final ProgressBar progressBar, final TextView searchingData, final int contestImageSource) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {

                             for (int i = 0; i < response.length(); i++) {

                                JSONObject liveJsonObj = response.getJSONObject(i);

                                /*Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), liveJsonObj.toString(), Toast.LENGTH_SHORT).show();*/

                                String code = liveJsonObj.getString("ccode_" + site);
                                String name = liveJsonObj.getString("cname_" + site);
                                String startdate = liveJsonObj.getString("cstartdate_" + site);
                                String enddate = liveJsonObj.getString("cenddate_" + site);
                                //int status = liveJsonObj.getInt("codechef_cstatus");

                                /*Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), startdate, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), enddate, Toast.LENGTH_SHORT).show();*/
                                if(code.equalsIgnoreCase("Null")){
                                    code = "";
                                }
                                //String imageUrl = "https://edsurge.imgix.net/uploads/post/image/7747/Kids_coding-1456433921.jpg?auto=compress%2Cformat&w=2000&h=810&fit=crop";
                                if(contestChosen(startdate, enddate, site)) {

                                    startdate = returnFormattedDate(startdate, site);
                                    enddate = returnFormattedDate(enddate, site);

                                    mostRelevantImage mri = new mostRelevantImage();
                                    String url = mri.findMostPerfectImage(code, name, startdate, enddate, context, index++);

                                    if (url.isEmpty()) {
                                        url = "https://www.computerhope.com/jargon/e/error.gif";
                                    }

                                    LiveList liveListObj = new LiveList(code, name, startdate, enddate, contestImageSource, url, name, "Pexels.com");

                                    listLive.add(liveListObj);
                                }
                            }

                            responseCounter += 1;

                            Log.i("responseCounter", "***" + responseCounter);

                            if (responseCounter == 3) {

                                size = listLive.size();

                                sort_list_live();

                                liveListAdapter = new LiveListAdapter(listLive, Live.this);
                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                searchingData.setVisibility(View.INVISIBLE);
                                recyclerView.setAdapter(liveListAdapter);

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

                            progressBar.setVisibility(View.INVISIBLE);
                            searchingData.setVisibility(View.INVISIBLE);
                            noInternetConnection.setVisibility(View.VISIBLE);
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

    public boolean contestChosen(String startdate, String enddate, String contest) {

        DayMonthYear dmy = new DayMonthYear();

        int startyear = dmy.returnYear(startdate, contest);
        int endyear = dmy.returnYear(enddate, contest);

        if(endyear < startyear) {

            return false;

        }
        else if(endyear - startyear > 1){

            return false;

        }
        else{

            return true;

        }

    }


    public String returnFormattedDate(String date, String contest) {

        DayMonthYear dmy = new DayMonthYear();

        int day = dmy.returnDay(date, contest);
        String month = dmy.returnMonth(date, contest);
        int year = dmy.returnYear(date, contest);

        Log.i("dateformat", "***"+date);

        String str = day + "" + month + "" + year;
        return str;

    }

    public void sort_list_live(){

        LiveList ll = null;

        DayMonthYear dmy = new DayMonthYear();
        Log.i("size", size+"***");

        for(int b = 0 ; b < size ; b++) {

            Log.i("listLiveInitial", listLive.get(b).getStartDate()+"***"+b);

            for (int a = 0; a < size-1; a++) {

                String startDate1 = listLive.get(a).getStartDate();
                int sd1 = dmy.returnDateNum(startDate1);
                Log.i("startdate1", startDate1+"***"+sd1);

                String startDate2 = listLive.get(a + 1).getStartDate();
                int sd2 = dmy.returnDateNum(startDate2);
                Log.i("startyear2", startDate2+"***"+sd2);

                if (sd1 > sd2) {

                    ll = listLive.get(a);
                    listLive.set(a, listLive.get(a+1));
                    listLive.set(a+1, ll);
                }
            }
            //listLiveSort.add(ll);
            Log.i("listLiveFinal", listLive.get(b).getStartDate()+"***"+b);
        }

    }

}
