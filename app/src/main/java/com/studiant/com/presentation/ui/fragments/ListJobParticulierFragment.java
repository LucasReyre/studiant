package com.studiant.com.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.impl.AddJobPresenterImpl;
import com.studiant.com.presentation.presenters.impl.DashboardPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.DashboardPresenter;
import com.studiant.com.presentation.ui.activities.AddJobActivity;
import com.studiant.com.presentation.ui.components.FoldingCellListAdapter;
import com.studiant.com.presentation.ui.components.FoldingCellRecyclerViewAdapter;
import com.studiant.com.presentation.ui.components.Item;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ListJobParticulierFragment extends Fragment implements DashboardPresenter.View{

    private static final boolean GRID_LAYOUT = false;
    @BindView(R.id.mainListView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fabButton)
    FloatingActionButton fabButton;

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

            mPresenter.getJobsByUser(user);

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

        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getApplicationContext(), items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new FoldingCellRecyclerViewAdapter(items));

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
    public void showError(String message) {

    }

    @Override
    public void onJobsRetrieve(ArrayList<Job> jobArrayList) {

    }
}
