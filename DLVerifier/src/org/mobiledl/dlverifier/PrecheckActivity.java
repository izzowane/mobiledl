package org.mobiledl.dlverifier;

import android.app.Activity;
import android.os.Bundle;

public class PrecheckActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String RESULT = "result";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_precheck);

	}
}
