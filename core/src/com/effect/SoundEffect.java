package com.effect;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.ss.core.util.GAssetsManager;

/* renamed from: com.ss.effect.SoundEffect */
public class SoundEffect {
    public static int MAX_COMMON = 20;
    public static int MAX_Music = 20;
//    public static Music bg1 = null;
//    public static Music bg2 = null;
    public static Sound[] commons = null;
    public static Music[] Music = null;
    public static boolean music = false;
    public static boolean mute = false;
    public  static int click =0;
    public  static int Result =1;
    public  static int Tick =2;
    public  static int unlock =3;
    public  static int Result2 =4;
    public  static int SwitchIn =5;
    public  static int SwitchOut =6;
    public  static int panelIn =7;
    public  static int panelOut =8;
    public  static int toggle =9;

    public  static int bg0 = 0;
    public  static int bg1 = 1;
    public  static int bg2 = 2;
    public  static int bg3 = 3;
    public  static int bg4 = 4;
    public  static int bg5 = 5;
    public  static int bg6 = 6;
    public  static int bg7 = 7;
    public  static int bg8 = 8;
    public  static int bgresult = 9;



    public static Audio bg = null;


    public static void initSound() {
        commons = new Sound[MAX_COMMON];
        Music = new Music[MAX_COMMON];
        commons[click] = GAssetsManager.getSound("click.mp3");
        commons[Result] = GAssetsManager.getSound("result.mp3");
        commons[Tick] = GAssetsManager.getSound("erase.mp3");
        commons[unlock] = GAssetsManager.getSound("unlock.mp3");
        commons[Result2] = GAssetsManager.getSound("result2.mp3");
        commons[SwitchIn] = GAssetsManager.getSound("switchIn.mp3");
        commons[SwitchOut] = GAssetsManager.getSound("switchOut.mp3");
        commons[panelIn] = GAssetsManager.getSound("panel_open.mp3");
        commons[panelOut] = GAssetsManager.getSound("panel_open.mp3");
        commons[toggle] = GAssetsManager.getSound("Toggle.mp3");
        //bg1 = GAssetsManager.getMusic("bg.mp3");
//        bg2 = GAssetsManager.getMusic("bg2.mp3");
        Music[bg0] =  GAssetsManager.getMusic("bg0.mp3");
        Music[bg1] =  GAssetsManager.getMusic("bg4.mp3");
        Music[bg2] =  GAssetsManager.getMusic("bg8.mp3");
        Music[bg3] =  GAssetsManager.getMusic("bg3.mp3");
        Music[bg4] =  GAssetsManager.getMusic("bg.mp3");
        Music[bg5] =  GAssetsManager.getMusic("bg5.mp3");
        Music[bg6] =  GAssetsManager.getMusic("bg6.mp3");
        Music[bg7] =  GAssetsManager.getMusic("bg7.mp3");
        Music[bg8] =  GAssetsManager.getMusic("bg2.mp3");
        Music[bgresult] =  GAssetsManager.getMusic("bgResult.mp3");

    }

    public static void Play(int i) {
        if (!mute) {
            commons[i].play();
        }
    }

    public static void Playmusic(int i) {
            Music[i].play();
            Music[i].setLooping(true);
            Music[i].setVolume(5);

    }

    public static void Stopmusic(int i){
        Music[i].stop();
    }
    public static void Pausemusic(int i){
        Music[i].pause();
    }
}
