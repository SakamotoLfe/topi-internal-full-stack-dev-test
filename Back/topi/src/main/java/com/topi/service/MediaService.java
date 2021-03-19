package com.topi.service;

import com.topi.model.Ingredient;
import com.topi.model.Media;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.MediaPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Class created to be the Service of Media.
 *
 * @since 2021-03-14
 */

@Service
public class MediaService extends BasicService<Media> {

    /**
     * Media Repository Instance.
     */
    private MediaRepository mediaRepository;

    /**
     * Path that the media will be saved and recovered.
     */
    private String path;

    /**
     * Constructor with params.
     * @param mediaRepository Media Repository Instance.
     * @param path Path that the media will be saved and recovered.
     */
    @Autowired
    public MediaService(MediaRepository mediaRepository, @Value("${path-medias}") String path) {
        this.mediaRepository = mediaRepository;
        this.path = path;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Media>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Media> getBasicRepository() {
        return mediaRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Media>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Media> getSearchPredicate(String search) {
        return new MediaPredicate(search);
    }

    /**
     * Method that fiscally saves a media.
     * @param file File to be saved.
     * @return {@link Boolean}. If the file was saved successfully.
     */
    public Boolean saveFile(MultipartFile file) {
        try {
            Media media = new Media(null, null, null, true,
                    file.getOriginalFilename().split("\\.")[1], path + "\\" + file.getOriginalFilename(),
                    file.getBytes().length / 1024F, file.getOriginalFilename(), file.getBytes());
            media = mediaRepository.save(media);
            saveThumb(media, 200, 200, path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method that retrieves a saved media by its id.
     * @param id ID from the media.
     * @return {@link Media}. Media recovered.
     */
    public Media getMedia(Long id) {
        Optional<Media> media = mediaRepository.findById(id);
        if(media.isPresent()){
            try {
                Path pathObj = Paths.get(path + "\\", media.get().getName() + "." + media.get().getType());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                InputStream is = Files.newInputStream(Paths.get(String.valueOf(pathObj)));
                BufferedImage bufferedImage = ImageIO.read(is);
                is.close();
                ImageIO.write(bufferedImage, media.get().getType(), bos);
                media.get().setBytes(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return media.get();
        } else {
            return null;
        }
    }

    /**
     * Method that resizes and saves a media.
     * @param media Media to be saved.
     * @param widthThumb Desired width.
     * @param heightThumb Desired heigh.
     * @param path Path that the media is going to be saved.
     */
    private static void saveThumb(Media media, int widthThumb, int heightThumb, String path) {
        try (InputStream is = new ByteArrayInputStream(media.getBytes())) {
            Image image = ImageIO.read(is);

            double thumbRatio = (double) widthThumb / (double) heightThumb;
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            double imageRatio = (double) imageWidth / (double) imageHeight;
            if (thumbRatio < imageRatio) {
                heightThumb = (int) (widthThumb / imageRatio);
            } else {
                widthThumb = (int) (heightThumb * imageRatio);
            }

            if (imageWidth < widthThumb && imageHeight < heightThumb) {
                widthThumb = imageWidth;
                heightThumb = imageHeight;
            } else if (imageWidth < widthThumb)
                widthThumb = imageWidth;
            else if (imageHeight < heightThumb)
                heightThumb = imageHeight;

            BufferedImage thumbImage = new BufferedImage(widthThumb, heightThumb, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = thumbImage.createGraphics();
            graphics2D.setBackground(Color.WHITE);
            graphics2D.setPaint(Color.WHITE);
            graphics2D.fillRect(0, 0, widthThumb, heightThumb);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image, 0, 0, widthThumb, heightThumb, null);

            File directory = new File(path + "\\" + media.getName());
            if (!directory.exists()) {
                directory.mkdir();
            }

            File mediaFile = new File(path + "\\" + media.getName(), media.getName() + "." + media.getType());
            ImageIO.write(thumbImage, media.getType().toUpperCase(), mediaFile);
        } catch (IOException e) {
            throw new UncheckedIOException("There's an error trying to save your media. " + media.getName(), e);
        }
    }
}
