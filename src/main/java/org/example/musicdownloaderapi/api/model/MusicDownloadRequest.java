package org.example.musicdownloaderapi.api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicDownloadRequest {
    private String urlFile;
    private String downloadDirection;


}