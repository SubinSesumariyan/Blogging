package blog.bloggingApp.service;

import blog.bloggingApp.entity.Category;
import blog.bloggingApp.entity.Post;
import blog.bloggingApp.entity.User;
import blog.bloggingApp.exception.ResourceNotFoundException;
import blog.bloggingApp.payloads.PostDto;
import blog.bloggingApp.repo.CategoryRepo;
import blog.bloggingApp.repo.PostRepo;
import blog.bloggingApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id", categoryId));
        Post post=new Post();
        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost=this.postRepo.save(post);

        PostDto postDto1=new PostDto();
        postDto1.setPostId(savedPost.getPostId());
        postDto1.setTitle(savedPost.getTitle());
        postDto1.setContent(savedPost.getContent());
        postDto1.setImageName(savedPost.getImageName());
        postDto1.setAddedDate(savedPost.getAddedDate());
        postDto1.setUser(savedPost.getUser());
        postDto1.setCategory(savedPost.getCategory());
        return postDto1;

    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id", categoryId));
        List<Post> posts=this.postRepo.findByCategory(category);
        List<PostDto> postDtos=new ArrayList<>();
        for(int i=0; i<posts.size(); i++){
            PostDto postDto=new PostDto();
            postDto.setPostId(posts.get(i).getPostId());
            postDto.setTitle(posts.get(i).getTitle());
            postDto.setContent(posts.get(i).getContent());
            postDto.setImageName(posts.get(i).getImageName());
            postDto.setAddedDate(posts.get(i).getAddedDate());
            postDtos.add(postDto);
            //postDto.setCategory(category);
            postDto.setCategory(posts.get(i).getCategory());  // to set category
            postDto.setUser(posts.get(i).getUser());   // to set user

        }
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=new ArrayList<>();
        for(int i=0; i<posts.size(); i++){
            PostDto postDto=new PostDto();
            postDto.setPostId(posts.get(i).getPostId());
            postDto.setTitle(posts.get(i).getTitle());
            postDto.setContent(posts.get(i).getContent());
            postDto.setImageName(posts.get(i).getImageName());
            postDto.setAddedDate(posts.get(i).getAddedDate());
            postDtos.add(postDto);

            postDto.setCategory(posts.get(i).getCategory());  // to set category
            postDto.setUser(posts.get(i).getUser());   // to set user
        }
        return  postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","Id",postId));
        PostDto postDto=new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());
        postDto.setCategory(post.getCategory());
        postDto.setUser(post.getUser());
        return  postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts=this.postRepo.findAll();
        List<PostDto> postDtos=new ArrayList<>();
        for(int i=0; i<posts.size(); i++){
            PostDto postDto=new PostDto();
            postDto.setPostId(posts.get(i).getPostId());
            postDto.setTitle(posts.get(i).getTitle());
            postDto.setContent(posts.get(i).getContent());
            postDto.setImageName(posts.get(i).getImageName());
            postDto.setAddedDate(posts.get(i).getAddedDate());
            postDto.setCategory(posts.get(i).getCategory());
            postDto.setUser(posts.get(i).getUser());

            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    public void deletePOst(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","Id", postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());
        //post.setUser(post.getUser());
        //post.setCategory(post.getCategory());

        Post savedPost=this.postRepo.save(post);

        PostDto postDto1=new PostDto();
        postDto1.setPostId(savedPost.getPostId());
        postDto1.setTitle(savedPost.getTitle());
        postDto1.setContent(savedPost.getContent());
        postDto1.setImageName(savedPost.getImageName());
        postDto1.setAddedDate(savedPost.getAddedDate());
        postDto1.setUser(savedPost.getUser());
        postDto1.setCategory(savedPost.getCategory());
        return postDto1;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos=new ArrayList<>();
        for(int i=0; i<posts.size(); i++){
            PostDto postDto=new PostDto();
            postDto.setPostId(posts.get(i).getPostId());
            postDto.setTitle(posts.get(i).getTitle());
            postDto.setContent(posts.get(i).getContent());
            postDto.setImageName(posts.get(i).getImageName());
            postDto.setAddedDate(posts.get(i).getAddedDate());
            postDto.setCategory(posts.get(i).getCategory());
            postDto.setUser(posts.get(i).getUser());

            postDtos.add(postDto);
        }
        return postDtos;
    }
}
