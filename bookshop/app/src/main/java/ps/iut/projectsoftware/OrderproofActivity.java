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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.util.regex.*;
import org.json.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrderproofActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private ArrayList<Map<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ImageView imageview1;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear4;
	private TextView textview2;
	private LinearLayout linear5;
	private TextView textview3;
	private LinearLayout linear6;
	private Button button1;
	
	private Intent intent = new Intent();
	private DatabaseReference contact = _firebase.getReference("contact_mission");
	private ChildEventListener _contact_child_listener;
	private SharedPreferences a;
	private SharedPreferences orders;
	private SharedPreferences history;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.orderproof);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		textview2 = findViewById(R.id.textview2);
		linear5 = findViewById(R.id.linear5);
		textview3 = findViewById(R.id.textview3);
		linear6 = findViewById(R.id.linear6);
		button1 = findViewById(R.id.button1);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		orders = getSharedPreferences("orders", Activity.MODE_PRIVATE);
		history = getSharedPreferences("history", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), MyorderActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});
		
		_contact_child_listener = new ChildEventListener() {
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
		contact.addChildEventListener(_contact_child_listener);
	}
	
	private void initializeLogic() {
		textview2.setText(getIntent().getStringExtra("orderno"));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#E8EAF6")); }
		String email = a.getString("email", "");
		String username = email.split("@")[0]; // Replace dots with underscores to make it Firebase-safe
		String email2 = email.replace("@", "_"). replace(".","_");
		// Get the order number from the intent (directly call it whenever needed)
		String orderno = getIntent().getStringExtra("orderno");
		if (orderno == null) {
				    // If the order number isn't in the intent, generate a default one
				    orderno = String.valueOf((int) (SketchwareUtil.getRandom(100, 10000)));
		}
		
		// Create the nested map for order details (under orderno)
		final Map<String, Object> orderDetails = new HashMap<>();
		orderDetails.put("orderid", getIntent().getStringExtra("orderid"));
		orderDetails.put("orderprice", getIntent().getStringExtra("orderprice")); // Ensure orderprice is in the intent
		
		// Firebase reference under "contact/username"
		final DatabaseReference ref = FirebaseDatabase.getInstance()
		                          .getReference()
		                          .child("contact")
		                          .child(username);  // Use the username as the key for contact
		
		// Fetch existing data from "contact/username" and update
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot dataSnapshot) {
						        Map<String, Object> existingData = new HashMap<>();
						
						        if (dataSnapshot.exists()) {
								            // Get the existing data as a map
								            existingData = (Map<String, Object>) dataSnapshot.getValue();
								        }
						
						        // If existingData is null, initialize it
						        if (existingData == null) {
								            existingData = new HashMap<>();
								        }
						
						        // Use getIntent().getStringExtra("orderno") directly in put()
						        existingData.put(getIntent().getStringExtra("orderno"), orderDetails);  // Use orderno directly
						        
						        // Update the data in Firebase
						        ref.updateChildren(existingData);
						    }
				
				    @Override
				    public void onCancelled(DatabaseError databaseError) {
						        // Handle possible errors
						        Log.e("FirebaseError", "Error while updating data: " + databaseError.getMessage());
						    }
		});
		
		// Firebase reference for myorders under "inter_user/email2/data/myorders"
		DatabaseReference myOrdersRef = FirebaseDatabase.getInstance()
		                           .getReference("inter_user/" + email2 + "/data/myorder");
		
		// Use push() to create a unique key for each myorder entry
		myOrdersRef.push().setValue(orderDetails);
		
		// Construct the message using the order number and price
		String orderMessage = "Order no. " + (getIntent().getStringExtra("orderno") != null ? getIntent().getStringExtra("orderno") : orderno) 
		                      + " Spent " + getIntent().getStringExtra("orderprice") + " Book id : " + getIntent().getStringExtra("orderid");
		
		// Prepare a single map to send to Firebase under the "history/username" path
		Map<String, Object> historyData = new HashMap<>();
		historyData.put("orderno", getIntent().getStringExtra("orderno"));  // Include orderno in the history map
		historyData.put("text", orderMessage);
		historyData.put("color", "red");  // Add color as "red" to the history map
		
		// Get the current date and time
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
		historyData.put("timestamp", currentDateTime);  // Add timestamp to historyData
		
		// Get a reference to the history path for the user (under "history/username")
		// Use push() to create a unique key for each history entry
		
		
		DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("inter_user/" + email2+"/data/history");
		historyRef.push().setValue(historyData);  // Use push() to add a unique key for each entry
		
	}
	
	@Override
	public void onBackPressed() {
		intent.setClass(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
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