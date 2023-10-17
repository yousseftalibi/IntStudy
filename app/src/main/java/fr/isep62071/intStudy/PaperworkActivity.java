package fr.isep62071.intStudy;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class PaperworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperwork);

        Button returnButton = findViewById(R.id.return_button);
        Button socialLifeButton = findViewById(R.id.see_social_life_button);
        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView travelInfoText = findViewById(R.id.travelInfoText);
        TextView paperworkText = findViewById(R.id.paperworkText);
        TextView stepsText = findViewById(R.id.stepsText);

        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaperworkActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        socialLifeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaperworkActivity.this, SocialLifeActivity.class);
            intent.putExtra("DESTINATION", getIntent().getStringExtra("DESTINATION"));
            startActivity(intent);
            finish();
        });

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("FIRST_NAME");
        String nationality = intent.getStringExtra("NATIONALITY");
        String destination = intent.getStringExtra("DESTINATION");

        welcomeText.setText(String.format("Hello, %s,", firstName));
        travelInfoText.setText(String.format("You are heading to %s from %s.", destination, nationality));

        String fileName = nationality.toLowerCase() + ".json";
        AssetManager assetManager = getAssets();
        InputStream input;
        String jsonText = "";
        try {
            input = assetManager.open(fileName);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            jsonText = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(jsonText);
            JSONObject destinationObj = obj.getJSONObject(destination.toLowerCase());
            paperworkText.setText(destinationObj.getString("paperwork"));
            stepsText.setText(destinationObj.getString("steps"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
