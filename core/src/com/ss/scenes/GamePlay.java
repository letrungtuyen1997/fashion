package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.effect.SoundEffect;
import com.effect.effect;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.action.exAction.GTemporalAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.ListAccessories1;
import com.ss.object.ListAccessories2;
import com.ss.object.ListAccessories3;
import com.ss.object.ListBg;
import com.ss.object.ListDress;
import com.ss.object.ListHair;
import com.ss.object.Config;
import com.ss.object.ListShirt;
import com.ss.object.ListShoes;
import com.ss.object.ListTrousers;
import com.ss.object.setting;

import java.nio.ByteBuffer;



public class GamePlay extends GScreen {
    private TextureAtlas atlas, atlashair,atlasshoes, atlasshirt, atlastrousers, atlasdress, atlasaccessories1,atlasaccessories2,atlasaccessories3,atlasBg;
    private Group group = new Group();
    private Group groupBody = new Group();
    private Group groupFotter = new Group();
    private Group groupButton = new Group();
    private Group groupScreenShot = new Group();
    private Group groupOther = new Group();
    private Group groupBoy;
    private Array<Image> ArrButtonOn = new Array<>();
    private Array<Image> ArrButtonOff = new Array<>();
    private Array<Image> ArrIconOff = new Array<>();
    private Array<Image> ArrIconOn = new Array<>();
    private Array<Group> ArrGroup = new Array<>();
    private Array<Image> arrHairShow = new Array<>();
    private Array<Image> arrDressShow = new Array<>();
    private Array<Image> arrShirtShow = new Array<>();
    private Array<Image> arrTrousersShow = new Array<>();
    private Array<Image> arrShoesShow = new Array<>();
    private Array<Image> arrAccessories1Show = new Array<>();
    private Array<Image> arrAccessories2Show = new Array<>();
    private Array<Image> arrAccessories3Show = new Array<>();
    private Array<Image> arrBgShow= new Array<>();
    private Array<effect> arrEffect = new Array<>();
    private Array<Array<Group>> arrEffectAll = new Array<>();
    private Array<Integer> arrScore;
    private static int counter = 1;
    private static int idTemp = 0;
    private static int show = 0;
    private int checkLoad =0;
    private int checkMove=0;
    private BitmapFont fontRed,font;

