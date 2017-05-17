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

package rus.cpuinfo.Util;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;

public class VersionUtil {

    public static boolean IS_JELLY_BEAN_MR2() {
        return isApiVersionEqualOrGrater(JELLY_BEAN_MR2);
    }

    private static boolean isApiVersionEqualOrGrater(int version) {
        return getApiVersion() >= version;
    }

    private static int getApiVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }
}
