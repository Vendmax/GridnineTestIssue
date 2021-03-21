package com.gridnine.testing;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter {

    public static List<Flight> IsCurrentTimeAfterDeparture(List<Flight> flights) {
        LocalDateTime CurrentTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isSegmentOK = true;
            for (Segment segment : flight.getSegments()) {
                LocalDateTime DepartureDate = segment.getDepartureDate();
                if (CurrentTime.isAfter(DepartureDate)) {
                    isSegmentOK = false;
                }
            }
            if (isSegmentOK)
                filteredFlights.add(flight);
        }
        return filteredFlights;
    }

    public static List<Flight> IsDepartureBeforeArrival(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isSegmentOK = true;
            for (Segment segment : flight.getSegments()) {
                LocalDateTime DepartureDate = segment.getDepartureDate();
                LocalDateTime ArrivalDate = segment.getArrivalDate();
                if (ArrivalDate.isBefore(DepartureDate)) {
                    isSegmentOK = false;
                }
            }
            if (isSegmentOK) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public static List<Flight> CommonTimeLessTwoHours(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<Flight>();
        for (Flight flight : flights) {
            long WaitingHours = 0;
            if (flight.getSegments().size() == 1) {
                filteredFlights.add(flight);
            } else {
                for (int y = 1; y < flight.getSegments().size(); y++) {
                    LocalDateTime ArrivalDate = flight.getSegments().get(y - 1).getArrivalDate();
                    LocalDateTime DepartureDate = flight.getSegments().get(y).getDepartureDate();
                    WaitingHours += (Timestamp.valueOf(DepartureDate).getTime() - Timestamp.valueOf(ArrivalDate).getTime());
                }
                if (WaitingHours <= 2 * (1000 * 60 * 60))
                    filteredFlights.add(flight);

            }
        }
        return filteredFlights;
    }
}

