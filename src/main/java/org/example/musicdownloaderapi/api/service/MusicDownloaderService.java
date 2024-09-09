package org.example.musicdownloaderapi.api.service;

import lombok.AllArgsConstructor;
import org.example.musicdownloaderapi.business.MusicDownloaderManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicDownloaderService {
    private final MusicDownloaderManager musicDownloaderManager;

    public String musicDownloader(String file, String downloadDirection)  {
       return musicDownloaderManager.musicDownloader(file,downloadDirection);

    }

}
