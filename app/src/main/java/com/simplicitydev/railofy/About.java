package com.simplicitydev.railofy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.simplicitydev.railofy.R.id.textView;

public class About extends AppCompatActivity {

    TextView v,c,t,s;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_about);

        v= (TextView) findViewById(R.id.volley);
        v.setClickable(true);
        v.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href = 'https://github.com/google/volley'> Volley </a>";
        v.setText(Html.fromHtml(text));

        c= (TextView) findViewById(R.id.cardview);
        c.setClickable(true);
        c.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://developer.android.com/reference/android/support/v7/widget/CardView.html'> Card View </a>";
        c.setText(Html.fromHtml(text2));

        t= (TextView) findViewById(R.id.tt);
        t.setClickable(true);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='https://data.gov.in/resources/indian-railways-time-table-trains-available-reservation-01112017/api'> Indian Railway Time Table API </a>";
        t.setText(Html.fromHtml(text3));

        s= (TextView) findViewById(R.id.sc);
        s.setClickable(true);
        s.setMovementMethod(LinkMovementMethod.getInstance());
        String text4 = "<a href='https://rapidapi.com/blaazetech/api/indian-railways'> Station Code API </a>";
        s.setText(Html.fromHtml(text4));


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d=new Dialog(About.this);
                d.setContentView(R.layout.feedback_layout);

                final RatingBar rt= (RatingBar) d.findViewById(R.id.rating);
                final EditText comment_val= (EditText) d.findViewById(R.id.comment);
                Button submit= (Button) d.findViewById(R.id.submit_feedback);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float rate=rt.getRating();
                        String comm=comment_val.getText().toString();
                        String to="hi.simplicitydev@gmail.com";

                        if(rate==0&&comm.isEmpty()){
                            Toast.makeText(About.this, "Please Rate the App!", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Intent i=new Intent();
                            i.setData(Uri.parse("mailto:"+to));
                            i.putExtra(Intent.EXTRA_EMAIL,to);
                            i.putExtra(Intent.EXTRA_SUBJECT,"Feedback for App");
                            i.putExtra(Intent.EXTRA_TEXT,"Rating:"+rate+"\nComment:"+comm);
                            startActivity(Intent.createChooser(i,"E-Mail"));
                        }
                    }
                });

                d.show();
            }
        });
    }
}
