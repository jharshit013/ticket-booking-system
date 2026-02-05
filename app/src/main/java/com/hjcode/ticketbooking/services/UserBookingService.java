package com.hjcode.ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjcode.ticketbooking.entities.Ticket;
import com.hjcode.ticketbooking.entities.User;
import com.hjcode.ticketbooking.util.UserServiceUtil;

public class UserBookingService {
    private User user; // the user who is booking the ticket

    private List<User> userList; // the list of all users in the system, this is used to check if the user is
                                 // valid and to update the user's
    // booking history

    private ObjectMapper objectMapper = new ObjectMapper(); // for mapping json to java objects and vice versa

    private static final String USERS_PATH = "../localDb/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = this.objectMapper.readValue(users, new TypeReference<List<User>>() { // read the users from the json
                                                                                        // file and map them to a list
                                                                                        // of User objects and
                                                                                        // TypeReference is used to
                                                                                        // specify the type of the list
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(u -> { // Optional is used to avoid null pointer exceptions
            return u.getName().equalsIgnoreCase(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), u.getHashedPassword());
        }).findFirst(); // check if the user exists in the list and if the password is correct, if both
                        // are true, return true, otherwise return false
        return foundUser.isPresent();
    }

    public Boolean signUp(User u) {
        try {
            userList.add(u);
            saveUsersToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private void saveUsersToFile() throws JsonGenerationException, JsonMappingException, IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile, userList); // serialize the userList to the json file
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        List<Ticket> tickets = user.getTicketsBooked();
        Optional<Ticket> ticketToCancel = tickets.stream().filter(t -> t.getTicketId().equals(ticketId)).findFirst();
        if (ticketToCancel.isPresent()) {
            tickets.remove(ticketToCancel.get());
            try {
                saveUsersToFile();
                return Boolean.TRUE;
            } catch (IOException e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        } else {
            return Boolean.FALSE; // ticket not found
        }
    }
}
