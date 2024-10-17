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

public class EditbalanceActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double number = 0;
	private String balance = "";
	private HashMap<String, Object> ma = new HashMap<>();
	private String username = "";
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
	
	private Intent content = new Intent();
	private DatabaseReference information = _firebase.getReference("information");
	private ChildEventListener _information_child_listener;
	private SharedPreferences a;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editbalance);
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
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setHint(a.getString("email", ""));
				if (!edittext1.getText().toString().isEmpty() && !edittext2.getText().toString().isEmpty()) {
					    final String email = edittext1.getText().toString();  // Get email from the EditText
					    final String username = email.split("@")[0].replace(".", "_");  // Extract username and replace dots with underscores
					    
					    final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("information/" + username);  // Reference for user data
					    
					    // Listener to check and update user data
					    dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
						        @Override
						        public void onDataChange(DataSnapshot snapshot) {
							            if (snapshot.exists()) {
								                // Retrieve current balance
								                String balance = snapshot.child("balance").getValue(String.class);
								                
								                if (balance != null) {
									                    // Update the balance by adding the amount from edittext2
									                    double newBalance = Double.parseDouble(balance) + Double.parseDouble(edittext2.getText().toString());
									                    
									                    // Update the user's balance in the 'information' node
									                    Map<String, Object> updateData = new HashMap<>();
									                    updateData.put("email", email);
									                    updateData.put("balance", String.valueOf(newBalance));
									                    
									                    dataRef.setValue(updateData);  // Update Firebase with new balance
									                    
									                    // Construct the message for the 'history/username' node
									                    String historyMessage = "Email: " + email + ", Added Amount: " + edittext2.getText().toString() + ", New Balance: " + newBalance;
									                    
									                    // Prepare data for the 'history/username' node
									                    Map<String, Object> historyData = new HashMap<>();
									                    historyData.put("text", historyMessage);
									                    historyData.put("color", "green");  // Set the color to green for the balance update
									                    historyData.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));  // Add timestamp in the desired format
									                    
									                    // Save the history message under 'history/username'
									                    DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("history/" + username);
									                    historyRef.push().setValue(historyData);  // Push the history message
									       SketchwareUtil.showMessage(getApplicationContext(), " Balance Added !");  
									
									       }
								            }
							        }
						
						        @Override
						        public void onCancelled(DatabaseError databaseError) {
							            Log.e("FirebaseError", "Database error: " + databaseError.getMessage());
							        }
						    });
				} else {
					    Log.e("InputError", "Email or amount is empty");  // Handle empty inputs
				}
			}
		});
		
		_information_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (bo) {
					bo = false;
					content.setClass(getApplicationContext(), AdminActivity.class);
					content.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(content);
					finish();
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
		information.addChildEventListener(_information_child_listener);
	}
	
	private void initializeLogic() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)10, 0xFF9FA8DA, 0xFF3F51B5));
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
		content.setClass(getApplicationContext(), AdminActivity.class);
		content.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(content);
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