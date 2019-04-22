package com.example.nick.orgsmobile;

import java.io.Serializable;

public class OrganizationEvents implements Serializable {

    String eventTitle;
    String eventOrganizer;
    String eventDate;
    String eventLocation;
    String eventDescription;
    int image;

    public OrganizationEvents(String title, String organizer, String date, String location, String description, int img){
        eventTitle = title;
        eventOrganizer = organizer;
        eventDate = date;
        eventLocation = location;
        eventDescription = description;
        image = img;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getImage() {
        return image;
    }
}
