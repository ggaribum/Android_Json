package com.onetwopunch.helloworld;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BaseActivityTest {

    @Rule
    public ActivityTestRule<BaseActivity> mActivityTestRule = new ActivityTestRule<>(BaseActivity.class);

    @Test
    public void baseActivityTest() {
    }

}
