/*
 * Copyright (c) 2014. CodeBoyTeam
 */

package com.codeboy.app.oschina.ui;

import java.util.List;

import android.widget.BaseAdapter;

import com.codeboy.app.oschina.R;
import com.codeboy.app.oschina.modul.MessageData;

import net.oschina.app.adapter.ListViewTweetAdapter;
import net.oschina.app.bean.TweetList;
import net.oschina.app.bean.Tweet;
import net.oschina.app.common.UIHelper;
import net.oschina.app.core.AppException;

/**
 * 类名 TweetHotFragment.java</br>
 * 创建日期 2014年4月24日</br>
 * @author LeonLee (http://my.oschina.net/lendylongli)</br>
 * Email lendylongli@gmail.com</br>
 * 更新时间 2014年4月24日 下午10:13:27</br>
 * 最后更新者 LeonLee</br>
 * 
 * 说明 热门动弹
 */
public class TweetHotFragment extends BaseSwipeRefreshFragment<Tweet, TweetList> {

	@Override
	public BaseAdapter getAdapter(List<Tweet> list) {
		return new ListViewTweetAdapter(getActivity(), list, R.layout.tweet_listitem);
	}

	@Override
	protected MessageData<TweetList> asyncLoadList(int page, boolean reflash) {
		MessageData<TweetList> msg = null;
		try {
			TweetList list = mApplication.getTweetList(TweetList.CATALOG_HOT, page, reflash);
			msg = new MessageData<TweetList>(list);
		} catch (AppException e) {
			e.printStackTrace();
			msg = new MessageData<TweetList>(e);
		}
		return msg;
	}
	
	@Override
	public void onItemClick(int position, Tweet data) {
		Tweet tweet = getData(position);
		// 跳转到动弹详情&评论页面
		UIHelper.showTweetDetail(getActivity(), tweet.getId());
	}
}
