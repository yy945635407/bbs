package org.bbs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bbs.entity.History;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;

public class UserCF {
	private int maxUserNum = 10000;
	private int maxPostNum = 10000;
	private int userNum = 0;
	private int postNum = 0;
	private int targetUserId = 0; //目标用户ID
	private int simUserNum = 0; //选择的相似用户的个数
	private int[][] allUsertoPost= new int[maxUserNum][maxPostNum]; //所有用户对帖子的访问数据
	private List<List<Object>> allUserSimilarities = null;
	private List<List<Object>> recommendPosts = null;
	
	public UserCF(int userNum, int postNum,int target, int simiNum) {//初始化构造函数
		//params: userNum:总用户个数 postNum:总帖子个数 target:目标用户ID simNum:选择的相似用户个数 
		this.userNum = userNum;
		this.postNum = postNum;
		this.targetUserId = target;
		this.simUserNum = simiNum>userNum?userNum:simiNum;
		IHistoryDao historyDao = new HistoryDaoImpl();
		
		List<History> histories = historyDao.queryAllHistories();
		
		for(History history:histories){
			allUsertoPost[history.getUserID()][history.getPostID()] += 1;
		}
//		System.out.println(allUsertoPost[2][2]);
//		System.out.println(this.postNum);
//		for(int i=1;i<=this.userNum;i++){
//			for(int j=1;j<=this.postNum;j++){
//				System.out.print(allUsertoPost[i][j]);
//			}
//			System.out.println("");
//		}
		return;
	}
	
	private double calSimilarity(int[] user1, int[] user2){//计算两个用户特征向量的余弦距离
		double up=0,down1=0,down2=0;
		for(int i=1;i<=postNum;i++){
			up += user1[i]*user2[i];
			down1 += user1[i] * user1[i];
			down2 += user2[i] * user2[i];
		}
		if(down1==0||down2==0) return Double.NaN;
		return up/Math.sqrt(down1)/Math.sqrt(down2);
	}
	
	private void calAllSimilarities(){//计算所有用户与目标用户的相似度
		allUserSimilarities = new ArrayList<>();
//		System.out.println("loopsItem:"+this.userNum);
		boolean again = false;
		for(int i=1;i<=userNum;i++){
			List<Object> similarityItem = new ArrayList<> ();
			similarityItem.add(i);
			double similarity = calSimilarity(allUsertoPost[i], allUsertoPost[targetUserId]);
			if(Double.isNaN(similarity)&&!again){
				again = true;
				continue;
			}else if(Double.isNaN(similarity)&&again) {
				again = false;
				break;
			}
			similarityItem.add(similarity);
			allUserSimilarities.add(similarityItem);
		}
		
		sortListOfList(allUserSimilarities, -1);
		
//		for(List<Object> similarity:allUserSimilarities){
//			System.out.println(similarity.get(1)+"--"+similarity.get(0));
//		}
	}
	
	private void calRecommend(){//计算推荐的帖子(同时除去目标用户已经有记录的帖子)
		double recommendRate = 0;
		recommendPosts = new ArrayList<>();
		for(int i=1;i<=postNum;i++){
			if(allUsertoPost[targetUserId][i]>0) continue; //跳过目标用户已经浏览过的帖子
			List<Object> recommendPost = new ArrayList<>();
			recommendPost.add(i);
			//计算推荐度
			for(int j=0;j<simUserNum;j++){// 推荐度=相似度 * 相似用户对该帖子的历史数
				double similarity = Double.valueOf(allUserSimilarities.get(j).get(1).toString());
				int historyNum = allUsertoPost[Integer.parseInt(allUserSimilarities.get(j).get(0).toString())][i];
				recommendRate += similarity * historyNum;
			}
			if(Double.isNaN(recommendRate))	continue;
			recommendPost.add(recommendRate);
			recommendPosts.add(recommendPost);
		}
		
		sortListOfList(recommendPosts, -1);
		
//		for(List<Object> recommend:recommendPosts){
//			System.out.println(recommend.get(1));
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
	
	public int[]  recommend(int recommendNum){//推荐recommendNum个帖子(ID)
		//冷启动处理
		IHistoryDao historyDao = new HistoryDaoImpl();
		int hisCount = historyDao.getTotalCount(targetUserId);
		if(hisCount==0){
			System.out.println("UserCF is random");
			return randomRecommend(recommendNum);
		}
		
		
		calAllSimilarities(); //计算所有相似度

		if(allUserSimilarities.size()==0) {
			System.out.println("UserCF is random");
			return randomRecommend(recommendNum);
		}
		
		calRecommend(); // 计算推荐的帖子
		
		if(recommendNum>recommendPosts.size()) recommendNum = recommendPosts.size();
		int[] results = new int[recommendNum];
		for(int i=0;i<recommendNum;i++){
			try{
            results[i] = Integer.parseInt(recommendPosts.get(i).get(0).toString());  
            }catch (Exception e) {//异常结束
				System.out.println(e);
				break;
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		}
		
//		for(List<Object> i:recommendPosts){
//			System.out.println(i.get(0));
//		}
		
		allUsertoPost = null;
		allUserSimilarities = null;
		recommendPosts = null;
		
		
		return results;
	}
	
	public int[] randomRecommend(int num){
		List<Integer> rs = new ArrayList<>();
		for(int i=0;i<num;i++){
			int a = (int) (Math.random()*(postNum));
			while(rs.contains(a)||allUsertoPost[targetUserId][a]>0){
				a = (int) (Math.random()*(postNum));
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
		String string = this.userNum + "--" + this.postNum + "--" + this.targetUserId + "--" +this.simUserNum;
		return string;
	}
}
