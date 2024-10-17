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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class SginupActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double times = 0;
	private boolean block = false;
	private boolean bool = false;
	private boolean registered = false;
	private HashMap<String, Object> map = new HashMap<>();
	
	private LinearLayout linear7;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private ImageView imageview1;
	private EditText edittext1;
	private EditText edittext2;
	private EditText edittext3;
	private LinearLayout linear6;
	private LinearLayout linear8;
	private MaterialButton b;
	private LinearLayout linear5;
	private Button b1;
	private CheckBox checkbox1;
	
	private Intent a = new Intent();
	private SharedPreferences blocbk;
	private FirebaseAuth g;
	private OnCompleteListener<AuthResult> _g_create_user_listener;
	private OnCompleteListener<AuthResult> _g_sign_in_listener;
	private OnCompleteListener<Void> _g_reset_password_listener;
	private OnCompleteListener<Void> g_updateEmailListener;
	private OnCompleteListener<Void> g_updatePasswordListener;
	private OnCompleteListener<Void> g_emailVerificationSentListener;
	private OnCompleteListener<Void> g_deleteUserListener;
	private OnCompleteListener<Void> g_updateProfileListener;
	private OnCompleteListener<AuthResult> g_phoneAuthListener;
	private OnCompleteListener<AuthResult> g_googleSignInListener;
	
	private TimerTask file;
	private DatabaseReference information = _firebase.getReference("information");
	private ChildEventListener _information_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.sginup);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear7 = findViewById(R.id.linear7);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		edittext1 = findViewById(R.id.edittext1);
		edittext2 = findViewById(R.id.edittext2);
		edittext3 = findViewById(R.id.edittext3);
		linear6 = findViewById(R.id.linear6);
		linear8 = findViewById(R.id.linear8);
		b = findViewById(R.id.b);
		linear5 = findViewById(R.id.linear5);
		b1 = findViewById(R.id.b1);
		checkbox1 = findViewById(R.id.checkbox1);
		blocbk = getSharedPreferences("a", Activity.MODE_PRIVATE);
		g = FirebaseAuth.getInstance();
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (true && ((edittext2.getText().toString().length() > 5) && (!block && edittext2.getText().toString().equals(edittext3.getText().toString())))) {
					b.setEnabled(false);
					g.createUserWithEmailAndPassword(edittext1.getText().toString().trim(), edittext2.getText().toString().trim()).addOnCompleteListener(SginupActivity.this, _g_create_user_listener);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Check the Given ✉️ and 🔑 and 📃!!");
					times++;
					edittext3.requestFocus();
					edittext2.requestFocus();
					edittext1.requestFocus();
					checkbox1.requestFocus();
				}
				if (times == 15) {
					blocbk.edit().putString("block_reg", "1").commit();
					block = true;
					SketchwareUtil.showMessage(getApplicationContext(), "Contact with us , you can't sginup anymore !");
					a.setAction(Intent.ACTION_VIEW);
					a.setData(Uri.parse("https://forms.gle/zH6hxVwVf7KbnY7R6"));
					startActivity(a);
				}
				file = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								b.setEnabled(true);
							}
						});
					}
				};
				_timer.schedule(file, (int)(5000));
			}
		});
		
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.setClass(getApplicationContext(), LoginActivity.class);
				a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(a);
				finish();
			}
		});
		
		checkbox1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.setAction(Intent.ACTION_VIEW);
				a.setData(Uri.parse("https://albreem.blogspot.com/2024/09/bookstore-application-policy-document.html"));
				startActivity(a);
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					bool = true;
				}
			}
		});
		
		_information_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				registered = true;
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
		
		g_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		g_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		g_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (registered && _success) {
					a.setClass(getApplicationContext(), LoginActivity.class);
					a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(a);
					finish();
				}
			}
		};
		
		g_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		g_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		g_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		g_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_g_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
					
					if (user != null) {
						    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
							        @Override
							        public void onComplete(@NonNull Task<Void> task) {
								            
								        }
							    });
					}
					SketchwareUtil.showMessage(getApplicationContext(), "Verify Your Account!!");
					map = new HashMap<>();
					map.put("email", edittext1.getText().toString());
					map.put("balance", "0.00");
					map.put("url", "");
					String email = edittext1.getText().toString();
					
					String username = email.split("@")[0];  
					
					
					DatabaseReference ref = FirebaseDatabase.getInstance()
					                          .getReference()
					                          .child("information")
					                          .child(username);  
					ref.updateChildren(map);
					map.clear();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_g_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_g_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		block = false;
		times = 0;
		edittext1.setSingleLine(true);
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFFFFFFF));
		b.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFF5C6BC0));
		b1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFF5C6BC0));
		checkbox1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		if (blocbk.getString("block_reg", "").equals("1")) {
			SketchwareUtil.showMessage(getApplicationContext(), "Registration is block for your device ! Contact us ");
			a.setClass(getApplicationContext(), LoginActivity.class);
			a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(a);
			finish();
		}
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		b.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)10, 0xFF3F51B5, 0xFFE8EAF6));
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFE8EAF6));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)10, 0xFF3F51B5, 0xFFE8EAF6));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFE8EAF6));
		edittext3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)10, 0xFF3F51B5, 0xFFE8EAF6));
		edittext3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFE8EAF6));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }Animation animation;
		        animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        imageview1.startAnimation(animation);
		        animation = null;
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        b.startAnimation(animation);
		        animation = null;
		
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        b1.startAnimation(animation);
		        animation = null;
		
		
		
	}
	
	@Override
	public void onBackPressed() {
		a.setClass(getApplicationContext(), LoginActivity.class);
		a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
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