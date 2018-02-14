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

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.Ui.Fragments.Base.CpuInfoBaseTabFragment;
import rus.cpuinfo.Util.StringHelper;

import static rus.cpuinfo.Controllers.MainController.Tabs.ABOUT;
import static rus.cpuinfo.Controllers.MainController.Tabs.BATTERY;
import static rus.cpuinfo.Controllers.MainController.Tabs.CPU;
import static rus.cpuinfo.Controllers.MainController.Tabs.DEVICE;
import static rus.cpuinfo.Controllers.MainController.Tabs.SENSORS;
import static rus.cpuinfo.Controllers.MainController.Tabs.SYSTEM;

public class CpuInfoTabTabFragment extends CpuInfoBaseTabFragment implements MainController.MainControllerUi
{
    private Map mFragments = createFragments();

    @Override
    public final void setTabs(MainController.Tabs[] tabs) {
        super.setTabs(tabs);

        TabPagerAdapter tabPagerAdapter = getAdapter();
        if (tabPagerAdapter.getCount() != tabs.length) {
            List<Fragment> fragments = new ArrayList<>();

            for (MainController.Tabs tab : tabs)
                    fragments.add(getFragment(tab));

            setFragments(fragments);
        }
    }

    private Fragment getFragment(MainController.Tabs tab) {
        return (Fragment) mFragments.get(tab);
    }

    private static Map createFragments()
    {
        return ArrayUtils.toMap(new Object[][]
                {{CPU,CpuInfoCpuFragment.newInstance()},
                {DEVICE,CpuInfoDeviceFragment.newInstance()},
                {SYSTEM,CpuInfoSystemFragment.newInstance()},
                {BATTERY,CpuInfoBatteryFragment.newInstance()},
                {SENSORS,CpuInfoSensorsFragment.newInstance()},
                {ABOUT,CpuInfoAboutFragment.newInstance()}});
    }

    @Override
    @Nullable
    protected final String getTabTitle(int position) {
        final MainController.Tabs [] tabs = getTabs();
        return tabs == null ? null : getString(StringHelper.getStringResId(tabs[position]));
    }

    @Override
    public void onResume() {
        super.onResume();
        getMainController().attachUi(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMainController().detachUi(this);
    }
}
