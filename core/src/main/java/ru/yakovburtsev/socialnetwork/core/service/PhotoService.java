package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.Photo;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link Photo} class.
 */

public interface PhotoService {
    void add(Photo photo);

    Photo get(Long id);

    void delete(Long id);

    List<Photo> getPhotos(Long userId);

}