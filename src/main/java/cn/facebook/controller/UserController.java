package cn.facebook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cn.facebook.doman.User;
import cn.facebook.service.UserService;
import cn.facebook.utils.PasswordUtil;

@Controller
@SessionAttributes("user")
public class UserController {
	private final String SESSION_USER = "user";

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerPage() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public String register(@RequestBody User user, Model model,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (userService.checkEmail(user.getEmail())) {
			json.put("error", "该邮箱已经被注册过了，不能使用！");
			return json.toString();
		}
		user.setPassword(PasswordUtil.passwordEncoder(user.getPassword(),
				user.getEmail()));
		boolean b = userService.creatUser(user);
		if (b) {
			User u = userService.queryUser(user.getEmail());
			// 写入session
			model.addAttribute(SESSION_USER, u);
			json.put("ok", "恭喜，注册成功！");
			json.put("next", request.getContextPath() + "/welcome");
		} else {
			json.put("error", "注册失败！");
			json.put("next", "");
		}
		return json.toString();
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView toUserCenterPage() {
		return new ModelAndView("userCenter");
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public String checkEmail(@RequestParam(required = true) String email) {
		System.out.println(email);
		boolean b = userService.checkEmail(email);
		JSONObject json = new JSONObject();
		if (b) {
			json.put("error", "该邮箱已被注册");
		} else {
			json.put("ok", "该邮箱可以注册");
		}
		return json.toString();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(required = true) String email,
			@RequestParam(required = true) String password, Model model,
			HttpSession session) {
		ModelAndView view = new ModelAndView();
		User user = userService.queryUser(email);

		if (user != null
				&& user.getPassword().equals(
						PasswordUtil.passwordEncoder(user.getPassword(),
								user.getEmail()))) {

			System.out.println("登陆成功");

			// 写入session
			model.addAttribute(SESSION_USER, user);

			// 如果需要创建cookie

			view.setViewName("redirect:/welcome");
		} else {
			System.out.println("登陆失败");
			view.setViewName("redirect:/");
		}
		return view;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Model model, SessionStatus status) {
		status.setComplete();
		return new ModelAndView("redirect:/");
	}
}
