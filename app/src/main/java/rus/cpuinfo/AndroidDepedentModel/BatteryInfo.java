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

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import static rus.cpuinfo.Model.BaseInfo.BATERY_HEALTH;
import static rus.cpuinfo.Model.BaseInfo.BATERY_LEVEL;
import static rus.cpuinfo.Model.BaseInfo.BATERY_STATUS;
import static rus.cpuinfo.Model.BaseInfo.BATERY_TEMPERATURE;
import static rus.cpuinfo.Model.BaseInfo.BATERY_VOLTAGE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_POWER_SOURCE;
import static rus.cpuinfo.Model.BaseInfo.BATTERY_TECHNOLOGY;

public class BatteryInfo extends BaseInfo {

    public BatteryInfo(Context context)
    {
        super(context);
    }

    @Override
    @NonNull
    public String getInfo(int query) {

        switch (query) {
            case BATERY_LEVEL:
                return getBatteryLevel();
            case BATERY_TEMPERATURE:
                return getBatteryTemparature();
            case BATERY_VOLTAGE:
                return getBatteryVoltage();
            case BATERY_STATUS:
                return getBatteryStatus();
            case BATERY_HEALTH:
                return getBatteryHelth();
            case BATTERY_TECHNOLOGY:
                return getBatteryTechnology();
            case BATTERY_POWER_SOURCE:
                return getBatteryPowerSource();
            default:
                throw new IllegalArgumentException("Query must be with \"BATERY.\" prefix");
        }
    }

    @NonNull
    private String getBatteryLevel()
    {
        final Integer level = getIntExtra(BatteryManager.EXTRA_LEVEL);
        final Integer scale = getIntExtra(BatteryManager.EXTRA_SCALE);

        return (level | scale) == -1 ? StringUtils.EMPTY : String.valueOf(((float)level / (float)scale) * 100.0f);
    }


    @NonNull
    private String getBatteryVoltage()
    {
        return getBatteryInfo(BatteryManager.EXTRA_VOLTAGE);
    }

    @NonNull
    private String getBatteryStatus()
    {
        return getBatteryInfo(BatteryManager.EXTRA_STATUS);

    }

    @NonNull
    private String getBatteryHelth()
    {

        return getBatteryInfo(BatteryManager.EXTRA_HEALTH);

    }

    @NonNull
    private String getBatteryPowerSource()
    {

        return getBatteryInfo(BatteryManager.EXTRA_PLUGGED);

    }

    @NonNull
    private String getBatteryTemparature()
    {
        Integer temperature = getIntExtra(BatteryManager.EXTRA_TEMPERATURE);
        return temperature == -1 ? StringUtils.EMPTY : String.valueOf(temperature / 10);
    }

    @NonNull
    private String getBatteryInfo(String name)
    {
        Integer info = getIntExtra(name);
        return info == -1 ? StringUtils.EMPTY : String.valueOf(info);
    }

    @NonNull
    private Integer getIntExtra(String name)
    {
        Intent batteryIntent = getBatteryIntent();
        return batteryIntent == null ? -1 : batteryIntent.getIntExtra(name,-1) ;
    }

    @NonNull
    private String getBatteryTechnology()
    {
        Intent batteryIntent = getBatteryIntent();
        return batteryIntent == null ? StringUtils.EMPTY : batteryIntent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
    }

    @Nullable
    private Intent getBatteryIntent(){
        return  getContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

}
