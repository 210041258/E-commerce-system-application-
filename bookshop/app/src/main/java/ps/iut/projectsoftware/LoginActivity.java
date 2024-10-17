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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double times = 0;
	private boolean block = false;
	private double login_req = 0;
	private double aa = 0;
	private String extracted = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private EditText edittext1;
	private EditText edittext2;
	private LinearLayout linear5;
	private Button b1;
	private LinearLayout linear7;
	private TextView textview1;
	private LinearLayout linear6;
	private MaterialButton b;
	
	private Intent ocm = new Intent();
	private SharedPreferences a;
	private FirebaseAuth ag;
	private OnCompleteListener<AuthResult> _ag_create_user_listener;
	private OnCompleteListener<AuthResult> _ag_sign_in_listener;
	private OnCompleteListener<Void> _ag_reset_password_listener;
	private OnCompleteListener<Void> ag_updateEmailListener;
	private OnCompleteListener<Void> ag_updatePasswordListener;
	private OnCompleteListener<Void> ag_emailVerificationSentListener;
	private OnCompleteListener<Void> ag_deleteUserListener;
	private OnCompleteListener<Void> ag_updateProfileListener;
	private OnCompleteListener<AuthResult> ag_phoneAuthListener;
	private OnCompleteListener<AuthResult> ag_googleSignInListener;
	
	private TimerTask g;
	private DatabaseReference information = _firebase.getReference("information");
	private ChildEventListener _information_child_listener;
	private AlertDialog.Builder comp;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		edittext1 = findViewById(R.id.edittext1);
		edittext2 = findViewById(R.id.edittext2);
		linear5 = findViewById(R.id.linear5);
		b1 = findViewById(R.id.b1);
		linear7 = findViewById(R.id.linear7);
		textview1 = findViewById(R.id.textview1);
		linear6 = findViewById(R.id.linear6);
		b = findViewById(R.id.b);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		ag = FirebaseAuth.getInstance();
		comp = new AlertDialog.Builder(this);
		
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (true && ((edittext2.getText().toString().length() > 5) && (!block && !"".equals(edittext1.getText().toString())))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Wait Logging In ..");
					b1.setEnabled(false);
					a.edit().putString("email", edittext1.getText().toString()).commit();
					ag.signInWithEmailAndPassword(edittext1.getText().toString().trim(), edittext2.getText().toString().trim()).addOnCompleteListener(LoginActivity.this, _ag_sign_in_listener);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Check the Email and Password ?");
					times++;
				}
				if (times == 15) {
					a.edit().putString("block_login", "1").commit();
					block = true;
					SketchwareUtil.showMessage(getApplicationContext(), "Contact with us , you can't login anymore !");
					ocm.setAction(Intent.ACTION_VIEW);
					ocm.setData(Uri.parse("https://forms.gle/zH6hxVwVf7KbnY7R6"));
					startActivity(ocm);
				}
				g = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								b1.setEnabled(true);
							}
						});
					}
				};
				_timer.schedule(g, (int)(5000));
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), ForgotpassActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), SginupActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
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
		
		ag_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ag_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ag_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ag_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ag_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		ag_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		ag_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_ag_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_ag_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
					
					if (user != null) {
						    if (user.isEmailVerified()) {
							        aa= 1;
							    } else {
							        aa=0;
							    }
					}
					if (aa == 1) {
						if (edittext1.getText().toString().equals("albreem@iut-dhaka.edu")) {
							comp.setTitle("Login As : ");
							comp.setPositiveButton("Admin", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									a.edit().putString("login-ad", "a").commit();
									a.edit().putString("email", edittext1.getText().toString()).commit();
									ocm.setClass(getApplicationContext(), AdminActivity.class);
									ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(ocm);
									finish();
								}
							});
							comp.setNegativeButton("User", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									// Get the email from the input
									String email = edittext1.getText().toString();  // Replace with actual email input
									
									// Split the email to get the username (before the '@' symbol)
									String username = email.split("@")[0];  // Get the part before the '@'
									
									// Construct the node path
									String nodePath = "information/" + username;  // Specify your node path
									
									
									DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(nodePath);
									
									
									dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
										    @Override
										    public void onDataChange(DataSnapshot snapshot) {
											        if (snapshot.exists()) {
												            
												            a.edit().putString("balance", snapshot.child("balance").getValue(String.class)).commit();   
												            
												        } else {
												            SketchwareUtil.showMessage(getApplicationContext(), "Not Finding any data for you. Check Customer Center");
												        }
											    }
										
										    @Override
										    public void onCancelled(DatabaseError error) {
											        SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
											    }
									});
									a.edit().putString("login", "a").commit();
									a.edit().putString("email", edittext1.getText().toString()).commit();
									ocm.setClass(getApplicationContext(), ViewMainActivity.class);
									ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(ocm);
									finish();
								}
							});
							comp.create().show();
						}
						else {
							// Get the email from the input
							String email = edittext1.getText().toString();  // Replace with actual email input
							
							// Split the email to get the username (before the '@' symbol)
							String username = email.split("@")[0];  // Get the part before the '@'
							
							// Construct the node path
							String nodePath = "information/" + username;  // Specify your node path
							
							
							DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(nodePath);
							
							
							dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
								    @Override
								    public void onDataChange(DataSnapshot snapshot) {
									        if (snapshot.exists()) {
										            
										            a.edit().putString("balance", snapshot.child("balance").getValue(String.class)).commit();   
										            
										        } else {
										            SketchwareUtil.showMessage(getApplicationContext(), "Not Finding any data for you. Check Customer Center");
										        }
									    }
								
								    @Override
								    public void onCancelled(DatabaseError error) {
									        SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
									    }
							});
							a.edit().putString("login", "a").commit();
							a.edit().putString("email", edittext1.getText().toString()).commit();
							ocm.setClass(getApplicationContext(), ViewMainActivity.class);
							ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(ocm);
							finish();
						}
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_ag_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		login_req = 0;
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFFFFFFF));
		b1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFF5C6BC0));
		b.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFF5C6BC0));
		if (a.getString("block_login", "").equals("1")) {
			ocm.setAction(Intent.ACTION_VIEW);
			ocm.setData(Uri.parse("https://forms.gle/zH6hxVwVf7KbnY7R6"));
			startActivity(ocm);
			finishAffinity();
		}
		edittext1.setSingleLine(true);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		b.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)10, 0xFF3F51B5, 0xFFE8EAF6));
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFE8EAF6));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)10, 0xFF3F51B5, 0xFFE8EAF6));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)58, 0xFFE8EAF6));
		imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)158, 0xFFE8EAF6));
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }Animation animation;
		        animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        b1.startAnimation(animation);
		        animation = null;
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        b.startAnimation(animation);
		        animation = null;
	}
	
	@Override
	public void onBackPressed() {
		SketchwareUtil.showMessage(getApplicationContext(), "We will Be Waiting you Next time !");
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