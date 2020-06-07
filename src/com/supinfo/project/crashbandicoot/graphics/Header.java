package com.supinfo.project.crashbandicoot.graphics;

import com.supinfo.project.crashbandicoot.entities.Player;
import com.supinfo.project.crashbandicoot.game.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;

public class Header {

    // Cette classe gère le header en haut du jeu (compteur de vie, de fruit ...)

    public static Font awtFont;
    public static UnicodeFont font, font2, fontBlackOut;

    public static Texture textureFruit;
    public static Texture headPlayer;
    public static Texture turtle;

    public static boolean mysteryObject = false;

    // fonction d'initialisation des fontes du jeu
    public static void init() {
        awtFont = new Font("Arial", Font.BOLD, 20);
        font = new UnicodeFont(awtFont);
        font2 = new UnicodeFont(awtFont);
        fontBlackOut = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font2.getEffects().add(new ColorEffect(java.awt.Color.white));
        fontBlackOut.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font2.addAsciiGlyphs();
        fontBlackOut.addAsciiGlyphs();

        try {
            font.loadGlyphs();
            font2.loadGlyphs();
            fontBlackOut.loadGlyphs();
        } catch (SlickException ex) {
            System.err.println(ex.toString());
        }

        textureFruit = Texture.apple;
        headPlayer = Texture.head_player;
        turtle = Texture.turtle;
    }

    static float screenScroller;

    // fonction de rendu frame par frame du header
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

        if(mysteryObject == true) {
            turtle.bind();
                Renderer.renderEntity(screenScroller + 170, 6, 37, 37, Colors.WHITE, 0.5f, 0, 0);
            turtle.unbind();
        }
    }

}
