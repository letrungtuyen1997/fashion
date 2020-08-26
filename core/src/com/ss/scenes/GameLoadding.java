package com.ss.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;

public class GameLoadding extends GScreen {
  public static TextureAtlas atlas, atlashair,atlasshoes, atlasshirt, atlastrousers, atlasdress, atlasaccessories1,atlasaccessories2,atlasaccessories3,atlasBg;
  public static BitmapFont fontRed,font;
  private Group group = new Group();

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    GStage.addToLayer(GLayer.ui,group);
    loading();
    initAtlas();


  }
  float waitTime = 7f;
  @Override
  public void run() {
//    System.out.println("run");
    waitTime-= Gdx.graphics.getDeltaTime();
    if(waitTime<=0) {
      if (!GAssetsManager.isFinished()) {
        GAssetsManager.update();
      } else {
        this.setScreen(new GameStart());
//        System.out.println("chuyen");
      }
    }

  }
  private void initAtlas(){

    atlas = GAssetsManager.getTextureAtlas("uiGame.atlas");
    atlashair = GAssetsManager.getTextureAtlas("head.atlas");
    atlasshoes = GAssetsManager.getTextureAtlas("shoes.atlas");
    atlastrousers = GAssetsManager.getTextureAtlas("trousers.atlas");
    atlasshirt = GAssetsManager.getTextureAtlas("shirt.atlas");
    atlasdress = GAssetsManager.getTextureAtlas("dress.atlas");
    atlasaccessories1 = GAssetsManager.getTextureAtlas("accessories1.atlas");
    atlasaccessories2 = GAssetsManager.getTextureAtlas("accessories2.atlas");
    atlasaccessories3 = GAssetsManager.getTextureAtlas("accessories3.atlas");
    atlasBg = GAssetsManager.getTextureAtlas("ListBg.atlas");
    fontRed = GAssetsManager.getBitmapFont("font_Red.fnt");
    font = GAssetsManager.getBitmapFont("font_white.fnt");
  }
  void loading(){
//        TextureAtlas loadding = GAssetsManager.getTextureAtlas("loadding.atlas");
//        Image load = GUI.createImage(loadding,"loadding");
    Image bg = new Image(new Texture("textureAtlas/bgLoad.png"));
    bg.setSize(GStage.getWorldWidth(),GStage.getWorldHeight());
    bg.setScale(1,-1);
    bg.setOrigin(Align.center);
    group.addActor(bg);
    Image load = new Image(new Texture("textureAtlas/loadding.png"));
    load.setOrigin(Align.center);
    load.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()*0.7f, Align.center);
    group.addActor(load);
    aniload(load);
  }
  void aniload(Image img){
    img.addAction(Actions.sequence(
            Actions.rotateBy(360,1f),
            GSimpleAction.simpleAction((d, a)->{
              aniload(img);
              return true;
            })
    ));
  }

}
