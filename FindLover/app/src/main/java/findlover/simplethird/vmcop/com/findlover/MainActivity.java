package findlover.simplethird.vmcop.com.findlover;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;
import com.vmcop.simplethird.findlover.constant.ConstantValue;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "=LOG=";

    CallbackManager mCallbackManager;

    List<String> permissionNeeds= Arrays.asList("user_birthday", "user_friends", "public_profile");

    JSONObject jsonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        mCallbackManager  = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                doAfterLoginSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                showDialog();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
                showDialog();
            }
        });

        // Click on Button Find
        final Button button = (Button) findViewById(R.id.btnFind);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findLoveAction();
            }
        });
    }

    private void initData(){
        jsonReturn = new JSONObject();
    }
    //===========2015/06/04 VMCop=========//

    private void showDialog(){
        Log.d(TAG, "showDialog!");
        // show a dialog notice no image found
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Connection error");
        builder.setMessage("Check your internet connection and try again.");
        builder.setPositiveButton("Try again",	new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, permissionNeeds);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Thuc hien xu ly sau khi login thanh cong
    private void doAfterLoginSuccess(LoginResult loginResult){
        Log.d(TAG, "Login Success!");
        Profile currProfile = Profile.getCurrentProfile();
        // Set Image vao ImageView ImageView yourImageView = (ImageView)findViewById(R.id.You);
        setImageProfileForImageView(currProfile.getProfilePictureUri(500, 500).toString(),(ImageView)findViewById(R.id.You));

        requestGraphData(loginResult);



    }

    // Set Image get tu Url vao ImageView
    private void setImageProfileForImageView(String imageUrl, ImageView inImageView){
        Picasso.with(MainActivity.this).load(imageUrl).into(inImageView);
    }

    // Lay ve nhung thong tin can thiet cua user login
    private void requestGraphData(LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d(TAG, "object:" + object.toString());
                        //----------TODO----------//
                        // Save xuong data cua may
                        SharedPreferences mySharedPreferencesInfo = getSharedPreferences(ConstantValue.PREFS_NAME, 0);
                        SharedPreferences.Editor editor = mySharedPreferencesInfo.edit();

                        editor.putString(ConstantValue.MY_PROFILE_ID,object.optString(ConstantValue.MY_PROFILE_ID));
                        editor.putString(ConstantValue.MY_NAME,object.optString(ConstantValue.MY_NAME));
                        editor.putString(ConstantValue.MY_SEX,object.optString(ConstantValue.MY_SEX));
                        editor.putString(ConstantValue.MY_BIRTHDAY,object.optString(ConstantValue.MY_BIRTHDAY));
                        editor.putInt(ConstantValue.MY_AGE, calculateAgeFromBirthDay(object.optString(ConstantValue.MY_BIRTHDAY)));

                        editor.commit();
                        //------------------------//
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    // Tinh toan tuoi tu ngay sinh
    private int calculateAgeFromBirthDay(String inBirthDay){
        int age = 0;
        try {
            age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(inBirthDay.split("/")[2]);
        }catch(Exception ex){
            Log.d(TAG, "calculateAgeFromBirthDay:" + ex.getMessage());
        }
        return age;
    }

    // Tim kiem nguoi yeu dua vao cac thong tin san co
    private void findLoveAction(){
        Log.d(TAG, "Find Love!");
        // Tim kiem nguoi thich hop
        SharedPreferences mySharedPreferencesInfo = getSharedPreferences(ConstantValue.PREFS_NAME, 0);
        Log.d(TAG, "Age getted:" + mySharedPreferencesInfo.getString(ConstantValue.MY_PROFILE_ID, ""));
        Log.d(TAG, "Age getted:" + mySharedPreferencesInfo.getInt(ConstantValue.MY_AGE, 0));
        // Thiet lap cac thong tin tren man hinh

    }
    // Kiem tra xem facebook user nay co trong db chua
    public Boolean checkFBidExist(String fbId){
        Boolean isExisted = false;

        return isExisted;
    }

    // Get cac thong tin can thiet cua user dua vao fbId
    private void getUserDataFromFbId(String fbId){

    }

    //====================================//

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }


}
