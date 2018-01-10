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
import rus.cpuinfo.Qualifers.ForSensors;
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

    private SensorInfo mSensorsInfo = new SensorInfo();
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

        mSensorsInfo.setAccelerometr(getInfo(SENSOR_ACCELEROMETR));
        mSensorsInfo.setMagneticField(getInfo(SENSOR_MAGNETIC_FIELD));
        mSensorsInfo.setTemperature(getInfo(SENSOR_TEMPERATURE));
        mSensorsInfo.setHumidity(getInfo(SENSOR_RELATIVE_HUMIDITY));
        mSensorsInfo.setLight(getInfo(SENSOR_LIGHT));
        mSensorsInfo.setOrientation(getInfo(SENSOR_ORIENTATION));
        mSensorsInfo.setProximity(getInfo(SENSOR_PROXIMITY));
        mSensorsInfo.setPressure(getInfo(SENSOR_PRESSURE));
        mSensorsInfo.setHumidity(getInfo(SENSOR_HUMIDITY));
        mSensorsInfo.setGravity(getInfo(SENSOR_TYPE_GRAVITY));
        mSensorsInfo.setRotationVector(getInfo(SENSOR_ROTATION_VECTOR));
        mSensorsInfo.setLinearAccelation(getInfo(SENSOR_LINEAR_ACCELERATION));

        mSensorsInfo.setAmbientTemperature(getInfo(SENSOR_AMBIENT_TEMPERATURE));
        mSensorsInfo.setGameRotationVector(getInfo(SENSOR_GAME_ROTATION_VECTOR));
        mSensorsInfo.setSignificantMotion(getInfo(SENSOR_SIGNIFICANT_MOTION));
        mSensorsInfo.setHearRate(getInfo(SENSOR_HEART_RATE));
        mSensorsInfo.setStepCounter(getInfo(SENSOR_STEP_COUNTER));
        mSensorsInfo.setStepDetector(getInfo(SENSOR_STEP_DETECTOR));

        mSensorsInfo.setGyroscope(getInfo(SENSOR_TYPE_GYROSCOPE));
        mSensorsInfo.setGeomagneticRotationVector(getInfo(SENSOR_GEOMAGNETIC_ROTATION_VECTOR));

        updateInformation(mSensorsInfo);
    }
    @Override
    protected void onSuspended() {
        super.onSuspended();
    }
    
}
