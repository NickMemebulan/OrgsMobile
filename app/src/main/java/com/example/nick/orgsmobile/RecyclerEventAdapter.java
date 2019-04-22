package com.example.nick.orgsmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerEventAdapter  extends RecyclerView.Adapter<RecyclerEventAdapter.EventViewHolder> {
    @NonNull

    private static int createdViewHolder;
    private int count;
    private ArrayList<OrganizationEvents> organizationEvents;

    public RecyclerEventAdapter(int count, ArrayList<OrganizationEvents> orgEvents){
        this.count = count;
        createdViewHolder = 0;
        organizationEvents = orgEvents;
    }

    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutForEventViewHolder = R.layout.events_recycler_layout;
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForEventViewHolder, viewGroup, shouldAttachToParentImmediately);
        EventViewHolder eventViewHolder = new EventViewHolder(view);

        createdViewHolder++;
        System.out.println("Created Home Event View Holder: " + createdViewHolder);

        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        eventViewHolder.EventPhoto.setImageResource(organizationEvents.get(i).getImage());
        eventViewHolder.EventTitle.setText(organizationEvents.get(i).getEventTitle());
        eventViewHolder.EventDateTime.setText(organizationEvents.get(i).getEventDate());
        eventViewHolder.EventOrganizer.setText(organizationEvents.get(i).getEventOrganizer());
        eventViewHolder.event = organizationEvents.get(i);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        ImageView EventPhoto;
        TextView EventTitle;
        TextView EventDateTime;
        TextView EventOrganizer;
        OrganizationEvents event;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            EventPhoto = itemView.findViewById(R.id.homeEventImage);
            EventTitle = itemView.findViewById(R.id.homeEventTitle);
            EventDateTime = itemView.findViewById(R.id.homeEventDate);
            EventOrganizer = itemView.findViewById(R.id.homeEventOrganizer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent eventTemplateIntent = new Intent(view.getContext(), TemplateEventLayout.class);
                    eventTemplateIntent.putExtra("event", (Serializable) event);
                    view.getContext().startActivity(eventTemplateIntent);
                }
            });
        }
    }
}
