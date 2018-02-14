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
import android.text.TextUtils;

import javax.inject.Inject;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Model.SensorInfo;
import rus.cpuinfo.Injections.Qualifers.ForSensors;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

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

public class SensorsController extends InfoController{

    private final static String mTag = SensorsController.class.getSimpleName();

    @Inject
    public SensorsController(@ForSensors HardwareInfoAdapter hardwareInfoAdapter, @ForSensors BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger)
    {
        super(hardwareInfoAdapter, baseInfo, stringFetcher,logger);
    }

    @Override
    @NonNull
    protected String getInfo(int q) {
        final String info = super.getInfo(q);
        return TextUtils.isEmpty(info) ? getString(R.string.sensor_not_supported) : info;
    }

    @Override
    protected void onInited() {
        super.onInited();

        getLogger().d(mTag,"SensorsController. OnInited");

        SensorInfo sensorInfo = new SensorInfo();
        sensorInfo.setAccelerometr(getInfo(SENSOR_ACCELEROMETR));
        sensorInfo.setMagneticField(getInfo(SENSOR_MAGNETIC_FIELD));
        sensorInfo.setTemperature(getInfo(SENSOR_TEMPERATURE));
        sensorInfo.setHumidity(getInfo(SENSOR_RELATIVE_HUMIDITY));
        sensorInfo.setLight(getInfo(SENSOR_LIGHT));
        sensorInfo.setOrientation(getInfo(SENSOR_ORIENTATION));
        sensorInfo.setProximity(getInfo(SENSOR_PROXIMITY));
        sensorInfo.setPressure(getInfo(SENSOR_PRESSURE));
        sensorInfo.setHumidity(getInfo(SENSOR_HUMIDITY));
        sensorInfo.setGravity(getInfo(SENSOR_TYPE_GRAVITY));
        sensorInfo.setRotationVector(getInfo(SENSOR_ROTATION_VECTOR));
        sensorInfo.setLinearAccelation(getInfo(SENSOR_LINEAR_ACCELERATION));

        sensorInfo.setAmbientTemperature(getInfo(SENSOR_AMBIENT_TEMPERATURE));
        sensorInfo.setGameRotationVector(getInfo(SENSOR_GAME_ROTATION_VECTOR));
        sensorInfo.setSignificantMotion(getInfo(SENSOR_SIGNIFICANT_MOTION));
        sensorInfo.setHearRate(getInfo(SENSOR_HEART_RATE));
        sensorInfo.setStepCounter(getInfo(SENSOR_STEP_COUNTER));
        sensorInfo.setStepDetector(getInfo(SENSOR_STEP_DETECTOR));

        sensorInfo.setGyroscope(getInfo(SENSOR_TYPE_GYROSCOPE));
        sensorInfo.setGeomagneticRotationVector(getInfo(SENSOR_GEOMAGNETIC_ROTATION_VECTOR));

        updateAllInformation(sensorInfo);
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
    }

}
