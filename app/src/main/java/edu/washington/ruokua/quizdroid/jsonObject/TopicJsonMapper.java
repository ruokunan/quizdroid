package edu.washington.ruokua.quizdroid.jsonObject;

import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by ruokua on 5/10/15.
 */
public class TopicJsonMapper {

    private final static String JSON_FILE_PATH = "/Users/ruokua/quizdroid/" +
            "app/src/main/java/edu/washington/ruokua/quizdroid/repository" +"questoins.json";


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();


        try {
            File jsonInput = new File(JSON_FILE_PATH);
            // read from file, convert it to user class
            TopicJsonMapper[] jsonMappers = mapper.readValue(jsonInput, TopicJsonMapper[].class);
            // display to console
           // System.out.println(user);
            Log.i("This is it" ,jsonMappers[0].toString());
        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
