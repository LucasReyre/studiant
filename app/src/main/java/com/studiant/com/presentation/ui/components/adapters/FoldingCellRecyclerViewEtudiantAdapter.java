package com.studiant.com.presentation.ui.components.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.Job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewEtudiantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellRecyclerViewEtudiantAdapter(ArrayList<Job> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_job_etudiant, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder cellViewHolder = (ViewHolder) holder;
        cellViewHolder.foldingCell.fold(true);
        cellViewHolder.titleDescription.setText(contents.get(position).getDescription());
        String prix = contents.get(position).getPrix()+" €";
        cellViewHolder.price.setText(prix);
        cellViewHolder.time.setText(contents.get(position).getHeure());
        cellViewHolder.date.setText(contents.get(position).getDate());
        //viewHolder.toAddress.setText(item.getToAddress());
         cellViewHolder.requestsCount.setText(contents.get(position).getUtilisateur().getFirstName());

        //Content
        cellViewHolder.name.setText(contents.get(position).getUtilisateur().getFirstName());
        cellViewHolder.priceContent.setText(contents.get(position).getPrix());
        cellViewHolder.dateContent.setText(contents.get(position).getDate());
        cellViewHolder.timeContent.setText(contents.get(position).getHeure());
        cellViewHolder.description.setText(contents.get(position).getDescription());

        /*cellViewHolder.contentRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "OKOKOKOK" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
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
        TextView price;
        TextView titleDescription;
        TextView contentRequestBtn;
        //TextView pledgePrice;
        //TextView toAddress;
        TextView requestsCount;
        TextView date;
        TextView time;
        FoldingCell foldingCell;

        //Content Cell
        TextView name;
        TextView priceContent;
        TextView timeContent;
        TextView dateContent;
        TextView description;

        public ViewHolder(View itemView){
            super(itemView);
            titleDescription = (TextView) itemView.findViewById(R.id.title_description);
            price = (TextView) itemView.findViewById(R.id.title_price);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            //viewHolder.toAddress = (TextView) cell.findViewById(R.id.title_to_address);
            requestsCount = (TextView) itemView.findViewById(R.id.title_study);
            //viewHolder.pledgePrice = (TextView) cell.findViewById(R.id.title_pledge);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            timeContent = (TextView) itemView.findViewById(R.id.content_date);
            dateContent = (TextView) itemView.findViewById(R.id.content_price_job);
            description = (TextView) itemView.findViewById(R.id.content_description_postulant);

        }
    }
}
