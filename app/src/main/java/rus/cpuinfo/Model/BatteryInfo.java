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

public class BatteryInfo extends HardwareInfo{

    public void setLevel(@NonNull String level) {
        if (isDigital(level))
            putInfo(BATERY_LEVEL,level.concat(" %"));
    }

    public void setTemperature(@NonNull String temperature) {
        if (isDigital(temperature))
            putInfo(BATERY_TEMPERATURE,temperature.concat(" °C"));
    }

    public void setVoltage(@NonNull String voltage) {
        if (isDigital(voltage))
            putInfo(BATERY_VOLTAGE,voltage.concat(" mV"));
    }
    public void setHealth(@NonNull String health) {
             putInfo(BATERY_HEALTH,health);
    }

    public void setStatus(@NonNull String status) {
            putInfo(BATERY_STATUS,status);
    }

    public void setTechnology(@NonNull String technology) {
           putInfo(BATTERY_TECHNOLOGY,technology);
    }

    public void setPowerSource(@NonNull String powerSource) {
         putInfo(BATTERY_POWER_SOURCE,powerSource);
    }
}
