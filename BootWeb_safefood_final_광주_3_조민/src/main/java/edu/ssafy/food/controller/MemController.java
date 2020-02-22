package edu.ssafy.food.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.food.dto.MemVO;
import edu.ssafy.food.service.MemService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
public class MemController {
	@Autowired
	@Qualifier("MemServiceImpl")
	private MemService ser;
	
	@RequestMapping("/signUp")
	public ModelAndView insert(HttpServletRequest request, ModelAndView mv) {
		try {
			// 입력처리
			
			String id = request.getParameter("pid");
	        String pw = request.getParameter("ppw1");
	        String pw2 = request.getParameter("ppw2");
	        String name = request.getParameter("pname");
	        String addr = request.getParameter("paddr");
	        String email1=request.getParameter("pemail1");
	        String email2=request.getParameter("pemail2");
	        System.out.println(id);
	        String email=email1+"@"+email2;
	        
	        String tel1=request.getParameter("ptel1");
	        String tel2=request.getParameter("ptel2");
	        String tel3=request.getParameter("ptel3");
	        
	        String tel=tel1+"-"+tel2+"-"+tel3;
	        
	        String[] value = request.getParameterValues("hobby");

	        String alinfo = "";
	        for(String tmp : value) {
	        	alinfo += tmp + ",";
	        }
	        
//			String id = req.getParameter("id");
//			String pw = req.getParameter("pw");
//			String name = req.getParameter("name");
//			String email = req.getParameter("email");
			// 로직처리
			ser.addMem(id, pw, name, addr,email,tel,alinfo);
			System.out.println(id+" "+pw);
		} catch (Exception e) {
			// TODO: handle exception
//			exceptionMethod(e, (Model) mv.getModel());
		} finally {
			// TODO: handle finally clause
		}
		// 결과처리
//		List<MemVO> list = ser.selectList();

//		mv.addObject("list", list);
		mv.setViewName("main");
		
		return mv;
	}
	

	@RequestMapping("/Login")
	public ModelAndView Login(HttpServletRequest request, ModelAndView mv) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String id = request.getParameter("pid");
		String pw = request.getParameter("ppw");
		System.out.println(id+" "+pw);
		boolean isLogin = ser.isLogin(id, pw);
		
		if (isLogin) {
			//세션에 로그인 저장
			request.getSession().setAttribute("islogin", "isLogin");
			request.getSession().setAttribute("id",id);
			mv.setViewName("main");
			return mv;
		} else {
//			 request.getRequestDispatcher("mypage.jsp").forward(request, response);
			mv.setViewName("main");
			return mv;
		}
	}
	
	@RequestMapping("/memInfo")
	public ModelAndView memInfo(HttpServletRequest request, ModelAndView mv) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String id = (String) request.getSession().getAttribute("id");
		
		MemVO mem =  ser.selectOne(id);
//		request.setAttribute("info", mem);
		mv.addObject("info", mem);
		mv.setViewName("mypage");
		return mv;
	}
	
	@RequestMapping("/Update")
	public ModelAndView Update(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String id = request.getParameter("pid");
        String pw = request.getParameter("ppw");
        String name = request.getParameter("pname");
        String addr = request.getParameter("paddr");
        String email = request.getParameter("pemail");
        String tel = request.getParameter("ptel");
        String alinfo = request.getParameter("pal");
        ser.updateMem(id, pw, name, addr, email, tel, alinfo);
        mv.setViewName("mypage");
        return mv; 
    }
	
	@RequestMapping("/Delete/pid/{pid}")
    public ModelAndView Delete(@PathVariable("pid") String id, HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        
        System.out.println(id);
        ser.delMem(id);
        
        String msg = "success";
		
		
		mv.addObject("msg", msg);
		request.getSession().invalidate();
		
		mv.setViewName("redirect:/");
		return mv;
    }
	
	@RequestMapping("/Logout")
	public ModelAndView Logout(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		System.out.println("들오와따");
		request.getSession().invalidate();
        mv.setViewName("main");
        return mv; 
    }
	
	@RequestMapping("findpw")
	public ModelAndView Findpw(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		System.out.println("???");
		mv.setViewName("findpw");
		return mv;
		
    }
	
	@RequestMapping("searchpw")
	public ModelAndView searchpw(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		 	String id = request.getParameter("pid");
	        String name = request.getParameter("pname");
//	        MemVO mem = ser.selectOne(id);
//	        String mail = ser.searchmail(id, name);
	        String pw = ser.searchpw(id, name);
//	        System.out.println(mail +" "+pw);
//	        
//	       Properties prop = System.getProperties();
//	       prop.put("mail.smtp.starttls.enable", "true");
//	       prop.put("mail.smtp.host", "smtp.gmail.com");
//	       prop.put("mail.smtp.auth", "true");
//	       prop.put("mail.smtp.port", "587");
//	       Authenticator auth = new MailAuth();
//	       Session session = Session.getDefaultInstance(prop, auth);
//	       MimeMessage msg = new MimeMessage(session);
//	       
//	       
//	       try {
//	           msg.setSentDate(new Date());
//	           msg.setFrom(new InternetAddress("shxorld@gmail.com", "safeFood"));
//	           InternetAddress to = new InternetAddress(mem.getEmail());
//	           msg.setRecipient(Message.RecipientType.TO, to);
//	           msg.setSubject(name+"님의 임시 비밀번호 안내", "UTF-8");
//	           msg.setText("임시 비밀번호는 1234입니다.", "UTF-8");
//	           ser.updateMem(mem.getId(), "1234", mem.getName(), mem.getAddr(), mem.getEmail(), mem.getTel(), mem.getAlinfo());
//	           Transport.send(msg);
//	       } catch(AddressException ae) {
//	           System.out.println("AddressException : " + ae.getMessage());
//	       } catch(MessagingException me) {
//	           System.out.println("MessagingException : " + me.getMessage());
//	       } catch(UnsupportedEncodingException e) {
//	           System.out.println("UnsupportedEncodingException : " + e.getMessage());            
//	       }
//	       
//	       String str = mem.getName()+ "님의 이메일로 임시 비밀번호가 전송되었습니다.";
//	       
////	        request.setAttribute("name", str);/*
////	        request.setAttribute("email", mv.getEmail());*/
////	        request.getRequestDispatcher("findpw.jsp").forward(request, response);
		
		System.out.println(pw);
		mv.addObject("pw", pw);
		mv.setViewName("tmp");
		return mv;
	}
}
