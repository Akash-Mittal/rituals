package com.am.bp.innovations.rituals;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Text2Speach implements RITUALS {
    private String scriptsFile;
    private String outPutFile;
    private String subtitlesFile;
    private String voiceType;
    int speechRate;
    int volume;
    int pitch;

    public static String generateDefaultTextToSpeachCommand(String textTRead) {
        Text2Speach defaultText2Speach = Text2Speach.builder().scriptsFile(textTRead).outPutFile("default.mp3")
                .subtitlesFile("default.srt").voiceType("Microsoft David Desktop").speechRate(-2).volume(100).pitch(-6)
                .build();
        return generateTextToSpeachCommand(defaultText2Speach);
    }

    public static String generateDefaultTextToSpeachCommand2(String textTRead, String output, String subtittle) {
        return generateTextToSpeachCommand(textTRead, output, subtittle, -2, -6);
    }

    public static String generateTextToSpeachCommand(String textTRead, String output, String subtittle, int speechRate,
            int pitch) {
        Text2Speach defaultText2Speach = Text2Speach.builder().scriptsFile(textTRead).outPutFile(output)
                .subtitlesFile(subtittle).voiceType("Microsoft David Desktop").speechRate(speechRate).volume(100)
                .pitch(pitch).build();
        return generateTextToSpeachCommand(defaultText2Speach);
    }

    public static String generateTextToSpeachCommand(Text2Speach text2Speach) {
        String rawCommand = "BALABOLKA\\balcon -f " + text2Speach.getScriptsFile() + " -w "
                + text2Speach.getOutPutFile() + " -srt " + text2Speach.getSubtitlesFile() + " -n \""
                + text2Speach.getVoiceType() + "\"  -s " + text2Speach.getSpeechRate() + " -v "
                + text2Speach.getVolume() + " -p " + text2Speach.getPitch();

        return rawCommand;
    }

}
