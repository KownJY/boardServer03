package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/upd")
public class UpdBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<BoardVO> list = BoardDAO.selBoardList();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(list);

        //브라우저 한글깨짐 방지
        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        //

        PrintWriter out = res.getWriter();
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Utils.getJson(req);
        Gson gson = new Gson();
        BoardVO param = gson.fromJson(json,BoardVO.class);
        int result = BoardDAO.updBoard(param);
    }
}
