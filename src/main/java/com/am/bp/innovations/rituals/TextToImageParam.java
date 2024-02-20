package com.am.bp.innovations.rituals;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
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
public class TextToImageParam {
    private String inputImage;
    private DIMENSIONS scale;
    private String drawBoox;
    private String outputFile;
    private TextParam textParams;

    public String buildCommand() {

        StringBuilder sb = new StringBuilder();
        sb.append("ffmpeg -y -i ").append(inputImage).append(" -vf \"");
        if (!Objects.isNull(scale)) {
            sb.append("scale=").append(scale.getDimensions()).append(",");
        }
        if (!StringUtils.isEmpty(drawBoox)) {
            sb.append(drawBoox).append(" :t=fill,");
        }
        sb.append(textParams.buildCommand()).append("\" ").append(outputFile);

        String command = sb.toString();
        return command;
    }

    public static TextToImageParam getDefaultImageWithCopyRights(String inputFile, String outputFile) {
        TextToImageParam textToImageParams = new TextToImageParam();
        textToImageParams.setInputImage(inputFile);
        textToImageParams.setScale(DIMENSIONS.PIXELS720);
        textToImageParams.setDrawBoox("drawbox=y=235:color=black@0.4:width=iw:height=250");
        textToImageParams.setOutputFile(outputFile);
        textToImageParams.setTextParams(TextParam.getDefaultCopyRightCommand());
        return textToImageParams;
    }

    public static TextToImageParam getDefaultImageWithMainHeading(String inputFile, String outputFile, String text) {
        TextToImageParam textToImageParams = new TextToImageParam();
        textToImageParams.setInputImage(inputFile);
        textToImageParams.setScale(null);
        textToImageParams.setDrawBoox(null);
        textToImageParams.setOutputFile(outputFile);
        textToImageParams.setTextParams(TextParam.getDefaultMainHeadingH1Command(text));
        return textToImageParams;
    }

    public static TextToImageParam getDefaultImageWithSubHeading(String inputFile, String outputFile, String text) {
        TextToImageParam textToImageParams = new TextToImageParam();
        textToImageParams.setInputImage(inputFile);
        textToImageParams.setScale(null);
        textToImageParams.setDrawBoox(null);
        textToImageParams.setOutputFile(outputFile);
        textToImageParams.setTextParams(TextParam.getDefaultSubHeadingH2Command(text));
        return textToImageParams;
    }

    public static TextToImageParam getCommand(String inputFile, String outputFile, String text, int fontSize,
            COLORS color, String textLocation) {
        TextToImageParam textToImageParams = new TextToImageParam();
        textToImageParams.setInputImage(inputFile);
        textToImageParams.setScale(null);
        textToImageParams.setDrawBoox(null);
        textToImageParams.setOutputFile(outputFile);
        textToImageParams.setTextParams(TextParam.getCommand(text, fontSize, color, textLocation));
        return textToImageParams;
    }

    public static void main(String[] args) {
        System.out.println(TextToImageParam.getDefaultImageWithCopyRights("", "").buildCommand());
    }

}