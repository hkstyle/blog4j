package com.blog4j.auth.api;

import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.service.AuthService;
import com.blog4j.auth.service.CaptchaService;
import com.blog4j.auth.vo.req.LoginReqVo;
import com.blog4j.common.model.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 认证接口
 * @Create on : 2024/6/20 12:45
 **/
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginReqVo loginReqVo) {
        LoginContext loginContext = new LoginContext();
        BeanUtils.copyProperties(loginReqVo, loginContext);
        authService.login(loginContext);
        return Result.ok(loginContext.getSaTokenInfo());
    }

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
}
