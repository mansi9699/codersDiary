package asquero.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
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
import java.util.List;

public class Ended extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter endedListAdapter;

    private List<EndedList> listEnded;
    private String JSON_Codechef_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/codechef/?cstatus=2&format=json";
    private String JSON_Spoj_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/spoj/?cstatus=2&format=json";
    private String JSON_Hackerrank_URL = "http://codersdiary-env.jrpma4ezhw.us-east-2.elasticbeanstalk.com/hackerrank/?cstatus=2&format=json";

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
        setContentView(R.layout.activity_ended);

        getSupportActionBar().setTitle("Ended");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listEnded = new ArrayList<>();

        noInternetConnection = (TextView) findViewById(R.id.noInternetConnection);

        if (!networkConnectivity()) {

            recyclerView.setVisibility(View.INVISIBLE);
            noInternetConnection.setVisibility(View.VISIBLE);
            //Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            loadEndedData(noInternetConnection);
        }

        /*for (int i = 0; i< 20 ; i++){
            EndedList EndedLists = new EndedList("DummyCode"+i,"DummyName"+i,"0","0","DummyName"+i);
            listEnded.add(EndedLists);
        }

        endedListAdapter = new EndedListAdapter(listEnded,Ended.this);
        recyclerView.setAdapter(endedListAdapter);*/

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
                            responseCounter = 0;
                            listEnded.clear();
                            endedListAdapter.notifyDataSetChanged();
                            loadEndedData(noInternetConnection);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                mySwipeRefreshLayout.setRefreshing(false);
                            }
                        }, 3500);
                    }
                }
        );

    }

    public void loadEndedData(TextView noInternetConnection) {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        searchingdata = (TextView) findViewById(R.id.searchingData);
        noInternetConnection.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        searchingdata.setVisibility(View.VISIBLE);

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


                            for (int i = 0; i < 5; i++) {

                                JSONObject endedJsonObj = response.getJSONObject(i);

                                /*Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), liveJsonObj.toString(), Toast.LENGTH_SHORT).show();*/

                                String code = endedJsonObj.getString("ccode_" + site);
                                String name = endedJsonObj.getString("cname_" + site);
                                String startdate = endedJsonObj.getString("cstartdate_" + site);
                                String enddate = endedJsonObj.getString("cenddate_" + site);
                                //int status = liveJsonObj.getInt("codechef_cstatus");

                                /*Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), startdate, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), enddate, Toast.LENGTH_SHORT).show();*/
                                if(code.equalsIgnoreCase("Null")){
                                    code = "";
                                }
                                //String imageUrl = "https://edsurge.imgix.net/uploads/post/image/7747/Kids_coding-1456433921.jpg?auto=compress%2Cformat&w=2000&h=810&fit=crop";
                                if (contestChosen(startdate, enddate, site)) {

                                    startdate = returnFormattedDate(startdate, site);
                                    enddate = returnFormattedDate(enddate, site);

                                    mostRelevantImage mri = new mostRelevantImage();
                                    String url = mri.findMostPerfectImage(code, name, startdate, enddate, context, index++);

                                    if (url.isEmpty()) {
                                        url = "https://www.computerhope.com/jargon/e/error.gif";
                                    }

                                    EndedList upcomingListObj = new EndedList(code, name, startdate, enddate, contestImageSource, url, name, "Pexels.com");

                                    listEnded.add(upcomingListObj);
                                }
                            }

                            responseCounter += 1;

                            Log.i("responseCounter", "***" + responseCounter);

                            if (responseCounter == 3) {

                                size = listEnded.size();

                                sort_list_ended();

                                endedListAdapter = new EndedListAdapter(listEnded, Ended.this);
                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                searchingData.setVisibility(View.INVISIBLE);
                                recyclerView.setAdapter(endedListAdapter);

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

        if (endyear < startyear) {

            return false;

        } else if (endyear - startyear > 1) {

            return false;

        } else {

            return true;

        }

    }


    public String returnFormattedDate(String date, String contest) {

        DayMonthYear dmy = new DayMonthYear();

        int day = dmy.returnDay(date, contest);
        String month = dmy.returnMonth(date, contest);
        int year = dmy.returnYear(date, contest);

        String str = day + "" + month + "" + year;
        return str;

    }

    public void sort_list_ended() {

        EndedList ll = null;

        DayMonthYear dmy = new DayMonthYear();
        Log.i("size", size + "***");

        for (int b = 0; b < size; b++) {

            Log.i("listLiveInitial", listEnded.get(b).getEndDate() + "***" + b);

            for (int a = 0; a < size - 1; a++) {

                String endDate1 = listEnded.get(a).getEndDate();
                int ed1 = dmy.returnDateNum(endDate1);
                Log.i("enddate1", endDate1 + "***" + ed1);

                String endDate2 = listEnded.get(a + 1).getEndDate();
                int ed2 = dmy.returnDateNum(endDate2);
                Log.i("endyear2", endDate2 + "***" + ed2);

                if (ed1 < ed2) {

                    ll = listEnded.get(a);
                    listEnded.set(a, listEnded.get(a + 1));
                    listEnded.set(a + 1, ll);
                }
            }
            //listLiveSort.add(ll);
            Log.i("listLiveFinal", listEnded.get(b).getEndDate() + "***" + b);
        }

    }

}