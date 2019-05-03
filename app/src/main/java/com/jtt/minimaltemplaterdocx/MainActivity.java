package com.jtt.minimaltemplaterdocx;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import hr.ngs.templater.Configuration;
import hr.ngs.templater.ITemplateDocument;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REQUEST_WRITE_PERMISSION = 1;
    public static final String TAG = "DOCXTESTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestCreateHelloWorldDocX();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createDocX() throws IOException {

        File docxDirectory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "DOCX_TESTS");

        if (!docxDirectory.exists() && !docxDirectory.mkdirs()) {
            Log.e(TAG, "failed to create " + docxDirectory.getAbsolutePath());
        }

        File docXFile = new File(docxDirectory, "output.docx");

        InputStream fis = getResources().openRawResource(R.raw.my_template);
        FileOutputStream fos = new FileOutputStream(docXFile);

        ITemplateDocument document = Configuration.factory().open(fis, "docx", fos);
        document.process(new Object() { public final String Tag = "a plane"; });
        document.flush();

    }

    private void requestCreateHelloWorldDocX() throws IOException {

        // check to see if we have permission to write to file
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // check to see if Android will ask the user for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Please add permission to write to file", Toast.LENGTH_LONG).show();
            } else {

                // Have Android ask the user for permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_REQUEST_WRITE_PERMISSION);
            }
        } else {
            createDocX();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_REQUEST_WRITE_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                try {
                    createDocX();
                } catch (IOException e) {
                    Toast.makeText(this, "An error occurred creating the DocX", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

}
