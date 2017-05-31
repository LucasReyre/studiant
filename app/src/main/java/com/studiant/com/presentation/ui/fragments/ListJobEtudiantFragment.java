package com.studiant.com.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.impl.DashboardEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.DashboardEtudiantPresenter;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewEtudiantAdapter;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.PostulantRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ListJobEtudiantFragment extends Fragment implements DashboardEtudiantPresenter.View{

    private static final boolean GRID_LAYOUT = false;
    @BindView(R.id.mainListView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fabButton)
    FloatingActionButton fabButton;

    User user;

    private DashboardEtudiantPresenter mPresenter;

    public ListJobEtudiantFragment() {
        // Required empty public constructor
    }

    public static ListJobEtudiantFragment newInstance(User user) {
        ListJobEtudiantFragment fragment = new ListJobEtudiantFragment();
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

            mPresenter = new DashboardEtudiantPresenterImpl(
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new JobRepositoryImpl(),
                    new PostulantRepositoryImpl()
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_list_job_etudiant, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.getJobs();

    }

    @OnClick(R.id.fabButton)
    void onClickFabButton() {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJobsRetrieve(final ArrayList<Job> jobArrayList) {
        // prepare elements to display
        //final ArrayList<Item> items = Item.getTestingList();

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellRecyclerViewEtudiantAdapter adapter = (new FoldingCellRecyclerViewEtudiantAdapter(jobArrayList));

        for (int i = 0 ; i<jobArrayList.size();i++){
            final int j = i;
            jobArrayList.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPostulerClick(jobArrayList.get(j));
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

    public void onPostulerClick(Job job){
        Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR "+job.getDescription(), Toast.LENGTH_SHORT).show();
        mPresenter.insertPostulant(job, user);
    }
}
