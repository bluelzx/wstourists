package com.yj.ws.pojo;

public class SysMenu {
	private String menuId;   //
	private String menuTag;		//模块标签(索引)
	private String menuName;	//模块名称
	private String menuURL;		//模块页面链接
	private String menuIcon;	//模块图片链接
	private Integer menuFlag;	//模块类别(0关闭不使用，1模块，2菜单，4有链接的页面，8特殊按钮，16快捷按钮,32只是页面功能)
	private String menuMenuOpen;  //作为菜单时链接打开方式(是在当前窗口打开，还是新开窗口，还是在某Iframe中打开)
	private Integer menuMenuView; //是否显示在菜单中(是否显示在菜单中（模块、链接设置为1显示，按钮设置为0不显示）)
	private String menuTabPage;		//此页有哪些Tab页(模块编号,模块编号,……,模块编号)
	private Integer menuOrder;		//显示排序(菜单类/按钮类/页面标签/快捷按钮 数字小的排在前面，
	private Integer menuRightFlag;	//模块类别(0关闭不使用，1模块，2菜单，4有链接的页面，8特殊按钮，16快捷按钮,32只是页面功能)
	private Integer menuData;		//是否需要设置数据权限(0不需要，1需要  此字段只能在数据库中手动修改  2所有数据 )
	private Integer menuLog;		//需要记录日志的操作(0不需要，需要记录（1查询，2增加，4修改，8删除）等操作)
	private Integer isSecurity;		//安全设置标志(0否1进入此页要带加密字串过来DES 2)
	private String pagename;		//页面名称(用于安全判断)
	private String menuUPRight;		//此页面所需要的权限(子页面（）ModFlag＝32）需要上级页面所设置的权限 Modid；权限；Modid；权限，用于进入当前页面的检查，加密字串的参数（解决同一个页在多个模块被调用的情况）)
	private String branchField;		//数据表中是否有网点字段名(用于是否需要按网点设置权限)
	private String partId;			//对应功能
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuTag() {
		return menuTag;
	}
	public void setMenuTag(String menuTag) {
		this.menuTag = menuTag;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuURL() {
		return menuURL;
	}
	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public Integer getMenuFlag() {
		return menuFlag;
	}
	public void setMenuFlag(Integer menuFlag) {
		this.menuFlag = menuFlag;
	}
	public String getMenuMenuOpen() {
		return menuMenuOpen;
	}
	public void setMenuMenuOpen(String menuMenuOpen) {
		this.menuMenuOpen = menuMenuOpen;
	}
	public Integer getMenuMenuView() {
		return menuMenuView;
	}
	public void setMenuMenuView(Integer menuMenuView) {
		this.menuMenuView = menuMenuView;
	}
	public String getMenuTabPage() {
		return menuTabPage;
	}
	public void setMenuTabPage(String menuTabPage) {
		this.menuTabPage = menuTabPage;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Integer getMenuRightFlag() {
		return menuRightFlag;
	}
	public void setMenuRightFlag(Integer menuRightFlag) {
		this.menuRightFlag = menuRightFlag;
	}
	public Integer getMenuData() {
		return menuData;
	}
	public void setMenuData(Integer menuData) {
		this.menuData = menuData;
	}
	public Integer getMenuLog() {
		return menuLog;
	}
	public void setMenuLog(Integer menuLog) {
		this.menuLog = menuLog;
	}
	public Integer getIsSecurity() {
		return isSecurity;
	}
	public void setIsSecurity(Integer isSecurity) {
		this.isSecurity = isSecurity;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public String getMenuUPRight() {
		return menuUPRight;
	}
	public void setMenuUPRight(String menuUPRight) {
		this.menuUPRight = menuUPRight;
	}
	public String getBranchField() {
		return branchField;
	}
	public void setBranchField(String branchField) {
		this.branchField = branchField;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	
}





