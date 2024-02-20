package com.am.bp.innovations.rituals;

import java.nio.file.Path;
import java.util.ArrayList;

public class MilitaryTimerApplication implements RITUALS, RITUALS.MILITARY_TIMER {

    public static void main(String[] args) throws Exception {
        Path[] imagesPath = RITUALS.getAllFilesInFolder(RESOURCE_FOLDER_PATH + COPYRIGHT_IMAGES, IMAGE_EXTENSION);
        String type = "30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY";

        for (int i = 0; i < imagesPath.length; i++) {
            ArrayList<String> commands = new ArrayList<>();

            String imageFileName = type + i + FILEEXTENSION.JPEG.getValue();
            commands.addAll(
                    TextToImageParams.generateImagesWithText("30 MINUTES MILITARY STYLE GUIDED TIMERS(EPIC MUSIC)", 55,
                            COLORS.WHITE, "FOR 10X PRODUCTIVITY", 115, COLORS.YELLOW,
                            COPYRIGHT_IMAGES + "/" + imagesPath[i].getFileName().toString(), imageFileName));

            String inputfileName = SCRIPT_30MINUTES;
            String subfileName = type + i + ".srt";
            String outputfileName = type + i + ".mp3";

            String command = Text2Speach.generateTextToSpeachCommand(inputfileName, outputfileName, subfileName, 0, -4);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

            inputfileName = type + i + ".mp3";
            outputfileName = type + "-bg" + i + ".mp3";
            command = BackGroundMusic.getCommandToAddBackgroundMusicToAudio(RESOURCE_FOLDER_PATH + MUSIC_FOLDER,
                    inputfileName, outputfileName);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
            commands.add("del " + inputfileName + " ");

            String inputAudio = outputfileName;
            String inputImage = imageFileName;
            String outputVideoName = FILEEXTENSION.removeExtension(inputAudio) + FILEEXTENSION.MP4.getValue();
            command = AudioToVideoService.getCommandToConvertAudioToVideoWithStillImage(inputAudio, inputImage,
                    outputVideoName);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

            commands.add("del " + inputAudio + " ");

            String inputVideo = outputVideoName;
            String inputSRT = subfileName;
            String outputVideo = type + i + FILEEXTENSION.MP4.getValue();
            command = SubtittleToVideoService.getCommandToAddSRTToVideo(inputVideo, inputSRT, outputVideo);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
            commands.add("del " + inputVideo + " " + inputSRT);
            RITUALS.write(RESOURCE_FOLDER_PATH + "/military-timer-video-generation-command-file-" + i + ".bat",
                    String.join("\n\r", commands));
        }

    }

}
