package com.example.projectfive_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the controller/Activity class for the storeOrder activity xml file.
 *
 * @author Kangwei Zhu, Michael Israel
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private TextView soOrderNum;
    private Button soBackButton;
    private Spinner orderSpinner;
    private TextView soOrderTotal;
    private Button exportButton;
    private Button clearOrderButton;
    private ArrayList<String> orders;
    private double[] orderTotal;
    private StoreOrders storeOrders;
    private ArrayList<Integer> orderNumber;
    private TextView ordersText;
    ArrayAdapter arrayAdapterForSpinner;
    private int position;

    /**
     * Default constructor
     */
    public StoreOrdersActivity() {
        storeOrders = new StoreOrders();
    }

    /**
     * This method initialize the UI
     */
    private void initializeUI() {
        soOrderNum = findViewById(R.id.soOrderNum);
        soBackButton = findViewById(R.id.soBackButton);
        orderSpinner = findViewById(R.id.orderNumSpinner);
        soOrderTotal = findViewById(R.id.soOrderTotal);
        exportButton = findViewById(R.id.exportButton);
        clearOrderButton = findViewById(R.id.clearOrderButton);
        ordersText = findViewById(R.id.orderText);
    }

    /**
     * This method handles the situation of clicking the back button.
     */
    private void clickBack() {
        soBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putIntegerArrayListExtra("orderNumberLst", orderNumber);
                intent.putStringArrayListExtra("orderLst", orders);
                intent.putExtra("orderTotalLst", orderTotal);
                setResult(111, intent);
                finish();
            }
        });
    }

    /**
     * This method receives the datas sends from MainActivity.
     */
    private void receivingDispatcher() {
        Intent intent = getIntent();
        orderNumber = intent.getIntegerArrayListExtra("orderNumberLst");
        orders = intent.getStringArrayListExtra("ordersLst");
        orderTotal = intent.getDoubleArrayExtra("orderTotalLst");
    }

    /**
     * This method set up the List View and the Spinner widget.
     */
    private void settingUp() {
        arrayAdapterForSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item
                , orderNumber);
        arrayAdapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(arrayAdapterForSpinner);
    }

    /**
     * This method will set Listener for the Spinner drop down selection.
     */
    private void setListenerForSpinner() {
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                ordersText.setText(orders.get(i));
                soOrderTotal.setText("Order Total: $" + orderTotal[i]);
                arrayAdapterForSpinner.notifyDataSetChanged();
            }

            /**
             * This method is
             * @param adapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    /**
     * This method handles the situation when the clear button is clicked
     */
    private void clickClear() {
        clearOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is Overrided from the OnclickListener interface, it will listen the
             * on click event.
             *
             * @param view The view that
             */
            @Override
            public void onClick(View view) {
                if (orders.size() == 0 || orderNumber.size() == 0) {
                    return;
                }
                for (int i = 0; i < orderNumber.size(); i++) {
                    System.out.println(orderNumber.get(i));
                }
                orders.remove(position);
                orderNumber.remove(position);
                removeFromArray(position);
                for (int i = 0; i < orderNumber.size(); i++) {
                    System.out.println(orderNumber.get(i));
                }
                arrayAdapterForSpinner.notifyDataSetChanged();
                settingUp();
                setListenerForSpinner();
                if (orders.size() == 0) {
                    ordersText.setText("empty. Add a order to view order detail");
                    soOrderTotal.setText("Order Total: $");
                }
                arrayAdapterForSpinner.notifyDataSetChanged();
                Toast.makeText(StoreOrdersActivity.this, "Successfully cleared",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method handles the situation for click the export button.
     */
    private void clickExport(){
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("exportedStoreOrders.txt"));
                    writer.write("Store Orders\n\n");
                    for (int i = 0; i < orders.size(); i++) {
                        writer.write(orders.get(i));
                        writer.write("\n");
                    }
                    writer.close();
                    Toast.makeText(StoreOrdersActivity.this, "exported to exportedStoreOrders.txt"  ,
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(StoreOrdersActivity.this, "failed export "  ,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This method is overrided from AppCompatActivity. It will set up the view.
     *
     * @param savedInstanceState The Bundld that might hold data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        initializeUI();
        receivingDispatcher();
        clickBack();
        setListenerForSpinner();
        settingUp();
        clickClear();
        clickExport();
    }

    private void removeFromArray(int position){
        double[] newArray = new double[orderTotal.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i > position){
                newArray[i] = orderTotal[i + 1];
            }else{
                newArray[i] = orderTotal[i];
            }
        }
        orderTotal = newArray;
    }
}

