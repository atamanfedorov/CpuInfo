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
import rus.cpuinfo.Injections.Qualifers.ForDevice;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
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

    @Inject
    public DeviceContoller(@ForDevice HardwareInfoAdapter hardwareInfoAdapter, @ForDevice BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger)
    {
        super(hardwareInfoAdapter, baseInfo,stringFetcher,logger);
    }

    @Override
    protected void onInited() {
        super.onInited();

        getLogger().d(mTag,"DeviceContoller. OnInited");
        rus.cpuinfo.Model.DeviceInfo deviceInfo = new rus.cpuinfo.Model.DeviceInfo();

        deviceInfo.setModel(getInfo(DEVICE_MODEL));
        deviceInfo.setBoard(getInfo(DEVICE_BOARD));
        deviceInfo.setManufacturer(getInfo(DEVICE_MANUFACTURER));

        String screenDensity = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_DENSITY),getString(R.string.device_dpi));
        deviceInfo.setScreenDensity(screenDensity);

        String screenResolution = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_RESOLUTION),getString(R.string.device_pixels));
        deviceInfo.setScreenResolution(screenResolution);

        String screenSize = String.format(Locale.getDefault(),"%s %s", getInfo(DEVICE_SCREEN_SIZE).replaceAll(",","."),getString(R.string.device_inches));
        deviceInfo.setScreenSize(screenSize);

        String totalRam = getInfo(DEVICE_TOTAL_RAM).replaceAll(",",".");
        deviceInfo.setTotalRam(totalRam);

        String internalStorage = getInfo(DEVICE_INTERNAL_STORAGE).replaceAll(",",".");
        deviceInfo.setInternalStorage(internalStorage);

        String availableStorage = getInfo(DEVICE_AVAILABLE_STORAGE).replaceAll(",",".");
        deviceInfo.setAvailableStorage(availableStorage);

        updateAllInformation(deviceInfo);

        setObservableOnSubscribe( e -> {

            String availableRam = getInfo(DEVICE_AVIALABLE_RAM).replaceAll(",",".");
            deviceInfo.setAvailableRam(availableRam);

            e.onNext(deviceInfo);
            e.onComplete();
        });


    }


    @Override
    void updateInformation(@NonNull rus.cpuinfo.Model.BaseInfo baseInfo) {
        super.updateInformation(baseInfo);

        HardwareInfoAdapter hardwareInfoAdapter = getAdapter();
        hardwareInfoAdapter.notifyItemChanged(hardwareInfoAdapter.getPosition(DEVICE_AVIALABLE_RAM));
    }

}


