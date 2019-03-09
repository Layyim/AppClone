package com.example.aly.appclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class WhatsappUser extends AppCompatActivity
{
    private ListView listView;
    private ArrayList<String> waUsers;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_user);

        FancyToast.makeText(this, "Welcome " +
                        ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_LONG,
                FancyToast.INFO, true).show();

        listView = findViewById(R.id.listView);
        waUsers = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tUsers);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        try
        {
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
            query.findInBackground(new FindCallback<ParseUser>()
            {
                @Override
                public void done(List<ParseUser> objects, ParseException e)
                {
                    if (objects.size() > 0 && e == null)
                    {
                        for (ParseUser whatsAppUser : objects)
                        {
                            waUsers.add(whatsAppUser.getUsername());
                        }

                        listView.setAdapter(adapter);
                    }
                }
            });
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.logout_item:
                FancyToast.makeText(WhatsappUser.this,
                        ParseUser.getCurrentUser().getUsername() + " has logged out.",
                        FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        if (e == null)
                        {
                            Intent intent = new Intent(WhatsappUser.this, SignUp.class);
                            startActivity(intent);
                            finish();
                        }
                     }
                });

                break;

/*            case R.id.sendTweetItem:

                Intent intent = new Intent(TwitterUsers.this, SendTweetActivity.class);
                startActivity(intent);

                break;*/
        }

        return super.onOptionsItemSelected(item);
    }
}
