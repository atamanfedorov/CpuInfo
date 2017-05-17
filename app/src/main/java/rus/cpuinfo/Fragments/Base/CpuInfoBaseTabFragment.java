/*
 * Copyright 2017 Ruslan_<<RUS_M>>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rus.cpuinfo.Fragments.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.R;

import static rus.cpuinfo.R.id.ViewPager;

public abstract class CpuInfoBaseTabFragment extends Fragment implements MainController.MainControllerUi{


    private static final String SELECTED_TAB = "SEL_TAB";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainController.Tabs [] mTabs;

    private int mCurrentItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTabs(MainController.Tabs... tabs) {
        mTabs = tabs;
    }

    protected final MainController.Tabs[] getTabs() {
        return mTabs;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tab, container, false);

        mViewPager = (ViewPager)view.findViewById(ViewPager);
        mViewPager.setAdapter(createAdapter());

        mTabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        if (savedInstanceState != null) {
            mCurrentItem = savedInstanceState.getInt(SELECTED_TAB);
        }

        return view;
    }

    protected void setFragments(@NonNull List<Fragment> fragments) {
        getAdapter().setFragments(fragments);
        mViewPager.setCurrentItem(mCurrentItem);
    }

    public void setSupportActionBar(Toolbar toolbar) {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final AppCompatSpinner spinner = (AppCompatSpinner) view.findViewById(R.id.action_bar_spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mViewPager.setCurrentItem(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    spinner.setSelection(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });}
    }

    @NonNull
    protected TabPagerAdapter createAdapter() {
        return new TabPagerAdapter(getChildFragmentManager());
    }

    @NonNull
    protected  TabPagerAdapter getAdapter()
    {
        return (TabPagerAdapter)mViewPager.getAdapter();
    }

    protected class TabPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments;

        protected TabPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
        }

        public void setFragments(@NonNull List<Fragment> fragments) {
            mFragments.clear();
            mFragments.addAll(fragments);
            notifyDataSetChanged();
        }

        @Override
        public final Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }


        @Override
        public final int getCount() {
            return mFragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return getTabTitle(position);
        }
    }

    protected abstract String getTabTitle(int position);


    protected final MainController getMainController()
    {
        return CpuInfoApp.from(getActivity()).getMainController();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurrentItem = mViewPager.getCurrentItem();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_TAB, mCurrentItem);
        super.onSaveInstanceState(outState);
    }
}





