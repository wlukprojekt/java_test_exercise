package com.app;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;


@Path("/app")
public class ShowTimes {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/time")
    public String allTimes() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("nowyjork", -4);
        map.put("londyn", 1);
        map.put("paryz", 2);
        map.put("moskwa", 3);
        map.put("pekin", 8);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("nowyjork", "Nowy Jork");
        map2.put("londyn", "Londyn");
        map2.put("paryz", "Paryż");
        map2.put("moskwa", "Moskwa");
        map2.put("pekin", "Pekin");

        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        String outputInformation = new String();

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            var now = addHours(date, value);
            outputInformation += "Aktualny czas w miejscowości: " + map2.get(key) + " to: " + formatter.format(now) + ".\n ";
        }

        return outputInformation;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    //@CrossOrigin(origins = "http://localhost/app")
    @Path("/time/{city}")
    public String cityTime(@PathParam String city) {
        return showLocalTime(city);
    }

    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static String showLocalTime(String param){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("nowyjork", -4);
        map.put("londyn", 1);
        map.put("paryz", 2);
        map.put("moskwa", 3);
        map.put("pekin", 8);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("nowyjork", "Nowy Jork");
        map2.put("londyn", "Londyn");
        map2.put("paryz", "Paryż");
        map2.put("moskwa", "Moskwa");
        map2.put("pekin", "Pekin");

        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Integer value = map.get(param);
        var godzina = addHours(date, value);
        String outputInformation = "Godzina w miejscowosci " + map2.get(param) + " to " + formatter.format(godzina);
        return outputInformation;
    }
}
