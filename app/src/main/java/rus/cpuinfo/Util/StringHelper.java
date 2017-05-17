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

package rus.cpuinfo.Util;

import android.support.annotation.StringRes;

import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.R;

import static rus.cpuinfo.Model.HardwareInfo.BATERY_HEALTH;
import static rus.cpuinfo.Model.HardwareInfo.BATERY_LEVEL;
import static rus.cpuinfo.Model.HardwareInfo.BATERY_STATUS;
import static rus.cpuinfo.Model.HardwareInfo.BATERY_TEMPERATURE;
import static rus.cpuinfo.Model.HardwareInfo.BATERY_VOLTAGE;
import static rus.cpuinfo.Model.HardwareInfo.BATTERY_POWER_SOURCE;
import static rus.cpuinfo.Model.HardwareInfo.BATTERY_TECHNOLOGY;
import static rus.cpuinfo.Model.HardwareInfo.CPU_CORES;
import static rus.cpuinfo.Model.HardwareInfo.CPU_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_IMPLEM;
import static rus.cpuinfo.Model.HardwareInfo.CPU_MAX_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_MIN_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_PART;
import static rus.cpuinfo.Model.HardwareInfo.CPU_PROCESSOR;
import static rus.cpuinfo.Model.HardwareInfo.CPU_REVISION;
import static rus.cpuinfo.Model.HardwareInfo.CPU_VARIANT;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_AVAILABLE_STORAGE;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_AVIALABLE_RAM;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_BOARD;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_INTERNAL_STORAGE;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_MANUFACTURER;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_MODEL;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_SCREEN_DENSITY;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_SCREEN_RESOLUTION;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_SCREEN_SIZE;
import static rus.cpuinfo.Model.HardwareInfo.DEVICE_TOTAL_RAM;
import static rus.cpuinfo.Model.HardwareInfo.HARDWARE;
import static rus.cpuinfo.Model.HardwareInfo.REVISION;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_ACCELEROMETR;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_AMBIENT_TEMPERATURE;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_GAME_ROTATION_VECTOR;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_GEOMAGNETIC_ROTATION_VECTOR;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_HEART_RATE;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_HUMIDITY;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_LIGHT;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_LINEAR_ACCELERATION;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_MAGNETIC_FIELD;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_ORIENTATION;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_PRESSURE;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_PROXIMITY;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_RELATIVE_HUMIDITY;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_ROTATION_VECTOR;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_SIGNIFICANT_MOTION;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_STEP_COUNTER;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_STEP_DETECTOR;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_TEMPERATURE;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_TYPE_GRAVITY;
import static rus.cpuinfo.Model.HardwareInfo.SENSOR_TYPE_GYROSCOPE;
import static rus.cpuinfo.Model.HardwareInfo.SERIAL;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_ANDROID_VERSION;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_API_LEVEL;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_BOOTLOADER;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_BUILD_ID;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_JAVA_VM;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_KERNEL_ARCHITECTURE;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_KERNEL_VERSION;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_ROOT_ACCESS;
import static rus.cpuinfo.Model.HardwareInfo.SYSTEM_UPTIME;
import static rus.cpuinfo.R.string.battery_status;

public abstract class StringHelper {

    @StringRes
    public static int getStringResId(MainController.Tabs tab) {
        switch (tab) {
            case SYSTEM:
                return R.string.tab_system;
            case CPU:
                return R.string.tab_cpu;
            case DEVICE:
                return R.string.tab_device;
            case BATTERY:
                return R.string.tab_battery;
            case SENSORS:
                return R.string.tab_sensors;
            case ABOUT:
                return R.string.tab_about;
            default:
                return 0;
        }
    }

