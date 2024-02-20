package com.am.bp.innovations.rituals;

import java.util.Calendar;

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
public class TextParam {
    String fontFile;
    Integer fontSize;
    COLORS color;
    String textBorder;
    String text;
    String textLocation;

    private String getNormalizedText() {
        return text.replace(":", "\\:").replace("'", "'\\\\\\''").toUpperCase();
    }

    public String buildCommand() {
        String command = "drawtext=fontfile=" + fontFile + ":fontsize=" + fontSize + ":fontcolor='" + color.getValue()
                + "':" + textBorder + ":text='" + getNormalizedText() + "':" + textLocation + "";
        return command;
    }

    public static TextParam getDefaultCopyRightCommand() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        TextParam textParams3 = new TextParam("C\\\\:/Windows/fonts/IMPACT.TTF", 24, COLORS.WHITE,
                "bordercolor='black':borderw=5:rate=25", "Copyright " + year + " 10XProductivity",
                TEXTPOSITION.BOTTOMRIGHT.getValue());
        return textParams3;
    }

    public static TextParam getDefaultMainHeadingH1Command(String text) {
        return getCommand(text, 80, COLORS.WHITE, TEXTPOSITION.TOP_CENTER_CENTER.getValue());
    }

    public static TextParam getCommand(String text, int fontSize, COLORS color,
            String textLocation) {
        TextParam textParams3 = new TextParam("C\\\\:/Windows/fonts/IMPACT.TTF", fontSize, color,
                "bordercolor='black':borderw=5:rate=25", text, textLocation);
        return textParams3;
    }

    public static TextParam getDefaultSubHeadingH2Command(String text) {
        TextParam textParams3 = new TextParam("C\\\\:/Windows/fonts/IMPACT.TTF", 50, COLORS.YELLOW,
                "bordercolor='black':borderw=5:rate=25", text, TEXTPOSITION.CENTER_BOTTOM_CENTER.getValue());
        return textParams3;
    }
}
