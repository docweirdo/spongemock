package de.docweirdo.spongemock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class StrikeThrough extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CharSequence text = getIntent()
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);

        boolean readonly = getIntent()
                .getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false);

        assert text != null;
        
        String replacementText = strikeThrough(text.toString());

        if (readonly){

            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cd = ClipData.newPlainText("struck through text", replacementText);
            cm.setPrimaryClip(cd);

            Context context = getApplicationContext();
            CharSequence msg = "Struck through text copied to clipboard";
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

    protected String strikeThrough(String text){

        char cp = '\u0336';

        text = insertPeriodically(text, cp, 1);

        return text;
    }

    public static String insertPeriodically(
    String text, char character, int period)
    {
        StringBuilder builder = new StringBuilder(
            text.length() * (text.length()/period)+1);

        int index = 0;
        String prefix = "";
        while (index < text.length())
        {
            // Don't put the insert in the very first iteration.
            // This is easier than appending it *after* each substring
            builder.append(prefix);
            prefix = "" + character;
            builder.append(text.substring(index, 
                Math.min(index + period, text.length())));
            index += period;
        }

        builder.append(prefix);

        return builder.toString();
    }
}
