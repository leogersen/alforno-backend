package com.leogersen.alforno.application.service;

import com.leogersen.alforno.domain.restaurant.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public class ItemMenuService {

    @Autowired
    private ItemMenuRepository itemMenuRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void saveItemMenu(ItemMenu itemMenu){


            itemMenu = itemMenuRepository.save(itemMenu);
            itemMenu.setImageFileName();
            imageService.uploadImage(itemMenu.getImageFile(), itemMenu.getImage());

        }



    }

