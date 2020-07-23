package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

    Button inputInfoButton;

    File directory = null;
    File userData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directory = getFilesDir();
        userData = new File(directory,"userdata");
        loadUserData(userData);
        inputInfoButton = (Button) findViewById(R.id.inputInfoButton);
        inputInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_input_info);
            }
        });

    }


    private String loadUserData(File file){
        System.out.println("-----------cow------------");
        FileInputStream fileInputStream  = null;
        try {
            fileInputStream = new FileInputStream(file);
            String userData = getFileContent(fileInputStream);
            if(userData != null && !userData.isEmpty()){
                return userData;
            } else {
                setInvalidNotifier();
                return null;
            }


        }
        catch (IOException e) {
            setInvalidNotifier();
            e.printStackTrace();
            return null;
        }
    }

    protected String getFileContent(FileInputStream fis) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();

    }

    protected void setInvalidNotifier(){
        TextView invalidDataNotifier = (TextView)findViewById(R.id.invalidUserDataNotifier);
        invalidDataNotifier.setText("invalid user data");
        invalidDataNotifier.setTextColor(RED);

    }

}