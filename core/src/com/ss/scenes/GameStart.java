package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.effect.SoundEffect;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.Config;
import com.ss.object.Spine;

public class GameStart extends GScreen {
    public static TextureAtlas atlas,atlashair,atlasshoes, atlasshirt, atlastrousers, atlasdress, atlasaccessories1,atlasaccessories2,atlasaccessories3,atlasBg;
    public static BitmapFont fontRed,font;
    Group group = new Group();
    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        SoundEffect.Playmusic(SoundEffect.bg0);
        Config.indexMusic = SoundEffect.bg0;
        GStage.addToLayer(GLayer.ui,group);
        initAtlas();
        Image bg = GUI.createImage(atlas,"bgStart");
        bg.setWidth(bg.getWidth()* Config.ratioX);
        bg.setHeight(bg.getHeight()* Config.ratioY);
        bg.setOrigin(Align.center);
        group.addActor(bg);
        Spine s = new Spine();
        group.addActor(s);
        Image Label = GUI.createImage(atlas,"icon");
        Label.setWidth(Label.getWidth()*Config.ratioX);
        Label.setHeight(Label.getHeight()*Config.ratioX);
        Label.setOrigin(Align.center);
        Label.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-Label.getHeight()/2,Align.center);
        group.addActor(Label);
        Image btnStart = GUI.createImage(atlas,"btnStart");
        btnStart.setWidth(btnStart.getWidth()*Config.ratioX);
        btnStart.setHeight(btnStart.getHeight()*Config.ratioX);
        btnStart.setOrigin(Align.center);
        btnStart.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2+btnStart.getHeight()/2,Align.center);
        group.addActor(btnStart);
        Image btnTop = GUI.createImage(atlas,"btnTop");
        btnTop.setWidth(btnTop.getWidth()*Config.ratioX);
        btnTop.setHeight(btnTop.getHeight()*Config.ratioX);
        btnTop.setOrigin(Align.center);
        btnTop.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2+btnTop.getHeight()*3/2,Align.center);
        group.addActor(btnTop);
        ////////// event button /////////
        evenBtnStart(btnStart);
        evenBtnTop(btnTop);

    }
    private void evenBtnStart(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                SoundEffect.Play(SoundEffect.click);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f)
                ));
                Tweens.setTimeout(group,0.2f,()->{
                    setScreen(new GamePlay());
                });
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void evenBtnTop(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                SoundEffect.Play(SoundEffect.click);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f)
                ));
                Tweens.setTimeout(group,0.2f,()->{
                    btn.setTouchable(Touchable.enabled);
                    GMain.platform.ShowLeaderboard();
                });
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void run() {

    }
    private void initAtlas(){

//        atlas = GAssetsManager.getTextureAtlas("uiGame.atlas");
//        atlashair = GAssetsManager.getTextureAtlas("head.atlas");
//        atlasshoes = GAssetsManager.getTextureAtlas("shoes.atlas");
//        atlastrousers = GAssetsManager.getTextureAtlas("trousers.atlas");
//        atlasshirt = GAssetsManager.getTextureAtlas("shirt.atlas");
//        atlasdress = GAssetsManager.getTextureAtlas("dress.atlas");
//        atlasaccessories1 = GAssetsManager.getTextureAtlas("accessories1.atlas");
//        atlasaccessories2 = GAssetsManager.getTextureAtlas("accessories2.atlas");
//        atlasaccessories3 = GAssetsManager.getTextureAtlas("accessories3.atlas");
//        atlasBg = GAssetsManager.getTextureAtlas("ListBg.atlas");
//        fontRed = GAssetsManager.getBitmapFont("font_Red.fnt");
//        font = GAssetsManager.getBitmapFont("font_white.fnt");
        this.atlas              = GameLoadding.atlas;
        this.atlashair          = GameLoadding.atlashair;
        this.atlasshoes         = GameLoadding.atlasshoes;
        this.atlasshirt         = GameLoadding.atlasshirt;
        this.atlastrousers      = GameLoadding.atlastrousers;
        this.atlasdress         = GameLoadding.atlasdress;
        this.atlasaccessories1  = GameLoadding.atlasaccessories1;
        this.atlasaccessories2  = GameLoadding.atlasaccessories2;
        this.atlasaccessories3  = GameLoadding.atlasaccessories3;
        this.atlasBg            = GameLoadding.atlasBg;
        this.fontRed            = GameLoadding.fontRed;
        this.font               = GameLoadding.font;
    }



}

