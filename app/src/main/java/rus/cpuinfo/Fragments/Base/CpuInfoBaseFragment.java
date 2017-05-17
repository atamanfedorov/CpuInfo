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

package rus.cpuinfo.Fragments.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.Controllers.InfoController;
import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.R;

public class CpuInfoBaseFragment extends android.support.v4.app.Fragment implements InfoController.InfoUi{

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info,container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView)v.findViewById(R.id.RVInfo);
        mRecyclerView.setLayoutManager(layoutManager);

        HardwareInfoAdapter.VerticalSpaceBetweenItem spaceBetweenItem = new HardwareInfoAdapter.VerticalSpaceBetweenItem(1);
        mRecyclerView.addItemDecoration(spaceBetweenItem);

        HardwareInfoAdapter.DividerItemDecoration dividerItemDecoration = new HardwareInfoAdapter.DividerItemDecoration(getContext(),R.drawable.black_gradient_divider);
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        return v;
    }

    protected final MainController getController()
    {
        return CpuInfoApp.from(getActivity()).getMainController();
    }

    @Override
    public void setAdapter(HardwareInfoAdapter hardwareInfoAdapter) {
        Preconditions.checkArgument(hardwareInfoAdapter != null,"Adapter must not be null");
        mRecyclerView.setAdapter(hardwareInfoAdapter);
    }

    @Override
    public void onPause() {
        mRecyclerView.setAdapter(null);
        super.onPause();
    }
}
