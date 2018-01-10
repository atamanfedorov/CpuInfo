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
import android.text.TextUtils;


public class CpuInfo extends BaseInfo {

    public void setCores(@NonNull String cores) {

        if(isDigital(cores) && cores.compareTo("0") > 0)
            putInfo(CPU_CORES,cores);

    }

    public void setCpuFreq(@NonNull String cpuFreq) {

        if (!TextUtils.isEmpty(cpuFreq)) {
            String[] values = cpuFreq.split(" ");
            for (int i = 0; i < values.length; i++)
                putInfo(CPU_FREQ | i, values[i]);
        }
    }

    public void setMinFreq(@NonNull String minFreq) {
        if (isDigital(minFreq))
            putInfo(CPU_MIN_FREQ, minFreq);

    }

    public void setMaxFreq(@NonNull String maxFreq) {
        if (isDigital(maxFreq))
            putInfo(CPU_MAX_FREQ,maxFreq);
    }

    public void setCpuImplementer(@NonNull String cpuImplementer) {
        if (isDigital(cpuImplementer))
            putInfo(CPU_IMPLEM,cpuImplementer);
    }

    public void setCpuPart(@NonNull String cpuPart) {
        if (isDigital(cpuPart))
            putInfo(CPU_PART,cpuPart);
    }

    public void setCpuVariant(@NonNull String cpuVariant) {
        if (isDigital(cpuVariant))
            putInfo(CPU_VARIANT,cpuVariant);
    }

    public void setCpuRevision(@NonNull String cpuRevision) {
        if (isDigital(cpuRevision))
            putInfo(CPU_REVISION,cpuRevision);
    }

    public void setSerial(@NonNull String serial) {
        if (isDigital(serial))
            putInfo(SERIAL,serial);
    }

    public void setRevision(@NonNull String revision) {
        if (isDigital(revision))
            putInfo(REVISION,revision);
    }

    public void setProcessor(@NonNull String processor) {
        putInfo(CPU_PROCESSOR,processor);
    }

    public void setHardware(@NonNull String hardware) {
        putInfo(HARDWARE,hardware);
    }
    }
