/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText name = findViewById(R.id.name_view);
        String nme = name.getText().toString();
        if (nme.isEmpty()){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Enter a name", Toast.LENGTH_SHORT);
            toast.show();
            setContentView(R.layout.activity_main);
            return;
        }
        CheckBox whippedCreme = findViewById(R.id.whipped_check);
        boolean hasChecked= whippedCreme.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChChecked = chocolate.isChecked();
//        display(quantity);
        String priceMessage ;
        int price=calculatePrice(quantity,hasChecked , hasChChecked);
        priceMessage = createOrderSummary(price, hasChecked , hasChChecked, nme);

        displayMessage(priceMessage);
        findViewById(R.id.next_button).setEnabled(false);
        findViewById(R.id.reorder_button).setEnabled(true);
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Order from Just Java App");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//        display(priceMessage);
    }

    public void resubmitOrder(View view){

        setContentView(R.layout.activity_main);
        findViewById(R.id.next_button).setEnabled(true);
        findViewById(R.id.reorder_button).setEnabled(false);

    }

    private String createOrderSummary(int price, boolean hasChecked , boolean hasChChecked, String nme) {
        return "Name : "+ nme +"\nQuantity "+ quantity+"\nAdd Whipped creme: "+hasChecked+"\nAdd Chocolate Topping: "+hasChChecked+"\nTotal : "+price+"\nThank You!!";
    }

    /** increment by one
    */
    public void increment(View view)
    {
        if (quantity==100) {
            Toast.makeText(this,"Too many coffee orders",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
       // displayPrice(quantity*5);
    }
    public void decrement(View view)
    {
        if(quantity==1) {
            Toast.makeText(this,"Cannot accept negative numbers",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity--;
        display(quantity);

//        displayPrice(quantity*5);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView quantityTextView = (TextView) findViewById(R.id.message_text_view);
        quantityTextView.append("\n" + message);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */

    private int calculatePrice(int quantity,boolean hasChecked , boolean hasChChecked) {
        int price=5;
        if(hasChChecked==true)
            price=price+2;
        if(hasChecked==true)
            price=price+1;
        price = quantity * price;
        return price;
    }
}