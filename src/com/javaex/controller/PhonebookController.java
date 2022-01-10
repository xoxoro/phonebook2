package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")//이게 주소값
public class PhonebookController extends HttpServlet {
	
    public PhonebookController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("PhonebookController");
		
		String act = request.getParameter("action");
		
		System.out.println(act);
		
		if("list".equals(act)) {
			System.out.println("action=list");
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			System.out.println(personList);//(personList.toString());이랑 같음 주소만 쓰면 알아서 투스트링 찾아감.
			
			request.setAttribute("pList", personList);//어트리뷰트 영역에 별명,실제데이터는 personList
			
			//포워드 메소드 걍 외워.
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
		}else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		}else if("write".equals(act)) {
			System.out.println("action=write");
			
			//파라미터 3개의 값을 꺼내온다
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 만든다
			PersonVo personVo = new PersonVo(name,hp,company);
			System.out.println(personVo);
			
			//dao메모리를 올린다.
			PhoneDao phoneDao = new PhoneDao();
			
			//dao.insert(vo);
			phoneDao.personInsert(personVo);
			
			//리다이렉트  (포워드x)
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else {
			System.out.println("파라미터 값 없음");
		}
	
	}		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
