package com.yj.ws.controller.back.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.yj.ws.pojo.SysMenu;
import com.yj.ws.pojo.SysUsers;
import com.yj.ws.service.SysMenuService;
import com.yj.ws.service.SysUsersService;
import com.yj.ws.service.imp.SysUsersServiceImpl;
import com.yj.ws.util.common.CommonConstant;
import com.yj.ws.util.common.MethodUtil;
/**
 * 
 * @author yanjun
 *
 */

@Controller
@RequestMapping("admin")
public class SysUserController extends BaseController{
	@Autowired
	private SysUsersService usersService;
	@Autowired 
    private SysMenuService sysMenuService;
	@RequestMapping("/sysLogin")
	public String sysLogin(HttpServletResponse response,HttpServletRequest request,String userName,String passWord){
		SysUsers sysUser=new SysUsers();
		if(userName!=null && userName.length()>0){
			sysUser.setUserName(userName);
			sysUser.setPassWord(passWord);
		}else{
			sysUser=null;
		}
		SysUsers users=(SysUsers) request.getSession().getAttribute(CommonConstant.SESSION_USER);
		if(users!=null && sysUser==null){
			List<SysMenu> glist=sysMenuService.showAllModel(1,users,"");
			String menus=getMunes(request,glist);
			request.setAttribute("menus", menus);
			request.setAttribute("glist", glist);
			request.setAttribute("userName", users.getUserName());
			//request.getSession().setAttribute(CommonConstant.SESSION_USER, users);
		//	request.setAttribute("clisy", clisy);	
			return "page/admin/mains/home";
		}else{
			if(users!=null){
				users=null;
				request.getSession().setAttribute(CommonConstant.SESSION_USER,users);
			}
			if(sysUser==null || sysUser.getUserName()==null || sysUser.getPassWord()==null){
				return "page/admin/login";
			}
			//System.out.println(sysUser.getUserName());
			String userPwd=MethodUtil.getMD5(sysUser.getPassWord(), null, 1);
			//users.setPassWord(userPwd);
			List<SysUsers> list=usersService.loginUsers(sysUser.getUserName(), userPwd);
			if(list.size()==1){ 				//登录成功
				users=list.get(0);
				//int right=users.get
				if(users.getPassWord()!=null && users.getPassWord().equals(users.getPassWord())){ 				//登录成功
					request.getSession().setAttribute(CommonConstant.SESSION_USER,users);
					request.getSession().setAttribute("users", users);
					List<SysMenu> glist=sysMenuService.showAllModel(1,users,"");
					String menus=getMunes(request,glist);
					request.setAttribute("menus", menus);
					request.setAttribute("glist", glist);
					request.setAttribute("userName", users.getUserName());
					return "page/admin/mains/home";
				}else{
					return "page/admin/login";
				}
			}else{
				return "page/admin/login";
			}
		}
		
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		return "page/admin/login";
	}
	@RequestMapping("/getMunes")
	public String getMunes(HttpServletRequest request,List<SysMenu> glist){
		/**前台菜单格式�?拼出菜单表示
		 * [{id:'',menu:[{text:'',items:[{id:'',text:'',href:''}] }]},{id:'',menu:[{text:'',itenms:[{id:'',text:'',href:''}]}]} ]
		 */
		String path = request.getContextPath()+"/";
		//得到路径
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		SysUsers users=(SysUsers) request.getSession().getAttribute(CommonConstant.SESSION_USER);
		String menuId="";
		StringBuffer sb=new StringBuffer("");
		sb.append("[{id:'1',menu:[{text:'首页',items:[{id:'1',text:'首页',href:'"+path+"admin/mians/main.jsp'}] }]}");
		//List<SysMenu> glist=sysMenuService.showAllModel(1,users,menuId);
		if(glist!=null && glist.size()>0){
							//�?��
			//int i=0;						//判断是否在前面加“，�?如果大于0�?
			for (SysMenu sysMenu : glist) {
				sb.append(",{id:'"+sysMenu.getMenuId()+"',");
				sb.append("menu:[{text:'"+sysMenu.getMenuName()+"',");
				sb.append("items:[");
				//获取当前菜单子菜�?
				menuId=sysMenu.getMenuId();
				List<SysMenu> clisy=sysMenuService.showAllModel(2,users,menuId);
				int j=0;					//判断子菜�?
				for (SysMenu menu : clisy) {
					if(j>0){
						sb.append(",{id:'"+menu.getMenuId()+"',text:'"+menu.getMenuName()+"',href:'"+path+menu.getMenuURL()+"'}");
					}else{
						sb.append("{id:'"+menu.getMenuId()+"',text:'"+menu.getMenuName()+"',href:'"+path+menu.getMenuURL()+"'}");
					}
					j++;
				}
				sb.append("]");
				sb.append("}]");
				sb.append("}");
				//i++;
			}
							//结束
		}
		sb.append("]");
		JSONObject json=new JSONObject();
		json.put("menus", sb.toString());
		return sb.toString();
	}
	//查询�?��用户
	@RequestMapping("/queryUsers")
	public String queryUsers(String curPage,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (MethodUtil.isEmpty(curPage)) {
			curPage = "0";// 当前页数
		}
		Integer count=usersService.getUserCount(map);
		//分页
		String limit = this.getLimit(Integer.parseInt(curPage), count);
		map.put("limit", limit);
		List<SysUsers> queryList=usersService.getQueryUser(map);
		request.setAttribute("list", queryList);
		// set分页参数
		this.initPage(request, Integer.parseInt(curPage), null, count);
		return "page/admin/users/users_list";
	}
	/**
	 * 退出系统
	 * @param curPage
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(String curPage,HttpServletRequest request){
		request.getSession().setAttribute(CommonConstant.SESSION_USER,null);
		return "page/admin/login";
	}
	//去查看用�?
	@RequestMapping("/toQuerys")
	public String toQuery(){
		return "page/admin/users/users";
	}
	@RequestMapping(value="/logins",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String logins(HttpServletRequest request){
		Map<String, Object> map=new HashMap();
		map.put("ss", "sssss");
		List list=new ArrayList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		//return "";
		return new Gson().toJson(list);
	}
}
















