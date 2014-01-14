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
        //获取开局时桌面扑克集合
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
				 Log.d("当前剩余牌数：", String.valueOf(pokerPool.getSurplusPokerPool().size()));
				 //获取被点击扑克的 Tag，解析出被点击扑克的花色和点数
				 String tag = ((ImageView)view).getTag().toString();
				 Poker deskPoker = new Poker();
				 String[] pokerInfo = tag.split("#");
				 deskPoker.setColor(pokerInfo[0].charAt(0));
				 if(pokerInfo.length > 1){
					 deskPoker.setPoint(Integer.parseInt(pokerInfo[1]));	 
				 }
				 Poker newPoker = pokerPool.getPoker();	//随机抽取一张新牌
				 if(null == newPoker){
					 Toast.makeText(view.getContext(), "没牌了，该局游戏结束，请返回重新开始",Toast.LENGTH_SHORT).show();
				 }else{
				 	 ((ImageView)view).setImageBitmap(pokerToBitMap(newPoker));		//覆盖被点击扑克图片
				 	 boolean king = false;
				 	 if (newPoker.getPoint() != -1){
				 		 ((ImageView)view).setTag(newPoker.getColor() + "#" + newPoker.getPoint());
				     }else{
			        	 ((ImageView)view).setTag(newPoker.getColor() + "#");
			        	 king = true;
				     }
				 	 switch(PokerCalculate.checkWin(deskPoker, newPoker)){		//判断输赢
					 	case 1:
					 		Toast.makeText(view.getContext(), "亲，炸弹要喝两杯哦",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：炸弹");
					 		break;
					 	case 2:
					 		Toast.makeText(view.getContext(), "亲，同花顺喝一杯哦",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：同花顺");
					 		break;
					 	case 3:
					 		Toast.makeText(view.getContext(), "亲，顺子喝半杯哦",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：顺子");
					 		break;
					 	case 4:
					 		Toast.makeText(view.getContext(), "起到同花，你过了，赶紧的，下一位",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：同花");
					 		break;
					 	case 5:
					 		Toast.makeText(view.getContext(), "起到对子了，下一位，速度走起",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：对子");
					 		break;
					 	case 6:
					 		Toast.makeText(view.getContext(), "什么都不是，你过了，下一位继续",Toast.LENGTH_SHORT).show();
					 		System.out.println("结果：什么都不是");
					 		break;
					 	default:
					 		Toast.makeText(view.getContext(), "呃。。。。我喝多了，不知道你过了还是没过",Toast.LENGTH_SHORT).show();
					 		break;
					 		
					 }
				 }
				 
			}
        	
        };
        

        Resources resources = this.getResources();
        Log.d("发牌人数：",String.valueOf(pokerList.size()));
        String resid;
		for(int i = 0;i < pokerList.size();i++){
//			TextView tvShow = new TextView(this.getApplicationContext());
//			tvShow.setText(String.valueOf(pokerList.get(i).point));
//			tvShow.setTextSize(30f);
//			tvShow.setTextColor(Color.BLACK);
//			tvShow.setOnClickListener(listener);
//			Log.d("当前点数：", String.valueOf(pokerList.get(i).point));
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
		System.out.println("新牌的资源花色：" + poker.getColor() + " 点数： " + poker.getPoint() + " 资源 id ： " + imageId);
		Drawable image = resources.getDrawable(imageId);
        BitmapDrawable bmd = (BitmapDrawable)image;
        return bmd.getBitmap();
	}
	

}
