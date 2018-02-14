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

package rus.cpuinfo.Ui.Fragments.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.Controllers.InfoController;
import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.R;

public class CpuInfoBaseFragment extends android.support.v4.app.Fragment implements InfoController.InfoUi{

    @BindView(R.id.RVInfo)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info,container,false);
        ButterKnife.bind(this,v);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.addItemDecoration(new HardwareInfoAdapter.DividerItemDecoration(getContext()));

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
    public void onStop() {
        mRecyclerView.setAdapter(null);
        super.onStop();
    }
}
