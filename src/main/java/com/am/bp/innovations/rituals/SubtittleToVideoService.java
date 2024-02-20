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
public class SubtittleToVideoService implements RITUALS {

    public static String getCommandToAddSRTToVideo(String inputVideo, String inputSRT, String outputVideo) {
        String command = "ffmpeg -y -i " + inputVideo + " -lavfi \"subtitles=" + inputSRT
                + ":force_style='Fontname=fonts/IMPACT.TTF,Alignment=6,OutlineColour=&H80000000,BackColour=&H80000000,BorderStyle=3,Outline=2,Shadow=2,Fontsize=45'\" -crf 1 -c:a copy "
                + outputVideo;
        return command;
    }

    public static void main(String[] args) throws IOException {
        String inputVideo = "ritual-bg0.mp4";
        String inputSRT = "ritual0.srt";
        String outputVideo = "ritual0.mp4";

        String command = SubtittleToVideoService.getCommandToAddSRTToVideo(inputVideo, inputSRT, outputVideo);
        Runtime.getRuntime().exec(command, null, null);

    }
}
