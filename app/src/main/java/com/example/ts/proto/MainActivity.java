package com.example.ts.proto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class MainActivity extends AppCompatActivity {
    ByteArrayOutputStream out;
    ByteArrayInputStream in;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.protoTest);
        createAndSend();

    }

    private void createAndSend(){
        Test.Person.Builder builder = Test.Person.newBuilder();
        builder.setId(11);
        builder.setName("HelloProto");
        builder.setEmail("******");
        Test.Person person = builder.build();
        out = new ByteArrayOutputStream();
        try{
            person.writeTo(out);
            getInput();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void getInput(){
        byte[] input = out.toByteArray();
        in = new ByteArrayInputStream(input);
        try{
            Test.Person person = Test.Person.parseFrom(in);
            display(person.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void display(String string){
        android.util.Log.d("liu","string:"+string);
        if(textView != null){
            textView.setText(string);
        }
    }
}
