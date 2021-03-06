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

package rus.cpuinfo.Ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rus.cpuinfo.Controllers.BatteryController;
import rus.cpuinfo.Ui.Fragments.Base.CpuInfoBaseFragment;

public class CpuInfoBatteryFragment extends CpuInfoBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static CpuInfoBatteryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CpuInfoBatteryFragment fragment = new CpuInfoBatteryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBatteryController().init();
    }

    @Override
    public void onPause() {
        super.onPause();
        getBatteryController().suspend();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBatteryController().attachUi(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getBatteryController().detachUi(this);
    }

    protected BatteryController getBatteryController() {
        return getController().getBatteryController();
    }

}
