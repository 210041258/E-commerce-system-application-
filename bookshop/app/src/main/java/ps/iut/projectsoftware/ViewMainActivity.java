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
import android.widget.HorizontalScrollView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ViewMainActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	private boolean new_notif = false;
	private double index = 0;
	private String email = "";
	
	private ArrayList<HashMap<String, Object>> cse_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> eee_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> cee_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tve_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> mpe_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> btm_list = new ArrayList<>();
	private ArrayList<String> liststring = new ArrayList<>();
	
	private LinearLayout linear6;
	private ScrollView vscroll1;
	private ImageView imageview1;
	private LinearLayout linear7;
	private ImageView imageview2;
	private LinearLayout linear1;
	private LinearLayout linear8;
	private LinearLayout cse;
	private LinearLayout eee;
	private LinearLayout mpe;
	private LinearLayout cee;
	private LinearLayout btm;
	private LinearLayout tve;
	private ImageView imageview3;
	private EditText edittext1;
	private LinearLayout linear17;
	private HorizontalScrollView hscroll3;
	private TextView textview5;
	private LinearLayout linear18;
	private TextView textview6;
	private LinearLayout linear19;
	private ImageView csea;
	private ImageView cseb;
	private ImageView csec;
	private ImageView csed;
	private ImageView csee;
	private LinearLayout linear33;
	private HorizontalScrollView hscroll7;
	private LinearLayout linear52;
	private TextView textview13;
	private LinearLayout linear34;
	private TextView textview14;
	private LinearLayout linear35;
	private ImageView eeea;
	private ImageView eeeb;
	private ImageView eeec;
	private ImageView eeed;
	private LinearLayout linear37;
	private HorizontalScrollView hscroll8;
	private TextView textview15;
	private LinearLayout linear38;
	private TextView textview16;
	private LinearLayout linear39;
	private ImageView mpea;
	private ImageView mpeb;
	private ImageView mpec;
	private ImageView mped;
	private ImageView mpee;
	private LinearLayout linear41;
	private HorizontalScrollView hscroll9;
	private TextView textview17;
	private LinearLayout linear42;
	private TextView textview18;
	private LinearLayout linear43;
	private ImageView ceea;
	private ImageView ceeb;
	private ImageView ceec;
	private ImageView ceed;
	private LinearLayout linear45;
	private HorizontalScrollView hscroll10;
	private TextView textview19;
	private LinearLayout linear46;
	private TextView textview20;
	private LinearLayout linear47;
	private ImageView btma;
	private ImageView btmb;
	private ImageView btmc;
	private ImageView btmd;
	private ImageView btme;
	private LinearLayout linear49;
	private HorizontalScrollView hscroll11;
	private TextView textview21;
	private LinearLayout linear50;
	private TextView textview22;
	private LinearLayout linear51;
	private ImageView tvea;
	private ImageView tveb;
	private ImageView tvec;
	private ImageView tved;
	private ImageView tvee;
	
	private Intent profile = new Intent();
	private SharedPreferences a;
	private SharedPreferences related;
	private SharedPreferences cart;
	private SharedPreferences orders;
	private SharedPreferences favorite;
	private SharedPreferences read;
	private SharedPreferences history;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear6 = findViewById(R.id.linear6);
		vscroll1 = findViewById(R.id.vscroll1);
		imageview1 = findViewById(R.id.imageview1);
		linear7 = findViewById(R.id.linear7);
		imageview2 = findViewById(R.id.imageview2);
		linear1 = findViewById(R.id.linear1);
		linear8 = findViewById(R.id.linear8);
		cse = findViewById(R.id.cse);
		eee = findViewById(R.id.eee);
		mpe = findViewById(R.id.mpe);
		cee = findViewById(R.id.cee);
		btm = findViewById(R.id.btm);
		tve = findViewById(R.id.tve);
		imageview3 = findViewById(R.id.imageview3);
		edittext1 = findViewById(R.id.edittext1);
		linear17 = findViewById(R.id.linear17);
		hscroll3 = findViewById(R.id.hscroll3);
		textview5 = findViewById(R.id.textview5);
		linear18 = findViewById(R.id.linear18);
		textview6 = findViewById(R.id.textview6);
		linear19 = findViewById(R.id.linear19);
		csea = findViewById(R.id.csea);
		cseb = findViewById(R.id.cseb);
		csec = findViewById(R.id.csec);
		csed = findViewById(R.id.csed);
		csee = findViewById(R.id.csee);
		linear33 = findViewById(R.id.linear33);
		hscroll7 = findViewById(R.id.hscroll7);
		linear52 = findViewById(R.id.linear52);
		textview13 = findViewById(R.id.textview13);
		linear34 = findViewById(R.id.linear34);
		textview14 = findViewById(R.id.textview14);
		linear35 = findViewById(R.id.linear35);
		eeea = findViewById(R.id.eeea);
		eeeb = findViewById(R.id.eeeb);
		eeec = findViewById(R.id.eeec);
		eeed = findViewById(R.id.eeed);
		linear37 = findViewById(R.id.linear37);
		hscroll8 = findViewById(R.id.hscroll8);
		textview15 = findViewById(R.id.textview15);
		linear38 = findViewById(R.id.linear38);
		textview16 = findViewById(R.id.textview16);
		linear39 = findViewById(R.id.linear39);
		mpea = findViewById(R.id.mpea);
		mpeb = findViewById(R.id.mpeb);
		mpec = findViewById(R.id.mpec);
		mped = findViewById(R.id.mped);
		mpee = findViewById(R.id.mpee);
		linear41 = findViewById(R.id.linear41);
		hscroll9 = findViewById(R.id.hscroll9);
		textview17 = findViewById(R.id.textview17);
		linear42 = findViewById(R.id.linear42);
		textview18 = findViewById(R.id.textview18);
		linear43 = findViewById(R.id.linear43);
		ceea = findViewById(R.id.ceea);
		ceeb = findViewById(R.id.ceeb);
		ceec = findViewById(R.id.ceec);
		ceed = findViewById(R.id.ceed);
		linear45 = findViewById(R.id.linear45);
		hscroll10 = findViewById(R.id.hscroll10);
		textview19 = findViewById(R.id.textview19);
		linear46 = findViewById(R.id.linear46);
		textview20 = findViewById(R.id.textview20);
		linear47 = findViewById(R.id.linear47);
		btma = findViewById(R.id.btma);
		btmb = findViewById(R.id.btmb);
		btmc = findViewById(R.id.btmc);
		btmd = findViewById(R.id.btmd);
		btme = findViewById(R.id.btme);
		linear49 = findViewById(R.id.linear49);
		hscroll11 = findViewById(R.id.hscroll11);
		textview21 = findViewById(R.id.textview21);
		linear50 = findViewById(R.id.linear50);
		textview22 = findViewById(R.id.textview22);
		linear51 = findViewById(R.id.linear51);
		tvea = findViewById(R.id.tvea);
		tveb = findViewById(R.id.tveb);
		tvec = findViewById(R.id.tvec);
		tved = findViewById(R.id.tved);
		tvee = findViewById(R.id.tvee);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		related = getSharedPreferences("related_json", Activity.MODE_PRIVATE);
		cart = getSharedPreferences("cart", Activity.MODE_PRIVATE);
		orders = getSharedPreferences("orders", Activity.MODE_PRIVATE);
		favorite = getSharedPreferences("favorite", Activity.MODE_PRIVATE);
		read = getSharedPreferences("notification", Activity.MODE_PRIVATE);
		history = getSharedPreferences("history", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ProfileActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		imageview2.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), NotificationActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", edittext1.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		edittext1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview5.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		csea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "A Handbook of Agile Software Craftsmanship");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		cseb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Operating System Concepts");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		csec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Computer Networking");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		csed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Engineering Drawing & Design");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		csee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Introduction to Algorithms");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		textview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview13.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		eeea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Electrical Engineering: Principles & Application");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		eeeb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Sedra/Smith Microelectronic Circuits");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		eeec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Fundamentals of Electric Circuits");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		eeed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Power System Analysis and Design");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		textview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview15.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		mpea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "University Physics with Modern Physics");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		mpeb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Advanced Engineering Mathematics");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		mpec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "The C Programming Language");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		mped.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Chemistry: The Central Science");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		mpee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Control Systems Engineering");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		textview18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview17.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		ceea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Design of Reinforced Concrete");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		ceeb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Environmental Engineering");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		ceec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Geotechnical Engineering");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		ceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Structural Analysis");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		textview20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview19.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		btma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Information Technology for Management");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		btmb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "The Lean Startup");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		btmc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Business Model Generation");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		btmd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Essentials of Business Processes and Information Systems");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		btme.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Technology Strategy for Managers and Entrepreneurs");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		textview22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", textview21.getText().toString());
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		tvea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Curriculum Development in Vocational");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		tveb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Teaching in Further Education");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		tvec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Teaching and Training Vocational Learners");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		tved.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "Competency Based Education and Training");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		tvee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewSearchActivity.class);
				profile.putExtra("key", "The Skillful Teacher");
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ViewCartActivity.class);
				profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(profile);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		email = a.getString("email", "");
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview13.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview15.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview17.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview18.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview20.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview21.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		
		Animation animation;
		        animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		        cse.startAnimation(animation);
		        animation = null;
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		        cee.startAnimation(animation);
		        animation = null;
		
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		       mpe.startAnimation(animation);
		        animation = null;
		
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		        tve.startAnimation(animation);
		        animation = null;
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		        btm.startAnimation(animation);
		        animation = null;
		
		animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(1000);
		       eee.startAnimation(animation);
		        animation = null;
		cse.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE3F2FD, 0xFF1976D2));
		eee.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFDE7, 0xFFFBC02D));
		mpe.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFD7CCC8, 0xFF5D4037));
		cee.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFF0F4C3, 0xFFAFB42B));
		tve.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8F5E9, 0xFF388E3C));
		btm.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFEDE7F6, 0xFF512DA8));
		cse_list = new Gson().fromJson("[\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.14%20AM.jpeg?alt=media&token=fa443e98-22c3-48fe-85c1-0512c447acf1\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.13%20AM.jpeg?alt=media&token=3341f3f9-32fd-43aa-9176-cf6143373427\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.13%20AM%20(1).jpeg?alt=media&token=3e897635-fe40-4341-807b-650295d4dde8\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.12%20AM.jpeg?alt=media&token=b0218286-6fb2-4d69-9cb6-d0f875bb07b1\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.12%20AM%20(1).jpeg?alt=media&token=0d0b5e7a-2a67-4107-a872-07fa50142e99\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		eee_list = new Gson().fromJson("[\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.27.31%20PM.jpeg?alt=media&token=e7bae42f-2042-4895-a302-5d50f94d42c5\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.28.07%20PM.jpeg?alt=media&token=093c8269-8caa-4490-8a22-0b865630f035\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.28.46%20PM.jpeg?alt=media&token=0e938fbe-783a-4cd9-a2e3-95a708dd5b70\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.29.34%20PM.jpeg?alt=media&token=7addaa76-eb04-4735-b0ad-9bb55f89d598\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.14%20AM%20(1).jpeg?alt=media&token=45da8ae6-5608-4b0c-bd9d-da500719001e\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		cee_list = new Gson().fromJson(" [\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.15%20AM.jpeg?alt=media&token=0f0caa21-ca13-43ab-94b5-52712d7012fb\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.16%20AM%20(1).jpeg?alt=media&token=dcddee96-bff3-442c-a465-5ea15e3d6e80\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.17%20AM.jpeg?alt=media&token=38a65378-a91b-4442-9301-bbb052a66fb4\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.16%20AM.jpeg?alt=media&token=84605282-7778-49ca-8b98-2b826452bb84\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.20.48%20PM.jpeg?alt=media&token=5fc96e81-d324-41b1-911a-f11cbb96198c\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		tve_list = new Gson().fromJson("[\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.17.29%20PM.jpeg?alt=media&token=6630d6ed-29aa-4e67-acfb-f6b142ff4469\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.18.24%20PM.jpeg?alt=media&token=11cc2728-bcbe-451a-beee-b34f4a992636\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.19.04%20PM.jpeg?alt=media&token=a9b40f02-3bfa-421b-b2be-6e06e195ab20\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.19.48%20PM.jpeg?alt=media&token=52a3ec7e-fd50-488f-8cdc-4d83e59c92d5\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.20.48%20PM.jpeg?alt=media&token=5fc96e81-d324-41b1-911a-f11cbb96198c\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		mpe_list = new Gson().fromJson("[\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.10%20AM%20(1).jpeg?alt=media&token=46d5eda8-84ec-4703-b1c0-30f76e07f465\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.10%20AM.jpeg?alt=media&token=87cdd866-f7d0-4821-a4bb-9ab48eda3995\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.11%20AM%20(1).jpeg?alt=media&token=25ac852e-6b50-4599-90a0-2914155ab0af\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.11%20AM.jpeg?alt=media&token=a82d1f26-5e12-4f03-802e-fe3d8ebf46db\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.30.43%20PM.jpeg?alt=media&token=aa794bd2-db8b-4828-9245-cb7d1813c3fb\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		btm_list = new Gson().fromJson(" [\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.22.27%20PM.jpeg?alt=media&token=93550f92-8115-49fb-b2ed-6e62c665a3ee\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.23.29%20PM.jpeg?alt=media&token=bb76b245-906c-4a58-b4fc-343ac9f86ace\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.24.24%20PM.jpeg?alt=media&token=f08ce3ef-3274-4717-8fef-c2af27503852\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.25.01%20PM.jpeg?alt=media&token=46aa3a3b-6bda-4019-89b0-a231440652a5\" },\n    { \"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.25.53%20PM.jpeg?alt=media&token=67f0f119-1829-4218-afc1-d0aff2d91a21\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		related.edit().putString("cse", new Gson().toJson(cse_list)).commit();
		related.edit().putString("eee", new Gson().toJson(eee_list)).commit();
		related.edit().putString("mpe", new Gson().toJson(mpe_list)).commit();
		related.edit().putString("btm", new Gson().toJson(btm_list)).commit();
		related.edit().putString("tve", new Gson().toJson(tve_list)).commit();
		related.edit().putString("cee", new Gson().toJson(cee_list)).commit();
		Glide.with(getApplicationContext()).load(Uri.parse(cse_list.get((int)0).get("link").toString())).into(csea);
		// CSE
		Glide.with(getApplicationContext()).load(Uri.parse(cse_list.get((int)1).get("link").toString())).into(cseb);
		Glide.with(getApplicationContext()).load(Uri.parse(cse_list.get((int)2).get("link").toString())).into(csec);
		Glide.with(getApplicationContext()).load(Uri.parse(cse_list.get((int)3).get("link").toString())).into(csed);
		Glide.with(getApplicationContext()).load(Uri.parse(cse_list.get((int)4).get("link").toString())).into(csee);
		
		// EEE
		Glide.with(getApplicationContext()).load(Uri.parse(eee_list.get((int)0).get("link").toString())).into(eeea);
		Glide.with(getApplicationContext()).load(Uri.parse(eee_list.get((int)1).get("link").toString())).into(eeeb);
		Glide.with(getApplicationContext()).load(Uri.parse(eee_list.get((int)2).get("link").toString())).into(eeec);
		Glide.with(getApplicationContext()).load(Uri.parse(eee_list.get((int)3).get("link").toString())).into(eeed);
		
		
		// BTM
		Glide.with(getApplicationContext()).load(Uri.parse(btm_list.get((int)0).get("link").toString())).into(btma);
		Glide.with(getApplicationContext()).load(Uri.parse(btm_list.get((int)1).get("link").toString())).into(btmb);
		Glide.with(getApplicationContext()).load(Uri.parse(btm_list.get((int)2).get("link").toString())).into(btmc);
		Glide.with(getApplicationContext()).load(Uri.parse(btm_list.get((int)3).get("link").toString())).into(btmd);
		Glide.with(getApplicationContext()).load(Uri.parse(btm_list.get((int)4).get("link").toString())).into(btme);
		
		// MPE
		Glide.with(getApplicationContext()).load(Uri.parse(mpe_list.get((int)0).get("link").toString())).into(mpea);
		Glide.with(getApplicationContext()).load(Uri.parse(mpe_list.get((int)1).get("link").toString())).into(mpeb);
		Glide.with(getApplicationContext()).load(Uri.parse(mpe_list.get((int)2).get("link").toString())).into(mpec);
		Glide.with(getApplicationContext()).load(Uri.parse(mpe_list.get((int)3).get("link").toString())).into(mped);
		Glide.with(getApplicationContext()).load(Uri.parse(mpe_list.get((int)4).get("link").toString())).into(mpee);
		
		// TVE
		Glide.with(getApplicationContext()).load(Uri.parse(tve_list.get((int)0).get("link").toString())).into(tvea);
		Glide.with(getApplicationContext()).load(Uri.parse(tve_list.get((int)1).get("link").toString())).into(tveb);
		Glide.with(getApplicationContext()).load(Uri.parse(tve_list.get((int)2).get("link").toString())).into(tvec);
		Glide.with(getApplicationContext()).load(Uri.parse(tve_list.get((int)3).get("link").toString())).into(tved);
		Glide.with(getApplicationContext()).load(Uri.parse(tve_list.get((int)4).get("link").toString())).into(tvee);
		
		// CEE
		Glide.with(getApplicationContext()).load(Uri.parse(cee_list.get((int)0).get("link").toString())).into(ceea);
		Glide.with(getApplicationContext()).load(Uri.parse(cee_list.get((int)1).get("link").toString())).into(ceeb);
		Glide.with(getApplicationContext()).load(Uri.parse(cee_list.get((int)2).get("link").toString())).into(ceec);
		Glide.with(getApplicationContext()).load(Uri.parse(cee_list.get((int)3).get("link").toString())).into(ceed);
		
		if (!getIntent().getStringExtra("gate").equals("")) {
			// Split the email to get the username (before the '@' symbol)
			String username = email.split("@")[0];  // Get the part before the '@'
			String email2 = a.getString("email", "").replace(".", "_").replace("@", "_");
			
			// Define Firebase paths based on the email
			String subPath_cart = "inter_user/" + email2 + "/data/cart";
			String subPath_myorder = "inter_user/" + email2 + "/data/myorder";
			String subPath_myfavorite = "inter_user/" + email2 + "/data/myfavorite";
			String subPath_notification = "inter_user/" + email2 + "/data/notification";
			String subPath_wishlist = "inter_user/" + email2 + "/data/wishlist";
			String subPath_history = "inter_user/" + email2 + "/data/history";
			String nodePath = "information/" + username;
			
			// Reference to the main information path
			DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(nodePath);
			dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            a.edit().putString("balance", snapshot.child("balance").getValue(String.class)).commit();
						        } else {
						            SketchwareUtil.showMessage(getApplicationContext(), "No data found. Check Customer Center.");
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// Save data for each path only if data exists
			
			// Cart data
			FirebaseDatabase.getInstance().getReference(subPath_cart).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                cart.edit().putString("cart", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for cart", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// Orders data
			FirebaseDatabase.getInstance().getReference(subPath_myorder).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                orders.edit().putString("orders", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for orders", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// Favorite data
			FirebaseDatabase.getInstance().getReference(subPath_myfavorite).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                favorite.edit().putString("favorite", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for favorite", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// Notification data
			FirebaseDatabase.getInstance().getReference(subPath_notification).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                read.edit().putString("notification", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for notification", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// Wishlist data
			FirebaseDatabase.getInstance().getReference(subPath_wishlist).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                favorite.edit().putString("wishlist", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for wishlist", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
			
			// History data
			FirebaseDatabase.getInstance().getReference(subPath_history).addListenerForSingleValueEvent(new ValueEventListener() {
				    @Override
				    public void onDataChange(DataSnapshot snapshot) {
					        if (snapshot.exists()) {
						            try {
							                String json = new Gson().toJson(snapshot.getValue());
							                history.edit().putString("history", json).commit();
							            } catch (JsonSyntaxException e) {
							                Log.e("JsonError", "Data format error for history", e);
							            }
						        }
					    }
				
				    @Override
				    public void onCancelled(DatabaseError error) {
					        Log.e("FirebaseError", error.getMessage());
					    }
			});
		}
		else {
			
		}
		cse.setElevation((float)25);
		eee.setElevation((float)25);
		cee.setElevation((float)25);
		btm.setElevation((float)25);
		btm.setElevation((float)25);
		tve.setElevation((float)25);
		linear8.setElevation((float)25);
		imageview1.setElevation((float)25);
		imageview2.setElevation((float)25);
		linear6.setElevation((float)50);
		// Set elevations for CSE
		cseb.setElevation(25f);
		csec.setElevation(25f);
		csed.setElevation(25f);
		csee.setElevation(25f);
		
		// Set elevations for EEE
		eeea.setElevation(25f);
		eeeb.setElevation(25f);
		eeec.setElevation(25f);
		eeed.setElevation(25f);
		
		// Set elevations for BTM
		btma.setElevation(25f);
		btmb.setElevation(25f);
		btmc.setElevation(25f);
		btmd.setElevation(25f);
		btme.setElevation(25f);
		
		// Set elevations for MPE
		mpea.setElevation(25f);
		mpeb.setElevation(25f);
		mpec.setElevation(25f);
		mped.setElevation(25f);
		mpee.setElevation(25f);
		
		// Set elevations for TVE
		tvea.setElevation(25f);
		tveb.setElevation(25f);
		tvec.setElevation(25f);
		tved.setElevation(25f);
		tvee.setElevation(25f);
		
		// Set elevations for CEE
		ceea.setElevation(25f);
		ceeb.setElevation(25f);
		ceec.setElevation(25f);
		ceed.setElevation(25f);
		_fab.hide();
		if (!"".equals(cart.getString("cart", ""))) {
			_fab.show();
		}
		new_notif = false;
		
		if (new_notif) {
			imageview2.setImageResource(R.drawable.ic_email_black);
		}
	}
	
	@Override
	public void onBackPressed() {
		// Split the email to get the username (before the '@' symbol)
		String username = email.split("@")[0];  // Get the part before the '@'
		
		// Replace the email's special characters for path safety
		String email2 = a.getString("email", "").replace(".", "_").replace("@", "_");
		
		// Define paths for various data
		String subPath_cart = "inter_user/" + email2 + "/data/cart";
		String subPath_myorder = "inter_user/" + email2 + "/data/myorder";
		String subPath_myfavorite = "inter_user/" + email2 + "/data/myfavorite";
		String subPath_notification = "inter_user/" + email2 + "/data/notification";
		String subPath_wishlist = "inter_user/" + email2 + "/data/wishlist";
		String subPath_history = "inter_user/" + email2 + "/data/history";
		
		// Construct node paths for each section
		String nodePath = "information/" + username;
		
		// Reference to main information path
		DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(nodePath);
		DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference(subPath_cart);
		DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference(subPath_myorder);
		DatabaseReference favoriteRef = FirebaseDatabase.getInstance().getReference(subPath_myfavorite);
		DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference(subPath_notification);
		DatabaseReference wishlistRef = FirebaseDatabase.getInstance().getReference(subPath_wishlist);
		DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference(subPath_history);
		
		// Push SharedPreferences data back to Firebase
		// Push balance data back to Firebase
		String balance = a.getString("balance", "");
		if (balance != null && !balance.isEmpty()) {
			    dataRef.child("balance").setValue(balance);
		}
		
		// Push cart data back to Firebase
		String cartData = cart.getString("cart", "");
		if (cartData != null && !cartData.isEmpty()) {
			    Object cartObject = new Gson().fromJson(cartData, Object.class);
			    cartRef.setValue(cartObject);
		}
		
		// Push myorder data back to Firebase
		String orderData = orders.getString("orders", "");
		if (orderData != null && !orderData.isEmpty()) {
			    Object orderObject = new Gson().fromJson(orderData, Object.class);
			    orderRef.setValue(orderObject);
		}
		
		// Push myfavorite data back to Firebase
		String favoriteData = favorite.getString("favorite", "");
		if (favoriteData != null && !favoriteData.isEmpty()) {
			    Object favoriteObject = new Gson().fromJson(favoriteData, Object.class);
			    favoriteRef.setValue(favoriteObject);
		}
		
		// Push notification data back to Firebase
		String notificationData = read.getString("notification", "");
		if (notificationData != null && !notificationData.isEmpty()) {
			    Object notificationObject = new Gson().fromJson(notificationData, Object.class);
			    notificationRef.setValue(notificationObject);
		}
		
		// Push wishlist data back to Firebase
		String wishlistData = favorite.getString("wishlist", "");
		if (wishlistData != null && !wishlistData.isEmpty()) {
			    Object wishlistObject = new Gson().fromJson(wishlistData, Object.class);
			    wishlistRef.setValue(wishlistObject);
		}
		
		// Push history data back to Firebase
		String historyData = history.getString("history", "");
		if (historyData != null && !historyData.isEmpty()) {
			    Object historyObject = new Gson().fromJson(historyData, Object.class);
			    historyRef.setValue(historyObject);
		}
		SketchwareUtil.showMessage(getApplicationContext(), "See you Next Time ! 😄");
		finishAffinity();
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