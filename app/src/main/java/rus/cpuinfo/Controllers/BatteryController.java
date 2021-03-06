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

import javax.inject.Inject;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Injections.Qualifers.ForBattery;
import rus.cpuinfo.Model.BatteryInfo;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

import static rus.cpuinfo.Model.BaseInfo.BATERY_HEALTH;
import static rus.cpuinfo.Model.BaseInfo.BATERY_LEVEL;
import static rus.cpuinfo.Model.BaseInfo.BATERY_STATUS;
import static rus.cpuinfo.Model.BaseInfo.BATERY_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.BATERY_VOLTAGE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_POWER_SOURCE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_TECHNOLOGY;

public class BatteryController extends InfoController {

    private static final String BATTERY_STATUS_UNKNOWN = "1";
    private static final String BATTERY_STATUS_CHARGING = "2";
    private static final String BATTERY_STATUS_DISCHARGING = "3";
    private static final String BBATTERY_STATUS_NOT_CHARGING = "4";
    private static final String BATTERY_STATUS_FULL = "5";

    private static final String BATTERY_HEALTH_UNKNOWN = "1";
    private static final String BATTERY_HEALTH_GOOD = "2";
    private static final String BATTERY_HEALTH_OVERHEAT = "3";
    private static final String BATTERY_HEALTH_DEAD = "4";
    private static final String BATTERY_HEALTH_OVER_VOLTAGE = "5";
    private static final String BATTERY_HEALTH_UNSPECIFIED_FAILURE = "6";
    private static final String BATTERY_HEALTH_COLD = "7";

    /** Power source is an AC charger*/
    private static final String BATTERY_PLUGGED_AC = "1";
    /** Power source is a USB port.*/
    private static final String BATTERY_PLUGGED_USB = "2";
    /** Power source is wireless.*/
    private static final String BATTERY_PLUGGED_WIRELESS = "4";

    private final static String mTag = BatteryController.class.getSimpleName();

    @Inject
    public BatteryController(@ForBattery HardwareInfoAdapter hardwareInfoAdapter, @ForBattery BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger) {
        super(hardwareInfoAdapter, baseInfo, stringFetcher,logger);
    }


    @Override
    protected void onInited() {
        super.onInited();
        getLogger().d(mTag,"BatteryController. OnInited");

        BatteryInfo batteryInfo = new BatteryInfo();
        batteryInfo.setTechnology(getInfo(BATTERY_TECHNOLOGY));
        updateAllInformation(batteryInfo);


        setObservableOnSubscribe( e -> {

            batteryInfo.setLevel(getInfo(BATERY_LEVEL));
            batteryInfo.setTemperature(getInfo(BATERY_TEMPERATURE));
            batteryInfo.setVoltage(getInfo(BATERY_VOLTAGE));
            batteryInfo.setStatus(getBatteryStatus());
            batteryInfo.setHealth(getBatteryHealth());
            batteryInfo.setPowerSource(getBatteryPowerSource());

            e.onNext(batteryInfo);
            e.onComplete();
        });
    }

    @Override
    void updateInformation(@NonNull rus.cpuinfo.Model.BaseInfo baseInfo) {
        super.updateInformation(baseInfo);
        HardwareInfoAdapter hardwareInfoAdapter = getAdapter();
        hardwareInfoAdapter.notifyDataSetChanged();
    }

    @NonNull
    private String getBatteryHealth() {
        String health = getInfo(BATERY_HEALTH);

        switch (health) {
            case BATTERY_HEALTH_GOOD:
                return getString(R.string.battery_health_good);
            case BATTERY_HEALTH_OVERHEAT:
                return getString(R.string.battery_health_overheat);
            case BATTERY_HEALTH_DEAD:
                return getString(R.string.battery_health_dead);
            case BATTERY_HEALTH_OVER_VOLTAGE:
                return getString(R.string.battery_health_over_voltage);
            case BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return getString(R.string.battery_health_unspecified_failure);
            case BATTERY_HEALTH_COLD:
                return getString(R.string.battery_health_cold);
            case BATTERY_HEALTH_UNKNOWN:
            default:
                return getString(R.string.battery_health_unknown);
        }
    }

    @NonNull
    private String getBatteryPowerSource() {
        String powerSource = getInfo(BATTERY_POWER_SOURCE);

        switch (powerSource) {
            case BATTERY_PLUGGED_AC:
                return getString(R.string.battery_plugged_ac);
            case BATTERY_PLUGGED_USB:
                return getString(R.string.battery_plugged_usb);
            case BATTERY_PLUGGED_WIRELESS:
                return getString(R.string.battery_plugged_wireless);
            default:
                return getString(R.string.battery_plugged_default);
        }
    }

    @NonNull
    private String getBatteryStatus() {
        String status = getInfo(BATERY_STATUS);

        switch (status) {
            case BATTERY_STATUS_CHARGING:
                return getString(R.string.battery_status_charging);
            case BATTERY_STATUS_DISCHARGING:
                return getString(R.string.battery_status_discharging);
            case BBATTERY_STATUS_NOT_CHARGING:
                return getString(R.string.battery_status_not_charging);
            case BATTERY_STATUS_FULL:
                return getString(R.string.battery_status_full);
            case BATTERY_STATUS_UNKNOWN:
            default:
                return getString(R.string.battery_status_unknown);
        }
    }
}
