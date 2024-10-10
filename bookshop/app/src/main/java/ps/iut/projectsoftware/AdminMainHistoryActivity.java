package ps.iut.projectsoftware;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class AdminMainHistoryActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private boolean d = false;
	private boolean c = false;
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView manager;
	private ListView coupon;
	private ListView stuff;
	private ListView book;
	private LinearLayout linear3;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview1;
	private LinearLayout linear4;
	
	private Intent back = new Intent();
	private AlertDialog.Builder vieworder;
	private DatabaseReference returnbook = _firebase.getReference("book");
	private ChildEventListener _returnbook_child_listener;
	private DatabaseReference bookdelete = _firebase.getReference("delete_book");
	private ChildEventListener _bookdelete_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_main_history);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		manager = findViewById(R.id.manager);
		coupon = findViewById(R.id.coupon);
		stuff = findViewById(R.id.stuff);
		book = findViewById(R.id.book);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		vieworder = new AlertDialog.Builder(this);
		
		_returnbook_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (d) {
					d = false;
					SketchwareUtil.showMessage(getApplicationContext(), "Successfully Restored 👍🏽");
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		returnbook.addChildEventListener(_returnbook_child_listener);
		
		_bookdelete_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				bookdelete.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(list));
						((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				bookdelete.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(list));
						((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (c) {
					SketchwareUtil.showMessage(getApplicationContext(), "Successfully Deleted 👍🏽  ");
					c = false;
				}
				bookdelete.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(list));
						((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (0 == list.size()) {
					book.setVisibility(View.GONE);
					linear2.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		bookdelete.addChildEventListener(_bookdelete_child_listener);
	}
	
	private void initializeLogic() {
		d = false;
		c = false;
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		stuff.setVisibility(View.GONE);
		manager.setVisibility(View.GONE);
		coupon.setVisibility(View.GONE);
		book.setVisibility(View.GONE);
		linear2.setVisibility(View.GONE);
		if ("staff".equals(getIntent().getStringExtra("key"))) {
			stuff.setVisibility(View.VISIBLE);
		}
		else {
			if ("manager".equals(getIntent().getStringExtra("key"))) {
				manager.setVisibility(View.VISIBLE);
			}
			else {
				if ("coupon".equals(getIntent().getStringExtra("key"))) {
					coupon.setVisibility(View.VISIBLE);
				}
				else {
					book.setVisibility(View.VISIBLE);
					bookdelete.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							list = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									list.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							book.setAdapter(new BookAdapter(list));
							((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
		}
		coupon.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		manager.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		stuff.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		book.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
	}
	
	@Override
	public void onBackPressed() {
		back.setClass(getApplicationContext(), AdminmainListviewActivity.class);
		back.putExtra("key", "book");
		back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(back);
		finish();
	}
	public class ManagerAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public ManagerAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.admin_manager, null);
			}
			
			return _view;
		}
	}
	
	public class CouponAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public CouponAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.admin_cuppon, null);
			}
			
			return _view;
		}
	}
	
	public class StuffAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public StuffAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.admin_staff, null);
			}
			
			return _view;
		}
	}
	
	public class BookAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public BookAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.admin_book, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView id = _view.findViewById(R.id.id);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView price = _view.findViewById(R.id.price);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final TextView name = _view.findViewById(R.id.name);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			id.setText(list.get((int)_position).get("id").toString());
			name.setText(list.get((int)_position).get("name").toString());
			price.setText(list.get((int)_position).get("price").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(list.get((int)_position).get("url").toString())).into(imageview1);
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					vieworder.setTitle("What do you Want to Restore ?");
					vieworder.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							map = new HashMap<>();
							map.put("id", list.get((int)_position).get("id").toString());
							map.put("name", list.get((int)_position).get("name").toString());
							map.put("description", list.get((int)_position).get("description").toString());
							map.put("copies", list.get((int)_position).get("copies").toString());
							map.put("department", list.get((int)_position).get("department").toString());
							map.put("price", list.get((int)_position).get("price").toString());
							map.put("url", list.get((int)_position).get("url").toString());
							map.put("semester", list.get((int)_position).get("semester").toString());
							
							String key = list.get((int)_position).get("id").toString();
							
							DatabaseReference ref = FirebaseDatabase.getInstance()
							                          .getReference()
							                          .child("book")
							                          .child(key);
							ref.updateChildren(map);
							bookdelete.child(list.get((int)_position).get("id").toString()).removeValue();
							map.clear();
						}
					});
					vieworder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							bookdelete.child(list.get((int)_position).get("id").toString()).removeValue();
						}
					});
					vieworder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					vieworder.create().show();
				}
			});
			
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