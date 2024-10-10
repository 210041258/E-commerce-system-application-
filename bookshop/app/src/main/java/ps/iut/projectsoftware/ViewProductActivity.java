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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.material.button.*;
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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ViewProductActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double num = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String email = "";
	private double copies = 0;
	private String com = "";
	private String id = "";
	
	private LinearLayout linear9;
	private ScrollView vscroll1;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private LinearLayout photo;
	private LinearLayout linear25;
	private LinearLayout linear11;
	private LinearLayout linear13;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private LinearLayout linear22;
	private TextView textview9;
	private TextView description;
	private ImageView imageview8;
	private ImageView imageview7;
	private TextView textview14;
	private TextView textview4;
	private LinearLayout linear14;
	private ImageView imageview5;
	private TextView textview5;
	private ImageView imageview6;
	private MaterialButton materialbutton1;
	private MaterialButton materialbutton2;
	private TextView textview1;
	private TextView title;
	private TextView textview2;
	private TextView price;
	private TextView textview11;
	private TextView semester;
	private TextView textview13;
	private TextView department;
	
	private SharedPreferences a;
	private Intent move = new Intent();
	private SharedPreferences cart;
	private DatabaseReference information = _firebase.getReference("information");
	private ChildEventListener _information_child_listener;
	private DatabaseReference book = _firebase.getReference("book");
	private ChildEventListener _book_child_listener;
	private TimerTask refresh;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_product);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear9 = findViewById(R.id.linear9);
		vscroll1 = findViewById(R.id.vscroll1);
		linear10 = findViewById(R.id.linear10);
		linear12 = findViewById(R.id.linear12);
		photo = findViewById(R.id.photo);
		linear25 = findViewById(R.id.linear25);
		linear11 = findViewById(R.id.linear11);
		linear13 = findViewById(R.id.linear13);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear23 = findViewById(R.id.linear23);
		linear24 = findViewById(R.id.linear24);
		linear22 = findViewById(R.id.linear22);
		textview9 = findViewById(R.id.textview9);
		description = findViewById(R.id.description);
		imageview8 = findViewById(R.id.imageview8);
		imageview7 = findViewById(R.id.imageview7);
		textview14 = findViewById(R.id.textview14);
		textview4 = findViewById(R.id.textview4);
		linear14 = findViewById(R.id.linear14);
		imageview5 = findViewById(R.id.imageview5);
		textview5 = findViewById(R.id.textview5);
		imageview6 = findViewById(R.id.imageview6);
		materialbutton1 = findViewById(R.id.materialbutton1);
		materialbutton2 = findViewById(R.id.materialbutton2);
		textview1 = findViewById(R.id.textview1);
		title = findViewById(R.id.title);
		textview2 = findViewById(R.id.textview2);
		price = findViewById(R.id.price);
		textview11 = findViewById(R.id.textview11);
		semester = findViewById(R.id.semester);
		textview13 = findViewById(R.id.textview13);
		department = findViewById(R.id.department);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		cart = getSharedPreferences("cart", Activity.MODE_PRIVATE);
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(num == 1)) {
					num--;
					textview5.setText(String.valueOf((long)(num)));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Can't be less One !");
				}
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Double.parseDouble(getIntent().getStringExtra("copies")) > num) {
					num++;
					textview5.setText(String.valueOf((long)(num)));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Can't be more than our Inventory !");
				}
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear11.getVisibility() == View.VISIBLE && (Double.parseDouble(getIntent().getStringExtra("price")) < Double.parseDouble(a.getString("balance", "")))) {
					    // Get the current price and balance as Double
					    double price = Double.parseDouble(getIntent().getStringExtra("price"));
					    double balance = Double.parseDouble(a.getString("balance", ""));
					    
					    // Retrieve the number of copies from textview5
					    String numStr = textview5.getText().toString();  // Get the number of copies directly from textview5
					    final int num;  // Declare num as final
					
					    // Check if numStr is not null or empty and parse it
					    if (numStr != null && !numStr.isEmpty()) {
						        num = Integer.parseInt(numStr);
						    } else {
						        SketchwareUtil.showMessage(getApplicationContext(), "Number of copies not provided.");
						        return;  // Exit if num is null or empty
						    }
					
					    // Calculate the total price for all copies
					    double totalPrice = price * num;  // Use num instead of currentCopies
					
					    // Subtract the total price from the balance and update shared preferences
					    double updatedBalance = balance - totalPrice;
					    a.edit().putString("balance", String.valueOf(updatedBalance)).commit();
					    
					    email = a.getString("email", "");
					
					    final String username = email.split("@")[0];  // Username is final
					
					    final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("information/" + username);
					
					    // Update user information in Firebase
					    dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
						        @Override
						        public void onDataChange(DataSnapshot snapshot) {
							            if (snapshot.exists()) {
								                // Retrieve balance as a String, then parse it as Double
								                String balanceStr = snapshot.child("balance").getValue(String.class);
								
								                if (balanceStr != null) {
									                    Double balance = Double.parseDouble(balanceStr);  // Convert to Double
									
									                    Map<String, Object> updateData = new HashMap<>();
									                    updateData.put("email", email);
									                    updateData.put("balance", a.getString("balance", ""));  // Keep balance as String for shared preferences
									
									                    // Update the balance in Firebase
									                    dataRef.setValue(updateData);
									                } else {
									                    SketchwareUtil.showMessage(getApplicationContext(), "Customer Center.");
									                }
								            } else {
								                SketchwareUtil.showMessage(getApplicationContext(), "No data found. Please check with Customer Center.");
								            }
							        }
						
						        @Override
						        public void onCancelled(DatabaseError error) {
							            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
							        }
						    });
					
					    // Update book copies
					    String bookId = getIntent().getStringExtra("id"); // Get book ID from intent
					
					    final DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference("book").child(bookId);
					
					    // Listener to decrease book copies in Firebase
					    bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
						        @Override
						        public void onDataChange(DataSnapshot snapshot) {
							            if (snapshot.exists()) {
								                // Retrieve copies as a String, then parse it as Integer
								                String copiesStr = snapshot.child("copies").getValue(String.class);
								
								                if (copiesStr != null) {
									                    Integer copiesInFirebase = Integer.parseInt(copiesStr);  // Convert to Integer
									
									                    int newCopies = copiesInFirebase - num;  // Subtract num (the number of copies the user wants)
									
									                    // Ensure that newCopies is not negative
									                    if (newCopies < 0) {
										                        newCopies = 0;
										                    }
									
									                    // Update the new copies count in Firebase
									                    bookRef.child("copies").setValue(String.valueOf(newCopies));  // Save back as String
									                } else {
									                    SketchwareUtil.showMessage(getApplicationContext(), "No copies data available in Firebase.");
									                }
								            } else {
								                SketchwareUtil.showMessage(getApplicationContext(), "Book not found in Firebase.");
								            }
							        }
						
						        @Override
						        public void onCancelled(DatabaseError error) {
							            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
							        }
						    });
					
					    // Proceed to the next activity after updating the balance and book copies
					    Intent move = new Intent(getApplicationContext(), OrderproofActivity.class);
					    move.putExtra("orderid", getIntent().getStringExtra("id"));
					    move.putExtra("orderno", String.valueOf((int) (SketchwareUtil.getRandom(100, 10000))));
					
					    // Add the total price to the move intent
					    move.putExtra("orderprice", String.valueOf(totalPrice));  // Pass the total price
					
					    move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					    startActivity(move);
					    finish();
					
				} else {
					    SketchwareUtil.showMessage(getApplicationContext(), "Check Balance!!");
				}
			}
		});
		
		materialbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setClass(getApplicationContext(), ViewCartActivity.class);
				move.putExtra("id", getIntent().getStringExtra("id"));
				move.putExtra("name", getIntent().getStringExtra("name"));
				move.putExtra("url", getIntent().getStringExtra("url"));
				move.putExtra("price", String.valueOf((long)(Double.parseDouble(textview5.getText().toString()) * Double.parseDouble(getIntent().getStringExtra("price")))));
				move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(move);
				finish();
			}
		});
		
		_information_child_listener = new ChildEventListener() {
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
		information.addChildEventListener(_information_child_listener);
		
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
		final int num =1;
		
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		materialbutton1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("url"))).into(imageview8);
		description.setText(getIntent().getStringExtra("description"));
		title.setText(getIntent().getStringExtra("name"));
		price.setText(getIntent().getStringExtra("price"));
		semester.setText(getIntent().getStringExtra("semester"));
		department.setText(getIntent().getStringExtra("department"));
		imageview8.setElevation((float)25);
		if (0 < Double.parseDouble(getIntent().getStringExtra("copies"))) {
			linear11.setVisibility(View.VISIBLE);
			linear25.setVisibility(View.GONE);
		}
		else {
			linear25.setVisibility(View.VISIBLE);
			linear11.setVisibility(View.GONE);
			linear13.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onBackPressed() {
		move.setClass(getApplicationContext(), ViewMainActivity.class);
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