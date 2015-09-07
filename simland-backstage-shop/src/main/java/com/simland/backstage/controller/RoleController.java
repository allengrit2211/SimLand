package com.simland.backstage.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.core.base.Constants;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.purview.entity.Power;
import com.simland.core.module.purview.entity.Role;
import com.simland.core.module.purview.entity.RolePower;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.purview.service.IPowerService;
import com.simland.core.module.purview.service.IRolePowerService;
import com.simland.core.module.purview.service.IRoleService;

@Controller
public class RoleController {

	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPowerService powerService;

	@Autowired
	private IRolePowerService rolePowerService;

	@RequestMapping(value = "/purview/roleList")
	public String roleList(HttpServletRequest request, Model model, PageView pageView) {

		String nameLike = request.getParameter("nameLike");
		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("sid", sessionShop.getSid());
		if (Utils.isObjectNotEmpty(nameLike))
			param.put("nameLike", nameLike);

		int totalRecord = roleService.getRoleCount(param);
		if (totalRecord == 0) {
			return "purview/roleList";
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List list = roleService.getSplitRoleList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		model.addAttribute("nameLike", nameLike);
		return "purview/roleList";
	}

	@RequestMapping(value = "/purview/roleAddShow")
	public String roleAddShow(HttpServletRequest request, Model model) {

		Role role = new Role();

		List<Power> plist = powerService.getPowerRecursiveList(null);
		Map<String, String> checkMap = new HashMap<String, String>();

		String id = request.getParameter("id");
		if (Utils.isObjectNotEmpty(id)) {
			Map param = new HashMap();
			param.put("rid", id);
			List<RolePower> list = rolePowerService.getRolePowerList(param);

			for (int i = 0; Utils.isObjectNotEmpty(list) && i < list.size(); i++) {
				RolePower rp = list.get(i);
				if (Utils.isObjectNotEmpty(rp.getPid()))
					checkMap.put(String.valueOf(rp.getPid()), String.valueOf(rp.getPid()));
			}

			param.clear();
			param.put("id", id);
			role = roleService.getRole(param);

		}

		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("checkMap", checkMap);
		model.addAttribute("plist", plist);
		model.addAttribute("msg", request.getParameter("msg"));
		return "purview/roleAdd";
	}

	/***
	 * 新增角色
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/purview/roleAdd")
	public String roleAdd(HttpServletRequest request, Model model) {

		Role role = new Role();

		String[] powerChk = request.getParameterValues("powerChk");
		String name = request.getParameter("name");
		String id = request.getParameter("id");

		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);
		
		model.addAttribute("role", role = new Role(null, name, powerChk));
		role.setSid(sessionShop.getSid());

		if (!validate(powerChk, name, model)){
			roleAddShow(request,model);
			return "purview/roleAdd";
		}
		int idv = 0;
		if (Utils.isObjectEmpty(id) || (idv = Utils.strToInteger(id)) == 0) {
			int f = roleService.insertRole(role, getPowerList(powerChk));
			model.addAttribute("msg", f > 0 ? "新增成功" : "新增失败");
			return "redirect:/purview/roleAddShow?id=" + role.getId();
		} else {
			role.setId(idv);
			int f = roleService.updateRole(role, getPowerList(powerChk));
			model.addAttribute("msg", f > 0 ? "修改成功" : "修改失败");
			return "redirect:/purview/roleAddShow?id=" + role.getId();
		}

	}

	private List<Integer> getPowerList(String[] powerChk) {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; Utils.isObjectNotEmpty(powerChk) && i < powerChk.length; i++) {
			list.add(Utils.strToInteger(powerChk[i]));
		}
		return list;
	}

	/***
	 * 验证
	 * 
	 * @param powerChk
	 * @param name
	 * @param model
	 * @return
	 */
	public boolean validate(String[] powerChk, String name, Model model) {

		if (Utils.isObjectEmpty(name)) {
			model.addAttribute("msg", "名称不能为空");
			return false;
		}

		if (Utils.isObjectEmpty(powerChk) || powerChk.length == 0) {
			model.addAttribute("msg", "没有选择权限不能提交");
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/Role/update")
	public String update() {
		return "/Role/update";
	}

}
