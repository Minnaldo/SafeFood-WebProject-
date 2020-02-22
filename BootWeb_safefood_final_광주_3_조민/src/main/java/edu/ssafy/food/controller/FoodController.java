package edu.ssafy.food.controller;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import edu.ssafy.food.dto.FoodVO;
import edu.ssafy.food.dto.MemVO;
import edu.ssafy.food.service.FoodService;
import edu.ssafy.food.service.MemService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
public class FoodController {
	
	@Autowired
	@Qualifier("MemServiceImpl")
	private MemService ser;
	
	@Autowired
	@Qualifier("FoodServiceImpl")
	private FoodService foodser;
	
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/")
	public String home() {
		
		String url = servletContext.getRealPath("/WEB-INF/res/foodInfo.xml");
		String url2 = servletContext.getRealPath("/WEB-INF/res/FoodNutritionInfo.xml");
		
		String s = "";
		try {
			foodser.loadData(url, url2);
//			s = "main";
			 System.out.println("오류가 아니당");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류당ㅠㅠ");
//			s= "main";
		}finally {
			System.out.println("메인으로 가즈아");
			return "main";
		}
	}
	
	
	
	@RequestMapping("/productInfo")
	public ModelAndView productInfo(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		String id = (String)request.getSession().getAttribute("id");
		System.out.println(id+"id는");
		System.out.println(id+"id2");
		try{
			System.out.println("들어왔다");
			List<FoodVO> foodlist = foodser.getFoodList();
			MemVO mem = ser.selectOne(id);
			String tmparr = mem.getAlinfo();
			if(tmparr!=null) {
				tmparr.substring(0, tmparr.length()-2);
				System.out.println(tmparr);
				String[] alarr =tmparr.split(",");
				System.out.println(alarr.toString());
				request.setAttribute("alinfo", alarr);
			} 
	//		request.setAttribute("foodlist", foodlist);
			mv.addObject("foodlist", foodlist);
	        mv.setViewName("productInfo");
	        return mv; 
		}
		catch(Exception e){
			System.out.println("???");
			mv.setViewName("main");
			return mv;
		}
		
    }
	
	@RequestMapping("/announce")
	public ModelAndView Announce(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		System.out.println("???");
		mv.setViewName("announce");
		return mv;
		
    }
	
	@RequestMapping("/pdetail/code/{code}")
	public ModelAndView pdetail(@PathVariable("code") String code,HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		System.out.println(code);
//		List<FoodVO> foodlist = foodser.getFoodList();
		//System.out.println(foodlist.size());
		FoodVO food = new FoodVO();
		
		food = foodser.getFood(Integer.parseInt(code));
		
		System.out.println(food.getName());
		mv.addObject("food", food);
        mv.setViewName("pdetail");
        return mv; 
    }
	
	int[] calorie = new int[20];
    String[] name = new String[20];
	
	@RequestMapping("/calo")
	public ModelAndView Calo(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		int calo = Integer.parseInt(request.getParameter("calo"));
        for (int i = 1; i <= 20; i++) {
            FoodVO tmp = foodser.getFood(i);
            calorie[i - 1] = (int) Double.parseDouble(tmp.getCalory());
            name[i - 1] = tmp.getName();
        }
        int N = calorie.length;
        int maxCal = 0;
        boolean[] chk = new boolean[N];
        boolean[] finalChk = new boolean[N];
        for (int i = 0; i < (1 << N); i++) {
            int sumCal = 0;
            Arrays.fill(chk, false);
            for (int j = 0; j < N; j++) {
                if (((1 << j) & i) != 0) {
                    if (sumCal + calorie[j] <= calo) {
                        sumCal += calorie[j];
                        chk[j] = true;
                    }
                }
            }
            if (maxCal < sumCal) {
                maxCal = sumCal;
                finalChk=chk.clone();
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < finalChk.length; i++) {
            if(finalChk[i]==true) {
                sb.append(name[i]).append("<br>");
            }
        }
		mv.addObject("resultCalo", sb);
        mv.setViewName("main");
        return mv; 
	}
	
	class cnt_keyword {
        String keyword;
        int cnt;
        
        public cnt_keyword(String keyword, int cnt){
            this.keyword = keyword;
            this.cnt = cnt;
        };
    }
	
	ArrayList<cnt_keyword> list = new ArrayList<>();
	
	@RequestMapping("searchProduct")
	public ModelAndView searchProduct(HttpServletRequest request, ModelAndView mv) throws ServletException, IOException {
        // TODO Auto-generated method stub
		int cnt = 0;
		
		String st = request.getParameter("searchType");
		List<FoodVO> FoodList = null;
		String name = request.getParameter("search");
		System.out.println(st+" "+name);
		if(name.equals(""))FoodList = foodser.getFoodList();
		
		else if(st.equals("상품명")) {
			FoodList = (List<FoodVO>) foodser.getFoodnamelist(name);
		}else {
			String pcom = name;
			FoodList = (List<FoodVO>) foodser.getFoodnamelist(pcom);
		}
		//해당 단어가 들어가는 제품 수 count
		cnt = FoodList.size();
		
		//제품명 순으로 정렬
		Collections.sort(FoodList);
		
//		request.setAttribute("cnt", cnt);
//		request.setAttribute("foodlist", FoodList);
		mv.addObject("cnt", cnt);
		mv.addObject("foodlist", FoodList);
		
		//=============================================
		
		
		String keyword = request.getParameter("search");
        list.add(new cnt_keyword(keyword, 0));
        System.out.println(keyword);
        boolean chk=false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).keyword.equals(keyword)) {
                chk=true;
                list.get(i).cnt++;
                break;
            }
        }
        
        int max_cnt = Integer.MIN_VALUE;
        String str="";
        for(int i = 0; i < list.size(); i++) {
            if(max_cnt<list.get(i).cnt) {
                max_cnt=list.get(i).cnt;
                str=list.get(i).keyword;
            }
        }
        System.out.println(max_cnt);    
        request.getSession().setAttribute("key", str);//키값이 중복 "" 덮ㅇor바꾸기
        
//		request.getRequestDispatcher("productInfo.jsp").forward(request, response);
		mv.setViewName("productInfo");
		
		
//		System.out.println(code);
//		List<FoodVO> foodlist = foodser.getFoodList();
		//System.out.println(foodlist.size());
//		FoodVO food = new FoodVO();
//		
//		food = foodser.getFood(Integer.parseInt(code));
//		
//		System.out.println(food.getName());
//		mv.addObject("food", food);
//        mv.setViewName("pdetail");
        return mv; 
    }
	
	
}
