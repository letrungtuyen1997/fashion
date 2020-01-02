package com.ss.object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.effect.SoundEffect;
import com.ss.GMain;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.scenes.GamePlay;

public class ListBg {
    private TextureAtlas atlas, uiAtlas;
    private Image item;
    private Group group;
    public  Group groupItem = new Group();
    private Table container;
    private Array<Image> arrItem = new Array<>();
    private Array<Image> arrLock = new Array<>();
    private GamePlay gamePlay;
    private String[] str2;
    private BitmapFont font;



    public ListBg(TextureAtlas atlas, Group group, GamePlay gamePlay,TextureAtlas uiAtlas,BitmapFont font){
        this.font = font;
        this.group = group;
        this.gamePlay = gamePlay;
        this.uiAtlas = uiAtlas;
        GStage.addToLayer(GLayer.top,groupItem);
        this.atlas = atlas;
        if(GMain.Pref.getInteger("check2")==0){
            setArrLock();
            GMain.Pref.putInteger("check2",1);
            GMain.Pref.flush();
        }else {
            System.out.println("check:"+GMain.Pref.getString("bg"));
            String str = GMain.Pref.getString("bg").replace("[","");
            str = str.replace("]","");
            str2  =str.split(",");
        }
        loadListItem();
        addListenner();
        setLock();


    }
    private void setLock(){
        for (int i=0;i<str2.length;i++){
            String check = str2[i].trim();
            if(check.equals("1")){
                arrItem.get(i).setName("lock");
            }else {
                arrLock.get(i).setVisible(false);
            }
        }
    }
    private void setArrLock(){
        Array<Integer> arrSet = new Array<>();
        for (int j=0;j<8;j++){
            arrSet.add(j);
        }
        for(int i=0;i<8;i++){
            if(i==0||i==3|| i==4||i==5){
                arrSet.set(i,1);
            }else {
                arrSet.set(i,0);
            }
        }
        GMain.Pref.putString("bg",arrSet.toString());
        GMain.Pref.flush();
        System.out.println("check:"+GMain.Pref.getString("bg"));
        String str = GMain.Pref.getString("bg").replace("[","");
        str = str.replace("]","");
        str2  =str.split(",");
        if(str2[0].equals("1")==false){
            System.out.println("ok");
        }
    }


    private void loadListItem(){
        float paddingX = 350;
        float paddingY = 292;
        groupItem.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
        groupItem.setWidth(2500);
        groupItem.setHeight(300);
        groupItem.setOrigin(Align.center);
        for (int i=0;i<8;i++){
            item = GUI.createImage(atlas,"bg"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX-0.8f));
            item.setHeight(item.getHeight()*(Config.ratioX-0.8f));
            item.setOrigin(Align.center);
            item.setPosition(50+(paddingX*0.7f)*i,30);
            groupItem.addActor(item);
            arrItem.add(item);
            /////// img lock/////
            Image Lock = GUI.createImage(uiAtlas,"lock");
            Lock.setWidth(Lock.getWidth()*(Config.ratioX-0.4f));
            Lock.setHeight(Lock.getHeight()*(Config.ratioX-0.4f));
            Lock.setOrigin(Align.center);
            Lock.setPosition(50+(paddingX*0.7f)*i,100);
            groupItem.addActor(Lock);
            arrLock.add(Lock);
        }

