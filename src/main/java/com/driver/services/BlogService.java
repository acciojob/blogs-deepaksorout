package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    public void createAndReturnBlog(Integer userId, String title, String content)throws Exception {
        //create a blog at the current time
        Blog blog=new Blog();
        blog.setContent(content);
        blog.setTitle(title);

        if(!userRepository.findById(userId).isPresent()){
            throw new Exception();
        }
        User curruser=userRepository.findById(userId).get();

        List<Blog> blogList=curruser.getBlogList();
        blogList.add(blog);
        curruser.setBlogList(blogList);

        userRepository.save(curruser);

        return;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository.deleteById(blogId);
        return;
    }
}
