package com.winegame;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.winegame.activity.GameActivity;
import com.winegame.common.PokerPool;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.winegame.R.layout.activity_start);
        Button btnConfirm = (Button)this.findViewById(R.id.btnConfirm);
        Button.OnClickListener listener = new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 EditText txtPeopleNum = (EditText)findViewById(com.winegame.R.id.txtPeopleNum);
				 EditText txtPorkSet = (EditText)findViewById(com.winegame.R.id.txtPorkSet);
			     
				 Intent gameIntent = new Intent(getApplicationContext(), GameActivity.class);
			     gameIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			     
			     int peopleNum = Integer.parseInt(txtPeopleNum.getText().toString());
			     int pokerSet = Integer.parseInt(txtPorkSet.getText().toString());
			     
			     
			     //��ȡApplication,ȫ�ֱ���������
			     PokerPool pokerPool = (PokerPool)(StartActivity.this.getApplication());
			     pokerPool.resumes();
			     //��ʼ���˿˳�
			     pokerPool.initPokerPool(peopleNum, pokerSet);
			     //��ʼ��Ϸ����ȡ��ʼʱ�����˿�
			     pokerPool.startPork();
			     //gameIntent.putExtra("startList", (Serializable)pokerPool.getDeskPokerPool());
			     startActivity(gameIntent);
			}
		};
        btnConfirm.setOnClickListener(listener);
       
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.winegame.R.menu.start, menu);
        return true;
    }
    
}
