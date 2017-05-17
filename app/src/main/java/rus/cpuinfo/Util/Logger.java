package rus.cpuinfo.Util;

import android.util.Log;

import rus.cpuinfo.Util.Interfaces.ILogger;

/**
 * Created by XXX on 19.04.2017.
 */

public class Logger implements ILogger {

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }
}
