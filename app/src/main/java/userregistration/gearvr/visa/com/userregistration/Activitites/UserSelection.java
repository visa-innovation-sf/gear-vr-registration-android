package userregistration.gearvr.visa.com.userregistration.Activitites;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import userregistration.gearvr.visa.com.userregistration.R;
import userregistration.gearvr.visa.com.userregistration.Model.User;

public class UserSelection extends AppCompatActivity {

    private static final int SPEECH_REQUEST_CODE = 0;
    private Button b;
    private Spinner s;
    public static final String BASE_URL = "http://172.16.1.172:5252/";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        b= (Button) findViewById(R.id.button);
        s = (Spinner) findViewById(R.id.spinner);
        user = (User) getIntent().getSerializableExtra("userData");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.header));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String choice = s.getSelectedItem().toString();
                user.setExperiences_choice(choice);
                sendIntentToUnity(user);
            }
        });

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // sendIntentToUnity(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void sendIntentToUnity(User user){
        // sendIntent is the object that will be broadcast outside our app
        Intent sendIntent = new Intent();
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_FROM_BACKGROUND|Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendIntent.setAction("userregistration.gearvr.visa.com.sendintent.IntentToUnity");
        sendIntent.putExtra(Intent.EXTRA_TEXT, user);
        sendBroadcast(sendIntent);
    }



}
