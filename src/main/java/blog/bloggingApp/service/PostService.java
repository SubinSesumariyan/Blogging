package blog.bloggingApp.service;

import blog.bloggingApp.payloads.PostDto;
import blog.bloggingApp.payloads.UserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts();
    void deletePOst(Integer postId);
    PostDto updatePost(PostDto postDto, Integer postId);
    List<PostDto> searchPosts(String keyword);

}
