package com.stantwice.dietgo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.stantwice.dietgo.service.DietGoService;
import com.stantwice.dietgo.service.DietGoSingleton;
import com.stantwice.dietgo.service.PredictResponse;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {


    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private DietGoService service;


    Uri image_uri;

    private File filePembayaran;

    ImageView mImageView;

    @BindView(R.id.result1)
    public TextView result1;
    @BindView(R.id.result2)
    public TextView result2;
    @BindView(R.id.result3)
    public TextView result3;
    @BindView(R.id.calorie1)
    public TextView calorie1;
    @BindView(R.id.calorie2)
    public TextView calorie2;
    @BindView(R.id.calorie3)
    public TextView calorie3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = DietGoSingleton.createServiceInstance(DietGoService.baseUrl);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

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
                // TODO add pay service implementation
                //mImageView.setImageURI(image_uri);
                Bitmap image = (BitmapFactory.decodeFile(image_uri.getEncodedPath()));

                image = getResizedBitmap(image,500);

                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() +"_image.webp");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG,0, bos);
                byte[] bitmapdata = bos.toByteArray();
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                filePembayaran = file;
                mImageView.setImageURI(image_uri);
                getPrediction();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }

    public void getPrediction() {
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"),filePembayaran);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", filePembayaran.getName(), reqFile);
        service.predict(body).enqueue(
            new Callback<PredictResponse>() {
                @Override
                public void onResponse(@NonNull Call<PredictResponse> call,
                    @NonNull Response<PredictResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("upload payment", "sukses");
                        Log.d("upload payment", response.body().getPredict().toString());
                        result1.setText(response.body().getPredict());
                        result2.setText(response.body().getPredict());
                        result3.setText(response.body().getPredict());
                        calorie1.setText(response.body().getPredict());
                        calorie2.setText(response.body().getPredict());
                        calorie3.setText(response.body().getPredict());

                    } else {
                        if (!response.isSuccessful()){
                            Log.d("upload payment", response.toString());
                        } else if (response.body() == null) {
                            Log.d("upload payment", "kosong");
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<PredictResponse> call, @NonNull Throwable t) {
                    Log.d("upload payment gagal", t.getMessage());
                    //handleException(t);
                }
            });
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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
