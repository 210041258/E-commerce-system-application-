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

public class OrderproofActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	
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
		String Email = a.getString("email", "");
		String key = Email.split("@")[0];
		
		// Get the order number
		String orderno = getIntent().getStringExtra("orderno");
		
		// Create the nested map for order details (under orderno)
		Map<String, Object> orderDetails = new HashMap<>();
		orderDetails.put("orderid", getIntent().getStringExtra("orderid"));
		orderDetails.put("orderprice", getIntent().getStringExtra("orderprice"));
		
		// Create the main map
		final Map<String, Object> map = new HashMap<>();
		map.put("email", Email);
		map.put(orderno, orderDetails); // ordno as key with nested details
		
		// Firebase reference
		final DatabaseReference ref = FirebaseDatabase.getInstance()
		                          .getReference()
		                          .child("contact")
		                          .child(key);
		
		// Fetch existing data and update
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			    @Override
			    public void onDataChange(DataSnapshot dataSnapshot) {
				        if (dataSnapshot.exists()) {
					            // Get the existing map
					            Map<String, Object> existingData = (Map<String, Object>) dataSnapshot.getValue();
					            // Merge existing data with new data
					            existingData.putAll(map);
					            
					            // Update the data in Firebase
					            ref.updateChildren(existingData);
					        } else {
					            // If it doesn't exist, just update with the new data
					            ref.updateChildren(map);
					        }
				    }
			
			    @Override
			    public void onCancelled(DatabaseError databaseError) {
				        // Handle possible errors.
				        Log.e("FirebaseError", "Error while updating data: " + databaseError.getMessage());
				    }
		});
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