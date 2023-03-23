package frc.API;

import java.util.Scanner;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import frc.API.api.BlueAllianceAPICallerAndParser;
import frc.API.api.IAPIKey;
import frc.API.api.APIKey;
import frc.API.output.Data;
import frc.API.output.DataIO;
import frc.API.serializers.JsonSerializerRegistry;
import frc.API.type.Match;
import frc.API.type.Team;

/**
 * An example CLI used for debugging.
 */
public class Main {

    //Activate test mode
    private static final boolean testMode = false;
    private static final boolean noSerialization = true;
    private static final IAPIKey apiKey = new APIKey("e68DJphX4Bo4JQjLB2x6ifLUqLmJbnkJLPoRmqvN8kzXwob382Ne4FN4c0vy6WQu");
    private static BlueAllianceAPICallerAndParser api = new BlueAllianceAPICallerAndParser(apiKey);


    public static void main(String[] args) {

        if(testMode){
            test();
        } else if (noSerialization) {
            //System.out.println(api.getEvents(new Team(8248)));
            System.out.println(api.jsonToGson(new Team(8248)));
        }
        else {
            JsonSerializerRegistry.registerSerializers();
            Gson gson = new GsonBuilder().create();

            Scanner scanner = new Scanner(System.in);
            String input = "";
            int number = 0;

            
            while (input != "exit") {

                System.out.print("Input team number:\n=> ");
                input = scanner.nextLine();

                try {
                    number = Integer.parseInt(input);
                    scanner.close();
                    break;
                } catch (Exception e){
                    System.out.println("Please enter a valid integer!");
                }

            }

            Team team = new Team(number);
            ArrayList<Match> matches = new ArrayList<>();

            JsonParser.parseString(api.getMatches(team, 2020)).getAsJsonArray().forEach((match) -> matches.add(gson.fromJson(match, Match.class))); //TODO: ew
            

            //write data to output file
            DataIO.writeRaw(api.getMatches(team, 2020), ".\\output.matches" + input);

        }


    }

    private static void test() {

        ArrayList<Match> matches = new Data(apiKey).getAllMatchesForTeam(new Team(8248), 2020);
        DataIO.writeMatchData(matches, ".\\output.matches");

        System.out.println("\n" + DataIO.readMatchData(".\\output.matches"));

    }

}
 