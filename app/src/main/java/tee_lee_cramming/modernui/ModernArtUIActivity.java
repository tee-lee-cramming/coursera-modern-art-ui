package tee_lee_cramming.modernui;

import android.app.DialogFragment;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;


public class ModernArtUIActivity extends ActionBarActivity {

    static String SEEK_TAG = "seek_value";

    ArrayList<View> mViews = new ArrayList<View>();
    static ArrayList<Integer> mViewIds =
            new ArrayList<Integer>(Arrays.asList(R.id.rect1, R.id.rect2, R.id.rect3,
                    R.id.rect4, R.id.rect5));
    static ArrayList<Integer> mColors =
            new ArrayList<Integer>(Arrays.asList(
                    Color.argb(255, 31, 255, 63),
                    Color.argb(255, 63, 63, 255),
                    Color.argb(255, 255, 0, 0),
                    Color.argb(255, 0, 255, 255),
                    Color.argb(255, 255, 255, 255)
            )
            );

    private SeekBar mSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_ui);

        for (Integer viewId : mViewIds){
            View view = findViewById(viewId);

            mViews.add(view);
        }
        updateColor(0);
        mSeekbar = (SeekBar) findViewById(R.id.seekbar);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor(seekBar.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                updateColor(seekBar.getProgress());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateColor(seekBar.getProgress());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            DialogFragment dialogFragment = new InfoDialogFragment();
            dialogFragment.show(getFragmentManager(), "xyz");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int interpolateColor(int percent, int start, int end){
        return (int) (percent /100.0 * (end - start) + start);
    }

    private void updateColor(int value){
        for (View view : mViews){
            int color = mColors.get(mViews.indexOf(view));
            int red = Color.red(color);
            int blue = Color.blue(color);
            int green = Color.green(color);
            int alpha = Color.alpha(color);
            int actual_value = (red == blue && red == green)? 100:value;

            view.setBackgroundColor(Color.argb(alpha,
                    interpolateColor(actual_value, red, green),
                    interpolateColor(actual_value, green, blue),
                    interpolateColor(actual_value, blue, red)));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SEEK_TAG, mSeekbar.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSeekbar.setProgress(savedInstanceState.getInt(SEEK_TAG));
    }
}
