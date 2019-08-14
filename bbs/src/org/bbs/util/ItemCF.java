package org.bbs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bbs.entity.History;

import org.bbs.dao.*;
import org.bbs.dao.impl.*;
public class ItemCF {
	private int maxUserNum = 10000;
	private int maxPostNum = 10000;
	private int userNum = 0;
	private int postNum = 0;
	private int targetUserId = 0; //目标用户ID
	private int simItemNum = 0; //选择的相似用户的个数
	private int[][] allPosttoUser= new int[maxPostNum][maxUserNum]; //所有帖子的用户访问数据
	private List<List<Object>> allPostSimilarities = null;
	private List<Integer> targetUserHasViewed = null;
	private List<List<Object>> recommendPosts = null;
	
	public ItemCF(int userNum, int postNum,int target, int simiNum) {//初始化构造函数
		//params: userNum:总用户个数 postNum:总帖子个数 target:目标用户ID simNum:选择的相似用户个数 
		long beforeQuery = System.currentTimeMillis();
		this.userNum = userNum;
		this.postNum = postNum;
		this.targetUserId = target;
		this.simItemNum = simiNum>userNum?userNum:simiNum;
		
		IHistoryDao historyDao = new HistoryDaoImpl();
		
		List<History> histories = historyDao.queryAllHistories();
		int i=0;
		long startInitTime = System.currentTimeMillis();
		System.out.println("queryTime:"+(startInitTime - beforeQuery) +"ms");
		targetUserHasViewed = new ArrayList<>();
		for(History history:histories){
			allPosttoUser[history.getPostID()][history.getUserID()] += 1;
			if(history.getUserID()==target){//记录目标用户浏览过的帖子
				if(!targetUserHasViewed.contains(history.getPostID()))	{
//					System.out.println("user "+targetUserId+" has viewed "+"post "+history.getPostID());
					targetUserHasViewed.add(history.getPostID());
				}
			}
		}
		
//		System.out.println("User has viewed "+targetUserHasViewed.size()+" posts");
//		System.out.println(allPosttoUser[2][2]);
//		System.out.println(this.postNum);
//		for(int i=1;i<=this.userNum;i++){
//			for(int j=1;j<=this.postNum;j++){
//				System.out.print(allPosttoUser[i][j]);
//			}
//			System.out.println("");
//		}
		long endFor = System.currentTimeMillis();
		System.out.println("endFor:"+(endFor - startInitTime)+"ms");
		return;
	}
	
	private double calSimilarity(int[] post1, int[] post2){//计算两个帖子特征向量的余弦距离
		double up=0,down1=0,down2=0;
		for(int i=1;i<=this.userNum;i++){
			up += post1[i]*post2[i];
			down1 += post1[i] * post1[i];
			down2 += post2[i] * post2[i];
			
		}
//		return 1;
//		return up/Math.sqrt(down1*down2);
//		System.out.println(up+"--"+down1+"--"+down2);
		if(down1==0||down2==0) return Double.NaN;
		return up/Math.sqrt(down1)/Math.sqrt(down2);
	}
	
	private void calAllSimilarities(){//计算所有帖子与目标用户浏览过的帖子的相似度
		allPostSimilarities = new ArrayList<>();
//		System.out.println("loopsItem:"+(targetUserHasViewed.size()*this.postNum));
		for(int j=0;j<targetUserHasViewed.size();j++){//计算所有帖子与目标用户浏览过的帖子的相似度
			boolean again = false;
			for(int i=1;i<=this.postNum;i++){
				List<Object> similarityItem = new ArrayList<> ();
				similarityItem.add(i); //待推荐帖子ID
				double similarity = calSimilarity(allPosttoUser[i], allPosttoUser[targetUserHasViewed.get(j)]);
				if(Double.isNaN(similarity)&&!again){
					again = true;
					continue;
				}else if(Double.isNaN(similarity)&&again) {
					break;
				}
				similarityItem.add(similarity);
				similarityItem.add(targetUserHasViewed.get(j)); // 用户浏览过的帖子ID
				allPostSimilarities.add(similarityItem);
//				System.out.println(1);
			}
		}
		
		
		sortListOfList(allPostSimilarities, -1);
		
//		for(List<Object> similarity:allPostSimilarities){
//			System.out.println(similarity.get(0)+"--"+similarity.get(1));
//		}
	}
	
