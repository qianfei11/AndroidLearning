package zjgsu.is.qff.homework01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final MyClassmate expert = new MyClassmate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.find_language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取Spinner引用
                Spinner spinner = findViewById(R.id.feature);
                // 获取Spinner指定的头衔
                String title = spinner.getSelectedItem().toString();
                // 查询模型层
                String people = expert.getInfo(title);
                // 获取TextView引用
                TextView textView = findViewById(R.id.language);
                // 设置TextView文字
                textView.setText(people);
            }
        });
    }
}
