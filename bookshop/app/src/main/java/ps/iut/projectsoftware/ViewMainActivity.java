package ps.iut.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class ViewMainActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	
	private LinearLayout linear6;
	private ScrollView vscroll1;
	private ImageView imageview1;
	private LinearLayout linear7;
	private ImageView imageview2;
	private LinearLayout linear1;
	private LinearLayout linear16;
	private LinearLayout linear32;
	private LinearLayout linear36;
	private LinearLayout linear40;
	private LinearLayout linear44;
	private LinearLayout linear48;
	private LinearLayout linear17;
	private HorizontalScrollView hscroll3;
	private TextView textview5;
	private LinearLayout linear18;
	private TextView textview6;
	private LinearLayout linear19;
	private ImageView imageview24;
	private ImageView imageview20;
	private ImageView imageview21;
	private ImageView imageview22;
	private ImageView imageview23;
	private LinearLayout linear33;
	private HorizontalScrollView hscroll7;
	private TextView textview13;
	private LinearLayout linear34;
	private TextView textview14;
	private LinearLayout linear35;
	private ImageView imageview40;
	private ImageView imageview41;
	private ImageView imageview42;
	private ImageView imageview43;
	private ImageView imageview44;
	private LinearLayout linear37;
	private HorizontalScrollView hscroll8;
	private TextView textview15;
	private LinearLayout linear38;
	private TextView textview16;
	private LinearLayout linear39;
	private ImageView imageview45;
	private ImageView imageview46;
	private ImageView imageview47;
	private ImageView imageview48;
	private ImageView imageview49;
	private LinearLayout linear41;
	private HorizontalScrollView hscroll9;
	private TextView textview17;
	private LinearLayout linear42;
	private TextView textview18;
	private LinearLayout linear43;
	private ImageView imageview50;
	private ImageView imageview51;
	private ImageView imageview52;
	private ImageView imageview53;
	private ImageView imageview54;
	private LinearLayout linear45;
	private HorizontalScrollView hscroll10;
	private TextView textview19;
	private LinearLayout linear46;
	private TextView textview20;
	private LinearLayout linear47;
	private ImageView imageview55;
	private ImageView imageview56;
	private ImageView imageview57;
	private ImageView imageview58;
	private ImageView imageview59;
	private LinearLayout linear49;
	private HorizontalScrollView hscroll11;
	private TextView textview21;
	private LinearLayout linear50;
	private TextView textview22;
	private LinearLayout linear51;
	private ImageView imageview60;
	private ImageView imageview61;
	private ImageView imageview62;
	private ImageView imageview63;
	private ImageView imageview64;
	
	private Intent profile = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear6 = findViewById(R.id.linear6);
		vscroll1 = findViewById(R.id.vscroll1);
		imageview1 = findViewById(R.id.imageview1);
		linear7 = findViewById(R.id.linear7);
		imageview2 = findViewById(R.id.imageview2);
		linear1 = findViewById(R.id.linear1);
		linear16 = findViewById(R.id.linear16);
		linear32 = findViewById(R.id.linear32);
		linear36 = findViewById(R.id.linear36);
		linear40 = findViewById(R.id.linear40);
		linear44 = findViewById(R.id.linear44);
		linear48 = findViewById(R.id.linear48);
		linear17 = findViewById(R.id.linear17);
		hscroll3 = findViewById(R.id.hscroll3);
		textview5 = findViewById(R.id.textview5);
		linear18 = findViewById(R.id.linear18);
		textview6 = findViewById(R.id.textview6);
		linear19 = findViewById(R.id.linear19);
		imageview24 = findViewById(R.id.imageview24);
		imageview20 = findViewById(R.id.imageview20);
		imageview21 = findViewById(R.id.imageview21);
		imageview22 = findViewById(R.id.imageview22);
		imageview23 = findViewById(R.id.imageview23);
		linear33 = findViewById(R.id.linear33);
		hscroll7 = findViewById(R.id.hscroll7);
		textview13 = findViewById(R.id.textview13);
		linear34 = findViewById(R.id.linear34);
		textview14 = findViewById(R.id.textview14);
		linear35 = findViewById(R.id.linear35);
		imageview40 = findViewById(R.id.imageview40);
		imageview41 = findViewById(R.id.imageview41);
		imageview42 = findViewById(R.id.imageview42);
		imageview43 = findViewById(R.id.imageview43);
		imageview44 = findViewById(R.id.imageview44);
		linear37 = findViewById(R.id.linear37);
		hscroll8 = findViewById(R.id.hscroll8);
		textview15 = findViewById(R.id.textview15);
		linear38 = findViewById(R.id.linear38);
		textview16 = findViewById(R.id.textview16);
		linear39 = findViewById(R.id.linear39);
		imageview45 = findViewById(R.id.imageview45);
		imageview46 = findViewById(R.id.imageview46);
		imageview47 = findViewById(R.id.imageview47);
		imageview48 = findViewById(R.id.imageview48);
		imageview49 = findViewById(R.id.imageview49);
		linear41 = findViewById(R.id.linear41);
		hscroll9 = findViewById(R.id.hscroll9);
		textview17 = findViewById(R.id.textview17);
		linear42 = findViewById(R.id.linear42);
		textview18 = findViewById(R.id.textview18);
		linear43 = findViewById(R.id.linear43);
		imageview50 = findViewById(R.id.imageview50);
		imageview51 = findViewById(R.id.imageview51);
		imageview52 = findViewById(R.id.imageview52);
		imageview53 = findViewById(R.id.imageview53);
		imageview54 = findViewById(R.id.imageview54);
		linear45 = findViewById(R.id.linear45);
		hscroll10 = findViewById(R.id.hscroll10);
		textview19 = findViewById(R.id.textview19);
		linear46 = findViewById(R.id.linear46);
		textview20 = findViewById(R.id.textview20);
		linear47 = findViewById(R.id.linear47);
		imageview55 = findViewById(R.id.imageview55);
		imageview56 = findViewById(R.id.imageview56);
		imageview57 = findViewById(R.id.imageview57);
		imageview58 = findViewById(R.id.imageview58);
		imageview59 = findViewById(R.id.imageview59);
		linear49 = findViewById(R.id.linear49);
		hscroll11 = findViewById(R.id.hscroll11);
		textview21 = findViewById(R.id.textview21);
		linear50 = findViewById(R.id.linear50);
		textview22 = findViewById(R.id.textview22);
		linear51 = findViewById(R.id.linear51);
		imageview60 = findViewById(R.id.imageview60);
		imageview61 = findViewById(R.id.imageview61);
		imageview62 = findViewById(R.id.imageview62);
		imageview63 = findViewById(R.id.imageview63);
		imageview64 = findViewById(R.id.imageview64);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ProfileActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), NotificationActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewCartActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview13.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview15.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview17.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview18.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview20.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview21.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
	}
	
	@Override
	public void onBackPressed() {
		SketchwareUtil.showMessage(getApplicationContext(), "See you Next Time ! 😄");
		finishAffinity();
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