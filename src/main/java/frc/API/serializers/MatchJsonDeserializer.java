package frc.API.serializers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import frc.API.type.Match;

/**
 * 
 */


 //to do: create interface so events, etc. can be deserialized
public class MatchJsonDeserializer implements JsonDeserializer<Match> {

    @Override
    public Match deserialize(JsonElement rawJson, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject json = rawJson.getAsJsonObject();
        JsonObject alliances = json.get("alliances").getAsJsonObject();
        ArrayList<Integer> blueIntermediate = new ArrayList<>();
        ArrayList<Integer> redIntermediate = new ArrayList<>();

        //TODO: comment this cursed mess
        JsonObject blueAlliance = alliances.get("blue").getAsJsonObject();
        blueAlliance.get("team_keys").getAsJsonArray().iterator().forEachRemaining((element) -> blueIntermediate.add(Integer.parseInt(element.getAsString().substring(2,6))));
    
        System.out.println("ba: " + blueAlliance.toString());
        JsonObject redAlliance = alliances.get("red").getAsJsonObject();
        redAlliance.get("team_keys").getAsJsonArray().iterator().forEachRemaining((element) -> redIntermediate.add(Integer.parseInt(element.getAsString().substring(2,6))));


        return new Match(
            redIntermediate.stream().mapToInt((i) -> i).toArray(),
            Integer.parseInt(redAlliance.get("score").getAsString()),
            blueIntermediate.stream().mapToInt((i) -> i).toArray(),
            Integer.parseInt(blueAlliance.get("score").getAsString()),
            (json.get("winning_alliance").getAsString() == "red")
        );

    }

    public static Match deserialize(JsonElement rawJson) throws JsonParseException {
        
        JsonObject json = rawJson.getAsJsonObject();
        JsonObject alliances = json.get("alliances").getAsJsonObject();
        ArrayList<Integer> blueIntermediate = new ArrayList<>();
        ArrayList<Integer> redIntermediate = new ArrayList<>();

        //TODO: comment this cursed mess
        JsonObject blueAlliance = alliances.get("blue").getAsJsonObject();
        blueAlliance.get("team_keys").getAsJsonArray().iterator().forEachRemaining((element) -> blueIntermediate.add(Integer.parseInt(element.getAsString().substring(3))));
    
        //System.out.println("ba: " + blueAlliance.toString());
        JsonObject redAlliance = alliances.get("red").getAsJsonObject();
        redAlliance.get("team_keys").getAsJsonArray().iterator().forEachRemaining((element) -> redIntermediate.add(Integer.parseInt(element.getAsString().substring(3))));


        return new Match(
            redIntermediate.stream().mapToInt((i) -> i).toArray(),
            Integer.parseInt(redAlliance.get("score").getAsString()),
            blueIntermediate.stream().mapToInt((i) -> i).toArray(),
            Integer.parseInt(blueAlliance.get("score").getAsString()),
            (json.get("winning_alliance").getAsString().equals("red"))
        );
        
    }
    
}
