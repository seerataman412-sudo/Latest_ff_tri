package com.android.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    // 🔑 आपकी सीक्रेट एक्टिवेशन की
    private final String SECRET_KEY = "FairHacks2026"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // हमारे XML लेआउट को स्क्रीन पर दिखाना
        setContentView(getResources().getIdentifier("activity_main", "layout", getPackageName()));

        final Context ctx = this;

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
                    Toast.makeText(ctx, "कृपया Activation Key डालें!", Toast.LENGTH_SHORT).show();
                } 
                // 🔑 अगर की मैच होती है
                else if (inputKey.equals(SECRET_KEY)) {
                    try {
                        // एंड्रॉइड 14+ के लिए सुरक्षित सर्विस स्टार्ट करने का तरीका
                        Intent serviceIntent = new Intent(ctx, FloatingModMenuService.class);
                        startService(serviceIntent);
                        
                        Toast.makeText(ctx, "पैनल सफलतापूर्वक अनलॉक हो गया!", Toast.LENGTH_SHORT).show();
                        finish(); 
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ctx, "सर्विस शुरू करने में समस्या आई!", Toast.LENGTH_SHORT).show();
                    }
                } 
                // ❌ अगर की गलत है
                else {
                    Toast.makeText(ctx, "गलत Key! कृपया सही कोड डालें।", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 2. GENERATE KEY बटन पर क्लिक करने का लॉजिक
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Key जनरेट करने के लिए ब्राउज़र खुल रहा है...", Toast.LENGTH_SHORT).show();
                android.net.Uri uri = android.net.Uri.parse("https://github.com"); 
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
