package cn.b3ale.jnianother;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        tv.setText(stringFromJNI());
        // ====== Add String ======
        Button bt1 = findViewById(R.id.add_string);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = ((EditText)findViewById(R.id.input_string)).getText().toString();
                TextView tv1 = findViewById(R.id.add_string_result);
                tv1.setText(addString(inputString));
            }
        });
        // ====== Add Numbers ======
        Button bt2 = findViewById(R.id.add_numbers);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(((EditText)findViewById(R.id.input_number_a)).getText().toString());
                int b = Integer.parseInt(((EditText)findViewById(R.id.input_number_b)).getText().toString());
                TextView tv2 = findViewById(R.id.add_int_result);
                tv2.setText(String.valueOf(addInt(a, b)));
            }
        });
        // ====== Check Password ======
        Button bt3 = findViewById(R.id.check_password);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = ((EditText)findViewById(R.id.input_password)).getText().toString();
                TextView tv3 = findViewById(R.id.check_password_result);
                if (1 == checkPwd("IS1701", password)) { // "LV4:34"
                    tv3.setText("Your password is correct!!!");
                } else {
                    tv3.setText("Are you a hacker???");
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int addInt(int a, int b);

    public native String addString(String a);

    public native int checkPwd(String a, String b);
}
