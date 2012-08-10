package com.example.clusterfuck;

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

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    
}
