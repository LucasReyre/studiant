package com.studiant.com.presentation.ui.components.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.AndroidApplication;
import com.studiant.com.R;
import com.studiant.com.domain.model.Categorie;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewJobParticulierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> contents;
    User user;
    Context context;

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private View.OnClickListener studiantCodeBtnClickListener;

    public FoldingCellRecyclerViewJobParticulierAdapter(ArrayList<Job> contents, User user, Context context) {
        this.contents = contents;
        this.user = user;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_job_particulier, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder cellViewHolder = (ViewHolder) holder;
        Log.d("onBindViewHolder", "ok "+position);
        cellViewHolder.foldingCell.fold(true);
//        cellViewHolder.titleDescription.setText(contents.get(position).getDescription());
        String prix = contents.get(position).getPrix()+" €";
        cellViewHolder.price.setText(prix);
        cellViewHolder.time.setText(contents.get(position).getHeure());
        cellViewHolder.date.setText(contents.get(position).getDate());
//        cellViewHolder.numberTextView.setText(String.valueOf(position));
        //Content
        cellViewHolder.name.setText(user.getFirstName());
        cellViewHolder.priceContent.setText(prix);
        cellViewHolder.dateContent.setText(contents.get(position).getDate());
        cellViewHolder.timeContent.setText(contents.get(position).getHeure());
        cellViewHolder.description.setText(contents.get(position).getDescription());
        cellViewHolder.categorieJobTextView.setText(contents.get(position).getCategorie());
        cellViewHolder.cityTextView.setText(contents.get(position).getAdresse());
        cellViewHolder.nummberPostulant.setText(AndroidApplication.getContext().getString(R.string.number_studiant_postulant, String.valueOf(contents.get(position).getPostulants().size())));

        Categorie categorie = new Categorie(contents.get(position).getCategorie());
        cellViewHolder.categorieJobImageView.setImageResource(categorie.getImageRessource());

        switch (contents.get(position).getModePaiement()){
            case "CB":
                cellViewHolder.modePaiementJobImageView.setImageResource(R.drawable.credit_card);
                cellViewHolder.studiantCodeBtn.setVisibility(View.VISIBLE);
                break;
            case "ESPECES":
                cellViewHolder.modePaiementJobImageView.setImageResource(R.drawable.change);
                cellViewHolder.studiantCodeBtn.setVisibility(View.INVISIBLE);
                break;
            case "CESU":
                cellViewHolder.modePaiementJobImageView.setImageResource(R.drawable.check);
                cellViewHolder.studiantCodeBtn.setVisibility(View.INVISIBLE);
                break;
        }

        if (contents.get(position).getStatut().equals("2")){
            cellViewHolder.backgroundJobImageView.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
            cellViewHolder.studiantCodeBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
            cellViewHolder.contentRequestBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
            cellViewHolder.deleteBtn.setVisibility(View.GONE);
        }else{
            cellViewHolder.backgroundJobImageView.setBackground(ContextCompat.getDrawable(context,R.drawable.background_job));
            cellViewHolder.deleteBtn.setVisibility(View.VISIBLE);
        }
        /*cellViewHolder.contentRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "OKOKOKOK" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
        // set custom btn handler for list item from that item
        if (contents.get(position).getRequestBtnClickListener() != null) {
            cellViewHolder.contentRequestBtn.setOnClickListener(contents.get(position).getRequestBtnClickListener());
            cellViewHolder.studiantCodeBtn.setOnClickListener(contents.get(position).getStudiantCodeBtnClickListener());
            cellViewHolder.deleteBtn.setOnClickListener(contents.get(position).getDeleteBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            cellViewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
            cellViewHolder.studiantCodeBtn.setOnClickListener(defaultRequestBtnClickListener);
            cellViewHolder.deleteBtn.setOnClickListener(defaultRequestBtnClickListener);
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
        TextView numberTextView;
        TextView titleDescription;
        TextView contentRequestBtn;
        TextView studiantCodeBtn;
        TextView deleteBtn;
        TextView date;
        TextView time;
        TextView nummberPostulant;
        TextView categorieJobTextView;
        ImageView categorieJobImageView;
        ImageView modePaiementJobImageView;
        RelativeLayout backgroundJobImageView;
        TextView cityTextView;
        FoldingCell foldingCell;

        //Content Cell
        TextView name;
        TextView priceContent;
        TextView timeContent;
        TextView dateContent;
        TextView description;

        public ViewHolder(View itemView){
            super(itemView);
            backgroundJobImageView = (RelativeLayout) itemView.findViewById(R.id.backgoundJob);
            categorieJobImageView = (ImageView) itemView.findViewById(R.id.categorieImageView);
            modePaiementJobImageView = (ImageView) itemView.findViewById(R.id.modePaiementJobParticulierImageView);
            titleDescription = (TextView) itemView.findViewById(R.id.categorieTextView);
            //numberTextView = (TextView) itemView.findViewById(R.id.numberTextView);
            price = (TextView) itemView.findViewById(R.id.title_price);
            price = (TextView) itemView.findViewById(R.id.title_price);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            studiantCodeBtn =  itemView.findViewById(R.id.studiant_btn);
            deleteBtn =  itemView.findViewById(R.id.delete_btn);
            categorieJobTextView = (TextView) itemView.findViewById(R.id.categorieTextView);
            cityTextView = (TextView) itemView.findViewById(R.id.cityTextView);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            timeContent = (TextView) itemView.findViewById(R.id.content_date);
            dateContent = (TextView) itemView.findViewById(R.id.content_date_job);
            description = (TextView) itemView.findViewById(R.id.content_description_postulant);
            nummberPostulant = (TextView) itemView.findViewById(R.id.number_postulant_textview);

        }
    }
}
