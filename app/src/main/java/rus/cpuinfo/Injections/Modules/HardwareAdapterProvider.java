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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.Model.BaseInfo;
import rus.cpuinfo.Injections.Qualifers.ForBattery;
import rus.cpuinfo.Injections.Qualifers.ForCpu;
import rus.cpuinfo.Injections.Qualifers.ForDevice;
import rus.cpuinfo.Injections.Qualifers.ForSensors;
import rus.cpuinfo.Injections.Qualifers.ForSystem;

import static rus.cpuinfo.Model.BaseInfo.BATERY_HEALTH;
import static rus.cpuinfo.Model.BaseInfo.BATERY_LEVEL;
import static rus.cpuinfo.Model.BaseInfo.BATERY_STATUS;
import static rus.cpuinfo.Model.BaseInfo.BATERY_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.BATERY_VOLTAGE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_POWER_SOURCE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_TECHNOLOGY;
import static rus.cpuinfo.Model.BaseInfo.CPU_CORES;
import static rus.cpuinfo.Model.BaseInfo.CPU_IMPLEM;
import static rus.cpuinfo.Model.BaseInfo.CPU_MAX_FREQ;
import static rus.cpuinfo.Model.BaseInfo.CPU_MIN_FREQ;
import static rus.cpuinfo.Model.BaseInfo.CPU_PART;
import static rus.cpuinfo.Model.BaseInfo.CPU_PROCESSOR;
import static rus.cpuinfo.Model.BaseInfo.CPU_REVISION;
import static rus.cpuinfo.Model.BaseInfo.CPU_VARIANT;
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
import static rus.cpuinfo.Model.BaseInfo.HARDWARE;
import static rus.cpuinfo.Model.BaseInfo.REVISION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_ACCELEROMETR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_AMBIENT_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_GEOMAGNETIC_ROTATION_VECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_HEART_RATE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_HUMIDITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_LIGHT;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_LINEAR_ACCELERATION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_MAGNETIC_FIELD;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_ORIENTATION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_PRESSURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_PROXIMITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_ROTATION_VECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_SIGNIFICANT_MOTION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_STEP_COUNTER;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_STEP_DETECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TYPE_GRAVITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TYPE_GYROSCOPE;
import static rus.cpuinfo.Model.BaseInfo.SERIAL;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_ANDROID_VERSION;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_API_LEVEL;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_BOOTLOADER;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_BUILD_ID;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_JAVA_VM;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_KERNEL_ARCHITECTURE;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_KERNEL_VERSION;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_ROOT_ACCESS;
import static rus.cpuinfo.Model.BaseInfo.SYSTEM_UPTIME;

@Module
public class HardwareAdapterProvider {

    @Provides
    @ForCpu
    HardwareInfoAdapter providesCpuAdapter()
    {
        // According to a position on the RecyclerView

       List<Integer> list = createList(
               CPU_CORES,
               CPU_PROCESSOR,
               CPU_MIN_FREQ,
               CPU_MAX_FREQ,
               CPU_IMPLEM,
               CPU_VARIANT,
               CPU_PART,
               CPU_REVISION,
               HARDWARE,
               REVISION,
               SERIAL);

        list.addAll(list.indexOf(CPU_MAX_FREQ)+1,createCpuFreq());

        return new HardwareInfoAdapter(list);
    }

    @Provides
    @ForDevice
    HardwareInfoAdapter providesDeviceAdapter()
    {

        return createAdapter(
                DEVICE_MODEL,
                DEVICE_MANUFACTURER,
                DEVICE_BOARD,
                DEVICE_SCREEN_SIZE,
                DEVICE_SCREEN_RESOLUTION,
                DEVICE_SCREEN_DENSITY,
                DEVICE_AVIALABLE_RAM,
                DEVICE_TOTAL_RAM,
                DEVICE_AVAILABLE_STORAGE,
                DEVICE_INTERNAL_STORAGE);
    }


    @Provides
    @ForSystem
    HardwareInfoAdapter providesSystemAdapter()
    {

        return createAdapter(
                SYSTEM_UPTIME,
                SYSTEM_ANDROID_VERSION,
                SYSTEM_API_LEVEL,
                SYSTEM_BOOTLOADER,
                SYSTEM_BUILD_ID,
                SYSTEM_JAVA_VM,
                SYSTEM_KERNEL_ARCHITECTURE,
                SYSTEM_KERNEL_VERSION,
                SYSTEM_ROOT_ACCESS);
    }

    @Provides
    @ForBattery
    HardwareInfoAdapter providesBatteryAdapter()
    {

        return createAdapter(
                BATERY_LEVEL,
                BATERY_TEMPERATURE,
                BATERY_VOLTAGE,
                BATTERY_POWER_SOURCE,
                BATERY_STATUS,
                BATTERY_TECHNOLOGY,
                BATERY_HEALTH);
    }

    @Provides
    @ForSensors
    HardwareInfoAdapter providesSensorAdapter()
    {

        return createAdapter(
                SENSOR_ACCELEROMETR,
                SENSOR_MAGNETIC_FIELD,
                SENSOR_ORIENTATION,
                SENSOR_TEMPERATURE,
                SENSOR_PROXIMITY,
                SENSOR_LIGHT,
                SENSOR_PRESSURE,
                SENSOR_HUMIDITY,
                SENSOR_TYPE_GRAVITY,
                SENSOR_ROTATION_VECTOR,
                SENSOR_LINEAR_ACCELERATION,
                SENSOR_SIGNIFICANT_MOTION,
                //QueryConstant.SENSOR_GAME_ROTATION_VECTOR,
                SENSOR_AMBIENT_TEMPERATURE,
                SENSOR_HEART_RATE,
                SENSOR_STEP_DETECTOR,
                SENSOR_STEP_COUNTER,
                SENSOR_GEOMAGNETIC_ROTATION_VECTOR,
                SENSOR_TYPE_GYROSCOPE);
    }

    private HardwareInfoAdapter createAdapter(Integer ...q)
    {
        return new HardwareInfoAdapter(createList(q));
    }

    private List<Integer> createCpuFreq()
    {
        return Collections.nCopies(Runtime.getRuntime().availableProcessors(), BaseInfo.CPU_FREQ);
    }

    private List<Integer> createList(Integer ...a)
    {
        return new ArrayList<>(Arrays.asList(a));
    }
}
