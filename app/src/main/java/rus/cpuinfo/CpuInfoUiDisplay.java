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

package rus.cpuinfo;

import android.content.res.ColorStateList;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CpuInfoUiDisplay implements Display {

    private final AppCompatActivity mActivity;
    private Toolbar mToolbar;

    CpuInfoUiDisplay(AppCompatActivity mMainActivity)
    {
        this.mActivity = mMainActivity;
    }

    @Override
    public void finishActivity() {
        mActivity.finish();
    }

    @Override
    public void setSupportActionBar(Toolbar toolbar) {


        if (toolbar != null) {

            mToolbar = toolbar;

            ActionBar ab = mActivity.getSupportActionBar();
            if (ab != null) {
                ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                ab.setCustomView(getView(), (ActionBar.LayoutParams) getLayoutParams());
            }
        }

        }

    protected View getView()
    {

        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View root  = inflater.inflate(R.layout.header, null);

        ColorStateList colorStateList = mActivity.getResources().getColorStateList(R.color.default_button);

        AppCompatImageButton appCompatImageButton = (AppCompatImageButton)root.findViewById(R.id.action_bar_image_button);
        appCompatImageButton.setSupportBackgroundTintList(colorStateList);

        return root;

    }

    private ViewGroup.LayoutParams getLayoutParams()
    {
        return new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
