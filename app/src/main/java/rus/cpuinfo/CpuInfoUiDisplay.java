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

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Singleton;

@Singleton
public class CpuInfoUiDisplay implements Display {

    private final AppCompatActivity mActivity;

    public CpuInfoUiDisplay(AppCompatActivity mMainActivity)
    {
        this.mActivity = mMainActivity;
    }

    @Override
    public void setSupportActionBar(Toolbar toolbar) {

        if (toolbar != null) {

            ActionBar ab = mActivity.getSupportActionBar();
            if (ab != null) {
                ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE );
            }
        }
    }

}
