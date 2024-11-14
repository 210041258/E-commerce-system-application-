package ps.iut.projectsoftware;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;

public class MyfivouritActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private boolean item = false;
	
	private ArrayList<String> liet = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private TextView textview1;
	
	private Intent ocm = new Intent();
	private SharedPreferences favorite;
	private AlertDialog.Builder dialog;
	private TimerTask itemselection;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.myfivourit);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		favorite = getSharedPreferences("favorite", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		item = false;
		linear2.setVisibility(View.GONE);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#E8EAF6")); }
		listview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		dialog.setTitle("Select Type of Books ");
		dialog.setPositiveButton("Favorite", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				if (!favorite.getString("favorite", "").equals("")) {
					map = new Gson().fromJson(favorite.getString("favorite", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					listview1.setAdapter(new Listview1Adapter(map));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					if (0 == map.size()) {
						listview1.setVisibility(View.GONE);
						linear2.setVisibility(View.VISIBLE);
					}
				}
			}
		});
		dialog.setNeutralButton("Wishlist", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				if (!favorite.getString("wishlist", "").equals("")) {
					map = new Gson().fromJson(favorite.getString("wishlist", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					listview1.setAdapter(new Listview1Adapter(map));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					if (0 == map.size()) {
						listview1.setVisibility(View.GONE);
						linear2.setVisibility(View.VISIBLE);
					}
				}
			}
		});
		dialog.create().show();
		if (item) {
			linear2.setVisibility(View.VISIBLE);
			listview1.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onBackPressed() {
		ocm.setClass(getApplicationContext(), ProfileActivity.class);
		ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(ocm);
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
				_view = _inflater.inflate(R.layout.product, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout photo = _view.findViewById(R.id.photo);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView semester = _view.findViewById(R.id.semester);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final TextView price = _view.findViewById(R.id.price);
			
			if (!"".equals(_data.get((int)_position).get("id").toString())) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("url").toString())).into(imageview1);
				semester.setText(_data.get((int)_position).get("semester").toString());
				price.setText(_data.get((int)_position).get("price").toString());
				price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
				semester.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						ocm.setClass(getApplicationContext(), ViewProductActivity.class);
						ocm.putExtra("url", _data.get((int)_position).get("url").toString());
						ocm.putExtra("semester", _data.get((int)_position).get("semester").toString());
						ocm.putExtra("price", _data.get((int)_position).get("price").toString());
						ocm.putExtra("copy_preview", _data.get((int)_position).get("copy_preview").toString());
						ocm.putExtra("copies", _data.get((int)_position).get("copies").toString());
						ocm.putExtra("description", _data.get((int)_position).get("description").toString());
						ocm.putExtra("author", _data.get((int)_position).get("author").toString());
						ocm.putExtra("edition", _data.get((int)_position).get("edition").toString());
						ocm.putExtra("department", _data.get((int)_position).get("department").toString());
						ocm.putExtra("id", _data.get((int)_position).get("department").toString());
						ocm.putExtra("name", _data.get((int)_position).get("name").toString());
						ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(ocm);
						finish();
					}
				});
			}
			else {
				item = true;
			}
			
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