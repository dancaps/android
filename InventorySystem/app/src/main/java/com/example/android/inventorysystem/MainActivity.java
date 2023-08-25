package com.example.android.inventorysystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment welcomeScreen = new WelcomeScreen();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_frame, welcomeScreen)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment welcomeScreen = new WelcomeScreen();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_frame, welcomeScreen)
                .commit();
    }

    public void customerMgmt(View view){
        Toast.makeText(MainActivity.this, "Manage a Customer", Toast.LENGTH_SHORT).show();

        Fragment customerScreen = new CustomerScreen();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, customerScreen)
                .commit();
    }

    public void inventoryMgmt(View view){
        Toast.makeText(MainActivity.this, "Manage Inventory", Toast.LENGTH_SHORT).show();

        Fragment inventoryScreen = new InventoryScreen();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, inventoryScreen)
                .commit();
    }

    public void sendInvoice(View view){
        Toast.makeText(MainActivity.this, "EMAIL a list of invoices", Toast.LENGTH_SHORT).show();
    }

    public void displayInvoice(View view){
        Toast.makeText(MainActivity.this, "DISPLAY a list of the invoices", Toast.LENGTH_SHORT).show();
    }

    public void addCustomer(View view) {
        Toast.makeText(MainActivity.this, "ADDING a customer to the external database", Toast.LENGTH_SHORT).show();
    }

    public void getCustomer(View view) {
        Toast.makeText(MainActivity.this, "GETTING a customer from the external database", Toast.LENGTH_SHORT).show();
    }

    public void updateCustomer(View view) {
        Toast.makeText(MainActivity.this, "UPDATING a customer in the external database", Toast.LENGTH_SHORT).show();
    }

    public void deleteCustomer(View view) {
        Toast.makeText(MainActivity.this, "DELETING an item to the external database", Toast.LENGTH_SHORT).show();
    }

    public void addInventory(View view) {
        Toast.makeText(MainActivity.this, "ADDING an item to the external database", Toast.LENGTH_SHORT).show();

        EditText itemName = findViewById(R.id.itemName);
        EditText itemDesc = findViewById(R.id.itemDesc);
        EditText price = findViewById(R.id.price);
        EditText itemId = findViewById(R.id.itemId);

        String itemNameTXT = itemName.getText().toString();
        String itemDescTXT = itemDesc.getText().toString();
        String priceTXT = price.getText().toString();
        String itemIdTXT = itemId.getText().toString();

        Boolean checkAddData = DB.addData(itemNameTXT, itemDescTXT, priceTXT, itemIdTXT);

        if (checkAddData == true)
            Toast.makeText(MainActivity.this, "New Entry ADDED", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "FAILED to Add New Entry", Toast.LENGTH_SHORT).show();
    }

    public void getInventory(View view) {
        Toast.makeText(MainActivity.this, "GETTING an item from the external database", Toast.LENGTH_SHORT).show();

        Cursor res = DB.getData();
        if (res.getCount()==0) {
            Toast.makeText(MainActivity.this, "NO Entries to View", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("ITEM NAME: "+res.getString(0)+"\n");
            buffer.append("ITEM DESCRIPTION: "+res.getString(1)+"\n");
            buffer.append("ITEM PRICE: "+res.getString(2)+"\n");
            buffer.append("ITEM ID: "+res.getString(3)+"\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("INVENTORY ITEMS");
        builder.setMessage((buffer.toString()));
        builder.show();
    }

    public void updateInventory(View view) {
        Toast.makeText(MainActivity.this, "UPDATING an item in the external database", Toast.LENGTH_SHORT).show();

        EditText itemName = findViewById(R.id.itemName);
        EditText itemDesc = findViewById(R.id.itemDesc);
        EditText price = findViewById(R.id.price);
        EditText itemId = findViewById(R.id.itemId);

        String itemNameTXT = itemName.getText().toString();
        String itemDescTXT = itemDesc.getText().toString();
        String priceTXT = price.getText().toString();
        String itemIdTXT = itemId.getText().toString();

        Boolean checkUpdateData = DB.updateData(itemNameTXT, itemDescTXT, priceTXT, itemIdTXT);

        if (checkUpdateData == true)
            Toast.makeText(MainActivity.this, "Item UPDATED", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "FAILED to Update", Toast.LENGTH_SHORT).show();
    }

    public void deleteInventory(View view) {
        EditText itemId = findViewById(R.id.itemId);
        String idTXT = itemId.getText().toString();

        Boolean checkDeleteData = DB.deleteData(idTXT);

        if (checkDeleteData == true)
            Toast.makeText(MainActivity.this, "DELETING an item from the external database", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "FAILED to Delete", Toast.LENGTH_SHORT).show();
    }
}