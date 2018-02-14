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

package rus.cpuinfo.Ui.Fragments.Base;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.R;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public abstract class CpuInfoBaseTabFragment extends Fragment implements MainController.MainControllerUi,AdapterView.OnItemSelectedListener,TabLayout.OnTabSelectedListener{

   @BindView(R.id.viewPager) ViewPager mViewPager;
   @BindView(R.id.slidingTabs) TabLayout mTabLayout;


    AppCompatSpinner mSpinner;
    MainController.Tabs [] mTabs;
    int mCurrentItem;
    static final String SELECTED_TAB = "SEL_TAB";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this,v);

        mViewPager.setAdapter(createAdapter());
        mTabLayout.setupWithViewPager(mViewPager);

        View root = mTabLayout.getChildAt(0);

        if (root instanceof LinearLayout) {

            LinearLayout linearLayout = ((LinearLayout) root);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

            GradientDrawable drawable =  new GradientDrawable();
            drawable.setSize(2,0);

            linearLayout.setDividerDrawable(drawable);
        }


        if (savedInstanceState != null) {
            mCurrentItem = savedInstanceState.getInt(SELECTED_TAB);
        }

        return v;
    }

    protected void setFragments(@NonNull List<Fragment> fragments) {
        getAdapter().setFragments(fragments);
        mViewPager.setCurrentItem(mCurrentItem);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        final int position = tab.getPosition();
        mSpinner.setSelection(position);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);

        inflater.inflate(R.menu.options, menu);

        MenuItem menuItem = menu.getItem(0);
        menuItem.setActionView(R.layout.include_app_comat_spinner);

        mSpinner = (AppCompatSpinner)menuItem.getActionView();
        mSpinner.setOnItemSelectedListener(this);
        mTabLayout.setOnTabSelectedListener(this);

        mSpinner.setSelection(mCurrentItem);

        if (getResources().getConfiguration().orientation
                    == ORIENTATION_PORTRAIT) {
            menuItem.setVisible(false);
        }
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

        TabPagerAdapter(FragmentManager fm) {
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





