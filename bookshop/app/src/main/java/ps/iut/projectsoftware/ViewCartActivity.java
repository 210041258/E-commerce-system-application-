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
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.google.gson.Gson;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCartActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String str = "";
	private boolean a = false;
	private HashMap<String, Object> mapp = new HashMap<>();
	private double totalprivate = 0;
	private double feesondelivary = 0;
	private boolean feesondelivarybool = false;
	private boolean cuppon = false;
	private double discount = 0;
	private boolean discounted = false;
	private double number = 0;
	private boolean boo = false;
	private String email = "";
	private boolean fouren = false;
	private boolean threeen = false;
	private String onthree = "";
	private double sumonsectionone = 0;
	private boolean oneen = false;
	private boolean twoen = false;
	private double secondsection = 0;
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private LinearLayout linear9;
	private TextView textview6;
	private CheckBox checkbox4;
	private CheckBox checkbox3;
	private TextView textview1;
	private TextView feesondelivey;
	private TextView textview12;
	private TextView textview9;
	private EditText edittext1;
	private Button button1;
	private TextView textview7;
	private TextView total;
	private TextView textview14;
	private MaterialButton materialbutton1;
	
	private Intent eb = new Intent();
	private TimerTask v;
	private SharedPreferences cart;
	private SharedPreferences information;
	private DatabaseReference cuppons = _firebase.getReference("cuppons");
	private ChildEventListener _cuppons_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_cart);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear8 = findViewById(R.id.linear8);
		linear10 = findViewById(R.id.linear10);
		linear9 = findViewById(R.id.linear9);
		textview6 = findViewById(R.id.textview6);
		checkbox4 = findViewById(R.id.checkbox4);
		checkbox3 = findViewById(R.id.checkbox3);
		textview1 = findViewById(R.id.textview1);
		feesondelivey = findViewById(R.id.feesondelivey);
		textview12 = findViewById(R.id.textview12);
		textview9 = findViewById(R.id.textview9);
		edittext1 = findViewById(R.id.edittext1);
		button1 = findViewById(R.id.button1);
		textview7 = findViewById(R.id.textview7);
		total = findViewById(R.id.total);
		textview14 = findViewById(R.id.textview14);
		materialbutton1 = findViewById(R.id.materialbutton1);
		cart = getSharedPreferences("cart", Activity.MODE_PRIVATE);
		information = getSharedPreferences("a", Activity.MODE_PRIVATE);
		
		listview1.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView abs, int _scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView abs, int _firstVisibleItem, int _visibleItemCount, int _totalItemCount) {
				
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		checkbox4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox3.setChecked(false);
					if (threeen) {
						sumonsectionone = sumonsectionone - 50;
						threeen = false;
						feesondelivey.setText(String.valueOf(Double.parseDouble(feesondelivey.getText().toString()) - 50));
						total.setText(String.valueOf(Double.parseDouble(total.getText().toString()) - 50));
					}
					else {
						sumonsectionone = sumonsectionone + 50;
						feesondelivey.setText(String.valueOf(sumonsectionone + Double.parseDouble(feesondelivey.getText().toString())));
						total.setText(String.valueOf(sumonsectionone + Double.parseDouble(total.getText().toString())));
					}
				}
				else {
					checkbox3.setChecked(true);
				}
				fouren = true;
			}
		});
		
		checkbox3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					checkbox4.setChecked(false);
					if (fouren) {
						sumonsectionone = 50 + sumonsectionone;
						fouren = false;
						feesondelivey.setText(String.valueOf(Double.parseDouble(feesondelivey.getText().toString()) + 50));
						total.setText(String.valueOf(Double.parseDouble(total.getText().toString()) + 50));
					}
					else {
						sumonsectionone = sumonsectionone + 100;
						feesondelivey.setText(String.valueOf(sumonsectionone + Double.parseDouble(feesondelivey.getText().toString())));
						total.setText(String.valueOf(sumonsectionone + Double.parseDouble(total.getText().toString())));
					}
				}
				else {
					checkbox4.setChecked(true);
				}
				threeen = true;
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				str = _charSeq;
				v = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								//check coupon 
								
								
							}
						});
					}
				};
				_timer.schedule(v, (int)(5000));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(0 == number)) {
					if ("".equals(edittext1.getText().toString())) {
						    // Check if the coupon input is empty
						    SketchwareUtil.showMessage(getApplicationContext(), "Please enter a coupon code!");
					} else {
						    // Initialize Firebase reference (change "coupons" to your actual reference path)
						  final  DatabaseReference couponsRef = FirebaseDatabase.getInstance().getReference("coupons");
						
						    // Get the entered coupon code
						  final  String enteredCoupon = edittext1.getText().toString();
						
						    // Get the email from SharedPreferences and format it
						 final   String email = information.getString("email", "").replace(".", "_").replace("@", "_");
						
						    // Check if the email is empty
						    if (email.isEmpty()) {
							        SketchwareUtil.showMessage(getApplicationContext(), "User email not found!");
							        return;
							    }
						
						    // Reference to check user's used coupons in Firebase
						 final   DatabaseReference userCouponsRef = FirebaseDatabase.getInstance().getReference("inter_user/" + email + "/data/coupons");
						
						    // Check if the coupon has already been used by querying the user's coupons branch
						    userCouponsRef.orderByValue().equalTo(enteredCoupon).addListenerForSingleValueEvent(new ValueEventListener() {
							        @Override
							        public void onDataChange(DataSnapshot dataSnapshot) {
								            if (dataSnapshot.exists()) {
									                // If the coupon is already found under the user's branch, it means it has been used
									                SketchwareUtil.showMessage(getApplicationContext(), "Coupon has already been used!");
									            } else {
									                // Proceed to check the coupon's validity in the general "coupons" branch
									                couponsRef.orderByChild("code").equalTo(enteredCoupon).addListenerForSingleValueEvent(new ValueEventListener() {
										                    @Override
										                    public void onDataChange(DataSnapshot dataSnapshot) {
											                        if (dataSnapshot.exists()) {
												                            for (DataSnapshot couponSnapshot : dataSnapshot.getChildren()) {
													                                // Extract coupon details
													                                String code = couponSnapshot.child("code").getValue(String.class);
													                                double discount = 0;
													                                String expiryDate = "";
													
													                                // Safely get the discount and expiry date
													                                if (couponSnapshot.child("discount").getValue() != null) {
														                                    discount = Double.parseDouble(couponSnapshot.child("discount").getValue().toString());
														                                }
													                                if (couponSnapshot.child("expiryDate").getValue() != null) {
														                                    expiryDate = couponSnapshot.child("expiryDate").getValue().toString();
														                                }
													
													                                // Check if the coupon is expired
													                                boolean isExpired = false;
													                                try {
														                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
														                                    Date currentDate = new Date();
														                                    Date expiry = dateFormat.parse(expiryDate);
														                                    if (currentDate.after(expiry)) {
															                                        isExpired = true;
															                                    }
														                                } catch (java.text.ParseException e) {
														                                    e.printStackTrace();
														                                    SketchwareUtil.showMessage(getApplicationContext(), "Error parsing expiry date.");
														                                    return;
														                                }
													
													                                // If the coupon is expired, show a message
													                                if (isExpired) {
														                                    SketchwareUtil.showMessage(getApplicationContext(), "Coupon is expired!");
														                                } else {
														                                    // Disable the edit text to prevent further edits
														                                    edittext1.setEnabled(false);
														
														                                    // Calculate the discounted amount
														                                    try {
															                                        double originalAmount = Double.parseDouble(total.getText().toString());
															                                        double discountedAmount = originalAmount - (originalAmount * (discount / 100));
															
															                                        // Set the new total with the discounted amount
															                                        total.setText(String.valueOf((long) discountedAmount));
															
															                                        // Insert the used coupon into the user's Firebase data branch
															                                        userCouponsRef.push().setValue(enteredCoupon);
															
															        discounted=true;                               SketchwareUtil.showMessage(getApplicationContext(), "Discount applied successfully!");
															                                    } catch (NumberFormatException e) {
															                                        SketchwareUtil.showMessage(getApplicationContext(), "Invalid total amount");
															                                    }
														                                }
													                            }
												                        } else {
												                            SketchwareUtil.showMessage(getApplicationContext(), "Invalid coupon code!");
												                        }
											                    }
										
										                    @Override
										                    public void onCancelled(DatabaseError databaseError) {
											                        SketchwareUtil.showMessage(getApplicationContext(), "Error: " + databaseError.getMessage());
											                    }
										                });
									            }
								        }
							
							        @Override
							        public void onCancelled(DatabaseError databaseError) {
								            SketchwareUtil.showMessage(getApplicationContext(), "Error: " + databaseError.getMessage());
								        }
							    });
					}
					if (discounted) {
						SketchwareUtil.showMessage(getApplicationContext(), "you can't edit the cuppon");
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "you need to select one item at least !!");
				}
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(0 == number)) {
					if ((checkbox3.isChecked() || checkbox4.isChecked()) && (true || true)) {
						if ((Double.parseDouble(information.getString("balance", "")) == Double.parseDouble(total.getText().toString())) || (Double.parseDouble(information.getString("balance", "")) > Double.parseDouble(total.getText().toString()))) {
							
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "you can the products to your wishlist because the balance not enough !");
							materialbutton1.setEnabled(false);
						}
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "you need to select one item at least !!");
				}
			}
		});
		
		_cuppons_child_listener = new ChildEventListener() {
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
		cuppons.addChildEventListener(_cuppons_child_listener);
	}
	
	private void initializeLogic() {
		cuppon = false;
		discounted = false;
		sumonsectionone = 0;
		number = 0;
		email = information.getString("email", "");
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#E8EAF6")); }
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		checkbox3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		feesondelivey.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		total.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/legel.ttf"), 1);
		vscroll1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFFFFFFF, 0xFFE8EAF6));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFFE8EAF6));
		listview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)10, 0xFFE8EAF6, 0xFFE8EAF6));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)58, (int)0, 0xFFE8EAF6, 0xFF1A237E));
		checkbox4.setChecked(true);
		if (!cart.getString("cart", "").equals("")) {
			    // Deserialize JSON data from SharedPreferences to map
			    map = new Gson().fromJson(cart.getString("cart", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			
			    // Set up ListView adapter
			    listview1.setAdapter(new Listview1Adapter(map));
			    ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			
			    // Modify email and set Firebase path
			    String email2 = email.replace("@", "_").replace(".", "_");
			    String subPath_cart = "inter_user/" + email2 + "/data/cart";
			
			    // Push data to Firebase
			    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(subPath_cart);
			    databaseRef.setValue(map);
		}
	}
	
	@Override
	public void onBackPressed() {
		cart.edit().putString("cart", new Gson().toJson(map)).commit();
		String jsn = map == null ? "null" : new Gson().toJson(map);
		cart.edit().putString("cart", jsn).apply();
		eb.setClass(getApplicationContext(), ViewMainActivity.class);
		eb.putExtra("gate", "");
		eb.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(eb);
		finish();
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.view_caart, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final CheckBox mins = _view.findViewById(R.id.mins);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView copies = _view.findViewById(R.id.copies);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView boid = _view.findViewById(R.id.boid);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView bookname = _view.findViewById(R.id.bookname);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView price = _view.findViewById(R.id.price);
			
			boid.setText(map.get((int)_position).get("id") != null ? map.get((int)_position).get("id").toString() : "");
			bookname.setText(map.get((int)_position).get("name") != null ? map.get((int)_position).get("name").toString() : "");
			Glide.with(getApplicationContext()).load(Uri.parse(map.get((int)_position).get("url") != null ? map.get((int)_position).get("url").toString() : "")).into(imageview1);
			price.setText(map.get((int)_position).get("price") != null ? map.get((int)_position).get("price").toString() : "");
			copies.setText(map.get((int)_position).get("copies") != null ? map.get((int)_position).get("copies").toString() : "");
			
			imageview3.setOnClickListener(new View.OnClickListener() {
				    @Override
				    public void onClick(View _view) {
					        // Get the price of the item to be deleted
					        double priceValue = 0;
					        String email2 = email.replace("@", "").replace(".", "");
					        String subPath_cart = "inter_user/" + email2 + "/data/cart";
					        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(subPath_cart);
					        databaseRef.setValue(map);
					
					        try {
						            // Calculate the total price for the item based on the price and copies
						            priceValue = Double.parseDouble(map.get((int)_position).get("price").toString()) * 
						                         Double.parseDouble(map.get((int)_position).get("copies").toString());
						        } catch (NumberFormatException e) {
						            Log.e("NumberFormatException", "Error parsing price or copies: " + e.getMessage());
						        }
					
					        // Remove the item at the specified position from the ArrayList
					        map.remove((int) _position);
					
					        // Subtract the price of the removed item from totalprivate
					        totalprivate -= priceValue;
					        if (totalprivate < 0) {
						            totalprivate = 0;
						        }
					        total.setText(String.valueOf(totalprivate)); // Update total price display
					
					        // Check if the cart is empty and clear the stored cart if it is
					        if (new Gson().toJson(map).equals("[]")) {
						            cart.edit().putString("cart", "").commit();
						        }
					
					        // Update the ListView with the modified map
					        listview1.setAdapter(new Listview1Adapter(map));
					        ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
					    }
			});
			
			mins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				    @Override
				    public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
					        try {
						            // Calculate the base price of the item
						            double itemPrice = Double.parseDouble(price.getText().toString());
						            double availableCopies = Double.parseDouble(map.get((int)_position).get("copies").toString());
						            double priceValue = itemPrice * availableCopies;
						
						            // Get the delivery fee value
						            double deliveryFee = 0;
						            try {
							                deliveryFee = Double.parseDouble(feesondelivey.getText().toString());
							            } catch (NumberFormatException e) {
							                Log.e("NumberFormatException", "Error parsing delivery fee: " + e.getMessage());
							            }
						
						            // Check if the checkbox or switch is checked
						            if (isChecked) {
							                number++;
							                
							                // Add the price and delivery fee (if it's not zero)
							                if (deliveryFee > 0) {
								                    priceValue += deliveryFee;
								                }
							                
							                totalprivate += priceValue;
							            } else {
							                number--;
							                
							                // Subtract the price and delivery fee (if it's not zero)
							                if (deliveryFee > 0) {
								                    priceValue += deliveryFee;
								                }
							                
							                totalprivate -= priceValue;
							                
							                // Ensure totalprivate doesn't go below zero
							                if (totalprivate < 0) {
								                    totalprivate = 0;
								                }
							            }
						
						            // Update the total TextView
						            total.setText(String.valueOf(totalprivate));
						
						            // Check if balance is sufficient to cover the total
						            double currentBalance = Double.parseDouble(information.getString("balance", ""));
						            if (currentBalance < totalprivate) {
							                SketchwareUtil.showMessage(getApplicationContext(), "You can add the products to your wishlist because the balance is not enough!");
							                materialbutton1.setEnabled(false);
							            } else {
							                materialbutton1.setEnabled(true);
							            }
						
						        } catch (NumberFormatException e) {
						            // Handle any parsing errors gracefully
						            Log.e("NumberFormatException", "Error parsing price or copies: " + e.getMessage());
						        }
					    }
			});
			mins.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			bookname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			boid.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			copies.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ggg.ttf"), 1);
			imageview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, Color.TRANSPARENT));
			if ((Double.parseDouble(information.getString("balance", "")) == Double.parseDouble(total.getText().toString())) || (Double.parseDouble(information.getString("balance", "")) > Double.parseDouble(total.getText().toString()))) {
				
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "you can the products to your wishlist because the balance not enough !");
				materialbutton1.setEnabled(false);
			}
			
			return _view;
		}
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