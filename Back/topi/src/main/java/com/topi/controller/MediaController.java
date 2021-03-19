package com.topi.controller;

import com.topi.model.Instruction;
import com.topi.model.Media;
import com.topi.service.BasicService;
import com.topi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class created to be the Controller of Media.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/medias")
public class MediaController extends BasicController<Media> {

    /**
     * Media Service Instance.
     */
    private MediaService mediaService;

    /**
     * Constructor with params.
     *
     * @param mediaService Media Service Instance.
     */
    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Media>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Media> getBasicService() {
        return mediaService;
    }

    /**
     * Endpoint that saves a media.
     * @param media Media to be saved.
     * @return {@link Boolean}. If the media was saved successfully.
     */
    @ResponseBody
    @PostMapping(value = "/upload/{id}")
    public ResponseEntity<Boolean> save(@RequestParam("file") MultipartFile media) {
        return new ResponseEntity<>(mediaService.saveFile(media), HttpStatus.CREATED);
    }

    /**
     * Endpoint that downloads a media.
     * @param id ID of the media to be downloaded.
     * @return {@link ResponseEntity<Media>}. Response with the Media downloaded.
     */
    @ResponseBody
    @GetMapping(value = "/download/{id}")
    public ResponseEntity<Media> loadById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mediaService.getMedia(id), HttpStatus.OK);
    }
}
