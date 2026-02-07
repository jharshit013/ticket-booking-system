package com.hjcode.ticketbooking.entities;

import java.util.List;
// import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    // private Map<String, String> stationTimes;
    private List<String> stations;

    public Train() {
    }

    // public Train(String trainId, String trainNo, List<List<Integer>> seats,
    // Map<String, String> stationTimes,
    // List<String> stations) {
    public Train(String trainId, String trainNo, List<List<Integer>> seats,
            List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        // this.stationTimes = stationTimes;
        this.stations = stations;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    // public Map<String, String> getStationTimes() {
    // return stationTimes;
    // }

    // public void setStationTimes(Map<String, String> stationTimes) {
    // this.stationTimes = stationTimes;
    // }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Train ID: ").append(trainId).append("\n");
        sb.append("Train No: ").append(trainNo).append("\n");
        sb.append("Stations: ").append(String.join(" -> ", stations)).append("\n");
        // sb.append("Station Times: ").append(stationTimes.toString()).append("\n");
        return sb.toString();
    }
}
