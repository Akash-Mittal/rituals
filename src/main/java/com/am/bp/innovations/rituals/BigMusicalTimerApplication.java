package com.am.bp.innovations.rituals;

import java.nio.file.Path;
import java.util.ArrayList;

public class BigMusicalTimerApplication implements RITUALS, RITUALS.MUSICAL_BIG_TIMER {

    public static void main(String[] args) throws Exception {
        int masterTimeUnitHour = 4;
        int minutesArray[] = { 2, 5, 10, 15, 20, 30, 45 };
        String fontFile = "../fonts/FjallaOne-Regular.ttf";
        Path[] imagesPath = RITUALS.getAllFilesInFolder(RESOURCE_FOLDER_PATH + RAW_IMAGES, RITUALS.IMAGE_EXTENSION);
        Path[] musicFiles = RITUALS.getAllFilesInFolder(RESOURCE_FOLDER_PATH + MUSIC_FOLDER,
                RITUALS.MUSIC_FILES_EXTENSIONS);
        int counter = 1;
        for (int i = 0; i < musicFiles.length; i++) {
            for (int j = 0; j < imagesPath.length; j++) {
                ArrayList<String> commands = new ArrayList<>();
                String folderName = (TYPE2 + " " + TYPE3 + " " + (counter++)).replace(" ", "-");
                RITUALS.createDefaultFolder(RESOURCE_FOLDER_PATH + "/" + folderName);
                String imageFileName = "..\\raw-images\\" + imagesPath[j].getFileName().toString();
                String musicFileName = "..\\music\\" + musicFiles[i].getFileName().toString();
                int mastertimeInMinutes = (masterTimeUnitHour * 60);
                String mstertimeInseconds = "" + (masterTimeUnitHour * 60 * 60);

                String timeTicks = masterTimeUnitHour + ":00" + ":00";
                String fadeOutTimeStartInSeconds = ((masterTimeUnitHour * 60 * 60) - 10) + "";
                String text1 = masterTimeUnitHour + " HOUR";

                String thumbFileName = (text1 + " " + TYPE2 + " " + TYPE3).replace(" ", "-")
                        + FILEEXTENSION.JPEG.getValue();
                String mp3FileName = (text1 + " " + TYPE2 + " " + TYPE3).replace(" ", "-")
                        + FILEEXTENSION.MP3.getValue();

                String mp3StagingFileName = (text1 + " " + TYPE2 + " " + TYPE3 + " STAGING").replace(" ", "-")
                        + FILEEXTENSION.MP3.getValue();

                String mp4FileNameStaging = (text1 + " " + TYPE2 + " " + TYPE3 + " " + "STAGING").replace(" ", "-")
                        + FILEEXTENSION.MP4.getValue();

                String mp4FileNameStaging2 = (text1 + " " + TYPE2 + " " + TYPE3 + " " + "STAGING 2").replace(" ", "-")
                        + FILEEXTENSION.MP4.getValue();

                String mp4FileName = (text1 + " " + TYPE2 + " " + TYPE3).replace(" ", "-")
                        + FILEEXTENSION.MP4.getValue();
                String thumbnailCommand = "ffmpeg -y -i " + imageFileName + " -vf \"scale=1280:720,drawtext=fontfile="
                        + fontFile + ":fontsize=200:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                        + text1 + " ':x=(w-text_w)/2:y=(h-text_h)/2 - 200,drawtext=fontfile=" + fontFile
                        + ":fontsize=80:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='" + TYPE2
                        + "':x=(w-text_w)/2:y=(h-text_h)/2 - 45,drawtext=fontfile=" + fontFile
                        + ":fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='" + TYPE3
                        + "':x=(w-text_w)/2:y=(h-text_h)/2 + 80,drawtext=fontfile=" + fontFile
                        + ":fontsize=24:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='"
                        + "BY 10X PRODUCTIVITY" + "':x=w-tw:y=h-th\" " + "" + thumbFileName + "";

                commands.add(thumbnailCommand);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());
                String mp3StagingCommand = "ffmpeg -y -stream_loop -1 -i " + musicFileName + " -t " + mstertimeInseconds
                        + "" + " " + mp3StagingFileName;
                commands.add(mp3StagingCommand);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());
                String mp3Command = "ffmpeg -y -i " + mp3StagingFileName + " -af \"afade=out:st="
                        + fadeOutTimeStartInSeconds + ":d=5\" " + mp3FileName;
                commands.add(mp3Command);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());

