package com.android.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // हमारे XML लेआउट को स्क्रीन पर दिखाना
        setContentView(getResources().getIdentifier("activity_main", "layout", getPackageName()));

        // XML के बटन्स और इनपुट बॉक्स को जावा से जोड़ना
        final EditText edtKey = findViewById(getResources().getIdentifier("edtKey", "id", getPackageName()));
        Button btnUnlock = findViewById(getResources().getIdentifier("btnUnlock", "id", getPackageName()));
        Button btnGenerate = findViewById(getResources().getIdentifier("btnGenerate", "id", getPackageName()));

        // 1. UNLOCK PANEL बटन पर क्लिक करने का काम
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputKey = edtKey.getText().toString().trim();
                
                // अगर इनपुट खाली नहीं है, तो फ्लोटिंग मेनू चालू करें
                if (!inputKey.isEmpty()) {
                    Main.Start(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Panel Unlocked Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Activation Key", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 2. GENERATE KEY बटन पर क्लिक करने का काम (सिर्फ मैसेज दिखेगा)
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Key Generation Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
