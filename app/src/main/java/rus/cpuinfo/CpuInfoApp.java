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

package rus.cpuinfo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;

import rus.cpuinfo.Controllers.MainController;
import rus.cpuinfo.Injections.Component.CpuInfoAppComponent;
import rus.cpuinfo.Injections.Component.DaggerCpuInfoAppComponent;
import rus.cpuinfo.Injections.Modules.ContextProvider;

public class CpuInfoApp extends Application
{
    private static Resources res;

    private static final String TAG = CpuInfoApp.class.getSimpleName();
    CpuInfoAppComponent mAppComponent;

    @Inject
    MainController mMainController;

    public static CpuInfoApp from(Context context) {
        return (CpuInfoApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        res = getResources();

        mAppComponent = DaggerCpuInfoAppComponent.builder()
                .contextProvider(new ContextProvider(this))
                .build();

        mAppComponent.inject(this);
    }

    public MainController getMainController() {
        return mMainController;
    }

    public static Resources getRes() {
        return res;
    }

}

