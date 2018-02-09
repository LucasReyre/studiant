package com.studiant.com.presentation.ui.activities.particulier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.fragments.common.SettingFragment;
import com.studiant.com.presentation.ui.components.AdjustKeyboard;
import com.studiant.com.presentation.ui.fragments.particulier.InscriptionParticulierFragment;
import com.studiant.com.presentation.ui.fragments.particulier.ListJobParticulierFragment;
import com.studiant.com.presentation.ui.fragments.RecyclerViewFragment;
import com.studiant.com.presentation.ui.fragments.particulier.ProfilParticulierFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.studiant.com.storage.Constants.INTENT_USER;


public class DashboardParticulierActivity extends AppCompatActivity implements ProfilParticulierFragment.OnFragmentInteractionListener, InscriptionParticulierFragment.OnFragmentInteractionListener{


    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_particulier);
        setTitle("");
        ButterKnife.bind(this);
        AdjustKeyboard.assistActivity(this);

        final User user = (User) getIntent().getSerializableExtra(INTENT_USER);
        System.out.println("user : "+user.getTelephone());

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 3) {
                    case 0:
                        return ListJobParticulierFragment.newInstance(user);
                    case 1:
                        return ProfilParticulierFragment.newInstance(user);
                    case 2:
                        return SettingFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 3) {
                    case 0:
                        return "Offres";
                    case 1:
                        return "Profil";
                    case 2:
                        return "Réglages";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.warm_grey,
                                ContextCompat.getDrawable(getApplicationContext(),R.drawable.home1));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.warm_grey,
                                ContextCompat.getDrawable(getApplicationContext(),R.drawable.home2));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorBackground,
                                ContextCompat.getDrawable(getApplicationContext(),R.drawable.mosaique));
                   /* case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");*/
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getToolbar().setVisibility(GONE);

        /*final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // mViewPager.notifyHeaderChanged();
                   // Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

    }
}