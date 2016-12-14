package com.amanuel.guecsftrial;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
/**
 * Created by Amanuel on 8/9/2016.
 */
public class Registration extends Fragment implements AdapterView.OnItemSelectedListener {



    EditText fname,lname,department;
    private String m;
    Spinner  campus,year,team;
    Button submit,browse;
    RadioButton male,female;
    View view;
    String Year,Campus,Team;
   // Context context;
    Activity activity;
    RadioGroup Radio_Group;
    private static final int SELECTED_PICTURE=1;
    ImageView im;



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        Year=parent.getItemAtPosition(pos).toString();

        Team=parent.getItemAtPosition(pos).toString();
        Campus=parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


          view= inflater.inflate(R.layout.registration, container,false);

        fname=(EditText)view.findViewById(R.id.First_name);
        lname=(EditText)view.findViewById(R.id.Last_name);
        department=(EditText)view.findViewById(R.id.Field_of_study);
        campus=(Spinner)view.findViewById(R.id.spinner_campus);
        year=(Spinner)view.findViewById(R.id.spinner_Year);
        team=(Spinner)view.findViewById(R.id.spinner_Ministering);
        submit=(Button)view.findViewById(R.id.button);
        browse=(Button) view.findViewById(R.id.Browse);
        male=(RadioButton)view.findViewById(R.id.Male);
        female=(RadioButton)view.findViewById(R.id.Female);
        Radio_Group=(RadioGroup)view.findViewById(R.id.Radio_group);
        im=(ImageView)view.findViewById(R.id.account_pic);
        activity=getActivity();



        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getContext(),
                R.array.Year,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter);
        year.setOnItemSelectedListener(this);



        ArrayAdapter<CharSequence> adapter2 =ArrayAdapter.createFromResource(getContext(),
                R.array.Team,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team.setAdapter(adapter2);
        team.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(getContext(),
                R.array.campus,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campus .setAdapter(adapter1);
        campus.setOnItemSelectedListener(this);
       Radio_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.Male:
                        m="male";
                        break;
                    case R.id.Female:
                        m="female";
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Full Name:" + fname.getText().toString()+" " + lname.getText().toString() + "\n" +
                        "Sex:" + m + "\n"+"Campus:"+ Campus+ "\n" + "Year of Study:" +Year+ "\n"+
                        "Field of Study:"+department.getText().toString()+"Ministering team:"+Team;

               sendEmail(message);

            }


        });


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i= new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,SELECTED_PICTURE);

            }
        });
        return view;
       }
   /* public void btnClick(View v){

        switch (v.)
        Intent i= new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,SELECTED_PICTURE);

        }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case SELECTED_PICTURE:
                if (resultCode== getActivity().RESULT_OK){
                    Uri uri =data.getData();
                    String[]projection={MediaStore.Images.Media.DATA};

                    Cursor cursor=getActivity().getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filePath=cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage= BitmapFactory.decodeFile(filePath);
                    Drawable d= new BitmapDrawable(yourSelectedImage);
                    im.setBackground(d);
                }
                break;



        }
    }
/*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case SELECTED_PICTURE:
                if (resultCode==RESULT_OK){
                    Uri uri =data.getData();
                    String[]projection={MediaStore.Images.Media.DATA};

                    Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filePath=cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage= BitmapFactory.decodeFile(filePath);
                    Drawable d= new BitmapDrawable(yourSelectedImage);
                    im.setBackground(d);
                }
                break;



        }

    }*/



    protected void sendEmail(String message) {
        String[] array = new String[]{"azewdu87@gmail.com"};
        String subject = ("Here is my Profile");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, array);//reciver
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);// message subject
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);//
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));

    }


    @Override
    public void onConfigurationChanged(final Configuration newConfig)
    {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }

}
