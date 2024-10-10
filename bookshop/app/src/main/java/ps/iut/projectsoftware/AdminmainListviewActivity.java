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
import android.view.View;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class AdminmainListviewActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private HashMap<String, Object> map_info = new HashMap<>();
	private boolean v = false;
	private HashMap<String, Object> map = new HashMap<>();
	private String searched_key = "";
	
	private ArrayList<HashMap<String, Object>> temp = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView manager;
	private ListView coupon;
	private ListView stuff;
	private ListView book;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview1;
	private LinearLayout linear6;
	
	private Intent image = new Intent();
	private AlertDialog.Builder vieworder;
	private AlertDialog.Builder delete_confirm;
	private DatabaseReference b = _firebase.getReference("book");
	private ChildEventListener _b_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.adminmain_listview);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		manager = findViewById(R.id.manager);
		coupon = findViewById(R.id.coupon);
		stuff = findViewById(R.id.stuff);
		book = findViewById(R.id.book);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		linear6 = findViewById(R.id.linear6);
		vieworder = new AlertDialog.Builder(this);
		delete_confirm = new AlertDialog.Builder(this);
		
		book.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vieworder.setTitle("Select Order : ");
				vieworder.setNeutralButton("History", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						image.setClass(getApplicationContext(), AdminMainHistoryActivity.class);
						if (coupon.getVisibility() == View.VISIBLE) {
							image.putExtra("key", "coupon");
						}
						else {
							if (stuff.getVisibility() == View.VISIBLE) {
								image.putExtra("key", "stuff");
							}
							else {
								if (manager.getVisibility() == View.VISIBLE) {
									image.putExtra("key", "manager");
								}
								else {
									image.putExtra("key", "book");
								}
							}
						}
						image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(image);
						finish();
					}
				});
				vieworder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						image.setClass(getApplicationContext(), AdminMainAddActivity.class);
						if (coupon.getVisibility() == View.VISIBLE) {
							image.putExtra("key", "coupon");
						}
						else {
							if (stuff.getVisibility() == View.VISIBLE) {
								image.putExtra("key", "stuff");
							}
							else {
								if (manager.getVisibility() == View.VISIBLE) {
									image.putExtra("key", "manager");
								}
								else {
									image.putExtra("key", "book");
								}
							}
						}
						image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(image);
						finish();
					}
				});
				vieworder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				vieworder.create().show();
			}
		});
		
		_b_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				b.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						temp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								temp.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(temp));
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
				b.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						temp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								temp.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(temp));
						((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (0 == temp.size()) {
					book.setVisibility(View.GONE);
					linear2.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (v) {
					SketchwareUtil.showMessage(getApplicationContext(), "Successfully Deleted 👍🏽");
					v = false;
					image.setClass(getApplicationContext(), AdminmainListviewActivity.class);
					image.putExtra("key", "book");
					image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(image);
					finish();
				}
				b.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						temp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								temp.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						book.setAdapter(new BookAdapter(temp));
						((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (0 == temp.size()) {
					book.setVisibility(View.GONE);
					linear2.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				book.setVisibility(View.GONE);
				linear3.setVisibility(View.VISIBLE);
				SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		b.addChildEventListener(_b_child_listener);
	}
	
	private void initializeLogic() {
		v = false;
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
					b.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							temp = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									temp.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							book.setAdapter(new BookAdapter(temp));
							((BaseAdapter)book.getAdapter()).notifyDataSetChanged();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
		}
		book.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFF9FA8DA, 0xFF3F51B5));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFF9FA8DA, 0xFF3F51B5));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
	}
	
	@Override
	public void onBackPressed() {
		image.setClass(getApplicationContext(), AdminActivity.class);
		image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(image);
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
			
			id.setText(temp.get((int)_position).get("id").toString());
			name.setText(temp.get((int)_position).get("name").toString());
			price.setText(temp.get((int)_position).get("price").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(temp.get((int)_position).get("url").toString())).into(imageview1);
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					vieworder.setTitle("Select Order : ");
					vieworder.setNeutralButton("History", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							image.setClass(getApplicationContext(), AdminMainHistoryActivity.class);
							image.putExtra("key", "book");
							image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(image);
							finish();
						}
					});
					vieworder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							image.setClass(getApplicationContext(), AdminMainEditActivity.class);
							image.putExtra("id", temp.get((int)_position).get("id").toString());
							image.putExtra("name", temp.get((int)_position).get("name").toString());
							image.putExtra("description", temp.get((int)_position).get("description").toString());
							image.putExtra("url", temp.get((int)_position).get("url").toString());
							image.putExtra("price", temp.get((int)_position).get("price").toString());
							image.putExtra("copies", temp.get((int)_position).get("copies").toString());
							image.putExtra("department", temp.get((int)_position).get("department").toString());
							image.putExtra("semester", temp.get((int)_position).get("semester").toString());
							image.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(image);
							finish();
						}
					});
					vieworder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							delete_confirm.setTitle("Confirm Delete :");
							delete_confirm.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									map_info = new HashMap<>();
									map_info.put("id", temp.get((int)_position).get("id").toString());
									map_info.put("name", temp.get((int)_position).get("name").toString());
									map_info.put("copies", temp.get((int)_position).get("copies").toString());
									map_info.put("department", temp.get((int)_position).get("department").toString());
									map_info.put("price", temp.get((int)_position).get("price").toString());
									map_info.put("url", temp.get((int)_position).get("url").toString());
									map_info.put("description", temp.get((int)_position).get("description").toString());
									map_info.put("semester", temp.get((int)_position).get("semester").toString());
									
									String key = id.getText().toString();
									
									DatabaseReference ref = FirebaseDatabase.getInstance()
									                          .getReference()
									                          .child("delete_book")
									                          .child(key);
									ref.updateChildren(map_info);
									map_info.clear();
									b.child(temp.get((int)_position).get("id").toString()).removeValue();
								}
							});
							delete_confirm.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							delete_confirm.create().show();
						}
					});
					vieworder.create().show();
				}
			});
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			
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