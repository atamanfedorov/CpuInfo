package rus.cpuinfo.Injections.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rus.cpuinfo.Ui.Activity.CpuInfoBaseActivity;
import rus.cpuinfo.CpuInfoUiDisplay;
import rus.cpuinfo.Display;

/**
 * Created by XXX on 15.02.2018.
 */

@Module(
        includes = {
                ActivityProvider.class
        }
)
public class DisplayProvider {

    @Singleton
    @Provides
    Display providesDisplay(CpuInfoBaseActivity activity)
    {
        return new CpuInfoUiDisplay(activity);
    }

}
