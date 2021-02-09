// Found on https://github.com/Panda-Soft

package p4nd4.sharetobrowser;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

    }


    public void setDefaultBrowser(View v) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final Intent intent = new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
            startActivity(intent);
        }
        else {

            final Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            startActivity(intent);
        }
    }

    public void myDevelopersPage(View v) {

        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/dev?id=5847423621940926942"));
        startActivity(i);
    }


    public void myDonation(View v) {
        Uri uri = Uri.parse("https://github.com/Panda-Soft");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);

    }
}
