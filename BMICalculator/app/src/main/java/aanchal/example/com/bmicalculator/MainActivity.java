package aanchal.example.com.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnRegister;
    TextView tvDisplay;
    EditText etName,etAge,etPhone;
    RadioGroup rgGender;
    RadioButton rbMale,rbFemale;
    Typeface typeface;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        typeface=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Light.otf");

        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnRegister.setTypeface(typeface);
        btnRegister.setTextColor(Color.parseColor("#280707"));
        tvDisplay=(TextView)findViewById(R.id.tvDisplay);
        tvDisplay.setTypeface(typeface);
        tvDisplay.setTextColor(Color.parseColor("#280707"));
        etName=(EditText)findViewById(R.id.etName);
        etName.setTypeface(typeface);
        etName.setTextColor(Color.parseColor("#280707"));
        etAge=(EditText)findViewById(R.id.etAge);
        etAge.setTypeface(typeface);
        etAge.setTextColor(Color.parseColor("#280707"));
        etPhone=(EditText)findViewById(R.id.etPhone);
        etPhone.setTypeface(typeface);
        etPhone.setTextColor(Color.parseColor("#280707"));
        rgGender=(RadioGroup)findViewById(R.id.rgGender);
        rbFemale=(RadioButton)findViewById(R.id.rbFemale);
        rbFemale.setTypeface(typeface);
        rbFemale.setTextColor(Color.parseColor("#280707"));
        rbMale=(RadioButton)findViewById(R.id.rbMale);
        rbMale.setTypeface(typeface);
        rbMale.setTextColor(Color.parseColor("#280707"));

        sp1=getSharedPreferences("MyP1",MODE_PRIVATE);

        String name=sp1.getString("name","");
        int age=sp1.getInt("age",0);
        String phone=sp1.getString("phone","");
        if(!name.equals(""))
        {
            Intent i= new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(i);
            finish();
        }
        else
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(etName.length()==0) //validating if name is empty
                    {
                        etName.setError("Name is empty");
                        etName.requestFocus();
                        return;
                    }
                    else
                    if(etAge.length()==0) {

                        etAge.setError("Age is Empty");
                        etAge.requestFocus();
                        return;
                    }
                    else
                    if(etPhone.length()==0) {
                    etPhone.setError("Enter Valid Phone Number");
                        etPhone.requestFocus();
                        return;
                    }
                    else
                    if(etPhone.length()!=10) {
                        etPhone.setError("Enter Valid Phone Number");
                        etPhone.requestFocus();
                        return;
                    }

                    int s1=rgGender.getCheckedRadioButtonId();
                    RadioButton rb=(RadioButton)findViewById(s1);
                    String gender=rb.getText().toString();

                    String name=etName.getText().toString();
                    int age=Integer.parseInt(etAge.getText().toString());
                    String phone=etPhone.getText().toString();

                    SharedPreferences.Editor editor=sp1.edit(); //making in entry in shared preference
                    editor.putString("name",name);
                    editor.putInt("age",age);
                    editor.putString("phone",phone);
                    editor.putString("gender",gender);
                    editor.commit();
                    Intent i= new Intent(MainActivity.this,WelcomeActivity.class);//tranfer control
                    startActivity(i);
                    finish();
                }
            });

    }


    }

