package com.example.kartkowka18092023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button genBtn;
    private EditText dzien;
    private EditText miesiac;
    private EditText rok;
    private EditText plec;
    private TextView wynik;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genBtn = findViewById(R.id.generateBtn);
        dzien = findViewById(R.id.dzien);
        miesiac = findViewById(R.id.miesiac);
        rok = findViewById(R.id.rok);
        plec = findViewById(R.id.plec);
        wynik = findViewById(R.id.wynik);

        genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean danePoprawne = true;
                String pesel = "";
                String rokTemp = "";
                String miesiacTemp = "";
                String dzienTemp = "";
                String plecTemp = "";

                rokTemp = rok.getText().toString().substring(2, 4);
                miesiacTemp = miesiac.getText().toString();
                dzienTemp = dzien.getText().toString();
                plecTemp = plec.getText().toString().toUpperCase();

                Log.d("res", rokTemp + "|" + miesiacTemp + "|" + dzienTemp + "|" + plecTemp);

                if(Integer.valueOf(rok.getText().toString()) < 1900 || Integer.valueOf(rok.getText().toString()) > 2100){
                    danePoprawne = false;
                }
                if(Integer.valueOf(miesiacTemp) < 1 || Integer.valueOf(miesiacTemp) > 12){
                    danePoprawne = false;
                }
                if(Integer.valueOf(dzienTemp) < 1 || Integer.valueOf(dzienTemp) > 31){
                    danePoprawne = false;
                }

                if(plecTemp.equals("K") || plecTemp.equals("M")){
                    danePoprawne = true;
                }
                else{
                    danePoprawne = false;
                }

                if(danePoprawne) {
                    pesel += rokTemp;
                    if (Integer.parseInt(rok.getText().toString()) < 2000) {
                        if (miesiacTemp.length() == 1) {
                            pesel += "0";
                        }
                        pesel += miesiacTemp;
                    } else if (Integer.parseInt(rok.getText().toString()) < 2100) {
                        if (miesiacTemp.length() == 1) {
                            pesel += "2";
                            pesel += miesiacTemp;
                        } else if (miesiacTemp.substring(0, 1).equals("0")) {
                            pesel += "2";
                            pesel += miesiacTemp.substring(1, 2);
                        } else {
                            pesel += "3";
                            pesel += miesiacTemp.substring(1, 2);
                        }

                    } else {
                        if (miesiacTemp.length() == 1) {
                            pesel += "4";
                            pesel += miesiacTemp;
                        } else if (miesiacTemp.substring(0, 1).equals("0")) {
                            pesel += "4";
                            pesel += miesiacTemp.substring(1, 2);
                        } else {
                            pesel += "5";
                            pesel += miesiacTemp.substring(1, 2);
                        }
                    }
                    pesel += dzienTemp;

                    for (int i = 0; i < 3; i++) {
                        int a = getRandomNumber(0,9);
                        pesel += String.valueOf(a);
                    }

                    boolean plec_bul_bul_bul = true;

                    while (plec_bul_bul_bul) {
                        int a = getRandomNumber(0,9);
                        if (plecTemp.equals("K")) {
                            if (a % 2 == 0) {
                                pesel += String.valueOf(a);
                                plec_bul_bul_bul = false;
                            }
                        } else {
                            if (a % 2 != 0) {
                                pesel += String.valueOf(a);
                                plec_bul_bul_bul = false;
                            }
                        }
                    }

                    int p1 = Integer.parseInt(pesel.substring(0, 1));
                    int p2 = Integer.parseInt(pesel.substring(1, 2));
                    int p3 = Integer.parseInt(pesel.substring(2, 3));
                    int p4 = Integer.parseInt(pesel.substring(3, 4));
                    int p5 = Integer.parseInt(pesel.substring(4, 5));
                    int p6 = Integer.parseInt(pesel.substring(5, 6));
                    int p7 = Integer.parseInt(pesel.substring(6, 7));
                    int p8 = Integer.parseInt(pesel.substring(7, 8));
                    int p9 = Integer.parseInt(pesel.substring(8, 9));
                    int p10 = Integer.parseInt(pesel.substring(9, 10));

                    Log.d("res", "liczby: " + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10);

                    int kontrolna_temp = 0;
                    kontrolna_temp += p1 * 1;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p2*3 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p3*7 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p4*9 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p5 * 1;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p6*3 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p7*7 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p8*9 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p9 * 1;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    kontrolna_temp += p10*3 % 10;
                    Log.d("control", String.valueOf(kontrolna_temp));
                    int kontrolna = 10 - kontrolna_temp%10;
                    if (kontrolna == 10) {
                        kontrolna = 0;
                    }

                    pesel += String.valueOf(kontrolna);
                }
                else{
                    pesel="bledne dane";
                }

                wynik.setText(pesel);
            }
        });
    }
}