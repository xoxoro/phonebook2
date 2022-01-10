package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	     
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("PhonebookController");
		
		String act = request.getParameter("action");
		
		System.out.println(act);
		
		if("list".equals(act) ) {
			System.out.println("action=list");
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//System.out.println(personList);
			
			//html 과 list  섞어서 표현해아한다
			//servlet 으로는 표현이 복잡하다 --> jsp를 이용한다
			
			request.setAttribute("pList", personList);
			
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
		
		}else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
		}else if("write".equals(act)) {
			System.out.println("action=write");
			
			//파라미터 3개의 꺼내온다
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로만든다
			PersonVo personVo = new PersonVo(name, hp, company);
			//System.out.println(personVo);
			
			//dao 메모리 올린다.
			PhoneDao phoneDao = new PhoneDao();
			
			//dao.insert(vo);
			phoneDao.personInsert(personVo);
			
			//리다이렉트      (포워드X)
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else {
			System.out.println("파라미터값 없음");
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
