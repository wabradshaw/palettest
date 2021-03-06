package com.wabradshaw.palettest.analysis;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A set of tests for the {@link Tone} class.
 */
public class ToneTest {

    /**
     * Tests that the fields supplied to the main constructor are saved.
     */
    @Test
    public void testMainConstructor(){
        Tone tone = new Tone("red", Color.RED);
        assertEquals(Color.RED, tone.getColor());
        assertEquals("red", tone.getName());
    }

    /**
     * Tests the unnamed constructor will represent red correctly.
     */
    @Test
    public void testUnnamedConstructor_Red(){
        Tone tone = new Tone(Color.RED);
        assertEquals(Color.RED, tone.getColor());
        assertEquals("#ff0000", tone.getName());
    }

    /**
     * Tests the unnamed constructor will represent green correctly.
     */
    @Test
    public void testUnnamedConstructor_Green(){
        Tone tone = new Tone(Color.GREEN);
        assertEquals(Color.GREEN, tone.getColor());
        assertEquals("#00ff00", tone.getName());
    }

    /**
     * Tests the unnamed constructor will represent blue correctly.
     */
    @Test
    public void testUnnamedConstructor_Blue(){
        Tone tone = new Tone(Color.BLUE);
        assertEquals(Color.BLUE, tone.getColor());
        assertEquals("#0000ff", tone.getName());
    }

    /**
     * Tests the unnamed constructor will represent a complex color correctly.
     */
    @Test
    public void testUnnamedConstructor_Composite(){
        Tone tone = new Tone(new Color(222,250,206));
        assertEquals("#deface", tone.getName());
    }

    /**
     * Tests the unnamed constructor will ignore the alpha component of the Color.
     */
    @Test
    public void testUnnamedConstructor_Alpha(){
        Tone tone = new Tone(new Color(222,250,206,50));
        assertEquals("#deface", tone.getName());
    }

    /**
     * Tests the main constructor will supply a name of its own if the name is null.
     */
    @Test
    public void testMainConstructor_WithoutName(){
        Tone tone = new Tone(null, new Color(222,250,206));
        assertEquals("#deface", tone.getName());
    }

    /**
     * Tests the main constructor will supply a name of its own if the name is null.
     */
    @Test
    public void testNullColor(){
        assertThrows(IllegalArgumentException.class, () -> new Tone("Red", null));
    }

    /**
     * Tests that the getRed method will get the red value.
     */
    @Test
    public void testGetRed(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(222, tone.getRed());
    }

    /**
     * Tests that the getGreen method will get the green value.
     */
    @Test
    public void testGetGreen(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(250, tone.getGreen());
    }

    /**
     * Tests that the getBlue method will get the blue value.
     */
    @Test
    public void testGetBlue(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(206, tone.getBlue());
    }

    /**
     * Tests that the getBlue method will get the blue value.
     */
    @Test
    public void testGetAlpha(){
        Tone tone = new Tone("test", new Color(222,250,206, 50));
        assertEquals(50, tone.getAlpha());
    }

    /**
     * Tests that the getHue method will correctly calculate the hue of the Tone.
     */
    @Test
    public void testGetHue(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(98.1818, tone.getHue(), 0.0001);
    }

    /**
     * Tests that the getHue method will define black/no color as 0.
     */
    @Test
    public void testGetHue_NoColor(){
        Tone tone = new Tone("test", new Color(50,50,50));
        assertEquals(0, tone.getHue(), 0.0001);
    }

    /**
     * Tests that the getSaturationL method will correctly calculate the saturation value of the Tone in the HSL model.
     */
    @Test
    public void testGetSaturationL(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(0.8148, tone.getSaturationL(), 0.0001);
    }

    /**
     * Tests that the getSaturationV method will correctly calculate the saturation value of the Tone in the HSV model.
     */
    @Test
    public void testGetSaturationV(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(0.1760, tone.getSaturationV(), 0.0001);
    }

    /**
     * Tests that the getLightness method will correctly calculate the lightness of the Tone.
     */
    @Test
    public void testGetLightness(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(0.8941, tone.getLightness(), 0.0001);
    }

    /**
     * Tests that the getValue method will correctly calculate the brightness value of the Tone.
     */
    @Test
    public void testGetValue(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(0.9804, tone.getValue(), 0.0001);
    }

    /**
     * Tests that a tone is equal to itself.
     */
    @Test
    public void testEquals_sameItem(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertEquals(tone, tone);
    }

    /**
     * Tests that a tone is not equal to null.
     */
    @Test
    public void testEquals_nullItem(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertNotEquals(null, tone);
    }

    /**
     * Tests that a tone is not equal to a completely different object.
     */
    @Test
    public void testEquals_differentItem(){
        Tone tone = new Tone("test", new Color(222,250,206));
        assertNotEquals(new StringBuilder(), tone);
    }

    /**
     * Tests that a tone is equal to an identical copy of itself.
     */
    @Test
    public void testEquals_matchingItems(){
        Tone toneA = new Tone("test", new Color(222,250,206));
        Tone toneB = new Tone("test", new Color(222,250,206));
        assertEquals(toneA, toneB);
    }


    /**
     * Tests that tones are equal if they share the same color, but not the same name.
     */
    @Test
    public void testEquals_matchingColors(){
        Tone toneA = new Tone("expected", new Color(222,250,206));
        Tone toneB = new Tone("actual", new Color(222,250,206));
        assertEquals(toneA, toneB);
    }

    /**
     * Tests that a tone is not equal to a tone with a different color.
     */
    @Test
    public void testEquals_differentColors(){
        Tone toneA = new Tone("test", Color.RED);
        Tone toneB = new Tone("test", Color.BLUE);
        assertNotEquals(toneA, toneB);
    }

    /**
     * Tests that a tone's hashcode is equal to an identical copy of itself.
     */
    @Test
    public void testHashCode_matchingItems(){
        Tone toneA = new Tone("test", new Color(222,250,206));
        Tone toneB = new Tone("test", new Color(222,250,206));
        assertEquals(toneA.hashCode(), toneB.hashCode());
    }


    /**
     * Tests that tones's hashcodes are equal if they share the same color, but not the same name.
     */
    @Test
    public void testHashCode_matchingColors(){
        Tone toneA = new Tone("expected", new Color(222,250,206));
        Tone toneB = new Tone("actual", new Color(222,250,206));
        assertEquals(toneA.hashCode(), toneB.hashCode());
    }

    /**
     * Tests that a tone's hashcode is not equal to a tone with a different color.
     */
    @Test
    public void testHashCode_differentColors(){
        Tone toneA = new Tone("test", Color.RED);
        Tone toneB = new Tone("test", Color.BLUE);
        assertNotEquals(toneA.hashCode(), toneB.hashCode());
    }

    @Test
    public void testToString(){
        Tone red = new Tone("red", Color.RED);

        assertEquals("red (#ff0000)", red.toString());
    }
}
