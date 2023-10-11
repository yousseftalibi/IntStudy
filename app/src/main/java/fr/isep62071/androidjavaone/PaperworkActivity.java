package fr.isep62071.androidjavaone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaperworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperwork);

        Button returnButton = findViewById(R.id.return_button);
        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView travelInfoText = findViewById(R.id.travelInfoText);
        TextView paperworkText = findViewById(R.id.paperworkText);
        TextView stepsText = findViewById(R.id.stepsText);

        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaperworkActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("FIRST_NAME");
        String nationality = intent.getStringExtra("NATIONALITY");
        String destination = intent.getStringExtra("DESTINATION");

        welcomeText.setText(String.format("Hello, %s,", firstName));

        travelInfoText.setText(String.format("You are heading to %s from %s.", destination, nationality));

        String paperworkKey = "paperwork_" + nationality.toLowerCase() + "_" + destination.toLowerCase();
        String stepsKey = "steps_" + nationality.toLowerCase() + "_" + destination.toLowerCase();

        int paperworkId = getResources().getIdentifier(paperworkKey, "string", getPackageName());
        int stepsId = getResources().getIdentifier(stepsKey, "string", getPackageName());

        if (paperworkId != 0 && stepsId != 0) {
            paperworkText.setText(getString(paperworkId));
            stepsText.setText(getString(stepsId));
        }
    }
}
