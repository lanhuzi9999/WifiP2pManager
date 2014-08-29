package com.tarena.aidl;
interface IPlayControllor{
   void play();
   void pause();
   void next();
   void previous();
   void seekTo(int time);
}