package com.design.pattern.adaptor;

public class Mp4Player implements AdvancedMediaPlayer {

	   public void playVlc(String fileName) {
	      //do nothing
	   }

	   public void playMp4(String fileName) {
	      System.out.println("Playing mp4 file. Name: "+ fileName);		
	   }
}
