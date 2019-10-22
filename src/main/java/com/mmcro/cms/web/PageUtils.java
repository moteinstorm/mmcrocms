package com.mmcro.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * 分页工具类
 *  
 */
public final class PageUtils {
	
	
	/**
	 * 
	 * @param page 当前第几页
	 * @param pages 总页数
	 * @param url 调用的url  list?name=12&sex=男&page=3
	 * 	 * @param pageSize 显示多少页
	 * @return
	 */
	  public static String pageLoad(int page, int pages, String url, int pageSize)
      {
          if (page< 1) page= 1;
          if (page> pages) page= pages;
          // 根据url是否参数,改变拼接的符合
          String flag = url.indexOf("?") != -1 ? "&" : "?";
          StringBuilder sb = new StringBuilder();
          sb.append("<nav aria-label='Page navigation example'>");
          //String.format("%1$s%2$s", "abc","123");
          //'url?page=1'   ,url, flag,"page" ,page- 1 < 1 ? 1 : page- 1)
          sb.append(String.format("<ul class='pagination'><li class='page-item'><a class='page-link'  href='javascript:void(0)' data='%1$s%2$s%3$s=%4$s' aria-label='Previous'>  <span aria-hidden='true'>&laquo;</span></a></li>" ,url, flag,"page" ,page- 1 < 1 ? 1 : page- 1));
          //设开始页为1
          int beginPage = 1;
          //中间页为 pageSize/2
          int midPage = pageSize / 2;
          if (page> midPage)
          {
              beginPage = page- midPage;
          }
          for (int i = 0; i < pageSize; i++)
          {
        	  //当前页不加链接
              sb.append(String.format("<li class='page-item'><a  class='page-link'  href='javascript:void(0)' data='%1$s%2$s%3$s=%4$s' >%4$s</a></li>" ,url, flag,"page" ,beginPage + i));
              //如果后面的页数大于总页数，退出循环
              if (beginPage + i >= pages)
              {
                  break;
              }
          }
          sb.append(String.format("<li class='page-item'><a  class='page-link' href='javascript:void(0)' data='%1$s%2$s%3$s=%4$s' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>" ,url, flag,"page" ,page+ 1 >= pages ? pages : page+ 1));
          sb.append("</ul></nav>");
          return sb.toString();
      }
	  
	
	/**
	 * 分页面显示
	 * @param request 
	 * @param url      请求的mapping路径
	 * @param pageSize 每页显示的数据条数
	 * @param list     显示的list集合
	 * @param listCount 一共有多少条数据
	 * @param page     当前页码
	 */
	public static void page(HttpServletRequest request, String url, Integer pageSize, List<?> list, Long listCount, Integer page) {
		// 通过符合要求的总条数和页面显示数来计算总页数
		long pageCount =  (listCount/pageSize + (listCount%pageSize == 0 ? 0 : 1));
		//System.out.println("pageCount=="+pageCount);
		// 判断url上是否有?号，如果有，后面通过&符号进行连接，否则通过?进行连接
		String flag = url.indexOf("?") != -1 ? "&" : "?";
		
		
		
		// 分页的首页、上一页、下一页、末页
		String first = "";
		String prev ="";
		String next = "";
		String last = "";
		
		if(page != 1) {
			first = "<a href='"+request.getContextPath()+url+flag+"page=1'>首页</a>";
		} else {
			first = "首页";
		}
		
		if(page > 1) {
			prev = "<a href='"+request.getContextPath()+url+flag+"page="+(page-1)+"'>上一页</a>";
		} else {
			prev = "上一页";
		}
		
		if(page < pageCount) {
			next = "<a href='"+request.getContextPath()+url+flag+"page="+(page + 1)+"'>下一页</a>";
		} else {
			next = "下一页";
		}
		
		if(page != pageCount) {
			last = "<a href='"+request.getContextPath()+url+flag+"page="+pageCount+"'>末页</a>";
		} else {
			last = "末页";
		}
		request.setAttribute("page", "共有"+listCount+"条数据"+" 当前为第"+page+"页 &nbsp;"+first+" "+prev+" "+next+" "+last);
	}
	
}
