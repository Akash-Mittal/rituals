package com.am.bp.innovations.rituals;

import java.io.File;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Setter
@Builder
@Slf4j
public class BackGroundMusic implements RITUALS {

    public static String getCommandToAddBackgroundMusicToAudio(String inputaudio, String outputAudio) {
        return getCommandToAddBackgroundMusicToAudio(BACKGROUND_MUSIC_FOLDER_PATH, inputaudio, outputAudio);
    }

    public static String getCommandToAddBackgroundMusicToAudio(String bgMusicFolder, String inputaudio,
            String outputAudio) {
        Random random = new Random();
        File[] musicFiles = RITUALS.getFilesFromFolder(bgMusicFolder, MUSIC_FILES_EXTENSIONS);

        int index = random.nextInt(musicFiles.length);
        String command = "ffmpeg -y -i " + inputaudio + " -stream_loop -1 -i " + RITUALS.MILITARY_TIMER.MUSIC_FOLDER
                + "/" + musicFiles[index].getName()
                + " -filter_complex amix=inputs=2:duration=1:dropout_transition=0:weights=\"1 0.25\":normalize=0 "
                + outputAudio;
        return command;
    }

}
