package ps.iut.projectsoftware;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class AdminMainAddActivity extends AppCompatActivity {
	
	public final int REQ_CD_PICK = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private FloatingActionButton _fab;
	private HashMap<String, Object> map = new HashMap<>();
	private boolean v = false;
	private String semester = "";
	private String depart = "";
	private String strlink = "";
	private String filename = "";
	
	private ArrayList<String> semster = new ArrayList<>();
	private ArrayList<String> department = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout books;
	private LinearLayout managers;
	private LinearLayout staffs;
	private LinearLayout coupon;
	private TextView textview1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView textview4;
	private EditText description;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private TextView textview2;
	private EditText id;
	private TextView textview3;
	private EditText name;
	private TextView textview5;
	private EditText copies;
	private TextView textview6;
	private Spinner spinner2;
	private TextView textview7;
	private EditText price;
	private TextView textview8;
	private EditText url;
	private CircleImageView circleimageview1;
	private TextView textview9;
	private Spinner spinner1;
	
	private Intent book = new Intent();
	private TimerTask ns;
	private DatabaseReference bsook = _firebase.getReference("book");
	private ChildEventListener _bsook_child_listener;
	private Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference images = _firebase_storage.getReference("images");
	private OnCompleteListener<Uri> _images_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _images_download_success_listener;
	private OnSuccessListener _images_delete_success_listener;
	private OnProgressListener _images_upload_progress_listener;
	private OnProgressListener _images_download_progress_listener;
	private OnFailureListener _images_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_main_add);
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
		books = findViewById(R.id.books);
		managers = findViewById(R.id.managers);
		staffs = findViewById(R.id.staffs);
		coupon = findViewById(R.id.coupon);
		textview1 = findViewById(R.id.textview1);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		textview4 = findViewById(R.id.textview4);
		description = findViewById(R.id.description);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		textview2 = findViewById(R.id.textview2);
		id = findViewById(R.id.id);
		textview3 = findViewById(R.id.textview3);
		name = findViewById(R.id.name);
		textview5 = findViewById(R.id.textview5);
		copies = findViewById(R.id.copies);
		textview6 = findViewById(R.id.textview6);
		spinner2 = findViewById(R.id.spinner2);
		textview7 = findViewById(R.id.textview7);
		price = findViewById(R.id.price);
		textview8 = findViewById(R.id.textview8);
		url = findViewById(R.id.url);
		circleimageview1 = findViewById(R.id.circleimageview1);
		textview9 = findViewById(R.id.textview9);
		spinner1 = findViewById(R.id.spinner1);
		pick.setType("image/*");
		pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					depart = "CSE";
				}
				else {
					if (_position == 1) {
						depart = "EEE";
					}
					else {
						if (_position == 2) {
							depart = "MPE";
						}
						else {
							if (_position == 3) {
								depart = "TVE";
							}
							else {
								if (_position == 4) {
									depart = "CEE";
								}
								else {
									depart = "BTM";
								}
							}
						}
					}
				}
				if (depart.equals("TVE")) {
					SketchwareUtil.showMessage(getApplicationContext(), "warning TVE let you select 1 to 6 semsters");
					semster.clear();
					semster.add((int)(0), "The First Semster");
					semster.add((int)(1), "The Second Semster");
					semster.add((int)(2), "The Third Semster");
					semster.add((int)(3), "The Fourth Semster");
					semster.add((int)(4), "The Fifth Semster");
					semster.add((int)(5), "The Sixth Semster");
					spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, semster));
					((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
				}
				else {
					semster.clear();
					semster.add((int)(0), "The First Semster");
					semster.add((int)(1), "The Second Semster");
					semster.add((int)(2), "The Third Semster");
					semster.add((int)(3), "The Fourth Semster");
					semster.add((int)(4), "The Fifth Semster");
					semster.add((int)(5), "The Sixth Semster");
					semster.add((int)(6), "The Seventh Semster");
					semster.add((int)(7), "The Eighth Semster");
					spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, semster));
					((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(pick, REQ_CD_PICK);
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					semester = "1";
				}
				else {
					if (_position == 1) {
						semester = "2";
					}
					else {
						if (_position == 2) {
							semester = "3";
						}
						else {
							if (_position == 3) {
								semester = "4";
							}
							else {
								if (_position == 4) {
									semester = "5";
								}
								else {
									if (_position == 5) {
										semester = "6";
									}
									else {
										if (_position == 6) {
											semester = "7";
										}
										else {
											semester = "8";
										}
									}
								}
							}
						}
					}
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (((((!"".equals(depart) && !"".equals(semester)) && !"".equals(url.getText().toString())) && !"".equals(copies.getText().toString())) && !"".equals(name.getText().toString())) && !id.getText().toString().equals("")) {
					map = new HashMap<>();
					map.put("id", id.getText().toString());
					map.put("name", name.getText().toString());
					map.put("description", description.getText().toString());
					map.put("copies", copies.getText().toString());
					map.put("price", price.getText().toString());
					map.put("url", url.getText().toString());
					map.put("semester", semester);
					map.put("department", depart);
					
					String key = id.getText().toString();
					
					DatabaseReference ref = FirebaseDatabase.getInstance()
					                          .getReference()
					                          .child("book")
					                          .child(key);
					ref.updateChildren(map);
					map.clear();
					book.setClass(getApplicationContext(), AdminmainListviewActivity.class);
					book.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(book);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Check the Values !!");
				}
			}
		});
		
		_bsook_child_listener = new ChildEventListener() {
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
		bsook.addChildEventListener(_bsook_child_listener);
		
		_images_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)).concat("%"));
			}
		};
		
		_images_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_images_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				url.setText(_downloadUrl);
			}
		};
		
		_images_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_images_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_images_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
	}
	
	private void initializeLogic() {
		name.setSingleLine(true);
		copies.setSingleLine(true);
		description.setSingleLine(false);
		id.setSingleLine(true);
		price.setSingleLine(true);
		url.setSingleLine(true);
		v = false;
		staffs.setVisibility(View.GONE);
		managers.setVisibility(View.GONE);
		books.setVisibility(View.GONE);
		coupon.setVisibility(View.GONE);
		if ("staff".equals(getIntent().getStringExtra("key"))) {
			staffs.setVisibility(View.VISIBLE);
		}
		else {
			if ("manager".equals(getIntent().getStringExtra("key"))) {
				managers.setVisibility(View.VISIBLE);
			}
			else {
				if ("coupon".equals(getIntent().getStringExtra("key"))) {
					coupon.setVisibility(View.VISIBLE);
				}
				else {
					books.setVisibility(View.VISIBLE);
				}
			}
		}
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		copies.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		url.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 0);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		semster.clear();
		semster.add((int)(0), "The First Semster");
		semster.add((int)(1), "The Second Semster");
		semster.add((int)(2), "The Third Semster");
		semster.add((int)(3), "The Fourth Semster");
		semster.add((int)(4), "The Fifth Semster");
		semster.add((int)(5), "The Sixth Semster");
		semster.add((int)(6), "The Seventh Semster");
		semster.add((int)(7), "The Eighth Semster");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, semster));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
		department.clear();
		department.add((int)(0), "CSE");
		department.add((int)(1), "EEE");
		department.add((int)(2), "MPE");
		department.add((int)(3), "TVE");
		department.add((int)(4), "CEE");
		department.add((int)(5), "BTM");
		spinner2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, department));
		((ArrayAdapter)spinner2.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PICK:
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
				strlink = _filePath.get((int)(0));
				filename = "temp".concat(String.valueOf((long)(1)));
				images.child("temp".concat(String.valueOf((long)(1)))).putFile(Uri.fromFile(new File(strlink))).addOnFailureListener(_images_failure_listener).addOnProgressListener(_images_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return images.child("temp".concat(String.valueOf((long)(1)))).getDownloadUrl();
					}}).addOnCompleteListener(_images_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		book.setClass(getApplicationContext(), AdminmainListviewActivity.class);
		book.putExtra("key", getIntent().getStringExtra("key"));
		book.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(book);
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