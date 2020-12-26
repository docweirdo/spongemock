package de.docweirdo.spongemock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class SpecialCharacters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CharSequence text = getIntent()
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);

        boolean readonly = getIntent()
                .getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false);

        assert text != null;
        
        String replacementText = specialize(text.toString());

        if (readonly){

            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cd = ClipData.newPlainText("specialized text", replacementText);
            cm.setPrimaryClip(cd);

            Context context = getApplicationContext();
            CharSequence msg = "Specialized text copied to clipboard";
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

    protected String specialize(String text){

        text = text.replace("a", "@");
        text = text.replace("A", "@");
        text = text.replace("e", "€");
        text = text.replace("E", "€");
        text = text.replace("s", "$");
        text = text.replace("S", "$");
        text = text.replace("r", "Ⓡ");
        text = text.replace("R", "Ⓡ");
        text = text.replace("c", "¢");
        text = text.replace("C", "©");


        return text;
    }
}
