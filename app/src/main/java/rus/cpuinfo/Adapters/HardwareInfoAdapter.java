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

package rus.cpuinfo.Adapters;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import rus.cpuinfo.Model.IHardwareInfo;
import rus.cpuinfo.R;
import rus.cpuinfo.Util.StringHelper;

public class HardwareInfoAdapter extends RecyclerView.Adapter<HardwareInfoAdapter.ViewHolder1> {

     class ViewHolder1 extends RecyclerView.ViewHolder {

         TextView mtxv1;
         TextView mtxv2;

         ViewHolder1(View v) {
            super(v);
            mtxv1 = (TextView) itemView.findViewById(R.id.txv1);
            mtxv2 = (TextView) itemView.findViewById(R.id.txv2);
        }

         Context getContext()
         {
            return itemView.getContext();
         }

    }

    private List<Integer> mQueryConstant;
    private IHardwareInfo<String> mHardwareInfo;


    public HardwareInfoAdapter(@NonNull IHardwareInfo<String> information, @NonNull List<Integer> queryConstant) {
        this(queryConstant);
        setInformation(information);
    }

    public HardwareInfoAdapter(@NonNull List<Integer> queryConstant) {
        mQueryConstant = Preconditions.checkNotNull(queryConstant,"queryConstant is null");
    }

    public void setInformation(@NonNull IHardwareInfo<String> information) {
        mHardwareInfo = Preconditions.checkNotNull(information,"information is null");
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);

        return new ViewHolder1(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {

        Integer queryConstant = mQueryConstant.get(position);
        Integer index = position - mQueryConstant.indexOf(queryConstant);

        Context context = holder.getContext();

        String fieldFeature = context.getString(StringHelper.getStringResId(queryConstant),index);
        holder.mtxv1.setText(fieldFeature); // left field

        String fieldValue = getValue(queryConstant | index);
        holder.mtxv2.setText(TextUtils.isEmpty(fieldValue) ? context.getString(R.string.undefined) : fieldValue); // right field
    }

    @Override
    public int getItemCount() {
        return mQueryConstant.size();
    }

    @NonNull
    private String getValue(int qf)
    {
        return mHardwareInfo != null ? mHardwareInfo.getInfoByQuery(qf) : StringUtils.EMPTY;
    }

    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public DividerItemDecoration(Context context) {

            TypedArray attributes = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
            mDivider = attributes.getDrawable(0);

            attributes.recycle();
        }

        public DividerItemDecoration(Context context,@DrawableRes int resId) {
            mDivider = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


            for (int i = 0; i < parent.getChildCount(); i++) {

                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();

                final int left = parent.getPaddingLeft();
                final int right = parent.getWidth() - parent.getPaddingRight();

                mDivider.setBounds(left,top,right,bottom);
                mDivider.draw(c);
            }
        }
    }

    public static class VerticalSpaceBetweenItem extends RecyclerView.ItemDecoration {

        private final int mVerticalSpaceHeight;

        public VerticalSpaceBetweenItem() {
            this(1);
        }
        public VerticalSpaceBetweenItem(int verticalSpaceHeight) {
            mVerticalSpaceHeight = verticalSpaceHeight;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.set(0, 0, 0, mVerticalSpaceHeight);
        }
    }
}
