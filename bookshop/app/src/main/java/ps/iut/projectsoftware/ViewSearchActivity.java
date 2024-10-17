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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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

public class ViewSearchActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> onit = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> searched_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> sorted = new ArrayList<>();
	
	private LinearLayout linear9;
	private LinearLayout linear8;
	private LinearLayout linear6;
	private LinearLayout linear10;
	private ListView listview1;
	private ImageView imageview3;
	private EditText edittext1;
	private TextView textview1;
	private Spinner spinner1;
	private ImageView imageview4;
	private LinearLayout linear11;
	private TextView tex;
	
	private Intent ocm = new Intent();
	private DatabaseReference book = _firebase.getReference("book");
	private ChildEventListener _book_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_search);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear9 = findViewById(R.id.linear9);
		linear8 = findViewById(R.id.linear8);
		linear6 = findViewById(R.id.linear6);
		linear10 = findViewById(R.id.linear10);
		listview1 = findViewById(R.id.listview1);
		imageview3 = findViewById(R.id.imageview3);
		edittext1 = findViewById(R.id.edittext1);
		textview1 = findViewById(R.id.textview1);
		spinner1 = findViewById(R.id.spinner1);
		imageview4 = findViewById(R.id.imageview4);
		linear11 = findViewById(R.id.linear11);
		tex = findViewById(R.id.tex);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ("".equals(edittext1.getText().toString())) {
					listview1.setVisibility(View.GONE);
					linear10.setVisibility(View.VISIBLE);
				}
				else {
					final String searchKey = getIntent().getStringExtra("key").toLowerCase(); // Search Key
					
					// Perform the search first
					book.addListenerForSingleValueEvent(new ValueEventListener() {
						    @Override
						    public void onDataChange(DataSnapshot _dataSnapshot) {
							        sorted = new ArrayList<>();
							        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								            HashMap<String, Object> _map = (HashMap<String, Object>) _data.getValue();
								
								            // Convert values to lowercase for case-insensitive searching
								            
								            String name = (_map.get("name") != null) ? _map.get("name").toString().toLowerCase() : "";
								            String id = (_map.get("id") != null) ? _map.get("id").toString().toLowerCase() : ""; // Retrieve and lowercase ID
								            String department = (_map.get("department") != null) ? _map.get("department").toString().toLowerCase() : "";
								
								            // Add to sorted list if search key is found in name, description, department, or id
								            if ( name.contains(searchKey) || department.contains(searchKey) || id.contains(searchKey)) {
									                sorted.add(_map);
									            }
								        }
							
							        // Check if sorted list is not empty
							        if (sorted.size() > 0) {
								            // Set adapter and update the list initially with unsorted data
								            listview1.setAdapter(new Listview1Adapter(sorted));
								            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
								
								            // Sort the list by semester ascending by default
								            Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
									                @Override
									                public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
										                    // Assuming "semester" is stored as a String and we need to parse it to an Integer
										                    int semesterA;
										                    int semesterB;
										
										                    // Attempt to parse semester values as integers
										                    try {
											                        semesterA = Integer.parseInt(a.get("semester").toString());
											                    } catch (NumberFormatException e) {
											                        semesterA = 0; // Default value for parsing error
											                    }
										
										                    try {
											                        semesterB = Integer.parseInt(b.get("semester").toString());
											                    } catch (NumberFormatException e) {
											                        semesterB = 0; // Default value for parsing error
											                    }
										
										                    // Compare the numeric values
										                    return Integer.compare(semesterA, semesterB);
										                }
									            });
								
								            // Notify the adapter after sorting
								            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
								        } else {
								            // Handle empty search result
								            linear10.setVisibility(View.VISIBLE);
								            listview1.setVisibility(View.GONE);
								        }
							    }
						
						    @Override
						    public void onCancelled(DatabaseError _databaseError) {
							        // Handle Firebase error
							        linear10.setVisibility(View.VISIBLE);
							        listview1.setVisibility(View.GONE);
							        Toast.makeText(getApplicationContext(), "Database error: " + _databaseError.getMessage(), Toast.LENGTH_SHORT).show();
							    }
					});
					listview1.setVisibility(View.VISIBLE);
					linear10.setVisibility(View.GONE);
				}
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) { // Sort by semester (first to eighth)
					    Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
						        @Override
						        public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
							            // Directly parse semester values to integers
							            int semesterA = Integer.parseInt(a.get("semester").toString());
							            int semesterB = Integer.parseInt(b.get("semester").toString());
							            return Integer.compare(semesterA, semesterB);
							        }
						    });
				} else if (_position == 1) { // Sort by price (low to high)
					    Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
						        @Override
						        public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
							            return Double.compare(
							                Double.parseDouble(a.get("price").toString()),
							                Double.parseDouble(b.get("price").toString())
							            );
							        }
						    });
				} else if (_position == 2) { // Sort by semester (eighth to first)
					    Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
						        @Override
						        public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
							            // Directly parse semester values to integers
							            int semesterA = Integer.parseInt(a.get("semester").toString());
							            int semesterB = Integer.parseInt(b.get("semester").toString());
							            return Integer.compare(semesterB, semesterA);
							        }
						    });
				} else if (_position == 3) { // Sort by price (high to low)
					    Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
						        @Override
						        public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
							            return Double.compare(
							                Double.parseDouble(b.get("price").toString()),
							                Double.parseDouble(a.get("price").toString())
							            );
							        }
						    });
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		_book_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		book.addChildEventListener(_book_child_listener);
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		str.add("First to Eight Semster ");
		str.add("Low to High Price");
		str.add("Eight to First Semster ");
		str.add("High to Low price");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, str));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
		linear10.setVisibility(View.GONE);
		tex.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		linear6.setElevation((float)25);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFF000000, 0xFF9FA8DA));
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFF000000, 0xFFE8EAF6));
		linear8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFF000000, 0xFFE8EAF6));
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFF000000, 0xFFE8EAF6));
		listview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFF000000, 0xFFE8EAF6));
		edittext1.setSingleLine(true);
		edittext1.setText(getIntent().getStringExtra("key"));
		if ("".equals(edittext1.getText().toString())) {
			linear10.setVisibility(View.VISIBLE);
			listview1.setVisibility(View.GONE);
		}
		else {
			final String searchKey = getIntent().getStringExtra("key").toLowerCase(); // Search Key
			
			// Perform the search first
			book.addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot _dataSnapshot) {
					        sorted = new ArrayList<>();
					        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						            HashMap<String, Object> _map = (HashMap<String, Object>) _data.getValue();
						
						            // Convert values to lowercase for case-insensitive searching
						            
						            String name = (_map.get("name") != null) ? _map.get("name").toString().toLowerCase() : "";
						            String id = (_map.get("id") != null) ? _map.get("id").toString().toLowerCase() : ""; // Retrieve and lowercase ID
						            String department = (_map.get("department") != null) ? _map.get("department").toString().toLowerCase() : "";
						
						            // Add to sorted list if search key is found in name, description, department, or id
						            if ( name.contains(searchKey) || department.contains(searchKey) || id.contains(searchKey)) {
							                sorted.add(_map);
							            }
						        }
					
					        // Check if sorted list is not empty
					        if (sorted.size() > 0) {
						            // Set adapter and update the list initially with unsorted data
						            listview1.setAdapter(new Listview1Adapter(sorted));
						            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
						
						            // Sort the list by semester ascending by default
						            Collections.sort(sorted, new Comparator<HashMap<String, Object>>() {
							                @Override
							                public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
								                    // Assuming "semester" is stored as a String and we need to parse it to an Integer
								                    int semesterA;
								                    int semesterB;
								
								                    // Attempt to parse semester values as integers
								                    try {
									                        semesterA = Integer.parseInt(a.get("semester").toString());
									                    } catch (NumberFormatException e) {
									                        semesterA = 0; // Default value for parsing error
									                    }
								
								                    try {
									                        semesterB = Integer.parseInt(b.get("semester").toString());
									                    } catch (NumberFormatException e) {
									                        semesterB = 0; // Default value for parsing error
									                    }
								
								                    // Compare the numeric values
								                    return Integer.compare(semesterA, semesterB);
								                }
							            });
						
						            // Notify the adapter after sorting
						            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
						        } else {
						            // Handle empty search result
						            linear10.setVisibility(View.VISIBLE);
						            listview1.setVisibility(View.GONE);
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError _databaseError) {
					        // Handle Firebase error
					        linear10.setVisibility(View.VISIBLE);
					        listview1.setVisibility(View.GONE);
					        Toast.makeText(getApplicationContext(), "Database error: " + _databaseError.getMessage(), Toast.LENGTH_SHORT).show();
					    }
			});
			linear10.setVisibility(View.GONE);
			listview1.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onBackPressed() {
		ocm.setClass(getApplicationContext(), ViewMainActivity.class);
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
			
			Animation animation;
			animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
			animation.setDuration(900); // Set the duration of the animation to 500 milliseconds
			linear1.startAnimation(animation); 
			
			// Setting text and loading image for the selected item
			semester.setText(sorted.get((int) _position).get("semester").toString());
			
			
			price.setText(sorted.get((int) _position).get("price").toString());
			
			
			
			
			// Set onClickListener for the image view
			imageview1.setOnClickListener(new View.OnClickListener() {
				    @Override
				    public void onClick(View _view) {
					        Intent ocm = new Intent(getApplicationContext(), ViewProductActivity.class);
					        // Put extra data to the next activity
					        ocm.putExtra("id", sorted.get((int) _position).get("id").toString());
					        ocm.putExtra("name", sorted.get((int) _position).get("name").toString());
					        ocm.putExtra("description", sorted.get((int) _position).get("description").toString());
					        ocm.putExtra("url", sorted.get((int) _position).get("url").toString()); 
					
					ocm.putExtra("copies", sorted.get((int) _position).get("copies").toString()); 
					
					
					        ocm.putExtra("price", sorted.get((int) _position).get("price").toString());
					        ocm.putExtra("copies", sorted.get((int) _position).get("copies").toString());
					        ocm.putExtra("department", sorted.get((int) _position).get("department").toString());
					        ocm.putExtra("semester", sorted.get((int) _position).get("semester").toString());
					        
					        // Clear the activity stack before starting the new activity
					        ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					        
					        // Start the new activity
					        startActivity(ocm);
					        finish(); // Finish the current activity
					    }
			});
			Glide.with(getApplicationContext()).load(Uri.parse(sorted.get((int)_position).get("url").toString())).into(imageview1);
			semester.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			imageview1.setElevation((float)10);
			
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