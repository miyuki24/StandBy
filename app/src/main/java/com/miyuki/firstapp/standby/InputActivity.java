package com.miyuki.firstapp.standby;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileDescriptor;
import java.io.IOException;

public class InputActivity extends AppCompatActivity {

    private Uri m_uri;
    private static final int RESULT_PICK_IMAGEFILE = 1001;

    private ImageView photoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Button post = (Button) findViewById(R.id.post_button);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, NewHomeActivity.class);
                startActivity(intent);
            }
        });
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button photo = (Button) findViewById(R.id.photo);
        photoImage = (ImageView) findViewById(R.id.photoImage);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Filter to show only images, using the image MIME data type.
                // it would be "*/*".
                intent.setType("*/*");

                startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            if(resultData.getData() != null){

                ParcelFileDescriptor pfDescriptor = null;
                try{
                    Uri uri = resultData.getData();

                    pfDescriptor = getContentResolver().openFileDescriptor(uri, "r");
                    if(pfDescriptor != null){
                        FileDescriptor fileDescriptor = pfDescriptor.getFileDescriptor();
                        Bitmap bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        pfDescriptor.close();
                        photoImage.setImageBitmap(bmp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try{
                        if(pfDescriptor != null){
                            pfDescriptor.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}