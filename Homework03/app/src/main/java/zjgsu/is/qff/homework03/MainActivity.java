package zjgsu.is.qff.homework03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false; // 存储之前的状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Life Cycle", "onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            // 获取bundle中的变量
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Life Cycle", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // 把相关变量存储到bundle中，方便下一个Activity接收
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Life Cycle", "onStart");
//        if (wasRunning) {
//            running = true;
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Life Cycle", "onStop");
//        wasRunning = running;
//        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Life Cycle", "onResume");
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Life Cycle", "onPause");
        wasRunning = running;
        running = false;
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler(); // 使用ui线程的handler定时更新
        handler.post(new Runnable() { // 使用post方法提交给ui线程
            @Override
            public void run() { // 实现run方法
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000); // 使用postDelayed方法实现定时调度，每一秒提交一次
            }
        });
    }
}






