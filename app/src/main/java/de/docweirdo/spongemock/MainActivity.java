package de.docweirdo.spongemock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CharSequence text = getIntent()
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);

        boolean readonly = getIntent()
                .getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false);

        assert text != null;
        String replacementText = mockify(text.toString());

        if (readonly){

            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cd = ClipData.newPlainText("mocked text", replacementText);
            cm.setPrimaryClip(cd);

            Context context = getApplicationContext();
            CharSequence msg = "Mocked text copied to clipboard";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, msg, duration);
            toast.show();
            finish();

        } else {
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_PROCESS_TEXT, replacementText);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    protected String mockify(String text){
        text = text.toLowerCase();
        Random R = new Random();
        int counter = 2;
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i<text.length(); i++){
            if (text.charAt(i) == ' '){
                counter = 0;
            }
            else if ((R.nextBoolean() && counter != 2) || counter == (-1) ){
                if (counter == -1) counter = 0;
                counter++;
                sb.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            } else counter = (counter > 0) ? 0 : counter-1;
        }
        return sb.toString();
    }
}
