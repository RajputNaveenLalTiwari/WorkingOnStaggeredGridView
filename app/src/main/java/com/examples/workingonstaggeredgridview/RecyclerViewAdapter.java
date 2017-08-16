package com.examples.workingonstaggeredgridview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 2114 on 13-02-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static final int NUM_ITEMS = 100;
    private TypedArray imageResources;
    private String[] thumbnailTitles;

    Random random;

    private List<Integer> randomSizeList,ramdomImageResourceList;
    private static List<String> randomTitleList;

    /**
     *   Constructor
     */
    public RecyclerViewAdapter(Context context)
    {
        imageResources = context.getResources().obtainTypedArray(R.array.imageResources);
        thumbnailTitles = context.getResources().getStringArray(R.array.thumbnailTitles);
        random = new Random();
        randomSizeList = new ArrayList<>();
        ramdomImageResourceList = new ArrayList<>();
        randomTitleList = new ArrayList<>();

        for (int i=0;i<NUM_ITEMS;i++)
        {
            randomSizeList.add(getRandomIntInRange(250,75));
            int position = random.nextInt(11);
            ramdomImageResourceList.add(imageResources.getResourceId(position,-1));
            randomTitleList.add(thumbnailTitles[position]);
        }

    }

    /**
     *   Inflate our view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     *   Show content of our view
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.getRecyclerViewImage().getLayoutParams().height = randomSizeList.get(position);
//        holder.getRecyclerViewImage().setBackgroundColor(getRandomHSVColor());
        holder.getRecyclerViewImage().setImageResource(ramdomImageResourceList.get(position));
    }

    /**
     *   Return's total number of items
     */
    @Override
    public int getItemCount()
    {
        return NUM_ITEMS;
    }

    // Custom method to get a random number between a range
    protected int getRandomIntInRange(int max, int min){
        return random.nextInt((max-min)+min)+min;
    }

    // Custom method to generate random HSV color
    protected int getRandomHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = random.nextInt(361);
        // We make the color depth full
        float saturation = 1.0f;
        // We make a full bright color
        float value = 1.0f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }

    /**
     *   Regiter our view's and return
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView recyclerViewImage;
        public ViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(this);
            recyclerViewImage = (ImageView) view.findViewById(R.id.recyclerViewImageID);
        }

        public ImageView getRecyclerViewImage()
        {
            return recyclerViewImage;
        }

        @Override
        public void onClick(View v)
        {
            int position = getAdapterPosition();
            Toast.makeText(v.getContext(),randomTitleList.get(position),Toast.LENGTH_LONG).show();
        }
    }
}
