package com.tarena.utils;

public class GolbalConsts {
	public static final String EXTRA_CURRENT_POSITION = "currentPosition";
	
	public static final String EXTRA_PLAY_MODE = "playMode";
	public static final int PLAY_MODE_LOOP = 1;
	public static final int PLAY_MODE_RANDOM = 2;
	
	public static final String EXTRA_PLAY_STATE = "playState";
	public static final int STATE_PLAYING=1;
	public static final int STATE_PAUSE=2;
	public static final int STATE_OTHERS=3;
	// Activity发送的广播
	public static final String ACTION_PLAY = "com.tarena.action.PLAY";
	public static final String ACTION_PAUSE = "com.tarena.action.PAUSE";
	public static final String ACTION_PREVIOUS = "com.tarena.action.PREVIOUS";
	public static final String ACTION_NEXT = "com.tarena.action.NEXT";
	public static final String ACTION_PLAYMODE_CHANGED = "com.tarena.action.PLAYMODE_CHANGED";
	public static final String ACTION_STOP_SERVICE = "com.tarena.action.STOP_SERVICE";
	public static final String ACTION_ACTIVITY_STARTED = "com.tarena.action.ACTIVITY_STARTED";
	public static final String ACTION_ACTIVITY_STOPED = "com.tarena.action.ACTIVITY_STOPED";
	public static final String ACTION_SEEK_TO = "com.tarena.action.SEEK_TO";

	// Service发送的广播
	public static final String ACTION_CURRENT_MUSIC_CHANGED = "com.tarena.action.CURRENT_MUSIC_CHANGED";
	public static final String ACTION_UPDATE_PROGRESS = "com.tarena.action.UPDATE_PROGRESS";
	public static final String ACTION_RESPONSE_PLAY_STATE = "com.tarena.action.RESPONSE_PLAY_STATE";
}
