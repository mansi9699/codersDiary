package asquero.com.myapplication;

import android.util.Log;

import java.util.StringTokenizer;

/**
 * Created by srajan on 16/4/18.
 */

public class DayMonthYear {

    int day, month, year;

    /*************************************************_Return Year_*********************************************************/

    public int returnYear(String date, String contest){

        String year = "";

        if (contest.equalsIgnoreCase("codechef")) {

            //012345678910
            //01 Apr 2018 Codechef

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 7 && a <= 10) {

                    year = year + ch;
                }
            }
        }

        if (contest.equalsIgnoreCase("spoj")) {
            //012345678910
            //2018-04-01 Spoj

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 0 && a <= 3) {

                    year = year + ch;
                }
            }
        }

        if (contest.equalsIgnoreCase("hackerrank")) {

            //2018-04-01 Hackerrank

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 0 && a <= 3) {

                    year = year + ch;
                }
            }
        }

        return Integer.parseInt(year);

    }

    /*************************************************_Return Month_*********************************************************/

    public String returnMonth(String date, String contest){

        String str = "", month = "";

        if (contest.equalsIgnoreCase("codechef")) {

            //012345678910
            //01 Apr 2018 Codechef

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 3 && a <= 5) {

                    month = month + ch;
                }
            }
        }

        else if (contest.equalsIgnoreCase("spoj")) {

            //012345678910
            //01 Apr 2018 Codechef

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 5 && a <= 6) {

                    month = month + ch;
                }
            }
        }

        else if (contest.equalsIgnoreCase("hackerrank")) {

            //012345678910
            //01 Apr 2018 Codechef

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 5 && a <= 6) {

                    month = month + ch;
                }
            }
        }

        if (month.equalsIgnoreCase("Jan") || month.equalsIgnoreCase("01")) {

            str = " January ";
        }

        else if (month.equalsIgnoreCase("Feb") || month.equalsIgnoreCase("02")) {

            str = " February ";
        }

        else if (month.equalsIgnoreCase("Mar") || month.equalsIgnoreCase("03")) {

            str = " March ";
        }

        else if (month.equalsIgnoreCase("Apr") || month.equalsIgnoreCase("04")) {

            str = " April ";
        }

        else if (month.equalsIgnoreCase("May") || month.equalsIgnoreCase("05")) {

            str = " May ";
        }

        else if (month.equalsIgnoreCase("Jun") || month.equalsIgnoreCase("06")) {

            str = " June ";
        }

        else if (month.equalsIgnoreCase("Jul") || month.equalsIgnoreCase("07")) {

            str = " July ";
        }

        else if (month.equalsIgnoreCase("Aug") || month.equalsIgnoreCase("08")) {

            str = " August ";
        }

        else if (month.equalsIgnoreCase("Sep") || month.equalsIgnoreCase("09")) {

            str = " September ";
        }

        else if (month.equalsIgnoreCase("Oct") || month.equalsIgnoreCase("10")) {

            str = " October ";
        }

        else if (month.equalsIgnoreCase("Nov") || month.equalsIgnoreCase("11")) {

            str = " November ";
        }

        else if (month.equalsIgnoreCase("Dec") || month.equalsIgnoreCase("12")) {

            str = " December ";
        }

        Log.i("Month", str);

        return str;

    }

    /*************************************************_Return Day_*********************************************************/

    public int returnDay(String date, String contest){

        String day = "";

        if (contest.equalsIgnoreCase("codechef")) {

            //012345678910
            //01 Apr 2018 Codechef

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 0 && a <= 1) {

                    day = day + ch;
                }
            }

            Log.i("codechef", "***"+day+"***"+date);
        }

        if (contest.equalsIgnoreCase("spoj")) {

            //012345678910
            //01 Apr 2018 spoj

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 8 && a <= 9) {

                    day = day + ch;
                }
            }

            Log.i("spoj", "***"+day+"***"+date);
        }

        if (contest.equalsIgnoreCase("hackerrank")) {

            //012345678910
            //2018-04-01 Hackerrank

            for (int a = 0; a < date.length(); a++) {

                char ch = date.charAt(a);

                if (a >= 8 && a <= 9) {

                    day = day + ch;
                }
            }

            Log.i("hackerrank", "***"+day+"***"+date);
        }

        return Integer.parseInt(day);
    }

    /*************************************************_Return Integer Month_*********************************************************/

    public int intMonth(String month){

        month = month.trim();

        if(month.equalsIgnoreCase("January")){

            return 1;
        }
        else if(month.equalsIgnoreCase("February")){

            return 2;
        }
        else if(month.equalsIgnoreCase("March")){

            return 3;
        }
        else if(month.equalsIgnoreCase("April")){

            return 4;
        }
        else if(month.equalsIgnoreCase("May")){

            return 5;
        }
        else if(month.equalsIgnoreCase("June")){

            return 6;
        }
        else if(month.equalsIgnoreCase("July")){

            return 7;
        }
        else if(month.equalsIgnoreCase("August")){

            return 8;
        }
        else if(month.equalsIgnoreCase("September")){

            return 9;
        }
        else if(month.equalsIgnoreCase("October")){

            return 10;
        }
        else if(month.equalsIgnoreCase("November")){

            return 11;
        }
        else if(month.equalsIgnoreCase("December")){

            return 12;
        }

        return 9999;
    }

    public int returnDateNum(String Date){

        //int len = Date.length();

        int defaultDay = 01;
        int defaultMonth = 01;
        int defaultYear = 2000;

        operationOnFormattedDate(Date);

        int yearInSec = (year-defaultYear)*365*24*60*60;
        int monthInSec = (month-defaultMonth)*30*24*60*60;
        int dayInSec = (day-defaultDay)*24*60*60;

        int DdInSec = yearInSec + monthInSec + dayInSec;

        Log.i("Date in Secs", ""+DdInSec);
        Log.i("Date separate", Date+" "+yearInSec+" "+monthInSec+" "+dayInSec);
        return DdInSec;
    }

    /*************************************************_Operation on Formatted Date_****************************************************/

    public void operationOnFormattedDate(String Date){

        StringTokenizer st = new StringTokenizer(Date);

        String stringDay = st.nextToken();
        String stringMonth = st.nextToken();
        String stringYear = st.nextToken();

        day = Integer.parseInt(stringDay);
        month = intMonth(stringMonth);
        year = Integer.parseInt(stringYear);



    }
}
