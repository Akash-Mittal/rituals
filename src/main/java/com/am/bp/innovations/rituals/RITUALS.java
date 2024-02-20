package com.am.bp.innovations.rituals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public interface RITUALS {

    public static String STATIC_RESOURCES_FOLDER = "E:/GIT_CLONE/rituals/src/main/resources/static-files/";

    public static String MAIN_RESOURCES_FOLDER = "E:/GIT_CLONE/rituals/src/main/resources/";

    interface MUSICAL_BIG_TIMER {
        public static String TYPE2 = "STUDY WORK TIMER (WITH MUSIC)";
        public static String TYPE3 = "FOR 10X PRODUCTIVITY";
        public static String MUSIC_FOLDER = "music";
        public static String RAW_IMAGES = "raw-images";
        public static String RESOURCE_FOLDER_PATH = "BigMusicalTimers\\";
    }

    interface MILITARY_TIMER {
        public static String ROOT_FOLDER_NAME = "military-timers";
        public static String RESOURCE_FOLDER_PATH = RITUALS.MAIN_RESOURCES_FOLDER + ROOT_FOLDER_NAME + "/";
        public static String MUSIC_FOLDER = "background-music";
        public static String RAW_IMAGES = "background-images-raw";
        public static String COPYRIGHT_IMAGES = "background-images-copyrighted";
        public static String TIMER_IMAGES = "background-images";
        public static String SCRIPT_30MINUTES = "script.txt";
    }

    interface BIG_TIMER {
        public static String TYPE = "STUDY WORK TIMER HOURLY REMINDER BY AK47";

        public static String ROOT_FOLDER_NAME = "big-timers";
        public static String RESOURCE_FOLDER_PATH = RITUALS.MAIN_RESOURCES_FOLDER + ROOT_FOLDER_NAME + "/";
        public static String MUSIC_FOLDER = "background-music";
        public static String RAW_IMAGES = "background-images-raw";
        public static String COPYRIGHT_IMAGES = "background-images-copyrighted";
        public static String TIMER_IMAGES = "background-images";
    }

    public static String[] MUSIC_FILES_EXTENSIONS = { FILEEXTENSION.MP3.getValue() };
    public static final String IMAGE_EXTENSION[] = { FILEEXTENSION.JPEG.getValue(), FILEEXTENSION.JPG.getValue(),
            FILEEXTENSION.PNG.getValue() };

    public static String BACKGROUND_MUSIC_FOLDER_PATH = "E:/GIT_CLONE/rituals/src/main/resources/backgroun-music";
    public static String RITUAL_IMAGES_TEMPLATE_FOLDER = "E:/GIT_CLONE/rituals/src/main/resources/background-images-template";
    public static String RITUAL_IMAGES_COPYRIGHTES_FOLDER = "E:/GIT_CLONE/rituals/src/main/resources/background-images-copyrighted";
    public static String RITUAL_IMAGES_FINAL_FOLDER = "E:/GIT_CLONE/rituals/src/main/resources/background-images";

    public static Path[] getAllFilesInFolder(String source, String[] fileExtensions) throws IOException {
        Path sourceDir = Paths.get(source);
        ArrayList<Path> files = new ArrayList<>();
        if (Files.walk(sourceDir).count() > 0) {
            Files.walk(sourceDir).filter(Files::isRegularFile).filter(path -> {
                for (String extension : fileExtensions) {
                    if (path.toString().endsWith(extension)) {
                        return true;
                    }
                }
                return false;
            }).forEach(path -> {
                files.add(path);
            });
        } else {
        }
        return files.toArray(new Path[0]);
    }

    public static File[] getFilesFromFolder(String folderPath, String[] extensions) {
        File folder = new File(folderPath);
        List<File> fileNames = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        for (int i = 0; i < extensions.length; i++) {
                            if (file.getName().endsWith(extensions[i])) {
                                fileNames.add(file);
                            }
                        }
                    }
                }
            }
        }
        return fileNames.toArray(new File[fileNames.size()]);

    }

    public static Path write(String filePath, String text) throws IOException {
        return write(filePath, text, StandardOpenOption.APPEND);
    }

    public static Path write(String filePath, String text, StandardOpenOption standardOpenOption) throws IOException {
        Path filePathFromPath = Paths.get(filePath);
        if (!Files.exists(filePathFromPath)) {
            Files.createFile(filePathFromPath);
        }
        return Files.write(filePathFromPath, text.getBytes(StandardCharsets.UTF_8), standardOpenOption);
    }

    public static String loadFileAndReadAsStrings(String fileName) throws IOException {
        String data = Files.readString(Paths.get(fileName), StandardCharsets.ISO_8859_1);
        return data;
    }

    public static void createDefaultFolder(String folderPath) {
        if (!Paths.get(folderPath).toFile().exists()) {
            try {
                Files.createDirectory(Paths.get(folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
