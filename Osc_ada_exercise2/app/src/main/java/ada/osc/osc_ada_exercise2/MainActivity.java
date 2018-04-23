package ada.osc.osc_ada_exercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.content.res.Configuration;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.edittext_firstnumber) EditText mFirstNumber;
    @BindView(R.id.edittext_secondnumber) EditText mSecondNumber;
    @BindView(R.id.button_add) Button mAdd;
    @BindView(R.id.button_divide) Button mDivide;
    @BindView(R.id.button_multiply) Button mMultiply;
    @BindView(R.id.button_subtract) Button mSubtract;
    @BindView(R.id.textview_result) TextView mResult;
    @BindView(R.id.button_localization) Button mLocalization;
    Locale locale;
    Configuration configuration;
    private static final String LOCALES[] = new String[] {"hr", "en"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        if(getResources().getConfiguration().locale.toString().substring(0, 2).equals(LOCALES[0]))
            mLocalization.setText(LOCALES[1]);
        else
            mLocalization.setText(LOCALES[0]);
    }

    @OnClick(R.id.button_localization)
    public void localize(View view){

        if(getResources().getConfiguration().locale.toString().substring(0, 2).equals(LOCALES[0])){
            setLocalization(LOCALES[1]);
        }
        else {
            setLocalization(LOCALES[0]);
        }
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void setLocalization(String lang) {
        mLocalization.setText(lang);
        locale = new Locale(lang);
        configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }


    @OnClick(R.id.button_add)
    public void add(View view){
        if(checkInput())
            return;
        mResult.setText("" + (Integer.parseInt(mFirstNumber.getText().toString()) +
            Integer.parseInt(mSecondNumber.getText().toString())));
    }

    @OnClick(R.id.button_subtract)
    public void subtract(View view){
        if(checkInput())
            return;
        mResult.setText("" + (Integer.parseInt(mFirstNumber.getText().toString()) -
                Integer.parseInt(mSecondNumber.getText().toString())));
    }

    @OnClick(R.id.button_multiply)
    public void multiply(View view){
        if(checkInput())
            return;
        mResult.setText("" + (Integer.parseInt(mFirstNumber.getText().toString()) *
                Integer.parseInt(mSecondNumber.getText().toString())));
    }

    @OnClick(R.id.button_divide)
    public void divide(View view){
        if(checkInput())
            return;
        try {
            if (Integer.parseInt(mSecondNumber.getText().toString()) == 0)
                throw new DivideByZeroException();
            mResult.setText("" + ((double)Integer.parseInt(mFirstNumber.getText().toString()) /
                    Integer.parseInt(mSecondNumber.getText().toString())));
        }
        catch (DivideByZeroException e){
            displayMessage("Can't divide by zero.");
        }

    }

    private boolean checkInput() {
        if(mFirstNumber.getText().toString().isEmpty() ||
                mSecondNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid values.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return false;
        }
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
