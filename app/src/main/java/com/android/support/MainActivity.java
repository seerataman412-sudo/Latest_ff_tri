package com.android.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    // 🔑 यहाँ आप अपनी पसंद की कोई भी "Activation Key" लिख सकते हैं
    private final String SECRET_KEY = "FAIRHACK2026"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // हमारे XML लेआउट को स्क्रीन पर दिखाना
        setContentView(getResources().getIdentifier("activity_main", "layout", getPackageName()));

        // XML के बटन्स और इनपुट बॉक्स को जावा से जोड़ना
        final EditText edtKey = findViewById(getResources().getIdentifier("edtKey", "id", getPackageName()));
        Button btnUnlock = findViewById(getResources().getIdentifier("btnUnlock", "id", getPackageName()));
        Button btnGenerate = findViewById(getResources().getIdentifier("btnGenerate", "id", getPackageName()));

        // 1. UNLOCK PANEL बटन पर क्लिक करने का लॉजिक
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputKey = edtKey.getText().toString().trim();
                
                if (inputKey.isEmpty()) {
                    Toast.makeText(MainActivity.this, "कृपया Activation Key डालें!", Toast.LENGTH_SHORT).show();
                } 
                // 🔑 अगर यूजर की डाली हुई की (Key) हमारी सीक्रेट की से मैच करती है
                else if (inputKey.equals(SECRET_KEY)) {
                    Main.Start(MainActivity.this); // फ्लोटिंग मेनू चालू करें
                    Toast.makeText(MainActivity.this, "पैनल सफलतापूर्वक अनलॉक हो गया!", Toast.LENGTH_SHORT).show();
                    finish(); // लॉगिन स्क्रीन बंद कर दें
                } 
                // ❌ अगर की गलत है
                else {
                    Toast.makeText(MainActivity.this, "गलत Key! कृपया सही कोड डालें।", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 2. GENERATE KEY बटन पर क्लिक करने पर आपका टेलीग्राम या कोई वेबसाइट खुल जाएगी
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Key जनरेट करने के लिए ब्राउज़र खुल रहा है...", Toast.LENGTH_SHORT).show();
                // यहाँ आप अपने टेलीग्राम चैनल का लिंक भी डाल सकते हैं
                android.net.Uri uri = android.net.Uri.parse("https://github.com"); 
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
