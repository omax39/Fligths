package org.example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
        private String origin;
        private String origin_name;
        private String destination;
        private String destination_name;
        private String departure_date;
        private String departure_time;
        private String  arrival_date;
        private String arrival_time;
        private String carrier;
        private int stops;
        private int price;

    public Ticket(String origin, String origin_name,
                  String destination, String destination_name,
                  String  departure_date, String departure_time,
                  String arrival_date, String arrival_time,
                  String carrier, int stops, int price) {
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public Date getDeparture_date() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
        Date date = null;
        try {
            date = format.parse(this.departure_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public Date getDeparture_time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = format.parse(this.departure_time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public Date getDeparture_dateAndTime() {
        String str = this.departure_date + "." + this.departure_time;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy.HH:mm");
        Date date;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public Date getArrival_date() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
        Date date = null;
        try {
            date = format.parse(this.arrival_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public Date getArrival_time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = format.parse(this.arrival_time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public Date getArrival_dateAndTime() {
        String str = this.arrival_date + "." + this.arrival_time;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy.HH:mm");
        Date date;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    public Ticket() {
    }

    public int getFlyTime(){
                return (int) (getArrival_time().getTime() - getDeparture_time().getTime())/1000;
    }
}
