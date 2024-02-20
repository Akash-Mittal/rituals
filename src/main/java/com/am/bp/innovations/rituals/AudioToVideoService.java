package com.am.bp.innovations.rituals;

import java.io.IOException;

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
public class AudioToVideoService implements RITUALS {

    public static String getCommandToConvertAudioToVideoWithStillImage(String inputaudio, String inputImage,
            String outputVideo) {
        String command = "ffmpeg -y -loop 1 -i " + inputImage + " -i " + inputaudio
                + " -c:v libx264 -tune stillimage -c:a aac -b:a 192k -pix_fmt yuv420p -shortest " + outputVideo;
        return command;
    }

    public static void main(String[] args) throws IOException {
        String inputAudio = "ritual-bg0.mp3";
        String inputImage = RITUAL_IMAGES_FINAL_FOLDER + "/" + "5-Minutes-Ritual-Type-0.jpeg";
        String outputVideoName = "ritual-bg0.mp4";

        String command = AudioToVideoService.getCommandToConvertAudioToVideoWithStillImage(inputAudio, inputImage,
                outputVideoName);
        Runtime.getRuntime().exec(command, null, null);

    }
}
