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

package rus.cpuinfo.DeviceInfo;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rus.cpuinfo.Util.RootUtil;

import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_ANDROID_VERSION;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_API_LEVEL;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_BOOTLOADER;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_BUILD_ID;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_JAVA_VM;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_KERNEL_ARCHITECTURE;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_KERNEL_VERSION;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_ROOT_ACCESS;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_UPTIME;

public class SysInfo extends DeviceInfo {

    public SysInfo(Context context)
    {
        super(context);
    }


    @Override
    @NonNull
    public String getInfo(int query) {
        switch (query) {
            case SYSTEM_UPTIME:
                return getUpTime();
            case SYSTEM_ANDROID_VERSION:
                return getAndroidVersion();
            case SYSTEM_API_LEVEL:
                return getApiLevel();
            case SYSTEM_BOOTLOADER:
                return getBootLoader();
            case SYSTEM_BUILD_ID:
                return getBuildId();
            case SYSTEM_KERNEL_ARCHITECTURE:
                return getKernelArchitecture();
            case SYSTEM_KERNEL_VERSION:
                return getKernelVersion();
            case SYSTEM_JAVA_VM:
                return getJavaVM();
            case SYSTEM_ROOT_ACCESS:
                return isDeviceRooted();
            default:
                throw new IllegalArgumentException("Query must be with \"SYSTEM.\" prefix");
        }
    }
    @NonNull
    private String getUpTime() {

        long m = SystemClock.elapsedRealtime();

        final long hr = TimeUnit.MILLISECONDS.toHours(m);
        final long min = TimeUnit.MILLISECONDS.toMinutes(m - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(m - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));

        return String.format(Locale.getDefault(),"%02d:%02d:%02d", hr, min, sec);
    }

    @NonNull
    private String getAndroidVersion()
    {

        return Build.VERSION.RELEASE != null ? Build.VERSION.RELEASE  : StringUtils.EMPTY;
    }

    @NonNull
    private String getApiLevel()
    {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    @NonNull
    private String getBootLoader()
    {
        return Build.BOOTLOADER != null ? Build.BOOTLOADER  : StringUtils.EMPTY;
    }

    @NonNull
    private String getBuildId()
    {
        return Build.DISPLAY != null ? Build.DISPLAY  : StringUtils.EMPTY;
    }

    @NonNull
    private String getKernelArchitecture()
    {
        String arch = System.getProperty("os.arch");
        return arch != null ? arch : StringUtils.EMPTY;
    }

    @NonNull
    private String getKernelVersion()
    {
        String kernelVers = System.getProperty("os.version");
        return kernelVers != null ? kernelVers : StringUtils.EMPTY;
    }

    @NonNull
    private String getJavaVM()
    {
        String vmName = System.getProperty("java.vm.name");
        String vmVersion = System.getProperty("java.vm.version");

        return vmName != null && vmVersion != null ? vmName + StringUtils.SPACE + vmVersion : StringUtils.EMPTY;

    }

    @NonNull
    private String isDeviceRooted()
    {
        return RootUtil.isDeviceRooted() ? "yes" : "no";
    }
}
