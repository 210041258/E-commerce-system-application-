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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MyprofileActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private ImageView imageview2;
	private LinearLayout linear7;
	private LinearLayout linear3;
	private EditText edittext1;
	private LinearLayout linear4;
	private EditText edittext2;
	private LinearLayout linear5;
	private EditText edittext3;
	private TextView textview1;
	private TextView textview2;
	private ImageView imageview4;
	private TextView textview3;
	private ImageView imageview5;
	
	private Intent ocm = new Intent();
	private AlertDialog.Builder dial;
	private FirebaseAuth user;
	private OnCompleteListener<AuthResult> _user_create_user_listener;
	private OnCompleteListener<AuthResult> _user_sign_in_listener;
	private OnCompleteListener<Void> _user_reset_password_listener;
	private OnCompleteListener<Void> user_updateEmailListener;
	private OnCompleteListener<Void> user_updatePasswordListener;
	private OnCompleteListener<Void> user_emailVerificationSentListener;
	private OnCompleteListener<Void> user_deleteUserListener;
	private OnCompleteListener<Void> user_updateProfileListener;
	private OnCompleteListener<AuthResult> user_phoneAuthListener;
	private OnCompleteListener<AuthResult> user_googleSignInListener;
	
	private SharedPreferences a;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.myprofile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear6 = findViewById(R.id.linear6);
		imageview2 = findViewById(R.id.imageview2);
		linear7 = findViewById(R.id.linear7);
		linear3 = findViewById(R.id.linear3);
		edittext1 = findViewById(R.id.edittext1);
		linear4 = findViewById(R.id.linear4);
		edittext2 = findViewById(R.id.edittext2);
		linear5 = findViewById(R.id.linear5);
		edittext3 = findViewById(R.id.edittext3);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		imageview4 = findViewById(R.id.imageview4);
		textview3 = findViewById(R.id.textview3);
		imageview5 = findViewById(R.id.imageview5);
		dial = new AlertDialog.Builder(this);
		user = FirebaseAuth.getInstance();
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Reset Password Sent to your Email");
				user.sendPasswordResetEmail(edittext1.getText().toString()).addOnCompleteListener(_user_reset_password_listener);
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dial.setTitle("Add Balance :");
				dial.setPositiveButton("By Manual Transactions", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						ocm.setClass(getApplicationContext(), ManuelActivity.class);
						ocm.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(ocm);
						finish();
					}
				});
				dial.setNegativeButton("By Online Transactions ", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						ocm.setClass(getApplicationContext(), GatewayActivity.class);
						ocm.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(ocm);
						finish();
					}
				});
				dial.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dial.create().show();
			}
		});
		
		user_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		user_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		user_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		user_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		user_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		user_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		user_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_user_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_user_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_user_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		edittext3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFF3F51B5));
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		edittext1.setText(a.getString("email", ""));
		edittext2.setText("**********");
		edittext3.setText(a.getString("balance", ""));
		edittext2.setEnabled(false);
		edittext3.setEnabled(false);
		edittext1.setEnabled(false);
	}
	
	@Override
	public void onBackPressed() {
		ocm.setClass(getApplicationContext(), ProfileActivity.class);
		ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(ocm);
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