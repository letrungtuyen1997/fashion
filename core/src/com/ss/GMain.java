package com.ss;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.effect.SoundEffect;
import com.platform.IPlatform;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.ss.core.exSprite.particle.GParticleSystem;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GDirectedGame;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GStage.StageBorder;
import com.ss.gameLogic.config.C;
import com.ss.scenes.GameLoadding;
import com.ss.scenes.GamePlay;
import com.ss.scenes.GameStart;

public class GMain extends GDirectedGame {

  public static boolean isDebug = false;
  public static final boolean isTest = false;
  public static int screenHeight = 0;
  public static int screenWidth = 0;
  public static final int testType = 2;
  public static TextureAtlas textureAtlas;
  public static float ratioX, ratioY;
  public static Preferences Pref;
  public static  int checkfirt1 =0;
  public static  int checkfirt2 =0;
  public static  int checkfirt3 =0;
  public static  int checkfirt4 =0;
  public static  int checkfirt5 =0;
  public static  int checkfirt6 =0;
  public static char[] arrayLockHair = new char[]{};
  public static char[] arrayLockBg = new char[]{};
  public static char[] arrayLockDress = new char[]{};
  public static char[] arrayLockShirt = new char[]{};
  public static char[] arrayLockShoes = new char[]{};
  public static char[] arrayLockTrousers = new char[]{};
  public static IPlatform platform;
  public GMain(IPlatform plat){
    platform = plat;
  }

  private void init()
  {
    float n = 480.0f;
    final boolean b = false;// 0.0f > 1.0f;
    final float n2 = Gdx.graphics.getWidth();
    final float n3 = Gdx.graphics.getHeight();
    final float n4 = n2 / n3;
    float n5;
    float n6;
    if (n4 == 0.0f) {
      n5 = 0.0f;
      n6 = 848.0f;
    }
    else if ((b && n4 > 0.0f) || (!b && n4 < 0.0f)) {
      final float n7 = 848.0f * n4;
      n5 = (n - n7) / 2.0f;
      n = n7;
      n6 = 848.0f;
    }
    else if ((b && n4 < 0.0f) || (!b && n4 > 0.0f)) {
      final float max = Math.max(800.0f, n / n4);
      GMain.screenHeight = (int)(0.5f + max);
      n6 = max;
      n5 = 0.0f;
    }
    else {
      n = n2;
      n6 = n3;
      n5 = 0.0f;
    }

    screenWidth = 720;
    screenHeight = 1280;
    n = 720;
    n6 = 1280;

    GStage.init(n, n6, n5, 0, new StageBorder() {
      @Override
      public void drawHorizontalBorder(Batch spriteBatch, float paramFloat1, float paramFloat2) {

      }

      @Override
      public void drawVerticalBorder(Batch spriteBatch, float paramFloat1, float paramFloat2) {

      }
    });
  }


  


  public void create()
  {
      SoundEffect.initSound();
      Pref = Gdx.app.getPreferences("my Data");
      ////// hair//////
      arrayLockHair = Pref.getString("hair").toCharArray();
//      Pref.putString("hair","");
//      Pref.flush();
//      Pref.putInteger("check1",0);
//      Pref.flush();
      checkfirt1 = Pref.getInteger("check1");
      ////////// bg ////////
      arrayLockBg = Pref.getString("bg").toCharArray();
//      Pref.putString("bg","");
//      Pref.flush();
//      Pref.putInteger("check2",0);
//      Pref.flush();
      checkfirt2 = Pref.getInteger("check2");
      ///////// dress //////
      arrayLockDress = Pref.getString("dress").toCharArray();
//      Pref.putString("dress","");
//      Pref.flush();
//      Pref.putInteger("check3",0);
//      Pref.flush();
      checkfirt3= Pref.getInteger("check3");
      ///////// dress //////
      arrayLockShirt = Pref.getString("shirt").toCharArray();
//      Pref.putString("shirt","");
//      Pref.flush();
//      Pref.putInteger("check4",0);
//      Pref.flush();
      checkfirt4= Pref.getInteger("check4");
      ///////// dress //////
      arrayLockShoes = Pref.getString("shoes").toCharArray();
//      Pref.putString("shoes","");
//      Pref.flush();
//      Pref.putInteger("check5",0);
//      Pref.flush();
      checkfirt5= Pref.getInteger("check5");
      ///////// dress //////
      arrayLockTrousers = Pref.getString("quan").toCharArray();
//      Pref.putString("quan","");
//      Pref.flush();
//      Pref.putInteger("check6",0);
//      Pref.flush();
      checkfirt6= Pref.getInteger("check6");

      this.init();
      C.init();
      this.setScreen(new GameLoadding());
  }
  
  public void dispose()
  {
    GMain.platform.log("############## gmain dispose");
    GParticleSystem.saveAllFreeMin();
    super.dispose();
  }
}
