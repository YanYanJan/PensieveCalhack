package com.example.yanyan.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by yanyan on 11/3/18.
 */

public class CameraCreate extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private Databasehelper myDb;
    private ImageButton chooseImage, takePicture;
    public static final int CAMERA_REQUEST = 1888;
    public static final int GALLERY_REQUEST = 1889;
    private static final int REQUEST_CODE = 123;
    private ImageView imageview;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.create);
        setContentView(R.layout.camera_create);

        mContext = this;
        mActivity = CameraCreate.this;

        imageview = findViewById(R.id.Photo);
        chooseImage = findViewById(R.id.image_select_button);
        takePicture = findViewById(R.id.camera_button);
        submit = findViewById(R.id.submit_bt);
        myDb = new Databasehelper(this);
        checkPermission();

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseImgIntent = new Intent(Intent.ACTION_PICK);
                chooseImgIntent.setType("image/*");
                startActivityForResult(chooseImgIntent, GALLERY_REQUEST);
            }
        });


        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the date and time when submitted
                Date todayDate = new Date();
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                String datetime = dateFormat.format(todayDate);
                //Toast.makeText(newpostActivity.this, datetime, Toast.LENGTH_LONG).show();
                String thought = null;
                byte[] photo = null;
                String vedio = null;
                String audio = null;
                String location = null;
                myDb.insertData(datetime, thought ,photo, vedio, audio,location);

                Toast.makeText(CameraCreate.this, "New Memory Saved:)", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CameraCreate.this, Memory.class);
                startActivity(intent);


                //boolean checksub = thoughts.equals("")&&record.equals("");
//String date, String thought, byte[] photo, String vedio, String audio)
//                if (myDb.insertData(datetime, thought ,photo, vedio, audio,location)){
//                }


            }


        });

        myDb.close();
    }



    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        // if the request code is camera request
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageview.setImageBitmap(imageBitmap);
            /*
            try {
                //bookCoverBlob = ImageUtils.getImageBytes(photo);
            }
            catch (NullPointerException e){
                Toast.makeText(this, "Take or choose a picture please.", Toast.LENGTH_LONG).show();
            }
            */
        }
        else if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageview.setImageBitmap(selectedImage);
                /*
                try {
                    bookCoverBlob = ImageUtils.getBytes(imageStream);
                }
                catch(IOException e){
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                */
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked or taken an Image",Toast.LENGTH_LONG).show();
        }

    }

    //check the permission

    protected void checkPermission(){
        if ( ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(mActivity,Manifest.permission.READ_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(mActivity,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            //show an alert dialog/ popup window request explanations
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Manifest.permission.READ_EXTERNAL_STORAGE)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    ) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mActivity);
                builder.setMessage("Auido and External storage permission are required for this APP");
                builder.setTitle("Please grant these permission");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(mActivity, new String[]{
                                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE);

                    }

                });

                builder.setNegativeButton("Cancel",null);
                android.app.AlertDialog dialog = builder.create();
                dialog.show();

            }
            else{
                ActivityCompat.requestPermissions(mActivity, new String[]{
                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
            }

        }
        else{
//            Toast.makeText(mContext,"Welcome!",Toast.LENGTH_LONG).show();
        }
        //if the permission is not granted,
        // you need to do something "explanation"
        //directly request for required permission of your app

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults){
        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && (grantResults[0]+grantResults[1]+grantResults[2] == PackageManager.PERMISSION_GRANTED)){
            }
            else{
                Toast.makeText(mContext,"Permission needed",Toast.LENGTH_LONG).show();
            }
        }
    }

}
