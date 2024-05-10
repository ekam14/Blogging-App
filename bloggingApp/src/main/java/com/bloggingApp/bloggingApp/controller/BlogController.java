package com.bloggingApp.bloggingApp.controller;

import com.bloggingApp.bloggingApp.entity.Blog;
import com.bloggingApp.bloggingApp.entity.Comment;
import com.bloggingApp.bloggingApp.entity.Like;
import com.bloggingApp.bloggingApp.entity.User;
import com.bloggingApp.bloggingApp.service.BlogService;
import com.bloggingApp.bloggingApp.service.CommentService;
import com.bloggingApp.bloggingApp.service.LikeService;
import com.bloggingApp.bloggingApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class BlogController {
  private final UserService userService;
  private final BlogService blogService;
  private final LikeService likeService;
  private final CommentService commentService;

  @Autowired
  public BlogController(UserService theUserService, BlogService theBlogService,
                        LikeService theLikeService, CommentService theCommentService) {
    userService = theUserService;
    blogService = theBlogService;
    likeService = theLikeService;
    commentService = theCommentService;
  }

  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    // Trims down any String input, will assign empty string to null.
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  @GetMapping("/")
  public String showLoginForm(Model theModel) {
    theModel.addAttribute("user", new User());
    return "login";
  }

  @GetMapping("/signUp")
  public String showSignUpForm(@ModelAttribute("user") User user) {
    return "signup";
  }

  @PostMapping("/processFormLogin")
  public String processFormLogin(@Valid @ModelAttribute("user") User user,
                            Model theModel, BindingResult theBindingResult) {
    if(user.getEmail() == null || user.getPassword() == null) {
      theModel.addAttribute("userNull", true);
      return "login";
    }

    // if there are no validation errors && password, email are correct
    boolean userExists = userService.checkForUser(user);

    if(!userExists) {
      theModel.addAttribute("userNotExists", true);
    }

    if(theBindingResult.hasErrors() || !userExists) {
      return "login";
    }

    theModel.addAttribute("user", user);
    return "redirect:/profile";
  }

  @PostMapping("/processFormSignup")
  public String processFormSignup(@Valid @ModelAttribute("user") User user,
                            Model theModel,
                            BindingResult theBindingResult) {
    if(user.getFirstName() == null || user.getLastName() == null
            || user.getEmail() == null || user.getPassword() == null) {
      theModel.addAttribute("userNull", true);
      return "signup";
    }

    boolean emailExists = userService.checkForEmail(user);

    if(emailExists) {
      theModel.addAttribute("emailExists", true);
    }

    // if there are no validation errors
    if(theBindingResult.hasErrors() || emailExists) {
      return "signup";
    }
    userService.addUser(user);

    theModel.addAttribute("user", user);
    return "redirect:/profile";
  }

  @GetMapping("/profile")
  public String showProfile(@ModelAttribute("user") User user, Model theModel) {
    theModel.addAttribute("user", userService.findUserByEmail(user.getEmail()));
    return "userProfile";
  }


  @GetMapping("/myBlogs")
  public String getMyBlogs(@ModelAttribute("user") User user, Model theModel) {
    List<Blog> blogs = blogService.getBlogs(user.getUserId());
    theModel.addAttribute("blogs", blogs);
    return "listBlogsForUser";
  }

  @GetMapping("/showFormAddBlog")
  public String showFormAddBlog(Model theModel){
    theModel.addAttribute("blog", new Blog());
    return "showFormAddBlog";
  }

  @PostMapping("/saveBlog")
  public String saveBlog(@ModelAttribute("user") User theUser, @ModelAttribute("blog") Blog theBlog) {
    theBlog.setUser(theUser);
    blogService.saveBlog(theBlog);

    return "redirect:/myBlogs";
  }

  @GetMapping("/showFormForUpdateBlog")
  public String showFormForUpdateBlog(@RequestParam("blogId") int blogId, Model theModel) {
    Blog theBlog = blogService.getBlogById(blogId);
    theModel.addAttribute("blog", theBlog);
    return "showFormAddBlog";
  }

  @PostMapping("/updateBlog")
  public String updateBlog(@ModelAttribute("blog") Blog theBlog) {
    blogService.updateBlog(theBlog);
    return "redirect:/myBlogs";
  }

  @GetMapping("/deleteBlog")
  public String deleteBlog(@RequestParam("blogId") int blogId) {
    blogService.deleteBlogById(blogId);

    return "redirect:/myBlogs";
  }

  @GetMapping("/home")
  public String showAllBlogs(Model theModel) {
    List<Blog> blogs = blogService.getAllBlogs();
    theModel.addAttribute("blogs", blogs);
    return "listBlogs";
  }

  @GetMapping("/likeBlog")
  public String likeBlog(@ModelAttribute("user") User theUser, @RequestParam("blogId") int blogId){
    Like theLike = new Like();

    theLike.setUser(theUser);
    theLike.setBlog(blogService.getBlogById(blogId));

    if(!likeService.likeExistsByUserAndBlog(theUser.getUserId(), blogId)) {
      likeService.saveLike(theLike);
    }
    return "redirect:/home";
  }

  @GetMapping("/commentBlog")
  public String commentBlog(Model theModel, @RequestParam("blogId") int blogId){
    Comment theComment = new Comment();
    theModel.addAttribute("comment", theComment);
    theModel.addAttribute("blog", blogService.getBlogById(blogId));
    return "showFormForCommentBlog";
  }

  @PostMapping("/processCommentBlog")
  public String processCommentBlog(@ModelAttribute("user") User theUser,
                                   @ModelAttribute("comment") Comment theComment,
                                   @ModelAttribute("blog") Blog blog){
    theComment.setUser(theUser);
    theComment.setBlog(blog);

    commentService.saveComment(theComment);

    return "redirect:/home";
  }
}
