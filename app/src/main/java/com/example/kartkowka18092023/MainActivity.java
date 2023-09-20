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

                String rokTemp = rok.getText().toString().substring(2,4);
                String miesiacTemp = miesiac.getText().toString();
                String dzienTemp = dzien.getText().toString();
                String plecTemp = plec.getText().toString().toUpperCase();
                String pesel = "";

                Log.d("res",rokTemp + "|" + miesiacTemp + "|" + dzienTemp + "|" + plecTemp);

                boolean danePoprawne = true;

                if(Integer.valueOf(rok.getText().toString()) < 1900 || Integer.valueOf(rok.getText().toString()) > 2100){
                    danePoprawne = false;
                    pesel="rok";
                }
                if(Integer.valueOf(miesiacTemp) < 1 || Integer.valueOf(miesiacTemp) > 12){
                    danePoprawne = false;
                    pesel="mies";
                }
                if(Integer.valueOf(dzienTemp) < 1 || Integer.valueOf(dzienTemp) > 31){
                    danePoprawne = false;
                    pesel="dzien";
                }

                if(plecTemp.equals("K") || plecTemp.equals("M")){
                    danePoprawne = true;
                }
                else{
                    danePoprawne = false;
                    pesel="plec";
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

                    int kontrolna_temp = 0;
                    kontrolna_temp += p1 * 1;
                    if (p2 * 3 > 9) {
                        kontrolna_temp += p2*3 % 10;
                    }
                    if (p3 * 7 > 9) {
                        kontrolna_temp += p3*7 % 10;
                    }
                    if (p4 * 9 > 9) {
                        kontrolna_temp += p4*9 % 10;
                    }
                    kontrolna_temp += p5 * 1;
                    if (p6 * 3 > 9) {
                        kontrolna_temp += p6*3 % 10;
                    }
                    if (p7 * 7 > 9) {
                        kontrolna_temp += p7*7 % 10;
                    }
                    if (p8 * 9 > 9) {
                        kontrolna_temp += p8*9 % 10;
                    }
                    kontrolna_temp += p9 * 1;
                    if (p10 * 3 > 9) {
                        kontrolna_temp += p10*3 % 10;
                    }
                    if (kontrolna_temp > 9) {
                        kontrolna_temp = kontrolna_temp % 10;
                    }
                    int kontrolna = 10 - kontrolna_temp;
                    if (kontrolna == 10) {
                        kontrolna = 0;
                    }

                    pesel += String.valueOf(kontrolna);
                }

                wynik.setText(pesel);
            }
        });
    }
}