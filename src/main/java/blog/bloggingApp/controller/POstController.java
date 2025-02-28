package blog.bloggingApp.controller;


import blog.bloggingApp.payloads.ApiResponse;
import blog.bloggingApp.payloads.PostDto;
import blog.bloggingApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class POstController {

    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPOst(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto postDto1= this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtos=this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
        PostDto post=this.postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> postDtos=this.postService.getAllPosts();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePOst(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true),HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePOst(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto postDto1=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable ("keywords") String keyword){
        List<PostDto> result= this.postService.searchPosts(keyword);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}


