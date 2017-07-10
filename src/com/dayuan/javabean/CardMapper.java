package com.dayuan.javabean;
import java.util.List;
import java.util.Map;

public interface CardMapper {
	public Map<String,Object> selectCardById(int param);
	
	public Card selectCardById2(int param);
	
	public List<Card> selectCardById3(Map<String,Object> map);
	
	public List<Card> selectCardById5(Map<String,Object> map);
	
	public List<Card> selectCardById6(List<Integer> list);
	
	public int insertCard(Card card);
	
	public int updateCardById(Card card);
	
	public int deleteCardById(int id);
}