                String timerVideoCommandStaging = "ffmpeg -y -loop 1 -i " + imageFileName + " -t " + mstertimeInseconds
                        + " -vf \"scale=1280:720,fps=1 ,drawbox=y=235:color=black@0.4:width=iw:height=300 :t=fill,drawtext=../fonts/FjallaOne-Regular.ttf:bordercolor='black':borderw=5:rate=25:text='%%{eif\\:trunc(mod(t/3600,24))\\:d\\:2}.%%{eif\\:trunc(mod(t/60,60))\\:d\\:2}.%%{eif\\:trunc(mod(t,60))\\:d\\:2}':fontcolor=white:fontsize=300:x=(w-tw)/2:y=(h-th)/2 + 20,format=yuv420p,drawtext=fontfile=../fonts/FjallaOne-Regular.ttf:fontsize=80:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='"
                        + TYPE2
                        + "' :x=(w-text_w)/2:y=(h-text_h)/2 - 200,drawtext=fontfile=../fonts/FjallaOne-Regular.ttf:fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                        + TYPE3
                        + "':x=(w-text_w)/2:y=(h-text_h)/2 + 240,drawtext=fontfile=../fonts/FjallaOne-Regular.ttf:fontsize=24:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                        + "BY 10X PRODUCTIVITY" + "':x=w-tw:y=h-th\" " + mp4FileNameStaging;
                commands.add(timerVideoCommandStaging);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());

                String timerVideoCommandStaging2 = "ffmpeg -y -i " + mp4FileNameStaging + " -itsoffset 00:00:00 -i "
                        + mp3FileName + " -map 0:0 -map 1:0 -c:v copy -preset ultrafast -async 1 "
                        + mp4FileNameStaging2;
                commands.add(timerVideoCommandStaging2);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());

                String finalTimerVideoCommand = "ffmpeg -y -i " + mp4FileNameStaging2
                        + " -vf \"drawtext=../fonts/FjallaOne-Regular.ttf:fontsize=100:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                        + text1 + "':x=(w-text_w)/2:y=(h-text_h)/2  - 300\" " + mp4FileName;
                commands.add(finalTimerVideoCommand);
                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());

                commands.add("del " + mp3FileName + " ");
                commands.add("del " + mp3StagingFileName + " ");

                commands.add("del " + mp4FileNameStaging + " ");

                commands.add(System.lineSeparator());
                commands.add(System.lineSeparator());
                for (int timeUnitCounterForMinutes = 0; timeUnitCounterForMinutes < minutesArray.length
                        && timeUnitCounterForMinutes < mastertimeInMinutes; timeUnitCounterForMinutes++) {
                    String text1Sliced = minutesArray[timeUnitCounterForMinutes] + " MINUTE";
                    thumbFileName = text1Sliced.replaceAll(" ", "-") + folderName + FILEEXTENSION.JPEG.getValue();

                    String slicedfinalMP4FileNameStaged = (text1Sliced + " " + TYPE2 + " " + TYPE3 + " STAGED")
                            .replace(" ", "-") + FILEEXTENSION.MP4.getValue();
                    String slicedfinalMP4FileName = (text1Sliced + " " + TYPE2 + " " + TYPE3).replace(" ", "-")
                            + FILEEXTENSION.MP4.getValue();
                    timeTicks = "00:" + minutesArray[timeUnitCounterForMinutes] + ":00";
                    mstertimeInseconds = "" + (minutesArray[timeUnitCounterForMinutes] * 60);
                    thumbnailCommand = "ffmpeg -y -i " + imageFileName + " -vf \"scale=1280:720,drawtext=fontfile="
                            + fontFile + ":fontsize=200:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                            + text1Sliced + " ':x=(w-text_w)/2:y=(h-text_h)/2 - 200,drawtext=fontfile=" + fontFile
                            + ":fontsize=80:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='" + TYPE2
                            + "':x=(w-text_w)/2:y=(h-text_h)/2 - 45,drawtext=fontfile=" + fontFile
                            + ":fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='" + TYPE3
                            + "':x=(w-text_w)/2:y=(h-text_h)/2 + 80,drawtext=fontfile=" + fontFile
                            + ":fontsize=24:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='"
                            + "BY 10X PRODUCTIVITY" + "':x=w-tw:y=h-th\" " + "" + thumbFileName + "";
                    commands.add(thumbnailCommand);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    String sliceMP4CommandStaged = "ffmpeg -y -ss  00:00:00 -to " + timeTicks + " -i "
                            + mp4FileNameStaging2 + " -c copy " + slicedfinalMP4FileNameStaged;
                    commands.add(sliceMP4CommandStaged);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    String sliceMP4Command = "ffmpeg -y -i " + slicedfinalMP4FileNameStaged
                            + " -vf \"drawtext=../fonts/FjallaOne-Regular.ttf:fontsize=100:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                            + text1Sliced + "':x=(w-text_w)/2:y=(h-text_h)/2  - 300\" " + slicedfinalMP4FileName;
                    commands.add(sliceMP4Command);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    commands.add("del " + slicedfinalMP4FileNameStaged + " ");
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());

                }

                for (int timeUnitCounterForHour = 1; timeUnitCounterForHour < 11
                        && timeUnitCounterForHour < masterTimeUnitHour; timeUnitCounterForHour++) {
                    String text1Sliced = timeUnitCounterForHour + " HOUR";
                    thumbFileName = text1Sliced.replaceAll(" ", "-") + folderName + FILEEXTENSION.JPEG.getValue();

                    String slicedfinalMP4FileNameStaged = (text1Sliced + " " + TYPE2 + " " + TYPE3 + " STAGED")
                            .replace(" ", "-") + FILEEXTENSION.MP4.getValue();
                    String slicedfinalMP4FileName = (text1Sliced + " " + TYPE2 + " " + TYPE3).replace(" ", "-")
                            + FILEEXTENSION.MP4.getValue();

                    timeTicks = timeUnitCounterForHour + ":00" + ":00";
                    mstertimeInseconds = "" + (timeUnitCounterForHour * 60 * 60);
                    thumbnailCommand = "ffmpeg -y -i " + imageFileName + " -vf \"scale=1280:720,drawtext=fontfile="
                            + fontFile + ":fontsize=200:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                            + text1Sliced + " ':x=(w-text_w)/2:y=(h-text_h)/2 - 200,drawtext=fontfile=" + fontFile
                            + ":fontsize=80:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='" + TYPE2
                            + "':x=(w-text_w)/2:y=(h-text_h)/2 - 45,drawtext=fontfile=" + fontFile
                            + ":fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='" + TYPE3
                            + "':x=(w-text_w)/2:y=(h-text_h)/2 + 80,drawtext=fontfile=" + fontFile
                            + ":fontsize=24:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='"
                            + "BY 10X PRODUCTIVITY" + "':x=w-tw:y=h-th\" " + "" + thumbFileName + "";
                    commands.add(thumbnailCommand);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    String sliceMP4CommandStaged = "ffmpeg -y -ss  00:00:00 -to " + timeTicks + " -i "
                            + mp4FileNameStaging2 + " -c copy " + slicedfinalMP4FileNameStaged;
                    commands.add(sliceMP4CommandStaged);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    String sliceMP4Command = "ffmpeg -y -i " + slicedfinalMP4FileNameStaged
                            + " -vf \"drawtext=../fonts/FjallaOne-Regular.ttf:fontsize=100:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='"
                            + text1Sliced + "':x=(w-text_w)/2:y=(h-text_h)/2  - 300\" " + slicedfinalMP4FileName;
                    commands.add(sliceMP4Command);
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());
                    commands.add("del " + slicedfinalMP4FileNameStaged + " ");
                    commands.add(System.lineSeparator());
                    commands.add(System.lineSeparator());

                }

                commands.add("del " + mp4FileNameStaging2 + " ");
                String finalCommands = String.join("\n\r", commands);
                finalCommands = finalCommands.replaceAll("E:\\GIT_CLONE\\rituals\\BigMusicalTimers", "..");
                RITUALS.write(RESOURCE_FOLDER_PATH + "/" + folderName + "/command.bat", finalCommands);
                if (counter == 200) {
                    break;
                }
            }
            // if (counter == 200) {
            // break;
            // }
        }

    }

}
