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


package rus.cpuinfo.DeviceInfo;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

import rus.cpuinfo.Util.ConverterUtil;
import rus.cpuinfo.Util.OsCommand;

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

public class CpuInfo extends DeviceInfo {

    private static final String GREP_COMMAND = "grep";
    private static final String CPU_INFO_PATH = "/proc/cpuinfo";
    private static final String CPU_FREQ_PATH = "/sys/devices/system/cpu/cpu%d/cpufreq/%s";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({A_CPU_IMPLEMENTER, A_CPU_VARIANT, A_CPU_PART, A_CPU_REVISION, A_PROCESSOR,
            A_HARDWARE, A_SERIAL, A_REVISION})
    private @interface ACpuInfo {}
    private static final String A_CPU_IMPLEMENTER = "CPU implementer";
    private static final String A_CPU_VARIANT = "CPU variant";
    private static final String A_CPU_PART = "CPU part";
    private static final String A_CPU_REVISION = "CPU revision";
    private static final String A_PROCESSOR = "Processor";
    private static final String A_HARDWARE = "Hardware";
    private static final String A_SERIAL = "Serial";
    private static final String A_REVISION = "Revision";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({A_CPU_INFO_MAX_FREQ, A_CPU_INFO_MIN_FREQ, A_SCALING_CUR_FREQ})
    private @interface ACpuFreq {}
    private static final String A_CPU_INFO_MAX_FREQ = "cpuinfo_max_freq";
    private static final String A_CPU_INFO_MIN_FREQ = "cpuinfo_min_freq";
    private static final String A_SCALING_CUR_FREQ = "scaling_cur_freq";

    private static final String MATCH_PATTERN = ".+:.+";
    private static final String SPLIT_PATTERN = ":";

    private static final String STOPPED = "stopped";
    private static final long CORES = Runtime.getRuntime().availableProcessors();

    public CpuInfo(Context context) {
        super(context);
    }

    @Override
    @NonNull
    public String getInfo(int query) {
        switch (query) {
            case CPU_PROCESSOR:
                return getProcessor();
            case CPU_CORES:
                return getAviableProcessors();
            case CPU_REVISION:
                return getCpuRevision();
            case CPU_IMPLEM:
                return getCpuImplementer();
            case CPU_MIN_FREQ:
                return getMinFreq();
            case CPU_MAX_FREQ:
                return getMaxFreq();
            case CPU_FREQ:
                return getCurFreq();
            case CPU_VARIANT:
                return getCpuVariant();
            case HARDWARE:
                return getHardware();
            case REVISION:
                return getRevision();
            case CPU_PART:
                return getCpuPart();
            case SERIAL:
                return getSerial();
            default:
                throw new IllegalArgumentException("Query must be with \"CPU.\" prefix");
        }
    }

    @NonNull
    private String getProcessor()
    {
         return requestToCpuInfo(A_PROCESSOR);
    }

    @NonNull
    private String getCpuImplementer()
    {
        return requestToCpuInfo(A_CPU_IMPLEMENTER);
    }

    @NonNull
    private String getCpuVariant()
    {
        return requestToCpuInfo(A_CPU_VARIANT);
    }

    @NonNull
    private String getHardware()
    {
        return requestToCpuInfo(A_HARDWARE);
    }

    @NonNull
    private String getCpuPart()
    {
        return requestToCpuInfo(A_CPU_PART);
    }

    @NonNull
    private String getRevision()
    {
        return requestToCpuInfo(A_REVISION);
    }

    @NonNull
    private String getSerial()
    {
        return requestToCpuInfo(A_SERIAL);
    }

    @NonNull
    private String getCpuRevision()
    {
        return requestToCpuInfo(A_CPU_REVISION);
    }

    private String getAviableProcessors()
    {
        return CORES + StringUtils.EMPTY;
    }

    @NonNull
    private String getMinFreq()
    {
        return requestToCpuFreq(A_CPU_INFO_MIN_FREQ);
    }

    @NonNull
    private String getMaxFreq()
    {
        return requestToCpuFreq(A_CPU_INFO_MAX_FREQ);
    }

    @NonNull
    private String getCurFreq()
    {

        StringBuilder values = new StringBuilder();
        for (int coreIndex = 0; coreIndex < CORES; coreIndex++) {

            String curFreq = requestToCpuFreq(coreIndex, A_SCALING_CUR_FREQ);
            values.append(curFreq.isEmpty()
                    ? STOPPED : curFreq).append(" ");
        }

        return values.toString();
    }


    private @NonNull String requestToCpuInfo(@ACpuInfo String request)
    {

        String [] command = {GREP_COMMAND, request, CPU_INFO_PATH};
        String info = OsCommand.executeCommand(command).trim();

        return info.matches(MATCH_PATTERN) ? info.split(SPLIT_PATTERN)[1].trim(): info;
    }

    private @NonNull String requestToCpuFreq(@ACpuFreq String fileName)
    {
        return requestToCpuFreq(0,fileName);
    }

    private @NonNull String requestToCpuFreq(@IntRange(from=0,to=255) int coreIndex, @ACpuFreq String fileName)
    {

        String [] command = {GREP_COMMAND,StringUtils.EMPTY,String.format(Locale.getDefault(),CPU_FREQ_PATH,coreIndex,fileName)};
        String info = OsCommand.executeCommand(command).trim();

        return !TextUtils.isEmpty(info) ? ConverterUtil.convertFromKHzToMHz(info) : info;
    }

}
