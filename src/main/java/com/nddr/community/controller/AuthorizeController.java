package com.nddr.community.controller;
//认证控制器

import com.nddr.community.dto.AccessTokenDTO;
import com.nddr.community.dto.GithubUser;
import com.nddr.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    private AccessTokenDTO accessTokenDTO;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){
        accessTokenDTO.setClient_id("Iv1.694c2cf213a57f85");
        accessTokenDTO.setClient_secret("58f0e2e92dd53ce0a396ff76df349e8e7c5f01ba");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }
}
