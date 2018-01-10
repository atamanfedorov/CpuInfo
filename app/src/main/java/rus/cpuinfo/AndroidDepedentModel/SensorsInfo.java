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

package rus.cpuinfo.AndroidDepedentModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import static rus.cpuinfo.Model.BaseInfo.SENSOR_ACCELEROMETR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_AMBIENT_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_GAME_ROTATION_VECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_GEOMAGNETIC_ROTATION_VECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_HEART_RATE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_HUMIDITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_LIGHT;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_LINEAR_ACCELERATION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_MAGNETIC_FIELD;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_ORIENTATION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_PRESSURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_PROXIMITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_RELATIVE_HUMIDITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_ROTATION_VECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_SIGNIFICANT_MOTION;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_STEP_COUNTER;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_STEP_DETECTOR;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TYPE_GRAVITY;
import static rus.cpuinfo.Model.BaseInfo.SENSOR_TYPE_GYROSCOPE;

public class SensorsInfo extends BaseInfo {

    private SensorManager mSensorManager;

    public SensorsInfo(Context context)
    {
        super(context);
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    @SuppressLint("NewApi")
    @NonNull
    public String getInfo(int query) {

        int type;

        switch (query)
        {
            case SENSOR_ACCELEROMETR:
                type = Sensor.TYPE_ACCELEROMETER;
                break;
            case SENSOR_AMBIENT_TEMPERATURE:
                type = Sensor.TYPE_AMBIENT_TEMPERATURE;
                break;
            case SENSOR_LINEAR_ACCELERATION:
                type = Sensor.TYPE_LINEAR_ACCELERATION;
                break;
            case SENSOR_LIGHT:
                type = Sensor.TYPE_LIGHT;
                break;
            case SENSOR_MAGNETIC_FIELD:
                type = Sensor.TYPE_MAGNETIC_FIELD;
                break;
            case SENSOR_ROTATION_VECTOR:
                type = Sensor.TYPE_ROTATION_VECTOR;
                break;
            case SENSOR_TYPE_GRAVITY:
                type = Sensor.TYPE_GRAVITY;
                break;
            case SENSOR_TYPE_GYROSCOPE:
                type = Sensor.TYPE_GYROSCOPE;
                break;
            case SENSOR_PROXIMITY:
                type = Sensor.TYPE_PROXIMITY;
                break;
            case SENSOR_PRESSURE:
                type = Sensor.TYPE_PRESSURE;
                break;
            case SENSOR_RELATIVE_HUMIDITY:
                type = Sensor.TYPE_RELATIVE_HUMIDITY;
                break;
            case SENSOR_TEMPERATURE:
                type = Sensor.TYPE_AMBIENT_TEMPERATURE;
                break;
            case SENSOR_HUMIDITY:
                type = Sensor.TYPE_RELATIVE_HUMIDITY;
                break;
            case SENSOR_ORIENTATION:
                type = Sensor.TYPE_ORIENTATION;
                break;
            case SENSOR_HEART_RATE:
                type = Sensor.TYPE_HEART_RATE;
                break;
            case SENSOR_SIGNIFICANT_MOTION:
                type = Sensor.TYPE_SIGNIFICANT_MOTION;
                break;
            case SENSOR_GEOMAGNETIC_ROTATION_VECTOR:
                type = Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR;
                break;
            case SENSOR_STEP_COUNTER:
                type = Sensor.TYPE_STEP_COUNTER;
                break;
            case SENSOR_STEP_DETECTOR:
                type = Sensor.TYPE_STEP_DETECTOR;
                break;
            case SENSOR_GAME_ROTATION_VECTOR:
                type = Sensor.TYPE_GAME_ROTATION_VECTOR;
                break;
            default:
                throw new IllegalArgumentException("Query must be with \"SENSOR.\" prefix");
        }
        return getSensorName(type);
    }

    @NonNull
    private String getSensorName(final int type)
    {
        Sensor sensor = mSensorManager.getDefaultSensor(type);
        return sensor == null ? StringUtils.EMPTY : sensor.getName();
    }
}
