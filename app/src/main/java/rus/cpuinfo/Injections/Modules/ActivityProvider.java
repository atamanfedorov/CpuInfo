package rus.cpuinfo.Injections.Modules;

import dagger.Module;
import dagger.Provides;
import rus.cpuinfo.Ui.Activity.CpuInfoBaseActivity;

/**
 * Created by XXX on 15.02.2018.
 */

@Module
public class ActivityProvider {

    private CpuInfoBaseActivity mActivity;

    public ActivityProvider(CpuInfoBaseActivity activity) {

        mActivity = activity;
    }

    @Provides
    CpuInfoBaseActivity providesActivity() {
        return mActivity;
    }

}
