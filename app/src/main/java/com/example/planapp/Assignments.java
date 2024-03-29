package com.example.planapp;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Collections;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

class AOBJ {

    // fields of class
    private String dueDate;
    private String assignmentName;

    // constructors of class
    public AOBJ(String dueDate, String assignmentName) {
        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
    }

    public AOBJ() {
        //do nothing
    }


    // getters and setters of class
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
}



public class Assignments {
    List<String> dueDatesValuesOfOBJ = new ArrayList<>();
    List<String> assignmentNamesOfOBJ = new ArrayList<>();
    //with multiple classes we would have an array of this
    //AOBJ HumanFactors = new AOBJ();





    ArrayList<AOBJ> aobjList = new ArrayList<>();
    int counter = 0;
    int counter2 = 0;


    public void addition_isCorrect() throws JSONException, IOException {
        //assertEquals(4, 2 + 2);
        extractJsonNames("data-mining.json");
        extractDueDates("data-mining.json");

        extractJsonNames("computing_systems_fund.json");
        extractDueDates("computing_systems_fund.json");

        extractJsonNames("software-engineering.json");
        extractDueDates("software-engineering.json");

        extractJsonNames("human_factors.json");
        extractDueDates("human_factors.json");

        extractJsonNames("algorithms.json");
        extractDueDates("algorithms.json");

        //System.out.println("Printing out info for data mining");


        //Organizes the objects by due date
        Collections.sort(aobjList, new Comparator<AOBJ>() {
            public int compare(AOBJ a1, AOBJ a2) {
                return a1.getDueDate().compareTo(a2.getDueDate());
            }
        });


        //prints out all objects
        System.out.println("------------------");
        for (AOBJ aobj : aobjList) {

            System.out.println("Due date: " + aobj.getDueDate());
            System.out.println("Assignment name: " + aobj.getAssignmentName());
            System.out.println("------------------");
        }
    }

    public void extractJsonNames(String filename) throws JSONException, IOException {

        String content = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        }

        Pattern pattern = Pattern.compile("\"name\"\\s*:\\s*\"([^\"]*|null)\"");
            Matcher matcher = pattern.matcher(content);

            //List<String> propertyValues = new ArrayList<>();

            while (matcher.find()) {
                String propertyValue = matcher.group(1);
                AOBJ aobj1 = new AOBJ();
                aobjList.add(aobj1);
                aobjList.get(counter).setAssignmentName(propertyValue);
                counter++;
                //assignmentNamesOfOBJ.add(propertyValue);
            }
            //HumanFactors.setAssignmentNamesOfOBJ(assignmentNamesOfOBJ);
            //System.out.println("Names: " + propertyValues);
    }
    public void extractDueDates(String filename) throws JSONException, IOException {
        String content = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        }

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
                //dueDatesValuesOfOBJ.add(propertyValue);
                //AOBJ aobj1 = new AOBJ();
                //aobjList.add(aobj1);
                aobjList.get(counter2).setDueDate(propertyValue);
                counter2++;
            }

            // Print the list of property values
            //System.out.println("Due dates: " + propertyValues);
           // HumanFactors.setDueDatesValuesOfOBJ(dueDatesValuesOfOBJ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}