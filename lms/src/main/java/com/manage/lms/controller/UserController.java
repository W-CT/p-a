package com.manage.lms.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.manage.lms.pojo.User;
import com.manage.lms.pojo.VoUserInfo;
import com.manage.lms.service.IUserService;
import com.manage.lms.utils.CheckCodeUtil;
import com.manage.lms.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {
    //处理登录请求
    @Autowired
    private IUserService userService;
    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody String userJson,HttpSession session){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        session = request.getSession();
        JSONObject object= JSONObject.parseObject(userJson);

        String userName = String.valueOf(object.get("userName"));
        String password = String.valueOf(object.get("password"));
        String checkCode = String.valueOf(object.get("checkCode"));
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        System.out.println(checkCodeGen);

        if (!checkCodeGen.equalsIgnoreCase(checkCode)){
            return ResponseUtil.error("验证码错误");
        }
        if (StringUtils.isEmpty(userService.selectUserName(userName))){
            userService.register(userName, password);
            return ResponseUtil.success(userJson);
        }else {
            return ResponseUtil.error("已存在该用户名");
        }
    }
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public void checkCode() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpSession session = request.getSession();
        // 生成验证码
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);

        // 存入Session
        session.setAttribute("checkCodeGen",checkCode);
    }
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName())) return ResponseUtil.error("用户名不能为空");
        if (StringUtils.isEmpty(user.getPassword())) return ResponseUtil.error("密码不能为空");
        String resStr = "";
        try {
            User voUser = userService.login(user.getUserName(), user.getPassword());
            if (voUser == null){
                return ResponseUtil.error("账号或密码不正确");
            }else if (voUser.getRole().equals(1)){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", "a123456789"); //token在此先暂时随便定义
                resStr = ResponseUtil.success(jsonObject);
            }else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", "abc123456"); //token在此先暂时随便定义
                resStr = ResponseUtil.success(jsonObject);
            }


        } catch (Exception e) {
            resStr = ResponseUtil.error("失败：" + e.getMessage());
            e.printStackTrace();
        }
        return resStr;

    }

    //传输登录token值和用户信息
    @RequestMapping(value = "/info", method = RequestMethod.GET)//会有x-token的请求头，需在MVC.XML配置
    public String getInfo(
            @RequestParam(value = "token") String token
    ) {
        System.out.println(token);
        if (StringUtils.isEmpty(token)) return ResponseUtil.error("token不能为空");
        String resStr = "";
        try {
            //验证token 有效合法
            if ("a123456789".equals(token)) { //List<String> role, String introduction, String avatar, String name
                VoUserInfo voUserInfo = new VoUserInfo();
                ArrayList<String> roleList = new ArrayList<>();
                roleList.add("admin");
                voUserInfo.setRole(roleList);
                voUserInfo.setName("admin");
                voUserInfo.setIntroduction("testAdmin");
                voUserInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                System.out.println(voUserInfo.toString());
                resStr = ResponseUtil.success(voUserInfo);
            }else if("abc123456".equals(token)){
                VoUserInfo voUserInfo1 = new VoUserInfo();
                ArrayList<String> roleList = new ArrayList<>();
                roleList.add("user");
                voUserInfo1.setRole(roleList);
                voUserInfo1.setName("user");
                voUserInfo1.setIntroduction("testUser");
                voUserInfo1.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                System.out.println(voUserInfo1.toString());
                resStr = ResponseUtil.success(voUserInfo1);
            }else {
                resStr = ResponseUtil.error("token无效");
            }
        } catch (Exception e) {
            resStr = ResponseUtil.error("失败：" + e.getMessage());
            e.printStackTrace();
        }
        return resStr;
    }

    //注销
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@RequestHeader(value = "X-Token") String token) {
        if (StringUtils.isEmpty(token)) return ResponseUtil.error("退出失败，token无效");
        String resStr = "";
        try {
            //验证清除toeken
            resStr = ResponseUtil.success("退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            resStr = ResponseUtil.error("退出失败" + e);
        }
        return resStr;


    }
}
