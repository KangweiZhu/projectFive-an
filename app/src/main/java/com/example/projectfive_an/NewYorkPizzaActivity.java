package com.example.projectfive_an;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewYorkPizzaActivity extends AppCompatActivity {
    private Button backButton;
    private Button addToCart;
    private TextView newYorkTitle;
    private RecyclerView pizzaChoosing;
    private RadioButton smallButton;
    private RadioButton mediumButton;
    private RadioButton largeButton;
    private ListView realToppings;
    private TextView crustDescription;
    private TextView price;
    private Size size;
    private RecyclerView.Adapter flavorAdp;
    private List<String> flavorList;
    private List<Integer> imageList;
    private Pizza curPizza = null;
    private PizzaFactory nyPizzaFactory = new NYPizza();
    private PizzaFactory chicagoPizzaFactory = new ChicagoPizza();
    private ArrayAdapter adapter;
    private FlavorAdp.onClickListener listener = null;
    private static final int MAXTOPPINGS = 7;
    private Topping[] toppings;

    /**
     * Default Constructor
     */
    public NewYorkPizzaActivity() {

    }

    private void initializeRecyclerViewListener() {
        listener = new FlavorAdp.onClickListener() {
            @Override
            public void clicked(String flavor) {
                if (flavor.equals("(Chicago)BBQ Chicken")) {
                    curPizza = chicagoPizzaFactory.createBBQChicken();
                    newYorkTitle.setText("Chicago Style Pizza - Current Selecting: " + "BBQ " +
                            "Chicken flavor");
                    disableListView(1);
                } else if (flavor.equals("(Chicago)Deluxe")) {
                    curPizza = chicagoPizzaFactory.createDeluxe();
                    newYorkTitle.setText("Chicago Style Pizza - Current Selecting: Deluxe flavor");
                    disableListView(2);
                } else if (flavor.equals("(Chicago)Meatzza")) {
                    curPizza = chicagoPizzaFactory.createMeatzza();
                    newYorkTitle.setText("Chicago Style Pizza - Current Selecting: Meatzza " +
                            "flavor");
                    disableListView(3);
                } else if (flavor.equals("(Chicago)BuildYourOwn")) {
                    curPizza = chicagoPizzaFactory.createBuildYourOwn();
                    newYorkTitle.setText("Chicago Style Pizza - Current Selecting: BuildTourOwn " +
                            "flavor");
                    disableListView(0);
                }
                checkNewYorkPizza(flavor);
                crustDescription.setText("Crust: " + curPizza.getCrust().getCrustText());
                if (size != null) {
                    curPizza.setSize(size);
                    updatePrice(curPizza.price());
                } else {
                    updatePrice(0.00);
                }
            }
        };
    }

    private void checkNewYorkPizza(String flavor) {
        if (flavor.equals("(New York)BBQ Chicken")) {
            curPizza = nyPizzaFactory.createBBQChicken();
            newYorkTitle.setText("New York Style Pizza - Current Selecting: " + "BBQ " +
                    "Chicken flavor");
            disableListView(1);
        } else if (flavor.equals("(New York)Deluxe")) {
            curPizza = nyPizzaFactory.createDeluxe();
            newYorkTitle.setText("New York Style Pizza - Current Selecting: Deluxe flavor");
            disableListView(2);
        } else if (flavor.equals("(New York)Meatzza")) {
            curPizza = nyPizzaFactory.createMeatzza();
            newYorkTitle.setText("New York Style Pizza - Current Selecting: Meatzza " +
                    "flavor");
            disableListView(3);
        } else if (flavor.equals("(New York)BuildYourOwn")) {
            curPizza = nyPizzaFactory.createBuildYourOwn();
            newYorkTitle.setText("New York Style Pizza - Current Selecting: BuildTourOwn " +
                    "flavor");
            disableListView(0);
        }
    }

    private void disableListView(int state) {
        uncheckEverything();
        if (state == 2) {
            for (int i = 0; i < toppings.length; i++) {
                if (i <= 4) {
                    realToppings.setItemChecked(i, true);
                }
            }
        } else if (state == 1) {
            realToppings.setItemChecked(5, true);
            realToppings.setItemChecked(2, true);
            realToppings.setItemChecked(6, true);
            realToppings.setItemChecked(7, true);
        } else if (state == 3) {
            realToppings.setItemChecked(0, true);
            realToppings.setItemChecked(1, true);
            realToppings.setItemChecked(8, true);
            realToppings.setItemChecked(9, true);
        } else if (state == 0) {
        }
    }

    private void uncheckEverything() {
        for (int i = 0; i < Topping.values().length; i++) {
            realToppings.setItemChecked(i, false);
        }
    }

    /**
     * This method initialize UI components for New York Style Pizza ordering page.
     */
    private void initializeUI() {
        realToppings = findViewById(R.id.realToppings);
        toppingSelection();
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
        addToCart = findViewById(R.id.addToCart);
        crustDescription = findViewById(R.id.crustDescription);
        price = findViewById(R.id.price);
        imageList = Arrays.asList(R.drawable.ny_deluxe, R.drawable.ny_bbq, R.drawable.ny_meatzza,
                R.drawable.createyourown, R.drawable.cs_deluxe, R.drawable.cs_deluxe,
                R.drawable.cs_meatzza, R.drawable.createyourown);
        flavorList = Arrays.asList("(Chicago)Deluxe", "(Chicago)BBQ Chicken", "(Chicago)Meatzza",
                "(Chicago)BuildYourOwn", "(New York)Deluxe", "(New York)BBQ Chicken",
                "(New York)Meatzza", "(New York)BuildYourOwn");
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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (state == 1) {
            alert.setTitle("Stop!");
            alert.setMessage("Build Your Own pizza could only have 7 toppings! ");
        } else if (state == 0) {
            alert.setTitle("Don't select toppings now!");
            alert.setMessage("First choose size, then choose flavor. After that, you will be able" +
                    "to choose toppings or using the preset toppings. ");

        } else if (state == -1) {
            alert.setTitle("You can't add Toppings for none build your own flavors!");
            alert.setMessage("Don't try to click that, it doesn't work. ");
        } else if (state == 3){
            alert.setTitle("select a flavor");
            alert.setMessage("failed adding to order cart ");
        } else if (state == 4){
            alert.setTitle("select a size");
            alert.setMessage("failed adding to order cart");
        }
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * This method is used for initializing the topping list and add listener to the listview.
     */
    private void toppingSelection() {
        toppings = Topping.values();
        String[] stringedToppings = new String[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            stringedToppings[i] = toppings[i].getToppingText();
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,
                stringedToppings);
        realToppings.setChoiceMode(realToppings.CHOICE_MODE_MULTIPLE);
        realToppings.setAdapter(adapter);
        realToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (curPizza == null) {
                    generateAlert(0);
                } else {
                    toppingCases(curPizza, i);
                }
            }
        });
    }

    private void toppingCases(Pizza curPizza, int position) {
        int i = position;
        if (curPizza instanceof BuildYourOwn) {
            if (curPizza.add(toppings[i])) {
                realToppings.setItemChecked(i, true);
                updatePrice(curPizza.price());
            } else if (curPizza.remove(toppings[i])) {
                realToppings.setItemChecked(i, false);
                updatePrice(curPizza.price());
            } else if (curPizza.getSelectedToppings().size() == MAXTOPPINGS) {
                realToppings.setItemChecked(i, false);
                generateAlert(1);
            }
        } else if (curPizza instanceof Deluxe) {
            if (!curPizza.getSelectedToppings().contains(toppings[i])) {
                realToppings.setItemChecked(i, false);
            } else {
                realToppings.setItemChecked(i, true);
            }
            generateAlert(-1);
        } else if (curPizza instanceof Meatzza) {
            if (!curPizza.getSelectedToppings().contains(toppings[i])) {
                realToppings.setItemChecked(i, false);
            } else {
                realToppings.setItemChecked(i, true);
            }
            generateAlert(-1);
        } else {
            if (curPizza.getSelectedToppings().contains(toppings[i])) {
                realToppings.setItemChecked(i, false);
                System.out.println("reached");
            } else {
                realToppings.setItemChecked(i, true);
            }
            generateAlert(-1);
        }
    }

    /**
     * This method is used for updating the price after new selections are made.
     */
    private void updatePrice(double newPrice) {
        DecimalFormat df = new DecimalFormat("00.##");
        String res = df.format(newPrice);
        if (size == null) {
            price.setText("Pizza Price $: " + 0.00);
            return;
        }
        price.setText("Pizza Price $: " + res);
    }

    private void clickAddToCart() {
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                ArrayList<String> pizzaList = intent.getStringArrayListExtra("PizzaList");
                if (curPizza == null) {
                    generateAlert(3);
                    return;
                }
                if (curPizza.getSize() == null) {
                    generateAlert(4);
                    return;
                }
                pizzaList.add(curPizza.toString());
                intent.putExtra("pizzalst",pizzaList);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_pizza);
        initializeUI();
        clickBackButton();
        sizeSelection();
        clickAddToCart();
    }
}