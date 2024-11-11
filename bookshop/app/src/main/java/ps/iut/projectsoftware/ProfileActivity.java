package ps.iut.projectsoftware;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
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

public class ProfileActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private LinearLayout linear6;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear18;
	private ImageView imageview2;
	private LinearLayout linear14;
	private TextView textview1;
	private ImageView imageview3;
	private LinearLayout linear15;
	private TextView textview2;
	private ImageView imageview4;
	private LinearLayout linear16;
	private TextView textview3;
	private ImageView imageview5;
	private LinearLayout linear17;
	private TextView textview4;
	
	private SharedPreferences a;
	private Intent ocm = new Intent();
	private SharedPreferences favorite;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		linear6 = findViewById(R.id.linear6);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear18 = findViewById(R.id.linear18);
		imageview2 = findViewById(R.id.imageview2);
		linear14 = findViewById(R.id.linear14);
		textview1 = findViewById(R.id.textview1);
		imageview3 = findViewById(R.id.imageview3);
		linear15 = findViewById(R.id.linear15);
		textview2 = findViewById(R.id.textview2);
		imageview4 = findViewById(R.id.imageview4);
		linear16 = findViewById(R.id.linear16);
		textview3 = findViewById(R.id.textview3);
		imageview5 = findViewById(R.id.imageview5);
		linear17 = findViewById(R.id.linear17);
		textview4 = findViewById(R.id.textview4);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		favorite = getSharedPreferences("favorite", Activity.MODE_PRIVATE);
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), MyprofileActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), MyorderActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), MyfivouritActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.edit().putString("login", "").commit();
				a.edit().putString("email", "").commit();
				a.edit().putString("balance", "").commit();
				favorite.edit().putString("favorite", "").commit();
				favorite.edit().putString("wishlist", "").commit();
				ocm.setClass(getApplicationContext(), LoginActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)48, (int)5, 0xFFFFFFFF, 0xFF3F51B5));
	}
	
	@Override
	public void onBackPressed() {
		ocm.setClass(getApplicationContext(), ViewMainActivity.class);
		ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(ocm);
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