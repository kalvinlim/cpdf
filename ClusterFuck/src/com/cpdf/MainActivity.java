package com.cpdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;


//Following Imports are for the Camera API
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends Activity {
	//--coding for Camera API
	private final static String DEBUG_TAG = "MainActivity";
	private Camera camera;
	private int cameraId = 0;
	//--END for coding for Camera API


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--START for coding for Camera API
        if (!getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
              Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                  .show();
            } else {
              cameraId = findFrontFacingCamera();
              camera = Camera.open(cameraId);
              if (cameraId < 0) {
                Toast.makeText(this, "No front facing camera found.",
                    Toast.LENGTH_LONG).show();
              }
            }
        //--END for coding for Camera API
        
        Toast msg = Toast.makeText(MainActivity.this,
                "File Path: " + android.os.Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG);
        msg.show();

        // step 1: creation of a document-object
        Document document = new Document();
        try {
                // step 2:
                // we create a writer that listens to the document
                // and directs a PDF-stream to a file
                PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "HelloWorld.pdf"));

                // step 3: we open the document
                document.open();
                // step 4: we add a paragraph to the document
                document.add(new Paragraph("Hello World"));
                


        } catch (DocumentException de) {
                System.err.println(de.getMessage());
        } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
        }

        // step 5: we close the document
        document.close();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //--START for coding for Camera API
    public void onClick(View view) {
        camera.takePicture(null, null,
            new PhotoHandler(getApplicationContext()));
    }
    
    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
          CameraInfo info = new CameraInfo();
          Camera.getCameraInfo(i, info);
          if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            Log.d(DEBUG_TAG, "Camera found");
            cameraId = i;
            break;
          }
        }
        return cameraId;
     }
    
    @Override
    protected void onPause() {
      if (camera != null) {
        camera.release();
        camera = null;
      }
      super.onPause();
    }
    //END for coding for Camera API
    
}
