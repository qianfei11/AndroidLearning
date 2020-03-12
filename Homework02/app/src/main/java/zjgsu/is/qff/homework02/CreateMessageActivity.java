package zjgsu.is.qff.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {
    public static final String MESSAGE_KEY = "zjgsu.is.qff.homework02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View button) {
        EditText editText = findViewById(R.id.input);
        String message = editText.getText().toString();
        Intent intent;
        Log.d("DEBUG", "message: " + message);
        Log.d("DEBUG", "result: " + (message == "hello"));
        if (message.equals("hello")) {
            intent = new Intent(this, ReceiveMessageActivity.class);
            intent.putExtra(MESSAGE_KEY, message);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "1712190426\n" + "钱非凡\n" + "Message:" + message);
            String chooserTitle = getString(R.string.chooser);
            Intent chooserIntent = Intent.createChooser(intent, chooserTitle);
            startActivity(chooserIntent);
        }
        startActivity(intent);
    }
}
