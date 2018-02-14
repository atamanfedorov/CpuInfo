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

import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

import rus.cpuinfo.Display;

@Singleton
public class MainController extends BaseUi<MainController.MainControllerUi>
{
    public enum Tabs {
        CPU,DEVICE,SYSTEM,BATTERY,SENSORS,ABOUT
    }

    private final CpuController mCpuController;
    private final DeviceContoller mDeviceContoller;
    private final SystemController mSystemContoller;
    private final BatteryController mBatteryController;
    private final SensorsController mSensorsController;

    @Inject
    public MainController(CpuController cpuController, DeviceContoller deviceContoller, SystemController systemController, BatteryController batteryController, SensorsController sensorsController){
        mCpuController = Preconditions.checkNotNull(cpuController,"cpuController must not be null");
        mDeviceContoller = Preconditions.checkNotNull(deviceContoller,"deviceContoller must not be null");
        mSystemContoller = Preconditions.checkNotNull(systemController,"systemController must not be null");
        mBatteryController = Preconditions.checkNotNull(batteryController,"batteryController must not be null");
        mSensorsController = Preconditions.checkNotNull(sensorsController,"sensorsController must not be null");
    }

    public interface MainControllerUi{

        void setTabs(Tabs...tabs);
    }


    private static final String mTAG = MainController.class.getSimpleName();


    @Override
    protected void onUiAttached(MainControllerUi ui) {
        Preconditions.checkNotNull(ui, "MainControllerUi is null");
        ui.setTabs(Tabs.CPU,Tabs.DEVICE,Tabs.SYSTEM,Tabs.BATTERY,Tabs.SENSORS,Tabs.ABOUT);
    }


    @Override
    protected void onUiDetached(MainControllerUi ui) {
        Preconditions.checkNotNull(ui, "MainControllerUi is null");
    }

    public void attachDisplay(Display display) {
        Preconditions.checkNotNull(display, "display is null");
        setDisplay(display);
    }

    public void detachDisplay()
    {
        setDisplay(null);
    }

    public DeviceContoller getDeviceContoller() {
        return mDeviceContoller;
    }

    public CpuController getCpuController() {
        return mCpuController;
    }

    public SystemController getSystemContoller() {
        return mSystemContoller;
    }

    public BatteryController getBatteryController() {
        return mBatteryController;
    }

    public SensorsController getSensorsController() {
        return mSensorsController;
    }

}
