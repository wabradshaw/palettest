package com.wabradshaw.palettest.analysis;

import java.awt.*;

/**
 * <p>
 * A {@code Tone} is a named {@link java.awt.Color}, which can be represented in multiple color models.
 * Each {@code Tone} can be described in terms of RGB (red, green, blue), HSL (hue, saturation, lightness), or
 * HSV (hue, saturation, value).
 * </p>
 * <p>
 * Alpha is also respected within each {@code Tone}, representing translucency. As such tones are compatible with RGBA,
 * HSLA, and HSVA as well.
 * </p>
 * <p>
 * Please note that two tones are identical if they represent the same underlying color, regardless of whether or not
 * they have two different names.
 * </p>
 * @see <a href="https://en.wikipedia.org/wiki/HSL_and_HSV">RGB</a>
 * @see <a href="https://en.wikipedia.org/wiki/HSL_and_HSV">HSL and HSV</a>
 */
public class Tone {
    private final String name;
    private final Color color;
    private final double hue;
//  private final double saturationL;
//  private final double saturationV;
//  private final double lightness;
//  private final double value;

    /**
     * <p>
     * Main constructor defining a particular {@link Color} and the name that should represent it.
     * </p>
     * <p>
     * If name is null, then a new name will be created based on its RGB makeup.
     * For example, pure red will become '#ff0000', green will be '#00ff00', and purple will be '#ff00ff'.
     * Please note that if name is not supplied, two {@code Tones} with the same red, green, and blue, but with
     * different alpha components will get the same name. This is done to avoid confusion about whether the code is
     * ARGB or RGBA.
     * </p>
     * @param name  The name given to the {@link Color}
     * @param color The {@link Color} being named. Cannot be null.
     */
    public Tone(String name, Color color){
        if(color == null){
            throw new IllegalArgumentException("A Tone called " + name + " was created without a Color.");
        }

        this.name = name != null ? name :  '#' + Integer.toHexString(color.getRGB()).substring(2);
        this.color = color;

        double r = color.getRed() / 255.0;
        double g = color.getGreen() / 255.0;
        double b = color.getBlue() / 255.0;

        double max = Math.max(r, Math.max(g,b));
        double min = Math.min(r, Math.min(g,b));
        double chroma = max - min;

        double rawHue;
        if(r == max){
            rawHue = (g-b)/chroma;
        } else if(g == max) {
            rawHue = 2.0 + ((b-r)/chroma);
        } else {
            rawHue = 4.0 + ((r-g)/chroma);
        }
        this.hue = (rawHue * 60) % 360;
    }

    /**
     * <p>
     * Unnamed constructor which will define a particular {@link Color} and give it a name based on its RGB makeup.
     * For example, pure red will become '#ff0000', green will be '#00ff00', and purple will be '#ff00ff'.
     * </p>
     * <p>
     * Please note that two {@code Tones} with the same red, green, and blue, but with different alpha components
     * will get the same name. This is done to avoid confusion about whether the code is ARGB or RGBA.
     * </p>
     *
     * @param color The {@link Color} being named. Cannot be null.
     */
    public Tone(Color color){
        this(null, color);
    }

    /**
     * Gets the given name for this {@link Tone}.
     *
     * @return The given name for this {@link Tone}.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the RGBA {@link Color} this {@link Tone} represents.
     *
     * @return The RGBA {@link Color} this {@link Tone} represents.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Gets the Red component of the Color, as a value from 0 to 255.
     *
     * @return The Red component of the Color, as a value from 0 to 255.
     */
    public int getRed() {
        return this.color.getRed();
    }

    /**
     * Gets the Green component of the Color, as a value from 0 to 255.
     *
     * @return The Green component of the Color, as a value from 0 to 255.
     */
    public int getGreen() {
        return this.color.getGreen();
    }

    /**
     * Gets the Blue component of the Color, as a value from 0 to 255.
     *
     * @return The Blue component of the Color, as a value from 0 to 255.
     */
    public int getBlue() {
        return this.color.getBlue();
    }

    public double getHue() {
        return hue;
    }
}