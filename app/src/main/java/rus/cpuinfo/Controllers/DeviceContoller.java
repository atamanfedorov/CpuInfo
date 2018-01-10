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

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Qualifers.ForDevice;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Repeater;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

import static rus.cpuinfo.Model.BaseInfo.DEVICE_AVAILABLE_STORAGE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_AVIALABLE_RAM;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_BOARD;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_INTERNAL_STORAGE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_MANUFACTURER;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_MODEL;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_DENSITY;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_RESOLUTION;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_SIZE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_TOTAL_RAM;

@Singleton
public class DeviceContoller extends InfoController{

    private final String mTag = DeviceContoller.class.getSimpleName();
    private rus.cpuinfo.Model.DeviceInfo mDeviceInfo = new rus.cpuinfo.Model.DeviceInfo();

    @Inject
    public DeviceContoller(@ForDevice HardwareInfoAdapter hardwareInfoAdapter, @ForDevice BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger)
    {
        super(hardwareInfoAdapter, baseInfo,stringFetcher,logger);
    }

    @Override
    protected void onInited() {

        getLogger().d(mTag,"DeviceContoller. OnInited");

        mDeviceInfo.setModel(getInfo(DEVICE_MODEL));
        mDeviceInfo.setBoard(getInfo(DEVICE_BOARD));
        mDeviceInfo.setManufacturer(getInfo(DEVICE_MANUFACTURER));

        String screenDensity = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_DENSITY),getString(R.string.device_dpi));
        mDeviceInfo.setScreenDensity(screenDensity);

        String screenResolution = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_RESOLUTION),getString(R.string.device_pixels));
        mDeviceInfo.setScreenResolution(screenResolution);

        String screenSize = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_SIZE).replaceAll(",","."),getString(R.string.device_inches));
        mDeviceInfo.setScreenSize(screenSize);

        String totalRam = getInfo(DEVICE_TOTAL_RAM).replaceAll(",",".");
        mDeviceInfo.setTotalRam(totalRam);

        String internalStorage = getInfo(DEVICE_INTERNAL_STORAGE).replaceAll(",",".");
        mDeviceInfo.setInternalStorage(internalStorage);

        String availableStorage = getInfo(DEVICE_AVAILABLE_STORAGE).replaceAll(",",".");
        mDeviceInfo.setAvailableStorage(availableStorage);

        getRepeater().setOnRepeatListener(new Repeater.onRepeatListener() {
            @Override
            public void onRepeat() {

                String availableRam = getInfo(DEVICE_AVIALABLE_RAM).replaceAll(",",".");


                mDeviceInfo.setAvailableRam(availableRam);
                updateInformation(mDeviceInfo);
            }
        });

        super.onInited();
    }

}


