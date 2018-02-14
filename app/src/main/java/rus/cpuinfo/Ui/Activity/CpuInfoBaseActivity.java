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

package rus.cpuinfo.Ui.Activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.Display;
import rus.cpuinfo.Injections.Component.CpuInfoActivityComponent;
import rus.cpuinfo.Injections.Component.DaggerCpuInfoActivityComponent;
import rus.cpuinfo.Injections.Modules.ActivityProvider;
import rus.cpuinfo.R;

public class CpuInfoBaseActivity extends AppCompatActivity {

    private MainController mMainController;
    private CpuInfoActivityComponent mCpuInfoActivityComponent;

    @Inject Display mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutId());

        mCpuInfoActivityComponent = DaggerCpuInfoActivityComponent.builder()
                .activityProvider(new ActivityProvider(this)).build();

        mCpuInfoActivityComponent.inject(this);
        mMainController = ((CpuInfoApp)getApplication()).getMainController();

    }

    @LayoutRes
    protected int getContentViewLayoutId() {
        return R.layout.main_layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainController.attachDisplay(mDisplay);
        mMainController.init();
    }

    @Override
    protected void onPause() {
        mMainController.suspend();
        mMainController.detachDisplay();
        super.onPause();
    }

    public Display getDisplay() {
        return mDisplay;
    }
}
