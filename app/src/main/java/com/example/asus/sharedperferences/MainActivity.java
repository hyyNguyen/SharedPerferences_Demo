package com.example.asus.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_save;
    private Button btn_show, btn_removekey, btn_removeall;
    public  final String SHARE_PERFERENCES_NAME = "huydeptrai";
   private final String MYNAME ="my_name";
    private final String AGE="age";
    private final String IS_SINGLE ="is_single";
    private final String WEIGHT = "weight";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_removekey = (Button) findViewById(R.id.btn_removekey);
        btn_removeall = (Button) findViewById(R.id.btn_removeall);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        btn_removekey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeBykey(MYNAME);
            }
        });
        btn_removeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeByAll();

            }
        });
    }
    @Override
    public void onClick(View view) {
        //khia bao doi tuong sharePerferences de luu xun
        //mode_private  khi luu xun chi co ung dung nay moi doc duoc
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PERFERENCES_NAME, Context.MODE_PRIVATE);
        //dua du lieu vao SHARE_PERFERENCES_NAME = "huydeptrai";
        //phai khoi tao editer.
        //la cong cu soan thao hay cong cu dua du lieu vao
        //mo cai file ra
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //dua du lieu vao
        //luu xun key va value
        editor.putString(MYNAME,"Nguyen Hoa Huy");
        editor.putInt(AGE,20);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGHT,50);

        //goi apply hay commit du lieu ms duoc dua vao
        //so sanh apply va commit
        //commit hoat động theo cơ chế đồng bộ. apply bât đồng bộ
        //commit tra ve kieu true hay false. true la lưu dc rồi. false chưa
        //khác nhau. commit khong dong do. then nao vo truoc thi sua truoc
        //apply thi ai sửa đường nấy. apply nhanh hơn
        editor.apply();
        Toast.makeText(this, "Thành cmn công", Toast.LENGTH_SHORT).show();
//        editor.commit();

    }

    private void show() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PERFERENCES_NAME, Context.MODE_PRIVATE);
        //lay ra key va gia tri mặc định. khi không có tên thì ra giá trị mặc định
        String name= sharedPreferences.getString(MYNAME,"huy");
        int age = sharedPreferences.getInt(AGE, 10);
        boolean isSingle = sharedPreferences.getBoolean(IS_SINGLE,false);
        long weight = sharedPreferences.getLong(WEIGHT, 40);
        Toast.makeText(this, "name: "+name +"\n" +"tuoi: "+age+"\n" + "Single: " + isSingle+"\n" + "weight: " + weight, Toast.LENGTH_SHORT).show();
    }
    //xoa theo key
    public void removeBykey(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PERFERENCES_NAME, Context.MODE_PRIVATE);
        //edit co quen de xoa
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();

    }
    public void removeByAll(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PERFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
