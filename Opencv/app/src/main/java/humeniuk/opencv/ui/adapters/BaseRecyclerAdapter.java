package humeniuk.opencv.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    protected LayoutInflater mInflater;
    protected OnItemSelectedListener mListener;
    protected List<T> mData;

    public BaseRecyclerAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener) {
        this.mInflater = mInflater;
        this.mListener = mListener;
    }

    public void swapData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemSelectedListener{
        void onItemSelected(String id);
    }
}
