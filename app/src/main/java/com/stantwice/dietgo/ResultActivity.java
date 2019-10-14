package com.stantwice.dietgo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ResultActivity extends AppCompatActivity {


    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;


    Uri image_uri;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mImageView = findViewById(R.id.result_view);


        openImagePicker();


        //takePicture();
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    //handling permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this method is called, when user presses Allow or Deny from Permission Request Popup
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission from popup was granted
                    openCamera();
                } else {
                    //permission from popup was denied
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //called when image was captured from camera

       /* if (resultCode == RESULT_OK){
            //set the image captured to our ImageView
            mImageView.setImageURI(image_uri);

        }*/

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                image_uri = result.getUri();
                mImageView.setImageURI(image_uri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }


    }

    public void openImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropResultSize(200, 200)
                .setAspectRatio(1, 1)
                .start(ResultActivity.this);
    }

    public void takePicture() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                //permission not enabled, request it
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                //show popup to request permissions
                requestPermissions(permission, PERMISSION_CODE);
            } else {
                //permission already granted
                openCamera();
            }
        } else {
            //system os < marshmallow
            openCamera();
        }

    }
}
