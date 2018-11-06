package com.studiant.com.presentation.ui.fragments.common;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.SettingPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.presenters.interfaces.SettingPresenter;
import com.studiant.com.presentation.ui.activities.common.MainActivity;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment implements SettingPresenter.View {
    @BindView(R.id.settingScrollView) NestedScrollView settingScrollView;

    private SettingPresenter mPresenter;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        mPresenter = new SettingPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //setup materialviewpager
        MaterialViewPagerHelper.registerScrollView(getActivity(), settingScrollView);

    }

    @OnClick(R.id.textViewDeconnexion)
    void onLogoutClick(){
        mPresenter.logout();
    }

    @OnClick(R.id.imageViewFacebook)
    void onFacebookClick(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/StudiantOfficiel/"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.imageViewTwitter)
    void onTwitterClick(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/StudiantOffici1"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.imageViewInstagram)
    void onInstagramClick(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/studiantofficiel/"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.textViewStudiantSite)
    void onStudiantSiteClick(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.studiant.fr/"));
        startActivity(browserIntent);
    }

    @Override
    public void onLogOut() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
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
}
