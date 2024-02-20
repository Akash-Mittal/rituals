package com.am.bp.innovations.rituals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TextToImageParams implements RITUALS, RITUALS.MILITARY_TIMER {
    private List<TextToImageParam> textToImageParams;

    public static String generateCopyrightsImage() throws IOException {
        return generateCopyrightsImage(RITUAL_IMAGES_TEMPLATE_FOLDER, RITUAL_IMAGES_COPYRIGHTES_FOLDER);
    }

    public static String generateCopyrightsImage(String inputFolder, String outPutFolder) throws IOException {
        String Status = "Success:Total # of Copyright Images Created=";
        Path[] imagefiles = RITUALS.getAllFilesInFolder(inputFolder, IMAGE_EXTENSION);

        for (int i = 0; i < imagefiles.length; i++) {
            Path baseImage = imagefiles[i];
            String command = TextToImageParam
                    .getDefaultImageWithCopyRights(baseImage.toString(), "ritual-" + baseImage.getFileName().toString())
                    .buildCommand();
            Process process = Runtime.getRuntime().exec(command, null, new File(outPutFolder));
        }
        return Status + imagefiles.length;
    }

    public static ArrayList<String> generateImagesWithText(String H1, int H1FontSize, COLORS H1color, String H2,
            int H2FontSize, COLORS H2color, String inputImage, String outPutFile)
            throws IOException, InterruptedException {
        String command1 = "";
        String command2 = "";
        ArrayList<String> commands = new ArrayList<>();
        String tempFileName = FILEEXTENSION.removeExtension(inputImage) + "-temp" + FILEEXTENSION.JPEG.getValue();
        command1 = TextToImageParam
                .getCommand(inputImage, tempFileName, H1, H1FontSize, H1color, "x=(w-text_w)/2:y=(h-text_h)/2 - 45")
                .buildCommand();
        command2 = TextToImageParam
                .getCommand(tempFileName, outPutFile, H2, H2FontSize, H2color, "x=(w-text_w)/2:y=(h-text_h)/2 + 40")
                .buildCommand();
        commands.add(command1);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add(command2);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add("del " + tempFileName);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());

        return commands;
    }

    public static ArrayList<String> generateImagesWithText(String H1, String H2, String outPutFile)
            throws IOException, InterruptedException {
        String command1 = "";
        String command2 = "";
        ArrayList<String> commands = new ArrayList<>();

        Random random = new Random();
        Path[] ALL_COPYRIGHTS_IMAGE = RITUALS.getAllFilesInFolder(RITUAL_IMAGES_COPYRIGHTES_FOLDER, IMAGE_EXTENSION);
        int randomIndex = random.nextInt(ALL_COPYRIGHTS_IMAGE.length);

        command1 = TextToImageParam.getDefaultImageWithMainHeading(ALL_COPYRIGHTS_IMAGE[randomIndex].toString(),
                "TEMP-" + ALL_COPYRIGHTS_IMAGE[randomIndex].getFileName().toString(), H1).buildCommand();

        command2 = TextToImageParam.getDefaultImageWithSubHeading(
                "TEMP-" + ALL_COPYRIGHTS_IMAGE[randomIndex].getFileName().toString(), outPutFile, H2).buildCommand();
        commands.add(command1);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        commands.add(command2);
        commands.add(System.lineSeparator());
        commands.add(System.lineSeparator());
        return commands;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // generateImagesWithText("5 Minutes Ritual", "SUMMIT BREATHING", "5-min-ritual.jpeg");
        generateCopyrightsImage(RESOURCE_FOLDER_PATH + RAW_IMAGES, RESOURCE_FOLDER_PATH + COPYRIGHT_IMAGES);
    }
}