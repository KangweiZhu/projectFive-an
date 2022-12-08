package com.example.projectfive_an;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView chicagoPizzaImageView;
    private ImageView newYorkPizzImageView;
    private ImageView storeOrdersImageView;
    private ImageView currentOrderImageView;
    private Order currentOrder;
    private StoreOrders storeOrders;
    private String PIZZA_STRINGLIST_KEY = "pizza_stringed_arrayList";
    private String PIZZA_LIST_KEY;
    private String ORDER_NUMBER;
    private ArrayList<String> allPizzas = new ArrayList<String>();

    /**
     * Default constructor.
     */
    public MainActivity(){}

    /**
     * General method for handle the clicking events.
     */
    private void clickEvents(){
        chicagoPizzaImageView = (ImageView) findViewById(R.id.chicagoPizzaImageView);
        chicagoPizzaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chicagoTrigger = new Intent(getApplicationContext(),
                        ChicagoPizaaActivity.class);
                startActivity(chicagoTrigger);
            }
        });
        newYorkPizzImageView = (ImageView) findViewById(R.id.newYorkPizzaImageView);
        newYorkPizzImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nyTrigger = new Intent(getApplicationContext(), NewYorkPizzaActivity.class);
                nyTrigger.putExtra("PizzaList",allPizzas);
                startActivityForResult(nyTrigger,1);
            }
        });
        storeOrdersImageView = (ImageView) findViewById(R.id.storeOrdersImageView);
        storeOrdersImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent storeTrigger = new Intent(getApplication(),StoreOrdersActivity.class);
                startActivity(storeTrigger);
            }
        });
        currentOrderImageView = (ImageView) findViewById(R.id.currentOrderImageView);
        currentOrderImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent curOrderTrigger = new Intent(getApplication(),CurrentOrderActivity.class);
                curOrderTrigger.putStringArrayListExtra(PIZZA_STRINGLIST_KEY,currentOrder.
                        getPizzaArrayListStringed());
                startActivity(curOrderTrigger);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                allPizzas = data.getStringArrayListExtra("pizzalst");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.currentOrder = new Order();
        this.storeOrders = new StoreOrders();
        clickEvents();
    }



}