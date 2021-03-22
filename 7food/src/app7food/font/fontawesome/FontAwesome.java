/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.font.fontawesome;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author emeri
 */
public class FontAwesome{
    
    private FontAwesome(){}
    
    public static Font getFont(float size){
            try {
                InputStream is = FontAwesome.class.getResourceAsStream("fontawesome-webfont.ttf");
                Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                font = font.deriveFont(Font.PLAIN, size);
                return font;
            } catch (FontFormatException ex) {
                System.err.println("Formato de fonte inválido");
            } catch (IOException ex) {
                System.err.println("Fonte não encontrada");
            }
        return null;
    }
    
}