    private Group groupHair = new Group();
    private Group groupDress = new Group();
    private Group groupShirt = new Group();
    private Group groupTrousers = new Group();
    private Group groupShoes = new Group();
    private Group groupAccessories1 = new Group();
    private Group groupAccessories2 = new Group();
    private Group groupAccessories3 = new Group();
    private Group groupBg = new Group();
    private Label diem;
    private  int countAds =0;

    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        GStage.addToLayer(GLayer.ui,group);
        GStage.addToLayer(GLayer.ui,groupBody);
        GStage.addToLayer(GLayer.top,groupOther);
        GStage.addToLayer(GLayer.top,groupFotter);
        GStage.addToLayer(GLayer.top,groupButton);
        GStage.addToLayer(GLayer.top,groupScreenShot);
        initAtlas();
        showBg();
        loadBg();
        loadButton();
        loadIcon();
        /////////// load List Hair ///////
        loadListShoes();
        loadAccessories3();
        loadListDress();
        loadListTrouser();
        loadListShirt();
        loadAccessories2();
        loadListHair();
        loadAccessories1();
        setArrGroup();
        /////// set Default button///////
        setDefault();
        evenButton();
        //////// load footer ///////
        loadFooter();
        ScreenShot();
        loadEffect();
        showbuttonSetting();

    }

    @Override
    public void run() {

    }

    void ScreenShot(){
        Image btnShow = GUI.createImage(atlas,"btnShow");
        btnShow.setWidth(btnShow.getWidth()*0.4f);
        btnShow.setHeight(btnShow.getHeight()*0.4f);
        btnShow.setOrigin(Align.center);
        btnShow.setPosition(GStage.getWorldWidth()-btnShow.getWidth()/2,btnShow.getHeight()/2,Align.center);
        groupOther.addActor(btnShow);
        btnShow.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                final GShapeSprite blackOverlay = new GShapeSprite();
                blackOverlay.createRectangle(true, -GStage.getWorldWidth(),-GStage.getWorldHeight(), GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
                blackOverlay.setColor(0,0,0,0.5f);
                groupScreenShot.addActor(blackOverlay);
                effect efWin = new effect(11,GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,false);
                groupScreenShot.addActor(efWin);
                efWin.start();
                Tweens.setTimeout(group,1f,()->{
                    blackOverlay.remove();
                });
                SoundEffect.Pausemusic(Config.indexMusic);
                SoundEffect.Playmusic(SoundEffect.bgresult);
                SoundEffect.Play(SoundEffect.click);
                groupOther.setVisible(false);
                Anishow();
                return super.touchDown(event, x, y, pointer, button);

            }
        });
    }
    void Anishow(){
        groupButton.setVisible(false);
        groupFotter.setVisible(false);
        groupHair.setVisible(false);
        groupShoes.setVisible(false);
        groupShirt.setVisible(false);
        groupDress.setVisible(false);
        groupTrousers.setVisible(false);
        groupAccessories1.setVisible(false);
        groupAccessories2.setVisible(false);
        groupAccessories3.setVisible(false);
        groupBg.setVisible(false);
        group.setOrigin(Align.right);
        group.addAction(Actions.scaleTo(1.2f,1.2f,1f,Interpolation.fastSlow));
        groupBody.addAction(Actions.moveBy(0,150,1f,Interpolation.fastSlow));
        Tweens.setTimeout(groupBg,1.5f,()->{
            AniRate();
        });

    }
    private void AniRate(){
        group.addAction(Actions.sequence(
                Actions.parallel(
                        Actions.scaleTo(2.5f,2.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(-500,-1800,1f,Interpolation.fastSlow)
                        ),
                Actions.parallel(
                        Actions.delay(0.5f),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkLoad==0){
                                loadAniboy();
                                checkLoad++;
                                SoundEffect.Play(SoundEffect.Tick);

                            }
                            int score = TickScore("shoes");
                            diem.setText(""+score);
                            arrScore.set(0,score);
                            return true;

                        })
                        ),
                Actions.delay(1f),
                Actions.parallel(
                        Actions.scaleTo(1.5f,1.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(300,1300,1f,Interpolation.fastSlow)

                ),
                Actions.parallel(
                        Actions.delay(0.5f),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkLoad==1){
                                SoundEffect.Play(SoundEffect.Tick);
                                checkLoad++;

                            }
                                groupBoy.setScale(0);
                                groupBoy.addAction(Actions.scaleTo(1,1,0.1f));
                                int score = TickScore("body");
                                diem.setText(""+score);
                                arrScore.set(1,score);
                            return true;
                        })
                ),
                Actions.delay(1.5f),
                Actions.parallel(
                        Actions.scaleTo(2.5f,2.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(-100,500,1f,Interpolation.fastSlow)

                ),
                Actions.parallel(
                        Actions.delay(0.5f),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkLoad==2){
                                SoundEffect.Play(SoundEffect.Tick);
                                checkLoad++;

                            }
                            groupBoy.setScale(0);
                            groupBoy.addAction(Actions.scaleTo(1,1,0.1f));
                            int score = TickScore("hair");
                            diem.setText(""+score);
                            arrScore.set(2,score);
                            return true;
                        })
                ),
                Actions.delay(1.5f),
                Actions.parallel(
                        Actions.scaleTo(1f,1f,1f,Interpolation.fastSlow),
                        Actions.moveBy(300,0,1f,Interpolation.fastSlow),
                        GSimpleAction.simpleAction((d,a)->{
                            groupBoy.remove();
                            checkLoad=0;
                            return true;
                        })
                ),
                Actions.delay(1),
                GSimpleAction.simpleAction((d,a)->{
                    ShowfrmResult();
                    return true;
                })
        ));
        groupBody.addAction(Actions.sequence(
                Actions.parallel(
                        Actions.scaleTo(2.5f,2.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(-500,-1800,1f,Interpolation.fastSlow),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkMove==0){
                                SoundEffect.Play(SoundEffect.SwitchIn);
                                checkMove++;
                            }
                            return true;
                        })

                ),
                Actions.delay(1.5f),
                Actions.parallel(
                        Actions.scaleTo(1.5f,1.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(400,1300,1f,Interpolation.fastSlow),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkMove==1){
                                SoundEffect.Play(SoundEffect.SwitchOut);
                                checkMove++;
                            }
                            return true;
                        })


                ),
                Actions.delay(2f),
                Actions.parallel(
                        Actions.scaleTo(2.5f,2.5f,1f,Interpolation.fastSlow),
                        Actions.moveBy(-300,500,1f,Interpolation.fastSlow),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkMove==2){
                                SoundEffect.Play(SoundEffect.SwitchIn);
                                checkMove++;
                            }
                            return true;
                        })


                ),
                Actions.delay(2f),
                Actions.parallel(
                        Actions.scaleTo(1f,1f,1f,Interpolation.fastSlow),
                        Actions.moveBy(400,0,1f,Interpolation.fastSlow),
                        GSimpleAction.simpleAction((d,a)->{
                            if(checkMove==3){
                                SoundEffect.Play(SoundEffect.SwitchOut);
                                checkMove=0;
                            }
                            return true;
                        })
                )

        ));
    }
    private void loadAniboy(){
        groupBoy = new Group();
        GStage.addToLayer(GLayer.top,groupBoy);
        Image boy = GUI.createImage(atlas,"boy");
        boy.setWidth(boy.getWidth()*(Config.ratioX+0.5f));
        boy.setHeight(boy.getHeight()*(Config.ratioX+0.5f));
        boy.setOrigin(Align.center);
        boy.setPosition(0,0,Align.center);
        groupBoy.addActor(boy);
        diem = new Label("",new Label.LabelStyle(fontRed,null));
        diem.setFontScale(2);
        diem.setOrigin(Align.center);
        diem.setPosition(100,-170,Align.center);
        diem.setAlignment(Align.center);
        groupBoy.addActor(diem);
        groupBoy.setPosition(boy.getWidth()/2,GStage.getWorldHeight()-boy.getHeight()/2,Align.center);
        groupBoy.setScale(0);
        groupBoy.setOrigin(Align.center);
        groupBoy.addAction(Actions.scaleTo(1,1,0.1f));
        arrScore = new Array<>();
        for(int i = 0;i<3;i++){
            arrScore.add(i);
        }
    }
    private int  TickScore(String Type){
        int countDress=0,countShirt=0,countTrousers=0,countHair=0,countShoes=0, counthead=0, countAccessor2=0,countAccessor3=0;
        if(Type.equals("body")){
            for(int i=0;i<arrDressShow.size;i++){
                if(arrDressShow.get(i).isVisible()==false)
                    countDress++;
                if(arrTrousersShow.get(i).isVisible()==false)
                    countTrousers++;
                if(arrShirtShow.get(i).isVisible()==false)
                    countShirt++;
            }
            for (int i=0;i<arrAccessories2Show.size;i++){
                if(arrAccessories2Show.get(i).isVisible()==false)
                    countAccessor2++;
            }
            for (int i=0;i<arrAccessories3Show.size;i++){
                if(arrAccessories3Show.get(i).isVisible()==false)
                    countAccessor3++;
            }
            if(countDress==arrDressShow.size&&countShirt==arrDressShow.size&&countTrousers==arrDressShow.size){
                return 0;
            }else{
                if(countDress!=arrDressShow.size&& countAccessor2 != arrAccessories2Show.size&& countAccessor3 != arrAccessories3Show.size)
                    return (int)(Math.random()*30)+71;
                else if(countDress!=arrDressShow.size&& countAccessor2 == arrAccessories2Show.size|| countAccessor3 == arrAccessories3Show.size)
                    return (int)(Math.random()*30)+50;
                else if(countDress==arrDressShow.size && countShirt!=arrDressShow.size&&countTrousers!=arrDressShow.size && countAccessor2 != arrAccessories2Show.size&& countAccessor3 != arrAccessories3Show.size)
                    return (int)(Math.random()*30)+71;
                else if(countDress==arrDressShow.size && countShirt!=arrDressShow.size && countTrousers!=arrDressShow.size&&countAccessor2 == arrAccessories2Show.size|| countAccessor3 == arrAccessories3Show.size)
                    return (int)(Math.random()*30)+50;
                else
                    return (int)(Math.random()*30)+20;
            }

        }
        if(Type.equals("hair")){
            for(int j=0;j<arrAccessories1Show.size;j++){
                if(arrAccessories1Show.get(j).isVisible()==false)
                    counthead++;
            }
            for(int i=0;i<arrHairShow.size;i++){
                if(arrHairShow.get(i).isVisible()==false)
                    countHair++;
            }
            if(countHair==arrHairShow.size&&counthead==arrAccessories1Show.size)
                return 0;
            else if(countHair!=arrHairShow.size&&counthead!=arrAccessories1Show.size)
                return (int)(Math.random()*30)+71;
            else if(countHair!=arrHairShow.size&&counthead==arrAccessories1Show.size)
                return (int)(Math.random()*30)+50;

        }
        if(Type.equals("shoes")){
            for(int i=0;i<arrShoesShow.size;i++){
                if(arrShoesShow.get(i).isVisible()==false)
                    countShoes++;
            }
            if(countShoes==arrShoesShow.size)
                return 0;
            else
                return (int)(Math.random()*30)+71;
        }
        return 1;
    }
    private void ShowfrmResult(){
        SoundEffect.Play(SoundEffect.Result);
        int Result =0;
        String type ="";
        Group group = new Group();
        GStage.addToLayer(GLayer.top,group);
        Image frm = GUI.createImage(atlas,"frmResult");
        frm.setHeight(frm.getHeight()*Config.ratioX);
        frm.setWidth(frm.getWidth()*Config.ratioX);
        frm.setOrigin(Align.center);
        frm.setPosition(0,0,Align.center);
        group.addActor(frm);
        for (int i=0;i<arrScore.size;i++){
            Result += arrScore.get(i);
        }
        Result = Result/3;
        GMain.platform.ReportScore(Result);
        Label score = new Label(""+Result,new Label.LabelStyle(fontRed,null));
        score.setFontScale(2);
        score.setOrigin(Align.center);
        score.setAlignment(Align.center);
        score.setPosition(0,25,Align.center);
        group.addActor(score);
        group.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2+200,Align.center);
        group.setScale(0);
        group.addAction(Actions.scaleTo(1,1,0.2f,Interpolation.swingOut));
        counterUp(score,Result);
        //////// result/////
        if(Result<50){
            type= "1";
        }else if(Result>=50&&Result<80){
            type = "2";
        }else if(Result>=80&&Result<=90){
            type = "3";
        }else if (Result>90){
            type = "4";
        }
        float dura =0;
        if(Result>0)
            dura = 1;
        else
            dura = 0;
        String finalType = type;
        Tweens.setTimeout(group,dura,()->{
            Image LableResult = GUI.createImage(atlas, finalType);
            LableResult.setWidth(LableResult.getWidth()*(Config.ratioX+0.5f));
            LableResult.setHeight(LableResult.getHeight()*(Config.ratioX+0.5f));
            LableResult.setOrigin(Align.center);
            LableResult.setPosition(0,-260,Align.center);
            group.addActor(LableResult);
            //////// button ////////
            Image btnXong = GUI.createImage(atlas,"btnXong");
            btnXong.setWidth(btnXong.getWidth()*Config.ratioX);
            btnXong.setHeight(btnXong.getHeight()*Config.ratioX);
            btnXong.setOrigin(Align.center);
            btnXong.setPosition(0,130,Align.center);
            group.addActor(btnXong);
            btnXong.setOrigin(Align.center);
            btnXong.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    SoundEffect.Play(SoundEffect.click);
                    GMain.platform.ShowFullscreen();
                    btnXong.setTouchable(Touchable.disabled);
                    btnXong.addAction(Actions.sequence(
                            Actions.scaleTo(0.8f,0.8f,0.1f),
                            Actions.scaleTo(1f,1f,0.1f)
                    ));
                    Tweens.setTimeout(group,0.2f,()->{
                        SoundEffect.Stopmusic(SoundEffect.bgresult);
                        SoundEffect.Playmusic(Config.indexMusic);
                        group.clear();
                        group.remove();
                        groupButton.setVisible(true);
                        groupFotter.setVisible(true);
                        groupHair.setVisible(true);
                        groupShoes.setVisible(true);
                        groupShirt.setVisible(true);
                        groupDress.setVisible(true);
                        groupTrousers.setVisible(true);
                        groupAccessories1.setVisible(true);
                        groupAccessories2.setVisible(true);
                        groupAccessories3.setVisible(true);
                        groupBg.setVisible(true);
                        group.addAction(Actions.scaleTo(1f,1f,1f,Interpolation.fastSlow));
                        groupBody.addAction(Actions.moveBy(0,-150,1f,Interpolation.fastSlow));
                        setDefault();
                        groupOther.setVisible(true);

                    });
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

        });


    }
    void counterUp(Label object, long target){
        if(target>0){
            SoundEffect.Play(SoundEffect.Result2);
        }
        group.addAction(
                GTemporalAction.add(1, (percent, actor) -> {
                    object.setText(""+ Math.round(target*percent));
                }));

    }

    void showBg(){
        Image bg = GUI.createImage(atlas,"bg");
        bg.setWidth(GStage.getWorldWidth());
        bg.setHeight(GStage.getWorldHeight());
        group.addActor(bg);
        Image body = GUI.createImage(atlas,"body");
        body.setWidth(body.getWidth()*Config.ratioX);
        body.setHeight(body.getHeight()*Config.ratioX);
        body.setOrigin(Align.center);
        body.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-80,Align.center);
        groupBody.addActor(body);
    }
    private void loadButton(){
        /////////// button on ///////////
        Image buttonOn;
        for (int i=0;i<9;i++){
            buttonOn = GUI.createImage(atlas,"buttonOn");
            buttonOn.setWidth(buttonOn.getWidth()*Config.ratioX);
            buttonOn.setHeight(buttonOn.getHeight()*Config.ratioX);
            buttonOn.setOrigin(Align.center);
            if(i>4){
                buttonOn.setScale(-1,1);
                buttonOn.setPosition(GStage.getWorldWidth()-buttonOn.getWidth()/2,(GStage.getWorldHeight()/2-300)+((i-4)*buttonOn.getHeight()),Align.center);

            }else {
                buttonOn.setPosition(buttonOn.getWidth()/2,(GStage.getWorldHeight()/2-300)+(i*buttonOn.getHeight()),Align.center);
            }
            groupButton.addActor(buttonOn);
            ArrButtonOn.add(buttonOn);
        }

       /////////// button off///////////
        Image buttonOff;
        for (int i=0;i<9;i++){
            buttonOff = GUI.createImage(atlas,"buttonOff");
            buttonOff.setWidth(buttonOff.getWidth()*Config.ratioX);
            buttonOff.setHeight(buttonOff.getHeight()*Config.ratioX);
            buttonOff.setOrigin(Align.center);
            if(i>4){
                buttonOff.setScale(-1,1);
                buttonOff.setPosition(GStage.getWorldWidth()-buttonOff.getWidth()/2,(GStage.getWorldHeight()/2-300)+((i-4)*buttonOff.getHeight()),Align.center);

            }else {
                buttonOff.setPosition(buttonOff.getWidth() / 2, (GStage.getWorldHeight() / 2 - 300) + (i * buttonOff.getHeight()), Align.center);
            }
            groupButton.addActor(buttonOff);
            ArrButtonOff.add(buttonOff);
        }



    }
    private void setDefault(){
        for (int i=0;i<ArrButtonOn.size;i++){
            ArrButtonOn.get(i).setVisible(false);
            ArrIconOn.get(i).setVisible(false);
            ArrGroup.get(i).setVisible(false);
            ArrButtonOff.get(i).setVisible(true);
            ArrIconOff.get(i).setVisible(true);
        }
        ArrButtonOn.get(0).setVisible(true);
        ArrButtonOff.get(0).setVisible(false);
        ArrIconOn.get(0).setVisible(true);
        ArrIconOff.get(0).setVisible(false);
        ArrGroup.get(0).setVisible(true);
    }
    private void  evenButton(){
        for (int i =0; i<ArrButtonOn.size;i++){
            int finalI = i;

            ArrButtonOff.get(i).addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    SoundEffect.Play(SoundEffect.toggle);
                    countAds++;
                    if(countAds==4){
                        countAds=0;
                        GMain.platform.ShowFullscreen();
                    }
                    for(int i = 0; i<ArrButtonOn.size;i++){
                        if(i==finalI){
                            ArrButtonOn.get(i).setVisible(true);
                            ArrButtonOff.get(i).setVisible(false);
                            ArrIconOn.get(i).setVisible(true);
                            ArrIconOff.get(i).setVisible(false);
                            ArrGroup.get(i).setVisible(true);
                        }else {
                            ArrButtonOn.get(i).setVisible(false);
                            ArrButtonOff.get(i).setVisible(true);
                            ArrIconOn.get(i).setVisible(false);
                            ArrIconOff.get(i).setVisible(true);
                            ArrGroup.get(i).setVisible(false);
                        }
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            ArrIconOff.get(i).addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    SoundEffect.Play(SoundEffect.toggle);
                    countAds++;
                    if(countAds==4){
                        countAds=0;
                        GMain.platform.ShowFullscreen();
                    }
                    for(int i = 0; i<ArrButtonOn.size;i++){
                        if(i==finalI){
                            ArrButtonOn.get(i).setVisible(true);
                            ArrButtonOff.get(i).setVisible(false);
                            ArrIconOn.get(i).setVisible(true);
                            ArrIconOff.get(i).setVisible(false);
                            ArrGroup.get(i).setVisible(true);

                        }else {
                            ArrButtonOn.get(i).setVisible(false);
                            ArrButtonOff.get(i).setVisible(true);
                            ArrIconOn.get(i).setVisible(false);
                            ArrIconOff.get(i).setVisible(true);
                            ArrGroup.get(i).setVisible(false);

                        }
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }
    private void loadIcon(){
        Image iconOn;
        for (int i=0;i<9;i++){
            iconOn = GUI.createImage(atlas,"iicon"+(i+1));
            iconOn.setWidth(iconOn.getWidth()*Config.ratioX);
            iconOn.setHeight(iconOn.getHeight()*Config.ratioX);
            iconOn.setOrigin(Align.center);
            if(i>4){
                iconOn.setPosition(ArrButtonOn.get(i).getX()+60,ArrButtonOn.get(i).getY()+20);
            }else {
                iconOn.setPosition(ArrButtonOn.get(i).getX()+20,ArrButtonOn.get(i).getY()+20);
            }
            groupButton.addActor(iconOn);
            ArrIconOn.add(iconOn);
        }

        /////////// button off///////////
        Image iconOff;
        for (int i=0;i<9;i++){
            iconOff = GUI.createImage(atlas,"icon"+(i+1));
            iconOff.setWidth(iconOff.getWidth()*Config.ratioX);
            iconOff.setHeight(iconOff.getHeight()*Config.ratioX);
            iconOff.setOrigin(Align.center);
            if(i>4){
                iconOff.setPosition(ArrButtonOn.get(i).getX()+100,ArrButtonOn.get(i).getY()+40);
            }else {
                iconOff.setPosition(ArrButtonOn.get(i).getX()+10,ArrButtonOn.get(i).getY()+40);
            }

            groupButton.addActor(iconOff);
            ArrIconOff.add(iconOff);
        }
    }
    private void loadFooter(){
        Image footer = GUI.createImage(atlas,"footer");
        footer.setWidth(footer.getWidth()*Config.ratioX);
        footer.setHeight(footer.getHeight()*Config.ratioX);
        footer.setOrigin(Align.center);
        footer.setPosition(footer.getWidth()/2,GStage.getWorldHeight()-footer.getHeight()/2,Align.center);
        groupFotter.addActor(footer);
    }
    private void loadListHair(){
        GStage.addToLayer(GLayer.top,groupHair);
        new ListHair(atlashair,groupHair,this,atlas,font);
            showListHair();
    }
    private void loadListDress(){
        GStage.addToLayer(GLayer.top,groupDress);
        new ListDress(atlasdress,groupDress,this,atlas,font);
        showListDress();
    }
    private void loadListShirt(){
        GStage.addToLayer(GLayer.top,groupShirt);
        new ListShirt(atlasshirt,groupShirt,this,atlas,font);
        showListShirt();
    }
    private void loadListTrouser(){
        GStage.addToLayer(GLayer.top,groupTrousers);
        new ListTrousers(atlastrousers,groupTrousers,this,atlas,font);
        showListTrouser();
    }
    private void loadListShoes(){
        GStage.addToLayer(GLayer.top,groupShoes);
        new ListShoes(atlasshoes,groupShoes,this,atlas,font);
        showListShoes();
    }
    private void loadAccessories1(){
        GStage.addToLayer(GLayer.top,groupAccessories1);
        new ListAccessories1(atlasaccessories1,groupAccessories1,this);
        showListAccessories1();
    }
    private void loadAccessories2(){
        GStage.addToLayer(GLayer.top,groupAccessories2);
        new ListAccessories2(atlasaccessories2,groupAccessories2,this);
        showListAccessories2();
    }
    private void loadAccessories3(){
        GStage.addToLayer(GLayer.top,groupAccessories3);
        new ListAccessories3(atlasaccessories3,groupAccessories3,this);
        showListAccessories3();
    }
    private void loadBg(){
        GStage.addToLayer(GLayer.top,groupBg);
        new ListBg(atlasBg,groupBg,this,atlas,font);
        showListBg();
    }
    private void setArrGroup(){
        ArrGroup.add(groupHair);
        ArrGroup.add(groupDress);
        ArrGroup.add(groupShirt);
        ArrGroup.add(groupTrousers);
        ArrGroup.add(groupShoes);
        ArrGroup.add(groupAccessories1);
        ArrGroup.add(groupAccessories2);
        ArrGroup.add(groupAccessories3);
        ArrGroup.add(groupBg);
    }
    /////// show list hair/////
    private void showListHair() {
        Image item;
        for (int i=0; i<11;i++){
            item = GUI.createImage(atlashair,"toc"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-115,Align.center);
            groupBody.addActor(item);
            arrHairShow.add(item);
            item.setVisible(false);
        }
    }
    public void showHair(int index){
        for (int i=0;i<arrHairShow.size;i++){
            if(i!=index){
                arrHairShow.get(i).setVisible(false);

            }else {
                arrHairShow.get(i).setVisible(true);

            }
        }
    }
    /////////// show list Dress//////
    private void showListDress() {
        Image item;
        for (int i=0; i<10;i++){
            item = GUI.createImage(atlasdress,"vay"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+5,GStage.getWorldHeight()/2-115,Align.center);
            groupBody.addActor(item);
            arrDressShow.add(item);
            item.setVisible(false);
        }
    }
    public void showDress(int index){
        for (int i=0;i<arrDressShow.size;i++){
            if(i!=index){
                arrDressShow.get(i).setVisible(false);
                arrShirtShow.get(i).setVisible(false);
                arrTrousersShow.get(i).setVisible(false);
//                idTemp = index;

            }else {
                arrDressShow.get(i).setVisible(true);
                arrShirtShow.get(i).setVisible(false);
                arrTrousersShow.get(i).setVisible(false);
            }
        }


    }
    /////////// show list shirt//////
    private void showListShirt() {
        Image item;
        for (int i=0; i<10;i++){
            item = GUI.createImage(atlasshirt,"ao"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+3,GStage.getWorldHeight()/2-115,Align.center);
            groupBody.addActor(item);
            arrShirtShow.add(item);
            item.setVisible(false);
        }
    }
    public void showShirt(int index){
        for (int i=0;i<arrShirtShow.size;i++){
            if(i!=index){
                arrShirtShow.get(i).setVisible(false);
                arrDressShow.get(i).setVisible(false);

            }else {
                arrShirtShow.get(i).setVisible(true);
                arrDressShow.get(i).setVisible(false);


            }
        }
    }
    /////////// show list Trouser//////
    private void showListTrouser() {
        Image item;
        for (int i=0; i<10;i++){
            item = GUI.createImage(atlastrousers,"quan"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+2,GStage.getWorldHeight()/2-115,Align.center);
            groupBody.addActor(item);
            arrTrousersShow.add(item);
            item.setVisible(false);
        }
    }
    public void showTrouser(int index){
        for (int i=0;i<arrTrousersShow.size;i++){
            if(i!=index){
                arrTrousersShow.get(i).setVisible(false);
                arrDressShow.get(i).setVisible(false);

            }else {
                arrTrousersShow.get(i).setVisible(true);
                arrDressShow.get(i).setVisible(false);


            }
        }
    }
    /////////// show list Shoes//////
    private void showListShoes() {
        Image item;
        for (int i=0; i<10;i++){
            item = GUI.createImage(atlasshoes,"giay"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+2,GStage.getWorldHeight()/2-110,Align.center);
            groupBody.addActor(item);
            arrShoesShow.add(item);
            item.setVisible(false);
        }
    }
    public void showShoes(int index){
        for (int i=0;i<arrShoesShow.size;i++){
            if(i!=index){
                arrShoesShow.get(i).setVisible(false);
            }else {
                arrShoesShow.get(i).setVisible(true);

            }
        }
    }
    /////////// show list Accessories head//////
    private void showListAccessories1() {

        Image item;
        for (int i=0; i<11;i++){
            item = GUI.createImage(atlasaccessories1,"dau"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+5,GStage.getWorldHeight()/2-110,Align.center);
            groupBody.addActor(item);
            arrAccessories1Show.add(item);
            item.setVisible(false);
        }
    }
    public void showAccessories1(int index){
        for (int i=0;i<arrAccessories1Show.size;i++){
            if(i!=index){
                arrAccessories1Show.get(i).setVisible(false);
            }else {
                arrAccessories1Show.get(i).setVisible(true);

            }
        }
    }
    /////////// show list Accessories neck//////
    private void showListAccessories2() {

        Image item;
        for (int i=0; i<9;i++){
            item = GUI.createImage(atlasaccessories2,"co"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+5,GStage.getWorldHeight()/2-110,Align.center);
            groupBody.addActor(item);
            arrAccessories2Show.add(item);
            item.setVisible(false);
        }
    }
    public void showAccessories2(int index){
        for (int i=0;i<arrAccessories2Show.size;i++){
            if(i!=index){
                arrAccessories2Show.get(i).setVisible(false);
            }else {
                arrAccessories2Show.get(i).setVisible(true);

            }
        }
    }
    /////////// show list Accessories Hand//////
    private void showListAccessories3() {

        Image item;
        for (int i=0; i<10;i++){
            item = GUI.createImage(atlasaccessories3,"tay"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioX));
            item.setOrigin(Align.center);
            item.setPosition(GStage.getWorldWidth()/2+5,GStage.getWorldHeight()/2-110,Align.center);
            groupBody.addActor(item);
            arrAccessories3Show.add(item);
            item.setVisible(false);
        }
    }
    public void showAccessories3(int index){
        for (int i=0;i<arrAccessories3Show.size;i++){
            if(i!=index){
                arrAccessories3Show.get(i).setVisible(false);
            }else {
                arrAccessories3Show.get(i).setVisible(true);

            }
        }
    }
    //////// show list bg //////
    private void showListBg() {

        Image item;
        for (int i=0; i<8;i++){
            item = GUI.createImage(atlasBg,"bg"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX));
            item.setHeight(item.getHeight()*(Config.ratioY));
            item.setOrigin(Align.center);
            item.setPosition(0,0);
            group.addActor(item);
            arrBgShow.add(item);
            item.setVisible(false);
        }
    }
    public void showBg(int index){
        for (int i=0;i<arrBgShow.size;i++){
            if(i!=index){
                arrBgShow.get(i).setVisible(false);
            }else {
                arrBgShow.get(i).setVisible(true);

            }
        }
        idTemp=index;
        if(show==0){
            show++;
            if(index!=0&&index!=1){
                showEffect(0,1);
            }else {
                showEffect(0,idTemp);
            }
        }
    }
    private void loadEffect(){

        Array<Group> arr1 = new Array<>();
        Array<Group> arr2 = new Array<>();
        Array<Group> arr3 = new Array<>();
        Array<Group> arr4 = new Array<>();
        Array<Group> arr5 = new Array<>();
        Array<Group> arr6 = new Array<>();
        Array<Group> arr7 = new Array<>();
        Array<Group> arr8 = new Array<>();
        Array<Group> arr9 = new Array<>();
        Array<Group> arr10 = new Array<>();
        Array<Group> arr11 = new Array<>();
        arrEffect.clear();
        for (int i=0;i<10;i++){
            Group groupEff1 = new Group();
            Group groupEff2 = new Group();
            Group groupEff3 = new Group();
            Group groupEff4 = new Group();
            Group groupEff5 = new Group();
            Group groupEff6 = new Group();
            Group groupEff7 = new Group();
            Group groupEff8 = new Group();
            Group groupEff9 = new Group();
            Group groupEff10 = new Group();
            Group groupEff11 = new Group();

            effect ef;
            ef= new effect(5,0,0,true);
            arrEffect.add(ef);
            groupEff1.addActor(ef);
            arr1.add(groupEff1);
            ///////
            ef= new effect(0,0,0,true);
            arrEffect.add(ef);
            groupEff2.addActor(ef);
            arr2.add(groupEff2);
            ///////
            ef= new effect(8,0,0,true);
            arrEffect.add(ef);
            groupEff3.addActor(ef);
            arr3.add(groupEff3);
            ///////
            ef= new effect(3,0,0,true);
            arrEffect.add(ef);
            groupEff4.addActor(ef);
            arr4.add(groupEff4);
            ///////
            ef= new effect(9,0,0,true);
            arrEffect.add(ef);
            groupEff5.addActor(ef);
            arr5.add(groupEff5);
            ///////
            ef= new effect(6,0,0,true);
            arrEffect.add(ef);
            groupEff6.addActor(ef);
            arr6.add(groupEff6);
            ///////
            ef= new effect(4,0,0,true);
            arrEffect.add(ef);
            groupEff7.addActor(ef);
            arr7.add(groupEff7);
            ///////
            ef= new effect(1,0,0,true);
            arrEffect.add(ef);
            groupEff8.addActor(ef);
            arr8.add(groupEff8);
            ///////
            ef= new effect(10,0,0,true);
            arrEffect.add(ef);
            groupEff9.addActor(ef);
            arr9.add(groupEff9);
            ///////
            ef= new effect(8,0,0,true);
            arrEffect.add(ef);
            groupEff10.addActor(ef);
            arr10.add(groupEff10);
            ///////
            ef= new effect(9,0,0,true);
            arrEffect.add(ef);
            groupEff11.addActor(ef);
            arr11.add(groupEff11);

        }
        arrEffectAll.add(arr1);
        arrEffectAll.add(arr2);
        arrEffectAll.add(arr3);
        arrEffectAll.add(arr4);
        arrEffectAll.add(arr5);
        arrEffectAll.add(arr6);
        arrEffectAll.add(arr7);
        arrEffectAll.add(arr8);
        arrEffectAll.add(arr9);
        arrEffectAll.add(arr10);
        arrEffectAll.add(arr11);
    }
    private void showEffect(int index, int id){
            if(id!=idTemp){
                id =idTemp;
            }
            for(int i=0;i<arrEffectAll.size;i++){
                for(int j=0;j<arrEffectAll.get(id).size;j++){
                    if(idTemp==i){
                        arrEffectAll.get(i).get(j).setVisible(true);
                    }else {
                        arrEffectAll.get(i).get(j).setVisible(false);
                    }
                }
            }

        if(index>=arrEffectAll.get(id).size){
            index=0;
        }
        effect ef=  (effect)arrEffectAll.get(id).get(index).getChildren().get(0);
        float x = (float) (Math.random()*GStage.getWorldWidth());
        float y = (float) (Math.random()*GStage.getWorldHeight());
        ef.setPosition(x,y);
        GStage.addToLayer(GLayer.ui,arrEffectAll.get(id).get(index));
        ef.start();
        index++;
        int finalIndex = index;
        int finalId = id;
        Tweens.setTimeout(group,0.05f,()->{
            showEffect(finalIndex, finalId);
        });
    }
    private void showbuttonSetting(){
        Image btnSetting = GUI.createImage(atlas,"btnSetting");
        btnSetting.setWidth(btnSetting.getWidth()*Config.ratioX);
        btnSetting.setHeight(btnSetting.getHeight()*Config.ratioX);
        btnSetting.setOrigin(Align.center);
        btnSetting.setPosition(btnSetting.getWidth()/2,btnSetting.getHeight()/2,Align.center);
        groupOther.addActor(btnSetting);
        btnSetting.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundEffect.Play(SoundEffect.click);
                btnSetting.setTouchable(Touchable.disabled);
                btnSetting.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f)
                ));
                Tweens.setTimeout(group,0.2f,()->{
                    new setting(atlas,btnSetting,GamePlay.this);
                });
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }

    private void initAtlas(){
        this.atlas = GameStart.atlas;
        this.atlashair = GameStart.atlashair;
        this.atlasshoes = GameStart.atlasshoes;
        this.atlasshirt = GameStart.atlasshirt;
        this.atlastrousers = GameStart.atlastrousers;
        this.atlasdress = GameStart.atlasdress;
        this.atlasaccessories1 = GameStart.atlasaccessories1;
        this.atlasaccessories2 = GameStart.atlasaccessories2;
        this.atlasaccessories3 = GameStart.atlasaccessories3;
        this.atlasBg = GameStart.atlasBg;
        this.fontRed = GameStart.fontRed;
        this.font = GameStart.font;
    }
}
