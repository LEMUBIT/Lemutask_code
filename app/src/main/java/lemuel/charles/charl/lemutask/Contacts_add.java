package lemuel.charles.charl.lemutask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Contacts_add extends AppCompatActivity {
    sql_helper_lemutask sqlht;
    EditText nametx,surnametx,nicktx,emailtx,phonetx,facebooktx,twittertx,instagramtx,skilltx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_add_activity);
        sqlht=new sql_helper_lemutask(this);
        nametx=(EditText) findViewById(R.id.c_name);
        surnametx=(EditText) findViewById(R.id.c_surname);
        nicktx=(EditText) findViewById(R.id.c_nickname);
        emailtx=(EditText) findViewById(R.id.c_email);
        phonetx=(EditText) findViewById(R.id.c_num);
        facebooktx=(EditText) findViewById(R.id.c_facebook);
        twittertx=(EditText) findViewById(R.id.c_twitter);
        instagramtx=(EditText) findViewById(R.id.c_instagram);
        skilltx=(EditText) findViewById(R.id.c_skill);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.menu_todo_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save:
                add_contact();
                finish();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(Contacts_add.this,"Not Saved",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }

    public void add_contact()
    {
        boolean isins = sqlht.insertContactdata(nametx.getText().toString(),surnametx.getText().toString(),nicktx.getText().toString(),emailtx.getText().toString(),phonetx.getText().toString(),facebooktx.getText().toString(),twittertx.getText().toString(),instagramtx.getText().toString(),skilltx.getText().toString());

        if(isins)
        {
            Toast.makeText(Contacts_add.this,"Success",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(Contacts_add.this,"Not Saved",Toast.LENGTH_SHORT).show();

    }




}
