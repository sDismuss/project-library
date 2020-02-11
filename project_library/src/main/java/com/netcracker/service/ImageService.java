package main.java.com.netcracker.service;

import main.java.com.netcracker.model.Image;
import main.java.com.netcracker.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findByLink(String link) {
        Optional<Image> optional = imageRepository.findById(link);
        return optional.orElse(null);
    }

    public List<Image> findByBook(String book) {
        return imageRepository.findByBook(book);
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public List<Image> retrieveByBook(String book) {
        return imageRepository.retrieveByBook(book);
    }

    public List<Image> getImages() {
        List<Image> images = imageRepository.findAll();
        return images;
    }

}
