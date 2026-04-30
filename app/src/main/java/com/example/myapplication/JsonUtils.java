package com.example.myapplication;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class JsonUtils {
    private static String loadJsonFromAssets(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    public static ArrayList<Movie> loadMoviesByType(Context context, String type) {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            String jsonStr = loadJsonFromAssets(context, "movies.json");
            if (jsonStr != null) {
                JSONArray jsonArray = new JSONArray(jsonStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String movieType = obj.getString("type");

                    if (movieType.equalsIgnoreCase(type)) {
                        String name = obj.getString("name");
                        String genre = obj.getString("genre");
                        String trailerUrl = obj.getString("trailerUrl");
                        String imageName = obj.getString("image");

                        int imageRes = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

                        if (imageRes == 0) {
                            imageRes = R.drawable.logo;
                        }

                        movieList.add(new Movie(imageRes, name, genre, trailerUrl));
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}