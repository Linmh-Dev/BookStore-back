package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Response;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
public class ValidateCodeController {
    private final Random random = new Random();
    public static final String VALIDATE_CODE_KEY = "VALIDATE_CODE";

    @SneakyThrows
    @GetMapping("/validateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        int width = 120;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null, Font.BOLD, 38));
        String code = String.valueOf(random.nextInt(10000-1000)+1000);
        HttpSession session = request.getSession();
        session.setAttribute(VALIDATE_CODE_KEY, code);
        graphics.drawString(code, 10, height - 5);
        ImageIO.write(bufferedImage, "png", out);
        out.close();

    }

    @ResponseBody
    @PostMapping("/validateCode")
    public Object verifyCode(@RequestBody String code,HttpServletRequest request) {
        String validateCode = (String) request.getSession().getAttribute(VALIDATE_CODE_KEY);
        if (validateCode !=null && validateCode.equals(code)) {
            return Response.success();
        }
        return false;
    }
}
