package e.rhacker.text2voice;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t;
    EditText text;
    Button speak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text_description);
        speak = (Button) findViewById(R.id.speak_button);

        t = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.US);
                }
            }
        });

        // Set a click listener on that View
        speak.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                String toSpeak = text.getText().toString();
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


    }

    public void onPause() {
        if (t != null) {
            t.stop();
            t.shutdown();
        }
        super.onPause();
    }
}
