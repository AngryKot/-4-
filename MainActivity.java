package com.example.pereclanddialog03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView mint;
    private ImageView apple;
    private ImageView blueberry;
    private ImageView grape;

    private CheckBox cB[] = new CheckBox[4];
    private EditText et_Count[] = new EditText[4];
    private EditText et_Price[] = new EditText[4];
    RadioButton rb_Alert;
    RadioButton rb_TextView;
    RadioButton rb_Screen;
    TextView tv_Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //настройка imageview
        //Изображения в Drowable
        mint = findViewById(R.id.imv_Mint);
        mint.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.amint));
        apple = findViewById(R.id.imv_App);
        apple.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.apple));
        blueberry = findViewById(R.id.imv_Blue);
        blueberry.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.blueberry));
        grape= findViewById(R.id.imv_Grape);
        grape.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.grape));

        rb_Alert=findViewById(R.id.rb_Alert);
        rb_TextView=findViewById(R.id.rb_TextView);
        rb_Screen = findViewById(R.id.rb_Screen);
        tv_Total = findViewById(R.id.tv_Total);


        //настройка checkbox, edittext
        cB[0] = findViewById(R.id.chkBox_Mint);
        cB[1] = findViewById(R.id.chkBox_App);
        cB[2] = findViewById(R.id.chkBox_Blue);
        cB[3] = findViewById(R.id.chkBox_Grape);

        et_Count[0] = findViewById(R.id.et_AmoMint);
        et_Count[1] = findViewById(R.id.et_AmoApp);
        et_Count[2] = findViewById(R.id.et_AmoBlue);
        et_Count[3] = findViewById(R.id.et_AmoGrape);

        et_Price[0] = findViewById(R.id.et_PriceMint);
        et_Price[1] = findViewById(R.id.et_PriceApp);
        et_Price[2] = findViewById(R.id.et_PriceBlue);
        et_Price[3] = findViewById(R.id.et_PriceGrape);
    }
    public void onClick(View view) {
        Double sum = 0.00;
        String rep2 = " ";
        Double sum_total = 0.00;
        int n = cB.length;
        for (int i = 0; i < n; i++) {
            if (!et_Count[i].getText().toString().isEmpty() && !et_Price[i].getText().toString().isEmpty()) {
                if (cB[i].isChecked()) {
                    Double Count = Double.parseDouble(et_Count[i].getText().toString());
                    Double Price = Double.parseDouble(et_Price[i].getText().toString());
                    sum = Count * Price;
                    sum_total += Count * Price;
                    rep2 += String.format("%s: %.2f*%.2f = %.2f rubles\n", cB[i].getText().toString(),
                            Count, Price, sum);

                }
            } else {
                tv_Total.setText("Please, fill the fields");
            }
        }
            rep2+=String.format("Total = %.2f rubles\n",sum_total);

            if(!et_Count[0].getText().toString().isEmpty()||!et_Count[1].getText().toString().isEmpty()
                    ||!et_Count[2].getText().toString().isEmpty()||
                    !et_Count[3].getText().toString().isEmpty()){
                if(!et_Price[0].getText().toString().isEmpty()||
                        !et_Price[1].getText().toString().isEmpty()||!et_Price[2].getText().toString().isEmpty()
                        ||!et_Price[3].getText().toString().isEmpty()){
                    if (rb_TextView.isChecked()) {
                        tv_Total.setText(rep2);
                    }

                    if (rb_Alert.isChecked()) {
                        Toast.makeText(this, rep2, Toast.LENGTH_SHORT).show();

                    }
                    if (rb_Screen.isChecked()) {
                        AlertDialog dialog = createDialog(rep2);
                        dialog.show();
                    }
                }
            }
            else {
                tv_Total.setText("Please, fill the fields");
            }
        }

    public AlertDialog createDialog (String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your cheque")
                .setIcon(R.drawable.check)
                .setMessage(message)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });
        return builder.create();
    }




}