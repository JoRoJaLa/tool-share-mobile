package com.toolsharemobile.myapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Tool;
import com.amplifyframework.datastore.generated.model.ToolTypeEnum;
import com.toolsharemobile.myapplication.R;
import com.toolsharemobile.myapplication.activity.ViewBorrowedToolActivity;
import com.toolsharemobile.myapplication.activity.ViewLendedToolActivity;

import java.util.List;

public class LendToolRecyclerViewAdapter extends RecyclerView.Adapter{



    List<Tool> toolList;
    Context callingActivity;
    public static final String TAG = "Tool to delete";



    public LendToolRecyclerViewAdapter(List<Tool> toolList, Context callingActivity){
        this.toolList = toolList;
        this.callingActivity = callingActivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toolListingFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_tool_listing, parent, false);

        return new ToolListViewHolder(toolListingFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView textViewToolName = holder.itemView.findViewById(R.id.textViewFindToolName);
        TextView pendingTextView = holder.itemView.findViewById(R.id.pendingTextView);
        ImageView imageViewToolIcon = holder.itemView.findViewById(R.id.imageViewToolIcon);

        if (toolList.get(position).getToolType().equals(ToolTypeEnum.CROWBAR)) imageViewToolIcon.setImageResource(R.drawable.crowbar);
        if (toolList.get(position).getToolType().equals(ToolTypeEnum.SLEDGEHAMMER)) imageViewToolIcon.setImageResource(R.drawable.sledgehammer);
        if (toolList.get(position).getToolType().equals(ToolTypeEnum.DRILL)) imageViewToolIcon.setImageResource(R.drawable.drill);
        if (toolList.get(position).getToolType().equals(ToolTypeEnum.CIRCULARSAW)) imageViewToolIcon.setImageResource(R.drawable.circular_saw);
        if (toolList.get(position).getToolType().equals(ToolTypeEnum.JIGSAW)) imageViewToolIcon.setImageResource(R.drawable.jigsaw);


        textViewToolName.setText(toolList.get(position).getToolType().toString());
        Tool tool = toolList.get(position);

            pendingTextView.setVisibility(View.INVISIBLE);
            if (tool.getOpenBorrowRequest() == true || tool.getOpenReturnRequest() == true) {
                pendingTextView.setVisibility(View.VISIBLE);
            }





        View toolViewHolder = holder.itemView;
        toolViewHolder.setOnClickListener(view -> {
            Intent goToViewToolListingIntent = new Intent(callingActivity, ViewLendedToolActivity.class);
            goToViewToolListingIntent.putExtra(TAG, tool.getId());
            callingActivity.startActivity(goToViewToolListingIntent);
        });

    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }

    public static class ToolListViewHolder extends RecyclerView.ViewHolder{

        public ToolListViewHolder(View itemView){
            super(itemView);
        }
    }
}
