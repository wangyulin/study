package com.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioFileFormat.Type;

import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;

/**
 * Created by wangyulin on 7/13/16.
 */
public class SoundTest {

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        try {
            File wavFile = new File("/Users/wangyulin/tmp/crchbell.wav");
            File dstFile = new File("/Users/wangyulin/tmp/crchbell_1.wav");
            WaveFileReader reader = new WaveFileReader();
            AudioInputStream audioIn = reader.getAudioInputStream(wavFile);
            AudioFormat srcFormat = audioIn.getFormat();

            AudioFormat dstFormat = new AudioFormat(srcFormat.getEncoding(),
                    srcFormat.getSampleRate() / 2,
                    srcFormat.getSampleSizeInBits(),
                    srcFormat.getChannels(),
                    srcFormat.getFrameSize(),
                    srcFormat.getFrameRate() / 2,
                    srcFormat.isBigEndian());

            AudioInputStream convertedIn = AudioSystem.getAudioInputStream(dstFormat, audioIn);

            WaveFileWriter writer = new WaveFileWriter();
            writer.write(convertedIn, Type.WAVE, dstFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
