package org.bbs.entity;

import java.util.ArrayList;
import java.util.List;

import org.bbs.util.ItemCF;
import org.bbs.util.ItemStatic;
import org.bbs.util.UserCF;

public class Recommend {
	/**
	 * @param userNum 用户数目
	 * @param postNum 帖子数目
	 * @param target 目标用户id
	 * @param simiRange 相似范围
	 * @param recommendNum 推荐数目
	 * @return 推荐的帖子ID列表
	 */
	public List<Integer> getRecommend(int userNum, int postNum, int target, int simiRange, int recommendNum){
		long startTime = System.currentTimeMillis();
		ItemCF itemCF = new ItemCF(userNum, postNum, target, simiRange);
		long initialTime1 = System.currentTimeMillis();
		System.out.println("inictialTime1:"+(initialTime1-startTime)+"ms");
//		int[] rec1 = ItemStatic.recommend(userNum, postNum, target, simiRange, recommendNum);
		int[] rec1 = itemCF.recommend(recommendNum);
	
		long endItemTime = System.currentTimeMillis();
		System.out.println("endItemTime:"+(endItemTime-startTime) +"ms");
		UserCF userCF = new UserCF(userNum, postNum, target, simiRange);
		long initialTime2 = System.currentTimeMillis();
		System.out.println("initialTime2:"+(initialTime2-endItemTime)+"ms");
		int[] rec2 = userCF.recommend(recommendNum);
//		System.out.println("itemBase:");
//		for(int i:rec1){
//			System.out.println(i);
//		}
//		System.out.println("userBase:");
//		for(int i:rec2){
//			System.out.println(i);
//		}
		long endUserTime = System.currentTimeMillis();
		System.out.println("endUserTime:"+(endUserTime-endItemTime) +"ms");
		List<Integer> result = new ArrayList<>();
		// 合并两种推荐的结果
		for(int i:rec1){
			result.add(i);
		}
		for(int i:rec2){
			if(!result.contains(i)) result.add(i);
		}
		long endCombineTime = System.currentTimeMillis();
		System.out.println("conbineTime:"+(endCombineTime-endUserTime) +"ms\n");
		return result;
	}
}
