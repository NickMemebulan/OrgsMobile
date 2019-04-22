package com.example.nick.orgsmobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerFeedbackAdapter extends RecyclerView.Adapter<RecyclerFeedbackAdapter.FeedbackViewHolder> {
    @NonNull

    private static int createdViewHolder;
    private int count;

    public RecyclerFeedbackAdapter(int count){
        this.count = count;
        createdViewHolder = 0;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutForFeedbackViewHolder = R.layout.feedbacks_recycler_layout;
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForFeedbackViewHolder, viewGroup, shouldAttachToParentImmediately);
        FeedbackViewHolder feedbacksViewHolder = new FeedbackViewHolder(view);

        createdViewHolder++;
        System.out.println("Created Feedback View Holder: " + createdViewHolder);

        return feedbacksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder feedbackViewHolder, int i) {
        feedbackViewHolder.FeedbackCommenter.setImageResource(R.drawable.ic_person_black);
        int feedbackNumber = i + 1;
        feedbackViewHolder.FeedbackComment.setText("Feedback number " + feedbackNumber +" .");
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder{

        ImageView FeedbackCommenter;
        TextView FeedbackComment;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            FeedbackCommenter = itemView.findViewById(R.id.feedbackCommenter);
            FeedbackComment = itemView.findViewById(R.id.feedbackComment);
        }
    }
}
