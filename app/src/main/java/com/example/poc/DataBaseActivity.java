package com.example.poc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText itemname, quantity, itemunit;
    Button add, showitem;
    TextView fdpClickHere;

    User user;
    ProgressBar progressBar;

    private DatabaseReference mDatabase;
    //FirebaseDatabase Database;
    //DatabaseReference ref;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        itemname = (EditText) findViewById(R.id.item);
        quantity = (EditText) findViewById(R.id.qty);
        itemunit = (EditText) findViewById(R.id.unit);
        add = (Button) findViewById(R.id.btninsert);
        showitem = (Button) findViewById(R.id.showItem);
        fdpClickHere = (TextView) findViewById(R.id.textview_diffrent);

        user = new User();
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        FirebaseApp.initializeApp(this);
        //ref = Database.getReference();
        //mAuth=FirebaseAuth.getInstance();


        findViewById(R.id.btninsert).setOnClickListener(this);
        findViewById(R.id.showItem).setOnClickListener(this);
        findViewById(R.id.textview_diffrent).setOnClickListener(this);

    }
    private void getvalues()
    {
        user.setName(itemname.getText().toString());
        user.setQuan(quantity.getText().toString());
        user.setUni(itemunit.getText().toString());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btninsert:

                mDatabase = FirebaseDatabase.getInstance().getReference();
                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                //final DatabaseReference ref = database.getReference("user01");
                progressBar.setVisibility(View.VISIBLE);
                //ref.setValue("Hello, World!");
                mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //ref.child("User01").setValue(user);
                    getvalues();
                    progressBar.setVisibility(View.GONE);
                    mDatabase.child("users").child("03").setValue(user);
                    Toast.makeText(DataBaseActivity.this, "Database inserted...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(DataBaseActivity.this,"Some error occured",Toast.LENGTH_SHORT).show();
                }
                });
                break;

            case R.id.showItem:

                break;

            case R.id.textview_diffrent:

                break;

        }
    }
}