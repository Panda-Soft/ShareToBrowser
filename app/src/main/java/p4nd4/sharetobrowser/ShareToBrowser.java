// Found on https://github.com/Panda-Soft

package p4nd4.sharetobrowser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShareToBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
                handleSendText(intent);
        }
        finish();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        String[] splitText;
        Integer httpPos;

            httpPos = sharedText.indexOf("http://");
            if (httpPos==-1) httpPos = sharedText.indexOf("https://");
            if (httpPos!=-1) {
                sharedText=sharedText.substring(httpPos);
            splitText = sharedText.split(" ");
            if (splitText.length>1) sharedText=splitText[0];

            Toast.makeText(this, sharedText, Toast.LENGTH_SHORT).show();


        if (sharedText != null) {
            Intent testBrowserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
            ResolveInfo resolveInfo = getPackageManager().resolveActivity(testBrowserIntent, PackageManager.MATCH_DEFAULT_ONLY);

            String packageName = resolveInfo.activityInfo.packageName;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharedText));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setPackage(packageName);
            try {
                startActivity(browserIntent);
            } catch (ActivityNotFoundException ex) {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharedText));
                startActivity(browserIntent);
            }
        }
        } else Toast.makeText(this,"Does not contain a link to a web page!`n"+sharedText, Toast.LENGTH_SHORT).show();
    }

}