        container = new Table();
        container.setSize(1000, 300);
        container.setPosition(0,GStage.getWorldHeight()-paddingY+60);
        group.addActor(container);
        Table table = new Table();
        final ScrollPane scroll1 = new ScrollPane(table);
        container.add(scroll1);
        table.add(groupItem);
        table.row();
    }
    private void addListenner(){
        for (int i =0 ;i<arrItem.size;i++){
            int finalI = i;
            arrItem.get(i).addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    SoundEffect.Play(SoundEffect.click);

                    if(arrLock.get(finalI).isVisible()!=true){
                        gamePlay.showBg(finalI);
                        SoundEffect.Stopmusic(Config.indexMusic);
                        SoundEffect.Playmusic(finalI+1);
                        Config.indexMusic = finalI+1;
                        setTouch(finalI);
                    }else {
                        showFrmUnlock(finalI);
                    }
                    return super.touchDown(event, x, y, pointer, button);

                }
            });

        }

    }
    private void setTouch(int index){
        for(int i=0;i<arrItem.size;i++){
            if(i==index){
                arrItem.get(i).setTouchable(Touchable.disabled);
            }else {
                arrItem.get(i).setTouchable(Touchable.enabled);

            }
        }
    }
    private void showFrmUnlock(int index){
        Group group = new Group();
        GStage.addToLayer(GLayer.top,group);
        final GShapeSprite blackOverlay = new GShapeSprite();
        blackOverlay.createRectangle(true, -GStage.getWorldWidth(),-GStage.getWorldHeight(), GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
        blackOverlay.setColor(0,0,0,0.5f);
        group.addActor(blackOverlay);
        Image frm = GUI.createImage(uiAtlas,"frmUnlock");
        frm.setWidth(frm.getWidth()*Config.ratioX+0.6f);
        frm.setHeight(frm.getHeight()*Config.ratioX+0.6f);
        frm.setOrigin(Align.center);
        frm.setPosition(0,0,Align.center);
        group.addActor(frm);
        //////button//////
        Image btnOk = GUI.createImage(uiAtlas,"btnOk");
        btnOk.setWidth(btnOk.getWidth()*Config.ratioX+1f);
        btnOk.setHeight(btnOk.getHeight()*Config.ratioX+1f);
        btnOk.setOrigin(Align.center);
        btnOk.setPosition(-btnOk.getWidth(),80, Align.center);
        group.addActor(btnOk);
        Image btnClose = GUI.createImage(uiAtlas,"btnHuy");
        btnClose.setWidth(btnClose.getWidth()*Config.ratioX+0.6f);
        btnClose.setHeight(btnClose.getHeight()*Config.ratioX+0.6f);
        btnClose.setOrigin(Align.center);
        btnClose.setPosition(btnOk.getWidth(),80, Align.center);
        group.addActor(btnClose);

        group.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2, Align.center);
        group.setScale(0);
        group.addAction(Actions.scaleTo(1,1,0.3f, Interpolation.bounceOut));
        ////// event button /////
        eventbtnOk(btnOk,index,group);
        eventbtnHuy(btnClose,group);

    }
    private void eventbtnOk(Image btn,int index,Group group){
        btn.setOrigin(Align.center);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundEffect.Play(SoundEffect.click);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1,1,0.1f),
                        GSimpleAction.simpleAction((d, a)->{
                            showAds(btn,group,index);
                            return  true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void eventbtnHuy(Image btn,Group group){
        btn.setOrigin(Align.center);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundEffect.Play(SoundEffect.click);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1,1,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            group.clear();
                            group.remove();
                            return  true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void Unlock(int index){
        SoundEffect.Play(SoundEffect.unlock);
        arrLock.get(index).setName("");
        arrLock.get(index).setVisible(false);
        str2[index] ="0";

        Array<Integer> arrStr = new Array<>();
        for (int i=0;i<8;i++){
            arrStr.add(i);
        }
        for(int i=0;i<8;i++){
            int tepm = Integer.parseInt(str2[i].trim());
            arrStr.set(i,tepm);
        }
        GMain.Pref.putString("bg",arrStr.toString());
        GMain.Pref.flush();
        for (int i=0;i<arrStr.size;i++){
            System.out.print(" "+arrStr.get(i));
        }

    }
    void showAds(Image btn,Group group,int index){
        if(GMain.platform.isVideoRewardReady()) {
            GMain.platform.ShowVideoReward((boolean success) -> {
                if (success) {
                    Unlock(index);
                    group.clear();
                    group.remove();
                }else {
                    btn.setTouchable(Touchable.enabled);

                }
            });
        }else {
            Label notice = new Label("Kiểm tra kết nối",new Label.LabelStyle(font, Color.RED));
            notice.setPosition(0,0,Align.center);
            group.addActor(notice);
            notice.addAction(Actions.sequence(
                    Actions.moveBy(0,-50,0.5f),
                    GSimpleAction.simpleAction((d, a)->{
                        notice.clear();
                        notice.remove();
                        btn.setTouchable(Touchable.enabled);
                        return true;
                    })
            ));

        }
    }


}
