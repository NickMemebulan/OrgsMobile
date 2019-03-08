package com.example.nick.orgsmobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class RecyclerEventAdapter  extends RecyclerView.Adapter<RecyclerEventAdapter.EventViewHolder> {
    @NonNull

    private static int createdViewHolder;
    private int count;

    public RecyclerEventAdapter(int count){
        this.count = count;
        createdViewHolder = 0;
    }

    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutForEventViewHolder = R.layout.home_event_layout;
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForEventViewHolder, viewGroup, shouldAttachToParentImmediately);
        EventViewHolder eventViewHolder = new EventViewHolder(view);

        createdViewHolder++;
        System.out.println("Created Home Event View Holder: " + createdViewHolder);

        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        eventViewHolder.EventPhoto.setImageResource(R.drawable.event_photo_handler);
        eventViewHolder.EventTitle.setText("Event number " + i +" .");
        eventViewHolder.EventDateTime.setText("Event date and time: " + i + " .");
        eventViewHolder.EventDesc.setText("Event description: " + i + " .");
        eventViewHolder.EventOrganizer.setText("Event Organizer: " + i + " .");
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        ImageView EventPhoto;
        TextView EventTitle;
        TextView EventDateTime;
        TextView EventDesc;
        TextView EventOrganizer;
        LinearLayout HomeEvent;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            EventPhoto = itemView.findViewById(R.id.homeEventImage);
            EventTitle = itemView.findViewById(R.id.homeEventTitle);
            EventDateTime = itemView.findViewById(R.id.homeEventDate);
            EventDesc = itemView.findViewById(R.id.homeEventDesc);
            EventOrganizer = itemView.findViewById(R.id.homeEventOrganizer);
            EventPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent eventTemplateIntent = new Intent(view.getContext(), TemplateEventLayout.class);
                    view.getContext().startActivity(eventTemplateIntent);
                }
            });
        }
    }
}
