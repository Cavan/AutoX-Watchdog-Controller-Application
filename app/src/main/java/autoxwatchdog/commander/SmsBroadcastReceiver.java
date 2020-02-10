package autoxwatchdog.commander;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Calendar;

import static android.Manifest.permission_group.CALENDAR;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";
    private static final String hardwareUnitAddress = "12262289161";
    private static final String TAG = "SmsBroadcastReceiver";

    public void onReceive(Context context, Intent intent){

        Bundle intentExtras = intent.getExtras();
        SmsMessage smsMessage = null;
        Calendar calendar = Calendar.getInstance();


        if (intentExtras != null){
            Object [] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i){
                String format = intentExtras.getString("format");
                smsMessage = SmsMessage.createFromPdu((byte[]) sms[i], format);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();


                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";

            }
            //Add the date and time to the message
            calendar.setTimeInMillis(smsMessage.getTimestampMillis());
            int date = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String dateStr = Integer.toString(date);
            String hourStr = Integer.toString(hour);
            smsMessageStr += "Date: " + dateStr + "\n";
            smsMessageStr += "Time: " + hourStr + "\n";

                CaptureView inst = CaptureView.instance();
                inst.updateInbox(smsMessageStr);


        }
    }
}
