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

package rus.cpuinfo.Util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/** Created by Ruslan_<<RUS_M>>.It simple class is needed for updating values of the screens.*/
public class Repeater implements Runnable
{
    private volatile boolean hasBeenStarted = false;
    private long mDelay;

    private Handler mHandler;

    private onRepeatListener mRepeatListener;


    public Repeater()
    {
        this(Looper.getMainLooper(),null,1000);
    }

    public Repeater(@NonNull Looper looper,@Nullable onRepeatListener repeatListener,long delay)
    {
        mHandler = new Handler(looper);
        mRepeatListener = repeatListener;
        mDelay = delay;
    }

    public long getDelay()
    {
        return mDelay;
    }

    public boolean isStarted() {
        return hasBeenStarted;
    }

    public void setDelay(long delay) {
        mDelay = delay;
    }

    public void setOnRepeatListener(@Nullable onRepeatListener repeatListener) {
        this.mRepeatListener = repeatListener;
    }


    public void startRepeating() {
            checkNotStarted();
            hasBeenStarted = true;

            performWithDelay();
    }

    public void stopRepeating() {
        mHandler.removeCallbacks(this);
        hasBeenStarted = false;
    }

    private void checkNotStarted() {
        if (hasBeenStarted)
            throw new IllegalThreadStateException("Repeater already started");
    }

    private void performWithDelay() {
        mHandler.postDelayed(this,mDelay);
    }

    public interface onRepeatListener {
        void onRepeat();
    }

    @Override
    public void run() {
        try {
            if (mRepeatListener != null)
                mRepeatListener.onRepeat();
        }
        finally {
            performWithDelay();
        }
    }
}