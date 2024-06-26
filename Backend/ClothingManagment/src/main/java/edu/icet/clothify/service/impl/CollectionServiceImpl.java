package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.icet.clothify.dto.CollectionDto;
import edu.icet.clothify.entity.Collection;
import edu.icet.clothify.repository.CollectionRepository;
import edu.icet.clothify.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean saveCollection(CollectionDto collectionDto) {
        Collection collection=objectMapper.convertValue(collectionDto,Collection.class);
        Collection isSaved=collectionRepository.save(collection);
        return isSaved.getId()!=null;
    }

    @Override
    public List<CollectionDto> getAllCollection() {
        Iterable<Collection> collections =collectionRepository.findAll();
        Iterator<Collection> collectionIterator =collections.iterator();
        List<CollectionDto> collectionDtoList= new ArrayList<>();
        while (collectionIterator.hasNext()){
            Collection collection=collectionIterator.next();
            CollectionDto collectionDto=objectMapper.convertValue(collection,CollectionDto.class);
            collectionDtoList.add(collectionDto);
        }
        return collectionDtoList;
    }

    @Override
    public CollectionDto getCollectionById(Long id) {
        try {
            Collection collection = collectionRepository.findById(id).get();
            if (collection.getId() == null) {
                return null;
            }
            return objectMapper.convertValue(collection, CollectionDto.class);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public boolean deleteCollectionById(Long id) {
        collectionRepository.deleteById(id);
        CollectionDto collectionDto=getCollectionById(id);
        return collectionDto == null;
    }

    @Override
    public CollectionDto getCollectionByName(String name) {
            Collection collection=collectionRepository.getByName(name);
        return objectMapper.convertValue(collection,CollectionDto.class);
    }
}
