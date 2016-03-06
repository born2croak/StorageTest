package born2croak.apps.storagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button saveCountButton = (Button) findViewById(R.id.saveCountButton);
        final Button advanceButton = (Button) findViewById(R.id.advanceButton);
        final TextView textView = (TextView) findViewById(R.id.textView);

        int value = 0;
        final MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        value = dbHandler.getValue();

        textView.setText(String.valueOf(value));

        saveCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(textView.getText().toString());
                dbHandler.saveValue(value);
            }
        });

        advanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(textView.getText().toString());
                String vText = String.valueOf(value + 1);
                textView.setText(vText);
            }
        });



    }


}
