package com.studiant.com.presentation.ui.fragments.particulier;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.impl.DashboardPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.DashboardPresenter;
import com.studiant.com.presentation.ui.activities.particulier.AddJobActivity;
import com.studiant.com.presentation.ui.activities.particulier.ListPostulantActivity;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewJobParticulierAdapter;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.studiant.com.storage.Constants.INTENT_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ListJobParticulierFragment extends Fragment implements DashboardPresenter.View{

    private static final boolean GRID_LAYOUT = false;
    @BindView(R.id.mainListView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fabButton)
    FloatingActionButton fabButton;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<Job> mJobArrayList;
    private int mDeletedJobClick;

    FoldingCellRecyclerViewJobParticulierAdapter adapter;
    User user;

    private DashboardPresenter mPresenter;

    public ListJobParticulierFragment() {
        // Required empty public constructor
    }

    public static ListJobParticulierFragment newInstance(User user) {
        ListJobParticulierFragment fragment = new ListJobParticulierFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(INTENT_USER);

            mPresenter = new DashboardPresenterImpl(
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new JobRepositoryImpl()
            );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_list_job_particulier, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.getJobsByUser(user);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                mPresenter.getJobsByUser(user);
            }
        });

    }

    @OnClick(R.id.fabButton)
    void onClickFabButton() {
        Intent intent = new Intent(getApplicationContext(), AddJobActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(WSException e) {

    }

    public void refreshData(ArrayList<Job> jobArrayList){

        //mJobArrayList.clear();
        mJobArrayList = jobArrayList;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onJobsRetrieve(final ArrayList<Job> jobArrayList) {

        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
            refreshData(jobArrayList);
            return;
        }

        mJobArrayList = jobArrayList;
        // prepare elements to display

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
       adapter = new FoldingCellRecyclerViewJobParticulierAdapter(mJobArrayList, user, getContext());
        for (int i = 0 ; i<jobArrayList.size();i++){
            final int j = i;
            jobArrayList.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewStudiantClick(jobArrayList.get(j));
                }
            });

            jobArrayList.get(i).setStudiantCodeBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStudiantCodeClick(jobArrayList.get(j));
                }
            });

            jobArrayList.get(i).setDeleteBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDeletedJobClick = j;
                    onDeleteClick(jobArrayList.get(j));
                }
            });
        }
        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(adapter);

    }

    public void onViewStudiantClick(Job job){
        if (job.getStatut().equals("0") || job.getStatut().equals("1")){
            Intent intent = new Intent(getApplicationContext(), ListPostulantActivity.class);
            intent.putExtra(INTENT_USER, user);
            //intent.putExtra(INTENT_LIST_USER, job.getPostulants());
            intent.putExtra(INTENT_JOB, PresentationModelConverter.convertToJobDomainModel(job));
            this.startActivity(intent);
        }/*else if(job.getStatut().equals("1") ){
            Toast.makeText(getActivity(), "Vous avez déja choisi un étudiant",
                    Toast.LENGTH_LONG).show();
        }*/else{
            toastJobCompleted();
        }

    }

    public void onStudiantCodeClick(Job job){
        if (!job.getStatut().equals("2")){

            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getContext());
            }
            builder.setTitle("S'tudiant Code")
                    .setMessage("Le S'tudiant code est : "+job.getId())
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .show();
        }else{
            toastJobCompleted();
        }
    }

    public void onDeleteClick(Job job){
        mPresenter.deleteJob(job.getId());
    }

    @Override
    public void onJobDelete() {
        System.out.println("jobDelete");
        mJobArrayList.remove(mDeletedJobClick);
        adapter.notifyDataSetChanged();

    }


    public void toastJobCompleted(){
        Toast.makeText(getActivity(), "Ce job est terminé",
                Toast.LENGTH_LONG).show();
    }

}
