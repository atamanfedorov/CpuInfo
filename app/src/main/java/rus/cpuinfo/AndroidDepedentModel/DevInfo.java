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

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.util.DisplayMetrics;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

import rus.cpuinfo.Util.VersionUtil;

import static android.text.format.Formatter.formatFileSize;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_AVAILABLE_STORAGE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_AVIALABLE_RAM;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_BOARD;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_INTERNAL_STORAGE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_MANUFACTURER;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_MODEL;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_DENSITY;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_RESOLUTION;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_SCREEN_SIZE;
import static rus.cpuinfo.Model.BaseInfo.DEVICE_TOTAL_RAM;

public class DevInfo extends BaseInfo {

    public DevInfo(Context context) {
        super(context);
    }

    @Override
    @NonNull
    public String getInfo(int query) {
        switch (query) {
            case DEVICE_AVIALABLE_RAM:
                return getFreeMemory();
            case DEVICE_MODEL:
                return getModel();
            case DEVICE_MANUFACTURER:
                return getManufacturer();
            case DEVICE_BOARD:
                return getBoard();
            case DEVICE_TOTAL_RAM:
                return getTotalMemory();
            case DEVICE_SCREEN_SIZE:
                return getScreenSize();
            case DEVICE_SCREEN_DENSITY:
                return getScreenDensity();
            case DEVICE_SCREEN_RESOLUTION:
                return getScreenResolution();
            case DEVICE_INTERNAL_STORAGE:
                return getInternalStorage();
            case DEVICE_AVAILABLE_STORAGE:
                return getAviableStorage();
            default:
                throw new IllegalArgumentException("Query must be with \"DEVICE.\" prefix");

        }
    }
    @NonNull
    private ActivityManager.MemoryInfo getMemoryInfo() {

        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(mi);

        return mi;
    }

    @NonNull
    private String getFreeMemory() {

        ActivityManager.MemoryInfo mi = getMemoryInfo();
        return Formatter.formatFileSize(getContext(),mi.availMem);
    }

    @NonNull
    private String getTotalMemory() {
        ActivityManager.MemoryInfo mi = getMemoryInfo();
        return formatFileSize(getContext(),mi.totalMem);
    }

    @NonNull
    private DisplayMetrics getDisplayMetrics() {

        return Resources.getSystem().getDisplayMetrics();

    }

    @NonNull
    private String getScreenSize() {

        DisplayMetrics metrics = getDisplayMetrics();

        final double x = Math.pow(metrics.widthPixels / metrics.xdpi, 2);
        final double y = Math.pow(metrics.heightPixels / metrics.ydpi, 2);
        final double screenInches = Math.sqrt(x + y);

        return String.format(Locale.getDefault(),"%.2f",screenInches);
    }


    @NonNull
    private String getScreenDensity() {
        DisplayMetrics metrics = getDisplayMetrics();
        return String.format(Locale.getDefault(),"%d",metrics.densityDpi);
    }

    @NonNull
    private String getScreenResolution() {
        DisplayMetrics metrics = getDisplayMetrics();
        return String.format(Locale.getDefault(),"%d %d",metrics.widthPixels,metrics.heightPixels);
    }

    @NonNull
    private String getModel() {
        return Build.MODEL != null ? Build.MODEL : StringUtils.EMPTY;
    }

    @NonNull
    private String getManufacturer() {
        return Build.MANUFACTURER != null ? Build.MANUFACTURER : StringUtils.EMPTY;
    }

    @NonNull
    private String getBoard() {
        return Build.BOARD != null ? Build.BOARD : StringUtils.EMPTY;
    }

    @NonNull
    @SuppressLint("NewApi")
    private String getInternalStorage() {

        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());

        final long blockCount = VersionUtil.IS_JELLY_BEAN_MR2()
                ? statFs.getBlockCountLong() * statFs.getBlockSizeLong()
                : statFs.getBlockCount() * statFs.getBlockSize();

       // return String.format(Locale.getDefault(),"%s",blockCount);
     return formatFileSize(getContext(),blockCount);

    }

    @NonNull
    @SuppressLint("NewApi")
    private String getAviableStorage() {

        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());

        final long availBlocks = VersionUtil.IS_JELLY_BEAN_MR2()
                ? statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()
                : statFs.getAvailableBlocks() * statFs.getBlockSize();

       // return String.format(Locale.getDefault(),"%s",availBlocks);
        return formatFileSize(getContext(),availBlocks).replaceAll(",",".");

    }
}
