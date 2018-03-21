package aanchal.example.com.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.location.Geocoder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class WelcomeActivity extends AppCompatActivity {

    TextView tvWelcome,tvLocation,tvHeight,tvFeet,tvInch,tvWeight;
    Spinner spnFeet,spnInch;
    EditText etWeight;
    Button btnCalculate,btnViewHistory;
    Typeface typeface;
    SharedPreferences sp2,sp1;
    Double temperature;

    double height=0,h=0;
    private static final int MY_PERMISSION_REQUEST_LOCATION =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        typeface=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Light.otf");




        tvWelcome=(TextView)findViewById(R.id.tvWelcome);
        tvWelcome.setTypeface(typeface);
        tvWelcome.setTextColor(Color.parseColor("#280707"));
        tvLocation=(TextView)findViewById(R.id.tvLocation);
        tvLocation.setTypeface(typeface);
        tvLocation.setTextColor(Color.parseColor("#280707"));
        tvHeight=(TextView)findViewById(R.id.tvHeight);
        tvHeight.setTypeface(typeface);
        tvHeight.setTextColor(Color.parseColor("#280707"));
        tvFeet=(TextView)findViewById(R.id.tvFeet);
        tvFeet.setTypeface(typeface);
        tvFeet.setTextColor(Color.parseColor("#280707"));
        tvInch=(TextView)findViewById(R.id.tvInch);
        tvInch.setTypeface(typeface);
        tvInch.setTextColor(Color.parseColor("#280707"));
        tvWeight=(TextView)findViewById(R.id.tvWeight);
        tvWeight.setTypeface(typeface);
        tvWeight.setTextColor(Color.parseColor("#280707"));
        spnFeet=(Spinner)findViewById(R.id.spnFeet);
        spnInch=(Spinner)findViewById(R.id.spnInch);
        etWeight=(EditText)findViewById(R.id.etWeight);
        etWeight.setTypeface(typeface);
        etWeight.setTextColor(Color.parseColor("#280707"));
        btnCalculate=(Button)findViewById(R.id.btnCalculate);
        btnCalculate.setTypeface(typeface);
        btnCalculate.setTextColor(Color.parseColor("#280707"));
        btnViewHistory=(Button)findViewById(R.id.btnViewHistory);
        btnViewHistory.setTypeface(typeface);
        btnViewHistory.setTextColor(Color.parseColor("#280707"));



        sp1=getSharedPreferences("MyP1",MODE_PRIVATE);

        String name=sp1.getString("name","");
        tvWelcome.setText("Welcome , "+ name);


        ArrayList<String> feet=new ArrayList<>();
        feet.add("1");
        feet.add("2");
        feet.add("3");
        feet.add("4");
        feet.add("5");
        feet.add("6");
        feet.add("7");

        ArrayAdapter<String> feetAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,feet);
        feetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnFeet.setAdapter(feetAdapter);
        spnFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                double h1;
                h1=Double.parseDouble(item);
                height=h1*0.3048;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> inches=new ArrayList<>();
        inches.add("0");
        inches.add("1");
        inches.add("2");
        inches.add("3");
        inches.add("4");
        inches.add("5");
        inches.add("6");
        inches.add("7");
        inches.add("8");
        inches.add("9");
        inches.add("10");
        inches.add("11");

        ArrayAdapter<String> inchesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,inches);
        inchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnInch.setAdapter(inchesAdapter);
        spnInch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city=adapterView.getItemAtPosition(i).toString();
                double h1;
                h1=Double.parseDouble(city);
                h=h1*0.0254;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double r=0;
                r= height+h;

                if(r<0.3301)
                {
                    Toast.makeText(WelcomeActivity.this, "Height should be at least 1 feet 1 inch", Toast.LENGTH_SHORT).show();
                }
                else
                if(etWeight.length()==0)
                {
                    etWeight.setError("Please Enter Weight");
                    etWeight.requestFocus();
                    return;
                }
                else
                    if(Integer.parseInt(etWeight.getText().toString())<10)
                {
                    etWeight.setError("Weight should be at least 10kgs ");
                    etWeight.requestFocus();
                    return;
                }
                else
                {
                    double h2;
                    h2=height+h;
                    int wt = Integer.parseInt(etWeight.getText().toString());
                    double h1 = h2 * h2;
                    float bmi = (float) (wt / h1);
                    String fs = String.format("%.02f", bmi);

                    if(bmi>45)
                    {
                        Toast.makeText(WelcomeActivity.this, "Check your numbers", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        sp2=getSharedPreferences("MyP2",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=sp2.edit();
                        editor1.putFloat("BMI",bmi);
                        editor1.putInt("Weight",wt);
                        editor1.commit();
                     Intent i=new Intent(WelcomeActivity.this,BMIResult.class);
                    startActivity(i);
                }
                }
            }
        });


        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WelcomeActivity.this,ViewActivity.class);
                startActivity(i);
            }
        });



        //Location Part
        if(ContextCompat.checkSelfPermission(WelcomeActivity.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(WelcomeActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(WelcomeActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

            }}
        else
        {
            LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                tvLocation.setText(herelocation(location.getLatitude(),location.getLongitude()));

            }
            catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Please Check GPS ", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST_LOCATION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(WelcomeActivity.this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try {
                            tvLocation.setText(herelocation(location.getLatitude(),location.getLongitude()));

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Please Enable GPS", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(this, " No Permission Granted ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


    } //end of OnCreate Method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.Website)
        {
            Intent i= new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://"+"www.google.com"));
            startActivity(i);
        }
        if(item.getItemId() == R.id.About)
        {
            Toast.makeText(this,"Developed by Aanchal Rohira",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }


    //get city name
    public String herelocation(double lat,double lon) {
        String curCity = "";

        Geocoder geocoder = new Geocoder(WelcomeActivity.this, Locale.getDefault());
        List<Address> addressList;
        try {

            addressList=geocoder.getFromLocation(lat,lon,1);
            if(addressList.size()>0)
            {
                curCity=addressList.get(0).getLocality();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return curCity;
    }





   /* class Task1 extends AsyncTask<String,Void,Double> {
        double temp;
        @Override
        protected Double doInBackground(String... strings) {
            String json="",line="";

            try {
                URL url=new URL(strings[0]); // website name written above is passed here
                HttpURLConnection hc=(HttpURLConnection) url.openConnection()  ;
                hc.connect();

                InputStream is=hc.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);


                while( (line=br.readLine()) !=null)
                {
                    json = json + line + "\n"; //once connected we are downloading the json data
                }

                if(json!=null)
                {
                    JSONObject j = new JSONObject(json);
                    JSONObject q= j.getJSONObject("main");
                    temp=q.getDouble("temp");
                }


            }
            catch (Exception e)
            {
                Toast.makeText(WelcomeActivity.this, "" + e, Toast.LENGTH_SHORT).show();
            }
            temperature=temp;
            return temp;
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);

        }
    }*/

}
