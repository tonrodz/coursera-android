package course.labs.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define View objects, one for each of the 5 rectangles
        final View textView1 = findViewById(R.id.textViewUpperLeft);
        final View textView2 = findViewById(R.id.textViewLowerLeft);
        final View textView3 = findViewById(R.id.textViewUpperRight);
        final View textView4 = findViewById(R.id.textViewMiddleRight);
        final View textView5 = findViewById(R.id.textViewLowerRight);

        //define SeekBar object
        SeekBar seek = (SeekBar) findViewById(R.id.seekBar);

        // set the background RGB color for each rectangle
        textView1.setBackgroundColor(Color.rgb(127, 127, 255));
        textView2.setBackgroundColor(Color.rgb(255, 127, 127));
        textView3.setBackgroundColor(Color.rgb(255, 0, 0));
        textView4.setBackgroundColor(Color.rgb(255, 255, 255));   //white
        textView5.setBackgroundColor(Color.rgb(0, 0, 255));

        // add OnSeekBarChangeListener
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // increment each of the RGB color values for each rectangle, except the white one,
            // when the seekbar moves (different combinations will give different colors)
            // "progress" ranges from 0 to 100 by default
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView1.setBackgroundColor(Color.rgb(127 + progress, 127 + progress, 255));
                textView2.setBackgroundColor(Color.rgb(255, 127 + progress, 127 + progress));
                textView3.setBackgroundColor(Color.rgb(255 - progress, 0, 0 + progress));
                textView5.setBackgroundColor(Color.rgb(0 + progress, 0 + progress, 255));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // not overriden
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // not overriden
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu to add "More Information" item to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // For the action bar selection
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialogFragment(MenuItem item) {
        // show the DialogFragment when the action bar "More Information" item is clicked
        new MoreInfoDialogFragment().show(getFragmentManager(), MainActivity.class.getName());
    }

    public static class MoreInfoDialogFragment extends DialogFragment {
        // DialogFragment to Create an AlertDialog for the selection
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.dialog_text)

                    // set up "not now" button
                    .setNegativeButton(R.string.dialog_notnow, null)

                    // set up "Visit MOMA" button
                    .setPositiveButton(R.string.dialog_visit,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        // implicit intent to open webpage
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                            Intent chooser = Intent.createChooser(intent, "Open With");
                            startActivity(chooser);
                        }
                    })
                    .create();
        }
    }
}