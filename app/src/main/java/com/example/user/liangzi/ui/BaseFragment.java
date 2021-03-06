package com.example.user.liangzi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2016/10/26.
 */
public abstract class BaseFragment extends Fragment implements UiInitInterface{
    protected View rootView;
    protected BaseActivity mContext;
    private int layoutId = -1;
    protected BaseFragment mPrevious;
    protected BaseFragment mNext;
    protected int mFragmentId;
    protected Bundle mBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            loadXml();
            if(layoutId == -1) throw new RuntimeException("layout id can not be null");
            rootView = LayoutInflater.from(mContext).inflate(layoutId,container,false);
            init();
            setListener();
            setData();
            setOther();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public BaseFragment getPrevious() {
        return mPrevious;
    }

    public void setPrevious(BaseFragment fragment) {
        mPrevious = fragment;
    }

    public BaseFragment getNext() {
        return mNext;
    }

    public void setNext(BaseFragment fragment) {
        mNext = fragment;
    }

    public int getFragmentId() {
        return mFragmentId;
    }

    public void setFragmentId(int fragmentId) {
        mFragmentId = fragmentId;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    public Bundle getBundle() {
        return mBundle;
    }

    protected void setContentView(int layoutId) {
        this.layoutId = layoutId;
    }
}
