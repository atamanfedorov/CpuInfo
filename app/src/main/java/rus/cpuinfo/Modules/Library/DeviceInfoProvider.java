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

package rus.cpuinfo.Modules.Library;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rus.cpuinfo.DeviceInfo.BatteryInfo;
import rus.cpuinfo.DeviceInfo.CpuInfo;
import rus.cpuinfo.DeviceInfo.DevInfo;
import rus.cpuinfo.DeviceInfo.DeviceInfo;
import rus.cpuinfo.DeviceInfo.SensorsInfo;
import rus.cpuinfo.DeviceInfo.SysInfo;
import rus.cpuinfo.Qualifers.ForBattery;
import rus.cpuinfo.Qualifers.ForCpu;
import rus.cpuinfo.Qualifers.ForDevice;
import rus.cpuinfo.Qualifers.ForSensors;
import rus.cpuinfo.Qualifers.ForSystem;

@Module(
        library = true,

        includes = {
        ContextProvider.class,
        }
)
public class DeviceInfoProvider {


    @Provides
    @Singleton
    @ForCpu
    DeviceInfo providesCpuInfo(Context context)
    {
        return new CpuInfo(context);
    }


    @Provides
    @Singleton
    @ForDevice
    DeviceInfo providesDevInfo(Context context)
    {
        return new DevInfo(context);
    }

    @Provides
    @Singleton
    @ForSystem
    DeviceInfo providesSysInfo(Context context)
    {
        return new SysInfo(context);
    }

    @Provides
    @Singleton
    @ForBattery
    DeviceInfo providesBatteryInfo(Context context)
    {
        return new BatteryInfo(context);
    }

    @Provides
    @Singleton
    @ForSensors
    DeviceInfo providesSensorsInfo(Context context)
    {
        return new SensorsInfo(context);
    }
}
