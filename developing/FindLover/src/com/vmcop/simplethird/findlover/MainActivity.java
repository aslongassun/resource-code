package com.vmcop.simplethird.findlover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.vmcop.simplethird.findlover.constant.ConstantValue;
import com.vmcop.simplethird.findlover.profileinfoendpoint.Profileinfoendpoint;
import com.vmcop.simplethird.findlover.profileinfoendpoint.model.CollectionResponseProfileInfo;
import com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo;


public class MainActivity extends Activity {

    private static final String TAG = "=LOG=";

    CallbackManager mCallbackManager;

    List<String> permissionNeeds= Arrays.asList("user_birthday", "user_friends", "public_profile");
    
	ProfileInfo profileInfo;
	
	List<ProfileInfo> listProfileInfo;
	
	Integer loverIndex;
    
	// ProgressDialog Vinh Hua Quoc
	ProgressDialog barProgressDialog;

	SharedPreferences mySharedPreferencesInfo;
	
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
                // Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
                showDialog();
            }
        });

        // Click on Button Find
        final Button button = (Button) findViewById(R.id.btnFind);
        
        // ProgressDialog Vinh Hua Quoc
        barProgressDialog = new ProgressDialog(MainActivity.this, AlertDialog.THEME_TRADITIONAL);
        barProgressDialog.setMessage("Finding...");
        barProgressDialog.setCanceledOnTouchOutside(false);
        barProgressDialog.hide();
        
        button.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
                findLoveAction();
            }
        });
        
    }

    private void initData(){
        profileInfo = new ProfileInfo();
        listProfileInfo = new ArrayList<ProfileInfo>();
        mySharedPreferencesInfo = getSharedPreferences(ConstantValue.PREFS_NAME, 0);
    }
    
    //===========2015/06/04 VMCop=========//
    
    // Tim kiem nguoi yeu dua vao cac thong tin san co
    private void findLoveAction(){
        Log.d(TAG, "Find Love!");
        
        // Thuc hien tiep action sau khi ham ben duoi thanh cong
        new ListOfProfileInfoAsyncRetriever().execute();
    }
    /**
     * AsyncTask for retrieving the list of places (e.g., stores) and updating the
     * corresponding results list.
    */
    private class ListOfProfileInfoAsyncRetriever extends AsyncTask<Void, Void, CollectionResponseProfileInfo> {

      @Override
      protected CollectionResponseProfileInfo doInBackground(Void... params) {

    	Profileinfoendpoint.Builder endpointBuilder = new Profileinfoendpoint.Builder(
            AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);

        endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

        CollectionResponseProfileInfo result = null;

        Profileinfoendpoint endpoint = endpointBuilder.build();

        try {
        	String fbUID = mySharedPreferencesInfo.getString(ConstantValue.MY_PROFILE_ID, ConstantValue.EMPTY_STRING);
        	if(fbUID != ConstantValue.EMPTY_STRING){
	        	String sexType = mySharedPreferencesInfo.getString(ConstantValue.MY_SEX, ConstantValue.EMPTY_STRING);
	        	
	        	if(sexType.equals(ConstantValue.SEX_MALE)){
	        		sexType = ConstantValue.SEX_FEMALE;
	        	} else {
	        		sexType = ConstantValue.SEX_MALE;
	        	}
	        	
	        	Integer bornYear = mySharedPreferencesInfo.getInt(ConstantValue.MY_BORN_YEAR, 0);
	        	
	        	Log.d(TAG, "sexType:" + sexType);
	        	Log.d(TAG, "bornYear:" + bornYear);
	        	
	        	result = endpoint.listProfileInfo().setSextype(sexType).setFromyear(1989).setToyear(1996).setLimit(3).execute();
	        	
	        	
        	} else {
        		showErrorMessage("Profile info data is Empty!");
        	}
        } catch (IOException e) {
          Log.d(TAG, "IOException:" + e.getMessage());
        }
        return result;
      }
      
      @Override
      protected void onPreExecute(){
          // ProgressDialog Vinh Hua Quoc
          barProgressDialog.show();
      }
      
      // If you want the UI to wait until the task returns, use a ProgressDialog in the onPreExecute and onPostExecute methods.
      @Override
      protected void onPostExecute(CollectionResponseProfileInfo result) {
    	  if(result == null){
    		  
    		  Log.d(TAG, "==List data from server is null==");
    		  
    		  // ProgressDialog Vinh Hua Quoc
              barProgressDialog.hide();
    		  return;
    	  }
    	  
    	  listProfileInfo = result.getItems();
    	  
    	  Log.d(TAG, "==listProfileInfo==" + listProfileInfo);
    	  
    	  loverIndex = randInt(0, listProfileInfo.size() - 1);
      	
      	  setImageProfileForImageView(listProfileInfo.get(loverIndex).getUrlImageProfile(),(ImageView)findViewById(R.id.Love),1);
      	  // Click on Imageview
          final ImageView imageViewYou = (ImageView) findViewById(R.id.Love);
          imageViewYou.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
              	Uri uri = Uri.parse("https://facebook.com/" + listProfileInfo.get(loverIndex).getFuid());
                  startActivity(new Intent(Intent.ACTION_VIEW, uri));
              }
          });
    	  
          // ProgressDialog Vinh Hua Quoc
          barProgressDialog.hide();
      }
    }
    
    // Thuc hien xu ly sau khi login thanh cong
    private void doAfterLoginSuccess(LoginResult loginResult){
        Log.d(TAG, "loginResult:" + loginResult);
        
        try{
	        Profile currProfile = Profile.getCurrentProfile();
	        // Set Image vao ImageView 
	        setImageProfileForImageView(buildImageProfileUrlFromUID(currProfile.getId()),(ImageView)findViewById(R.id.You),0);
        } catch(Exception ex){
        	Log.d(TAG, "Profile set failse:" + ex.getMessage());
        }
        
        // Click on Imageview
        final ImageView imageViewYou = (ImageView) findViewById(R.id.You);
        imageViewYou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d(TAG, "show url");
            	Uri uri = Uri.parse("https://facebook.com/" + Profile.getCurrentProfile().getId());
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        
        // Insert se chi duoc goi khi lan dau tien dung ung dung
        String strFUID = mySharedPreferencesInfo.getString(ConstantValue.MY_PROFILE_ID, ConstantValue.EMPTY_STRING);
        if(strFUID == ConstantValue.EMPTY_STRING){
        	requestGraphData(loginResult);
        }
        
    }

    // Set Image get tu Url vao ImageView
	private void setImageProfileForImageView(String imageUrl,final ImageView inImageView,final Integer typeYouAre){
    	
        Picasso.with(MainActivity.this).load(imageUrl).into(inImageView, new Callback() {
            @SuppressLint("NewApi")
			@Override
            public void onSuccess() {
            	if(typeYouAre == 1){
                	Drawable background = inImageView.getDrawable();
                	inImageView.setImageDrawable(null);
                	inImageView.setBackground(background);
            		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon);
        	    	inImageView.setImageBitmap(mBitmap);
            	}
            }
            
            @Override
            public void onError() {

            }
        });
    	
    }
    
    // Luu fUID tren may user
	private void saveDataToClient(ProfileInfo inProfile){
		
        SharedPreferences.Editor editor = mySharedPreferencesInfo.edit();
        
        editor.putString(ConstantValue.MY_PROFILE_ID, inProfile.getFuid());
        editor.putString(ConstantValue.MY_SEX, inProfile.getUserSex());
        editor.putInt(ConstantValue.MY_BORN_YEAR, inProfile.getBornYear());
        
        editor.commit();
    }
    
    private String buildImageProfileUrlFromUID(String inUID){
    	if(inUID == null || inUID == ""){
    		showErrorMessage("inUID is Blank!");
    		return "";
    	}
    	String resultUrl = "https://graph.facebook.com/"+ inUID +"/picture?type=large&width=250&height=250";
    	return resultUrl;
    }
    
    private void showErrorMessage(String inMessage){
    	Log.d(TAG, "Message_Error:" + inMessage);
    }
    
    /**
     * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
    */
    private class ProfileInfoTask extends AsyncTask<Void, Void, Void> {

      /**
       * Calls appropriate CloudEndpoint to indicate that user checked into a place.
       *
       * @param params the place where the user is checking in.
       */
      @Override
      protected Void doInBackground(Void... params) {
        
    	Profileinfoendpoint.Builder builder = new Profileinfoendpoint.Builder(
            AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
            null);
            
        builder = CloudEndpointUtils.updateBuilder(builder);

        Profileinfoendpoint endpoint = builder.build();
        
        try {
        	endpoint.insertProfileInfo(profileInfo).execute();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        return null;
      }
    }
    

    
    // Lay ve nhung thong tin can thiet cua user login
    private void requestGraphData(LoginResult loginResult){
    	Log.d(TAG, "loginResult");
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                    	
                        Log.d(TAG, "object:" + object.toString());

                        // Facebook ID only for app
                        profileInfo.setFuid(object.optString(ConstantValue.MY_PROFILE_ID));
                        // Facebook Name
                        profileInfo.setUserName(object.optString(ConstantValue.MY_NAME));
                        // Facebook Sex - Male/Female
                        profileInfo.setUserSex(object.optString(ConstantValue.MY_SEX));
                        // Birthday
                        String birthDayStr = object.optString(ConstantValue.MY_BIRTHDAY);
                        String[] dateBirthArr = birthDayStr.split("/");
                        profileInfo.setBirthday(birthDayStr);
                        if(dateBirthArr.length > 2){
                        	// Facebook born year
                        	profileInfo.setBornYear(Integer.parseInt(dateBirthArr[2]));
                        }
                        // Facebook Image Profile
                        profileInfo.setUrlImageProfile(buildImageProfileUrlFromUID(object.optString(ConstantValue.MY_PROFILE_ID)));
                        // User Local
                        profileInfo.setLocale(ConstantValue.LOCAL_VIETNAM);
                        // User nay ko duoc lay tu upload
                        profileInfo.setIsFromUpload(false);
                        
                        saveDataToClient(profileInfo);
                        
                        new ProfileInfoTask().execute();
                        
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }
    
    // Random Integer
    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    private void showDialog(){
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
