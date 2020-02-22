package edu.ssafy.food.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.food.dto.AnnounceVO;
import edu.ssafy.food.dto.FoodVO;
import edu.ssafy.food.dto.MemVO;
import edu.ssafy.food.dto.QnaVO;
import edu.ssafy.food.service.AnnounceService;
import edu.ssafy.food.service.FoodService;
import edu.ssafy.food.service.MemService;
import edu.ssafy.food.service.QnaService;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class RestSafeController {

	@Autowired
	@Qualifier("MemServiceImpl")
	MemService ser;

	@Autowired
	@Qualifier("FoodServiceImpl")
	FoodService foodser;
	
	
	@Autowired
	@Qualifier("QnaServiceImpl")
	QnaService qnaser;
	
	@Autowired
	@Qualifier("AnnounceServiceImpl")
	AnnounceService announceser;
	
	
	@Autowired
	private ServletContext servletContext;
	
	@PostMapping("/signup")
	public ResponseEntity<String> insert(@RequestBody MemVO mem, HttpServletRequest request) {
		System.out.println(mem.toString());
		ResponseEntity re = null;
		System.out.println("들어왔니?");
		try {
			ser.addMem(mem.getId(), mem.getPw(), mem.getName(),mem.getAddr(), mem.getEmail(),mem.getTel(),mem.getAlinfo());
			re = new ResponseEntity<String>("잘들어갔어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("입력실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	@GetMapping("/findpw/{id}/{name}/{tel}")
	public ResponseEntity<MemVO> searchpw(@PathVariable String id,@PathVariable String name,@PathVariable String tel) {
		ResponseEntity<MemVO> re = null;
		try {
			MemVO selectOne = ser.selectOne(id);
			if(!selectOne.getName().equals(name)) {
				selectOne.setPw("");
			}
			re = new ResponseEntity<MemVO>(selectOne, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	@PutMapping("/memupdate")
	public ResponseEntity<String> update(@RequestBody MemVO mem) {
		ResponseEntity re = null;
		try {
			ser.updateMem(mem.getId(), mem.getPw(), mem.getName(),mem.getAddr(), mem.getEmail(),mem.getTel(),mem.getAlinfo());
			System.out.println(mem.toString());
			re = new ResponseEntity<String>("잘수정되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("수정실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	@PutMapping("/replyannounce/{anum}/{areply}")
	public ResponseEntity<String> replyannounce(@PathVariable String anum, @PathVariable String areply) {
		ResponseEntity<String> re = null;
		System.out.println("replyannounce 들어왔니?");
		try {
			//System.out.println(ser.selectOne(id).getLikefood()+" 을 이어붙여");
			System.out.println("replyannounce 수정이이ㅑ 들어왔니?");
			
			AnnounceVO vo = announceser.getAnnounce(anum);
			vo.setAreply(vo.getAreply()+","+ areply);
			//announceser.updateAnnounce(anum, announceser.getAnnounce(anum).getAtitle(), announceser.getAnnounce(anum).getAwriter(), announceser.getAnnounce(anum).getAcontent(), announceser.getAnnounce(anum).getAdate(), announceser.getAnnounce(anum).getAcnt(), announceser.getAnnounce(anum).getAlike(), areply);
			updateAnnounce(vo);
			
			System.out.println("민오빠가 자고있어요"+vo.toString());
			re = new ResponseEntity<String>("잘수정되었어요", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("replyannounce 들어왔니? 실패야");
			re = new ResponseEntity<String>("수정실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	
	@PutMapping("/updatelike/{id}/{foodlike}")
	public ResponseEntity<String> updatelike(@PathVariable String id, @PathVariable String foodlike) {
		System.out.println(id+" 찜"+ foodlike);
		ResponseEntity<String> re = null;
		
		try {
			//System.out.println(ser.selectOne(id).getLikefood()+" 을 이어붙여");
			System.out.println(ser.selectOne(id).toString());
			if(!ser.selectOne(id).getLikefood().contains(","+foodlike+","))
				ser.updatelike(id, ser.selectOne(id).getLikefood()+foodlike+",");
			System.out.println(foodlike);
			re = new ResponseEntity<String>("잘수정되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("수정실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	@DeleteMapping("/memdelete/{id}")
	public ResponseEntity delete(@PathVariable String id) {
		ResponseEntity re = null;
		try {
			ser.delMem(id);
			re = new ResponseEntity<String>("잘삭제되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("삭제실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}

	@GetMapping("/meminfo/{id}")
	public ResponseEntity<MemVO> selectOne(@PathVariable String id) {
		ResponseEntity<MemVO> re = null;
		try {
			MemVO selectOne = ser.selectOne(id);
			re = new ResponseEntity<MemVO>(selectOne, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}

	@GetMapping("/memsellist")
	public ResponseEntity<List<MemVO>> selectList() {
		System.out.println("");
		ResponseEntity<List<MemVO>> re = null;
		try {
			List<MemVO> list = ser.selectList();
			re = new ResponseEntity<List<MemVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	@GetMapping("/login/{id}/{pw}")
	public ResponseEntity<MemVO> login(@PathVariable String id,@PathVariable String pw, HttpServletRequest request) {
		ResponseEntity<MemVO> re = null;
		boolean isLogin = ser.isLogin(id, pw);

		try {
			if (isLogin) {
				//세션에 로그인 저장
				request.getSession().setAttribute("islogin", "isLogin");
				request.getSession().setAttribute("id",id);
				MemVO log = ser.login(id, pw);
				re = new ResponseEntity<MemVO>(log, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			re = new ResponseEntity("로그인실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	
	@GetMapping("/logincheck/{pid}/{ppw}")
	public @ResponseBody ResponseEntity<Map<String,Object>> login(@PathVariable("pid") String pid, @PathVariable("ppw") String ppw) {
	   ResponseEntity<Map<String,Object>> re = null;
	   System.out.println(pid+"얘 로그인 되어있니?");
	   // 로그인 체크
	   boolean result = ser.isLogin(pid, ppw);
	      
	   if (result) {
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("message", "true");
	      re = new ResponseEntity(map, HttpStatus.OK);
	   } else {
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("message", "false");
	      re = new ResponseEntity(map, HttpStatus.OK);
	   }
	   return re;
	}
	
	@PostMapping("/")
	public ResponseEntity<String> Main() {
		System.out.println("gooooooood");
		ResponseEntity re = null;
		String url = servletContext.getRealPath("/WEB-INF/res/foodInfo.xml");
		String url2 = servletContext.getRealPath("/WEB-INF/res/FoodNutritionInfo.xml");
		
		try {
			foodser.loadData(url, url2);
			re = new ResponseEntity<String>("잘들어갔어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("입력실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;

		
	}
	
	@GetMapping("/pdetail/code/{code}")
	public ResponseEntity<FoodVO> Pdetail(@PathVariable("code") String code) {
		ResponseEntity<FoodVO> re = null;
		System.out.println(code);
		
		FoodVO food = new FoodVO();
		System.out.println(food.getName());
		food = foodser.getFood(Integer.parseInt(code));
		re = new ResponseEntity<FoodVO>(food, HttpStatus.OK);
		return re;

	}
	
	@GetMapping("/foodinfo")
	public ResponseEntity<List<FoodVO>> productlist() {
		System.out.println("");
		ResponseEntity<List<FoodVO>> re = null;
		try {
			List<FoodVO> list = foodser.getFoodList();
			re = new ResponseEntity<List<FoodVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}

	@GetMapping("/likefoodinfo/{id}")
	public ResponseEntity<List<FoodVO>> likeproductlist(@PathVariable("id") String id) {
		System.out.println("dmdmdmdmdm");
		ResponseEntity<List<FoodVO>> re = null;
		System.out.println(id+" like목록은?");
		try {
			List<FoodVO> list = new ArrayList<>();
			for (FoodVO vo : foodser.getFoodList()) {
				if(ser.selectOne(id).getLikefood().contains(","+Integer.toString(vo.getCode())+",")) {
					list.add(vo);
				}
			}
			re = new ResponseEntity<List<FoodVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	
	@GetMapping("/searchfoodinfo/{search}")
	public ResponseEntity<List<FoodVO>> searchproductlist(@PathVariable("search") String search) {
		ResponseEntity<List<FoodVO>> re = null;
		try {
			List<FoodVO> list = new ArrayList<>();
			for (FoodVO vo : foodser.getFoodList()) {
				if(vo.getName().contains(search) || vo.getMaker().contains(search)) {
					list.add(vo);
				}
			}
			re = new ResponseEntity<List<FoodVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	/** -------------공지사항(announce) start 부분------------------ **/
	@GetMapping("/announcelist")
	public ResponseEntity<List<AnnounceVO>> announcelist() {
		System.out.println("");
		ResponseEntity<List<AnnounceVO>> re = null;
		try {
			List<AnnounceVO> list = announceser.getAnnounceList();
			re = new ResponseEntity<List<AnnounceVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}

	@PostMapping("/announceadd")
	public ResponseEntity<String> insertAnnounce(@RequestBody AnnounceVO announce) {
		ResponseEntity re = null;
		System.out.println("공지사항에 들어갔니?");
		try {
			announceser.addAnnounce(announce.getAnum(), announce.getAtitle(), announce.getAwriter(), announce.getAcontent(), announce.getAdate(), announce.getAcnt(), announce.getAlike(), announce.getAreply());
			System.out.println(announce.toString());
			re = new ResponseEntity<String>("잘 들어갔어요", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("실패래");
			re = new ResponseEntity<String>("입력 실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}

	@PutMapping("/announceupdate/{anum}")
	public ResponseEntity<String> updateAnnounce(@RequestBody AnnounceVO announce) {
		ResponseEntity re = null;
		
		System.out.println(announce.toString());
		try {
			announceser.updateAnnounce(announce.getAnum(), announce.getAtitle(), announce.getAwriter(), announce.getAcontent(), announce.getAdate(), announce.getAcnt(), announce.getAlike(), announce.getAreply());
			re = new ResponseEntity<String>("잘수정되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("수정실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	
	@DeleteMapping("/announcedelete/{anum}")
	public ResponseEntity deleteAnnounce(@PathVariable String anum) {
		ResponseEntity re = null;
		try {
			announceser.deleteAnnounce(anum);
			re = new ResponseEntity<String>("잘삭제되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("삭제 실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}

	@GetMapping("/announcedetail/{anum}")
	public ResponseEntity<AnnounceVO> selectOne1_Announce(@PathVariable String anum) {
		ResponseEntity<AnnounceVO> re = null;
		try {
			AnnounceVO selectOne = announceser.getAnnounce(anum);
			int tmp = Integer.parseInt(selectOne.getAcnt());
			tmp++;
			selectOne.setAcnt(Integer.toString(tmp));
			announceser.updateAnnounce(selectOne.getAnum(), selectOne.getAtitle(), selectOne.getAwriter(), selectOne.getAcontent(), selectOne.getAdate(), Integer.toString(tmp), selectOne.getAlike(), selectOne.getAreply());
			re = new ResponseEntity<AnnounceVO>(selectOne, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	/** ----------------공지사항(announce) end 부분------------------ **/
	
	/** ----------------QnA(qna) start 부분------------------ **/
	@GetMapping("/qnalist")
	public ResponseEntity<List<QnaVO>> qnalist() {
		System.out.println("");
		ResponseEntity<List<QnaVO>> re = null;
		try {
			List<QnaVO> list = qnaser.getQnaList();
			re = new ResponseEntity<List<QnaVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	
	@PostMapping("/qnaadd")
	public ResponseEntity<String> insert(@RequestBody QnaVO qna) {
		ResponseEntity re = null;
		try {
			qnaser.addQna(qna.getNum(), qna.getImg(), qna.getTitle(),qna.getName(), qna.getStatus(), qna.getDate(), qna.getContent());
			re = new ResponseEntity<String>("잘들어갔어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("입력실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	
	
	@DeleteMapping("/qnadel/{num}")
	public ResponseEntity delete1(@PathVariable String num) {
		ResponseEntity re = null;
		try {
			qnaser.delQna(num);
			re = new ResponseEntity<String>("잘삭제되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("삭제실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	
	
	@PutMapping("/qnaupdate")
	public ResponseEntity<String> update(@RequestBody QnaVO qna) {
		ResponseEntity re = null;
		
		System.out.println(qna.toString());
		try {
			qnaser.updateQna(qna.getNum(), qna.getImg(), qna.getTitle(), qna.getName(), qna.getStatus(), qna.getDate(), qna.getContent());
			re = new ResponseEntity<String>("잘수정되었어요", HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("수정실패했어요", HttpStatus.NOT_MODIFIED);
		}
		return re;
	}
	

	
	@GetMapping("/qnadetail/{num}")
	public ResponseEntity<QnaVO> selectOne1(@PathVariable String num) {
		ResponseEntity<QnaVO> re = null;
		try {
			QnaVO selectOne = qnaser.getQna(num);
			re = new ResponseEntity<QnaVO>(selectOne, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity("조회실패했어요", HttpStatus.NO_CONTENT);
		}
		return re;
	}
	/** ----------------QnA(qna) end부분------------------ **/
	
	@GetMapping("/Logout")
	public void Logout() {
	}
	
	@GetMapping("/searchProduct")
	public void searchProduct() {
	}
	
	@GetMapping("/date")
	public void date() {
	}
	
	@GetMapping("/calo")
	public void calo() {
	}
	
	
	
	@GetMapping("/shoppingbox")
	public void shoppingbox() {
	}
	
	
	
}
