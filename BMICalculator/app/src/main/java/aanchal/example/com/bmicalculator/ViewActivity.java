package aanchal.example.com.bmicalculator;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    TextView txtHistory;
    DbHandler db;
    Typeface typeface;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        typeface= Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Light.otf");

        txtHistory=(TextView)findViewById(R.id.txtHistory);
        txtHistory.setTypeface(typeface);
        txtHistory.setTextColor(Color.parseColor("#280707"));
        db=new DbHandler(this);

        ArrayList<String> a= new ArrayList<String>();
        a=db.getAllRecords();

        if(a.size()==0)
        {
            txtHistory.setText("No Records");
        }
        else {
            for (String m : a) {
                txtHistory.setText(txtHistory.getText() + "\n" + m.toString());
            }
        }

    }
}
