package ada.osc.osc_ada_exercise4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textviewresultindex) TextView textviewresult;
    @BindView(R.id.textviewresultdescription) TextView textviewresultdescription;
    @BindView(R.id.edittextheight) EditText edittextheight;
    @BindView(R.id.edittextweight) EditText edittextweight;
    @BindView(R.id.buttoncalculate) Button buttoncalculate;
    @BindView(R.id.textviewresultcolor) TextView textviewresultcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttoncalculate)
    public void calculate(View view){
        if(!Calculate.validInput(Float.parseFloat(edittextweight.getText().toString()),
                Float.parseFloat(edittextheight.getText().toString()))){
            displayMessage("Invalid input");
            return;
        }
        float index = Calculate.calculate(Float.parseFloat(edittextweight.getText().toString()),
                Float.parseFloat(edittextheight.getText().toString()));
        DecimalFormat df = new DecimalFormat("#.##");
        textviewresult.setText(df.format(index));
        if(index > 30.0) {
            textviewresultdescription.setText(R.string.veryfat);
            textviewresultcolor.setBackgroundResource(R.color.veryfat);
        }
        else if(index <= 30.0 && index > 25.0) {
            textviewresultdescription.setText(R.string.fat);
            textviewresultcolor.setBackgroundResource(R.color.fat);
        }
        else if(index <= 25.0 && index > 18.5) {
            textviewresultdescription.setText(R.string.healthy);
            textviewresultcolor.setBackgroundResource(R.color.healthy);
        }
        else {
            textviewresultdescription.setText(R.string.undernourished);
            textviewresultcolor.setBackgroundResource(R.color.undernourishedcolor);
        }
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
