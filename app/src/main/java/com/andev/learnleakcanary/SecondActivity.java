package com.andev.learnleakcanary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leak_layout);

		button = findViewById(R.id.button);
		button.setText("SecondActivity click me");

		button.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				startActivity(new Intent(SecondActivity.this, ThirdActivity.class));

				startAsyncTask();
			}
		});
	}

	@SuppressLint("StaticFieldLeak")
	void startAsyncTask() {
		// This async task is an anonymous class and therefore has a hidden reference to the outer
		// class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
		// the activity instance will leak.
		new AsyncTask<Void, Void, Void>() {
			@Override protected Void doInBackground(Void... params) {
				// Do some slow work in background
				SystemClock.sleep(10000);
				return null;
			}
		}.execute();
	}
}
