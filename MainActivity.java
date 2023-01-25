package com.example.dialogboxes.rezakov303;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CheckBox[] checkBoxesProduct = new CheckBox[4];
    EditText[] editTextsAmount = new EditText[4];
    EditText[] editTextsPrice = new EditText[4];

    int[] productsAmount = new int[4]; // Количество товаров
    float[] productsPrice = new float[4]; // Цены на продукты


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxesProduct[0] = findViewById(R.id.AppleCheckBox);
        checkBoxesProduct[1] = findViewById(R.id.StrawberryCheckBox);
        checkBoxesProduct[2] = findViewById(R.id.BlueberryCheckBox);
        checkBoxesProduct[3] = findViewById(R.id.PotatoesCheckBox);

        editTextsAmount[0] = findViewById(R.id.appleAmount);
        editTextsAmount[1] = findViewById(R.id.strawberryAmount);
        editTextsAmount[2] = findViewById(R.id.blueberryAmount);
        editTextsAmount[3] = findViewById(R.id.potatoesAmount);

        editTextsPrice[0] = findViewById(R.id.applePrice);
        editTextsPrice[1] = findViewById(R.id.strawberryPrice);
        editTextsPrice[2] = findViewById(R.id.blueberryPrice);
        editTextsPrice[3] = findViewById(R.id.potatoesPrice);
    }

    public void Calculate_onClick(View v)
    {
        int amount;
        String message = "", checkBoxName = "";
        float sumProduct, price, finalSum = 0;

        if (!GetProductsPrice())
            return;

        for (int i = 0; i < 4; i++)
        {
            if (checkBoxesProduct[i].isChecked())
            {
                amount = Integer.parseInt(editTextsAmount[i].getText().toString());
                price = productsPrice[i];
                checkBoxName = checkBoxesProduct[i].getText().toString();
                sumProduct = amount * price;
                finalSum += (amount * price);

                message += String.format("%s: %d*%.2f = %.2f рублей\n", checkBoxName, amount, price, sumProduct);
            }
        }

        String str2 = String.format("\nСумма: %.2f рублей\n", finalSum);
        message += str2;

        AlertDialog dialog = CreateDialog(message);
        dialog.show();
    }

    private boolean GetProductsPrice()
    {
        int i = 0, type = 0;

        try {
            for (; i < 4; i++) {
                type = 1;
                productsAmount[i] = Integer.parseInt(editTextsAmount[i].getText().toString());

                type = 2;
                productsPrice[i] = Float.parseFloat(editTextsPrice[i].getText().toString());

                type = 0;
            }
        }
        catch (Exception e)
        {
            if (type == 1) {
                editTextsAmount[i].setError("Введите значение!");
            }
            else if (type == 2)
            {
                editTextsPrice[i].setError("Введите значение!");
            }

            return false;
        }

        return true;
    }

    private AlertDialog CreateDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ваши товары").setMessage(message).setPositiveButton("ОК", (dialog, id) -> dialog.cancel());

        return builder.create();
    }

}
