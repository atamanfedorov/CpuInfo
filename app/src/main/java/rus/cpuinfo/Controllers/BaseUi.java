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
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class BaseUi<U> extends BaseController {

    private final Set<U> mUiSet;
    private final Set<U> mUnmodifiableUiSet;

    public BaseUi()
    {
        mUiSet = new CopyOnWriteArraySet<>();
        mUnmodifiableUiSet = Collections.unmodifiableSet(mUiSet);
    }

    public synchronized final void attachUi(U ui)
    {
        mUiSet.add(ui);
        onUiAttached(ui);
    }

    public synchronized final void detachUi(U ui)
    {
        onUiDetached(ui);
        mUiSet.remove(ui);
    }

    protected final Set<U> getUiSet() {
        return mUnmodifiableUiSet;
    }

    protected abstract void onUiAttached(U ui);
    protected abstract void onUiDetached(U ui);

}
