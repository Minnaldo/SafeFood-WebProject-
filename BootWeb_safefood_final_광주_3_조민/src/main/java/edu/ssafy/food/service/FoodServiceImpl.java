package edu.ssafy.food.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.ssafy.food.dto.FoodVO;
import edu.ssafy.food.repository.FoodRepo;
import edu.ssafy.food.repository.MemRepo;


@Service(value = "FoodServiceImpl")
public class FoodServiceImpl implements FoodService{

	@Autowired
	@Qualifier("FoodMybatisRepoImpl")
	private FoodRepo repo;
	
	@Override
	public String getTagValue(String tag, Element eElement) {

	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	@Override
	public List<FoodVO> productparsing1(String url) {
		
		int page = 1;	
		List<FoodVO> foodlist = new ArrayList<>();
		try{
			while(true){
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("food");
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						Element eElement = (Element) nNode;
						FoodVO vo = new FoodVO();
						vo.setCode(Integer.parseInt(getTagValue("code", eElement)));
						vo.setName(getTagValue("name", eElement));
						vo.setMaker(getTagValue("maker", eElement));
						vo.setMaterial(getTagValue("material", eElement));
						vo.setImage(getTagValue("image", eElement));
						foodlist.add(vo);
					}	
				}	
				page += 1;
				if(page > 1){	
					break;
				}
			}	// while end
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
		return foodlist;
	}
	
	
	@Override
	public List<FoodVO> productparsing2(String url) {
		
		int page = 1;	
		List<FoodVO> foodlist = new ArrayList<>();
		try{
			while(true){
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						Element eElement = (Element) nNode;
						FoodVO vo1 = new FoodVO();
					
						vo1.setSupportpereat(getTagValue("SERVING_WT", eElement));
						vo1.setCalory(getTagValue("NUTR_CONT1", eElement));
						vo1.setCarbo(getTagValue("NUTR_CONT2", eElement));
						vo1.setProtein(getTagValue("NUTR_CONT3", eElement));
						vo1.setFat(getTagValue("NUTR_CONT4", eElement));
						vo1.setSugar(getTagValue("NUTR_CONT5", eElement));
						vo1.setNatrium(getTagValue("NUTR_CONT6", eElement));
						vo1.setChole(getTagValue("NUTR_CONT7", eElement));
						vo1.setFattyacid(getTagValue("NUTR_CONT8", eElement));
						vo1.setTransfat(getTagValue("NUTR_CONT9", eElement));

						foodlist.add(vo1);
					}	
				}	
				page += 1;
				if(page > 1){	
					break;
				}
			}	// while end
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
		return foodlist;
	}


	@Override
	public void loadData(String url, String url2) throws SQLException {
		List<FoodVO> productList = new ArrayList<>();
		List<FoodVO> productListdetail = new ArrayList<>();
		url = "file:///" + url;
		productList = productparsing1(url);
		
		url2 = "file:///" + url2;
		productListdetail = productparsing2(url2);
		
		int cnt = 0;
			for(FoodVO productList2 : productList) {
				productList2.setSupportpereat(productListdetail.get(cnt).getSupportpereat());
				productList2.setCalory(productListdetail.get(cnt).getCalory());
				productList2.setCarbo(productListdetail.get(cnt).getCarbo());
				productList2.setProtein(productListdetail.get(cnt).getProtein());
				productList2.setFat(productListdetail.get(cnt).getFat());
				productList2.setSugar(productListdetail.get(cnt).getSugar());
				productList2.setNatrium(productListdetail.get(cnt).getNatrium());
				productList2.setChole(productListdetail.get(cnt).getChole());
				productList2.setFattyacid(productListdetail.get(cnt).getFattyacid());
				productList2.setTransfat(productListdetail.get(cnt).getTransfat());
				cnt++;
				
				repo.insertfood(productList2);
			}
			
			
			
	}

	@Override
	public List<FoodVO> getFoodList() {
		// TODO Auto-generated method stub
		return repo.getFoodList();
	}

	@Override
	public FoodVO getFood(int code) {
		// TODO Auto-generated method stub
		System.out.println("???");
		return repo.getFood(code);
	}

	@Override
	public List<FoodVO> getFoodnamelist(String name) {
		// TODO Auto-generated method stub
		return repo.getFoodnamelist(name);
	}
}
