package juanfu.recordatorios;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.data;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //mFileId = (DriveId) data.getParcelableExtra(
         //       OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);
        //String url = "https://docs.google.com/document/d/1hQnBnOaR_xmUrKA2tFykZX1Ri_bCgtzX9Y_8hB6-vbg/edit?usp=sharing";
        //Intent i = new Intent(Intent.ACTION_VIEW);
        //i.setData(Uri.parse(url));

        //startActivity(i);

        //try {
            // Create a URL for the desired page
            //URL url = new URL("https://docs.google.com/document/d/1hQnBnOaR_xmUrKA2tFykZX1Ri_bCgtzX9Y_8hB6-vbg");
        //    URL url = new URL("https://pastebin.com/raw/ypZayTpA");
            // Read all the text returned by the server
        //    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        //    String str;
        //    while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                //texto.setText(texto.getText()+str +  "\n");

        //    }
       //     in.close();
        //} catch (MalformedURLException e) {
       //     e.printStackTrace();
      //  } catch (IOException e) {
       //   e.printStackTrace();
       // }
        usoDatos dat = new usoDatos();



        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                boolean isWifiConn = networkInfo.isConnected();
                networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                boolean isMobileConn = networkInfo.isConnected();
                Log.d( "MyInfoTag","Wifi connected: " + isWifiConn);
                Log.d("MyInfoTag","Mobile connected: " + isMobileConn);



                String cadena = "";
                String br= "\n";
                try  {
                    TextView texto = (TextView) findViewById(R.id.tevie);
                    //texto.setText("");
                    URL url = null;
                    //if(isOnlineNet())
                    try {
                        url = new URL("https://pastebin.com/raw/ypZayTpA");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    BufferedReader reader = null;
                    StringBuilder builder = new StringBuilder();

                    try {
                        reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                        for (String line; (line = reader.readLine()) != null;) {
                            builder.append(line.trim());
                            cadena = cadena + line + br;

                           Log.e("myInfoTag",line);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
                    }

                    texto.setText(cadena);
                    //String start = "<div class=\"post-text\"><p>";
                    //String end = "</p>";
                    //String part = builder.substring(builder.indexOf(start) + start.length());
                    //String question = part.substring(0, part.indexOf(end));
                    //texto.setText(question);
                    //System.out.println(question);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
