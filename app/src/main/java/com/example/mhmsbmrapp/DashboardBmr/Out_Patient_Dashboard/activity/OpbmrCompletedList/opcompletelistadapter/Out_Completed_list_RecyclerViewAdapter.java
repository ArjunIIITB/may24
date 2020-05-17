package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList.opcompletelistadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.PatientDetails;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList.opcompletelistmodel.Out_Completed_list_AnimeOpBmrTab;
import com.example.mhmsbmrapp.R;

import java.util.List;

public class Out_Completed_list_RecyclerViewAdapter extends RecyclerView.Adapter<Out_Completed_list_RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Out_Completed_list_AnimeOpBmrTab> mData ;
    RequestOptions option;


    public Out_Completed_list_RecyclerViewAdapter(Context mContext, List<Out_Completed_list_AnimeOpBmrTab> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }
    @NonNull
    @Override
    public Out_Completed_list_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.op_row_item,parent,false) ;
        final Out_Completed_list_RecyclerViewAdapter.MyViewHolder viewHolder = new Out_Completed_list_RecyclerViewAdapter.MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, PatientDetails.class);
                i.putExtra("anime_givenName",mData.get(viewHolder.getAdapterPosition()).getGivenName());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getMiddleName());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getPersonId());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getPhoneNumber());
                i.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getDateOfBirth());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getEmail());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());
                i.putExtra("anime_patientName",mData.get(viewHolder.getAdapterPosition()).getPatientName());
                i.putExtra("anime_patientId",mData.get(viewHolder.getAdapterPosition()).getPatientId());
                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Out_Completed_list_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getGivenName());
        holder.tv_rating.setText(mData.get(position).getEmail());
        holder.tv_studio.setText(mData.get(position).getPersonId());
        holder.tv_category.setText(mData.get(position).getPhoneNumber());
        holder.tv_patientName.setText(mData.get(position).getPatientName());
        holder.tv_patientId.setText(mData.get(position).getPatientId());

        // Load Image from the internet and set it into Imageview using Glide

        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);



    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name ;
        TextView tv_rating ;
        TextView tv_studio ;
        TextView tv_category;
        TextView tv_patientName;
        TextView tv_patientId;
        //ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            //img_thumbnail = itemView.findViewById(R.id.thumbnail);
            tv_patientName = itemView.findViewById(R.id.patientName);
            tv_patientId = itemView.findViewById(R.id.patientId);

        }
    }
}