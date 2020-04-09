/* FILE: CameraCommand.java
 * PROJECT: AutoX Watchdog
 * PROGRAMMER: Cavan Biggs
 * FIRST VERSION: February 10th 2020
 * DESCRIPTION: Source in this file is used to handle the outgoing commands that are sent to the
 *              hardware unit via SMS messages.
 *
 *
 *
 *
 *
 */

package autoxwatchdog.commander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class CameraCommand extends AppCompatActivity {

    private SmsManager smgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_command);
        //Initialize the sms manager variable
        smgr = SmsManager.getDefault();
    }


    /*
     *	METHOD			  : sendFrontCaptureCommand
     *
     *	DESCRIPTION		  : Button event handler
     *
     *
     *	PARAMETERS		  : View view
     *
     *
     *	RETURNS			  : void
     *
     */
    public void sendFrontCaptureCommand(View view) {
        buttonTest("Front Image Requested: " + getString(R.string.capture_front) );
        sendCommandToHardware(getString(R.string.capture_front));
    }
    /*
     *	METHOD			  : sendLeftCaptureCommand
     *
     *	DESCRIPTION		  : Button event handler
     *
     *
     *	PARAMETERS		  : View view
     *
     *
     *	RETURNS			  : void
     *
     */
    public void sendLeftCaptureCommand(View view) {
        buttonTest("Left Image Requested: " + getString(R.string.capture_left));
        sendCommandToHardware(getString(R.string.capture_left));
    }
    /*
     *	METHOD			  : sendRightCaptureCommand
     *
     *	DESCRIPTION		  : Button event handler
     *
     *
     *	PARAMETERS		  : View view
     *
     *
     *	RETURNS			  : void
     *
     */
    public void sendRightCaptureCommand(View view) {
        buttonTest("Right Image Requested: " + getString(R.string.capture_right));
        sendCommandToHardware(getString(R.string.capture_right));
    }
    /*
     *	METHOD			  : sendRearCaptureCommand
     *
     *	DESCRIPTION		  : Button event handler
     *
     *
     *	PARAMETERS		  : View view
     *
     *
     *	RETURNS			  : void
     *
     */
    public void sendRearCaptureCommand(View view) {
        buttonTest("Rear Image Requested: " + getString(R.string.capture_rear));
        sendCommandToHardware(getString(R.string.capture_rear));
    }
    /*
     *	METHOD			  : buttonTest
     *
     *	DESCRIPTION		  : Displays a toast message to the user showing what button
     *                      was pressed.
     *
     *
     *	PARAMETERS		  : String buttonPressed
     *
     *
     *	RETURNS			  : void
     *
     */
    private void buttonTest(String buttonPressed){
        Context context = getApplicationContext();
        CharSequence text = buttonPressed;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    /*
     *	METHOD			  : sendCommandToHardware
     *
     *	DESCRIPTION		  : Sends the command to the hardware via SMS
     *
     *
     *	PARAMETERS		  : String command
     *
     *
     *	RETURNS			  : void
     *
     */
    //Send the command to the hardware
    private void sendCommandToHardware(String command){

        smgr.sendTextMessage(getString(R.string.hardware_unit), null, command, null, null);

    }
    /*
     *	METHOD			  : viewReceivedCaptures
     *
     *	DESCRIPTION		  : Launches the activity to view returned captures
     *
     *
     *	PARAMETERS		  : View view
     *
     *
     *	RETURNS			  : void
     *
     */
    public void viewReceivedCaptures(View view) {
        Intent intent = new Intent(this, CaptureView.class);
        startActivity(intent);

    }
}
