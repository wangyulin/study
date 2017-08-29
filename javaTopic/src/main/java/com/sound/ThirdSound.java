package com.sound;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangyulin on 7/13/16.
 */
public class ThirdSound {
    public static void main(String[] args) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        MP3File f = (MP3File) AudioFileIO.read(new File("/Users/wangyulin/Music/iTunes/iTunes Media/Music/好妹妹乐队/送你一朵山茶花/01 送你一朵山茶花.mp3"));
        MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
        System.out.println(audioHeader.getTrackLength());
    }
}
