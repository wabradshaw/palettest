package com.wabradshaw.palettest.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A set of tests for the {@link ImageFileUtils} class.
 */
@EnableRuleMigrationSupport
public class ImageFileUtilsTest {

    /**
     * Tests the save method when used on a byte array.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testSaveArray() throws IOException {
        BufferedImage original = ImageIO.read(this.getClass().getResourceAsStream("/sampleImages/maps/Rome.png"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(original, "png", outputStream);
        outputStream.flush();
        byte[] array = outputStream.toByteArray();

        File outputLocation = createTemporaryFileLocation("/saveArray.png");

        assertEquals(false, outputLocation.exists());
        ImageFileUtils.save(array, outputLocation.getPath());
        assertEquals(true, outputLocation.exists());
    }

    /**
     * Tests the save method for byte arrays when supplied with null.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testSaveArray_null() throws IOException {
        File outputLocation = createTemporaryFileLocation("/saveNullArray.png");

        assertThrows(RuntimeException.class, ()-> ImageFileUtils.save(null, outputLocation.getPath()));
    }

    /**
     * Tests the save method when used on a buffered image.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testSaveImage() throws IOException {
        BufferedImage original = ImageIO.read(this.getClass().getResourceAsStream("/sampleImages/maps/Rome.png"));

        File outputLocation = createTemporaryFileLocation("/saveBufferedImage.png");

        assertEquals(false, outputLocation.exists());
        ImageFileUtils.save(original, outputLocation.getPath(), "png");
        assertEquals(true, outputLocation.exists());
    }

    /**
     * Tests the save method when used on a buffered image.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testSaveImage_null() throws IOException {
        File outputLocation = createTemporaryFileLocation("/saveNullImage.png");

        assertThrows(RuntimeException.class, ()-> ImageFileUtils.save(null, outputLocation.getPath(), "png"));
    }

    /**
     * Tests that saving a BufferedImage with a different extension to the path will still work.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testSaveImage_differentExtension() throws IOException {
        BufferedImage original = ImageIO.read(this.getClass().getResourceAsStream("/sampleImages/maps/Rome.png"));

        File outputLocation = createTemporaryFileLocation("/saveAsJpg.jpg");

        assertEquals(false, outputLocation.exists());
        ImageFileUtils.save(original, outputLocation.getPath(), "png");
        assertEquals(true, outputLocation.exists());
    }

    /**
     * Tests that loadImageResource will throw an illegal argument exception if the path is null.
     */
    @Test
    void testLoadImageResource_null() {
        assertThrows(IllegalArgumentException.class, ()-> ImageFileUtils.loadImageResource(null));
    }

    /**
     * Tests that loadImageResource will throw an illegal argument exception if the file doesn't exist.
     */
    @Test
    void testLoadImageResource_nonExistent() {
        assertThrows(RuntimeException.class, ()-> ImageFileUtils.loadImageResource("this/doesnt/exist.png"));
    }

    /**
     * Tests that loadImageResource will throw an illegal argument exception if the file doesn't represent an image.
     */
    @Test
    void testLoadImageResource_nonImage() {
        assertThrows(RuntimeException.class, ()-> ImageFileUtils.loadImageResource("/misc/notAnImage.txt"));
    }

    /**
     * Tests that loadImageResource can load an image.
     */
    @Test
    void testLoadImageResource_loaded() {
        BufferedImage result = ImageFileUtils.loadImageResource("/sampleImages/dimensions/2x4.png");
        assertEquals(2, result.getWidth());
        assertEquals(4, result.getHeight());
    }

    /**
     * Tests the asImage function works.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testAsImage() throws IOException {
        BufferedImage original = ImageIO.read(this.getClass().getResourceAsStream("/sampleImages/maps/Rome.png"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(original, "png", outputStream);
        outputStream.flush();
        byte[] array = outputStream.toByteArray();

        BufferedImage result = ImageFileUtils.asImage(array);
        assertEquals(original.getData().toString(), result.getData().toString());
    }

    /**
     * Tests the asImage function works.
     *
     * @throws IOException - If the file with the test image can't be loaded.
     */
    @Test
    void testAsImage_null() throws IOException {
        assertThrows(IllegalArgumentException.class, ()-> ImageFileUtils.asImage(null));
    }

    /**
     * Creates a File object in a temporary folder. This folder will be cleared up after the test runs. Does not
     * instantiate the file on disk, only as a Java object.
     *
     * @param location The name of the file (includes extension, doesn't need a path
     * @return A pointer to the file location
     * @throws IOException If the temporary folder couldn't be created.
     */
    private File createTemporaryFileLocation(String location) throws IOException{
        TemporaryFolder tempFolder = new TemporaryFolder();
        tempFolder.create();
        return new File(tempFolder.getRoot().getPath() + location);
    }
}
