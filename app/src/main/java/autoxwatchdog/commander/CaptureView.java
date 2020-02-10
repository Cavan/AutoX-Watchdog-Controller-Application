package autoxwatchdog.commander;
//Source citation: https://www.androidauthority.com/how-to-create-an-sms-app-721438/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CaptureView extends AppCompatActivity {

    ArrayList<String> smsMessagesList = new ArrayList<>();
    ListView messages;
    ArrayAdapter arrayAdapter;
    private static CaptureView inst;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
    private static final String hardwareUnit = "2262289161";
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
        }
    }

    public void updateInbox(final String smsMessage) {
        Log.d(TAG, "Raw message: " + smsMessage + "\n");
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

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
            } else {
                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
                 }

            } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //Refresh the listview
    public void refreshSmsInbox(){
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
            if (smsInboxCursor.getString(indexAddress).equals(hardwareUnit))
            {
                String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
                        "\n" + smsInboxCursor.getString(indexBody) + "\n";
                arrayAdapter.add(str);
            }


        } while (smsInboxCursor.moveToNext());
    }




}
