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



package rus.cpuinfo.Controllers;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.google.common.base.Preconditions;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.DeviceInfo.DeviceInfo;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Repeater;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

public abstract class InfoController extends BaseUi<InfoController.InfoUi> {

    public interface InfoUi
    {
        void setAdapter(HardwareInfoAdapter hardwareInfoAdapter);
    }

    private HardwareInfoAdapter mHardwareInfoAdapter;
    private IStringFetcher mStringFetcher;
    private DeviceInfo mDeviceInfo;
    private ILogger mLogger;

    private Repeater mRepeater = new Repeater();

    private final static String mTag = InfoController.class.getSimpleName();

    public InfoController(@NonNull HardwareInfoAdapter hardwareInfoAdapter,@NonNull DeviceInfo deviceInfo,@NonNull IStringFetcher stringFetcher,@NonNull ILogger logger)
    {
        mDeviceInfo = Preconditions.checkNotNull(deviceInfo,"deviceInfo must not be null");
        mStringFetcher = Preconditions.checkNotNull(stringFetcher,"stringFetcher must not be null");
        mHardwareInfoAdapter = Preconditions.checkNotNull(hardwareInfoAdapter,"hardwareInfoAdapter must not be null");
        mLogger = Preconditions.checkNotNull(logger,"logger must not be null");
    }

    @NonNull
    private HardwareInfoAdapter getAdapter()
    {
        return mHardwareInfoAdapter;
    }


    @NonNull String getString(@StringRes int id) {
        return mStringFetcher.getString(id);
    }

    @Override
    protected void onUiAttached(InfoUi ui) {
        ui.setAdapter(mHardwareInfoAdapter);
        mLogger.d(mTag,"InfoController. onUiAttached");
        mRepeater.startRepeating();
    }

    @Override
    protected void onUiDetached(InfoUi ui) {
        mLogger.d(mTag,"InfoController. onUiDetached");
        mRepeater.stopRepeating();
    }

    String getInfo(int q)
    {
        return mDeviceInfo.getInfo(q);
    }

    Repeater getRepeater()
    {
        return mRepeater;
    }

    ILogger getLogger()
    {
        return mLogger;
    }

    void updateInformation(@NonNull rus.cpuinfo.Model.HardwareInfo hardwareInfo)
    {
        mLogger.d(mTag,"cl-InfoController. m - updateInformation. p1 - hardwareInfo = " + hardwareInfo);

        HardwareInfoAdapter hardwareInfoAdapter = getAdapter();

        hardwareInfoAdapter.setInformation(hardwareInfo);
        hardwareInfoAdapter.notifyDataSetChanged();
    }

}

