package com.ledboot.nicechat.launcher;

import android.view.View;
import android.widget.OverScroller;

public class SmoothScroller {
	private View mScrollView = null;
	private OnScrollingListener mListener = null;
	private boolean mActive = false;
	private OverScroller mScroller = null;
	
	/*以相同的速度一直滑动到结束*/
	public static final int TYPE_UNIFORM_SPEED = 0; //匀速滑动
	
	public static final int TYPE_DAMP = 1;  //阻尼滑动,离目标越近滑动越慢
	
	private static final int REFRESH_RATE = 10; //刷新频率 ms
	
	private static final int UNIFORM_SPEED = 56; //匀速速度
	
	public SmoothScroller(View view) {
		mScrollView = view;
		mScroller = new OverScroller(view.getContext());
	}
	
	public synchronized void activeScroll(final int dstX , final int dstY) {
		mActive = true;
		mScroller.startScroll(mScrollView.getScrollX(), mScrollView.getScrollY(), 
								dstX-mScrollView.getScrollX(), dstY-mScrollView.getScrollY());
		if(mListener != null) {
			mListener.onScrollStart(mScrollView.getScrollX(), mScrollView.getScrollY());
		}
		mScrollView.invalidate();
	}
	
	public void onStep() {
		if(mScroller.computeScrollOffset()) {
			int x = mScroller.getCurrX();
			int y = mScroller.getCurrY();
			//mScrollView.scrollTo(x,y);
			if(mListener != null) {
				mListener.smoothScrollTo(x, y);
				mScrollView.invalidate();
			}
		}else{			
			if(mListener != null && mActive) {
				mActive = false;
				mListener.onScrollFinish(mScrollView.getScrollX(), mScrollView.getScrollY());
			}
		}
	}
	
//	public synchronized void activeScroll(final int dstX , final int dstY , final int type) {
//		mActive = true;
//		final int startX = mScrollView.getScrollX();
//		final int startY = mScrollView.getScrollY();
//		new BaseThread() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if(mListener != null) {
//					mScrollView.getHandler().post(new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							mListener.onScrollStart(mScrollView.getScrollX(),mScrollView.getScrollY());
//						}
//					});
//				}
//				while(true) {
//					final Point toPoint = getStepPoint(startX,startY,dstX,dstY,type);
//					
//					mScrollView.getHandler().post(new Runnable() {
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							mScrollView.scrollTo(toPoint.x, toPoint.y);
//							if(mListener != null) {
//								mListener.onScrollAt(toPoint.x, toPoint.y);
//							}
//						}
//					});
//
//					
//					if(toPoint.x == dstX && toPoint.y == dstY) {
//						break;
//					}
//					
//					try {
//						Thread.sleep(REFRESH_RATE);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//				if(mListener != null) {
//					mScrollView.getHandler().post(new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							mListener.onScrollFinish(dstX,dstY);
//						}
//					});
//				}
//				
//				mActive = false;
//			}
//			
//		}.start();
//	}
	
	public void setOnScrollingListener(OnScrollingListener listener) {
		mListener = listener;
	}
	
//	private Point getStepPoint(final int startX , final int startY ,final int dstX , final int dstY , final int type) {
//		Point stepPoint = new Point();
//		int currX = mScrollView.getScrollX();
//		int currY = mScrollView.getScrollY();
//		
//		switch(type) {
//		case TYPE_DAMP :
//		{
//			if(currX < dstX) {
//				if((currX + UNIFORM_SPEED) >= dstX) {
//					stepPoint.x = dstX;
//				}else{
//					stepPoint.x = currX + UNIFORM_SPEED;
//				}
//			}else if(currX > dstX){
//				if((currX - UNIFORM_SPEED) <= dstX) {
//					stepPoint.x = dstX;
//				}else{
//					stepPoint.x = currX - UNIFORM_SPEED;
//				}
//			}
//			
//			if(currY < dstY) {
//				if((currY + UNIFORM_SPEED) >= dstY) {
//					stepPoint.y = dstY;
//				}else{
//					stepPoint.y = currY + UNIFORM_SPEED;
//				}
//			}else if(currY > dstY){
//				if((currY - UNIFORM_SPEED) <= dstY) {
//					stepPoint.y = dstY;
//				}else{
//					stepPoint.y = currY - UNIFORM_SPEED;
//				}
//			}
//		}
//		break;
//		case TYPE_UNIFORM_SPEED :
//		default:
//		{
//			if(currX < dstX) {
//				if((currX + UNIFORM_SPEED) >= dstX) {
//					stepPoint.x = dstX;
//				}else{
//					stepPoint.x = currX + UNIFORM_SPEED;
//				}
//			}else if(currX > dstX){
//				if((currX - UNIFORM_SPEED) <= dstX) {
//					stepPoint.x = dstX;
//				}else{
//					stepPoint.x = currX - UNIFORM_SPEED;
//				}
//			}
//			
//			if(currY < dstY) {
//				if((currY + UNIFORM_SPEED) >= dstY) {
//					stepPoint.y = dstY;
//				}else{
//					stepPoint.y = currY + UNIFORM_SPEED;
//				}
//			}else if(currY > dstY){
//				if((currY - UNIFORM_SPEED) <= dstY) {
//					stepPoint.y = dstY;
//				}else{
//					stepPoint.y = currY - UNIFORM_SPEED;
//				}
//			}
//		}
//		break;
//		}
//		
//		return stepPoint;
//	}
	
	public interface OnScrollingListener {
		public void onScrollStart(final int x, final int y);
		public void smoothScrollTo(final int x, final int y);
		public void onScrollFinish(final int x, final int y);
	}
}
