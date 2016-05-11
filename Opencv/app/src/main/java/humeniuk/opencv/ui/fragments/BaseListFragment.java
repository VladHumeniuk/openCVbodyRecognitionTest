package humeniuk.opencv.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import humeniuk.opencv.R;
import humeniuk.opencv.ui.adapters.BaseRecyclerAdapter;

public abstract class BaseListFragment extends BaseFragment {

    @Bind(R.id.recycler_list) RecyclerView mList;
    private BaseRecyclerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.list_fragment_layout;
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        mAdapter = createAdapter();
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public BaseRecyclerAdapter getAdapter() {
        return mAdapter;
    }

    protected abstract BaseRecyclerAdapter createAdapter();

}
