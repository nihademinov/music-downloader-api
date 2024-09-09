package org.example.musicdownloaderapi.api.controller;

import lombok.AllArgsConstructor;
import org.example.musicdownloaderapi.api.model.MusicDownloadRequest;
import org.example.musicdownloaderapi.api.service.MusicDownloaderService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/music")
public class MusicDownloadController {
    private final MusicDownloaderService musicDownloaderService;

    @PostMapping()
    public String download(@RequestBody MusicDownloadRequest musicDownloadRequest)  {
       return musicDownloaderService.musicDownloader(musicDownloadRequest.getUrlFile(),musicDownloadRequest.getDownloadDirection());
    }

}
