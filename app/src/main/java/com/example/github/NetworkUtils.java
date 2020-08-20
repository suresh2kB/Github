package com.example.github;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";


    public static URL bulidUrl(String githubSearchQuery){
        Uri buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
        .appendQueryParameter(PARAM_QUERY,githubSearchQuery)
         .appendQueryParameter(PARAM_SORT,sortBy)
         .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromHttpUrl(URL url)throws IOException{
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            }
            else{
                return null;
            }
        }finally {
            httpURLConnection.disconnect();
        }
    }
}
