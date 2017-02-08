package userregistration.gearvr.visa.com.userregistration.Activitites;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import userregistration.gearvr.visa.com.userregistration.R;
import userregistration.gearvr.visa.com.userregistration.Model.User;

public class UserRegistration extends AppCompatActivity {


    Button save;
    CircleImageView selfie;
    Uri file;
    EditText fName, lName, mail;
    String firstName, lastName, email, profileImage;
    String[] receiptUrls;
    private boolean tookPhoto = false;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.header));
            actionBar.setDisplayShowTitleEnabled(false);
        }
        save = (Button) findViewById(R.id.saveBtn);
        selfie = (CircleImageView) findViewById(R.id.selfieImg);
        fName = (EditText) findViewById(R.id.firstName);
        lName = (EditText) findViewById(R.id.lastName);
        mail = (EditText) findViewById(R.id.email);

        selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = fName.getText().toString();
                lastName = lName.getText().toString();
                email = mail.getText().toString();

                User user = constructUserModel();
                Intent i = new Intent(getApplicationContext(), VoiceToTextConverter.class);
                i.putExtra("userData", user);
                startActivity(i);
            }
        });

        fName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lName.setText("GearVR");
                mail.setText("gearvr@sample.com");
            }
        });
    }

    private User constructUserModel() {
        return new User.UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setReceiptUrls(receiptUrls)
                .setProfileImage(profileImage)
                .settookPhoto(tookPhoto)
                .build();
    }

    public void takePicture() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                tookPhoto = true;
                imageBitmap = (Bitmap) data.getExtras().get("data");
                imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 400, 300, false);
                selfie.setImageBitmap(imageBitmap);
                profileImage = convertToBase64(imageBitmap);
            }
        }
    }

    public static String convertToBase64(final Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return "data:image/png;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Selfie");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String pathName = mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".PNG";
        return new File(pathName);
    }


}

