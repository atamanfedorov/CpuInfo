package rus.cpuinfo.Injections.Component;

import javax.inject.Singleton;

import dagger.Component;
import rus.cpuinfo.CpuInfoApp;
import rus.cpuinfo.Injections.Modules.CpuInfoAppModule;

/**
 * Created by XXX on 12.02.2018.
 */

@Singleton
@Component(modules={CpuInfoAppModule.class})
public interface CpuInfoAppComponent {
    void inject(CpuInfoApp cpuInfoApp);
}
