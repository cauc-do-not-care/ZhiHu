package com.chenyao.zhihu;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    EditText userName = null;
    EditText passwd = null;
    Button login = null;
    Button register = null;
    TextView textView = null;

    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.sign_in_and_sign_up);

        //set relevance between button and code

        userName = (EditText) findViewById(R.id.editText);
        passwd = (EditText)findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new loginOnClick());
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new registerOnClick());
        textView = (TextView)findViewById(R.id.other_way);
        textView.setOnClickListener(new otherWayToLogin());
    }

    /*
    * login frame without logic judgement
    * Next we should input some logical judgement and access to the database
    * logic:
    *
    */

    public class loginOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(TextUtils.isEmpty(userName.getText())){
                new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage("     用户名为空！").setPositiveButton("确定", null).show();

            }
            else if(TextUtils.isEmpty(passwd.getText())){
                new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage( "     密码为空！").setPositiveButton("确定", null).show();

            }
            else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                login.setEnabled(false);
            }
        }
    }

    public class registerOnClick implements View.OnClickListener{
        public void onClick(View v){

        }
    }

    public class otherWayToLogin implements View.OnClickListener{
        public void onClick(View v){

        }
    }

}
