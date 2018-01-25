package com.example.utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;

/**
 * fragment切换工具类
 */

public class FragmentSwitcher {

    private static final String FRAGMENT_NAME = "fragmentName";
    private static final String CONTAINER_ID = "containerId";
    private static final String FRAGMENT_TAG = "fragmentTag";
    private FragmentManager manager;

    public FragmentSwitcher(FragmentManager manager) {
        this.manager = manager;
    }

    public Fragment switchFragment(@IdRes int containerId, Fragment f, String tag) {
        return switchFragment(containerId, f, tag, 0, 0);
    }

    /**
     * 显示隐藏fragment，返回当前切换的fragment
     *
     * @param containerId fragment容器
     * @param f           fragment
     * @param tag         fragment tag
     * @return 当前切换的fragment
     */
    public Fragment switchFragment(@IdRes int containerId, Fragment f, String tag, int enter, int exit) {
        if (f == null || containerId == View.NO_ID) {
            return null;
        }

        hideOtherFragments(containerId);
        String actualTag = makeTag(f, tag, containerId);
        Fragment oldF = null;
        if (!TextUtils.isEmpty(actualTag)) {
            oldF = manager.findFragmentByTag(actualTag);
        }

        FragmentTransaction t = getHaveAnimationTransaction(enter, exit);
        if (oldF != null) {
            t.show(oldF).commitNowAllowingStateLoss();
            return oldF;

        } else {
            t.add(containerId, f, actualTag).commitNowAllowingStateLoss();
            return f;
        }
    }

    private FragmentTransaction getHaveAnimationTransaction(int enter, int exit) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (enter != 0 && exit != 0) {
            transaction.setCustomAnimations(enter, exit);
        }
        return transaction;
    }

    /**
     * 隐藏currentContainerId容器里其它的fragment
     *
     * @param currentContainerId
     */
    private void hideOtherFragments(@IdRes int currentContainerId) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null || fragments.isEmpty()) {
            return;
        }

        for (Fragment fragment : fragments) {
            String tag = fragment.getTag();
            if (TextUtils.isEmpty(tag)) {
                continue;
            }

            JSONTokener jsonTokener = new JSONTokener(tag);
            try {
                Object o = jsonTokener.nextValue();
                if (o instanceof JSONObject) {
                    JSONObject jo = (JSONObject) o;
                    int containerId = jo.optInt(CONTAINER_ID);
                    if (containerId == currentContainerId) {
                        //hide
                        hideFragment(fragment);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void hideFragment(Fragment fragment) {
        if (fragment != null) {
            manager.beginTransaction().hide(fragment).commitNowAllowingStateLoss();
        }
    }

    private String makeTag(Fragment f, String tag, @IdRes int containerId) {
        JSONObject jo = new JSONObject();
        try {
            jo.put(FRAGMENT_NAME, f.getClass().getCanonicalName());
            jo.put(CONTAINER_ID, containerId);
            jo.put(FRAGMENT_TAG, tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }
}
