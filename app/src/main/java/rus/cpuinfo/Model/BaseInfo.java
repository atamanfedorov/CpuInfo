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

import android.text.TextUtils;
import android.util.SparseArray;

// FIXME: 1.02.2018

public class BaseInfo implements IBaseInfo<String> {

    final static String DIGITAL_PATTERN = "0[xX][0-9a-fA-F]+|(?:\\d+\\.?)+";

    // instead of the ENUM

    // CPU
    public static final int CPU_PROCESSOR =  1;
    public static final int CPU_CORES =  2;
    public static final int CPU_MIN_FREQ =  3;
    public static final int CPU_MAX_FREQ =  5;
    public static final int CPU_IMPLEM =  6;
    public static final int CPU_VARIANT =  7;
    public static final int CPU_REVISION =  8;
    public static final int CPU_PART =  9;
    public static final int HARDWARE =  10;
    public static final int REVISION =  11;
    public static final int SERIAL =  12;

    //Device
    public static final int DEVICE_AVIALABLE_RAM =  13;
    public static final int DEVICE_TOTAL_RAM =  14;
    public static final int DEVICE_MODEL =  15;
    public static final int DEVICE_BOARD =  16;
    public static final int DEVICE_MANUFACTURER =  17;
    public static final int DEVICE_SCREEN_SIZE =  18;
    public static final int DEVICE_SCREEN_RESOLUTION =  19;
    public static final int DEVICE_SCREEN_DENSITY =  20;
    public static final int DEVICE_INTERNAL_STORAGE =  21;
    public static final int DEVICE_AVAILABLE_STORAGE =  22;

    //System
    public static final int SYSTEM_UPTIME =  23;
    public static final int SYSTEM_ANDROID_VERSION =  24;
    public static final int SYSTEM_API_LEVEL =  25;
    public static final int SYSTEM_BOOTLOADER =  26;
    public static final int SYSTEM_BUILD_ID =  27;
    public static final int SYSTEM_JAVA_VM =  28;
    public static final int SYSTEM_KERNEL_ARCHITECTURE =  29;
    public static final int SYSTEM_KERNEL_VERSION =  30;
    public static final int SYSTEM_ROOT_ACCESS =  31;

    //Battery
    public static final int BATERY_TEMPERATURE =  32;
    public static final int BATERY_VOLTAGE =  33;
    public static final int BATERY_LEVEL =  34;
    public static final int BATERY_HEALTH =  35;
    public static final int BATERY_STATUS =  36;
    public static final int BATTERY_POWER_SOURCE =  37;
    public static final int BATTERY_TECHNOLOGY =  38;

    //Sensor
    public static final int SENSOR_ACCELEROMETR =  39;
    public static final int SENSOR_AMBIENT_TEMPERATURE =  40;
    public static final int SENSOR_GAME_ROTATION_VECTOR =  41;
    public static final int SENSOR_ORIENTATION =  42;
    public static final int SENSOR_TEMPERATURE =  43;
    public static final int SENSOR_GEOMAGNETIC_ROTATION_VECTOR =  44;
    public static final int SENSOR_TYPE_GRAVITY =  45;
    public static final int SENSOR_TYPE_GYROSCOPE =  46;
    public static final int SENSOR_HEART_RATE =  47;
    public static final int SENSOR_LIGHT =  48;
    public static final int SENSOR_LINEAR_ACCELERATION =  49;
    public static final int SENSOR_MAGNETIC_FIELD =  50;
    public static final int SENSOR_PRESSURE =  51;
    public static final int SENSOR_PROXIMITY =  52;
    public static final int SENSOR_HUMIDITY =  53;
    public static final int SENSOR_RELATIVE_HUMIDITY =  54;
    public static final int SENSOR_ROTATION_VECTOR =  55;
    public static final int SENSOR_SIGNIFICANT_MOTION =  56;
    public static final int SENSOR_STEP_COUNTER =  57;
    public static final int SENSOR_STEP_DETECTOR =  58;


    public static final int CPU_FREQ = 64;

    private SparseArray<String> mInfo = new SparseArray<>();

    @Override
    public String getInfoByQuery(Integer query) {
        return mInfo.get(query);
    }

    void putInfo(Integer query,String info)
    {
        if (!TextUtils.isEmpty(info))
             mInfo.put(query,info);
    }

    @Override
    public String toString() {
        return mInfo.toString();
    }

    @Override
    public int hashCode() {
        return mInfo.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);}

    boolean isDigital(String data)
    {
        return !TextUtils.isEmpty(data) && data.matches(DIGITAL_PATTERN);
    }
}
