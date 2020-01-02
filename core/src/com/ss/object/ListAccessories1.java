package com.ss.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.effect.SoundEffect;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.scenes.GamePlay;

public class ListAccessories1 {
    private TextureAtlas atlas;
    private Image item;
    private Group group;
    public  Group groupItem = new Group();
    private Table container;
    private Array<Image> arrItem = new Array<>();
    private GamePlay gamePlay;


    public ListAccessories1(TextureAtlas atlas, Group group, GamePlay gamePlay){
        this.group = group;
        this.gamePlay = gamePlay;
        GStage.addToLayer(GLayer.top,groupItem);
        this.atlas = atlas;
        loadListItem();
        addListenner();


    }


    private void loadListItem(){
        float paddingX = 300;
        float paddingY = 292;
        groupItem.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
        groupItem.setWidth(3700);
        groupItem.setHeight(300);
        groupItem.setOrigin(Align.center);
        for (int i=0;i<11;i++){
            item = GUI.createImage(atlas,"head"+(i+1));
            item.setWidth(item.getWidth()*(Config.ratioX+0.5f));
            item.setHeight(item.getHeight()*(Config.ratioX+0.5f));
            item.setOrigin(Align.center);
            item.setPosition((paddingX)*i,0);
            groupItem.addActor(item);
            arrItem.add(item);
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
                    gamePlay.showAccessories1(finalI);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

        }

    }
}
