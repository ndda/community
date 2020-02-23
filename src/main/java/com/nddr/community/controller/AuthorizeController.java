package com.nddr.community.controller;
//认证控制器，所有和登录有关的控制机制都放在这

import com.nddr.community.dto.AccessTokenDTO;
import com.nddr.community.dto.GithubUser;
import com.nddr.community.mapper.UserMapper;
import com.nddr.community.model.User;
import com.nddr.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    //倚赖注入
    @Autowired //不需要传入数据给变量的类才能使用此注解
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){

        //给AccessTokenDTO赋值
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);

        //调用githubProvider给github发送accessTokenDTO并接收accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        //向github发送accessToken接收user信息
        GithubUser githubUser = githubProvider.getUser(accessToken);


        if(githubUser!=null){
            //登录成功，写cookie和session

            
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModefied(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/index";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
