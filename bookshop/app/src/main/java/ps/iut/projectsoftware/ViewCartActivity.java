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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.material.button.*;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ViewCartActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String str = "";
	private boolean a = false;
	private HashMap<String, Object> mapp = new HashMap<>();
	private double totalprivate = 0;
	private double feesondelivary = 0;
	private boolean feesondelivarybool = false;
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear11;
	private ListView listview1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private LinearLayout linear9;
	private TextView textview6;
	private CheckBox checkbox3;
	private CheckBox checkbox4;
	private TextView textview1;
	private TextView feesondelivey;
	private TextView textview12;
	private TextView textview5;
	private CheckBox checkbox1;
	private CheckBox checkbox2;
	private TextView textview3;
	private TextView feesonpay;
	private TextView textview13;
	private TextView textview9;
	private EditText edittext1;
	private TextView textview10;
	private TextView textview7;
	private TextView total;
	private TextView textview14;
	private MaterialButton materialbutton1;
	private LinearLayout linear12;
	private ImageView imageview1;
	private LinearLayout linear13;
	private TextView textview11;
	
	private Intent eb = new Intent();
	private TimerTask v;
	private SharedPreferences cart;
	private SharedPreferences information;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_cart);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear11 = findViewById(R.id.linear11);
		listview1 = findViewById(R.id.listview1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear10 = findViewById(R.id.linear10);
		linear9 = findViewById(R.id.linear9);
		textview6 = findViewById(R.id.textview6);
		checkbox3 = findViewById(R.id.checkbox3);
		checkbox4 = findViewById(R.id.checkbox4);
		textview1 = findViewById(R.id.textview1);
		feesondelivey = findViewById(R.id.feesondelivey);
		textview12 = findViewById(R.id.textview12);
		textview5 = findViewById(R.id.textview5);
		checkbox1 = findViewById(R.id.checkbox1);
		checkbox2 = findViewById(R.id.checkbox2);
		textview3 = findViewById(R.id.textview3);
		feesonpay = findViewById(R.id.feesonpay);
		textview13 = findViewById(R.id.textview13);
		textview9 = findViewById(R.id.textview9);
		edittext1 = findViewById(R.id.edittext1);
		textview10 = findViewById(R.id.textview10);
		textview7 = findViewById(R.id.textview7);
		total = findViewById(R.id.total);
		textview14 = findViewById(R.id.textview14);
		materialbutton1 = findViewById(R.id.materialbutton1);
		linear12 = findViewById(R.id.linear12);
		imageview1 = findViewById(R.id.imageview1);
		linear13 = findViewById(R.id.linear13);
		textview11 = findViewById(R.id.textview11);
		cart = getSharedPreferences("cart", Activity.MODE_PRIVATE);
		information = getSharedPreferences("a", Activity.MODE_PRIVATE);
		
		checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox4.setChecked(!_isChecked);
				}
				if (!feesondelivarybool) {
					feesondelivary = 1;
					feesondelivarybool = true;
				}
				feesondelivey.setText(String.valueOf(feesondelivary));
			}
		});
		
		checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox3.setChecked(!_isChecked);
				}
				if (!feesondelivarybool) {
					feesondelivary = 1;
					feesondelivarybool = true;
				}
				feesondelivey.setText(String.valueOf(feesondelivary));
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox2.setChecked(!_isChecked);
				}
			}
		});
		
		checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox1.setChecked(!_isChecked);
				}
				if (!feesondelivarybool) {
					feesondelivary = 5;
					feesondelivarybool = true;
				}
				feesondelivey.setText(String.valueOf(feesondelivary));
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				str = _charSeq;
				v = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								//check coupon 
								textview10.setVisibility(View.VISIBLE);
								textview10.setText("");
							}
						});
					}
				};
				_timer.schedule(v, (int)(5000));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#E8EAF6")); }
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		feesondelivey.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		feesonpay.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		total.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview10.setVisibility(View.GONE);
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFFE8EAF6));
		listview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFFE8EAF6));
		if (!"".equals(cart.getString("cart", ""))) {
			map = new Gson().fromJson(cart.getString("cart", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			linear11.setVisibility(View.GONE);
			linear1.setVisibility(View.VISIBLE);
			listview1.setAdapter(new Listview1Adapter(map));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
		else {
			linear11.setVisibility(View.VISIBLE);
			linear1.setVisibility(View.GONE);
		}
		if (!"".equals(getIntent().getStringExtra("id")) && (!"".equals(getIntent().getStringExtra("name")) && (!"".equals(getIntent().getStringExtra("price")) && (!getIntent().getStringExtra("copy").equals("") && !"".equals(getIntent().getStringExtra("url")))))) {
			mapp = new HashMap<>();
			mapp.put("id", getIntent().getStringExtra("id"));
			mapp.put("name", getIntent().getStringExtra("name"));
			mapp.put("price", getIntent().getStringExtra("price"));
			mapp.put("copy", getIntent().getStringExtra("copy"));
			mapp.put("url", getIntent().getStringExtra("url"));
			map.add(mapp);
			mapp.clear();
			cart.edit().putString("cart", new Gson().toJson(map)).commit();
			map = new Gson().fromJson(cart.getString("cart", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			linear11.setVisibility(View.GONE);
			linear1.setVisibility(View.VISIBLE);
			listview1.setAdapter(new Listview1Adapter(map));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
		checkbox3.setChecked(true);
		if ((Double.parseDouble(information.getString("balance", "")) > Double.parseDouble(total.getText().toString())) && (Double.parseDouble(information.getString("balance", "")) == Double.parseDouble(total.getText().toString()))) {
			checkbox1.setChecked(true);
		}
		else {
			checkbox1.setEnabled(false);
			checkbox2.setChecked(true);
		}
	}
	
	@Override
	public void onBackPressed() {
		if (linear1.getVisibility() == View.VISIBLE) {
			cart.edit().putString("cart", new Gson().toJson(map)).commit();
		}
		eb.setClass(getApplicationContext(), ViewMainActivity.class);
		eb.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(eb);
		finish();
	}
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.view_caart, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final CheckBox mins = _view.findViewById(R.id.mins);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView copies = _view.findViewById(R.id.copies);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView boid = _view.findViewById(R.id.boid);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView bookname = _view.findViewById(R.id.bookname);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView price = _view.findViewById(R.id.price);
			
			boid.setText(map.get((int)_position).get("id").toString());
			bookname.setText(map.get((int)_position).get("name").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(map.get((int)_position).get("url").toString())).into(imageview1);
			price.setText(map.get((int)_position).get("price").toString());
			copies.setText(map.get((int)_position).get("copy").toString());
			mins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
					totalprivate = totalprivate + Double.parseDouble(price.getText().toString());
				}});
			Animation animation;
			animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
			animation.setDuration(1500); // Set the duration of the animation to 500 milliseconds
			linear1.startAnimation(animation); // Start the animation on the imageview
			
			return _view;
		}
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