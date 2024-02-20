package com.am.bp.innovations.rituals;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

public class BigTimerApplication implements RITUALS {

    public static void main(String[] args) throws IOException {
        Random random = new Random();

        int numberOfPlayLists = 20;
        int counter = 1;
        String heading = BIG_TIMER.TYPE;
        String subHeading = "FOR 10X PRODUCTIVITY";
        String maxHOUR = "13";

        while (counter < numberOfPlayLists) {
            ArrayList<String> commands = new ArrayList<>();
            String folderName = BIG_TIMER.RESOURCE_FOLDER_PATH + "/" + (heading + " " + subHeading).replace(" ", "-")
                    + "-TYPE-" + counter;
            RITUALS.createDefaultFolder(folderName);
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            String hex = String.format("#%02x%02x%02x", r, g, b);
            String thumbnailFileName = (maxHOUR + " HOUR " + heading + " " + subHeading).replace(" ", "-") + ".jpeg";
            commands.addAll(createThumbnail(hex, maxHOUR + " HOUR " + heading, subHeading, thumbnailFileName));
            String videoFileName = FILEEXTENSION.removeExtension(thumbnailFileName).concat("-raw")
                    .concat(FILEEXTENSION.MP4.getValue());
            commands.addAll(createVideo(hex, maxHOUR, videoFileName));
            String videoFileWithAudioName = FILEEXTENSION.removeExtension(thumbnailFileName)
                    .concat(FILEEXTENSION.MP4.getValue());
            commands.addAll(
                    addAudioToVideo(folderName, videoFileName, videoFileWithAudioName, Integer.parseInt(maxHOUR)));
            commands.addAll(sliceVideos(maxHOUR, videoFileWithAudioName, hex));

            RITUALS.write(folderName + "/big-timer-video-generation-command-file-" + counter + ".bat",
                    String.join("\n\r", commands));
            counter++;
        }
    }

    private static ArrayList<String> createThumbnail(String color, String heading, String subHeading,
            String outPutFileName) {
        ArrayList<String> commands = new ArrayList<>();

        String tempFileName = FILEEXTENSION.removeExtension(outPutFileName) + "-temp" + FILEEXTENSION.JPEG.getValue();
        String command = "ffmpeg -y -f lavfi -i color=c=" + color
                + ":s=1280x720 -vf \"drawtext=fontfile=../fonts/armalite.ttf:fontsize=45:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='"
                + heading + "':x=(w-text_w)/2:y=(h-text_h)/2 - 45\" " + tempFileName;
        commands.add(command);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());

        command = "ffmpeg -y -i " + tempFileName
                + " -vf \"drawtext=fontfile=../fonts/armalite.ttf:fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                + subHeading + "':x=(w-text_w)/2:y=(h-text_h)/2 + 40\" " + outPutFileName;
        commands.add(command);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add("del " + tempFileName);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());

        return commands;
    }

    private static ArrayList<String> createVideo(String colors, String maxHOUR, String outFileName) {
        ArrayList<String> commands = new ArrayList<>();
        String command = "ffmpeg -y -f lavfi -i color=c=" + colors + ":s=1280x720 -ss 00:00:00 -t " + maxHOUR
                + ":00:00 -vf \"fps=1 ,drawbox=y=235:color=black@0.4:width=iw:height=250 :t=fill,drawtext=fontfile=../fonts/armalite.ttf:text='%%{eif\\:trunc((t)/86400)\\:d\\:2}.%%{eif\\:trunc(mod(t/3600,24))\\:d\\:2}.%%{eif\\:trunc(mod(t/60,60))\\:d\\:2}.%%{eif\\:trunc(mod(t,60))\\:d\\:2}':fontcolor=white:fontsize=200:x=(w-tw)/2:y=(h-th)/2:box=1:boxcolor=black@0.4:boxborderw=500,format=yuv420p\" "
                + outFileName;
        commands.add(command);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());

        return commands;

    }

    private static ArrayList<String> addAudioToVideo(String folderName, String inputFileName, String outPutFileName,
            int maxHours) throws IOException {
        ArrayList<String> commands = new ArrayList<>();
        StringBuilder silence = new StringBuilder();
        for (int i = 0; i < maxHours; i++) {
            silence.append("file '../static-files/ak47withsilence.mp3' \n\r");
        }
        RITUALS.write(folderName + "/files.txt", silence.toString(), StandardOpenOption.TRUNCATE_EXISTING);

        String command = "ffmpeg -f concat -safe 0 -i files.txt -c copy ak47withsilencefull.mp3";
        commands.add(command);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add("del files.txt");
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());

        command = "ffmpeg -y -i " + inputFileName
                + " -itsoffset 00:00:00 -i ak47withsilencefull.mp3 -map 0:0 -map 1:0 -c:v copy -preset ultrafast -async 1 "
                + outPutFileName;
        commands.add(command);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add("del " + inputFileName + " ak47withsilencefull.mp3");
        return commands;

    }

    private static ArrayList<String> sliceVideos(String maxHOUR, String inputFileName, String color) {
        int HOUR = Integer.parseInt(maxHOUR);
        ArrayList<String> commands = new ArrayList<>();
        String heading = BIG_TIMER.TYPE;
        String subHeading = "FOR 10X PRODUCTIVITY";

        for (int i = 1; i < HOUR; i++) {
            String outFileName = (i + " HOUR " + heading + " " + subHeading).replace(" ", "-")
                    + FILEEXTENSION.MP4.getValue();

            String command = "ffmpeg -y -ss  00:00:00 -to " + i + ":00:00 -i " + inputFileName + " -c copy "
                    + outFileName;
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
            String thumbnailFileName = FILEEXTENSION.removeExtension(outFileName).concat(FILEEXTENSION.JPEG.getValue());
            commands.addAll(createThumbnail(color, i + " HOUR " + heading, subHeading, thumbnailFileName));
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());

        }
        for (int i = 5; i < 59;) {
            String outFileName = (i + " MINUTE " + heading + " " + subHeading).replace(" ", "-")
                    + FILEEXTENSION.MP4.getValue();
            // Thumbnail
            String command = "ffmpeg -y -ss  00:00:00 -to 00:" + i + ":00 -i " + inputFileName + " -c copy "
                    + outFileName;
            commands.add(command);
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
            String thumbnailFileName = FILEEXTENSION.removeExtension(outFileName).concat(FILEEXTENSION.JPEG.getValue());
            commands.addAll(createThumbnail(color, heading, subHeading, thumbnailFileName));
            commands.add(System.lineSeparator());
            commands.add(System.lineSeparator());
            i = i + 5;
        }
        return commands;
    }

}
