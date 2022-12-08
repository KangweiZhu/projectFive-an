package com.example.projectfive_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CurrentOrderActivity extends AppCompatActivity {
    private TextView currentOrderTitle;
    private Button coBackButton;
    private TextView orderNumber;
    private ListView currentOrderList;
    private TextView subtotal;
    private TextView orderTotal;
    private TextView salesTax;
    private Button removePizzaButton;
    private Button placeOrderButton;
    private Button clearButton;
    private ArrayList<String> pizzaList;
    private int orderNum;
    private double orderT;
    private double salesT;
    private double[] subT;
    private ArrayList<String> storeOrders;
    /**
     * Default constructor
     */
    public CurrentOrderActivity(){}

    /**
     * This method setting up the UI for the current order page view.
     */
    private void initializeUI(){
        currentOrderTitle= findViewById(R.id.currentOrderTitile);
        coBackButton = findViewById(R.id.coBackButton);
        orderNumber = findViewById(R.id.orderNumber);
        currentOrderList = findViewById(R.id.currentOrderList);
        subtotal = findViewById(R.id.subtotal);
        orderTotal = findViewById(R.id.orderTotal);
        salesTax = findViewById(R.id.salesTax);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        placeOrderButton = findViewById(R.id.placeOrder);
        clearButton = findViewById(R.id.clearButton);
    }

    private void setValues(){
        DecimalFormat df = new DecimalFormat("0.##");
        orderNumber.setText("Order Number: " + orderNum);
        orderTotal.setText("OrderTotal: $" + df.format(orderT));
        subtotal.setText("Subtotal: $" + 0.00);
        salesTax.setText("Sales Tax: $" + df.format(salesT));
    }

    private void clickBack(){
        coBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    private void clickClear(){
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("0.##");
                orderNumber.setText("Order Number: " + orderNum);
                orderTotal.setText("OrderTotal: $" + 0.00);
                subtotal.setText("Subtotal: $" + 0.00);
                salesTax.setText("Sales Tax: $" + 0.00);
            }
        });
    }

    private void clickPlaceOrder(){
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = null;
                storeOrders.add(res);
            }
        });
    }

    private void clickRemovePizza(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initializeUI();
        Intent intent = getIntent();
        orderNum = intent.getIntExtra("orderNum",0x3f3f3f3f);
        pizzaList = intent.getStringArrayListExtra("pizzaLst");
        salesT = intent.getDoubleExtra("salesTax",0.00);
        orderT = intent.getDoubleExtra("orderTotal",0.00);
        subT = intent.getDoubleArrayExtra("subTotal");
        setValues();
        clickBack();
        clickClear();
        settingUpListView();
        clickRemovePizza();
        clickPlaceOrder();
    }
}