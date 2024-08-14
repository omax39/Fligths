package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Utils {

    public static List<Ticket> parseJson(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> employeeList;
        try {
            Map<String, Object> s = objectMapper.readValue(file, new TypeReference<>(){});
            employeeList = objectMapper
                    .readValue(new ObjectMapper().writeValueAsString(s.get("tickets")),
                            new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public static Map<String, String> minFromFliesForAnyCarrier(List<Ticket> tickets) {
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCarrier))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> {
                            return secToNormalTime(x.getValue().stream()
                                    .filter(cities -> cities.getOrigin_name().equals("Владивосток") &&
                                            cities.getDestination_name().equals("Тель-Авив"))
                                    .map(y -> y.getFlyTime())
                                    .mapToInt(Integer::intValue)
                                    .min()
                                    .getAsInt());
                        }));
    }

    public static String secToNormalTime(Integer sec) {
        int hours = sec/3600;
        int minutes = sec / 60 - hours*60;
        String shours = String.valueOf(hours);
        String sminutes = String.valueOf(minutes);
        if (hours < 10) shours = "0" + shours;
        if (minutes < 10) sminutes = "0" + minutes;
        return String.format("%s:%s", shours, sminutes);
    }

    public static double getDiffPriceAndAverage(List<Ticket> tickets) {



        List<Integer> prices = tickets.stream().filter(cities -> cities.getOrigin_name().equals("Владивосток") &&
                        cities.getDestination_name().equals("Тель-Авив"))
                        .map(Ticket::getPrice).collect(Collectors.toList());
        int tSize = prices.size();
        Collections.sort(prices);

        double pricesAverage = prices.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        double pricesMedian;

        if (tSize % 2 == 0) pricesMedian = (double) (prices.get(tSize / 2 - 1) + prices.get(tSize / 2)) / 2;
        else pricesMedian = prices.get(tSize/2);

        return pricesAverage - pricesMedian;
    }

}
