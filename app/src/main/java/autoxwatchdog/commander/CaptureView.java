/* FILE: CaptureView.java
 * PROJECT: AutoX Watchdog
 * PROGRAMMER: Cavan Biggs
 * FIRST VERSION: February 10th 2020
 * DESCRIPTION:
 *
 *
 *
 *
 *
 */
package autoxwatchdog.commander;
//Source citation: https://www.androidauthority.com/how-to-create-an-sms-app-721438/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaptureView extends AppCompatActivity {

    ArrayList<String> smsMessagesList = new ArrayList<>();
    //Images array
    ArrayList<Integer> autoxMMS = new ArrayList<>();
    ListView messages;
    ArrayAdapter arrayAdapter;
    private static CaptureView inst;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
    private static final String hardwareUnit = "2262289161";
    private static final String testNumber = "2262289161";
    private static final String TAG = "CaptureView";

    public static CaptureView instance() {
        return inst;
    }

    @Override
    public void onStart(){
        super.onStart();
        inst = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_view);
        //Custom code start
        messages = (ListView) findViewById(R.id.requestResponse);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, smsMessagesList);
        messages.setAdapter(arrayAdapter);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            getPermissionToReadSMS();
        } else {
            refreshSmsInbox();
            //refreshAllMessagesInbox();
            //refreshAllMessagesInbox();
        }
    }
    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    public void updateInbox(final String smsMessage) {
        Log.d(TAG, "Raw message: " + smsMessage + "\n");
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    //Permission Methods
    public void getPermissionToReadSMS(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
        != PackageManager.PERMISSION_GRANTED){
            if(shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission", Toast.LENGTH_SHORT).show();

            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);

        }
    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults){
        //Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST){
            if (grantResults.length == 1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                refreshSmsInbox();
                //refreshAllMessagesInbox();
                //processMMSImages();
            } else {
                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
                 }

            } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    public void refreshSmsInbox(){
        //Refresh the listview
        //URI for SMS: "content://sms/inbox"
        //URI for MMS: "content://mms/inbox"
        //URI for both SMS/MMS: content://mms-sms/conversations
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"),null, null, null, null );
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        String indexAddressStr = Integer.toString(indexAddress);
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        Log.d(TAG, "indexAddressStr Value: " + smsInboxCursor.getString(indexAddress) + "\n");
        Log.d(TAG, "hardwareUnit Value: " + hardwareUnit + "\n");

        do {
            if (smsInboxCursor.getString(indexAddress).equals(testNumber))
            {
                String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
                        "\n" + smsInboxCursor.getString(indexBody) + "\n";
                arrayAdapter.add(str);
            }


        } while (smsInboxCursor.moveToNext());
    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    //Get both SMS and MMS messages
    public void refreshAllMessagesInbox(){

        ContentResolver contentResolver = getContentResolver();
        final String[] projection = new String[]{"*"};
        Uri uri = Uri.parse("content://mms-sms/conversations/");
        Cursor query = contentResolver.query(uri, projection, null, null, null);
        if (query.moveToFirst()) {
            do {
                String string = query.getString(query.getColumnIndex("ct_t"));
                if ("application/vnd.wap.multipart.related".equals(string)) {
                    // it's MMS
                    Log.d(TAG, "MMS Messages found \n");
                    processMMSImages();
                    //Process and extract the MMS message and insert it into a container which will be used to ...
                    // display in a list activity.

                } else {
                    // it's SMS
                    Log.d(TAG, "SMS Messages found \n");
                }
            } while (query.moveToNext());
        }

    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    // Code to get MMS is from the following link
    //https://stackoverflow.com/questions/3012287/how-to-read-mms-data-in-android
    private void processMMSImages(){

        String selectionPart = "mid=";
        Uri uri = Uri.parse("content://mms/part");
        Cursor cPart = getContentResolver().query(uri, null,
                null, null, null);
        if (cPart.moveToFirst()) {
            do {
                String partId = cPart.getString(cPart.getColumnIndex("_id"));
                String type = cPart.getString(cPart.getColumnIndex("ct"));
                if ("image/jpeg".equals(type) || "image/bmp".equals(type) ||
                        "image/gif".equals(type) || "image/jpg".equals(type) ||
                        "image/png".equals(type)) {
                    Bitmap bitmap = getMmsImage(partId);
                    //Insert into a container
                    autoxMMS.add(bitmap.hashCode());
                }
            } while (cPart.moveToNext());
        }
    }

    /*
     *	METHOD			  :
     *
     *	DESCRIPTION		  :
     *
     *
     *	PARAMETERS		  :
     *
     *
     *	RETURNS			  :
     *
     */
    // Code to get MMS is from the following link
    //https://stackoverflow.com/questions/3012287/how-to-read-mms-data-in-android
    private Bitmap getMmsImage(String _id) {
        Uri partURI = Uri.parse("content://mms/part/" + _id);
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = getContentResolver().openInputStream(partURI);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {}
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        return bitmap;
    }




} //End of class
