package com.hjcode.ticketbooking.entities;

// import java.sql.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String destination;
    private String source;
    // private Date dateOfTravel;
    private Train train;
    private int seatRow;
    private int seatNo;

    public Ticket() {
    }

    // public Ticket(String ticketId, String userId, String destination, String
    // source, Date dateOfTravel, Train train,
    // int seatRow, int seatNo) {
    public Ticket(String ticketId, String userId, String destination, String source, Train train,
            int seatRow, int seatNo) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.destination = destination;
        this.source = source;
        // this.dateOfTravel = dateOfTravel;
        this.train = train;
        this.seatRow = seatRow;
        this.seatNo = seatNo;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // public Date getDateOfTravel() {
    // return dateOfTravel;
    // }

    // public void setDateOfTravel(Date dateOfTravel) {
    // this.dateOfTravel = dateOfTravel;
    // }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getTicketInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket ID: ").append(ticketId).append("\n");
        sb.append("User ID: ").append(userId).append("\n");
        sb.append("Source: ").append(source).append("\n");
        sb.append("Destination: ").append(destination).append("\n");
        // sb.append("Date of Travel: ").append(dateOfTravel).append("\n");
        sb.append("Seat Row: ").append(seatRow).append("\n");
        sb.append("Seat No: ").append(seatNo).append("\n");
        sb.append("Train Info: ").append(train.getTrainInfo()).append("\n");
        return sb.toString();
    }
}
