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
import android.support.annotation.StringRes;

import com.google.common.base.Preconditions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rus.cpuinfo.Adapters.HardwareInfoAdapter;
import rus.cpuinfo.AndroidDepedentModel.BaseInfo;
import rus.cpuinfo.Util.Interfaces.ILogger;
import rus.cpuinfo.Util.Interfaces.IStringFetcher;

public abstract class InfoController extends BaseUi<InfoController.InfoUi> {

    public interface InfoUi
    {
        void setAdapter(HardwareInfoAdapter hardwareInfoAdapter);
    }

    private HardwareInfoAdapter mHardwareInfoAdapter;
    private IStringFetcher mStringFetcher;
    private BaseInfo mBaseInfo;
    private ILogger mLogger;

    private final static String mTag = InfoController.class.getSimpleName();

    public InfoController(@NonNull HardwareInfoAdapter hardwareInfoAdapter, @NonNull BaseInfo baseInfo, @NonNull IStringFetcher stringFetcher, @NonNull ILogger logger)
    {
        mBaseInfo = Preconditions.checkNotNull(baseInfo,"baseInfo must not be null");
        mStringFetcher = Preconditions.checkNotNull(stringFetcher,"stringFetcher must not be null");
        mHardwareInfoAdapter = Preconditions.checkNotNull(hardwareInfoAdapter,"hardwareInfoAdapter must not be null");
        mLogger = Preconditions.checkNotNull(logger,"logger must not be null");
    }

    @NonNull
    protected HardwareInfoAdapter getAdapter()
    {
        return mHardwareInfoAdapter;
    }


    @NonNull String getString(@StringRes int id) {
        return mStringFetcher.getString(id);
    }

    @Override
    protected void onUiAttached(InfoUi ui) {
        ui.setAdapter(mHardwareInfoAdapter);
        mLogger.d(mTag,"InfoController. onUiAttached");
    }

    @Override
    protected void onUiDetached(InfoUi ui) {
        mLogger.d(mTag,"InfoController. onUiDetached");
    }

    String getInfo(int q)
    {
        return mBaseInfo.getInfo(q);
    }

    ILogger getLogger()
    {
        return mLogger;
    }

    void updateInformation(@NonNull rus.cpuinfo.Model.BaseInfo baseInfo)
    {
        mLogger.d(mTag,"cl-InfoController. m - updateInformation. p1 - baseInfo = " + baseInfo);
        mHardwareInfoAdapter.setInformation(baseInfo);
    }


    final void updateAllInformation(@NonNull rus.cpuinfo.Model.BaseInfo baseInfo)
    {
        mLogger.d(mTag,"cl-InfoController. m - updateInformation. p1 - baseInfo = " + baseInfo);

        updateInformation(baseInfo);
        getAdapter().notifyDataSetChanged();

    }

    final void setObservableOnSubscribe(ObservableOnSubscribe<rus.cpuinfo.Model.BaseInfo> obs)
    {
        setObservableOnSubscribe(obs,1000);
    }

    final void setObservableOnSubscribe(ObservableOnSubscribe<rus.cpuinfo.Model.BaseInfo> obs, long delayInMilliseconds)
    {

        Observable.create(obs)
                .subscribeOn(Schedulers.single())
                .delay(delayInMilliseconds, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .repeatUntil(() -> !isInited())
                .subscribe(b -> updateInformation(b));

    }

}

