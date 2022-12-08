package com.example.projectfive_an;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewYorkPizzaActivity extends AppCompatActivity {
    private Button backButton;
    private TextView newYorkTitle;
    private RecyclerView pizzaChoosing;
    private RadioButton smallButton;
    private RadioButton mediumButton;
    private RadioButton largeButton;
    private TextView sizeDescription;
    private ListView realToppings;
    private TextView crustDescription;
    private TextView price;
    private Size size;
    private RecyclerView.Adapter flavorAdp;
    private List<String> flavorList;
    private List<Integer> imageList;
    private Pizza curPizza = null;
    private PizzaFactory pizzaFactory = new NYPizza();
    private ArrayAdapter<String> adapter;
    private FlavorAdp.onClickListener listener = null;
    private static final int MAXTOPPINGS = 7;

    /**
     * Default Constructor
     */
    public NewYorkPizzaActivity() {

    }

    private void initializeRecyclerViewListener() {
        listener = new FlavorAdp.onClickListener() {
            @Override
            public void clicked(String flavor) {
                if (flavor.equals("BBQ Chicken")) {
                    curPizza = pizzaFactory.createBBQChicken();
                    newYorkTitle.setText("New York Style Pizza - Current Selecting: " + "BBQ " +
                            "Chicken flavor");
                } else if (flavor.equals("Deluxe")) {
                    curPizza = pizzaFactory.createDeluxe();
                    newYorkTitle.setText("New York Style Pizza - Current Selecting: Deluxe flavor");
                } else if (flavor.equals("Meatzza")) {
                    curPizza = pizzaFactory.createMeatzza();
                    newYorkTitle.setText("New York Style Pizza - Current Selecting: Meatzza " +
                            "flavor");
                } else if (flavor.equals("BuildYourOwn")) {
                    curPizza = pizzaFactory.createBuildYourOwn();
                    newYorkTitle.setText("New York Style Pizza - Current Selecting: BuildTourOwn " +
                            "flavor");
                }
                crustDescription.setText("Crust: " + curPizza.getCrust().getCrustText());
                if (size != null){
                    curPizza.setSize(size);
                }
                updatePrice(curPizza.price());
            }
        };
    }

    /**
     * This method initialize UI components for New York Style Pizza ordering page.
     */
    private void initializeUI() {
        backButton = findViewById(R.id.backButton);
        newYorkTitle = findViewById(R.id.newYorkTitle);
        pizzaChoosing = findViewById(R.id.pizzaChoosing);
        pizzaChoosing.setLayoutManager(new LinearLayoutManager(this));
        pizzaChoosing.setItemAnimator(new DefaultItemAnimator());
        pizzaChoosing.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.
                VERTICAL));
        smallButton = findViewById(R.id.smallButton);
        mediumButton = findViewById(R.id.mediumButton);
        largeButton = findViewById(R.id.largeButton);
        sizeDescription = findViewById(R.id.sizeDescription);
        realToppings = findViewById(R.id.realToppings);
        crustDescription = findViewById(R.id.crustDescription);
        price = findViewById(R.id.price);
        imageList = Arrays.asList(R.drawable.ny_deluxe, R.drawable.ny_bbq, R.drawable.ny_meatzza,
                R.drawable.createyourown);
        flavorList = Arrays.asList("Deluxe", "BBQ Chicken", "Meatzza", "BuildYourOwn");
        initializeRecyclerViewListener();
        flavorAdp = new FlavorAdp(this, imageList, flavorList, listener);
        pizzaChoosing.setLayoutManager(new LinearLayoutManager(this));
        pizzaChoosing.setItemAnimator(new DefaultItemAnimator());
        pizzaChoosing.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.
                VERTICAL));
        pizzaChoosing.setAdapter(flavorAdp);
    }

    /**
     * This method is to set a listener to the back button. Once it is clicked, it will finish the
     * current activity and go back to the main page.
     */
    private void clickBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newYorkTitle.setText("New York Style Pizza");
                finish();
            }
        });
    }

    /**
     * This method provides services for receiving which size of pizza is chosen.
     */
    private void sizeSelection() {
        smallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = Size.SMALL;
                if (curPizza != null && size != null) {
                    curPizza.setSize(size);
                    updatePrice(curPizza.price());
                }
            }
        });
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = Size.MEDIUM;
                if (curPizza != null && size != null) {
                    curPizza.setSize(size);
                    updatePrice(curPizza.price());
                }
            }
        });
        largeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = Size.LARGE;
                if (curPizza != null && size != null) {
                    curPizza.setSize(size);
                    updatePrice(curPizza.price());
                }
            }
        });
    }

    private void generateAlert(int state) {
        if(state == 1) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Stop!");
            alert.setMessage("Build Your Own pizza could only have 7 toppings! ");
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }

    /**
     * This method is used for initializing the topping list and add listener to the listview.
     */
    private void toppingSelection() {
        Topping[] toppings = Topping.values();
        String[] stringedToppings = new String[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            stringedToppings[i] = toppings[i].getToppingText();
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                stringedToppings);
        realToppings.setChoiceMode(realToppings.CHOICE_MODE_MULTIPLE);
        realToppings.setAdapter(adapter);
        realToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (curPizza instanceof BuildYourOwn) {
                    if (curPizza.add(toppings[i])) {
                        realToppings.setItemChecked(i, true);
                        updatePrice(curPizza.price());
                    } else if (curPizza.remove(toppings[i])) {
                        realToppings.setItemChecked(i, false);
                        updatePrice(curPizza.price());
                    } else if (curPizza.getSelectedToppings().size() == MAXTOPPINGS) {
                        generateAlert(1);
                    }
                }
            }
        });
    }

    /**
     * This method is used for updating the price after new selections are made.
     */
    private void updatePrice(double newPrice) {
        price.setText("Pizza Price $: " + newPrice);
    }

    private void clickAddToCart(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_pizza);
        initializeUI();
        clickBackButton();
        sizeSelection();
        toppingSelection();
    }
}