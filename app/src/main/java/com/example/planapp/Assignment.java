package com.example.planapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String name;
    private String dueDate;
    private String description;

    public Assignment() {
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public static List<Assignment> getAssignments(Context context) throws IOException, JSONException {
        String json = loadJSONFromAsset(context, "data-mining.json");
        JSONArray jsonArray = new JSONArray(json);

        List<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String dueDate = jsonObject.getString("due date");
            String description = jsonObject.getString("description");
            assignments.add(new Assignment());
        }
        return assignments;
    }

    private static String loadJSONFromAsset(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
