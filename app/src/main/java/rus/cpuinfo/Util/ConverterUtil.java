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

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Locale;

public class ConverterUtil {

    @NonNull
    public static String convertFromKHzToMHz(final String KHz) {

        Preconditions.checkArgument(NumberUtils.isCreatable(KHz),"KHz must be a numeric value");
        return String.format(Locale.getDefault(),"%d",convertFromKHzToMHz(Integer.parseInt(KHz)));
    }

    public static int convertFromKHzToMHz(final int KHz) {

        Preconditions.checkArgument(KHz > 0,"KHz must be greater than zero");
        return KHz / 1000;
    }
}
