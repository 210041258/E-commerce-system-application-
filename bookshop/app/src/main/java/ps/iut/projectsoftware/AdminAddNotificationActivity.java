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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.util.regex.*;
import org.json.*;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AdminAddNotificationActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private boolean bo = false;
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private TextView textview1;
	private LinearLayout linear5;
	private TextView textview2;
	private EditText edittext1;
	private LinearLayout linear9;
	private TextView textview3;
	private EditText edittext2;
	private LinearLayout linear6;
	private MaterialButton materialbutton1;
	
	private Intent books_intiligance = new Intent();
	private DatabaseReference db_notification = _firebase.getReference("notification");
	private ChildEventListener _db_notification_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_add_notification);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		linear5 = findViewById(R.id.linear5);
		textview2 = findViewById(R.id.textview2);
		edittext1 = findViewById(R.id.edittext1);
		linear9 = findViewById(R.id.linear9);
		textview3 = findViewById(R.id.textview3);
		edittext2 = findViewById(R.id.edittext2);
		linear6 = findViewById(R.id.linear6);
		materialbutton1 = findViewById(R.id.materialbutton1);
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!"".equals(edittext1.getText().toString()) && !edittext2.getText().toString().equals("")) {
					map = new HashMap<>();
					map.put("message".concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(1000000000))))), edittext2.getText().toString());
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a dd-MM-yyyy", Locale.getDefault());
					String currentTimeDate = sdf.format(new Date());
					
					map.put("time", currentTimeDate);
					
					
					String email = edittext1.getText().toString();
					
					String message =edittext2.getText().toString();
					
					
					String username = email.split("@")[0];  
					
					
					
					
					// Use the username as the key in Firebase under the 'notification' path
					DatabaseReference ref = FirebaseDatabase.getInstance()
					                          .getReference()
					                          .child("notification")
					                          .child(username);  // Use the username as the key
					
					// Update Firebase
					ref.updateChildren(map);
					bo = true;
					map.clear();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Check !!");
				}
			}
		});
		
		_db_notification_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (bo) {
					SketchwareUtil.showMessage(getApplicationContext(), "Notification Send Successfully 😀");
					bo = false;
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
		db_notification.addChildEventListener(_db_notification_child_listener);
	}
	
	private void initializeLogic() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFF9FA8DA, 0xFF3F51B5));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)10, 0xFF9FA8DA, 0xFFE8EAF6));
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)10, 0xFF9FA8DA, 0xFFE8EAF6));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		
		Animation animation;
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
		animation.setDuration(700); // Set the duration of the animation to 500 milliseconds
		edittext1.startAnimation(animation); // Start the animation on the imageview
		
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
		animation.setDuration(700); // Set the duration of the animation to 500 milliseconds
		edittext2.startAnimation(animation); // Start the animation on the imageview
		bo = false;
	}
	
	@Override
	public void onBackPressed() {
		books_intiligance.setClass(getApplicationContext(), AdminActivity.class);
		books_intiligance.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(books_intiligance);
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