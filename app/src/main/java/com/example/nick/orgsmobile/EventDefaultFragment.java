package com.example.nick.orgsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDefaultFragment extends Fragment {

    private RecyclerView feedbacksRecyclerView;
    private RecyclerView.Adapter feedbacksAdapter;
    private RecyclerView.LayoutManager feedbacksLayoutManager;
    private int numberOfSampleFeedbacks = 5;

    public EventDefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View eventView = inflater.inflate(R.layout.event_default_fragment, container, false);

        TextView eventTitle = eventView.findViewById(R.id.eventTitle);
        TextView eventOrganizer = eventView.findViewById(R.id.eventOrganizer);
        ImageView eventImage = eventView.findViewById(R.id.eventPhoto);
        TextView eventPlace = eventView.findViewById(R.id.eventPlace);
        TextView eventDateTime = eventView.findViewById(R.id.eventDateTime);
        TextView eventDescription = eventView.findViewById(R.id.eventDescription);

        OrganizationEvents event = ((TemplateEventLayout) getActivity()).getEvent();

        eventTitle.setText(event.getEventTitle());
        eventOrganizer.setText(event.getEventOrganizer());
        eventImage.setImageResource(event.getImage());
        eventPlace.setText(event.getEventLocation());
        eventDateTime.setText(event.getEventDate());
        eventDescription.setText(event.getEventDescription());


        feedbacksRecyclerView = (RecyclerView) eventView.findViewById(R.id.eventFeedbacks);
        feedbacksRecyclerView.setHasFixedSize(true);
        feedbacksLayoutManager = new LinearLayoutManager(eventView.getContext());
        feedbacksRecyclerView.setLayoutManager(feedbacksLayoutManager);
        feedbacksAdapter = new RecyclerFeedbackAdapter(numberOfSampleFeedbacks);
        feedbacksRecyclerView.setAdapter(feedbacksAdapter);

        return eventView;
    }

}
