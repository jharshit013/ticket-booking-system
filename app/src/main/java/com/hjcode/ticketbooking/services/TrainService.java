package com.hjcode.ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjcode.ticketbooking.entities.Train;

public class TrainService {
    private Train train;

    private List<Train> trainList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAINS_PATH = "../localDb/trains.json";

    public List<Train> loadTrains() throws IOException {
        File trains = new File(TRAINS_PATH);
        return this.objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public TrainService() throws IOException {
        loadTrains();
    }

    public TrainService(Train train) throws IOException {
        this.train = train;
        loadTrains();
    }

    public boolean validateTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();
        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destinationIndex = stations.indexOf(destination.toLowerCase());
        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    public List<Train> SearchTrains(String source, String destination) {
        try {
            return trainList.stream().filter(train -> validateTrain(train, source, destination))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error searching for trains: " + e.getMessage());
            return null;
        }
    }

    public List<List<Integer>> fetchSeats() {
        return train.getSeats();
    }

    private void saveTrainsToFile() throws JsonGenerationException, JsonMappingException, IOException {
        File trainFile = new File(TRAINS_PATH);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(trainFile, trainList);
    }

    public Boolean bookSeat(int row, int seatNo) {
        try {
            List<List<Integer>> seats = fetchSeats();
            if (row < 0 || row >= seats.size() || seatNo < 0 || seatNo >= seats.get(row).size()) {
                System.out.println("Invalid seat selection. Please try again.");
                return Boolean.FALSE;
            }
            if (seats.get(row).get(seatNo) == 1) {
                System.out.println("Seat already booked. Please select another seat.");
                return Boolean.FALSE;
            }
            seats.get(row).set(seatNo, 1);
            saveTrainsToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean cancelSeat(int row, int seatNo) {
        try {
            List<List<Integer>> seats = fetchSeats();
            if (row < 0 || row >= seats.size() || seatNo < 0 || seatNo >= seats.get(row).size()) {
                System.out.println("Invalid seat selection. Please try again.");
                return Boolean.FALSE;
            }
            if (seats.get(row).get(seatNo) == 0) {
                System.out.println("Seat is not booked. Please select another seat.");
                return Boolean.FALSE;
            }
            seats.get(row).set(seatNo, 0);
            saveTrainsToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Train getTrainById(String selectedTrain) {
        return trainList.stream().filter(train -> train.getTrainId().equals(selectedTrain)).findFirst().orElse(null);
    }

}
