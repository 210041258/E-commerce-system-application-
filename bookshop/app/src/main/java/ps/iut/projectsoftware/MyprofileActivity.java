package ps.iut.projectsoftware;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class MyprofileActivity extends AppCompatActivity {
	
	public final int REQ_CD_FILE = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private FloatingActionButton _fab;
	
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
	private Intent file = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference exchange = _firebase_storage.getReference("images");
	private OnCompleteListener<Uri> _exchange_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _exchange_download_success_listener;
	private OnSuccessListener _exchange_delete_success_listener;
	private OnProgressListener _exchange_upload_progress_listener;
	private OnProgressListener _exchange_download_progress_listener;
	private OnFailureListener _exchange_failure_listener;
	
	private DatabaseReference vk = _firebase.getReference("information");
	private ChildEventListener _vk_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.myprofile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
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
		file.setType("image/*");
		file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dial.setTitle("Select One :");
				dial.setNeutralButton("Upload Photo", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						startActivityForResult(file, REQ_CD_FILE);
					}
				});
				dial.setPositiveButton("View Photo", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (!"".equals(a.getString("url", ""))) {
							ocm.setClass(getApplicationContext(), ViewPhotoActivity.class);
							ocm.putExtra("url", a.getString("url", ""));
							ocm.putExtra("back", "myprofile");
							ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(ocm);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "No photo Uploaded 😕 !!");
						}
					}
				});
				dial.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dial.create().show();
			}
		});
		
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
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), HistoryOperationActivity.class);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		_exchange_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_exchange_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_exchange_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				SketchwareUtil.showMessage(getApplicationContext(), "Successfully Uploaded !!");
				Glide.with(getApplicationContext()).load(Uri.parse(_downloadUrl)).into(imageview2);
				a.edit().putString("url", _downloadUrl).commit();
				
				
				// Retrieve the email from the EditText and extract the username
				String email = a.getString("email", "");
				String username = email.split("@")[0];
				
				// Create a HashMap with the new data to update in Firebase
				HashMap<String, Object> map = new HashMap<>();
				map.put("email", email);
				map.put("balance", a.getString("balance", ""));  // assuming balance is being fetched from a variable 'a'
				
				// Reference to the Firebase Database node "information" and the user's data
				DatabaseReference ref = FirebaseDatabase.getInstance()
				                          .getReference()
				                          .child("information")
				                          .child(username);  
				
				// Update the user's information in Firebase
				ref.updateChildren(map);
			}
		};
		
		_exchange_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_exchange_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_exchange_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_vk_child_listener = new ChildEventListener() {
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
		vk.addChildEventListener(_vk_child_listener);
		
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
		if (!a.getString("url", "").equals("")) {
			Glide.with(getApplicationContext()).load(Uri.parse(a.getString("url", ""))).into(imageview2);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				exchange.child("profile").putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_exchange_failure_listener).addOnProgressListener(_exchange_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return exchange.child("profile").getDownloadUrl();
					}}).addOnCompleteListener(_exchange_upload_success_listener);
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "No File Selected !!");
			}
			break;
			default:
			break;
		}
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