package aanchal.example.com.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BMIResult extends AppCompatActivity {
TextView txtResult1,txtResult2,txtResult3,txtResult4,txtResult5;
    Button btnShare,btnSave,btnBack;
    Typeface typeface;
    SharedPreferences sp2,sp1;
    DbHandler db;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        typeface= Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Light.otf");

        txtResult1=(TextView)findViewById(R.id.txtResult1);
        txtResult1.setTypeface(typeface);
        txtResult2=(TextView)findViewById(R.id.txtResult2);
        txtResult2.setTypeface(typeface);
        txtResult3=(TextView)findViewById(R.id.txtResult3);
        txtResult3.setTypeface(typeface);
        txtResult4=(TextView)findViewById(R.id.txtResult4);
        txtResult4.setTypeface(typeface);
        txtResult5=(TextView)findViewById(R.id.txtResult5);
        txtResult5.setTypeface(typeface);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setTypeface(typeface);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setTypeface(typeface);
        btnShare=(Button)findViewById(R.id.btnShare);
        btnShare.setTypeface(typeface);
        db=new DbHandler(this);


        sp2=getSharedPreferences("MyP2",MODE_PRIVATE);
        float bmi = sp2.getFloat("BMI",0);
        final int wt=sp2.getInt("Weight",0);
        final String fs = String.format("%.02f", bmi);

        sp1=getSharedPreferences("MyP1",MODE_PRIVATE);
        String name=sp1.getString("name","");
        int age=sp1.getInt("age",0);
        String phone=sp1.getString("phone","");
        String gender=sp1.getString("gender","");
        final String bmires;

        if(bmi<18.5)
        {
            txtResult1.setText("Your BMI is  " + fs + " and You are Underweight");
            txtResult1.setTextColor(Color.parseColor("#280707"));
            bmires="Your BMI is  " + fs + " and You are Underweight";

            txtResult2.setText("Below 18.5 is UnderWeight");
            txtResult2.setTextColor(Color.parseColor("#a71d3d"));
            txtResult3.setText("Between 18.5 to 25 is Normal");
            txtResult3.setTextColor(Color.parseColor("#417143"));
            txtResult4.setText("Between 25 to 30 is Overweight");
            txtResult4.setTextColor(Color.parseColor("#417143"));
            txtResult5.setText("More than 30 is obese");
            txtResult5.setTextColor(Color.parseColor("#417143"));
        }
        else
            if(bmi>18.5 && bmi<25)
            {
                txtResult1.setText("Your BMI is  " + fs + " and You are Normal");
                txtResult1.setTextColor(Color.parseColor("#280707"));
                bmires="Your BMI is  " + fs + " and You are Normal";

                txtResult2.setText("Below 18.5 is UnderWeight");
                txtResult2.setTextColor(Color.parseColor("#417143"));
                txtResult3.setText("Between 18.5 to 25 is Normal");
                txtResult3.setTextColor(Color.parseColor("#a71d3d"));
                txtResult4.setText("Between 25 to 30 is Overweight");
                txtResult4.setTextColor(Color.parseColor("#417143"));
                txtResult5.setText("More than 30 is obese");
                txtResult5.setTextColor(Color.parseColor("#417143"));
            }
        else if(bmi>26 && bmi<30)
            {
                txtResult1.setText("Your BMI is  " + fs + " and You are Overweight");
                txtResult1.setTextColor(Color.parseColor("#280707"));
                bmires="Your BMI is  " + fs + " and You are Overweight";

                txtResult2.setText("Below 18.5 is UnderWeight");
                txtResult2.setTextColor(Color.parseColor("#417143"));
                txtResult3.setText("Between 18.5 to 25 is Normal");
                txtResult3.setTextColor(Color.parseColor("#417143"));
                txtResult4.setText("Between 25 to 30 is Overweight");
                txtResult4.setTextColor(Color.parseColor("#a71d3d"));
                txtResult5.setText("More than 30 is obese");
                txtResult5.setTextColor(Color.parseColor("#417143"));

            }
        else

                {
                    txtResult1.setText("Your BMI is  " + fs + " and You are Obese");
                    txtResult1.setTextColor(Color.parseColor("#280707"));
                    bmires="Your BMI is  " + fs + " and You are Obese";

                    txtResult2.setText("Below 18.5 is UnderWeight");
                    txtResult2.setTextColor(Color.parseColor("#417143"));
                    txtResult3.setText("Between 18.5 to 25 is Normal");
                    txtResult3.setTextColor(Color.parseColor("#417143"));
                    txtResult4.setText("Between 25 to 30 is Overweight");
                    txtResult4.setTextColor(Color.parseColor("#417143"));
                    txtResult5.setText("More than 30 is obese");
                    txtResult5.setTextColor(Color.parseColor("#a71d3d"));
                }


        final String bmiresult="Name : " + name + System.lineSeparator() +
                "Age : " + age + System.lineSeparator() +
                "Phone : " + phone + System.lineSeparator() +
                "Sex : " + gender + System.lineSeparator() + bmires;


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,bmiresult);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
                        .getInstance().getTime());
                String finalbmi= " Bmi is: " + fs + " Weight: " + wt ;
                Date today=new Date();
                String t="Date: " + today.toString();
                db.addRecord(t,finalbmi,date);
                //Toast.makeText(BMIResult.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });



}}
