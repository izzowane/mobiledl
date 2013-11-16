package org.mobiledl.dlverifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();
	private Button scannerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scannerButton = (Button) findViewById(R.id.scanButton);
		scannerButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (scannerButton.isPressed()) {
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("SCAN_FORMATS", "PDF_417");
			startActivityForResult(intent, 0);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = data.getStringExtra("SCAN_RESULT");
				String format = data.getStringExtra("SCAN_RESULT_FORMAT");
				Log.d(TAG, "Content:" + contents + "\nFormat:" + format);

				try {
					String dlno = GeorgiaLicenseParser.getDLNumber(contents);
					Log.d(TAG, " DL NO = [" + dlno + "]");
					Intent i = new Intent(this, DDSViewActivity.class);
					i.putExtra(DDSViewActivity.EXTRA_DLNO, dlno);
					i.putExtra(DDSViewActivity.EXTRA_BARCODE_DATA, contents);
					this.startActivity(i);
				} catch (Exception e) {
					Log.d(TAG, "Exception occured ..." + e.getMessage());

					Intent i = new Intent(this, PrecheckActivity.class);
					i.putExtra(PrecheckActivity.RESULT, false);

					this.startActivity(i);
				}

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				Toast toast = Toast.makeText(this, "Scan was Cancelled!",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.TOP, 25, 400);
				toast.show();

			}
		}
	}

}
