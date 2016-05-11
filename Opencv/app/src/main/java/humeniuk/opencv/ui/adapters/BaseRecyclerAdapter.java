package humeniuk.opencv.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    protected LayoutInflater mInflater;
    protected OnItemSelectedListener mListener;
    protected List<T> mData;

    public BaseRecyclerAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener, List<T> data) {
        this.mInflater = mInflater;
        this.mListener = mListener;
        mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemSelectedListener{
        void onItemSelected(String id);
    }
}
