package com.supinfo.project.crashbandicoot.graphics;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;

public class Header {

    public static UnicodeFont font, font2;

    public static Texture textureFruit;
    public static Texture headPlayer;

    public static void init() {
        Font awtFont = new Font("Arial", Font.BOLD, 20);
        font = new UnicodeFont(awtFont);
        font2 = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font2.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font2.addAsciiGlyphs();

        try {
            font.loadGlyphs();
            font2.loadGlyphs();
        } catch (SlickException ex) {
            System.err.println(ex.toString());
        }


        textureFruit = Texture.apple;
        headPlayer = Texture.head_player;
    }

    static float screenScroller;

    public static void render() {
        screenScroller = Math.abs(Game.xScroll);

        font.drawString(screenScroller + 33, 4, "" + Player.numberFruits);
        font2.drawString(screenScroller + 113, 4, "" + Player.playerLife);

        textureFruit.bind();
            Renderer.renderEntity(screenScroller + 10, 5, 37, 37, Colors.WHITE, 0.5f, 0, 0);
        textureFruit.unbind();

        headPlayer.bind();
            Renderer.renderEntity(screenScroller + 90, 6, 37, 37, Colors.WHITE, 0.5f, 0, 0);
        headPlayer.unbind();
    }

}
