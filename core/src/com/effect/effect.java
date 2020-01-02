package com.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class effect extends Actor {
  private static FileHandle ef1 = Gdx.files.internal("particle/effect1");
  private static FileHandle ef2 = Gdx.files.internal("particle/effect2");
  private static FileHandle ef3 = Gdx.files.internal("particle/effect3");
  private static FileHandle ef4 = Gdx.files.internal("particle/effect4");
  private static FileHandle ef5 = Gdx.files.internal("particle/effect5");
  private static FileHandle ef6 = Gdx.files.internal("particle/effect6");
  private static FileHandle ef7 = Gdx.files.internal("particle/effect7");
  private static FileHandle ef8 = Gdx.files.internal("particle/effect8");
  private static FileHandle ef9 = Gdx.files.internal("particle/effect9");
  private static FileHandle ef10 = Gdx.files.internal("particle/effect10");
  private static FileHandle ef11 = Gdx.files.internal("particle/effect11");
  private static FileHandle efwin = Gdx.files.internal("particle/Win");



  public ParticleEffect effect;
  private Actor parent = this.parent;
  private Group stage;
  private boolean loop;

  public effect(int id, float f, float f2, boolean loop) {
    this.loop = loop;
    this.effect = new ParticleEffect();
    setX(f);
    setY(f2);
    if(id==0) {
      this.effect.load(ef1, Gdx.files.internal("particle"));
      this.effect.scaleEffect(1.0f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==1) {
      this.effect.load(ef2, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==2) {
      this.effect.load(ef3, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.7f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==3) {
      this.effect.load(ef4, Gdx.files.internal("particle"));
      this.effect.scaleEffect(1.0f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==4) {
      this.effect.load(ef5, Gdx.files.internal("particle"));
      this.effect.scaleEffect(1.0f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==5) {
      this.effect.load(ef6, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==6) {
      this.effect.load(ef7, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==7) {
      this.effect.load(ef8, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==8) {
      this.effect.load(ef9, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.4f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==9) {
      this.effect.load(ef10, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==10) {
      this.effect.load(ef11, Gdx.files.internal("particle"));
      this.effect.scaleEffect(0.3f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }else if(id==11) {
      this.effect.load(efwin, Gdx.files.internal("particle"));
      this.effect.scaleEffect(2f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }
    this.effect.setPosition(f, f2);
  }

  public void act(float f) {
    super.act(f);
    this.effect.setPosition(getX(), getY());
    this.effect.update(f);
  }

  public void draw(Batch batch, float f) {
    super.draw(batch, f);
    if (!this.effect.isComplete()) {
      this.effect.draw(batch);
      return;
    }
    if(loop==false)
      this.effect.dispose();
  }

  public void start() {
    this.effect.start();
  }
}