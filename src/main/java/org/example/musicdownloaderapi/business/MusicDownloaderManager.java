package org.example.musicdownloaderapi.business;

import lombok.AllArgsConstructor;
import org.example.musicdownloaderapi.MusicDownloaderApiApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MusicDownloaderManager {


    public String musicDownloader(String filePath, String downloadDirectory)  {
        Path downloadPath = Paths.get(downloadDirectory);
        try {
            if (!Files.exists(downloadPath)) {
                Files.createDirectories(downloadPath);
            }
        } catch (IOException e) {
            return ("Could not create directory: " + e.getMessage());
        }

        List<String> links = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String link;
            while ((link = br.readLine()) != null) {
                if (!link.isEmpty()) {
                    links.add(link);
                }
            }
        } catch (IOException e) {
            return ("Error reading the file: " + e.getMessage());
        }

        for (String link : links) {

            downloadAudioAsMp3(link, downloadDirectory);
        }
        return "success";
    }


    private static void downloadAudioAsMp3(String link, String downloadDirectory)  {

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    getPath("yt-dlp.exe"),
                    "--extract-audio",
                    "--audio-format", "mp3",
                    "--ffmpeg-location", getPath("ffmpeg.exe"),
                    "--no-playlist",
                    "-o", downloadDirectory + File.separator + "%(title)s.%(ext)s",
                    link);

            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("yt-dlp process exited with code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred while downloading audio: " + e.getMessage());
        }
    }


    private static String getPath(String fileName)
    {
        ClassLoader classLoader = MusicDownloaderApiApplication.class.getClassLoader();

        URL resource = classLoader.getResource("exe/"+fileName);  // Modify this if the file is in a subdirectory

        if (resource != null) {
            File file = new File(resource.getFile());
            String filePath = file.getAbsolutePath();

//            System.out.println("File Path: " + filePath);
            return filePath;
        } else {
            System.out.println("File not found!");
        }
        return null;
    }
}


