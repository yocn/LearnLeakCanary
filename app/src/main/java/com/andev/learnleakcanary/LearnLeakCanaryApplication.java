package com.andev.learnleakcanary;

import android.app.Application;

import com.andev.learnleakcanary.leakcanary.ActivityRefWatcher;
import com.andev.learnleakcanary.leakcanary.RefWatcher;


public class LearnLeakCanaryApplication extends Application {


	@Override
	public void onCreate() {
		super.onCreate();

		ActivityRefWatcher.install(this, new RefWatcher());
	}

}