    @StringRes
    public static int getStringResId(int query) {

        switch (query) {
            //Cpu ----------------
            case CPU_CORES:
                return R.string.cpu_cores;
            case CPU_PROCESSOR:
                return R.string.cpu_processor;
            case CPU_MIN_FREQ:
                return R.string.cpu_min_freq;
            case CPU_MAX_FREQ:
                return R.string.cpu_max_freq;
            case CPU_IMPLEM:
                return R.string.cpu_implementer;
            case CPU_FREQ:
                return R.string.cpu_string;
            case CPU_VARIANT:
                return R.string.cpu_variant;
            case CPU_PART:
                return R.string.cpu_part;
            case CPU_REVISION:
                return R.string.cpu_revision;
            case SERIAL:
                return R.string.cpu_serial;
            case HARDWARE:
                return R.string.cpu_hardware;
            case REVISION:
                return R.string.revision;

            //Device ----------------
            case DEVICE_AVIALABLE_RAM:
                return R.string.device_available_ram;
            case DEVICE_TOTAL_RAM:
                return R.string.device_total_ram;
            case DEVICE_INTERNAL_STORAGE:
                return R.string.device_internal_storage;
            case DEVICE_MANUFACTURER:
                return R.string.device_manufacturer;
            case DEVICE_SCREEN_DENSITY:
                return R.string.device_screen_density;
            case DEVICE_SCREEN_RESOLUTION:
                return R.string.device_screen_resolution;
            case DEVICE_MODEL:
                return R.string.device_model;
            case DEVICE_BOARD:
                return R.string.device_board;
            case DEVICE_SCREEN_SIZE:
                return R.string.device_screen_size;
            case DEVICE_AVAILABLE_STORAGE:
                return R.string.device_available_storage;

            //System ----------------
            case SYSTEM_UPTIME:
                return R.string.system_uptime;
            case SYSTEM_ANDROID_VERSION:
                return R.string.system_android_version;
            case SYSTEM_API_LEVEL:
                return R.string.system_api_level;
            case SYSTEM_BOOTLOADER:
                return R.string.system_bootloader;
            case SYSTEM_BUILD_ID:
                return R.string.system_build_id;
            case SYSTEM_JAVA_VM:
                return R.string.system_java_vm;
            case SYSTEM_KERNEL_ARCHITECTURE:
                return R.string.system_kernel_architecture;
            case SYSTEM_KERNEL_VERSION:
                return R.string.system_kernel_version;
            case SYSTEM_ROOT_ACCESS:
                return R.string.system_root_access;

            //Battery ----------------
            case BATERY_LEVEL:
                return R.string.battery_level;
            case BATERY_TEMPERATURE:
                return R.string.battery_temperature;
            case BATERY_VOLTAGE:
                return R.string.battery_voltage;
            case BATERY_HEALTH:
                return R.string.battery_health;
            case BATERY_STATUS:
                return battery_status;
            case BATTERY_TECHNOLOGY:
                return R.string.battery_technology;
            case BATTERY_POWER_SOURCE:
                return R.string.battery_power_source;

            //Sensors ----------------
            case SENSOR_ACCELEROMETR:
                return R.string.sensor_accelerometr_sensor;
            case SENSOR_AMBIENT_TEMPERATURE:
                return R.string.sensor_ambient_temperature_sensor;
            case SENSOR_GAME_ROTATION_VECTOR:
                return R.string.sensor_game_rotation_vector;
            case SENSOR_SIGNIFICANT_MOTION:
                return R.string.sensor_significant_motion_sensor;
            case SENSOR_STEP_COUNTER:
                return R.string.sensor_step_counter_sensor;
            case SENSOR_ORIENTATION:
                return R.string.sensor_orientation_sensor;
            case SENSOR_TEMPERATURE:
                return R.string.sensor_temperature_sensor;
            case SENSOR_PROXIMITY:
                return R.string.sensor_proximity_sensor;
            case SENSOR_LIGHT:
                return R.string.sensor_light_sensor;
            case SENSOR_PRESSURE:
                return R.string.sensor_pressure_sensor;
            case SENSOR_HUMIDITY:
                return R.string.sensor_humidity_sensor;
            case SENSOR_MAGNETIC_FIELD:
                return R.string.sensor_magnetic_field_sensor;
            case SENSOR_ROTATION_VECTOR:
                return R.string.sensor_rotation_vector_sensor;
            case SENSOR_LINEAR_ACCELERATION:
                return R.string.sensor_linear_acceleration_sensor;
            case SENSOR_TYPE_GRAVITY:
                return R.string.sensor_gravity_sensor;
            case SENSOR_HEART_RATE:
                return R.string.sensor_heart_rate_sensor;
            case SENSOR_STEP_DETECTOR:
                return R.string.sensor_step_dector_sensor;
            case SENSOR_GEOMAGNETIC_ROTATION_VECTOR:
                return R.string.sensor_geomagnetic_rotation_vector;
            case SENSOR_TYPE_GYROSCOPE:
                return R.string.sensor_gyroscope_sensor;
            case SENSOR_RELATIVE_HUMIDITY:
                return R.string.sensor_relative_humidity_sensor;
            //end ----------------

            default:
                return 0;
        }
    }
}
