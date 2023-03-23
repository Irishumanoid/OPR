package frc.API.output;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import frc.API.api.BlueAllianceAPICallerAndParser;
import frc.API.api.IAPIKey;
import frc.API.serializers.MatchJsonDeserializer;
import frc.API.type.Event;
import frc.API.type.Match;
import frc.API.type.Team;

/**
 * A high-level interface for the BlueAlliance API, allowing data processing and formatting in Java.
*/
public class Data {

    private static BlueAllianceAPICallerAndParser api;

    public Data(IAPIKey key) {
        api = new BlueAllianceAPICallerAndParser(key);
    }

    public ArrayList<Event> getEvents() {
        return null;
    }

    public ArrayList<Match> getAllMatchesForTeam(Team team, int year) {
        
        ArrayList<Match> matches = new ArrayList<>();

        JsonParser.parseString(api.getEvents(team)).getAsJsonArray().forEach((match) -> {
            //TO DO: fix thrown null pointer exception
            matches.add(MatchJsonDeserializer.deserialize(match));
        });

        return matches;
    }
}
