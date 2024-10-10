package ps.iut.projectsoftware;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class ManuelActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private TextView textview1;
	private TextView textview6;
	private TextView textview4;
	private TextView textview5;
	private TextView textview7;
	private Button button1;
	private LinearLayout linear3;
	private TextView textview2;
	private TextView textview3;
	private Button button2;
	
	private Intent move = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.manuel);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		textview6 = findViewById(R.id.textview6);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview7 = findViewById(R.id.textview7);
		button1 = findViewById(R.id.button1);
		linear3 = findViewById(R.id.linear3);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		button2 = findViewById(R.id.button2);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setAction(Intent.ACTION_VIEW);
				move.setData(Uri.parse("mailto:albreem@iut-dhaka.edu"));
				startActivity(move);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setAction(Intent.ACTION_VIEW);
				move.setData(Uri.parse("https://wa.me/+8801986474598"));
				startActivity(move);
			}
		});
	}
	
	private void initializeLogic() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		button2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFF000000, 0xFF9FA8DA));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFF000000, 0xFF9FA8DA));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFF9FA8DA, 0xFF3F51B5));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#E8EAF6")); }
	}
	
	@Override
	public void onBackPressed() {
		move.setClass(getApplicationContext(), MyprofileActivity.class);
		move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(move);
		finish();
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}