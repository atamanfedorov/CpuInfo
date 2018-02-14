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
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Injections.Qualifers.ForCpu;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

import static rus.cpuinfo.Model.BaseInfo.CPU_CORES;
import static rus.cpuinfo.Model.BaseInfo.CPU_FREQ;
import static rus.cpuinfo.Model.BaseInfo.CPU_IMPLEM;
import static rus.cpuinfo.Model.BaseInfo.CPU_MAX_FREQ;
import static rus.cpuinfo.Model.BaseInfo.CPU_MIN_FREQ;
import static rus.cpuinfo.Model.BaseInfo.CPU_PART;
import static rus.cpuinfo.Model.BaseInfo.CPU_PROCESSOR;
import static rus.cpuinfo.Model.BaseInfo.CPU_REVISION;
import static rus.cpuinfo.Model.BaseInfo.CPU_VARIANT;
import static rus.cpuinfo.Model.BaseInfo.HARDWARE;
import static rus.cpuinfo.Model.BaseInfo.REVISION;
import static rus.cpuinfo.Model.BaseInfo.SERIAL;

@Singleton
public class CpuController extends InfoController {

    private final static String mTag = CpuController.class.getSimpleName();

    @Inject
    public CpuController(@ForCpu HardwareInfoAdapter hardwareInfoAdapter, @ForCpu BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger) {
        super(hardwareInfoAdapter, baseInfo, stringFetcher,logger);
    }

    @Override
    protected void onInited() {
        super.onInited();
        getLogger().d(mTag,"CpuContoller. OnInited");

        rus.cpuinfo.Model.CpuInfo cpuInfo = new rus.cpuinfo.Model.CpuInfo();
        cpuInfo.setCores(getInfo(CPU_CORES));
        cpuInfo.setCpuImplementer(getInfo(CPU_IMPLEM));
        cpuInfo.setMinFreq(getInfo(CPU_MIN_FREQ));
        cpuInfo.setMaxFreq(getInfo(CPU_MAX_FREQ));
        cpuInfo.setCpuRevision(getInfo(CPU_REVISION));
        cpuInfo.setCpuPart(getInfo(CPU_PART));
        cpuInfo.setRevision(getInfo(REVISION));
        cpuInfo.setCpuVariant(getInfo(CPU_VARIANT));
        cpuInfo.setHardware(getInfo(HARDWARE));
        cpuInfo.setSerial(getInfo(SERIAL));
        cpuInfo.setProcessor(getInfo(CPU_PROCESSOR).replaceFirst("Processor",""));
        updateAllInformation(cpuInfo);

        setObservableOnSubscribe( e -> {

            String frequences = getInfo(CPU_FREQ).replaceFirst("Processor","");
            cpuInfo.setCpuFreq(frequences.replaceAll("stopped+",getString(R.string.cpu_stopped)));

            e.onNext(cpuInfo);
            e.onComplete();
        });
    }

    @Override
    void updateInformation(@NonNull rus.cpuinfo.Model.BaseInfo baseInfo) {
        super.updateInformation(baseInfo);

        HardwareInfoAdapter hardwareInfoAdapter = getAdapter();
        hardwareInfoAdapter.notifyItemRangeChanged(hardwareInfoAdapter.getPosition(CPU_FREQ),Runtime.getRuntime().availableProcessors());

    }

}



