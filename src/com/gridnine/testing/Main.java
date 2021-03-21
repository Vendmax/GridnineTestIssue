package com.gridnine.testing;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> TestFlights = FlightBuilder.createFlights();
        System.out.println(FlightFilter.IsCurrentTimeAfterDeparture(TestFlights));
        System.out.println(FlightFilter.IsDepartureBeforeArrival(TestFlights));
        System.out.println(FlightFilter.CommonTimeLessTwoHours(TestFlights));
    }
}
