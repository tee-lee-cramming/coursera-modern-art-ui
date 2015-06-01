package tee_lee_cramming.modernui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by tlee on 01/06/15.
 */
public class ModernArtUIActivityTest extends ActivityInstrumentationTestCase2<ModernArtUIActivity>{
    public ModernArtUIActivityTest(){super(ModernArtUIActivity.class);}

    private Activity mActivity;
    private Solo solo;


    protected void setUp() throws Exception {
        super.setUp();

        solo =  new Solo(getInstrumentation(),getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testSimple(){
        int delay = 2000;
        assertTrue(
                "ImplicitTest:" +
                        "Section One:" +
                        "ActivityLoaderActivity did not load correctly",
                solo.waitForActivity(ModernArtUIActivity.class, delay));


        solo.sleep(delay);

        int before = ((ColorDrawable) solo.getView(R.id.rect3).getBackground()).getColor();

        solo.setProgressBar(0, 30);
        solo.sleep(delay);
        int after = ((ColorDrawable) solo.getView(R.id.rect3).getBackground()).getColor();

        assertTrue("color did not change", after != before);

        solo.clickOnImage(0);
        solo.sleep(delay);
        solo.clickOnText("More Information");
        solo.sleep(delay);
        solo.clickOnText("Not Now");
        solo.sleep(delay);

        solo.clickOnImage(0);
        solo.sleep(delay);
        solo.clickOnText("More Information");
        solo.sleep(delay);
        solo.clickOnText("Visit MOMA");

        solo.sleep(delay);




    }

}
