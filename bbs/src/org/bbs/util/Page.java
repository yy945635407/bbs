package org.bbs.util;

import java.util.List;


//分页显示所需帮助类
public class Page {
	//分页显示所需数据
		private int currentPage ;//当前页 
		private int pageSize;//当前页显示数据量
		private int totalCount;//总数据量 
		private int totalPage;//总页数 
		
		public Page() {}
		public Page(int currentPage, int pageSize, List<Object> data, int totalCount) {
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.totalCount = totalCount;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
			//自动计算总页数
			this.totalPage = (this.totalCount%this.pageSize==0)?(this.totalCount/this.pageSize):(this.totalCount/this.pageSize+1);
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount){
			this.totalCount=totalCount;
		}
		public int getTotalPage() {
			return totalPage;
		}
		@Override
		public String toString() {
			String string = this.currentPage+"--"+this.pageSize+"--"+this.totalCount+"--"+this.totalPage;
			return string;
		}
		
}
