package com.example.lg.firstgdg2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button search;
    EditText editText;
    ArrayList<Data> d;
    ImageView imageView;
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext =this;

        textView = (TextView)findViewById(R.id.textView);
        search = (Button)findViewById(R.id.search);
        editText= (EditText)findViewById(R.id.editText);

        mListView = (ListView)findViewById(R.id.mListView);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OpenRestAPITast a = new OpenRestAPITast();
                    String input = editText.getText().toString();

                    d = a.execute(input).get();

                    mAdapter = new ListViewAdapter(mContext);
                    mListView.setAdapter(mAdapter);

                    for(int i =0 ; i<d.size();i++){
                        mAdapter.addItem(d.get(i).getImage(),d.get(i).getTitle(),d.get(i).getLink());
                    }

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            String url = d.get(position).getLink();
                            Uri uri = Uri.parse(url);
                            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                            startActivity(intent);
                            /*
                            Uri uri = Uri.parse("http://www.facebook.com/Nowcomgame");

                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                             */
                        }
                    });


                   // textView.setText(outputTitle);

                   // Drawable qwe = imageView.getDrawable();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
