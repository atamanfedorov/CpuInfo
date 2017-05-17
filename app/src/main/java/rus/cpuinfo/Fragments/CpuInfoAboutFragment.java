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

package rus.cpuinfo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rus.cpuinfo.R;

public class CpuInfoAboutFragment extends Fragment {

    public static CpuInfoAboutFragment newInstance() {

        Bundle args = new Bundle();

        CpuInfoAboutFragment fragment = new CpuInfoAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View r = inflater.inflate(R.layout.fragment_about,container,false);

        TextView txv = (TextView)r.findViewById(R.id.txv_version);
        txv.append(rus.cpuinfo.BuildConfig.VERSION_NAME);

        return r;
    }

}
