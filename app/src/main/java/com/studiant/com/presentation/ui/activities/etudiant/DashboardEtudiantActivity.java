package com.studiant.com.presentation.ui.activities.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.particulier.DashboardParticulierActivity;
import com.studiant.com.presentation.ui.components.AdjustKeyboard;
import com.studiant.com.presentation.ui.fragments.common.SettingFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.AddRibFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.FilterFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.ListHistoriqueJobEtudiantFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.ListJobEtudiantFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.ProfilEtudiantFragment;
import com.studiant.com.presentation.ui.fragments.RecyclerViewFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.StudiantCodeFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;


public class DashboardEtudiantActivity extends AppCompatActivity implements FilterFragment.OnFragmentInteractionListener,
        AddRibFragment.OnFragmentInteractionListener, StudiantCodeFragment.OnFragmentInteractionListener
{


    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    public boolean isRibDisplay = false;
    private ListJobEtudiantFragment listJobEtudiantFragment;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);
        setTitle("");
        ButterKnife.bind(this);
        AdjustKeyboard.assistActivity(this);

        this.user = (User) getIntent().getSerializableExtra(INTENT_USER);


        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        listJobEtudiantFragment = ListJobEtudiantFragment.newInstance(user);
                        return listJobEtudiantFragment;
                    case 1:
                        return ListHistoriqueJobEtudiantFragment.newInstance(user);
                    case 2:
                        return ProfilEtudiantFragment.newInstance(user);
                    case 3:
                        return SettingFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Les offres";
                    case 1:
                        return "Mes jobs";
                    case 2:
                        return "Mon Compte";
                    case 3:
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
                                R.color.warm_grey,
                                ContextCompat.getDrawable(getApplicationContext(),R.drawable.home3));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorBackground,
                                ContextCompat.getDrawable(getApplicationContext(),R.drawable.mosaique));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getToolbar().setVisibility(GONE);
/*
        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

        /*
        // get our list view
        ListView theListView = (ListView) findViewById(R.id.mainListView);

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
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });
*/
    }

    @Override
    public void onBackPressed() {

        if (!isRibDisplay){
            super.onBackPressed();

        }else{

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment f = getSupportFragmentManager().findFragmentByTag(AddRibFragment.class.getName());
            if(f!=null){
                ft.remove(f);
                ft.commit();
            }
        }

    }

    @Override
    public void onValidateFilter(String price, String categorie) {
        listJobEtudiantFragment.onFilterRequest(price, categorie);
    }

    public void updateUser(User user){
        this.user = user;
    }

    public void displaySetStudiantCode(Job job){
        StudiantCodeFragment studiantCodeFragment =StudiantCodeFragment.newInstance(user, job);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.add(android.R.id.content,studiantCodeFragment, studiantCodeFragment.getClass().getName()).commit();
    }

    @Override
    public void onAddRibDisplay() {
        isRibDisplay = true;
    }

    @Override
    public void onAddRibClose() {
        isRibDisplay = false;
    }

    public void reloadDashboardEtudiant(){
        Intent intent = new Intent(this, DashboardEtudiantActivity.class);
        intent.putExtra(INTENT_USER, user);
        startActivity(intent);
        finish();
    }
}