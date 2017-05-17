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
import javax.inject.Singleton;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.DeviceInfo.DeviceInfo;
import rus.cpuinfo.Qualifers.ForCpu;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Repeater;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

import static rus.cpuinfo.Model.HardwareInfo.CPU_CORES;
import static rus.cpuinfo.Model.HardwareInfo.CPU_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_IMPLEM;
import static rus.cpuinfo.Model.HardwareInfo.CPU_MAX_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_MIN_FREQ;
import static rus.cpuinfo.Model.HardwareInfo.CPU_PART;
import static rus.cpuinfo.Model.HardwareInfo.CPU_PROCESSOR;
import static rus.cpuinfo.Model.HardwareInfo.CPU_REVISION;
import static rus.cpuinfo.Model.HardwareInfo.CPU_VARIANT;
import static rus.cpuinfo.Model.HardwareInfo.HARDWARE;
import static rus.cpuinfo.Model.HardwareInfo.REVISION;
import static rus.cpuinfo.Model.HardwareInfo.SERIAL;

@Singleton
public class CpuController extends InfoController {

    private rus.cpuinfo.Model.CpuInfo mCpuInfo = new rus.cpuinfo.Model.CpuInfo();
    private final static String mTag = CpuController.class.getSimpleName();
    @Inject
    public CpuController(@ForCpu HardwareInfoAdapter hardwareInfoAdapter, @ForCpu DeviceInfo deviceInfo, @NonNull IStringFetcher stringFetcher,@NonNull ILogger logger) {
        super(hardwareInfoAdapter, deviceInfo, stringFetcher,logger);
    }

    @Override
    protected void onInited() {
        super.onInited();
        getLogger().d(mTag,"CpuContoller. OnInited");

        mCpuInfo.setCores(getInfo(CPU_CORES));
        mCpuInfo.setCpuImplementer(getInfo(CPU_IMPLEM));
        mCpuInfo.setMinFreq(getInfo(CPU_MIN_FREQ));
        mCpuInfo.setMaxFreq(getInfo(CPU_MAX_FREQ));
        mCpuInfo.setCpuRevision(getInfo(CPU_REVISION));
        mCpuInfo.setCpuPart(getInfo(CPU_PART));
        mCpuInfo.setRevision(getInfo(REVISION));
        mCpuInfo.setCpuVariant(getInfo(CPU_VARIANT));
        mCpuInfo.setHardware(getInfo(HARDWARE));
        mCpuInfo.setSerial(getInfo(SERIAL));

        String processor = getInfo(CPU_PROCESSOR).replaceFirst("Processor","");
        mCpuInfo.setProcessor(processor);

        getRepeater().setOnRepeatListener(new Repeater.onRepeatListener() {
            @Override
            public void onRepeat() {

                String frequences = getInfo(CPU_FREQ);
                mCpuInfo.setCpuFreq(frequences.replaceAll("stopped+",getString(R.string.cpu_stopped)));

                updateInformation(mCpuInfo);
            }
        });
    }
}

