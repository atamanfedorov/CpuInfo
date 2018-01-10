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

import javax.inject.Inject;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Model.SystemInfo;
import rus.cpuinfo.Qualifers.ForSystem;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;
import rus.cpuinfo.Util.Repeater;

import static rus.cpuinfo.Model.BaseInfo.SYSTEM_ANDROID_VERSION;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_API_LEVEL;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_BOOTLOADER;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_BUILD_ID;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_JAVA_VM;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_KERNEL_ARCHITECTURE;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_KERNEL_VERSION;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_ROOT_ACCESS;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_UPTIME;

public class SystemController extends InfoController {

  private SystemInfo mSystemInfo = new SystemInfo();
  private final static String mTag = SystemController.class.getSimpleName();

    @Inject
    public SystemController(@ForSystem HardwareInfoAdapter hardwareInfoAdapter, @ForSystem BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger) {
        super(hardwareInfoAdapter, baseInfo, stringFetcher,logger);
    }

    @Override
    protected void onInited() {
        super.onInited();

        getLogger().d(mTag,"SystemController. OnInited");

        mSystemInfo.setBuildId(getInfo(SYSTEM_BUILD_ID));
        mSystemInfo.setKernelArchitecture(getInfo(SYSTEM_KERNEL_ARCHITECTURE));
        mSystemInfo.setRootAccess(isDeviceRooted());
        mSystemInfo.setJavaVm(getInfo(SYSTEM_JAVA_VM));

        String bootloader = getInfo(SYSTEM_BOOTLOADER);
        mSystemInfo.setBootloader(bootloader.equals("unknown") ? getString(R.string.undefined) : bootloader);

        // Just in case
        String apiLevel = getInfo(SYSTEM_API_LEVEL).replaceAll("[a-zA-Z\\s]","");
        mSystemInfo.setApiLevel(apiLevel);

        String androidVersion = getInfo(SYSTEM_ANDROID_VERSION).replaceAll("[a-zA-Z\\s\\+]","");
        mSystemInfo.setAndroidVersion(androidVersion);

        String kernelVersion = getInfo(SYSTEM_KERNEL_VERSION).replaceAll("[a-zA-Z\\s\\+]","");
        mSystemInfo.setKernelVersion(kernelVersion);

        getRepeater().setOnRepeatListener(new Repeater.onRepeatListener() {
            @Override
            public void onRepeat() {

                String systemTime = getInfo(SYSTEM_UPTIME).replaceAll("[a-zA-Z\\s]","");
                mSystemInfo.setUpTime(systemTime);

                updateInformation(mSystemInfo);
            }
        });
    }

    @NonNull
    private String isDeviceRooted()
    {
        String rootAccess = getInfo(SYSTEM_ROOT_ACCESS);
        return rootAccess.equals("yes") ? getString(R.string.system_yes) : getString(R.string.system_no);
    }

}
