package rus.cpuinfo.Injections.Component;

import javax.inject.Singleton;

import dagger.Component;
import rus.cpuinfo.Ui.Activity.CpuInfoBaseActivity;
import rus.cpuinfo.Injections.Modules.DisplayProvider;

/**
 * Created by XXX on 15.02.2018.
 */

@Singleton
@Component(modules={DisplayProvider.class})
public interface CpuInfoActivityComponent {
    void inject(CpuInfoBaseActivity activity);
}
