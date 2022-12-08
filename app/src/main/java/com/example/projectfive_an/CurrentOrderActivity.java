package com.example.projectfive_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayAdapter arrayAdapter;
    private ArrayList<String> pizzaList;
    private int orderNum;
    private double orderT;
    private double salesT;
    private double[] subT;
    private ArrayList<String> storeOrders = new ArrayList<String>();
    private int position;
    /**
     * Default constructor
     */
    public CurrentOrderActivity() {
    }

    /**
     * This method setting up the UI for the current order page view.
     */
    private void initializeUI() {
        currentOrderTitle = findViewById(R.id.currentOrderTitile);
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

    private void setValues() {
        DecimalFormat df = new DecimalFormat("0.##");
        orderNumber.setText("Order Number: " + orderNum);
        orderTotal.setText("OrderTotal: $" + df.format(orderT));
        subtotal.setText("Subtotal: $" + 0.00);
        salesTax.setText("Sales Tax: $" + df.format(salesT));
    }

    private void clickBack() {
        coBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putStringArrayListExtra("pizzaLst",pizzaList);
                intent.putExtra("orderNum",orderNum);
                intent.putExtra("subtotals",subT);
                intent.putExtra("orderTotal",orderT);
                intent.putExtra("salesTax",salesT);
                setResult(-111, intent);
                finish();
            }
        });
    }

    private void clickClear() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("0.##");
                pizzaList.clear();
                orderNumber.setText("Order Number: " + orderNum);
                orderTotal.setText("OrderTotal: $" + 0.00);
                subtotal.setText("Subtotal: $" + 0.00);
                salesTax.setText("Sales Tax: $" + 0.00);
                settingUpListView();
            }
        });
    }

    private void clickPlaceOrder() {
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(generateOrderInfo());
                storeOrders.add(generateOrderInfo());
                Intent intent = getIntent();
                intent.putExtra("storeOrders",storeOrders);
                intent.putExtra("orderNum",orderNum);
                intent.putStringArrayListExtra("pizzaLst",null);
                intent.putExtra("subtotals",0.00);
                intent.putExtra("orderTotal",0.00);
                intent.putExtra("salesTax",0.00);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void clickRemovePizza() {
        currentOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DecimalFormat df = new DecimalFormat("0.##");
                position = i;
                view.setMinimumHeight(100);
                subtotal.setText("Subtotal: $" + df.format(subT[position]));
            }
        });
        removePizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizzaList.size()==0){
                    return;
                }
                DecimalFormat df = new DecimalFormat("0.##");
                arrayAdapter.notifyDataSetChanged();
                pizzaList.remove(position);
                settingUpListView();
                orderT -= subT[position];
                removeFromArray(position);
                salesTax.setText("Sales Tax: $" + df.format(orderT * (6.625/100)));
                orderTotal.setText("OrderTotal: $" + orderT);
                subtotal.setText("Subtotal: $" + 0.00);
            }
        });

    }

    private void settingUpListView() {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, pizzaList);
        currentOrderList.setChoiceMode(currentOrderList.CHOICE_MODE_SINGLE);
        currentOrderList.setAdapter(arrayAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initializeUI();
        Intent intent = getIntent();
        orderNum = intent.getIntExtra("orderNum", 0x3f3f3f3f);
        pizzaList = intent.getStringArrayListExtra("pizzaLst");
        salesT = intent.getDoubleExtra("salesTax", 0.00);
        orderT = intent.getDoubleExtra("orderTotal", 0.00);
        subT = intent.getDoubleArrayExtra("subtotals");
        setValues();
        clickBack();
        clickClear();
        settingUpListView();
        clickRemovePizza();
        clickPlaceOrder();
    }

    private void removeFromArray(int position){
        double[] newArray = new double[subT.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i > position){
                newArray[i] = subT[i + 1];
            }else{
                newArray[i] = subT[i];
            }
        }
        subT = newArray;
    }

    private String generateOrderInfo(){
        String res = "Order Number : " + orderNum + "   Order Total: " + orderT + "   Order " +
                "taxRate: " + 6.625/100;
        for (int i = 0; i < pizzaList.size(); i++) {
            res += "\n" + "   ";
            res += pizzaList.get(i);

        }
        return res;
    }
}