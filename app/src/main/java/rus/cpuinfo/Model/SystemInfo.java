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

package rus.cpuinfo.Model;

import android.support.annotation.NonNull;
import android.text.TextUtils;


public class SystemInfo extends BaseInfo {

    private static final String UPTIME_PATTERN = "^\\d+:[0-5]?\\d:[0-5]?\\d$";
    private static final String VERSION_PATTERN = "^(?:\\d+\\.?\\+?){1,5}(?<!\\.)$";

    public void setUpTime(@NonNull String upTime) {
       if (!TextUtils.isEmpty(upTime) && upTime.matches(UPTIME_PATTERN))
           putInfo(SYSTEM_UPTIME,upTime);
    }

    public void setAndroidVersion(@NonNull String androidVersion) {
        if (!TextUtils.isEmpty(androidVersion) && androidVersion.matches(VERSION_PATTERN))
            putInfo(SYSTEM_ANDROID_VERSION,androidVersion);
    }

    public void setApiLevel(@NonNull String apiLevel) {
        if (!TextUtils.isEmpty(apiLevel) && apiLevel.matches(DIGITAL_PATTERN))
            putInfo(SYSTEM_API_LEVEL,apiLevel);
    }

    public void setKernelVersion(@NonNull String kernelVersion) {
        if (!TextUtils.isEmpty(kernelVersion) && kernelVersion.matches(VERSION_PATTERN))
            putInfo(SYSTEM_KERNEL_VERSION,kernelVersion);
    }

    public void setRootAccess(@NonNull String rootAccess) {
        putInfo(SYSTEM_ROOT_ACCESS,rootAccess);
    }

    public void setBootloader(@NonNull String bootloader) {
        putInfo(SYSTEM_BOOTLOADER,bootloader);
    }

    public void setBuildId(@NonNull String buildId) {
        putInfo(SYSTEM_BUILD_ID,buildId);
    }

    public void setJavaVm(@NonNull String javaVm) {
        putInfo(SYSTEM_JAVA_VM,javaVm);
    }

    public void setKernelArchitecture(@NonNull String kernelArchitecture) {
        putInfo(SYSTEM_KERNEL_ARCHITECTURE,kernelArchitecture);
    }
}
