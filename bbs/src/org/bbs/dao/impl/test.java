package org.bbs.dao.impl;

import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.catalina.startup.UserDatabase;
import org.bbs.dao.IAdministratorDao;
import org.bbs.dao.ICollectionDao;
import org.bbs.dao.ICommentDao;
import org.bbs.dao.IHistoryDao;
import org.bbs.dao.ILikeDao;
import org.bbs.dao.IOrdinaryUserDao;
import org.bbs.dao.IPostDao;
import org.bbs.entity.Administrator;
import org.bbs.entity.Collection;
import org.bbs.entity.Comment;
import org.bbs.entity.History;
import org.bbs.entity.Like;
import org.bbs.entity.OrdinaryUser;
import org.bbs.entity.Post;
import org.bbs.entity.Recommend;
import org.bbs.util.GetTime;
import org.bbs.util.ItemCF;
import org.bbs.util.ItemStatic;
import org.bbs.util.UserCF;

import com.sun.jndi.ldap.LdapClient;
import com.sun.org.apache.bcel.internal.generic.AllocationInstruction;

import sun.launcher.resources.launcher;
import sun.text.resources.el.CollationData_el;

public class test {
	
	public static void main(String[] args) {
//		IOrdinaryUserDao ordinaryUserDao = new OrdinaryUserDaoImpl();
//		IPostDao postDao = new PostDaoImpl();
//		int users = ordinaryUserDao.getTotalCount();
//		int posts = postDao.getTotalCount();
//		Recommend recommend = new Recommend();
//		List<Integer> recs = recommend.getRecommend(users, posts, 8388, 3, 5);
////		for(Object i:recs){
////			System.out.println(i);
////		}
////		long start = System.currentTimeMillis();
////		ItemCF itemCF = new ItemCF(users, posts, 8388, 3);
////		long end = System.currentTimeMillis();
////		System.out.println("Time:" + (end - start));
////		int[] recs = ItemStatic.recommend(users, posts, 8388, 3, 5);
//		for(Object i:recs){
//			System.out.println(i);
//		}
	}
	
}
