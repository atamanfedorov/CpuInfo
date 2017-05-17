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


// FIXME: 14.03.2017

public class SensorInfo extends HardwareInfo {

    public void setAccelerometr(@NonNull String accelerometr) {
        putInfo(SENSOR_ACCELEROMETR,accelerometr);
    }

    public void setAmbientTemperature(@NonNull String ambientTemperature) {
        putInfo(SENSOR_AMBIENT_TEMPERATURE,ambientTemperature);

    }

    public void setGameRotationVector(@NonNull String gameRotationVector) {
        putInfo(SENSOR_ROTATION_VECTOR,gameRotationVector);

    }

    public void setSignificantMotion(@NonNull String significantMotion) {
        putInfo(SENSOR_SIGNIFICANT_MOTION,significantMotion);

    }

    public void setLinearAccelation(@NonNull String linearAccelation) {
        putInfo(SENSOR_LINEAR_ACCELERATION,linearAccelation);
    }

    public void setGravity(@NonNull String gravity) {
        putInfo(SENSOR_TYPE_GRAVITY,gravity);

    }

    public void setRotationVector(@NonNull String rotationVector) {
        putInfo(SENSOR_ROTATION_VECTOR,rotationVector);

    }

    public void setHumidity(@NonNull String humidity) {
        putInfo(SENSOR_HUMIDITY,humidity);
    }

    public void setStepDetector(@NonNull String stepDetector) {
        putInfo(SENSOR_STEP_DETECTOR,stepDetector);
    }

    public void setHearRate(@NonNull String heartRate) {
        putInfo(SENSOR_HEART_RATE,heartRate);
    }

    public void setLight(@NonNull String light) {
        putInfo(SENSOR_LIGHT,light);
    }

    public void setPressure(@NonNull String pressure) {
        putInfo(SENSOR_PRESSURE,pressure);
    }

    public void setProximity(@NonNull String proximity) {
        putInfo(SENSOR_PROXIMITY,proximity);
    }

    public void setMagneticField(@NonNull String magneticField) {
        putInfo(SENSOR_MAGNETIC_FIELD,magneticField);
    }

    public void setOrientation(@NonNull String orientation) {
        putInfo(SENSOR_ORIENTATION,orientation);
    }

    public void setTemperature(@NonNull String temperature) {
        putInfo(SENSOR_TEMPERATURE,temperature);
    }

    public void setStepCounter(@NonNull String stepCounter) {
        putInfo(SENSOR_STEP_COUNTER,stepCounter);
    }

    public void setGeomagneticRotationVector(@NonNull String geomagneticRotationVector) {
        putInfo(SENSOR_GEOMAGNETIC_ROTATION_VECTOR,geomagneticRotationVector);

    }

    public void setRelativeHumidity(@NonNull String relativeHumidity) {
        putInfo(SENSOR_RELATIVE_HUMIDITY,relativeHumidity);
    }

    public void setGyroscope(@NonNull String gyroscope) {
        putInfo(SENSOR_TYPE_GYROSCOPE,gyroscope);
    }
}
