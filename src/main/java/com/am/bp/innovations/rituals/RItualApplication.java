package com.am.bp.innovations.rituals;

import java.util.ArrayList;

public class RItualApplication implements RITUALS {

    public static void main(String[] args) throws Exception {
        String fileData[] = RITUALS.loadFileAndReadAsStrings("E:\\GIT_CLONE\\rituals\\ritualTypeProfessional.txt")
                .split(SYMBOLS.SEMI_COLON.getRegexEqual());
        ArrayList<String> commands = new ArrayList<>();
        for (int i = 0; i < fileData.length; i++) {
            String type = "5-Minutes-Ritual-Type-";

            String inputfileName = "ritual" + i + ".txt";
            String subfileName = "ritual" + i + ".srt";
            String outputfileName = "ritual" + i + ".mp3";
            Ritual ritual = Ritual.parse(fileData[i]);

            String imageFileName = type + i + FILEEXTENSION.JPEG.getValue();
            commands.addAll(TextToImageParams.generateImagesWithText("Uplifting Ritual for:",
                    "Spiritual Health Mental Health Physical Health", imageFileName));
            String script = Ritual.generateStringForT2SEngine(ritual);
            {
                RITUALS.write(inputfileName, script);
            }

            String command = Text2Speach.generateDefaultTextToSpeachCommand2(inputfileName, outputfileName,
                    subfileName);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

            inputfileName = "ritual" + i + ".mp3";
            outputfileName = "ritual-bg" + i + ".mp3";
            command = BackGroundMusic.getCommandToAddBackgroundMusicToAudio(inputfileName, outputfileName);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

            String inputAudio = outputfileName;
            String inputImage = RITUAL_IMAGES_FINAL_FOLDER + "/" + imageFileName;
            String outputVideoName = FILEEXTENSION.removeExtension(inputAudio) + FILEEXTENSION.MP4.getValue();
            command = AudioToVideoService.getCommandToConvertAudioToVideoWithStillImage(inputAudio, inputImage,
                    outputVideoName);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

            String inputVideo = outputVideoName;
            String inputSRT = subfileName;
            String outputVideo = "ritual" + i + FILEEXTENSION.MP4.getValue();
            command = SubtittleToVideoService.getCommandToAddSRTToVideo(inputVideo, inputSRT, outputVideo);
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
        }
        RITUALS.write("commands.bat", String.join("\n\r", commands));

    }

}
