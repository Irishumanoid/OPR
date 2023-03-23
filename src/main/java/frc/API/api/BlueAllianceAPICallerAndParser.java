package frc.API.api;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import frc.API.type.*;


/**
 * Serves as a intermediatery to get raw api requests.
 */
public class BlueAllianceAPICallerAndParser {

    private static HttpClient client;
    private IAPIKey key;

    public BlueAllianceAPICallerAndParser(IAPIKey key) {

        client = HttpClient.newHttpClient();
        this.key = key;

    }

    //get events for a given team
    public String getEvents(Team team) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "team/" + team.getTeamId() + "/events");
        return generateResult(request);
    }

    //get matches for a given event
    public String getMatches(Event event) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "event/" + event.getEvent_code() + "/week/" + event.getWeek() + "/matches");
        return generateResult(request);
    }

    //get matches for a given team and year of competition
    public String getMatches(Team team, int year) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "team/" + team.getTeamId() + "/matches/" + year + "/simple");
        return generateResult(request);
    }

    //get events for a given district
    public String getEvents(District district) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "team/" + district.getAbbreviation() + "/events/" + district.getYear());
        return generateResult(request);
    }

    public static String generateResult(BlueAllianceAPIRequest request) {
        try {
            return request.get();
        } catch (RequestFailedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //test
    public Collection<Event> jsonToGson(int teamNumber) {
        Gson gson = new Gson();
        Type eventCollection = new TypeToken<Collection<Event>>(){}.getType();
        Collection<Event> events = gson.fromJson(getEvents(new Team(teamNumber)), eventCollection);

        return events;
    }

    //parses json to java object
    public <T> Collection<T> jsonToGson(T type) {
        Gson gson = new Gson();
        Type typeCollection = new TypeToken<Collection<T>>(){}.getType();
        Collection<T> types;

        if (type.getClass() == Team.class) {
            types = gson.fromJson(getEvents((Team) type), typeCollection);
            return types;
        } else if (type.getClass() == Event.class) {
            types = gson.fromJson(getMatches((Event) type), typeCollection);
            return types;
        } else if (type.getClass() == District.class) {
            types = gson.fromJson(getEvents((District) type), typeCollection);
            return types;
        }  
        return null;
    }

}
