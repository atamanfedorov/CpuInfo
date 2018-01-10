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


public class DeviceInfo extends BaseInfo {

    public void setScreenSize(@NonNull String screenSize) {
        if (isMatched(screenSize))
            putInfo(DEVICE_SCREEN_SIZE, screenSize);
    }

    public void setScreenResolution(@NonNull String screenResolution) {

        if (!TextUtils.isEmpty(screenResolution))
        {
            String [] splitted = screenResolution.split("\\s");

            if (splitted.length >= 2 && splitted[0].matches(DIGITAL_PATTERN) && splitted[1].matches(DIGITAL_PATTERN))
                putInfo(DEVICE_SCREEN_RESOLUTION, screenResolution.replaceFirst("\\s", " x "));
        }
    }

    public void setScreenDensity(@NonNull String screenDensity) {
         if (isMatched(screenDensity))
             putInfo(DEVICE_SCREEN_DENSITY,screenDensity);
    }

    public void setAvailableRam(@NonNull String availableRam) {
       // if (isMatched(availableRam))
            putInfo(DEVICE_AVIALABLE_RAM,availableRam);
    }

    public void setAvailableStorage(@NonNull String availableStorage) {
       // if (isMatched(availableStorage))
            putInfo(DEVICE_AVAILABLE_STORAGE,availableStorage);
    }

    public void setInternalStorage(@NonNull String internalStorage) {
      //  if (isMatched(internalStorage))
            putInfo(DEVICE_INTERNAL_STORAGE,internalStorage);
    }

    public void setTotalRam(@NonNull String totalRam) {
     //   if (isMatched(totalRam))
         putInfo(DEVICE_TOTAL_RAM,totalRam);
    }


    public void setBoard(@NonNull String board) {
        putInfo(DEVICE_BOARD,board);
    }

    public void setModel(@NonNull String model) {
        putInfo(DEVICE_MODEL,model);
    }

    public void setManufacturer(@NonNull String manufacturer) {
        putInfo(DEVICE_MANUFACTURER,manufacturer);
    }

   private boolean isMatched(String data)
    {
        return !TextUtils.isEmpty(data) && data.split("\\s")[0].matches(DIGITAL_PATTERN);
    }
}
