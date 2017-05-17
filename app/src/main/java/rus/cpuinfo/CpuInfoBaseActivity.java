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

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import rus.cpuinfo.Controllers.MainController;

public class CpuInfoBaseActivity extends AppCompatActivity {

    private MainController mMainController;
    private Display mDisplay = new CpuInfoUiDisplay(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewLayoutId());
        mMainController = CpuInfoApp.from(this).getMainController();
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

    @Override
    public final void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        mDisplay.setSupportActionBar(toolbar);
    }

    public Display getDisplay() {
        return mDisplay;
    }
}
