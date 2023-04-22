package com.example.planapp;

import org.json.JSONObject;
import org.junit.*;
import org.json.*;
import org.*;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

class AOBJ {

    // field of class
    List<String> dueDatesValuesOfOBJ = new ArrayList<>();
    List<String> assignmentNamesOfOBJ = new ArrayList<>();


//    public AOBJ(List<String> dueDatesValuesOfOBJ, List<String> assignmentNamesOfOBJ){
//        this.dueDatesValuesOfOBJ = dueDatesValuesOfOBJ;
//        this.assignmentNamesOfOBJ = assignmentNamesOfOBJ;
//    }


    // method of class
    public List<String> getDueDatesValuesOfOBJ() {
        return dueDatesValuesOfOBJ;
    }

    public void setDueDatesValuesOfOBJ(List<String> dueDatesValuesOfOBJ) {
        this.dueDatesValuesOfOBJ = dueDatesValuesOfOBJ;
    }

    public List<String> getAssignmentNamesOfOBJ() {
        return assignmentNamesOfOBJ;
    }

    public void setAssignmentNamesOfOBJ(List<String> assignmentNamesOfOBJ) {
        this.assignmentNamesOfOBJ = assignmentNamesOfOBJ;
    }


}


public class ExampleUnitTest {
    //with multiple classes we would have an array of this
    AOBJ HumanFactors = new AOBJ();
    List<String> dueDatesValuesOfOBJ = new ArrayList<>();
    List<String> assignmentNamesOfOBJ = new ArrayList<>();


    @Test
    public void addition_isCorrect() throws JSONException, IOException {
        //assertEquals(4, 2 + 2);
        extractJsonNames("data-mining.json");
        extractDueDates("data-mining.json");
        System.out.println("Printing out info for data mining");

        System.out.println(HumanFactors.assignmentNamesOfOBJ);
        System.out.println(HumanFactors.dueDatesValuesOfOBJ);
    }

    public void extractJsonNames(String filename) throws JSONException, IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)));

             Pattern pattern = Pattern.compile("\"name\"\\s*:\\s*\"([^\"]*|null)\"");
            Matcher matcher = pattern.matcher(content);

            //List<String> propertyValues = new ArrayList<>();

            while (matcher.find()) {
                String propertyValue = matcher.group(1);
                assignmentNamesOfOBJ.add(propertyValue);
            }
            HumanFactors.setAssignmentNamesOfOBJ(assignmentNamesOfOBJ);
            //System.out.println("Names: " + propertyValues);
    }
    public void extractDueDates(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));

        try {
            // Read the contents of the JSON file into a string
           // String content = new String(Files.readAllBytes(Paths.get("data-mining.json")));

            // Define a regular expression pattern to match the "due_at" property
            Pattern pattern = Pattern.compile("\"due_at\"\\s*:\\s*(\"[^\"]*\"|null)");

            // Create a matcher object to find matches in the content string
            Matcher matcher = pattern.matcher(content);

            // Create a list to hold the property values
            //List<String> propertyValues = new ArrayList<>();

            // Loop through all matches found by the matcher
            while (matcher.find()) {
                // Extract the property value from the match group
                String propertyValue = matcher.group(1);
                dueDatesValuesOfOBJ.add(propertyValue);
            }

            // Print the list of property values
            //System.out.println("Due dates: " + propertyValues);
            HumanFactors.setDueDatesValuesOfOBJ(dueDatesValuesOfOBJ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}