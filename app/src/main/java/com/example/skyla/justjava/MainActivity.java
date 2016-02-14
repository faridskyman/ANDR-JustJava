package com.example.skyla.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //display(1);
        displayPrice(returnTxtViewAsInt(getTxtView(R.id.quantity_text_view)) * 4);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = getTxtView(R.id.quantity_text_view);
        int val = returnTxtViewAsInt(quantityTextView) + number;

        quantityTextView.setText("" + val);
    }

    public void incrementQuantity(View view)
    {
        updateQuantity(true);
    }

    public void decrementQuantity(View view)
    {
        updateQuantity(false);
    }


    public void updateQuantity(boolean isIncrement)
    {
        TextView quantityTextView = getTxtView(R.id.quantity_text_view);
        int val = returnTxtViewAsInt(quantityTextView);
        if (isIncrement) {
            if (val <3)
            {
                val = 3;
            }
            else {
                val++;
            }
        }
        else {
            if (val >=2) {
                val--;
            }
            else
            {
                val = 1;
            }
        }
        quantityTextView.setText("" + val);
        displayPrice(returnTxtViewAsInt(getTxtView(R.id.quantity_text_view)) * 4);
    }




    private int returnTxtViewAsInt(TextView tv)
    {
        return Integer.parseInt(tv.getText().toString());
    }

    private TextView getTxtView(int id)
    {
        return (TextView)findViewById(id);
    }

    private void  displayPrice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


}
