package com.studiant.com.presentation.ui.components.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;
import com.studiant.com.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewPostulantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<com.studiant.com.presentation.presenters.model.User> contents;
    Context context;

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellRecyclerViewPostulantAdapter(Context context, ArrayList<com.studiant.com.presentation.presenters.model.User> contents) {
        this.contents = contents;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_job_postulant, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder cellViewHolder = (ViewHolder) holder;
        cellViewHolder.foldingCell.fold(true);
        cellViewHolder.titleNamePostulant.setText(contents.get(position).getFirstName() + " "+contents.get(position).getLastName());
        cellViewHolder.contentNamePostulant.setText(contents.get(position).getFirstName() + " "+contents.get(position).getLastName());
        cellViewHolder.studyPostulant.setText(contents.get(position).getDiplome());
        cellViewHolder.descriptionPostulant.setText(contents.get(position).getDescription());

        System.out.println("etudiant en "+ contents.get(position).getDiplome());

        Picasso.with(context).load(contents.get(position).getProfilePicture()).into(cellViewHolder.profilPicture);
        Picasso.with(context).load(contents.get(position).getProfilePicture()).into(cellViewHolder.profilPictureTitle);

        // set custom btn handler for list item from that item
        if (contents.get(position).getRequestBtnClickListener() != null) {
            cellViewHolder.contentRequestBtn.setOnClickListener(contents.get(position).getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            cellViewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toggle clicked cell state
                ((FoldingCell) v).toggle(false);
                // register in adapter that state for selected cell is toggled
                registerToggle(position);
            }
        });
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleNamePostulant;
        TextView contentNamePostulant;
        TextView studyPostulant;
        TextView descriptionPostulant;
        ImageView profilPicture;
        ImageView profilPictureTitle;
        TextView contentRequestBtn;
        TextView time;
        FoldingCell foldingCell;

        //Content Cell
        TextView name;
        TextView priceContent;
        TextView timeContent;
        TextView dateContent;

        public ViewHolder(View itemView){
            super(itemView);
            titleNamePostulant = (TextView) itemView.findViewById(R.id.title_name_postulant);
            contentNamePostulant = (TextView) itemView.findViewById(R.id.content_name_postulant);
            studyPostulant = (TextView) itemView.findViewById(R.id.title_study);
            descriptionPostulant = (TextView) itemView.findViewById(R.id.content_description_postulant);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            profilPicture = (ImageView) itemView.findViewById(R.id.content_profil_picture_postulant);
            profilPictureTitle = (ImageView) itemView.findViewById(R.id.imageViewProfilPictureTitle);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            timeContent = (TextView) itemView.findViewById(R.id.content_date);
            dateContent = (TextView) itemView.findViewById(R.id.content_price_job);

        }
    }
}
