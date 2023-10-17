package fr.isep62071.intStudy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TodoActivity extends AppCompatActivity {
    private TextView rexInfo;
    private EditText rexGetInfo;
    private Button sendRexButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        rexInfo = findViewById(R.id.rex_info);
        rexGetInfo = findViewById(R.id.Rex_getInfo);
        sendRexButton = findViewById(R.id.SendRexButton);
        Button returnButton = findViewById(R.id.return_button3);


        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(TodoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        sendRexButton.setOnClickListener(v -> {
            String inputText = rexGetInfo.getText().toString();
            try {
                writeTextFile(inputText);
                readAndDisplayTextFile();
                rexGetInfo.setText("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void readAndDisplayTextFile() throws IOException {
        FileInputStream fis = openFileInput("ArgentinaRex.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line).append('\n');
            line = reader.readLine();
        }
        rexInfo.append(stringBuilder.toString()+"\n");
    }

    private void writeTextFile(String text) throws IOException {
        String fileName = "ArgentinaRex.txt";
        FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
        outputStream.write(text.getBytes());
        outputStream.close();
    }
}
