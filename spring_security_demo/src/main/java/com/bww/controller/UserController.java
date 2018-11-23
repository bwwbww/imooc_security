package com.bww.controller;


import com.bww.dto.User;
import com.bww.dto.UserCondition;
import com.bww.exception.MyRuntimeException;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PutMapping(value = {"/{id}"})
    public User Update(@Valid @RequestBody User user,BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        return user;
    }

    //时间类型  后台应该是时间戳的形式传给前台  怎么显示由前台来决定
    @PostMapping
    @JsonView(User.WithAgeView.class)
    public User CreateUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user+"????");
        user.setId(1);
        return user;
    }

    //    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping
    @JsonView(User.WithoutAgeView.class)
    public List<User> QueryUsers(@RequestParam(name = "username", required = false) String name, UserCondition condition, @PageableDefault(size = 2, page = 3, sort = "age", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println(pageable.getPageSize() + "..." + pageable.getPageNumber() + "..." + pageable.getSort());
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        System.out.println(name);
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //可以使用正则表达式 限制使用的ID的；类型
    @GetMapping(value = "/{id}")
    @JsonView(User.WithAgeView.class)
    public User QueryById(@PathVariable Integer id) {
        User user = new User();
        user.setName("tom");
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        System.out.println(id);

    }

    //测试异常处理机制
    @GetMapping(value = "/exception/{id}")
    @JsonView(User.WithAgeView.class)
    public User QueryByIdWithException(@PathVariable Integer id) {
//        throw new RuntimeException("error。。。。");
          throw  new MyRuntimeException(id);
    }
}
