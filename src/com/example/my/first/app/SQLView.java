package com.example.my.first.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SQLView extends Activity implements OnClickListener{
	TextView tvDBInfo = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tvDBInfo = (TextView)findViewById(R.id.tvInfoFromDB);
		HotOrNot info = new HotOrNot(SQLView.this);
		info.open();
		
		tvDBInfo.setText(info.getdata());
		
		info.close();
//		Button b = (Button)findViewById(R.id.bOpenDBView);
//		
//		b.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bOpenDBView:
			
			break;

		default:
			break;
		}
	}

	
	
	
}
