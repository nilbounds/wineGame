package com.winegame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.winegame.R;
import com.winegame.common.PokerCalculate;
import com.winegame.common.PokerPool;
import com.winegame.model.Poker;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PokerPool pokerPool = (PokerPool)this.getApplication();
        //��ȡ����ʱ�����˿˼���
        SparseArray<Poker> pokerList = pokerPool.getDeskPokerPool();
        
		LinearLayout layout2=new LinearLayout(this);  
        layout2.setOrientation(LinearLayout.VERTICAL); 
		
//		LayoutInflater inflater = LayoutInflater.from(this);    
//		View layoutView = inflater.inflate(R.layout.activity_game, null);
//		RelativeLayout layout = (RelativeLayout)layoutView;
        OnClickListener listener = new OnClickListener(){
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				 PokerPool pokerPool = (PokerPool)(GameActivity.this.getApplication());
				 Log.d("��ǰʣ��������", String.valueOf(pokerPool.getSurplusPokerPool().size()));
				 //��ȡ������˿˵� Tag��������������˿˵Ļ�ɫ�͵���
				 String tag = ((ImageView)view).getTag().toString();
				 Poker deskPoker = new Poker();
				 String[] pokerInfo = tag.split("#");
				 deskPoker.setColor(pokerInfo[0].charAt(0));
				 if(pokerInfo.length > 1){
					 deskPoker.setPoint(Integer.parseInt(pokerInfo[1]));	 
				 }
				 Poker newPoker = pokerPool.getPoker();	//�����ȡһ������
				 if(null == newPoker){
					 Toast.makeText(view.getContext(), "û���ˣ��þ���Ϸ�������뷵�����¿�ʼ",Toast.LENGTH_SHORT).show();
				 }else{
				 	 ((ImageView)view).setImageBitmap(pokerToBitMap(newPoker));		//���Ǳ�����˿�ͼƬ
				 	 boolean king = false;
				 	 if (newPoker.getPoint() != -1){
				 		 ((ImageView)view).setTag(newPoker.getColor() + "#" + newPoker.getPoint());
				     }else{
			        	 ((ImageView)view).setTag(newPoker.getColor() + "#");
			        	 king = true;
				     }
				 	 switch(PokerCalculate.checkWin(deskPoker, newPoker)){		//�ж���Ӯ
					 	case 1:
					 		Toast.makeText(view.getContext(), "�ף�ը��Ҫ������Ŷ",Toast.LENGTH_SHORT).show();
					 		System.out.println("�����ը��");
					 		break;
					 	case 2:
					 		Toast.makeText(view.getContext(), "�ף�ͬ��˳��һ��Ŷ",Toast.LENGTH_SHORT).show();
					 		System.out.println("�����ͬ��˳");
					 		break;
					 	case 3:
					 		Toast.makeText(view.getContext(), "�ף�˳�ӺȰ뱭Ŷ",Toast.LENGTH_SHORT).show();
					 		System.out.println("�����˳��");
					 		break;
					 	case 4:
					 		Toast.makeText(view.getContext(), "��ͬ��������ˣ��Ͻ��ģ���һλ",Toast.LENGTH_SHORT).show();
					 		System.out.println("�����ͬ��");
					 		break;
					 	case 5:
					 		Toast.makeText(view.getContext(), "�𵽶����ˣ���һλ���ٶ�����",Toast.LENGTH_SHORT).show();
					 		System.out.println("���������");
					 		break;
					 	case 6:
					 		Toast.makeText(view.getContext(), "ʲô�����ǣ�����ˣ���һλ����",Toast.LENGTH_SHORT).show();
					 		System.out.println("�����ʲô������");
					 		break;
					 	default:
					 		Toast.makeText(view.getContext(), "�����������Һȶ��ˣ���֪������˻���û��",Toast.LENGTH_SHORT).show();
					 		break;
					 		
					 }
				 }
				 
			}
        	
        };
        

        Resources resources = this.getResources();
        Log.d("����������",String.valueOf(pokerList.size()));
        String resid;
		for(int i = 0;i < pokerList.size();i++){
//			TextView tvShow = new TextView(this.getApplicationContext());
//			tvShow.setText(String.valueOf(pokerList.get(i).point));
//			tvShow.setTextSize(30f);
//			tvShow.setTextColor(Color.BLACK);
//			tvShow.setOnClickListener(listener);
//			Log.d("��ǰ������", String.valueOf(pokerList.get(i).point));
//			layout2.addView(tvShow);
			
			ImageView iview = new ImageView(this);
			Poker temp = pokerList.get(i);
			
			if (temp.getPoint() > -1){
				iview.setTag(temp.getColor() + "#" + temp.getPoint());
				resid = temp.getColor() + String.valueOf(temp.getPoint());
	        }else{
				iview.setTag(temp.getColor() + "#");
				resid = String.valueOf(temp.getColor()); 
	        }
			
			
			int imageId = resources.getIdentifier(resid,"drawable", this.getPackageName());
			Drawable image = resources.getDrawable(imageId);
	        
	        BitmapDrawable bmd = (BitmapDrawable)image;
	        iview.setImageBitmap(bmd.getBitmap());
	        iview.setLayoutParams(new LayoutParams(170,170));
	        
	        iview.setClickable(true);
	        iview.setOnClickListener(listener);
	        
			//pokerToImage(pokerList.get(i),listener);
	        
	        layout2.addView(iview);
		}
		
		
//		setContentView(R.layout.activity_game);
		setContentView(layout2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	
	
	public Bitmap pokerToBitMap(Poker poker){
		Resources resources = this.getResources();
		
		String resid;
		if (poker.getPoint() > -1){
			resid = poker.getColor() + String.valueOf(poker.getPoint());
        }else{
			resid = String.valueOf(poker.getColor()); 
        }
		int imageId = resources.getIdentifier(resid,"drawable", this.getPackageName());
		System.out.println("���Ƶ���Դ��ɫ��" + poker.getColor() + " ������ " + poker.getPoint() + " ��Դ id �� " + imageId);
		Drawable image = resources.getDrawable(imageId);
        BitmapDrawable bmd = (BitmapDrawable)image;
        return bmd.getBitmap();
	}
	

}
