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



            Blog currblog = blogRepository.findById(blogId).get();
            List<Image> imageList = currblog.getImageList();
            imageList.add(image);
            currblog.setImageList(imageList);

            blogRepository.save(currblog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository.deleteById(id);
        return;
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String [] scrarray = screenDimensions.split("X"); //A=Length   X    B=Breadth
//        if(!imageRepository2.findById(id).isPresent()){
//            throw new Exception();
//        }
        Image image = imageRepository.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] imgarray = imageDimensions.split("X");

        int scrl = Integer.parseInt(scrarray[0]); //A -- > integer
        int scrb = Integer.parseInt(scrarray[1]); //B -- > integer

        int imgl = Integer.parseInt(imgarray[0]); //A -- > integer
        int imgb = Integer.parseInt(imgarray[1]); //B -- > integer

        return no_Images(scrl,scrb,imgl,imgb);
    }
    private int no_Images(int scrl, int scrb, int imgl, int imgb) {
        int lenC = scrl/imgl; //
        int lenB = scrb/imgb;
        return lenC*lenB;
    }
}
