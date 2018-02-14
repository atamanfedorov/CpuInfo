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

package rus.cpuinfo.Injections.Modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.AndroidDepedentModel.BatteryInfo;
import rus.cpuinfo.AndroidDepedentModel.CpuInfo;
import rus.cpuinfo.AndroidDepedentModel.DevInfo;
import rus.cpuinfo.AndroidDepedentModel.SensorsInfo;
import rus.cpuinfo.AndroidDepedentModel.SysInfo;
import rus.cpuinfo.Injections.Qualifers.ForBattery;
import rus.cpuinfo.Injections.Qualifers.ForCpu;
import rus.cpuinfo.Injections.Qualifers.ForDevice;
import rus.cpuinfo.Injections.Qualifers.ForSensors;
import rus.cpuinfo.Injections.Qualifers.ForSystem;

@Module(

        includes = {
        ContextProvider.class,
        }
)
public class DeviceInfoProvider {


    @Provides
    @Singleton
    @ForCpu
    BaseInfo providesCpuInfo(Context context)
    {
        return new CpuInfo(context);
    }


    @Provides
    @Singleton
    @ForDevice
    BaseInfo providesDevInfo(Context context)
    {
        return new DevInfo(context);
    }

    @Provides
    @Singleton
    @ForSystem
    BaseInfo providesSysInfo(Context context)
    {
        return new SysInfo(context);
    }

    @Provides
    @Singleton
    @ForBattery
    BaseInfo providesBatteryInfo(Context context)
    {
        return new BatteryInfo(context);
    }

    @Provides
    @Singleton
    @ForSensors
    BaseInfo providesSensorsInfo(Context context)
    {
        return new SensorsInfo(context);
    }
}
