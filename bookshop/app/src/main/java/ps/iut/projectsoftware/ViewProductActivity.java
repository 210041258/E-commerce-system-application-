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
import com.google.android.material.button.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;


public class ViewProductActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private double num = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String email = "";
	private double copies = 0;
	private String com = "";
	private String id = "";
	private double nu = 0;
	private double times = 0;
	private double index = 0;
	private double selected = 0;
	private boolean inserted = false;
	private HashMap<String, Object> mapadditionally = new HashMap<>();
	private boolean bool_favourite = false;
	private boolean bool_faviourte = false;
	private boolean bool_wishlist = false;
	private String str1 = "";
	private String str2 = "";
	
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> temp = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> items = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap2 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tempp = new ArrayList<>();
	
	private LinearLayout linear9;
	private ScrollView vscroll1;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private LinearLayout photo;
	private LinearLayout linear25;
	private LinearLayout linear11;
	private LinearLayout linear13;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private LinearLayout linear28;
	private LinearLayout linear29;
	private LinearLayout linear22;
	private TextView textview9;
	private TextView description;
	private LinearLayout linear33;
	private LinearLayout linear32;
	private ImageView imageview8;
	private ImageView imageview9;
	private LinearLayout linear27;
	private ImageView imageview7;
	private TextView textview14;
	private ImageView imageview10;
	private TextView textview4;
	private LinearLayout linear14;
	private ImageView imageview5;
	private TextView textview5;
	private ImageView imageview6;
	private MaterialButton materialbutton1;
	private MaterialButton materialbutton2;
	private TextView textview1;
	private LinearLayout linear26;
	private TextView title;
	private TextView textview2;
	private TextView price;
	private TextView textview11;
	private TextView semester;
	private TextView textview13;
	private TextView department;
	private TextView textview15;
	private TextView textview16;
	private TextView textview17;
	private TextView textview18;
	private TextView textview20;
	private LinearLayout linear37;
	private ImageView csea;
	private LinearLayout linear34;
	private ImageView csec;
	private LinearLayout linear36;
	
	private SharedPreferences a;
	private Intent move = new Intent();
	private SharedPreferences cart;
	private DatabaseReference book = _firebase.getReference("book");
	private ChildEventListener _book_child_listener;
	private TimerTask refresh;
	private AlertDialog.Builder confirm;
	private SharedPreferences related;
	private Intent ocm = new Intent();
	private SharedPreferences favorite;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_product);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear9 = findViewById(R.id.linear9);
		vscroll1 = findViewById(R.id.vscroll1);
		linear10 = findViewById(R.id.linear10);
		linear12 = findViewById(R.id.linear12);
		photo = findViewById(R.id.photo);
		linear25 = findViewById(R.id.linear25);
		linear11 = findViewById(R.id.linear11);
		linear13 = findViewById(R.id.linear13);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear23 = findViewById(R.id.linear23);
		linear24 = findViewById(R.id.linear24);
		linear28 = findViewById(R.id.linear28);
		linear29 = findViewById(R.id.linear29);
		linear22 = findViewById(R.id.linear22);
		textview9 = findViewById(R.id.textview9);
		description = findViewById(R.id.description);
		linear33 = findViewById(R.id.linear33);
		linear32 = findViewById(R.id.linear32);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		linear27 = findViewById(R.id.linear27);
		imageview7 = findViewById(R.id.imageview7);
		textview14 = findViewById(R.id.textview14);
		imageview10 = findViewById(R.id.imageview10);
		textview4 = findViewById(R.id.textview4);
		linear14 = findViewById(R.id.linear14);
		imageview5 = findViewById(R.id.imageview5);
		textview5 = findViewById(R.id.textview5);
		imageview6 = findViewById(R.id.imageview6);
		materialbutton1 = findViewById(R.id.materialbutton1);
		materialbutton2 = findViewById(R.id.materialbutton2);
		textview1 = findViewById(R.id.textview1);
		linear26 = findViewById(R.id.linear26);
		title = findViewById(R.id.title);
		textview2 = findViewById(R.id.textview2);
		price = findViewById(R.id.price);
		textview11 = findViewById(R.id.textview11);
		semester = findViewById(R.id.semester);
		textview13 = findViewById(R.id.textview13);
		department = findViewById(R.id.department);
		textview15 = findViewById(R.id.textview15);
		textview16 = findViewById(R.id.textview16);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		textview20 = findViewById(R.id.textview20);
		linear37 = findViewById(R.id.linear37);
		csea = findViewById(R.id.csea);
		linear34 = findViewById(R.id.linear34);
		csec = findViewById(R.id.csec);
		linear36 = findViewById(R.id.linear36);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		cart = getSharedPreferences("cart", Activity.MODE_PRIVATE);
		confirm = new AlertDialog.Builder(this);
		related = getSharedPreferences("related_json", Activity.MODE_PRIVATE);
		favorite = getSharedPreferences("favorite", Activity.MODE_PRIVATE);
		
		linear25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setClass(getApplicationContext(), ViewPhotoActivity.class);
				move.putExtra("id", getIntent().getStringExtra("id"));
				move.putExtra("name", getIntent().getStringExtra("name"));
				move.putExtra("description", getIntent().getStringExtra("description"));
				move.putExtra("url", getIntent().getStringExtra("url"));
				move.putExtra("copies", getIntent().getStringExtra("copies"));
				move.putExtra("back", getIntent().getStringExtra("back"));
				move.putExtra("price", getIntent().getStringExtra("price"));
				move.putExtra("department", getIntent().getStringExtra("department"));
				move.putExtra("semester", getIntent().getStringExtra("semester"));
				move.putExtra("back", "view_product"); 
				
				
				
				        
				move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(move);
				finish();
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setClass(getApplicationContext(), ViewPhotoActivity.class);
				move.putExtra("id", getIntent().getStringExtra("id"));
				move.putExtra("name", getIntent().getStringExtra("name"));
				move.putExtra("description", getIntent().getStringExtra("description"));
				move.putExtra("url", getIntent().getStringExtra("url"));
				move.putExtra("copies", getIntent().getStringExtra("copies"));
				move.putExtra("back", getIntent().getStringExtra("back"));
				
				move.putExtra("edition", getIntent().getStringExtra("edition"));
				move.putExtra("author", getIntent().getStringExtra("author"));
				move.putExtra("copy_preview", getIntent().getStringExtra("copy_preview"));
				
				move.putExtra("price", getIntent().getStringExtra("price"));
				move.putExtra("department", getIntent().getStringExtra("department"));
				move.putExtra("semester", getIntent().getStringExtra("semester"));
				move.putExtra("back", "view_product"); 
				        
				move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(move);
				finish();
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Toggle favorite status
				if (!bool_faviourte) {
					    // Check if item with the same ID is already in listmap
					    boolean isAlreadyInserted = false;
					    final String itemId = getIntent().getStringExtra("id");
					
					    for (HashMap<String, Object> item : listmap) {
						        if (itemId.equals(item.get("id"))) {
							            isAlreadyInserted = true;
							            break;
							        }
						    }
					
					    if (!isAlreadyInserted) {
						        // Set favorite icon to filled
						        imageview7.setImageResource(R.drawable.ic_favorite_white);
						
						        // Create a new map to store the item details
						        HashMap<String, Object> map = new HashMap<>();
						
						        // Add item details from intent extras to the map
						        if (itemId != null) map.put("id", itemId);
						        if (getIntent().getStringExtra("name") != null) map.put("name", getIntent().getStringExtra("name"));
						        if (getIntent().getStringExtra("description") != null) map.put("description", getIntent().getStringExtra("description"));
						        if (getIntent().getStringExtra("copies") != null) map.put("copies", getIntent().getStringExtra("copies"));
						        if (getIntent().getStringExtra("author") != null) map.put("author", getIntent().getStringExtra("author"));
						        if (getIntent().getStringExtra("edition") != null) map.put("edition", getIntent().getStringExtra("edition"));
						        if (getIntent().getStringExtra("price") != null) map.put("price", getIntent().getStringExtra("price"));
						        if (getIntent().getStringExtra("semester") != null) map.put("semester", getIntent().getStringExtra("semester"));
						        if (getIntent().getStringExtra("copy_preview") != null) map.put("copy_preview", getIntent().getStringExtra("copy_preview"));
						        if (getIntent().getStringExtra("department") != null) map.put("department", getIntent().getStringExtra("department"));
						        if (getIntent().getStringExtra("url") != null) map.put("url", getIntent().getStringExtra("url"));
						
						        // Add the map to the listmap
						        listmap.add(map);
						
						        // Update favorite status
						        bool_faviourte = true;
						        inserted = true;
						    }
				} else {
					    // Set favorite icon to outline
					    imageview7.setImageResource(R.drawable.ic_favorite_outline_white);
					
					    // Get the ID from the TextView text
					    final String textViewId = getIntent().getStringExtra("id");
					
					    // Iterate and remove matching items
					    for (Iterator<HashMap<String, Object>> iterator = listmap.iterator(); iterator.hasNext();) {
						        HashMap<String, Object> item = iterator.next();
						        if (textViewId.equals(item.get("id"))) {
							            iterator.remove();
							        }
						    }
					
					    // Update favorite status
					    bool_faviourte = false;
					    inserted = false;
				}
			}
		});
		
		imageview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Toggle favorite status
				if (!bool_wishlist) {
					    // Check if item with the same ID is already in listmap2
					    boolean isAlreadyInserted = false;
					    final String itemId = getIntent().getStringExtra("id");
					
					    for (HashMap<String, Object> item : listmap2) {
						        if (itemId.equals(item.get("id"))) {
							            isAlreadyInserted = true;
							            break;
							        }
						    }
					
					    if (!isAlreadyInserted) {
						        // Set favorite icon to filled
						        imageview10.setImageResource(R.drawable.ic_bookmark_white);
						
						        // Create a new map to store the item details
						        HashMap<String, Object> map = new HashMap<>();
						
						        // Add item details from intent extras to the map
						        if (itemId != null) map.put("id", itemId);
						        if (getIntent().getStringExtra("name") != null) map.put("name", getIntent().getStringExtra("name"));
						        if (getIntent().getStringExtra("description") != null) map.put("description", getIntent().getStringExtra("description"));
						        if (getIntent().getStringExtra("copies") != null) map.put("copies", getIntent().getStringExtra("copies"));
						        if (getIntent().getStringExtra("author") != null) map.put("author", getIntent().getStringExtra("author"));
						        if (getIntent().getStringExtra("edition") != null) map.put("edition", getIntent().getStringExtra("edition"));
						        if (getIntent().getStringExtra("price") != null) map.put("price", getIntent().getStringExtra("price"));
						        if (getIntent().getStringExtra("semester") != null) map.put("semester", getIntent().getStringExtra("semester"));
						        if (getIntent().getStringExtra("copy_preview") != null) map.put("copy_preview", getIntent().getStringExtra("copy_preview"));
						        if (getIntent().getStringExtra("department") != null) map.put("department", getIntent().getStringExtra("department"));
						        if (getIntent().getStringExtra("url") != null) map.put("url", getIntent().getStringExtra("url"));
						
						        // Add the map to the listmap
						        listmap2.add(map);
						
						        // Update favorite status
						        bool_wishlist = true;
						        inserted = true;
						    }
				} else {
					    // Set favorite icon to outline
					    imageview10.setImageResource(R.drawable.ic_bookmark_outline_white);
					
					    // Get the ID from the TextView text
					    final String textViewId = getIntent().getStringExtra("id");
					
					    // Iterate and remove matching items
					    for (Iterator<HashMap<String, Object>> iterator = listmap2.iterator(); iterator.hasNext();) {
						        HashMap<String, Object> item = iterator.next();
						        if (textViewId.equals(item.get("id"))) {
							            iterator.remove();
							        }
						    }
					
					    // Update favorite status
					    bool_wishlist = false;
					    inserted = false;
				}
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(num == 1)) {
					num--;
					textview5.setText(String.valueOf((long)(num)));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Can't be less One !");
				}
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Double.parseDouble(getIntent().getStringExtra("copies")) > num) {
					num++;
					textview5.setText(String.valueOf((long)(num)));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Can't be more than our Inventory !");
				}
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear11.getVisibility() == View.VISIBLE) {
					    // Get the current price and balance as Double
					    final double price = Double.parseDouble(getIntent().getStringExtra("price"));  // Mark as final
					    final double balance = Double.parseDouble(a.getString("balance", ""));  // Mark as final
					
					    // Retrieve the number of copies from textview5
					    String numStr = textview5.getText().toString();
					    final int num;
					
					    // Check if numStr is not null or empty and parse it
					    if (numStr != null && !numStr.isEmpty()) {
						        num = Integer.parseInt(numStr);
						    } else {
						        SketchwareUtil.showMessage(getApplicationContext(), "Number of copies not provided.");
						        return;  // Exit if num is null or empty
						    }
					
					    // Calculate the total price for all copies
					    final double totalPrice = price * num;  // Mark as final
					
					    // Check if the user has enough balance
					    if (balance < totalPrice) {
						        SketchwareUtil.showMessage(getApplicationContext(), "Insufficient balance.");
						        return;  // Stop if the balance is not enough
						    }
					
					    // Now check if there are enough copies of the book available
					    String bookId = getIntent().getStringExtra("id");
					    final DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference("book").child(bookId);
					
					    // Listener to check book availability in Firebase
					    bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
						        @Override
						        public void onDataChange(DataSnapshot snapshot) {
							            if (snapshot.exists()) {
								                String copiesStr = snapshot.child("copies").getValue(String.class);
								
								                if (copiesStr != null) {
									                    final int copiesInFirebase = Integer.parseInt(copiesStr);  // Mark as final
									
									                    // Check if there are enough copies available
									                    if (copiesInFirebase < num) {
										                        SketchwareUtil.showMessage(getApplicationContext(), "Not enough copies available.");
										                        return;  // Stop if not enough copies are available
										                    }
									
									                    // If there are enough copies and the balance is sufficient, show confirmation dialog
									                    AlertDialog.Builder confirm = new AlertDialog.Builder(ViewProductActivity.this);  // Use the correct activity name
									                    confirm.setTitle("Do you want to confirm?");
									                    confirm.setMessage("You will spend: " + num + " books, Total price: " + totalPrice + " Taka,\nName of the book: " + title.getText().toString());
									                    confirm.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
										                        @Override
										                        public void onClick(DialogInterface _dialog, int _which) {
											                            // Subtract the total price from the balance and update shared preferences
											                            double updatedBalance = balance - totalPrice;
											                            a.edit().putString("balance", String.valueOf(updatedBalance)).commit();
											
											                            // Update user balance in Firebase
											                            final String email = a.getString("email", "");
											                            final String username = email.split("@")[0];
											                            final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("information/" + username);
											
											                            dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
												                                @Override
												                                public void onDataChange(DataSnapshot snapshot) {
													                                    if (snapshot.exists()) {
														                                        Map<String, Object> updateData = new HashMap<>();
														                                        updateData.put("email", email);
														                                        updateData.put("balance", a.getString("balance", ""));
														                                        dataRef.setValue(updateData);
														                                    } else {
														                                        SketchwareUtil.showMessage(getApplicationContext(), "No data found. Please check with Customer Center.");
														                                    }
													                                }
												
												                                @Override
												                                public void onCancelled(DatabaseError error) {
													                                    SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
													                                }
												                            });
											
											                            // Update the number of copies in Firebase
											                            int newCopies = copiesInFirebase - num;
											                            bookRef.child("copies").setValue(String.valueOf(newCopies));
											
											                            // Proceed to the next activity after updating
											                            Intent move = new Intent(getApplicationContext(), OrderproofActivity.class);
											                            move.putExtra("orderid", getIntent().getStringExtra("id"));
											                            move.putExtra("orderno", String.valueOf((int) (SketchwareUtil.getRandom(100, 10000))));
											                            move.putExtra("orderprice", String.valueOf(totalPrice));
											                            move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											                            startActivity(move);
											                            finish();
											                        }
										                    });
									
									                    confirm.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										                        @Override
										                        public void onClick(DialogInterface _dialog, int _which) {
											                            // User cancelled the action, no further steps
											                        }
										                    });
									
									                    confirm.create().show();  // Show the confirmation dialog
									
									                } else {
									                    SketchwareUtil.showMessage(getApplicationContext(), "No copies data available.");
									                }
								            } else {
								                SketchwareUtil.showMessage(getApplicationContext(), "Book not found.");
								            }
							        }
						
						        @Override
						        public void onCancelled(DatabaseError error) {
							            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + error.getMessage());
							        }
						    });
				} else {
					    SketchwareUtil.showMessage(getApplicationContext(), "Check Balance!!");
				}
			}
		});
		
		materialbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mapadditionally = new HashMap<>();
				
				if (getIntent().getStringExtra("id") != null) 
				    mapadditionally.put("id", getIntent().getStringExtra("id"));
				if (getIntent().getStringExtra("name") != null) 
				    mapadditionally.put("name", getIntent().getStringExtra("name"));
				if (getIntent().getStringExtra("description") != null) 
				    mapadditionally.put("description", getIntent().getStringExtra("description"));
				if (getIntent().getStringExtra("copies") != null) 
				    mapadditionally.put("available_copies", getIntent().getStringExtra("copies"));
				if (getIntent().getStringExtra("author") != null) 
				    mapadditionally.put("author", getIntent().getStringExtra("author"));
				if (getIntent().getStringExtra("edition") != null) 
				    mapadditionally.put("edition", getIntent().getStringExtra("edition"));
				if (getIntent().getStringExtra("price") != null) 
				    mapadditionally.put("price", getIntent().getStringExtra("price"));
				if (getIntent().getStringExtra("semester") != null) 
				    mapadditionally.put("semester", getIntent().getStringExtra("semester"));
				if (getIntent().getStringExtra("copy_preview") != null) 
				    mapadditionally.put("copy_preview", getIntent().getStringExtra("copy_preview"));
				if (getIntent().getStringExtra("department") != null) 
				    mapadditionally.put("department", getIntent().getStringExtra("department"));
				if (getIntent().getStringExtra("url") != null) 
				    mapadditionally.put("url", getIntent().getStringExtra("url"));
				
				// Calculate "total_price" and "copies" if "price" is non-null and "textview5" has a valid value
				try {
					    double price = Double.parseDouble(getIntent().getStringExtra("price"));
					    double copies = Double.parseDouble(textview5.getText().toString());
					    mapadditionally.put("total_price", String.valueOf((long)(copies * price)));
					    mapadditionally.put("copies", String.valueOf((long)copies));
				} catch (NumberFormatException | NullPointerException e) {
					    // Handle or log the exception if needed
				}
				
				items.add(items.size(), mapadditionally);
				cart.edit().putString("cart", new Gson().toJson(items)).commit();
				
				move.setClass(getApplicationContext(), ViewCartActivity.class);
				move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(move);
				finish();
			}
		});
		
		csea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), ViewSearchActivity.class);
				ocm.putExtra("key", str1);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		csec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ocm.setClass(getApplicationContext(), ViewSearchActivity.class);
				ocm.putExtra("key", str2);
				ocm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ocm);
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (nu >= 14 && nu <= 26) {
						nu = nu + 4;
						times++;
				}
				
				department.setTextSize((int)nu);
				semester.setTextSize((int)nu);
				price.setTextSize((int)nu);
				description.setTextSize((int)nu);
				textview1.setTextSize((int)nu);
				textview2.setTextSize((int)nu);
				textview11.setTextSize((int)nu);
				textview13.setTextSize((int)nu);
				textview9.setTextSize((int)nu);
				title.setTextSize((int)nu);
				
				if (times >= 3) {
						times = 0;
						nu = 14;
				}
			}
		});
		
		_book_child_listener = new ChildEventListener() {
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
		book.addChildEventListener(_book_child_listener);
	}
	
	private void initializeLogic() {
		if (!"".equals(cart.getString("cart", ""))) {
			items = new Gson().fromJson(cart.getString("cart", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		bool_faviourte = false;
		if (!"".equals(favorite.getString("favorite", ""))) {
			listmap = new Gson().fromJson(favorite.getString("favorite", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		str1 = "";
		str2 = "";
		bool_wishlist = false;
		
		final int num =1;
		
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#3F51B5")); }
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		textview18.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
		materialbutton2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		materialbutton1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		linear32.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFF3F51B5));
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("url"))).into(imageview8);
		description.setText(getIntent().getStringExtra("description"));
		title.setText(getIntent().getStringExtra("name"));
		price.setText(getIntent().getStringExtra("price"));
		semester.setText(getIntent().getStringExtra("semester"));
		department.setText(getIntent().getStringExtra("department"));
		linear32.setVisibility(View.GONE);
		linear33.setVisibility(View.GONE);
		if ("CSE".equals(getIntent().getStringExtra("department").trim().toUpperCase())) {
			linear32.setVisibility(View.VISIBLE);
			linear33.setVisibility(View.VISIBLE);
			tempp = new Gson().fromJson("[\n    { \"text\":\"A Handbook of Agile Software Craftsmanship\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.14%20AM.jpeg?alt=media&token=fa443e98-22c3-48fe-85c1-0512c447acf1\" },\n    { \"text\":\"Operating System Concepts\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.13%20AM.jpeg?alt=media&token=3341f3f9-32fd-43aa-9176-cf6143373427\" },\n    { \"text\":\"Computer Networking\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.13%20AM%20(1).jpeg?alt=media&token=3e897635-fe40-4341-807b-650295d4dde8\" },\n    { \"text\":\"Engineering Drawing & Design\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.12%20AM.jpeg?alt=media&token=b…\" }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		else {
			if ("EEE".equals(getIntent().getStringExtra("department").trim().toUpperCase())) {
				linear33.setVisibility(View.VISIBLE);
				linear32.setVisibility(View.VISIBLE);
				tempp = new Gson().fromJson("[\n    { \"text\":\"Electrical Engineering: Principles & Application\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.27.31%20PM.jpeg?alt=media&token=e7bae42f-2042-4895-a302-5d50f94d42c5\" },\n    { \"text\":\"Sedra/Smith Microelectronic Circuits\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.28.07%20PM.jpeg?alt=media&token=093c8269-8caa-4490-8a22-0b865630f035\" },\n    { \"text\":\"Fundamentals of Electric Circuits\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.28.46%20PM.jpeg?alt=media&token=0e938fbe-783a-4cd9-a2e3-95a708dd5b70\" },\n    { \"text\":\"Power System Analysis and Design\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.29.34%20PM.jpeg?alt=media&token=7addaa76-eb04-4735-b0ad-9bb55f89d598\" },\n    { \"text\":\"Engineering Mechanics: Dynamics\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.14%20AM%20(1).jpeg?alt=media&token=45da8ae6-5608-4b0c-bd9d-da500719001e\" }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			}
			else {
				if ("MPE".equals(getIntent().getStringExtra("department").trim().toUpperCase())) {
					linear33.setVisibility(View.VISIBLE);
					linear32.setVisibility(View.VISIBLE);
					tempp = new Gson().fromJson("[\n    { \"text\":\"University Physics with Modern Physics\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.10%20AM%20(1).jpeg?alt=media&token=46d5eda8-84ec-4703-b1c0-30f76e07f465\" },\n    { \"text\":\"Advanced Engineering Mathematics\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.10%20AM.jpeg?alt=media&token=87cdd866-f7d0-4821-a4bb-9ab48eda3995\" },\n    { \"text\":\"The C Programming Language\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.11%20AM%20(1).jpeg?alt=media&token=25ac852e-6b50-4599-90a0-2914155ab0af\" },\n    { \"text\":\"Chemistry: The Central Science\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.11%20AM.jpeg?alt=media&token=a82d1f26-5e12-4f03-802e-fe3d8ebf46db\" },\n    { \"text\":\"Control Systems Engineering\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.30.43%20PM.jpeg?alt=media&token=aa794bd2-db8b-4828-9245-cb7d1813c3fb\" }\n  ]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				}
				else {
					if ("TVE".equals(getIntent().getStringExtra("department").trim().toUpperCase())) {
						linear33.setVisibility(View.VISIBLE);
						linear32.setVisibility(View.VISIBLE);
						tempp = new Gson().fromJson("[\n    { \"text\":\"Curriculum Development in Vocational\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.17.29%20PM.jpeg?alt=media&token=6630d6ed-29aa-4e67-acfb-f6b142ff4469\" },\n    { \"text\":\"Teaching in Further Education\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.18.24%20PM.jpeg?alt=media&token=11cc2728-bcbe-451a-beee-b34f4a992636\" },\n    { \"text\":\"Teaching and Training Vocational Learners\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.19.04%20PM.jpeg?alt=media&token=a9b40f02-3bfa-421b-b2be-6e06e195ab20\" },\n    { \"text\":\"Competency Based Education and Training\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.19.48%20PM.jpeg?alt=media&token=52a3ec7…\" }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					}
					else {
						if ("CEE".equals(getIntent().getStringExtra("department").trim().toUpperCase())) {
							linear33.setVisibility(View.VISIBLE);
							linear32.setVisibility(View.VISIBLE);
							tempp = new Gson().fromJson("[\n    { \"text\":\"Design of Reinforced Concret=\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.15%20AM.jpeg?alt=media&token=0f0caa21-ca13-43ab-94b5-52712d7012fb\" },\n    { \"text\":\"Environmental Engineering\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.16%20AM%20(1).jpeg?alt=media&token=dcddee96-bff3-442c-a465-5ea15e3d6e80\" },\n    { \"text\":\"Geotechnical Engineering\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.17%20AM.jpeg?alt=media&token=38a65378-a91b-4442-9301-bbb052a66fb4\" },\n    { \"text\":\"Structural Analysis\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-10%20at%201.44.16%20AM.jpeg?alt=media&token=8…\" }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						}
						else {
							linear33.setVisibility(View.VISIBLE);
							linear32.setVisibility(View.VISIBLE);
							tempp = new Gson().fromJson("[\n    { \"text\":\"Information Technology for Management\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.22.27%20PM.jpeg?alt=media&token=93550f92-8115-49fb-b2ed-6e62c665a3ee\" },\n    { \"text\":\"The Lean Startup\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.23.29%20PM.jpeg?alt=media&token=bb76b245-906c-4a58-b4fc-343ac9f86ace\" },\n    { \"text\":\"Business Model Generation\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.24.24%20PM.jpeg?alt=media&token=f08ce3ef-3274-4717-8fef-c2af27503852\" },\n    { \"text\":\"Essentials of Business Processes and Information Systems\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.25.01%20PM.jpeg?alt=media&token=46aa3a3b-6bda-4019-89b0-a231440652a5\" },\n    { \"text\":\"Technology Strategy for Managers and Entrepreneurs\",\"link\": \"https://firebasestorage.googleapis.com/v0/b/iutianbookshop.appspot.com/o/WhatsApp%20Image%202024-10-07%20at%201.25.53%20PM.jpeg?alt=media&token=67f0f119-1829-4218-afc1-d0aff2d91a21\" }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						}
					}
				}
			}
		}
		int index = 0;
		int selected = 0;
		
		for (int _repeat796 = 0; _repeat796 < 3; _repeat796++) {
			    String currentUrl = tempp.get(index).get("link").toString();
			    String text = tempp.get(index).get("text").toString();  // Retrieve the 'text' value
			    
			    if (!getIntent().getStringExtra("url").equals(currentUrl) && selected < 2) {
				        // Load images based on 'selected' value
				        if (selected == 0) {
					            Glide.with(getApplicationContext()).load(Uri.parse(currentUrl)).into(csea);
					            str1 =  text;  // Store the text value
					        } else if (selected == 1) {
					            Glide.with(getApplicationContext()).load(Uri.parse(currentUrl)).into(csec);
					           str2= text;  // Store the text value
					        }
				        selected++;
				    }
			    index++;
		}
		textview16.setText(getIntent().getStringExtra("author"));
		textview18.setText(getIntent().getStringExtra("edition"));
		//copies & id
		imageview8.setElevation((float)25);
		if (0 < Double.parseDouble(getIntent().getStringExtra("copies"))) {
			linear11.setVisibility(View.VISIBLE);
			linear25.setVisibility(View.GONE);
		}
		else {
			linear25.setVisibility(View.VISIBLE);
			linear11.setVisibility(View.GONE);
			linear13.setVisibility(View.GONE);
			if (!"".equals(favorite.getString("wishlist", ""))) {
				listmap2 = new Gson().fromJson(favorite.getString("wishlist", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			}
			index = 0;
			for(int _repeat763 = 0; _repeat763 < (int)(listmap2.size()); _repeat763++) {
				if (listmap2.get((int)index).get("id").toString().equals(getIntent().getStringExtra("id"))) {
					bool_wishlist = true;
				}
				else {
					bool_wishlist = false;
				}
				index++;
			}
			if (bool_wishlist) {
				imageview10.setImageResource(R.drawable.ic_bookmark_white);
			}
			else {
				imageview10.setImageResource(R.drawable.ic_bookmark_outline_white);
			}
		}
		imageview8.setElevation((float)10);
		if (!"".equals(cart.getString("size", ""))) {
			nu = Double.parseDouble(cart.getString("size", ""));
			times = 3;
		}
		else {
			nu = 14;
			times = 0;
		}
		department.setTextSize((int)nu);
		semester.setTextSize((int)nu);
		price.setTextSize((int)nu);
		description.setTextSize((int)nu);
		textview1.setTextSize((int)nu);
		textview2.setTextSize((int)nu);
		textview11.setTextSize((int)nu);
		textview13.setTextSize((int)nu);
		textview9.setTextSize((int)nu);
		title.setTextSize((int)nu);
		index = 0;
		for(int _repeat739 = 0; _repeat739 < (int)(listmap.size()); _repeat739++) {
			if (listmap.get((int)index).get("id").toString().equals(getIntent().getStringExtra("id"))) {
				bool_faviourte = true;
			}
			else {
				bool_faviourte = false;
			}
			index++;
		}
		if (bool_faviourte) {
			imageview7.setImageResource(R.drawable.ic_favorite_white);
		}
		else {
			imageview7.setImageResource(R.drawable.ic_favorite_outline_white);
		}
	}
	
	@Override
	public void onBackPressed() {
		cart.edit().putString("size", String.valueOf((long)(nu))).commit();
		favorite.edit().putString("favorite", new Gson().toJson(listmap)).commit();
		favorite.edit().putString("wishlist", new Gson().toJson(listmap2)).commit();
		move.setClass(getApplicationContext(), ViewSearchActivity.class);
		move.putExtra("key", getIntent().getStringExtra("department"));
		move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(move);
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