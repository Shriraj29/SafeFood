package com.example.safefood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     Button Button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button = findViewById(R.id.button);
        Button.setOnClickListener(v->
        {
            scanCode();
        });
    }

    @Override
    public void onClick(View view) {
            scanCode();
    }

    private void scanCode(){

        ScanOptions options = new ScanOptions();
        options.setPrompt("Scannez votre produit");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
       if(result.getContents() !=null)
       {
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("Résultat");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("Analysez le produit ?", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int which) {

                   dialogInterface.dismiss();
               }
           }).show();
       }
    });


}

