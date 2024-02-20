package com.am.bp.innovations.rituals;

public enum FILEEXTENSION {

    BAT(".bat"), JPEG(".jpeg"), JPG(".jpg"), WEBP(".webp"), MP3(".mp3"), MP4(".mp4"), TXT(".txt"), PNG(".png"), JSON(
            ".json"), HTML(
                    ".html"), AICHATTER_HISTORY(".aichatter.history"), AICHATTER_ZIP(".aichatter.zip"), NOEXTENSION("");

    private String value;

    FILEEXTENSION(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static String removeExtension(final String filename) {
        return filename.split(SYMBOLS.DOT.getRegexEqual())[0];
    }

    public static void main(String[] args) {
        System.out.println(FILEEXTENSION.removeExtension(
                "Ultra-Focus-Countdown-Timer-Curated-on-Tuvalu-Flag-In-HD-Tik-Tok-In-Last-10-Seconds.png"));
        System.out.println(FILEEXTENSION.removeExtension(
                "Ultra-Focus-Countdown-Timer-Curated-on-Tuvalu-Flag-In-HD-Tik-Tok-In-Last-10-Seconds.png.png"));
        System.out.println(FILEEXTENSION.removeExtension(
                "Ultra-Focus-Countdown-Timer-Curated-on-Tuvalu-Flag-In-HD-Tik-Tok-In-Last-10-Seconds.bat"));
        System.out.println(FILEEXTENSION.removeExtension(
                "Ultra-Focus-Countdown-Timer-Curated-on-Tuvalu-Flag-In-HD-Tik-Tok-In-Last-10-Seconds.png.png.bat"));
        System.out.println(FILEEXTENSION.removeExtension(
                "Ultra-Focus-Countdown-Timer-Curated-on-Tuvalu-Flag-In-HD-Tik-Tok-In-Last-10-Seconds.bat.jpeg"));

    }
}