package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions) {
        //add an image to the blog
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        if(blogRepository.findById(blogId).isPresent()) {


            Blog currblog = blogRepository.findById(blogId).get();
            List<Image> imageList = currblog.getImageList();
            imageList.add(image);
            currblog.setImageList(imageList);

            blogRepository.save(currblog);
        }
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository.deleteById(id);
        return;
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image currimage=imageRepository.findById(id).get();
        String currdimensions= currimage.getDimensions();

        int indexofx=-1;
        for(int i=0;i<currdimensions.length();i++){
            if(currdimensions.charAt(i)=='X'){
                indexofx=i;
                break;
            }
        }
        int firstpart=Integer.valueOf(currdimensions.substring(0,indexofx));
        int secondpart=Integer.valueOf(currdimensions.substring(indexofx+1));

        int temp=firstpart*secondpart;

        int indexofx1=-1;
        for(int i=0;i<screenDimensions.length();i++){
            if(currdimensions.charAt(i)=='X'){
                indexofx1=i;
                break;
            }
        }
        int temp2=Integer.valueOf(screenDimensions.substring(0,indexofx1)) * Integer.valueOf(screenDimensions.substring(indexofx1+1));

        return ( temp2/temp);

    }
}
