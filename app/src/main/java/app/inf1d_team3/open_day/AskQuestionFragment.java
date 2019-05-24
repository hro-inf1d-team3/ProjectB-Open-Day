package app.inf1d_team3.open_day;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskQuestionFragment extends Fragment implements FragmentOnClickable {

    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    public AskQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_ask_question, container, false);

        mEditTextSubject = fragmentView.findViewById(R.id.edit_text_subject);
        mEditTextMessage = fragmentView.findViewById(R.id.edit_text_message);

        return fragmentView;
    }

    private void sendMail(View view) {
        String[] recipients = new String[]{" studievoorlichting@hr.nl"};

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setData(Uri.parse("mailto:"));
        startActivity(Intent.createChooser(intent, "Choose an email application"));
    }

    @Override
    public void fragmentOnClick(View v) {
        if(v.getId() == R.id.button_send) sendMail(v);
    }
}
