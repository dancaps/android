package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String KEY_TOTAL_PIZZAS = "totalPizzas";
    private int mTotalPizzas;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the widgets to fields
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);

        // Restore state
        if (savedInstanceState != null) {
            mTotalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS);
            displayTotal();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TOTAL_PIZZAS, mTotalPizzas);
    }

    public void calculateClick(View view) {

        // Get how many are attending the party
        int numAttend;
        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;
        if (checkedId == R.id.light_radio_button) {
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        else if (checkedId == R.id.medium_radio_button) {
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }

        // Show the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        mTotalPizzas = calc.getTotalPizzas();
        displayTotal();
    }

    private void displayTotal() {
        String totalText = getString(R.string.total_pizzas_num, mTotalPizzas);
        mNumPizzasTextView.setText(totalText);
    }
}