	private void calRecommend(){//计算推荐的帖子(同时除去目标用户已经有记录的帖子)
		double recommendRate = 0;
		recommendPosts = new ArrayList<>();
		for(int i=1;i<=this.postNum;i++){
			if(allPosttoUser[i][targetUserId]>0) continue; //跳过目标用户已经浏览过的帖子
			List<Object> recommendPost = new ArrayList<>();
			recommendPost.add(i);
			//计算推荐度
//			System.out.println(allPostSimilarities.size());
			for(int j=0;j<simItemNum;j++){// 推荐度=相似度（待推荐帖子a与浏览过的帖子b） * 目标用户对该帖子（b）的历史数
				double similarity = Double.valueOf(allPostSimilarities.get(j).get(1).toString());
				int historyNum = allPosttoUser[i][Integer.parseInt(allPostSimilarities.get(j).get(2).toString())];
				recommendRate += similarity * historyNum;
			}
			recommendPost.add(recommendRate);
			if(Double.isNaN(recommendRate))	continue;
			recommendPost.add(recommendRate);
			recommendPosts.add(recommendPost);
		}
		
		sortListOfList(recommendPosts, -1);
		
//		for(List<Object> recommend:recommendPosts){
//			System.out.println(recommend.get(0)+"--"+recommend.get(1));
//		}
	}
	
	private void sortListOfList(List<List<Object>> list,int order){//二维列表排序函数
        Collections.sort(list, new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                double compareResult = order * (Double.valueOf(o1.get(1).toString()) - Double.valueOf(o2.get(1).toString()));
                if(compareResult>0) return 1;
                else if(compareResult<0) return -1;
                else return 0;
            }
        });
    }
	
	public int[] recommend(int recommendNum){//推荐recommendNum个帖子(ID)
		//冷启动处理
		IHistoryDao historyDao = new HistoryDaoImpl();
		int hisCount = historyDao.getTotalCount(this.targetUserId);
		if(hisCount==0){
			System.out.println("ItemCF is random");
			return randomRecommend(recommendNum);
		}
//		long beforeCal = System.currentTimeMillis();
		calAllSimilarities(); //计算所有相似度
//		long calAll = System.currentTimeMillis();
//		System.out.println("calAll:"+ (calAll-beforeCal)+"ms");
		
		if(allPostSimilarities.size()==0) {
			System.out.println("ItemCF is random");
			return randomRecommend(recommendNum);
			}
		
		calRecommend(); // 计算推荐的帖子
//		long recAll = System.currentTimeMillis();
//		System.out.println("recAll:"+ (recAll-calAll)+"ms");
//		System.out.println(recommendPosts.size());
		if(recommendNum>recommendPosts.size()) recommendNum = recommendPosts.size();
		int[] results = new int[recommendNum];
		for(int i=0;i<recommendNum;i++){
			try {
				results[i] = Integer.parseInt(recommendPosts.get(i).get(0).toString());   
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		}
//		long selectAll = System.currentTimeMillis();
//		System.out.println("selectAll:"+ (selectAll - recAll)+"ms");
//		for(List<Object> i:recommendPosts){
//			System.out.println(i.get(0));
//		}
//		for(int i:results){
//			System.out.println(i);
//		}
		
		allPosttoUser = null;
		allPostSimilarities = null;
		targetUserHasViewed = null;
		recommendPosts = null;
		
		return results;
	}
	
	public int[] randomRecommend(int num){
		List<Integer> rs = new ArrayList<>();
		for(int i=0;i<num;i++){
			int a = (int) (Math.random()*(this.postNum));
			while(rs.contains(a)||targetUserHasViewed.contains(a)){
				a = (int) (Math.random()*(this.postNum));
			}
			rs.add(a);
		}
		
		int[] result = new int[num];
		for(int i=0;i<num;i++){
			result[i] = rs.get(i);
		}
		return result;
	}
	
	@Override
	public String toString() {
		String string = this.userNum + "--" + this.postNum + "--" + this.targetUserId + "--" +this.simItemNum;
		return string;
	}
}
